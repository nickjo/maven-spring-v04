package com.springV04.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springV04.service.login.Login001Service;

@Controller
public class Login001Controller {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired(required=false)
	private Login001Service login001Serv;
	
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
	public void login001C01(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String textPw = request.getParameter("textPw");
		
		Map<String, Object> inData = new HashMap<>();
		Map<String, Object> outData = new HashMap<>();
		
		try {
			inData.put("USER_NM", name);
			inData.put("EMAIL", email);
			inData.put("USER_PW", textPw);
			
			 log.info(login001Serv.login001C01(inData));
			
			log.info("##outData: " + outData.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public String login001R03() throws Exception{
		Map<String, Object> outData = new HashMap<>();
		log.info("###login001R03");
		
		try {
			outData = login001Serv.login001R03();
			
			log.info("##login001R03 outData: " + outData.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return outData.toString(); 
	}
}
