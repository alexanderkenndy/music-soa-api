package com.kascend.music2.api3.service.log.action;

import java.util.Map;

import com.kascend.frameworkcommons.dao.BaseDao;
import com.kascend.music2.api3.entity.GetSongInfoLog;

public class GetSongInfoLogAction extends LogAction{

	public GetSongInfoLogAction(Object params) {
		super(null, params);
	}
	@Override
	public void doAction(BaseDao dao) {
		Map countMap=(Map)params;
		GetSongInfoLog getSongInfoLog =(GetSongInfoLog) dao.queryForObject(
				"metadata_log.getSongInfoCountByKeyWords", countMap);
		if (getSongInfoLog!=null&&getSongInfoLog.getSongscount() > 0) {
			countMap.put("logId", getSongInfoLog.getSongid());
			dao.update("metadata_log.updateSongInfoByKeyWords", countMap);
		} else {
			dao.insert("metadata_log.addSongInfoByKeyWords", countMap);
		}
	}
}
