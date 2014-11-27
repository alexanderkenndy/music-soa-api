package com.kascend.music2.api3.service.log;

import org.apache.log4j.Logger;

public class LogCommitThread extends Thread {
	private static final Logger log = Logger
			.getLogger(LogCommitThread.class);
	
	LogCommitExecutor logCommitExecutor;
	public LogCommitThread(String name, LogCommitExecutor logCommitExecutor) {
		super(name);
		this.logCommitExecutor=logCommitExecutor;
	}

	@Override
	public void run() {
		logCommitExecutor.execute();
	}
	

}
