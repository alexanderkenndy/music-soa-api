package com.kascend.music2.api3.service.social.info;

import java.util.List;

import com.kascend.music2.api3.service.metadata.info.PageInfo;

public class RecommendUserInfo extends PageInfo {
	
	private List<Long> uidList;
	
	private int pagecount=10;

	public List<Long> getUidList() {
		return uidList;
	}

	public void setUidList(List<Long> uidList) {
		this.uidList = uidList;
	}

	public int getPagecount() {
		if(this.pagecount>50)
			pagecount=50;
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	
}
