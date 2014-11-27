package com.kascend.music2.api3.entity;


public class ArtistMpAlbum implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long albumId;
	private long artistId;
	private int type;


	public long getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(long albumId) {
	     this.albumId = albumId;
	}
	public long getArtistId() {
		return this.artistId;
	}

	public void setArtistId(long artistId) {
	     this.artistId = artistId;
	}
	public int getType() {
		return this.type;
	}

	public void setType(int type) {
	     this.type = type;
	}


}
