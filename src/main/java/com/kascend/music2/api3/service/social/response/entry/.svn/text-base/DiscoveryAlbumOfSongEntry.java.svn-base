package com.kascend.music2.api3.service.social.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.entity.Album;
import com.kascend.music2.api3.service.metadata.response.entry.New_SongListEntry;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.ServiceUtils;
@XmlType(propOrder = { "title", "albumid", "artist", "artistid", "albumart",
		 "bio", "publishtime","songscount","listeneduserscount","likeduserscount","songslist","listeneduserslist","likeduserslist"})
public class DiscoveryAlbumOfSongEntry extends MusicBaseEntry {
	
	private Album album;
	
	private String title;
	private Long albumid;
	private String artist;
	private Long artistid;
	private String albumart;
	private String bio;
	private String publishtime;
	private Integer songscount;
	
	private Integer listeneduserscount;
	
	private Integer likeduserscount;
	
	private New_SongListEntry songslist;
	
	private UserListEntry listeneduserslist;
	
	private UserListEntry likeduserslist;
	
	public DiscoveryAlbumOfSongEntry(){
		
	}
	public DiscoveryAlbumOfSongEntry(Album album){
		this.album=album;
	}
	
	public Integer getListeneduserscount() {
		listeneduserscount=this.album.getListenedUsersCount();
		return listeneduserscount;
	}
	public void setListeneduserscount(Integer listeneduserscount) {
		this.listeneduserscount = listeneduserscount;
	}
	
	
	public Integer getLikeduserscount() {
		return likeduserscount;
	}
	public void setLikeduserscount(Integer likeduserscount) {
		this.likeduserscount = likeduserscount;
	}
	@XmlElement
	public UserListEntry getLikeduserslist() {
		return likeduserslist;
	}
	public void setLikeduserslist(UserListEntry likeduserslist) {
		this.likeduserslist = likeduserslist;
	}
	@XmlElement
	public String getTitle() {
		title=this.album.getAlbumTitle();
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@XmlElement
	public Long getAlbumid() {
		albumid=this.album.getAlbumId();
		return albumid;
	}

	public void setAlbumid(Long albumid) {
		this.albumid = albumid;
	}
	@XmlElement
	public String getArtist() {
		artist=this.album.getArtistName();
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
	@XmlElement
	public Long getArtistid() {
		artistid=this.album.getArtistId();
		return artistid;
	}

	public void setArtistid(Long artistid) {
		this.artistid = artistid;
	}
	@XmlElement
	public String getAlbumart() {
		albumart=ServiceUtils.returnURL(this.album.getAlbumThumbUri());
		return albumart;
	}

	public void setAlbumart(String albumart) {
		this.albumart = albumart;
	}
	@XmlElement
	public String getBio() {
		bio=this.album.getAlbumBio();
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	@XmlElement
	public String getPublishtime() {
		publishtime=this.album.getAlbumPublishTime();
		return publishtime;
	}

	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}
	@XmlElement
	public Integer getSongscount() {
		songscount=this.album.getPublishedSongs();
		return songscount;
	}

	public void setSongscount(Integer songscount) {
		this.songscount = songscount;
	}
	@XmlElement
	public New_SongListEntry getSongslist() {
		return songslist;
	}

	public void setSongslist(New_SongListEntry songslist) {
		this.songslist = songslist;
	}
	@XmlElement
	public UserListEntry getListeneduserslist() {
		return listeneduserslist;
	}

	public void setListeneduserslist(UserListEntry listeneduserslist) {
		this.listeneduserslist = listeneduserslist;
	}
	
	
	

}
