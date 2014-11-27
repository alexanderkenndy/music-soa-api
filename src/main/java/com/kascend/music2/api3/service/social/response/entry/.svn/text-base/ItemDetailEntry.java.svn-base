package com.kascend.music2.api3.service.social.response.entry;

import javax.xml.bind.annotation.XmlElement;

import com.kascend.music2.api3.service.metadata.response.entry.New_AlbumEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_ArtistEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_MvEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_SongEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_UserPlaylistEntry;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;

public class ItemDetailEntry extends MusicBaseEntry {
	
	
	private New_SongEntry song;
	private New_ArtistEntry artist;
	private New_AlbumEntry album;
	private New_MvEntry mv;
	private SnsItemEntry snsitem;
	private UserPlaylistEntry playlist;
	private ItemCommentEntry comment;
	
	
	@XmlElement
	public ItemCommentEntry getComment() {
		return comment;
	}
	public void setComment(ItemCommentEntry comment) {
		this.comment = comment;
	}
	@XmlElement
	public New_SongEntry getSong() {
		return song;
	}
	public void setSong(New_SongEntry song) {
		this.song = song;
	}
	@XmlElement
	public New_ArtistEntry getArtist() {
		return artist;
	}
	public void setArtist(New_ArtistEntry artist) {
		this.artist = artist;
	}
	@XmlElement
	public New_AlbumEntry getAlbum() {
		return album;
	}
	public void setAlbum(New_AlbumEntry album) {
		this.album = album;
	}
	@XmlElement
	public New_MvEntry getMv() {
		return mv;
	}
	public void setMv(New_MvEntry mv) {
		this.mv = mv;
	}
	@XmlElement
	public SnsItemEntry getSnsitem() {
		return snsitem;
	}
	public void setSnsitem(SnsItemEntry snsitem) {
		this.snsitem = snsitem;
	}
	@XmlElement
	public UserPlaylistEntry getPlaylist() {
		return playlist;
	}
	public void setPlaylist(UserPlaylistEntry playlist) {
		this.playlist = playlist;
	}	
}
