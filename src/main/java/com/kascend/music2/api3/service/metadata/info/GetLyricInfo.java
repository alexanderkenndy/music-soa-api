package com.kascend.music2.api3.service.metadata.info;

public class GetLyricInfo extends PageInfo {
	
	private String artist;
	private String song;
	private long songid;
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getSong() {
		return song;
	}
	public void setSong(String song) {
		this.song = song;
	}
	public long getSongid() {
		return songid;
	}
	public void setSongid(long songid) {
		this.songid = songid;
	}
}
