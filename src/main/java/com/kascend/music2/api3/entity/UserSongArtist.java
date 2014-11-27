package com.kascend.music2.api3.entity;

import java.util.List;

public class UserSongArtist extends BaseData {
	private long playlistId;
	private long artistId;
	private int albumCount;
	private int songCount;
	
	private List<Long> artistIdList;
	
	private boolean includeFlag=false;
	
	private boolean containsFlag=false;
	
	public boolean isContainsFlag() {
		return containsFlag;
	}
	public void setContainsFlag(boolean containsFlag) {
		this.containsFlag = containsFlag;
	}
	public boolean isIncludeFlag() {
		return includeFlag;
	}
	public void setIncludeFlag(boolean includeFlag) {
		this.includeFlag = includeFlag;
	}
	public List<Long> getArtistIdList() {
		return artistIdList;
	}
	public void setArtistIdList(List<Long> artistIdList) {
		this.artistIdList = artistIdList;
	}
	public long getPlaylistId() {
		return playlistId;
	}
	public void setPlaylistId(long playlistId) {
		this.playlistId = playlistId;
	}
	public long getArtistId() {
		return artistId;
	}
	public void setArtistId(long artistId) {
		this.artistId = artistId;
	}
	public int getAlbumCount() {
		return albumCount;
	}
	public void setAlbumCount(int albumCount) {
		this.albumCount = albumCount;
	}
	public int getSongCount() {
		return songCount;
	}
	public void setSongCount(int songCount) {
		this.songCount = songCount;
	}
	
}
