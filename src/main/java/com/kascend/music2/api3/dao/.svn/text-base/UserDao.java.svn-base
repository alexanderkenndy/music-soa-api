package com.kascend.music2.api3.dao;

import java.util.HashMap;
import java.util.Map;

import com.kascend.music2.api3.entity.ClientVersion;
import com.kascend.music2.api3.entity.User;

public class UserDao extends AbstractDao {
	
	public static final String NAME_SPACE="user";
	@Override
	protected String getNameSpace() {
		return NAME_SPACE;
	}
	
	public String getToken(Long uid){
		return (String)this.dao.queryForObject(super.getStatementName("getToken"),uid);
	}
	
	public void insertUserToken(Map map){
		this.dao.insert(super.getStatementName("insertUserToken"),map);
	}
	
	public void updateUserToken(Map map){
		this.dao.update(super.getStatementName("updateUserToken"),map);
	}
	
	public Long getUidByToken(String token){
		return (Long)this.dao.queryForObject(super.getStatementName("getUidByToken"),token);
	}
	
	public Long getUserUid(Long uid){
		long uids=0l;
		Long uidDb=(Long)this.dao.queryForObject(super.getStatementName("getUserUid"),uid);
		if(uidDb!=null){
			uids=uidDb.longValue();
		}
		return uids;
	}


	public User getUserByToken(String token) {
		return (User)this.dao.queryForObject(super.getStatementName("getUserByToken"),token);
	}

	public long getUidByUsUid(long usUid) {
		long uids=0l;
		Long uidDb=(Long)this.dao.queryForObject(super.getStatementName("getUidByUsUid"),usUid);
		if(uidDb!=null){
			uids=uidDb.longValue();
		}
		return uids;
	}

	public long insertUser(User user) {
		Long uid=(Long)this.dao.insert(super.getStatementName("insertUser"), user);
		if(uid!=null){
			return uid;
		}
		return 0;
	}

	public void updateUser(User user) {
		this.dao.update(super.getStatementName("updateUser"), user);
	}

	public Integer getClientVerid(String version, int appid) {
		Map params=new HashMap();
		params.put("version", version);
		params.put("appid", appid);
		
		return (Integer)this.dao.queryForObject(super.getStatementName("getClientVerid"), params);
	}

	public Integer insertClientVersion(ClientVersion clientVersion) {
		Integer verid=(Integer)this.dao.insert(super.getStatementName("insertClientVersion"), clientVersion);
		if(verid!=null){
			return verid;
		}
		return 0;
	}
	
}
