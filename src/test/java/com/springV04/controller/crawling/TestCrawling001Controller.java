package com.springV04.controller.crawling;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/*-context.xml"
								,"classpath:spring/servlet-context-test.xml"})
public class TestCrawling001Controller {

	@Autowired(required=false)
	private Crawling001Controller crawling001Contoller;
	
	@Test
	public void connSite() {
		String outSite = "";
		System.out.println("========Test connSite==========");
		
		try {
			outSite = crawling001Contoller.crawling001R01();
			
			System.out.println("outSite: " + outSite);
			
			assertNotNull(outSite);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
