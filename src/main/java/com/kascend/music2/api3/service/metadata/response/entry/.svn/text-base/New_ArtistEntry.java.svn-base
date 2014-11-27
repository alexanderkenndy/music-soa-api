package com.kascend.music2.api3.service.metadata.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.entity.Artist;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.ServiceUtils;
@XmlType(propOrder = { "title","englishname", "artistid", "type","region","unlistenedalbums","artistart","bio","songscount", "albumscount","mtvscount","artistartlist","albumslist"})
public class New_ArtistEntry extends MusicBaseEntry{
	
	
	
	private Artist artist;
	
	public New_ArtistEntry(){
		
	}
	
	public New_ArtistEntry(Artist artist){
		this.artist=artist;
	}
	private String title;
	private String englishname;
	private Long artistid;
	private String region;
	private String type;
	private String artistart;
	private String bio;
	private Integer songscount;
	private Integer albumscount;
	private Integer mtvscount;
	
	private Integer unlistenedalbums;
	
	private New_ArtistartEntry artistartlist;
	
	private New_AlbumListEntry albumslist;
	
	@XmlElement
	public Integer getUnlistenedalbums() {
		return unlistenedalbums;
	}

	public void setUnlistenedalbums(Integer unlistenedalbums) {
		this.unlistenedalbums = unlistenedalbums;
	}

	@XmlElement
	public New_AlbumListEntry getAlbumslist() {
		return albumslist;
	}

	public void setAlbumslist(New_AlbumListEntry albumslist) {
		this.albumslist = albumslist;
	}

	@XmlElement
	public String getEnglishname() {
		englishname=this.artist.getArtistEnglishName();
		return englishname;
	}

	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}
	@XmlElement
	public New_ArtistartEntry getArtistartlist() {
		return artistartlist;
	}

	public void setArtistartlist(New_ArtistartEntry artistartlist) {
		this.artistartlist = artistartlist;
	}

	@XmlElement
	public String getTitle() {
		title=this.artist.getArtistName();
		return title;
	}

	@XmlElement
	public Long getArtistid() {
		artistid=this.artist.getArtistId();
		return artistid;
	}

	@XmlElement
	public String getRegion() {
		int regionId= this.artist.getArtistRegion();
		region=ServiceUtils.getMetaDataRegionByRegionId(regionId);
		return region;
	}

	@XmlElement
	public String getType() {
		int genderId=this.artist.getArtistType();
		type=ServiceUtils.getArtistGenderByGenderId(genderId);
		return type;
	}

	@XmlElement
	public String getArtistart() {
		artistart=ServiceUtils.returnURL(this.artist.getArtistThumbnail());
		return artistart;
	}

	@XmlElement
	public String getBio() {
		bio=this.artist.getArtistDescription();
		return bio;
	}

	@XmlElement
	public Integer getSongscount() {
		if(this.artist.getPublishedSongs()>0){
			songscount=this.artist.getPublishedSongs();
		}
		return songscount;
	}

	@XmlElement
	public Integer getAlbumscount() {
		if(this.artist.getPublishedAlbums()>0){
			albumscount=this.artist.getPublishedAlbums();
		}
		return albumscount;
	}

	@XmlElement
	public Integer getMtvscount() {
		if(this.artist.getPublishedMvs() >0){
			mtvscount=this.artist.getPublishedMvs();
		}
		return mtvscount;
	}

}
