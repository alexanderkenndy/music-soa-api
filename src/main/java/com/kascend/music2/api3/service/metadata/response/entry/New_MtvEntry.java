package com.kascend.music2.api3.service.metadata.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.entity.SongMv;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.ServiceUtils;
@XmlType(propOrder = { "title", "mtvid","songid", "artist","artistid","mtvart", "duration"})
public class New_MtvEntry extends MusicBaseEntry{
	
	
	private SongMv songMv;
	
	public New_MtvEntry(){
		
	}
	
	public New_MtvEntry(SongMv songMv){
		this.songMv=songMv;
	}
	private String title;
	private Integer mtvid;
	private Long songid;
	private String artist;
	private Long artistid;
	private String mtvart;
	private Integer duration;
	@XmlElement
	public String getTitle() {
		title=this.songMv.getMvTitle();
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	@XmlElement
	public Integer getMtvid() {
		mtvid=this.songMv.getMvId();
		return mtvid;
	}
	
	public void setMtvid(Integer mtvid) {
		this.mtvid = mtvid;
	}
	@XmlElement
	public Long getSongid() {
		songid=this.songMv.getSongId();
		return songid;
	}
	
	public void setSongid(Long songid) {
		this.songid = songid;
	}
	@XmlElement
	public String getArtist() {
		artist=this.songMv.getArtistName();
		return artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	@XmlElement
	public Long getArtistid() {
		artistid=this.songMv.getArtistId();
		return artistid;
	}
	
	public void setArtistid(Long artistid) {
		this.artistid = artistid;
	}


	@XmlElement
	public String getMtvart() {
		mtvart=ServiceUtils.returnURL(this.songMv.getSongMvThumb());
		return mtvart;
	}
	
	public void setMtvart(String mtvart) {
		this.mtvart = mtvart;
	}
	@XmlElement
	public Integer getDuration() {
		if(this.songMv.getDuration()>=0){
			duration=this.songMv.getDuration();
		}
		return duration;
	}
	
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

}
