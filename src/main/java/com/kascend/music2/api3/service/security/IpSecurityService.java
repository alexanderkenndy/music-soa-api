package com.kascend.music2.api3.service.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.kascend.frameworkcommons.config.Configer;


public class IpSecurityService extends SecurityService{

	@Override
	protected void initConfig() {
		intervalTime=Configer.getValueInt("security.blockIp.time");
		accessCount=Configer.getValueInt("security.blockIp.accessCount");
		releaseTime=Configer.getValueInt("security.blockIp.releaseTime");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected List<Map> getAbnormalList(int status) {
		List<Map> list=(List<Map>)getDao().queryForList("security.getIpList", status);
		return list;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Map> queryAbnormalList(int intervalTime, int accessCount) {
		Map params=new  HashMap();
		params.put("intervalTime", intervalTime);
		params.put("accessCount", accessCount);
		List<Map> list=(List<Map>)getDao().queryForList("security.queryAbnormalIp", params);
		return list;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void updateStatus(int id, int status) {
		Map params=new  HashMap();
		params.put("id", id);
		params.put("status", status);
		getDao().update("security.updateIpStatus", params);
	}

	@Override
	@SuppressWarnings({"rawtypes" })
	public boolean check(Object params) {
		String remoteIp=(String)params;
		if(remoteIp==null || remoteIp.length()==0)return false;
		
		if(remoteIp.startsWith("192.168.")
				|| remoteIp.startsWith("192.169.")){
			
			return false;
		}
		
		for(int i=0; i<exceptList.size(); i++ ){
			Map ipMap=exceptList.get(i);
			String ip=(String)ipMap.get("ip");
			if(remoteIp.equals(ip)){
				return false;
			}
		}
		
		for(int i=0; i<blockList.size(); i++ ){
			Map ipMap=blockList.get(i);
			String ip=(String)ipMap.get("ip");
			if(remoteIp.equals(ip)){
				return true;
			}
		}
		return false;
	}

}
