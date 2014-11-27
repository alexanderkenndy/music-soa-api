package com.kascend.music2.api3.entity;

public class UserPhoto extends BaseData {
	
	private int photoId;
	private long uid;
	private String photoUri;
	private int photoType;
	private String smallPhoto;
	private String middlePhoto;
	private int createTime;
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getPhotoUri() {
		return photoUri;
	}
	public void setPhotoUri(String photoUri) {
		this.photoUri = photoUri;
	}
	public int getPhotoType() {
		return photoType;
	}
	public void setPhotoType(int photoType) {
		this.photoType = photoType;
	}
	public String getSmallPhoto() {
		return smallPhoto;
	}
	public void setSmallPhoto(String smallPhoto) {
		this.smallPhoto = smallPhoto;
	}
	public String getMiddlePhoto() {
		return middlePhoto;
	}
	public void setMiddlePhoto(String middlePhoto) {
		this.middlePhoto = middlePhoto;
	}
	public int getCreateTime() {
		return createTime;
	}
	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}
	
}
