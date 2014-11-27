package com.kascend.music2.api3.service.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.arcsoft.e2e.osm.model.InfoWeibo;
import com.arcsoft.e2e.osm.userserver.sdk.E2eResponse;
import com.arcsoft.e2e.osm.userserver.sdk.E2eUser;
import com.arcsoft.e2e.osm.userserver.sdk.E2eUserService;
import com.kascend.common.util.Validator;
import com.kascend.frameworkcommons.config.Configer;
import com.kascend.interfaceframework.service.auth.AuthServiceImpl;
import com.kascend.music2.api3.cache.CacheService;
import com.kascend.music2.api3.dao.UserDao;
import com.kascend.music2.api3.entity.ClientVersion;
import com.kascend.music2.api3.entity.User;
import com.kascend.music2.api3.exception.MusicConst;
import com.kascend.music2.api3.service.log.LogCommitExecutor;
import com.kascend.music2.api3.service.log.action.LogAction;
import com.kascend.music2.api3.service.log.action.OpLogAction;
import com.kascend.music2.api3.service.social.impl.SocialServiceHelper;
import com.kascend.music2.api3.service.util.Constants;


public class UserServiceImpl extends AuthServiceImpl implements UserService {
	final static Log log = LogFactory.getLog(UserServiceImpl.class);
	
	CacheService cacheService;
	LogCommitExecutor logCommitExecutor;
	UserDao userDao;
	protected SocialServiceHelper sosHelper;
	
	private byte[] verLockWait=new byte[0];
	private byte[] userLockWait=new byte[0];
	
	
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void log(Integer uid, String method, Date startTime, Date endTime,
			int result, String parameters, String ip) {
	}

	private void saveOrupdateToken(String token, Long uid) {
		Map userMap = new HashMap();
		userMap.put("token", token);
		userMap.put("uid", uid);
		String dbtoken =userDao.getToken(uid);
		if (Validator.isBlank(dbtoken)) {
			userDao.insertUserToken(userMap);
		} else {
			userDao.updateUserToken(userMap);
		}
	}

	public User getUserByToken(String token) {
		String cacheKey=getUserByTokenCacheKey(token);
		User user =(User)cacheService.getNoRefresh(cacheKey);
		if(user==null){
			user=userDao.getUserByToken(token);
			if (user != null) {
				cacheService.put(cacheKey, user);
			}
		}
		return user;
	}
	private String getUserByTokenCacheKey(String token){
		StringBuffer sb=new StringBuffer();
		sb.append("auth.getUserByToken").append("_").append(token);
		return sb.toString();
	}
	
	public int getClientVerid(String version, int appid) {
		if(Validator.isBlank(version)){
			return 0;
		}
		StringBuffer sb=new StringBuffer();
		sb.append("auth.getClientVerid")
		.append("_").append(version)
		.append("_").append(appid);
		Integer verid =(Integer)cacheService.getNoRefresh(sb.toString());
		if(verid==null){
			synchronized(verLockWait){
				verid=userDao.getClientVerid(version, appid);
				if(verid==null){
					ClientVersion clientVersion=new ClientVersion();
					clientVersion.setAppid(appid);
					clientVersion.setVersion(version);
					
					verid=userDao.insertClientVersion(clientVersion);
				}
				if (verid != null) {
					cacheService.put(sb.toString(), verid);
				}
			}
		}
		return verid==null? 0: verid;
	}
	
	private void saveOrupdate(User user) {

		long dbuid =userDao.getUidByUsUid(user.getUsUid());
		if (dbuid == 0) {
			long uid=userDao.insertUser(user);
			user.setUid(uid);
		} else {
			user.setUid(dbuid);
			userDao.updateUser(user);
		}
	}

	@Override
	public void saveLog(Integer uid, String method, Date startTime,
			Date endTime, int result, String parameters, String ip,
			Integer appid, String version) {
		Map map = new HashMap();
		if (uid == null) {
			uid = 0;
		}
		map.put("uid", uid);
		map.put("method", method);
		map.put("startTime", startTime.getTime()/1000);
		map.put("endTime", endTime.getTime()/1000);
		map.put("result", result);
		map.put("expendTime", (endTime.getTime() - startTime.getTime()));
		map.put("ip", ip);
//		String area = "";// ipseeker.getCountry(ip);
//		map.put("area", area);
		map.put("appid", appid);
		map.put("parameters", parameters);
		int verid=this.getClientVerid(version, appid);
		map.put("verid", verid);
		
		LogAction logAction=new OpLogAction( map);
		logCommitExecutor.add(logAction);
	
	}

