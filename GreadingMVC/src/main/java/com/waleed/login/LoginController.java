package com.waleed.login;

import com.waleed.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.SQLException;

@Controller
@SessionAttributes("ID")

public class LoginController {

	@Autowired
	private StudentService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String handleUserLogin(ModelMap model, @RequestParam int ID,
			@RequestParam String password) throws SQLException {

		int id = ID;
		boolean isValid = service.logIn(id,password);

		if (!isValid) {
			model.put("errorMessage", "Invalid Credentials");
			return "login";
		}
		model.put("ID", id);
		return "options";
	}
}
