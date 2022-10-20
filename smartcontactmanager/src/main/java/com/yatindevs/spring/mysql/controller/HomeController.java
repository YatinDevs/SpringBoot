package com.yatindevs.spring.mysql.controller;

import javax.servlet.http.HttpSession;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yatindevs.spring.mysql.config.*;
import com.yatindevs.spring.mysql.dao.UserRepository;
import com.yatindevs.spring.mysql.entities_model.User;
import com.yatindevs.spring.mysql.helper.*;

@Controller
public class HomeController {
	
   @Autowired
	private UserRepository userRepository;
	
	
	@RequestMapping("/")
	public String home(Model model) 
	{
		// with help of model we send data to html template
		model.addAttribute("title","Home-Smart Contact Manager");
		return "home";
	}
	

	@RequestMapping("/about")
	public String about(Model model) 
	{
		// with help of model we send data to html template
		model.addAttribute("title","About-Smart Contact Manager");
		return "about";
	}
	
	
	// Register Page
    // sending blank user fields to be filled in Registeration page passing as key
	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Register - Smart Contact Manager");
		model.addAttribute("user", new User());
		return "signup";
	}
	// handler for registering user
		@RequestMapping(value = "/do_register", method = RequestMethod.POST)
		public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result1,
				@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
				HttpSession session) {

			try {

				if (!agreement) {
					System.out.println("You have not agreed the terms and conditions");
					throw new Exception("You have not agreed the terms and conditions");
				}

				if (result1.hasErrors()) {
					System.out.println("ERROR " + result1.toString());
					model.addAttribute("user", user);
					return "signup";
				}

				user.setRole("ROLE_USER");
				user.setEnabled(true);
				user.setImageUrl("default.png");
			

				System.out.println("Agreement " + agreement);
				System.out.println("USER " + user);

				User result = this.userRepository.save(user);

				model.addAttribute("user", new User());

				session.setAttribute("message", new Message("Successfully Registered !!", "alert-success"));
				return "signup";

			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("user", user);
				session.setAttribute("message", new Message("Something Went wrong !! " + e.getMessage(), "alert-danger"));
				return "signup";
			}

		}

	@RequestMapping("/signin")
	public String Login(Model model) 
	{
		// with help of model we send data to html template
		model.addAttribute("title","Login-Smart Contact Manager");
		return "login";
	}
}
