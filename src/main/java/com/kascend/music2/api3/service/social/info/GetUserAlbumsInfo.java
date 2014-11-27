package com.kascend.music2.api3.service.social.info;

import com.kascend.music2.api3.service.metadata.info.PageInfo;

public class GetUserAlbumsInfo extends PageInfo {
	private long targetuid;
	
	private int userPlaylistDataFrom;
	

	public int getUserPlaylistDataFrom() {
		return userPlaylistDataFrom;
	}

	public void setUserPlaylistDataFrom(int userPlaylistDataFrom) {
		this.userPlaylistDataFrom = userPlaylistDataFrom;
	}

	public long getTargetuid() {
		return targetuid;
	}

	public void setTargetuid(long targetuid) {
		this.targetuid = targetuid;
	}
	
}