	@Override
	public Integer getAppId(String appkey) {
		Map<String, ?> app=getApplication(appkey);
		Integer appid=0;
		if(app!=null)
			appid = (Integer)app.get("appid");
		return appid;
	}

	@Override
	public Map<String, ?> getApplication(String appkey) {
		StringBuffer sb=new StringBuffer();
		sb.append("auth.getApplication")
			.append(appkey);
		Map<String, ?> app= (Map)cacheService.getNoRefresh(sb.toString());
		if(app==null){
			app=super.getApplication(appkey);
			cacheService.put(sb.toString(), app);
		}
		return app;
	}
	
	public User checkLoginWithUserServer(String token, String ip) {
		User user=null;
		try {
			E2eUserService userService;
			E2eResponse retRes = null;
			List<InfoWeibo> snsInfoList=null;
			
			userService = new E2eUserService(Configer.getValueString(MusicConst.USER_SERVER_APPKEY)
					, Configer.getValueString(MusicConst.USER_SERVER_SECRET)
					,30000, 30000, 30000);
			E2eUser inputUser = new E2eUser();
			inputUser.setToken(token);// 用户token值
			retRes = userService.excuteGetUserInfo(inputUser);

			if (retRes != null && retRes.getRetValue() == 0) {
				E2eUser e2eUser=retRes.getE2eUser();
				snsInfoList=retRes.getSnsInfo();
				if(e2eUser!=null && e2eUser.getUserId()>0){
					user=new User();
					user.setNickname(e2eUser.getNickname());
					user.setName(e2eUser.getNickname());
					user.setHeadIcon(e2eUser.getUserHeadicon());
					user.setStatus(e2eUser.getUserState());
					user.setUsUid(e2eUser.getUserId());
					user.setSignature(e2eUser.getUserSignname());
					user.setEmail(e2eUser.getEmail());
					user.setPhone(e2eUser.getPhnum());
					if(Validator.isBlank(user.getNickname()) && !Validator.isBlank(user.getEmail())){
						try{
							user.setNickname(user.getEmail().substring(0, user.getEmail().indexOf('@')));
						}catch(Exception e){
						}
					}
					try{
						user.setAge(Integer.parseInt(e2eUser.getUserAge()));
					}catch(Exception e){
					}
					if(e2eUser.getUserGender()!=null 
							&& e2eUser.getUserGender().equalsIgnoreCase("m")){
						user.setSex(Constants.USER_SEX_MALE);
					}else if(e2eUser.getUserGender()!=null
							&& e2eUser.getUserGender().equalsIgnoreCase("f")){
						user.setSex(Constants.USER_SEX_FEMALE);
					}
				}
			}
			
			long ctime=System.currentTimeMillis();
			if(log.isDebugEnabled() && (System.currentTimeMillis()-ctime>1000))
				log.debug("checkLoginWithUserServer: "+(System.currentTimeMillis()-ctime));
			
			if (user!=null) {
				user.setRegisterIp(ip);
				synchronized(userLockWait){
					saveOrupdate(user);
					saveOrupdateToken(token, user.getUid());
					if(snsInfoList!=null){
						sosHelper.saveUserSnsInfo(snsInfoList, user.getUid());
					}
				}
				/**
				 * put into cache
				 */
				cacheService.put(getUserByTokenCacheKey(token), user);
			}
		} catch (Exception e) {
			if(log.isDebugEnabled()){
				log.debug(e.getMessage(), e);
			}else{
				log.error(e.getMessage());
			}
		}
		return user;
	}


	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	public LogCommitExecutor getLogCommitExecutor() {
		return logCommitExecutor;
	}

	public void setLogCommitExecutor(LogCommitExecutor logCommitExecutor) {
		this.logCommitExecutor = logCommitExecutor;
	}

	public SocialServiceHelper getSosHelper() {
		return sosHelper;
	}

	public void setSosHelper(SocialServiceHelper sosHelper) {
		this.sosHelper = sosHelper;
	}

}
