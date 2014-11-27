package com.kascend.music2.api3.entity;

public class UserOpAlbum extends BaseData {
	private long uid;
	private long albumId;
	private int type;
	private long lastTime;
	
	private long lastSongId;
	
	private long lastCommentId;
	
	private String subTitle;
	
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public long getLastSongId() {
		return lastSongId;
	}
	public void setLastSongId(long lastSongId) {
		this.lastSongId = lastSongId;
	}
	public long getLastCommentId() {
		return lastCommentId;
	}
	public void setLastCommentId(long lastCommentId) {
		this.lastCommentId = lastCommentId;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public long getAlbumId() {
		return albumId;
	}
	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getLastTime() {
		return lastTime;
	}
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	
	
}
