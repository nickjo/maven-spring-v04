package com.springV04.controller.login;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/*-context.xml"})
public class TestLogin001Controller {

	@SuppressWarnings("unused")
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired(required=false)
	private Login001Controller login001Controller;

	@Autowired
	private DriverManagerDataSource ds;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private SqlSession sqlSession;
	
//	@Test
//	public void getUserInfo() {
//		try {
//			String outData = login001Controller.login001R03();
//			System.out.println("##junit: " + outData);
//			
//			assertNotNull(login001Controller.login001R03());
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@Test
    public void testConnection() throws Exception {
        try(Connection conn = ds.getConnection()) {
            System.out.println("##DB Conn: "+conn);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	@Test
    public void testSession() throws Exception {
        try {
        		List<Map<String, Object>> list= sqlSession.selectList("com.springv04.mapper.Login001Mapper.selectUserInfo", null);
            
            System.out.println("##Test selectUserInfo: " + list.toString());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }



}
