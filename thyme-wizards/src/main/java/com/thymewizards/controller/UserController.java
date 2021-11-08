package com.thymewizards.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thymewizards.domain.entity.Gender;
import com.thymewizards.dto.UserDTO;
import com.thymewizards.service.IUserService;
import com.thymewizards.util.LoggingUtils;
import com.thymewizards.validator.ValidationGroupSequence;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(path = "/users")
@Controller
public class UserController {

	private static final String INDEX_URL = "users/list";
	private static final String EDIT_URL = "users/edit";
	private static final String REDIRECT_INDEX_URL = "redirect:/users";

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

	@GetMapping(path = "/create")
	public String createForm(Model model) {
		log.debug("LOG: Class: " + this.getClass().getName() + " --> Method: " + LoggingUtils.getCurrentMethodName());
		model.addAttribute("user", new UserDTO());
		model.addAttribute("listOfGenders", List.of(Gender.MALE, Gender.FEMALE, Gender.OTHER));
		return EDIT_URL;
	}

	@PostMapping(path = "/create")
	public String save(@Validated(ValidationGroupSequence.class) @ModelAttribute("user") UserDTO dto, BindingResult bindingResult, Model model) {
		log.debug("LOG: Class: " + this.getClass().getName() + " --> Method: " + LoggingUtils.getCurrentMethodName());
		if (bindingResult.hasErrors()) {
			model.addAttribute("listOfGenders", List.of(Gender.MALE, Gender.FEMALE, Gender.OTHER));
			return EDIT_URL;
		}
		service.save(dto);
		return REDIRECT_INDEX_URL;
	}

}
