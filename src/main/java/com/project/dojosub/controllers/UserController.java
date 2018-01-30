package com.project.dojosub.controllers;

import java.security.Principal;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
		
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.dojosub.services.UserService;
import com.project.dojosub.models.User;
import com.project.dojosub.services.SubService;
import com.project.dojosub.models.Sub;


@Controller
@RequestMapping("/users")
public class UserController{
	private UserService us;
	private SubService subService;

	public UserController(UserService us, SubService subService){
		this.us=us;
		this.subService = subService;
	}
	
	// Router for admin or user
	@RequestMapping("")
	public String adminOrUser(HttpSession session){
		User user = us.find((Long)session.getAttribute("id"));
		if (user.getIsAdmin() == true){
			return "redirect:/admin";
		}
		else{
			if (user.getSub() != null){
				return "redirect:/users/profile";
			}
			else{
				return "redirect:users/selection";
			}
		}
	}	
	// Logout on visiting login / registration.
	@RequestMapping("/new")
	public String newUser(@ModelAttribute("user") User user,HttpSession session){
		us.logout(session);
		return "newUser";
	}
	// Create a user.
	@PostMapping("/new")
	public String create(@Valid @ModelAttribute("user") User user,BindingResult res,RedirectAttributes flash,HttpSession session){
		if(res.hasErrors()){			
			flash.addFlashAttribute("errors",res.getAllErrors());
			return "redirect:/users/new";
		}else{

			// Check if passwords match
			if (!user.getPasswordConfirmation().equals(user.getPassword())){
				flash.addFlashAttribute("pwerror","Password and Password confirmation must match!");
				return "redirect:/users/new";
			}	
			else{	

				User exists = us.findByEmail(user.getEmail());

				if(exists == null){
					if (us.all().size() == 0){
						user.setIsAdmin(true);
					}
					else{
						user.setIsAdmin(false);
					}
					User u = us.create(user);
					us.login(session,u.getId());
					return "redirect:/users";
				}else{			
					flash.addFlashAttribute("error","A user with this email already exists.");
					return "redirect:/users/new";				
				}
			}
		}
	}

	@PostMapping("/login")
	public String login(@RequestParam("email") String email,@RequestParam("password") String password,HttpSession session,RedirectAttributes flash){
		if(email.length() < 1){// Dont waste a query.
			flash.addFlashAttribute("error","Email cannot be blank.");
			return "redirect:/users/new";			
		}

		User user = us.findByEmail(email);

		if(user == null){
			flash.addFlashAttribute("error","No user with this email was found.");
			return "redirect:/users/new";
		}else{
			if(us.isMatch(password,user.getPassword())){
				us.login(session,user.getId());
				return "redirect:/users";
			}else{
				flash.addFlashAttribute("error","Invalid Credentials");
				return "redirect:/users/new";								
			}
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpSession s){
		return us.redirect();
	}

	// User Dashboard for Sub selection	
	@RequestMapping("/selection")
	public String selecion(Model model,HttpSession session){
		if(!us.isValid(session)) return us.redirect();
		User user = us.find((Long)session.getAttribute("id"));
		List<Sub> subs = subService.findAllSubsAvailable();
		model.addAttribute("user",user);
		model.addAttribute("subs",subs);
		return "subscriptionPage";
	}

	// SET USER SUB TO SELECTED SUB WITH DUE DATE

	@PostMapping("/sub")
	public String sub_join(@RequestParam("sublist") Long sub_id, @RequestParam("duedatelist") String due_date, HttpSession session){		
		User user = us.find((Long)session.getAttribute("id"));
		Sub sub = subService.find(sub_id);
		user.setDueDate(Integer.parseInt(due_date));
		user.setSub(sub);
		us.update(user);
		return "redirect:/users/profile";
	}

	@RequestMapping("/profile")
	public String profile(Model model,HttpSession session){
		if(!us.isValid(session)) return us.redirect();
		User user = us.find((Long)session.getAttribute("id"));
		model.addAttribute("user",user);
		model.addAttribute("date", us.findNextDueDate(user));
		return "userProfile";
	}
}
