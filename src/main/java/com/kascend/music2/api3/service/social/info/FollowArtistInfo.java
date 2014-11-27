package com.kascend.music2.api3.service.social.info;

import com.kascend.music2.api3.service.info.MusicBaseInfo;

public class FollowArtistInfo extends MusicBaseInfo {
	
	private long artistid;
	private long artistuid;
	private int type;
	public long getArtistid() {
		return artistid;
	}
	public void setArtistid(long artistid) {
		this.artistid = artistid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getArtistuid() {
		return artistuid;
	}
	public void setArtistuid(long artistuid) {
		this.artistuid = artistuid;
	}

}
