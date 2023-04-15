package com.lib.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lib.dao.UserDAO;
import com.lib.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class LoginController {

	@RequestMapping("/")
	public String home() {
		return "redirect:/login.htm";
	}

// ##################################### LOGIN ######################################	

	@GetMapping("/login.htm")
	public String loginGet(Model model, User user) {

		return "login";
	}

	@PostMapping("/login.htm")
	public String addAdvertPost(@ModelAttribute("user") User user, BindingResult result, SessionStatus status,
			HttpServletRequest request, UserDAO userdao, Model model) {

		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		boolean custFlag = false;
		boolean adminFlag = false;
		boolean empFlag = false;
		try {

			custFlag = userdao.validateCustomer(username, password);
			adminFlag = userdao.validateAdmin(username, password);
			empFlag = userdao.validateEmployee(username, password);
		} catch (Exception e) {
			System.out.println("Error in validate customer method");
		}

		if (custFlag) {
			System.out.println("user is a customer");
			session.setAttribute("username", username);
			return "customer/customer-dashboard";
		} else if (adminFlag) {
			System.out.println("user is a admin");
			session.setAttribute("username", username);
			return "admin/admin-dashboard";
		} else if (empFlag) {
			System.out.println("user is a employee");
			session.setAttribute("username", username);
			model.addAttribute(username);
			return "employee/employee-dashboard";
		}
		String error="Incorrect Username/password";
		model.addAttribute("error",error);
		return "login";
	}

	// ##################################### LOGOUT ######################################

	@GetMapping("/logout.htm")
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/login.htm";
	}

	// ##################################### REGISTRATION ######################################
	
	@GetMapping("/adduser.htm")
	public String addUserGet(ModelMap model, User user) {
		// command object
		model.addAttribute("user", user);

		// return form view
		return "addUserForm";
	}

	@PostMapping("/adduser.htm")
	public String addUserPost(@ModelAttribute("user") User user,HttpServletRequest request, BindingResult result, SessionStatus status,
			UserDAO userdao,Model model) throws Exception {

		System.out.println(user.getUserid());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getRole());

		HttpSession session=request.getSession();
		String username=request.getParameter("username");
				
		User User=userdao.getUserByUsername(username);
		if(User !=null)
		{
			String error="User already exists";
			model.addAttribute("error",error);
			return "addUserForm";
		}

		
		userdao.save(user);

		if (result.hasErrors()) {
			return "addUserForm";
		}
		status.setComplete(); // mark it complete
		return "addedUser";
	}

}