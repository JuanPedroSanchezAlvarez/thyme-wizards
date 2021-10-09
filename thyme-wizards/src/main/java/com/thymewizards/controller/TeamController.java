package com.thymewizards.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/teams")
@Controller
public class TeamController {

	private static final String INDEX_URL = "teams/list";

	@GetMapping(path = {"", "/"})
	public String index(Model model) {
		return INDEX_URL;
	}

}
