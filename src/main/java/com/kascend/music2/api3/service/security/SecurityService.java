package com.kascend.music2.api3.service.security;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kascend.frameworkcommons.service.BaseService;


public abstract class SecurityService extends BaseService{
	
	public static final int STATUS_ABNORMAL=0;
	public static final int STATUS_NORMAL=1;
	public static final int STATUS_WHITE=2;
	
	private static final Logger log = Logger
			.getLogger(SecurityService.class);
	
	protected int intervalTime;
	protected int accessCount;
	protected int releaseTime;
	
	protected byte[] waitLock=new byte[0];
	@SuppressWarnings("rawtypes")
	protected List<Map> blockList=null;
	@SuppressWarnings("rawtypes")
	protected List<Map> exceptList=null;
	
	public int getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
	}

	public int getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(int accessCount) {
		this.accessCount = accessCount;
	}
	@SuppressWarnings({"rawtypes" })
	public void init(){
		initConfig();
		
		blockList=getAbnormalList(STATUS_ABNORMAL);
		exceptList=getAbnormalList(STATUS_WHITE);
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(true){
					synchronized (waitLock) {
						try {
							waitLock.wait(intervalTime*60*1000);
						} catch (InterruptedException e) {
							log.error(e.getMessage(), e);
						}
					}
					synchronized (blockList){
						try{
							for(int i=0; i<blockList.size(); i++ ){
								Map ipMap=blockList.get(i);
								int createTime=(Integer)ipMap.get("create_time");
								if((System.currentTimeMillis()/1000-createTime)>=releaseTime*60){
									updateStatus((Integer)ipMap.get("id"), STATUS_NORMAL);
								}
							}
						}catch(Exception e){
							log.error(e.getMessage(), e);
						}
						blockList=queryAbnormalList(intervalTime,accessCount);
					}
					synchronized(exceptList){
						exceptList=getAbnormalList(STATUS_WHITE);
					}
				}
						
			}}, this.getClass().getSimpleName()+"_Monitor").start();
	}
	
	protected abstract void initConfig();
	
	protected abstract List<Map> getAbnormalList(int status);
	
	protected abstract List<Map> queryAbnormalList(int intervalTime, int accessCount);
	
	protected abstract void updateStatus(int id, int status);
	
	/**
	 * return true is block, false not block.
	 * 
	 */
	public abstract boolean check(Object params);

}
