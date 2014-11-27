package com.kascend.music2.api3.service.metadata.info;

public class GetSongsOfArtistInfo extends PageInfo {
	private String artist;
	private long artistid;
	private int albumartflag;
	private int albumartsize;
	
	private int songFileFlag=0;
	
	public int getSongFileFlag() {
		return songFileFlag;
	}
	public void setSongFileFlag(int songFileFlag) {
		this.songFileFlag = songFileFlag;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public long getArtistid() {
		return artistid;
	}
	public void setArtistid(long artistid) {
		this.artistid = artistid;
	}
	public int getAlbumartflag() {
		return albumartflag;
	}
	public void setAlbumartflag(int albumartflag) {
		this.albumartflag = albumartflag;
	}
	public int getAlbumartsize() {
		return albumartsize;
	}
	public void setAlbumartsize(int albumartsize) {
		this.albumartsize = albumartsize;
	}
	
	

}
