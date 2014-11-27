package com.kascend.music2.api3.entity;

import java.util.List;

public class UserSongAlbum extends BaseData {
	private long playlistId;
	private long albumId;
	private int songCount;
	
	private boolean includeFlag=false;
	
	public boolean isIncludeFlag() {
		return includeFlag;
	}
	public void setIncludeFlag(boolean includeFlag) {
		this.includeFlag = includeFlag;
	}
	private List<Long> albumIdList;
	
	
	public List<Long> getAlbumIdList() {
		return albumIdList;
	}
	public void setAlbumIdList(List<Long> albumIdList) {
		this.albumIdList = albumIdList;
	}
	public long getPlaylistId() {
		return playlistId;
	}
	public void setPlaylistId(long playlistId) {
		this.playlistId = playlistId;
	}
	public long getAlbumId() {
		return albumId;
	}
	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}
	public int getSongCount() {
		return songCount;
	}
	public void setSongCount(int songCount) {
		this.songCount = songCount;
	}
	
}
