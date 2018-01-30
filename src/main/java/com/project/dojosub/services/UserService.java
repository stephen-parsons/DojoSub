package com.project.dojosub.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import com.project.dojosub.repositories.UserRepository;
import com.project.dojosub.models.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
	private UserRepository userRepository;
	private BCryptPasswordEncoder bcrypt;	

	public UserService(UserRepository userRepository){
		this.userRepository=userRepository;
		this.bcrypt=encoder();
	}

	public BCryptPasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}

	public boolean isMatch(String password,String dbPassword){
		if(bcrypt.matches(password,dbPassword)){
			return true;
		}else{
			return false;
		}
	}

	public void login(HttpSession s,long id){s.setAttribute("id",id);}
	
	public void logout(HttpSession s){s.setAttribute("id",null);}
	
	public String redirect(){return "redirect:/users/new";}
	
	public boolean isValid(HttpSession session){
		if(session.getAttribute("id") == null){return false;}
		else{return true;}
	}

	public User create(User user){
		user.setPassword(bcrypt.encode(user.getPassword()));
		return this.userRepository.save(user);
	}

	public ArrayList<User> all(){
		return (ArrayList<User>) this.userRepository.findAll();
	}

	public User find(long id){
		return (User) this.userRepository.findOne(id);
	}

	public User findByEmail(String email){
		return (User) this.userRepository.findByEmail(email);
	}

	public void update(User user){
		this.userRepository.save(user);
	}

	public void destroy(long id){
		this.userRepository.delete(id);
	}
	public List<User> findAllUsersNotAdmin(){
		return this.userRepository.findAllUsersNotAdmin();
	}

	public String findNextDueDate(User user){
		SimpleDateFormat day1formatter = new SimpleDateFormat("MMMM' 'd'st, 'yyyy"); 
		SimpleDateFormat day2formatter = new SimpleDateFormat("MMMM' 'd'nd, 'yyyy"); 
		SimpleDateFormat day3formatter = new SimpleDateFormat("MMMM' 'd'rd, 'yyyy"); 
		SimpleDateFormat daysingleformatter = new SimpleDateFormat("MMMM' 'd'th, 'yyyy"); 
		SimpleDateFormat daydoubleformatter = new SimpleDateFormat("MMMM' 'dd'th, 'yyyy"); 
		SimpleDateFormat day21formatter = new SimpleDateFormat("MMMM' 'dd'st, 'yyyy"); 
		SimpleDateFormat day22formatter = new SimpleDateFormat("MMMM' 'dd'nd, 'yyyy"); 
		SimpleDateFormat day23formatter = new SimpleDateFormat("MMMM' 'dd'rd, 'yyyy"); 
		SimpleDateFormat day31formatter = new SimpleDateFormat("MMMM' 'dd'st, 'yyyy"); 
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.DAY_OF_MONTH) > user.getDueDate()){
			cal.add(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH, user.getDueDate());
		}
		else{
			cal.set(Calendar.DAY_OF_MONTH, user.getDueDate());
		}
		String date = "";
		// CHECK FOR CORRECT FORMATTING
		if (cal.get(Calendar.DAY_OF_MONTH) == 1){
			date = day1formatter.format(cal.getTime());
		}
		else if (cal.get(Calendar.DAY_OF_MONTH) == 2){
			date = day3formatter.format(cal.getTime());
		}
		else if (cal.get(Calendar.DAY_OF_MONTH) == 3){
			date = day3formatter.format(cal.getTime());
		}
		else if (cal.get(Calendar.DAY_OF_MONTH) == 21){
			date = day21formatter.format(cal.getTime());
		}
		else if (cal.get(Calendar.DAY_OF_MONTH) == 22){
			date = day2formatter.format(cal.getTime());
		}
		else if (cal.get(Calendar.DAY_OF_MONTH) == 23){
			date = day23formatter.format(cal.getTime());
		}
		else if (cal.get(Calendar.DAY_OF_MONTH) == 31){
			date = day31formatter.format(cal.getTime());
		}
		else if (cal.get(Calendar.DAY_OF_MONTH) > 4 || cal.get(Calendar.DAY_OF_MONTH) < 10){
			date = daysingleformatter.format(cal.getTime());
		}
		else if (cal.get(Calendar.DAY_OF_MONTH) > 9 || cal.get(Calendar.DAY_OF_MONTH) < 21){
			date = daydoubleformatter.format(cal.getTime());
		}
		return date;
	}
}
