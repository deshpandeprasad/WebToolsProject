package com.lib.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lib.dao.BookDAO;
import com.lib.dao.UserDAO;
import com.lib.pojo.Book;
import com.lib.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class EmployeeController {
	
// #################################### EMPLOYEE DASHBOARD ############################################	
	@GetMapping("/employee-dashboard.htm")
    public String getEmpMenu(Model model) {
		

        return "employee/employee-dashboard";
    }
	
// #################################### BOOK CATALOG ############################################	
	
	@GetMapping("/books.htm")
    public String getBooks(Model model,BookDAO bookdao,HttpServletRequest request) throws Exception {
		
		HttpSession session=request.getSession();
		List<Book> books = bookdao.getAllBooks();
		model.addAttribute("books", books);
        return "employee/books";
    }
	
// #################################### ADD BOOKS ############################################
	
		@GetMapping("/add-books.htm")
		public String addBook(ModelMap model,Book book,HttpServletRequest request) {
			// command object
//		model.addAttribute("book", book);
			
		HttpSession session=request.getSession();
		model.addAttribute("book", book);

			// return form view
			return "employee/add-books";
		}
		
		@PostMapping("/add-books.htm")
		public String saveBook(@ModelAttribute("book") Book book, BindingResult result, SessionStatus status, BookDAO bookdao,Model model) throws Exception {
			
				
				System.out.println(book.getIsbn());
				System.out.println(book.getTitle());
				System.out.println(book.getAuthor());
				
				book.setReadyForPickup(false);
				
				
				if(bookdao.isbnExists(book.getIsbn()))
				{
					String isbnExistsErr="ISBN already exists";
					System.out.println(isbnExistsErr);
					model.addAttribute("isbnExistsErr",isbnExistsErr);
					return "employee/add-books";
				}
				
				String fileName = "img_" + System.currentTimeMillis() + "" + new Random().nextInt(100000000) + "" + new Random().nextInt(100000000) + ".jpg";
				book.setImagePath(fileName);
				try {
					book.getImageFile().transferTo(new File("src/main/webapp/images/" + fileName));
				} catch (IllegalStateException e1) {
					System.out.println("IllegalStateException: " + e1.getMessage());
				} catch (IOException e1) {
					System.out.println("IOException: " + e1.getMessage());
				}
				
				
				bookdao.save(book);
				
				if(result.hasErrors())
				{
					return "employee/add-books";
				}
				
				status.setComplete(); //mark it complete
				
			
			return "employee/book-add-success";
		}
		
// #################################### CONFIRM EDIT ############################################	
		
		@GetMapping("/confirm-edit.htm")
		public String getConfirmEdit(Model model, HttpServletRequest request, BookDAO bookdao, UserDAO userdao)
				throws Exception {
		
			HttpSession session = request.getSession();
			String bookid = request.getParameter("bookId");

			
			System.out.println("IN confirm edit method");
			long bookId = Long.parseLong(bookid);
			Book book = bookdao.getBookById(bookId);

			model.addAttribute(book);
			
			return "employee/confirm-edit";
		}
		
		
		@PostMapping("/confirm-edit.htm")
		public String postConfirmEdit(SessionStatus status,
				BookDAO bookdao, HttpServletRequest request, UserDAO userdao) throws Exception {
			
			System.out.println("############### EMPLOYEE: Confirm EDIT Post Mapping ###############");
			HttpSession session = request.getSession();

			String id = request.getParameter("id");
			long bookid = Long.parseLong(id);
			
			
			Book book=bookdao.getBookById(bookid);
			
			String title=request.getParameter("title");
			String author=request.getParameter("author");
			String removeReservation=request.getParameter("removeReservation");
			String removeCurrentUser=request.getParameter("removeCurrentUser");
			
						
			book.setBookId(book.getBookId());
			book.setIsbn(book.getIsbn());
			book.setAuthor(author);
			book.setTitle(title);
			book.setBookingStartDate(book.getBookingStartDate());
			book.setBookingEndDate(book.getBookingEndDate());
			book.setReturnDate(book.getReturnDate());
			book.setReadyForPickup(book.getReadyForPickUp());
			
			if(removeReservation !=null) {
				book.setReservedByUser(null);
				book.setBookingStartDate(null);
				book.setBookingEndDate(null);
				book.setReturnDate(null);
			}
			else {
				book.setReservedByUser(book.getReservedByUser());
				book.setBookingStartDate(book.getBookingStartDate());
				book.setBookingEndDate(book.getBookingEndDate());
				book.setReturnDate(book.getReturnDate());
			}
			
			if(removeCurrentUser !=null) {
				book.setTheUser(null);
				book.setBookingStartDate(null);
				book.setBookingEndDate(null);
				book.setReturnDate(null);
			}
			else {
				book.setTheUser(book.getTheUser());
				book.setBookingStartDate(book.getBookingStartDate());
				book.setBookingEndDate(book.getBookingEndDate());
				book.setReturnDate(book.getReturnDate());
			}
			
//			SET IMAGE PATH
			book.setImagePath(book.getImagePath());
			
			bookdao.update(book);
			
			status.setComplete();
			
			return "employee/edit-success";
		}
		
// #################################### CONFIRM DELETE ############################################
		
		@GetMapping("/confirm-delete.htm")
		public String getConfirmDelete(Model model, HttpServletRequest request, BookDAO bookdao, UserDAO userdao)
				throws Exception {
		
			HttpSession session = request.getSession();
			String bookid = request.getParameter("bookId");

			
			System.out.println("IN confirm delete method");
			long bookId = Long.parseLong(bookid);
			Book book = bookdao.getBookById(bookId);

			model.addAttribute(book);
			
			return "employee/confirm-delete";
		}

		@PostMapping("/confirm-delete.htm")
		public String postConfirmDelete(SessionStatus status,
				BookDAO bookdao, HttpServletRequest request, UserDAO userdao) throws Exception {
			
			System.out.println("############### EMPLOYEE: Confirm EDIT Post Mapping ###############");
			HttpSession session = request.getSession();

			String id = request.getParameter("id");
			long bookid = Long.parseLong(id);
			
			
			Book book=bookdao.getBookById(bookid);
			
			
			bookdao.delete(book);
			
			status.setComplete();
			
			return "employee/delete-success";
		}
		
// #################################### USER RESERVATIONS ############################################	
	@GetMapping("/book-reservations.htm")
    public String getReservations(Model model,BookDAO bookdao,HttpServletRequest request,SessionStatus status) throws Exception {
		
//		HttpSession session=request.getSession();
//		String username = request.getParameter("username");
//		model.addAttribute("username", username);
		
		
		LinkedHashMap<Book,String> reservedBooks = bookdao.getReservedAllBooks();
		
		if(reservedBooks !=null) {
			System.out.println("Got resreved books");
		}

		model.addAttribute("books", reservedBooks);
//		request.setAttribute("books", reservedBooks);
        return "employee/book-reservations";
        
    }
	
	


 // #################################### CONFIRM PICKUP ############################################
	
	@GetMapping("/confirm-pickup.htm")
	public String getConfirmPickup(Model model, HttpServletRequest request, BookDAO bookdao, UserDAO userdao)
			throws Exception {
		
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String bookid = request.getParameter("bookId");

		long bookId = Long.parseLong(bookid);
		Book book = bookdao.getBookById(bookId);
		User user = userdao.getUserByUsername(username);
//		model.addAttribute("book", book);
//		model.addAttribute("username", username);
		
		model.addAttribute("book", book);
		model.addAttribute("username", username);

		System.out.println("userid:" + username);


		return "employee/confirm-pickup";
		
	}
	
	@PostMapping("/confirm-pickup.htm")
	public String setConfirmReservation(SessionStatus status,
			BookDAO bookdao, HttpServletRequest request, UserDAO userdao) throws Exception {
		
		System.out.println("############### EMPLOYEE: Confirm Pickup Post Mapping ###############");
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		System.out.println("Post username:" + username);
		User user = userdao.getUserByUsername(username);

		String id = request.getParameter("id");
		long bookid = Long.parseLong(id);
		
//		String bookingStartDate = request.getParameter("bookingStartDate");
//		System.out.println(bookingStartDate);
//
//		String bookingEndDate = request.getParameter("bookingEndDate");
//		System.out.println(bookingEndDate);
//
//		String returnDate = request.getParameter("returnDate");
//		System.out.println(returnDate);
//
//		String userid = request.getParameter("username");
//		System.out.println(userid);
		
		Book book=bookdao.getBookById(bookid);
		
		System.out.println(book.getBookId());
		System.out.println(book.getTitle());
		System.out.println(book.getAuthor());
		System.out.println(book.getIsbn());
		System.out.println(book.getBookingStartDate());
//		System.out.print
		
		
		
		book.setBookId(book.getBookId());
		book.setIsbn(book.getIsbn());
		book.setAuthor(book.getAuthor());
		book.setTitle(book.getTitle());
		book.setBookingStartDate(book.getBookingStartDate());
		book.setBookingEndDate(book.getBookingEndDate());
		book.setReturnDate(book.getReturnDate());
		book.setReadyForPickup(false);
		book.setReservedByUser(null);
		book.setTheUser(user);
		
//		SET IMAGE PATH
		book.setImagePath(book.getImagePath());
			
		bookdao.update(book);
		
		status.setComplete();
		return "employee/pickup-success";
	}
	
	
// #################################### RETURNS ############################################
	
	@GetMapping("/book-returns.htm")
    public String getBookReturns(Model model,BookDAO bookdao,HttpServletRequest request) throws Exception {
		
		HttpSession session=request.getSession();
		String username = request.getParameter("username");
		model.addAttribute("username", username);
		
		LinkedHashMap<Book,String> booksInUse = bookdao.getAllBooksInUse();
//		List<Book> booksInUse=bookdao.getAllBooksInUse();
		
		if(booksInUse !=null) {
			System.out.println("Got all books in use");
		}

//		model.addAttribute("books", booksInUse);
		model.addAttribute("books", booksInUse);
        return "employee/book-returns";
    }
	
	// #################################### CONFIRM RETURN ############################################
	
		@GetMapping("/confirm-return.htm")
		public String getConfirmReturn(Model model, HttpServletRequest request, BookDAO bookdao, UserDAO userdao)
				throws Exception {
			
			HttpSession session = request.getSession();
			String username = request.getParameter("username");
			String bookid = request.getParameter("bookId");

			long bookId = Long.parseLong(bookid);
			Book book = bookdao.getBookById(bookId);
			User user = userdao.getUserByUsername(username);
//			model.addAttribute("book", book);
//			model.addAttribute("username", username);
			
			request.setAttribute("book", book);
			request.setAttribute("username", username);

			System.out.println("userid:" + username);


			return "employee/confirm-return";
			
		}
		
		@PostMapping("/confirm-return.htm")
		public String postConfirmReturn(SessionStatus status,
				BookDAO bookdao, HttpServletRequest request, UserDAO userdao) throws Exception {
			
			System.out.println("############### EMPLOYEE: Confirm RETURN Post Mapping ###############");
			HttpSession session = request.getSession();
			String username = request.getParameter("username");
			System.out.println("Post username:" + username);
			User user = userdao.getUserByUsername(username);

			String id = request.getParameter("id");
			long bookid = Long.parseLong(id);
			
//			String bookingStartDate = request.getParameter("bookingStartDate");
//			System.out.println(bookingStartDate);
	//
//			String bookingEndDate = request.getParameter("bookingEndDate");
//			System.out.println(bookingEndDate);
	//
//			String returnDate = request.getParameter("returnDate");
//			System.out.println(returnDate);
	//
//			String userid = request.getParameter("username");
//			System.out.println(userid);
			
			Book book=bookdao.getBookById(bookid);
			
			System.out.println(book.getBookId());
			System.out.println(book.getTitle());
			System.out.println(book.getAuthor());
			System.out.println(book.getIsbn());
			System.out.println(book.getBookingStartDate());
//			System.out.print
			
			
			book.setBookId(book.getBookId());
			book.setIsbn(book.getIsbn());
			book.setAuthor(book.getAuthor());
			book.setTitle(book.getTitle());
			book.setBookingStartDate(null);
			book.setBookingEndDate(null);
			book.setReturnDate(null);
			book.setReadyForPickup(false);
			book.setReservedByUser(null);
			book.setTheUser(null);
			
//			SET IMAGE PATH
			book.setImagePath(book.getImagePath());
			
			bookdao.update(book);
			
			status.setComplete();
			return "employee/return-success";
		}

}

