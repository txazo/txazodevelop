package org.txazo.spring.mvc.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginAction {

	// http://127.0.0.1/spring/login.html?user=root&password=root
	@RequestMapping(value = "/login")
	public String login(String user, String password) {
		if ("root".equals(user) && "root".equals(password)) {
			return "login/login";
		}

		return "login/index";
	}

}
