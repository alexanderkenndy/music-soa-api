package com.kascend.music2.api3.service.metadata.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.entity.Song;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.ServiceUtils;

@XmlType(propOrder = { "title","songid", 
		"album", "albumid", "artist", "artistid",  "albumart",
		"duration","playtimes",
		"mtv","mv","isnew"})

public class New_SongEntry  extends MusicBaseEntry{
	
	private Song song;
	
	public New_SongEntry(){
		
	}
	
	public New_SongEntry(Song song){
		this.song=song;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	private Long songid;
	private String album;
	private Long albumid;
	private String artist;
	private Long artistid;
	private String albumart;
	private Integer duration;
	private Integer isnew;
	private Integer mtv;
	private Integer mv;
	
	private Integer playtimes;
	
	@XmlElement	
	public Integer getPlaytimes() {
		playtimes=this.song.getPlayTimes();
		return playtimes;
	}
	public void setPlaytimes(Integer playtimes) {
		this.playtimes = playtimes;
	}

	@XmlElement
	public Integer getMv() {
		mv=this.song.getHasMv();
		return mv;
	}

	@XmlElement
	public String getTitle() {
		title=this.song.getSongTitle();
		return title;
	}
	@XmlElement
	public Long getSongid() {
		songid=this.song.getSongId();
		return songid;
	}
	@XmlElement
	public String getAlbum() {
		album=this.song.getAlbumTitle();
		return album;
	}
	@XmlElement
	public Long getAlbumid() {
		if(this.song.getAlbumId()>0){
			albumid=this.song.getAlbumId();
		}
		return albumid;
	}
	@XmlElement
	public String getArtist() {
		artist=this.song.getArtistName();
		return artist;
	}
	@XmlElement
	public Long getArtistid() {
		if(this.song.getArtistId()>0){
			artistid=this.song.getArtistId();
		}
		return artistid;
	}

	@XmlElement
	public String getAlbumart() {
		albumart=ServiceUtils.returnURL(this.song.getSongThumbUri());
		return albumart;
	}
	@XmlElement
	public Integer getDuration() {
		if(this.song.getSongDuration()>0){
			duration=this.song.getSongDuration();
		}
		return duration;
	}
	@XmlElement
	public Integer getIsnew() {
		if(song.getIsnew()!=0){
			isnew=song.getIsnew();
		}
		return isnew;
	}
	@XmlElement
	public Integer getMtv() {
		mtv=song.getHasMv();
		return mtv;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setAlbumid(Long albumid) {
		this.albumid = albumid;
	}

	public void setAlbumart(String albumart) {
		this.albumart = albumart;
	}

}
