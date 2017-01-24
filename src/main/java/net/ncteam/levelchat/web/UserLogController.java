package net.ncteam.levelchat.web;

import java.util.Map;
import java.util.List;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.ncteam.levelchat.domain.UsersLog;
import net.ncteam.levelchat.service.UserLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserLogController {
	@Autowired
	@Qualifier("UserLogService")
	private UserLogService userLogService;


	@RequestMapping("/index")
	public String listContacts(Map<String, Object> map) {

		map.put("users_log", new UsersLog());
		map.put("users_logList", userLogService.listUser());

		return "index";
	}
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}
	
	@RequestMapping("/userpage")
	public String userPage() {
		return "userpage";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("users_log") UsersLog user,
			BindingResult result) {

		userLogService.addUser(user);

		return "redirect:/index";
	}
	
	
	
	
	@RequestMapping(value = "/exist", method = RequestMethod.POST)
	public String existUser(@ModelAttribute("credential") UsersLog credential, BindingResult result)
	{
		try
		{
			OutputStream logFile = new FileOutputStream("c:/MyLog.txt");
			logFile.write((credential.getLogin()+" "+credential.getPassword()).getBytes());
		}
		catch(FileNotFoundException e)
		{
			
		}
		catch(IOException e)
		{
			
		}
		if(userLogService.existUser(credential.getLogin(), credential.getPassword()))
		{
			return "redirect:/userpage";
		}
		else
		{
			return "redirect:/index";
		}
	}
	

	@RequestMapping("/delete/{iduserlog}")
	public String deleteContact(@PathVariable("iduserlog") Integer iduserlog) {

		userLogService.removeUser(iduserlog);

		return "redirect:/index";
	}
}
