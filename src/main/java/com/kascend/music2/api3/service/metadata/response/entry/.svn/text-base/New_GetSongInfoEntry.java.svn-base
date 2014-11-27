package com.kascend.music2.api3.service.metadata.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.entity.Song;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.ServiceUtils;

@XmlType(propOrder = { "songid", "title", "album", "albumid", "artist",
		"artistid", "albumart", "artistart", "lyric","mtv","mv"})
public class New_GetSongInfoEntry extends MusicBaseEntry{
	
	private Song song;
	public New_GetSongInfoEntry(){
		
	}
	
	public New_GetSongInfoEntry(Song song){
		this.song=song;
	}
	private Long songid;
	private String title;
	private String album;
	private Long albumid;
	private String artist;
	private Long artistid;
	private String albumart;
	private String artistart;
	private String lyric;
	private Integer mtv;
	private Integer mv;
	
	@XmlElement
	public Integer getMv() {
		mv=this.song.getHasMv();
		return mv;
	}

	public void setMv(Integer mv) {
		this.mv = mv;
	}

	@XmlElement
	public Integer getMtv() {
		mtv=this.song.getHasMv();
		return mtv;
	}

	public void setMtv(Integer mtv) {
		this.mtv = mtv;
	}

	@XmlElement
	public Long getAlbumid() {
		albumid=this.song.getAlbumId();
		return albumid;
	}

	public void setAlbumid(Long albumid) {
		this.albumid = albumid;
	}

	@XmlElement
	public Long getArtistid() {
		artistid=this.song.getArtistId();
		return artistid;
	}

	public void setArtistid(Long artistid) {
		this.artistid = artistid;
	}

	@XmlElement
	public Long getSongid() {
		songid=this.song.getSongId();
		return songid;
	}

	public void setSongid(Long songid) {
		this.songid = songid;
	}

	@XmlElement
	public String getTitle() {
		title=this.song.getSongTitle();
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement
	public String getAlbum() {
		album=this.song.getAlbumTitle();
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	@XmlElement
	public String getArtist() {
		artist=this.song.getArtistName();
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	@XmlElement
	public String getAlbumart() {
		albumart=ServiceUtils.returnURL(this.song.getSongThumbUri());
		return albumart;
	}

	public void setAlbumart(String albumart) {
		this.albumart = albumart;
	}

	@XmlElement
	public String getArtistart() {
		artistart=ServiceUtils.returnURL(this.song.getArtistArt());
		return artistart;
	}

	public void setArtistart(String artistart) {
		this.artistart = artistart;
	}

	@XmlElement
	public String getLyric() {
		return lyric;
	}

	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

}
