package com.kascend.music2.api3.interceptor;


import static com.kascend.interfaceframework.exception.RcException.INVALID_TOKEN;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kascend.interfaceframework.controller.Action;
import com.kascend.interfaceframework.controller.Interceptor;
import com.kascend.interfaceframework.exception.RcException;
import com.kascend.music2.api3.entity.User;
import com.kascend.music2.api3.service.user.UserService;

public class TokenInterceptor implements Interceptor {
	
	final static Log log = LogFactory.getLog(TokenInterceptor.class);

	@Override
	public void after(Action ai, Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void before(Action a) {


		String token = null;
		User user=null;
		UserService userService = a.fetchService("auth");
		if (a.getParameters().get("token") != null
				&& a.getParameters().get("token").length > 0) {
			token = (String) (a.getParameters().get("token")[0]);
		} else {
			token = null;
		}
		if (a.getParameters().get("appkey") != null
				&& a.getParameters().get("appkey").length > 0) {
			String appkey = (String) (a.getParameters().get("appkey")[0]);
			if(appkey!=null){
				int appid=userService.getAppId(appkey);
				if(appid>0){
					a.getParameters().put("appid", new String[] { String.valueOf(appid)});
				}
			}
		}

		if (token != null && token.trim().length() > 0) {
			user=userService.getUserByToken(token);
			
			
			if (user == null && !token.equalsIgnoreCase("test")) {
				user=userService.checkLoginWithUserServer(token, a.getIp());
			}

			if (user != null) {
				a.getParameters().put("uid",
						new String[] { String.valueOf(user.getUid()) });
				a.getParameters().put("userstatus",
						new String[] { String.valueOf(user.getStatus()) });
			}
		}
		if(user==null){
			throw new RcException(INVALID_TOKEN);
		}
	
	}
}
