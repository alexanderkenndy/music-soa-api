package com.kascend.music2.api3.service.social.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.common.util.Validator;
import com.kascend.music2.api3.entity.Album;
import com.kascend.music2.api3.service.metadata.response.entry.New_SongListEntry;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.ServiceUtils;
@XmlType(propOrder = { "title", "albumid", "artist", "artistid", "albumart",
		 "bio", "publishtime","user","listenedsongslist","othersongslist"})
public class MyAttentionAlbumEntry extends MusicBaseEntry {
	
	private String title;
	private Long albumid;
	private String artist;
	private Long artistid;
	private String albumart;
	private String bio;
	private String publishtime;
	
	private Album album;
	
	private UserEntry user;
	
	private New_SongListEntry listenedsongslist;
	
	private New_SongListEntry othersongslist;
	
	public MyAttentionAlbumEntry(Album album){
		this.album=album;
	}
	
	public MyAttentionAlbumEntry(){
		
	}
	
	@XmlElement
	public UserEntry getUser() {
		return user;
	}
	public void setUser(UserEntry user) {
		this.user = user;
	}
	@XmlElement
	public New_SongListEntry getListenedsongslist() {
		return listenedsongslist;
	}
	public void setListenedsongslist(New_SongListEntry listenedsongslist) {
		this.listenedsongslist = listenedsongslist;
	}
	@XmlElement
	public New_SongListEntry getOthersongslist() {
		return othersongslist;
	}
	public void setOthersongslist(New_SongListEntry othersongslist) {
		this.othersongslist = othersongslist;
	}

	@XmlElement
	public String getTitle() {
		title=this.album.getAlbumTitle();
		return title;
	}
	@XmlElement
	public Long getAlbumid() {
		albumid=this.album.getAlbumId();
		return albumid;
	}
	@XmlElement
	public String getArtist() {
		artist=this.album.getArtistName();
		return artist;
	}
	@XmlElement
	public Long getArtistid() {
		artistid=this.album.getArtistId();
		return artistid;
	}
	@XmlElement
	public String getAlbumart() {
		String albumArt=this.album.getAlbumThumbUri();
		if(!Validator.isBlank(albumArt)){
			if(albumArt.startsWith("http")){
				albumart=albumArt;
			}else{
				albumart=ServiceUtils.returnURL(albumArt);
			}
		}
		return albumart;
	}
	@XmlElement
	public String getBio() {
		bio=this.album.getAlbumBio();
		return bio;
	}
	@XmlElement
	public String getPublishtime() {
		publishtime=this.album.getAlbumPublishTime();
		return publishtime;
	}

}
