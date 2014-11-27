package com.kascend.music2.api3.service.social.info;

import java.util.List;

import com.kascend.music2.api3.service.metadata.info.PageInfo;

public class RelevanceUidInfo extends PageInfo {
	private List<Long> songIdList;
	private List<Long> artistIdList;
	private List<Long> albumIdList;
	
	private List<Long> uidList;
	
	public List<Long> getUidList() {
		return uidList;
	}
	public void setUidList(List<Long> uidList) {
		this.uidList = uidList;
	}
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
	public List<Long> getArtistIdList() {
		return artistIdList;
	}
	public void setArtistIdList(List<Long> artistIdList) {
		this.artistIdList = artistIdList;
	}
	public List<Long> getAlbumIdList() {
		return albumIdList;
	}
	public void setAlbumIdList(List<Long> albumIdList) {
		this.albumIdList = albumIdList;
	}
	
}
