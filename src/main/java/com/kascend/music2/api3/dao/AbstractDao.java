package com.kascend.music2.api3.dao;

import com.kascend.frameworkcommons.dao.BaseDao;

public abstract class AbstractDao{
	
	protected BaseDao dao;
	
	protected String getStatementName(String statementName){
		return getNameSpace()+"."+statementName;
	}

	protected abstract String getNameSpace() ;

	public BaseDao getDao() {
		return dao;
	}

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

}
