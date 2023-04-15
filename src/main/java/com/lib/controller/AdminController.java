package com.lib.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lib.dao.BookDAO;
import com.lib.dao.UserDAO;
import com.lib.pojo.Book;
import com.lib.pojo.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class AdminController {
	
	// ################################### DASHBOARD ##########################################################		

		@GetMapping("/admin-dashboard.htm")
		public String getCustomerDashboard(Model model, HttpServletRequest request) {
			return "admin/admin-dashboard";
		}
	
	// ################################### ALL USERS ##########################################################	
		@GetMapping("/users.htm")
		public String getUsers(Model model, BookDAO bookdao,UserDAO userdao, HttpServletRequest request) throws Exception {
			
			List<User> users=userdao.getAllUsers();
			model.addAttribute("users",users);
			return "admin/users";
			
		}
		
	// ################################### EDIT USER ##########################################################	
		@GetMapping("/user-edit.htm")
		public String getUserEdit(Model model, HttpServletRequest request, BookDAO bookdao, UserDAO userdao)
				throws Exception {
		
			HttpSession session = request.getSession();
			String userid=request.getParameter("userId");
			Long userId=Long.parseLong(userid);
			
			User user=userdao.getUserById(userId);

			model.addAttribute("user",user);
			
			return "admin/user-edit";
		}

		@PostMapping("/user-edit.htm")
		public String postUserEdit(SessionStatus status,
				BookDAO bookdao, HttpServletRequest request, UserDAO userdao,Model model) throws Exception {
			
			HttpSession session=request.getSession();
			String id = request.getParameter("id");
			long userid = Long.parseLong(id);
			
			
			// VALIDATE USER INPUTS
			
			boolean errorFlag=false;
			
			System.out.println("userid:"+userid);
			
			User user=userdao.getUserById(userid);
			
			
			String address=request.getParameter("address");
			String contact=request.getParameter("contact");
			
//			String contactErr=validate.validateContact(contact);
//			System.out.println("contactErr:"+contactErr);
//			
//			if(contactErr != "" || contactErr != null)
//			{
//				System.out.println("In Contact Err:");
//				model.addAttribute("contactErr",contactErr);
//				model.addAttribute("user",user);
//				errorFlag=true;
//				
//			}
//			
//			if(address == "" || address==null)
//			{
//				System.out.println("In address");
//				String addressErr="Address cannot be empty";
//				model.addAttribute("addressErr",addressErr);
//				model.addAttribute("user",user);
//				errorFlag=true;
//			}
//			
//			System.out.println("errorflag:"+errorFlag);			
//			if(errorFlag)
//			{
//				return "admin/user-edit";
//			}
			
			user.setUserid(user.getUserid());
			user.setUsername(user.getUsername());
			user.setPassword(user.getPassword());
			user.setRole(user.getRole());
			user.setAddress(address);
			user.setContact(contact);
			
			userdao.update(user);
			
			return "admin/user-edit-success";
			
		}
		
		// ################################### DELETE USER ##########################################################	
				@GetMapping("/user-delete.htm")
				public String getUserDelete(Model model, HttpServletRequest request, BookDAO bookdao, UserDAO userdao)
						throws Exception {
				
					HttpSession session = request.getSession();
					String userid=request.getParameter("userId");
					Long userId=Long.parseLong(userid);
					
					User user=userdao.getUserById(userId);

					model.addAttribute("user",user);
					
					return "admin/user-delete";
				}

				@PostMapping("/user-delete.htm")
				public String postUserDelete(SessionStatus status,
						BookDAO bookdao, HttpServletRequest request, UserDAO userdao,BindingResult result) throws Exception {
					
					HttpSession session = request.getSession();
					String id = request.getParameter("id");
					long userid = Long.parseLong(id);
					
					System.out.println("userid:"+userid);
					
					User user=userdao.getUserById(userid);
					
					if(user!=null)
					{
						System.out.println("found user");
					}
					
					List<Book> booksInUse=bookdao.getBooksInUserByUser(user);
					for(Book book:booksInUse)
					{
						System.out.println("De-assigning book in use:"+book.getTitle());
						book.setTheUser(null);
						book.setBookingStartDate(null);
						book.setBookingEndDate(null);
						book.setReturnDate(null);
						bookdao.update(book);
						
					}
					
					List<Book> reservedBooks=bookdao.getReservedBooksByUser(user);
					for(Book book: reservedBooks)
					{
						System.out.println("De-assigning book reserved:"+book.getTitle());
						book.setReservedByUser(null);
						book.setBookingStartDate(null);
						book.setBookingEndDate(null);
						book.setReturnDate(null);
						bookdao.update(book);
						
					}
					userdao.delete(user);
					
					if (result.hasErrors()) {
						List<FieldError> errors = result.getFieldErrors();
					    for (FieldError error : errors ) {
					        System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
					    }
						return "admin/users";
					}
					status.setComplete();
					
					return "admin/user-delete-success";
					
				}
		
}
