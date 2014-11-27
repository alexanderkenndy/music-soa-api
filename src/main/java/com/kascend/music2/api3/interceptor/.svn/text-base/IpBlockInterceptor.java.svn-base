package com.kascend.music2.api3.interceptor;


import com.kascend.interfaceframework.controller.Action;
import com.kascend.interfaceframework.controller.Interceptor;
import com.kascend.music2.api3.exception.MusicRcException;
import com.kascend.music2.api3.service.security.IpSecurityService;

public class IpBlockInterceptor implements Interceptor {

	@Override
	public void before(Action ai) {
		IpSecurityService securityService=(IpSecurityService)ai.fetchService("ipSecurityService");
		String ip = ai.getIp();
		if(securityService.check(ip)){
			throw new MusicRcException(MusicRcException.SEC_IP_BLOCK);
		}
	}

	@Override
	public void after(Action ai, Object obj) {
		// TODO Auto-generated method stub

	}

}
