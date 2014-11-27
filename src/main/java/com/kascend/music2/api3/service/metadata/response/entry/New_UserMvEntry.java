package com.kascend.music2.api3.service.metadata.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.entity.UserMv;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.ServiceUtils;
@XmlType(propOrder = { "title", "mvid","songid", "artist","artistid","mvart", "duration"})
public class New_UserMvEntry extends MusicBaseEntry{
	
	
	private UserMv userMv;
	
	public New_UserMvEntry(){
		
	}
	
	public New_UserMvEntry(UserMv userMv){
		this.userMv=userMv;
	}
	private String title;
	private Integer mvid;
	private Long songid;
	private String artist;
	private Long artistid;
	private String mvart;
	private Integer duration;
	@XmlElement
	public String getTitle() {
		title=this.userMv.getMvTitle();
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	

	@XmlElement
	public Long getSongid() {
		songid=this.userMv.getSongId();
		return songid;
	}
	@XmlElement
	public Integer getMvid() {
		mvid=this.userMv.getMvId();
		return mvid;
	}

	public void setMvid(Integer mvid) {
		this.mvid = mvid;
	}

	public void setSongid(Long songid) {
		this.songid = songid;
	}
	@XmlElement
	public String getArtist() {
		artist=this.userMv.getArtistName();
		return artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	@XmlElement
	public Long getArtistid() {
		artistid=this.userMv.getArtistId();
		return artistid;
	}
	
	public void setArtistid(Long artistid) {
		this.artistid = artistid;
	}
	@XmlElement
	public String getMvart() {
		mvart=ServiceUtils.returnURL(this.userMv.getMvThumb());
		return mvart;
	}

	public void setMvart(String mvart) {
		this.mvart = mvart;
	}

	@XmlElement
	public Integer getDuration() {
		if(this.userMv.getDuration()>=0){
			duration=this.userMv.getDuration();
		}
		return duration;
	}
	
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

}
