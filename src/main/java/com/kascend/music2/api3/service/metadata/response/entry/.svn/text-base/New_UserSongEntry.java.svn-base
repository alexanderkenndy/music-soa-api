package com.kascend.music2.api3.service.metadata.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.entity.UserSong;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.ServiceUtils;

@XmlType(propOrder = { "title","songid", 
		"album", "albumid", "artist", "artistid", 
		 "albumart","artistart","duration","mv","playtimes","songpath","songsyncstatus"})

public class New_UserSongEntry  extends MusicBaseEntry{
	
	private UserSong userSong;
	
	public New_UserSongEntry(){
		
	}
	
	public New_UserSongEntry(UserSong userSong){
		this.userSong=userSong;
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
	
	private String artistart;
	private Integer duration;
	private Integer mv;
	
	private Integer playtimes;
	
	private String songpath;
	
	private Integer songsyncstatus;
	
	@XmlElement
	public Integer getSongsyncstatus() {
		if(this.userSong.getSongsyncstatus()>-1){
			songsyncstatus=this.userSong.getSongsyncstatus();
		}
		return songsyncstatus;
	}

	@XmlElement
	public String getArtistart() {
		artistart=ServiceUtils.returnURL(this.userSong.getArtistArt());
		return artistart;
	}

	@XmlElement
	public Integer getPlaytimes() {
		playtimes=this.userSong.getPlayTimes();
		return playtimes;
	}

	@XmlElement
	public Integer getMv() {
		mv=this.userSong.getHasMv();
		return mv;
	}

	@XmlElement
	public String getTitle() {
		title=this.userSong.getSongTitle();
		return title;
	}
	@XmlElement
	public Long getSongid() {
		songid=this.userSong.getSongId();
		return songid;
	}
	@XmlElement
	public String getAlbum() {
		album=this.userSong.getAlbumTitle();
		return album;
	}
	@XmlElement
	public Long getAlbumid() {
		albumid=this.userSong.getAlbumId();
		return albumid;
	}
	@XmlElement
	public String getArtist() {
		artist=this.userSong.getArtistName();
		return artist;
	}
	@XmlElement
	public Long getArtistid() {
		artistid=this.userSong.getArtistId();
		return artistid;
	}
	@XmlElement
	public String getAlbumart() {
		albumart=ServiceUtils.returnURL(this.userSong.getSongThumb());
		return albumart;
	}
	@XmlElement
	public Integer getDuration() {
		if(this.userSong.getDuration()>=0){
			duration=this.userSong.getDuration();
		}
		return duration;
	}
	@XmlElement
	public String getSongpath() {
		songpath=this.userSong.getSongPath();
		return songpath;
	}

}
