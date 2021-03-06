package com.springV04.service.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.service.BaseService;
import com.springV04.dao.login.Login001Dao;

@Service("login001Service")
public class Login001Service extends BaseService{

	@Autowired(required=false)
	private Login001Dao login001Dao;
	
	// user information Insert
	public Object login001C01(Map<String,Object> inData) throws Exception {
		Object result = null;
		
		result = login001Dao.login001C01(inData);
			
		return result;
	}
	
	public Map<String,Object> login001R03() throws Exception{
		Map<String, Object> result = new HashMap<>();
		
		//result.put("OUT_DATA", mtDao.queryForList("com.springv04.mapper.Login001Mapper.selectUserInfo", null));
		
		return result;
		
	}
	
	// User Id, Pw 확인 
	public Map<String, Object> login001R04(Map<String, Object> inData) throws Exception{
		log.debug("------[Start Service]  login001R04-------------");
		Map<String, Object> outData = new HashMap<>();
		
		outData = login001Dao.login001R04(inData);
		
		log.debug("------[End Service]  login001R04-------------");
		return outData;
	}
}
