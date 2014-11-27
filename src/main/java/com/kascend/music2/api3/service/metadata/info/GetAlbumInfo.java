package com.kascend.music2.api3.service.metadata.info;

public class GetAlbumInfo extends PageInfo {
	private String album;
	private String artist;
	private long albumid;
	private int albumartflag;
	private int albumartsize;
	
	public int getAlbumartflag() {
		return albumartflag;
	}
	public void setAlbumartflag(int albumartflag) {
		this.albumartflag = albumartflag;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public long getAlbumid() {
		return albumid;
	}
	public void setAlbumid(long albumid) {
		this.albumid = albumid;
	}
	public int getAlbumartsize() {
		return albumartsize;
	}
	public void setAlbumartsize(int albumartsize) {
		this.albumartsize = albumartsize;
	}
	
	

}
