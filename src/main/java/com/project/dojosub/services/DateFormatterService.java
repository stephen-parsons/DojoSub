package com.project.dojosub.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.project.dojosub.models.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DateFormatterService {	

	// GETS DUE DATE FOR USER WITHOUT YEAR
	// USED AS BEAN IN JSP

	public String findNextDueDate(User user){
		SimpleDateFormat day1formatter = new SimpleDateFormat("MMMM' 'd'st'"); 
		SimpleDateFormat day2formatter = new SimpleDateFormat("MMMM' 'd'nd'"); 
		SimpleDateFormat day3formatter = new SimpleDateFormat("MMMM' 'd'rd'"); 
		SimpleDateFormat daysingleformatter = new SimpleDateFormat("MMMM' 'd'th'"); 
		SimpleDateFormat daydoubleformatter = new SimpleDateFormat("MMMM' 'dd'th'"); 
		SimpleDateFormat day21formatter = new SimpleDateFormat("MMMM' 'dd'st'"); 
		SimpleDateFormat day22formatter = new SimpleDateFormat("MMMM' 'dd'nd'"); 
		SimpleDateFormat day23formatter = new SimpleDateFormat("MMMM' 'dd'rd'"); 
		SimpleDateFormat day31formatter = new SimpleDateFormat("MMMM' 'dd'st'"); 
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

	public String modifyCreatedAt(User user){
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
		cal.setTime(user.getCreatedAt());
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
