package com.kascend.music2.api3.entity;


public class SongMpArtist implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long songId;
	private long artistId;
	private int props;
	private int type;


	public long getSongId() {
		return this.songId;
	}

	public void setSongId(long songId) {
	     this.songId = songId;
	}
	public long getArtistId() {
		return this.artistId;
	}

	public void setArtistId(long artistId) {
	     this.artistId = artistId;
	}
	public int getProps() {
		return this.props;
	}

	public void setProps(int props) {
	     this.props = props;
	}
	public int getType() {
		return this.type;
	}

	public void setType(int type) {
	     this.type = type;
	}


}
