package com.springV04.controller.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/*-context.xml"
								,"classpath:spring/servlet-context-test.xml"})
public class TestLogin001Controller {
	@Autowired(required=false)
	private Login001Controller login001Controller;
	
/*	
	1. 로그인 
	1) 입력된 아이디, 패스워드 DB와 비교
	2) 로그인 성공 유뮤확인 메세지 
	3) 성공 후 메인 화면 이동

	
	2.회원 가입
	1) 아이디 중복 확인
	2) 비밀번호 정합성 확인(사용가능한지 여부)
	3) 최초입력된 비밀번호와 두번째 확인을 위해 입력한 비밀번호가 동일한지 확인
	4) 성공 유무 확인 메세지
	5) 성공 후 로그인 화면 이동
*/	
	
	// 1.로그인 
	// 1) 입력된 아이디, 패스워드 DB와 비교 
	@Test
	public void selectUserInfo() {
		Map<String, Object> inData = new HashMap<>();
		Map<String, Object> outData = new HashMap<>();
		
		inData.put("USER_ID", "1234");
		inData.put("USER_PW", "0987");
		
		try {
			outData = login001Controller.login001R04(inData);
			assertNotNull(outData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 2) 로그인 성공 유뮤확인 메세지 
	@Test
	public void loginResult() {
		Map<String, Object> inData = new HashMap<>();
		Map<String, Object> outData = new HashMap<>();
		
		inData.put("USER_ID", "1234");
		inData.put("USER_PW", "0987");
		
		try {
			outData = login001Controller.login001R04(inData);
			System.out.println("##loginResult: " + outData.toString());
			
			assertEquals(outData.get("LOGIN_STATE"), true);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	// 3) 성공 후 메인 화면 이동
	@Test(expected=Exception.class)
	public void loginSuccessMoveToMainPage() {
		
	}
	
	// 2.회원 가입
	// 1) 아이디 중복 확인
	@Test(expected=Exception.class)
	public void idDuplicationCheck() {
		
	}
	
	// 2) 비밀번호 정합성 확인(사용가능한지 여부)
	@Test(expected=Exception.class)
	public void passwordValidCheck() {
		
	}
	
	// 3) 최초입력된 비밀번호와 두번째 확인을 위해 입력한 비밀번호가 동일한지 확인
	@Test(expected=Exception.class)
	public void passwordInputConfirm() {
		
	}
	
	// 4) 성공 유무 확인 메세지
	@Test(expected=Exception.class)
	public void joinResult() {
		
	}
	
	// 5) 성공 후 로그인 화면 이동
	@Test(expected=Exception.class)
	public void joinSuccessMoveToLoginPage() {
		
	}

}
