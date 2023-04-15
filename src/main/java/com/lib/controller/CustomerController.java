package com.lib.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lib.dao.BookDAO;
import com.lib.dao.UserDAO;
import com.lib.exception.LibException;
import com.lib.pojo.Book;
import com.lib.pojo.User;
import com.mysql.cj.Session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class CustomerController {

// ################################### DASHBOARD ##########################################################		

	@GetMapping("/customer-dashboard.htm")
	public String getCustomerDashboard(Model model, HttpServletRequest request) {
		return "customer/customer-dashboard";
	}

// ################################### BROWSE BOOKS ##########################################################	

	@GetMapping("/browse-books.htm")
	public String getBrowseBooks(Model model, BookDAO bookdao,UserDAO userdao, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		model.addAttribute("username", username);

		List<Book> books = bookdao.getAllBooks();
		model.addAttribute("books", books);
		
		
		// CHECK TOTAL NO OF BOOKS OF USER
		User user=userdao.getUserByUsername(username);
		List<Book> userbooks = bookdao.getReservedBooksByUser(user);
		userbooks.addAll(bookdao.getBooksInUserByUser(user));
		System.out.println("userbooks:"+userbooks.size());
//		model.addAttribute("userbooks",userbooks);
		request.setAttribute("userbooks", userbooks);
		

		return "customer/browse-books";
	}

// ################################### MY BOOKS ##########################################################

	@GetMapping("/my-books.htm")
	public String getMyBooks(Model model, HttpServletRequest request,BookDAO bookdao,UserDAO userdao) throws Exception {

		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		
		
		User user=userdao.getUserByUsername(username);
		List<Book> books=bookdao.getBooksInUserByUser(user);
		
		if(books!=null)
		{
			System.out.println("My Books exists");
			
		}
		
		model.addAttribute("books",books);
//		request.setAttribute("books",books);
		return "customer/my-books";
	}

// ################################### MY RESERVATIONS ##########################################################

	@GetMapping("/my-reservations.htm")
	public String getMyReservations(Model model, HttpServletRequest request,BookDAO bookdao,UserDAO userdao) throws Exception {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		
		User user=userdao.getUserByUsername(username);
		List<Book> books=bookdao.getReservedBooksByUser(user);
		
		if(books!=null)
		{
			System.out.println("My Reservations exists");
			
		}

		model.addAttribute("books",books);
//		request.setAttribute("books",books);

		return "customer/my-reservations";
	}

// ################################### CONFIRM RESERVATION ##########################################################	

	@GetMapping("/confirm-reservation.htm")
	public String getConfirmReservation(Model model, HttpServletRequest request, BookDAO bookdao, UserDAO userdao,SessionStatus status)
			throws Exception {

		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String bookid = request.getParameter("bookId");

		long bookId = Long.parseLong(bookid);
		Book book = bookdao.getBookById(bookId);
		User user = userdao.getUserByUsername(username);
		model.addAttribute("book", book);

		model.addAttribute("username", username);
		
		model.addAttribute("book", book);

		model.addAttribute("username", username);

		System.out.println("userid:" + username);

		LocalDate bookingStartDate = LocalDate.now();
		LocalDate bookingEndDate = LocalDate.now().plusDays(3);
		LocalDate returnDate = LocalDate.now().plusDays(7);

		model.addAttribute("bookingStartDate", bookingStartDate);
		model.addAttribute("bookingEndDate", bookingEndDate);
		model.addAttribute("returnDate", returnDate);

		return "customer/confirm-reservation";

	}

	@PostMapping("/confirm-reservation.htm")
	public String setConfirmReservation(@ModelAttribute("book") Book book, BindingResult result, SessionStatus status,
			BookDAO bookdao, HttpServletRequest request, UserDAO userdao) throws Exception {

		System.out.println("############### CUSTOMER: Confirm Reservation Post Mapping ###############");
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		System.out.println("Post username:" + username);

		String id = request.getParameter("id");
		long bookid = Long.parseLong(id);

		System.out.println("Bookid:" + id);
		System.out.println("Title" + book.getTitle());
		System.out.println("Author" + book.getAuthor());

		String bookingStartDate = request.getParameter("bookingStartDate");
		System.out.println(bookingStartDate);

		String bookingEndDate = request.getParameter("bookingEndDate");
		System.out.println(bookingEndDate);

		String returnDate = request.getParameter("returnDate");
		System.out.println(returnDate);

		String userid = request.getParameter("username");
		System.out.println(userid);

		User user = userdao.getUserByUsername(username);		

		LocalDate bsd = LocalDate.parse(bookingStartDate);
		LocalDate bed = LocalDate.parse(bookingEndDate);
		LocalDate rd = LocalDate.parse(returnDate);

		book.setBookId(bookid);
		book.setIsbn(book.getIsbn());
		book.setAuthor(book.getAuthor());
		book.setTitle(book.getTitle());
		book.setBookingStartDate(bsd);
		book.setBookingEndDate(bed);
		book.setReturnDate(rd);
		book.setReadyForPickup(true);
		book.setReservedByUser(user);
		
		String imagePath=request.getParameter("imagePath");
		System.out.println("IMGWGE PTAH"+book.getImagePath());
		
		
// 	    Image path
		book.setImagePath(imagePath);
		
		

		bookdao.update(book);

//		if (result.hasErrors()) {
//			List<FieldError> errors = result.getFieldErrors();
//		    for (FieldError error : errors ) {
//		        System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
//		    }
//			return "customer/my-reservations";
//		}
		status.setComplete(); // mark it complete
		return "customer/reservation-success";
	}
	

}
