package com.kascend.music2.api3.service.log.action;

import java.util.Map;

import com.kascend.frameworkcommons.dao.BaseDao;

public class OpLogAction extends LogAction {

	public OpLogAction(Object params) {
		super(null, params);
	}

	@Override
	public void doAction(BaseDao dao) {
		long oid=(Long)dao.insert("metadata_log.insertRequest", params);
		Map map=(Map)params;
		map.put("oid", oid);
		dao.insert("metadata_log.insertRequestParameters", map);
	}

}
