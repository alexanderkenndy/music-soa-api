package com.kascend.music2.api3.entity;


public class SongMpGenre implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long songId;
	private long genreId;
	private int type;


	public long getSongId() {
		return this.songId;
	}

	public void setSongId(long songId) {
	     this.songId = songId;
	}
	public long getGenreId() {
		return this.genreId;
	}

	public void setGenreId(long genreId) {
	     this.genreId = genreId;
	}
	public int getType() {
		return this.type;
	}

	public void setType(int type) {
	     this.type = type;
	}


}
