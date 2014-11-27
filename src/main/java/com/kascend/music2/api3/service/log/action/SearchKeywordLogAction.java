package com.kascend.music2.api3.service.log.action;

import java.util.Map;

import com.kascend.frameworkcommons.dao.BaseDao;

public class SearchKeywordLogAction extends LogAction {

	public SearchKeywordLogAction(Object params) {
		super(null, params);
	}

	@Override
	public void doAction(BaseDao dao) {
		Map map=(Map)params;
		Long oid=(Long)dao.queryForObject("metadata_log.getSearchKeyWord", params);
		if (oid==null|| oid.longValue() == 0) {
			dao.insert("metadata_log.saveSearchKeyWord", map);
		} else {
			map.put("id", oid);
			dao.update("metadata_log.updateSearchKeyWord",map);
		}
	}
}
