package com.kascend.music2.api3.service.log;

import com.kascend.frameworkcommons.service.BaseService;

public class BaseLogService extends BaseService {
	
	LogCommitExecutor logCommitExecutor;

	public LogCommitExecutor getLogCommitExecutor() {
		return logCommitExecutor;
	}

	public void setLogCommitExecutor(LogCommitExecutor logCommitExecutor) {
		this.logCommitExecutor = logCommitExecutor;
	}

}
