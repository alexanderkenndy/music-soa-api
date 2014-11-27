package com.kascend.music2.api3.service.social.info;

import java.util.List;

import com.kascend.music2.api3.service.metadata.info.PageInfo;

public class MyAttentionAlbumInfo extends PageInfo {
	private long albumid;

	private List<Long> songIdList;
	
	private long targetuid;
	
	
	public long getTargetuid() {
		return targetuid;
	}

	public void setTargetuid(long targetuid) {
		this.targetuid = targetuid;
	}

	public List<Long> getSongIdList() {
		return songIdList;
	}

	public void setSongIdList(List<Long> songIdList) {
		this.songIdList = songIdList;
	}

	public long getAlbumid() {
		return albumid;
	}

	public void setAlbumid(long albumid) {
		this.albumid = albumid;
	}
	
}
