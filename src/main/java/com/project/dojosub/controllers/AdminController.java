package com.project.dojosub.controllers;

import java.security.Principal;
import java.util.Date;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.dojosub.models.User;
import com.project.dojosub.services.UserService;
import com.project.dojosub.models.Sub;
import com.project.dojosub.services.SubService;

@Controller
@RequestMapping("/admin")
public class AdminController{
	
	private UserService us;
	private SubService subService;

	public AdminController(UserService us, SubService subService){
		this.us=us;
		this.subService = subService;
	}
	
	@RequestMapping("")
	public String admin_dashboard(HttpSession session, Model model, @ModelAttribute("sub") Sub sub, BindingResult result, RedirectAttributes flash){		
		User user = us.find((Long)session.getAttribute("id"));
		if (user.getIsAdmin() == false){
			return "redirect:/users/";
		}
		else{
			model.addAttribute("admin", user);
			model.addAttribute("users", us.findAllUsersNotAdmin());
			model.addAttribute("subs", subService.all());
		}
		return "adminDashboard";
	}	

	@PostMapping("/sub/create")
	public String sub_create(HttpSession session, @Valid @ModelAttribute("sub") Sub sub, BindingResult result, RedirectAttributes flash){		
		if (result.hasErrors()){
			flash.addFlashAttribute("errors",result.getAllErrors());
			return "redirect:/admin";
		}
		else{
			subService.create(sub);
		}
		return "redirect:/admin";
	}

	@RequestMapping("/sub/deactivate/{id}")
	public String deactivate_sub(@PathVariable("id") Long id){		
		Sub sub = subService.find(id);
		sub.setAvailable(false);
		subService.update(sub);
		return "redirect:/admin";
	}
	@RequestMapping("/sub/activate/{id}")
	public String activate_sub(@PathVariable("id") Long id){		
		Sub sub = subService.find(id);
		sub.setAvailable(true);
		subService.update(sub);
		return "redirect:/admin";
	}		
	@RequestMapping("/sub/delete/{id}")
	public String delete_sub(@PathVariable("id") Long id){		
		subService.destroy(id);
		return "redirect:/admin";
	}	
	
}
