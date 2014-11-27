package com.kascend.music2.api3.interceptor;


import com.kascend.interfaceframework.controller.Action;
import com.kascend.interfaceframework.controller.Interceptor;
import com.kascend.music2.api3.exception.MusicRcException;
import com.kascend.music2.api3.service.security.SecurityService;

public class UserBlockInterceptor implements Interceptor {

	@Override
	public void before(Action a) {
		SecurityService securityService=(SecurityService)a.fetchService("userSecurityService");
		long uid =0;
		try{
			if (a.getParameters().get("uid") != null
					&& a.getParameters().get("uid").length > 0) {
				uid = Long.parseLong(a.getParameters().get("uid")[0].toString());
			}
		}catch(Exception e){
		}
		if(securityService.check(uid)){
			throw new MusicRcException(MusicRcException.SEC_USER_BLOCK);
		}
	}

	@Override
	public void after(Action ai, Object obj) {
		// TODO Auto-generated method stub
	}

}
