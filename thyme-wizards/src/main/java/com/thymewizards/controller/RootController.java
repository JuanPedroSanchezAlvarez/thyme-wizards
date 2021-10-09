package com.thymewizards.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/")
@Controller
public class RootController {

	private static final String INDEX_URL = "redirect:/users";

	@GetMapping()
	public String index() {
		return INDEX_URL;
	}

}
