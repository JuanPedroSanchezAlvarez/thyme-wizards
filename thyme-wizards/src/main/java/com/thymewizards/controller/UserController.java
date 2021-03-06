package com.thymewizards.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thymewizards.dto.UserDTO;
import com.thymewizards.service.IUserService;
import com.thymewizards.util.EditMode;
import com.thymewizards.util.Gender;
import com.thymewizards.util.LoggingUtils;
import com.thymewizards.util.UserRole;
import com.thymewizards.validator.CreateValidationGroupSequence;
import com.thymewizards.validator.UpdateValidationGroupSequence;

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
	@Secured("ROLE_ADMIN")
	public String createForm(Model model) {
		log.debug("LOG: Class: " + this.getClass().getName() + " --> Method: " + LoggingUtils.getCurrentMethodName());
		model.addAttribute("user", new UserDTO());
		model.addAttribute("editMode", EditMode.CREATE);
		return EDIT_URL;
	}

	@PostMapping(path = "/create")
	@Secured("ROLE_ADMIN")
	public String create(@Validated(CreateValidationGroupSequence.class) @ModelAttribute("user") UserDTO dto, BindingResult bindingResult, Model model) {
		log.debug("LOG: Class: " + this.getClass().getName() + " --> Method: " + LoggingUtils.getCurrentMethodName());
		if (bindingResult.hasErrors()) {
			model.addAttribute("editMode", EditMode.CREATE);
			return EDIT_URL;
		}
		service.save(dto);
		return REDIRECT_INDEX_URL;
	}

    @GetMapping(path = "/{id}")
    public String updateForm(@PathVariable("id") UUID id, Model model) {
    	log.debug("LOG: Class: " + this.getClass().getName() + " --> Method: " + LoggingUtils.getCurrentMethodName());
        model.addAttribute("user", service.findById(id));
        model.addAttribute("editMode", EditMode.UPDATE);
        return EDIT_URL;
    }

    @PostMapping(path = "/{id}")
    @Secured("ROLE_ADMIN")
    public String update(@PathVariable("id") UUID id, @Validated(UpdateValidationGroupSequence.class) @ModelAttribute("user") UserDTO dto, BindingResult bindingResult, Model model) {
    	log.debug("LOG: Class: " + this.getClass().getName() + " --> Method: " + LoggingUtils.getCurrentMethodName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("editMode", EditMode.UPDATE);
            return EDIT_URL;
        }
        service.update(dto);
        return REDIRECT_INDEX_URL;
    }

    @DeleteMapping(path = "/{id}")
    @Secured("ROLE_ADMIN")
    public String delete(@PathVariable("id") UUID id, RedirectAttributes redirectAttributes) {
    	log.debug("LOG: Class: " + this.getClass().getName() + " --> Method: " + LoggingUtils.getCurrentMethodName());
    	UserDTO dto = service.findById(id);
    	service.deleteById(id);
    	redirectAttributes.addFlashAttribute("deletedUserName", dto.getFullName());
    	return REDIRECT_INDEX_URL;
    }

//    @GetMapping("/ex")
//    public String throwException() {
//    throw new RuntimeException("This is a fake exception for testing");
//    }

    @ModelAttribute("listOfGenders")
    public List<Gender> getListOfGenders() {
    	return List.of(Gender.MALE, Gender.FEMALE, Gender.OTHER);
    }

    @ModelAttribute("listOfRoles")
    public List<UserRole> getListOfRoles() {
    	return List.of(UserRole.values());
    }

}
