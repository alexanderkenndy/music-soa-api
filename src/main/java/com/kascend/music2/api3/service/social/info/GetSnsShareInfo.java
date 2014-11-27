package com.kascend.music2.api3.service.social.info;

import com.kascend.music2.api3.service.metadata.info.PageInfo;

public class GetSnsShareInfo extends PageInfo {
	private long shareid;

	public long getShareid() {
		return shareid;
	}

	public void setShareid(long shareid) {
		this.shareid = shareid;
	}
	
}
