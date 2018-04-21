package com.springV04.controller.crawling;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springV04.service.crawling.Crawling002Service;

@Controller
public class Crawling001Controller {
	private Logger log = Logger.getLogger(this.getClass());
	
//	@Resource(name="crawling001Service")
//	private Crawling001Service crawling001Service;
	@Resource(name="crawling002Service")
	private Crawling002Service crawling002Service;
	
	
	@RequestMapping(value = "/crawling001R01.do")
	public String crawling001R01() throws Exception{
		
		log.info("===========crawling001R01 Start================");
		log.info("##Start Time: " + getCurrentData());
		String strSite = "";
		
		strSite = crawling002Service.connMolit();
		
		log.info("##End Time: " + getCurrentData());
		log.info("===========crawling001R01 End================");
		
		return strSite;
	}
	
	public static String getCurrentData(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return sdf.format(new Date());
	}



	
}
