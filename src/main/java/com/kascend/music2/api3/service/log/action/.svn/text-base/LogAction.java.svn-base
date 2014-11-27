package com.kascend.music2.api3.service.log.action;

import com.kascend.common.util.Validator;
import com.kascend.frameworkcommons.dao.BaseDao;

public class LogAction {
	
	protected String logStatement;
	protected Object params;
	
	public LogAction(String logStatement, Object params){
		this.logStatement=logStatement;
		this.params=params;
	}
	
	public void doAction(BaseDao dao){
		if(!Validator.isBlank(this.logStatement)){
			dao.insert(logStatement, params);
		}
	}

}
