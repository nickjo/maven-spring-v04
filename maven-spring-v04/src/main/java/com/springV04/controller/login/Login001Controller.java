package com.springV04.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Login001Controller {
	private Logger log = Logger.getLogger(this.getClass());
	
	// 로그인 페이지 이동 
	@RequestMapping(value = "/login001R01.do")
	public String login001R01(HttpServletRequest request, HttpServletResponse response) {
		return "/tiles/login";
	}
	
	// 회원가입 페이지 이동 
	@RequestMapping(value = "/login001R02.do")
	public String login001R02(HttpServletRequest request, HttpServletResponse response) {
		return "/tiles/join";
	}
	
	
	//	회원가입 
	@RequestMapping(value="/login001C01.do", method=RequestMethod.POST)
	@ResponseBody
	public void login001C01(HttpServletRequest request, HttpServletResponse response, Model model) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String textPw = request.getParameter("textPw");
		
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		model.addAttribute("textPw", textPw);
		
		log.info("name: " + name + ", email: " + email + ", textPw: " + textPw);
	}
}
