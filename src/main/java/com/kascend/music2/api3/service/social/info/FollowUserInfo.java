package com.kascend.music2.api3.service.social.info;

import com.kascend.music2.api3.service.info.MusicBaseInfo;

public class FollowUserInfo extends MusicBaseInfo {
	
	private long followuid;
	private int type;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getFollowuid() {
		return followuid;
	}
	public void setFollowuid(long followuid) {
		this.followuid = followuid;
	}
	

}
