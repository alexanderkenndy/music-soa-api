package com.kascend.music2.api3.entity;

public class AlbumProfile extends BaseData {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8108739741784318766L;
	private long albumId;
	private String profile;
	public long getAlbumId() {
		return albumId;
	}
	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}

}
