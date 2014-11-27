package com.kascend.music2.api3.service.metadata.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "title","mtvid","songid","artist",
		"artistid","region","mtvart", "duration"})
public class New_GetMtvInfoEntry implements java.io.Serializable{
	private Long songid;
	private Integer mtvid;
	private String title;
	private String artist;
	private Long artistid;
	private Integer duration;
	private String mtvart;
	private String region;

	@XmlElement
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	@XmlElement
	public Long getSongid() {
		return songid;
	}
	public void setSongid(Long songid) {
		this.songid = songid;
	}
	@XmlElement
	public Integer getMtvid() {
		return mtvid;
	}
	public void setMtvid(Integer mtvid) {
		this.mtvid = mtvid;
	}
	@XmlElement
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@XmlElement
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	@XmlElement
	public Long getArtistid() {
		return artistid;
	}
	public void setArtistid(Long artistid) {
		this.artistid = artistid;
	}
	@XmlElement
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	@XmlElement
	public String getMtvart() {
		return mtvart;
	}
	public void setMtvart(String mtvart) {
		this.mtvart = mtvart;
	}
	
}
