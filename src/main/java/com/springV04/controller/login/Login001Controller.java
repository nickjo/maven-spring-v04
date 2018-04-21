package com.springV04.controller.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	@Resource(name="login001Service")
	private Login001Service login001Serv;
	
	// 로그인 페이지 이동 
	@RequestMapping(value = "/login001R01.do")
	public String login001R01(HttpServletRequest request, HttpServletResponse response) {
		return "/tiles/login";
	}
	
	// 회원가입 페이지 이동 
	@RequestMapping(value = "/login001R02.do")
	public String login001R02(HttpServletRequest request, HttpServletResponse response) {
		log.info("###login001R02");
		return "/tiles/join";
	}
	
	// 로그인 가능 유무확인
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login001R04.do", method=RequestMethod.POST)
	public Map<String, Object> login001R04(Map<String, Object> inData) throws Exception{
		log.debug("------[Start Controller]  login001R04-------------");
		Map<String, Object> outMap = null;
		
		try {
			outMap = (Map<String, Object>) (login001Serv.login001R04(inData)).get("OUT_DATA");
			
			// login success
			if(!outMap.get("USER_ID").equals("")) {
				outMap.put("RESULT_MESSAGE", "LOGIN SUCCESS");
				outMap.put("LOGIN_STATE", true);
			}else { // login false
				outMap.put("RESULT_MESSAGE", "LOGIN FAILURE");
				outMap.put("LOGIN_STATE", false);
			}
			
			log.debug("------[End Controller]  login001R04-------------");
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return outMap;
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
