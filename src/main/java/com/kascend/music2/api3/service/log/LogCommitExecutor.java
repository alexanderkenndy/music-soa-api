package com.kascend.music2.api3.service.log;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.kascend.frameworkcommons.dao.BaseDao;
import com.kascend.music2.api3.service.log.action.LogAction;

public class LogCommitExecutor{
	
	private static final Logger log = Logger
			.getLogger(LogCommitExecutor.class);
	
	protected Vector<LogAction> queue=new Vector<LogAction>();
	protected byte[] queueLock=new byte[0];
	protected  BaseDao dao;
	
	private int maxThreads=5;
	private Integer threadIndex=0;
	
	public void add(LogAction logAction){
		this.queue.add(logAction);
	}
	
	public void init(){
		new LogCommitThread( "LogCommitExecutor",this).start();
	}

	public void execute() {
		
		while(true){
			if(this.queue.isEmpty()){
				synchronized (queueLock) {
					try {
						queueLock.wait(1000);
						continue;
					} catch (InterruptedException e) {
						if(log.isDebugEnabled()){
							log.debug(e.getMessage(), e);
						}else{
							log.error(e.getMessage());
						}
					}
				}
			}
			while(!this.queue.isEmpty()){
				if(threadIndex >= maxThreads ){
					synchronized(queueLock){
						try {
							queueLock.wait();
						} catch (InterruptedException e) {
							log.error(e.getMessage(), e);
						}
					}
				}
				synchronized(threadIndex){
					threadIndex++;
				}
				final LogAction logAction=queue.get(0);
				queue.remove(0);
				new Thread(new Runnable() {

					@Override
					public void run() {
						try{
							if(logAction!=null)
								logAction.doAction(dao);
						}catch(Exception e){
							if(log.isDebugEnabled()){
								log.debug(e.getMessage(), e);
							}else{
								log.error(e.getMessage());
							}
						}
						
						synchronized(threadIndex){
							threadIndex--;
							synchronized(queueLock){
								queueLock.notifyAll();
							}
						}
					}
				},"LogCommitExecutor_" + threadIndex).start();
			
			}
		}
		
	}

	public BaseDao getDao() {
		return dao;
	}

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public int getMaxThreads() {
		return maxThreads;
	}

	public void setMaxThreads(int maxThreads) {
		this.maxThreads = maxThreads;
	}


}
