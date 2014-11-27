package com.kascend.music2.api3.entity;


public class ArtistMpGenre implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long genreId;
	private long artistId;
	private int type;


	public long getGenreId() {
		return this.genreId;
	}

	public void setGenreId(long genreId) {
	     this.genreId = genreId;
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
