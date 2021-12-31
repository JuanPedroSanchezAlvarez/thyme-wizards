package com.thymewizards.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerAdvice {

	/** Manages ObjectOptimisticLockingFailureException for all controllers in the application. */
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({DataIntegrityViolationException.class, ObjectOptimisticLockingFailureException.class})
	public ModelAndView handleConflict(HttpServletRequest request, Exception e) {
		ModelAndView result = new ModelAndView("error/409");
		result.addObject("url", request.getRequestURL());
		return result;
	}

	/**
	 * Users of your application might add one or more extra spaces when they need to input some data.
	 * You could ensure to trim (removes all excess whitespaces) that for all controllers in the application.
	 * The boolean flag indicates if you want to have an empty string returned as null (use true), or if an empty string should remain an empty string (use false).
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);
	}

}
