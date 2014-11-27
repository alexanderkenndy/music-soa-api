package com.kascend.music2.api3.service.user;

import java.util.Date;

import com.kascend.interfaceframework.service.auth.AuthService;
import com.kascend.music2.api3.entity.User;

public interface UserService extends AuthService {

	public void saveLog(Integer uid, String method, Date startTime,
			Date endTime, int result, String parameters, String ip,
			Integer appid, String version);

	public Integer getAppId(String appkey);
	
	public User checkLoginWithUserServer(String token, String ip);
	public User getUserByToken(String token) ;
}
