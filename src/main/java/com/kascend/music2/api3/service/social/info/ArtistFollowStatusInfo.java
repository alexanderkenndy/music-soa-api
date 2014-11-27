package com.kascend.music2.api3.service.social.info;

import java.util.List;

public class ArtistFollowStatusInfo {
	private List<Long> artistIdList;
	private long uid;
	
	public List<Long> getArtistIdList() {
		return artistIdList;
	}
	public void setArtistIdList(List<Long> artistIdList) {
		this.artistIdList = artistIdList;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	
}
