package com.kascend.music2.api3.service.social.info;

import java.util.List;

public class UserFollowStatusInfo {
	private List<Long> followUidList;
	private long uid;
	public List<Long> getFollowUidList() {
		return followUidList;
	}
	public void setFollowUidList(List<Long> followUidList) {
		this.followUidList = followUidList;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	
}
