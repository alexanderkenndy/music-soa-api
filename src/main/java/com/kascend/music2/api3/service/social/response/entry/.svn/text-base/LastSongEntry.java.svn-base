package com.kascend.music2.api3.service.social.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.entity.Song;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
@XmlType(propOrder = { "title","songid", 
		"album", "albumid", "artist", "artistid",
		"duration","playtimes","lastplaytime"})
public class LastSongEntry extends MusicBaseEntry {

	
	private Song song;
	
	public LastSongEntry(){
		
	}
	
	public LastSongEntry(Song song){
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
	private Integer duration;
	
	private Long lastplaytime;
	
	private Integer playtimes;
	
	@XmlElement	
	public Long getLastplaytime() {
		lastplaytime=this.song.getLastPlayTime();
		return lastplaytime;
	}

	@XmlElement	
	public Integer getPlaytimes() {
		playtimes=this.song.getPlayTimes();
		return playtimes;
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
		albumid=this.song.getAlbumId();
		return albumid;
	}
	@XmlElement
	public String getArtist() {
		artist=this.song.getArtistName();
		return artist;
	}
	@XmlElement
	public Long getArtistid() {
		artistid=this.song.getArtistId();
		return artistid;
	}

	@XmlElement
	public Integer getDuration() {
		duration=this.song.getSongDuration();
		return duration;
	}

}
