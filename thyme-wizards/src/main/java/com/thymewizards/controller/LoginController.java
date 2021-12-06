package com.thymewizards.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	private static final String LOGIN_URL = "login";
	private static final String REDIRECT_URL = "redirect:/";

	@GetMapping(path = {"/login"})
	public String login(@AuthenticationPrincipal UserDetails userDetails) {
		return userDetails == null ? LOGIN_URL : REDIRECT_URL;
	}

}
