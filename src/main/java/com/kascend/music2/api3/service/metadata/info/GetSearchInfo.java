package com.kascend.music2.api3.service.metadata.info;

import java.util.List;

public class GetSearchInfo extends PageInfo {
	private long songid;
	private String song;
	private String artist;
	private String album;
	private String filename;
	
	private int mvflag;
	
	private List<Long> songIdList;
	
	private long artistid;
	
	
	public int getMvflag() {
		return mvflag;
	}
	public void setMvflag(int mvflag) {
		this.mvflag = mvflag;
	}
	public long getArtistid() {
		return artistid;
	}
	public void setArtistid(long artistid) {
		this.artistid = artistid;
	}
	public List<Long> getSongIdList() {
		return songIdList;
	}
	public void setSongIdList(List<Long> songIdList) {
		this.songIdList = songIdList;
	}
	public long getSongid() {
		return songid;
	}
	public void setSongid(long songid) {
		this.songid = songid;
	}
	public String getSong() {
		return song;
	}
	public void setSong(String song) {
		this.song = song;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
