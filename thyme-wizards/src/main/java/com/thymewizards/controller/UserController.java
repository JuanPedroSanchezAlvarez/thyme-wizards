package com.thymewizards.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thymewizards.service.IUserService;
import com.thymewizards.util.LoggingUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(path = "/users")
@Controller
public class UserController {

	private static final String INDEX_URL = "users/list";

	private final IUserService service;

	public UserController(IUserService service) {
		this.service = service;
	}

	@GetMapping(path = {"", "/"})
	public String index(Model model, @SortDefault.SortDefaults({@SortDefault("firstName"), @SortDefault("lastName")}) Pageable pageable) {
		log.debug("LOG: Class: " + this.getClass().getName() + " --> Method: " + LoggingUtils.getCurrentMethodName());
		model.addAttribute("pageOfUsers", service.findAll(pageable));
		return INDEX_URL;
	}

}
