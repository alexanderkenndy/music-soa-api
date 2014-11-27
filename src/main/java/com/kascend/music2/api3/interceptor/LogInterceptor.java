package com.kascend.music2.api3.interceptor;

import java.util.Date;

import org.apache.log4j.Logger;

import com.kascend.interfaceframework.controller.Action;
import com.kascend.interfaceframework.controller.BaseInfo;
import com.kascend.interfaceframework.controller.Response;
import com.kascend.interfaceframework.interceptor.OperationLogInterceptor;
import com.kascend.music2.api3.service.info.MusicBaseInfo;
import com.kascend.music2.api3.service.response.MusicBaseResponse;
import com.kascend.music2.api3.service.user.UserService;

public class LogInterceptor extends OperationLogInterceptor {
	
	private static Logger log = Logger.getLogger(LogInterceptor.class);

	@Override
	public void before(Action a) {

	}

	@Override
	public void after(Action a, Object obj) {
		try {
			if (a.getActionInfo() instanceof BaseInfo
					&& a.getActionInfo() != null) {
				UserService userService = a.fetchService("auth");
				BaseInfo info = (BaseInfo) a.getActionInfo();
				String appkey = info.getAppkey();
				String version=null;
				if(info instanceof MusicBaseInfo){
					version=((MusicBaseInfo)info).getClientver();
				}
				Integer appid = userService.getAppId(appkey);
				if (appid == null) {
					appid = 1;
				}
				int rc = 0;
				if (obj instanceof MusicBaseResponse) {
					MusicBaseResponse res = (MusicBaseResponse) obj;
					rc = res.getRc();
				} else if (obj instanceof Response) {
					Response res = (Response) obj;
					rc = res.getRc();
				}
				String ip = a.getIp();
				String requestUri=a.getParameters().toString();
				
				long ctime=System.currentTimeMillis();
				
				userService.saveLog(info.getUid(), a.getServiceName() + "."
						+ a.getMethod(), new Date(a.getBeforTimeMillis()),
						new Date(a.getAfterTimeMillis()), rc, requestUri, ip, appid, version);
				
				if(log.isDebugEnabled() && (System.currentTimeMillis()-ctime>1000))
					log.debug("userService.SaveLog: "+(System.currentTimeMillis()-ctime));
			}
		} catch (Exception e) {
			if(log.isDebugEnabled())
				log.debug(e.getMessage(), e);
			else
				log.error(e.getMessage());
		}

	}
}
