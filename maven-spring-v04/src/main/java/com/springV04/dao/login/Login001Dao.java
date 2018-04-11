package com.springV04.dao.login;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.framework.dao.MybatisService;

@Repository
public class Login001Dao extends MybatisService{
	private Logger log = Logger.getLogger(this.getClass());

	public Object login001C01(Map<String, Object> inData) throws Exception {
		Object result = this.queryForInsert("com.springv04.mapper.Login001Mapper.insertUserInfo", inData);
		
		return result;
	}
	
	// User Id, Pw 확인 
	public Map<String, Object> login001R04(Map<String, Object> inData) throws Exception{
		Map<String, Object> outData = new HashMap<>();
		
		outData.put("OUT_DATA",this.queryForMap("com.springv04.mapper.Login001Mapper.selectUserInfo", inData));
		return outData;
	}
}
