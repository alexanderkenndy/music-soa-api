package com.kascend.music2.api3.entity;

public class AlbumUdata extends BaseData {
	private long albumId;
	private int likeTimes;
	private int commentTimes;
	private int shareTimes;
	private float ratingValue;
	private int ratingTimes;
	private int userTimes;
	private long lastPlayTime;
	
	private int lastOpType;
	
	public int getLastOpType() {
		return lastOpType;
	}
	public void setLastOpType(int lastOpType) {
		this.lastOpType = lastOpType;
	}
	public long getAlbumId() {
		return albumId;
	}
	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}
	public int getLikeTimes() {
		return likeTimes;
	}
	public void setLikeTimes(int likeTimes) {
		this.likeTimes = likeTimes;
	}
	public int getCommentTimes() {
		return commentTimes;
	}
	public void setCommentTimes(int commentTimes) {
		this.commentTimes = commentTimes;
	}
	public int getShareTimes() {
		return shareTimes;
	}
	public void setShareTimes(int shareTimes) {
		this.shareTimes = shareTimes;
	}
	public float getRatingValue() {
		return ratingValue;
	}
	public void setRatingValue(float ratingValue) {
		this.ratingValue = ratingValue;
	}
	public int getRatingTimes() {
		return ratingTimes;
	}
	public void setRatingTimes(int ratingTimes) {
		this.ratingTimes = ratingTimes;
	}
	public int getUserTimes() {
		return userTimes;
	}
	public void setUserTimes(int userTimes) {
		this.userTimes = userTimes;
	}
	public long getLastPlayTime() {
		return lastPlayTime;
	}
	public void setLastPlayTime(long lastPlayTime) {
		this.lastPlayTime = lastPlayTime;
	}
	
}
