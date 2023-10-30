package org.chun.eyes.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Slf4j
@RequestMapping(value = "/admin")
@Controller
public class AdminController {

	@GetMapping("/")
	public ModelAndView loginPage(HttpSession session) {

		System.out.println(session.getId());
		session.setAttribute("loginTime", LocalDateTime.now());
		return new ModelAndView("redirect:/admin/index");
	}

	@GetMapping("/index")
	public ModelAndView index(HttpSession session){
		System.out.println(session.getId());
		return new ModelAndView("admin/adminIndex");
	}

}
