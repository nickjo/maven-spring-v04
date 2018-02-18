package com.springV04.dao.login;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.framework.dao.MybatisService;

@Repository
public class Login001Dao extends MybatisService{

	public Object login001C01(Map<String, Object> inData) throws Exception {
		Object result = this.queryForInsert("com.springv04.mapper.Login001Mapper.insertUserInfo", inData);
		
		return result;
	}
}
