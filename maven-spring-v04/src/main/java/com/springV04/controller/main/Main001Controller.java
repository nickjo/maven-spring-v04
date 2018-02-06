package com.springV04.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Main001Controller {
	
	@RequestMapping(value = "/mainPage.do")
	public String mainPage() {
		return "/tiles/mainTiles";
	}
	
	@RequestMapping(value = "/testHtml.do")
	public String testHtml() {
		return "testHtml";
	}
	
	
}
