package com.springV04.controller.main;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Main001Controller {
	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value = "/mainPage.do")
	public String mainPage01() {
		log.info("====/mainPage.do========");
		return "/tiles/mainTiles";
	}
	
	@RequestMapping(value = "/maven-spring-v04/mainPage.do")
	public String mainPage02() {
		log.info("====/maven-spring-v04/mainPage.do========");
		return "/tiles/mainTiles";
	}
	
	@RequestMapping(value = "/testHtml.do")
	public String testHtml() {
		return "testHtml";
	}
	
	
}
