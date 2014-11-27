package com.kascend.music2.api3.service.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kascend.frameworkcommons.config.Configer;



public class UserSecurityService extends SecurityService{
	
	private static final Logger log = Logger
			.getLogger(UserSecurityService.class);
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Map> getAbnormalList(int status){
		List<Map> list=(List<Map>)getDao().queryForList("security.getUserList", status);
		return list;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Map> queryAbnormalList(int intervalTime, int accessCount){
		Map params=new  HashMap();
		params.put("intervalTime", intervalTime);
		params.put("accessCount", accessCount);
		List<Map> list=(List<Map>)getDao().queryForList("security.queryAbnormalUser", params);
		return list;
	}
	@Override
	protected void initConfig() {
		intervalTime=Configer.getValueInt("security.blockUser.time");
		accessCount=Configer.getValueInt("security.blockUser.accessCount");
		releaseTime=Configer.getValueInt("security.blockUser.releaseTime");
		
	}
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void updateStatus(int id, int status) {
		Map params=new  HashMap();
		params.put("id", id);
		params.put("status", status);
		getDao().update("security.updateUserStatus", params);
	}
	@Override
	@SuppressWarnings({ "rawtypes" })
	public boolean check(Object params) {
		if(params==null){
			return false;
		}
		long uid=(Long)params;
		if(uid==0){
			return false;
		}
		
		for(int i=0; i<exceptList.size(); i++ ){
			Map map=exceptList.get(i);
			long uid2=(Long)map.get("uid");
			if(uid==uid2){
				return false;
			}
		}
		
		for(int i=0; i<blockList.size(); i++ ){
			Map map=blockList.get(i);
			long uid2=(Long)map.get("uid");
			if(uid==uid2){
				return true;
			}
		}
		return false;
	}
	

}
