package com.kascend.music2.api3.service.metadata.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.entity.Album;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.social.response.entry.UserListEntry;
import com.kascend.music2.api3.service.util.Constants;
import com.kascend.music2.api3.service.util.ServiceUtils;

@XmlType(propOrder = { "title", "albumid", "artist", "artistid", "albumart",
		 "bio", "publishtime", "songscount","lastplaytime","listeneduserscount","lastoptype","lastop","lastcomment","songslist","userslist","listenedsongslist","othersongslist"})
public class New_AlbumEntry extends MusicBaseEntry{
	
	
	private Album album;
	
	private String title;
	private Long albumid;
	private String artist;
	private Long artistid;
	private String albumart;
	private String bio;
	private String publishtime;
	private Integer songscount;
	
	private Long lastplaytime;
	
	private Integer listeneduserscount;
	
	private New_SongListEntry songslist;
	
	private New_SongListEntry listenedsongslist;
	
	private New_SongListEntry othersongslist;
	
	
	private UserListEntry userslist;
	
	private Integer lastoptype;
	
	private String lastop;
	
	private String lastcomment;

	
	@XmlElement
	public String getLastop() {
		lastop=this.album.getLastOp();
		return lastop;
	}
	@XmlElement
	public String getLastcomment() {
//		if(this.album.getLastOpType()==Constants.OP_TYPE_COMMENT){
//		
//		}
		lastcomment=this.album.getLastComment();
		return lastcomment;
	}

	@XmlElement
	public Integer getLastoptype() {
		if(this.album.getLastOpType()>=0){
			lastoptype=this.album.getLastOpType();
		}
		return lastoptype;
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
	public UserListEntry getUserslist() {
		return userslist;
	}

	public void setUserslist(UserListEntry userslist) {
		this.userslist = userslist;
	}

	@XmlElement
	public New_SongListEntry getSongslist() {
		return songslist;
	}

	public void setSongslist(New_SongListEntry songslist) {
		this.songslist = songslist;
	}

	public New_AlbumEntry(){
		
	}
	
	public New_AlbumEntry(Album album){
		this.album=album;
	}
	
	@XmlElement
	public Integer getListeneduserscount() {
		listeneduserscount=this.album.getListenedUsersCount();
		return listeneduserscount;
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
	public Long getLastplaytime() {
		lastplaytime=this.album.getLastPlayTime();
		return lastplaytime;
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
		if(this.album.getPublishedSongs()>0){
			songscount=this.album.getPublishedSongs();
		}
		return songscount;
	}
	public void setSongscount(Integer songscount) {
		this.songscount = songscount;
	}
	
}
