package com.kascend.music2.api3.entity;

public class ArtistProfile extends BaseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long artistId;
	
	private String profile;

	public long getArtistId() {
		return artistId;
	}

	public void setArtistId(long artistId) {
		this.artistId = artistId;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	

}
