package com.kascend.music2.api3.service.metadata.info;

import com.kascend.interfaceframework.controller.BaseInfo;

public class MusicInfo extends BaseInfo {

	private Integer page;
	private Integer pagecount;
	private Integer albumartflag;
	private Integer allcountflag;
	private Integer start;
	private Long billboardid;
	private Integer bioflag;
	private Long updatetime;
	private Integer regionflag;
	private Integer artistartflag;
	private String artist;
	private Long artistid;
	private Long albumid;
	private String album;
	private String song;
	private Long songid;
	private Integer rate;
	private String keyname;
	private Integer albumartsize;
	private Integer artistartsize;
	private String clientversionid;
	private Integer stattype;
	private Integer linkagetype;
	private String url;
	private Integer availability;
	private String musicfeatures;
	private Integer lyricflag;
	private Integer artistartfilter;
	private Integer albumartfilter;
	private Integer lyricfilter;
	private String filename;
	private Integer client;
	private Integer type;
	private Integer like;
	private Long billboardgroupid;
	private String billboardgroupcode;
	private String fingerprint;
	private Integer  length;
	private Integer mvflag;
	private Integer mtvid;
	private Integer mtvartsize;
	private Integer albummtvflag;
	private Integer artistmtvflag;
	private String xml;
	private Integer flushcode;
	
	public Integer getFlushcode() {
		return flushcode;
	}

	public void setFlushcode(Integer flushcode) {
		this.flushcode = flushcode;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public Integer getAlbummtvflag() {
		return albummtvflag;
	}

	public void setAlbummtvflag(Integer albummtvflag) {
		this.albummtvflag = albummtvflag;
	}

	public Integer getArtistmtvflag() {
		return artistmtvflag;
	}

	public void setArtistmtvflag(Integer artistmtvflag) {
		this.artistmtvflag = artistmtvflag;
	}

	public Integer getMtvartsize() {
		return mtvartsize;
	}

	public void setMtvartsize(Integer mtvartsize) {
		this.mtvartsize = mtvartsize;
	}

	public Integer getMtvid() {
		return mtvid;
	}

	public void setMtvid(Integer mtvid) {
		this.mtvid = mtvid;
	}

	public Integer getMvflag() {
		return mvflag;
	}

	public void setMvflag(Integer mvflag) {
		this.mvflag = mvflag;
	}

	public String getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Long getBillboardgroupid() {
		return billboardgroupid;
	}

	public void setBillboardgroupid(Long billboardgroupid) {
		this.billboardgroupid = billboardgroupid;
	}

	public String getBillboardgroupcode() {
		return billboardgroupcode;
	}

	public void setBillboardgroupcode(String billboardgroupcode) {
		this.billboardgroupcode = billboardgroupcode;
	}

	public Integer getLike() {
		return like;
	}

	public void setLike(Integer like) {
		this.like = like;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getClient() {
		return client;
	}

	public void setClient(Integer client) {
		this.client = client;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Integer getLyricflag() {
		return lyricflag;
	}

	public void setLyricflag(Integer lyricflag) {
		this.lyricflag = lyricflag;
	}

	public Integer getArtistartfilter() {
		return artistartfilter;
	}

	public void setArtistartfilter(Integer artistartfilter) {
		this.artistartfilter = artistartfilter;
	}

	public Integer getAlbumartfilter() {
		return albumartfilter;
	}

	public void setAlbumartfilter(Integer albumartfilter) {
		this.albumartfilter = albumartfilter;
	}

	public Integer getLyricfilter() {
		return lyricfilter;
	}

	public void setLyricfilter(Integer lyricfilter) {
		this.lyricfilter = lyricfilter;
	}

	public String getMusicfeatures() {
		return musicfeatures;
	}

	public void setMusicfeatures(String musicfeatures) {
		this.musicfeatures = musicfeatures;
	}

	public Integer getLinkagetype() {
		return linkagetype;
	}

	public void setLinkagetype(Integer linkagetype) {
		this.linkagetype = linkagetype;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getAvailability() {
		return availability;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	public Integer getStattype() {
		return stattype;
	}

	public void setStattype(Integer stattype) {
		this.stattype = stattype;
	}

	public String getClientversionid() {
		return clientversionid;
	}

	public void setClientversionid(String clientversionid) {
		this.clientversionid = clientversionid;
	}

	public Integer getArtistartsize() {
		return artistartsize;
	}

	public void setArtistartsize(Integer artistartsize) {
		this.artistartsize = artistartsize;
	}

	public Integer getAlbumartsize() {
		return albumartsize;
	}

	public void setAlbumartsize(Integer albumartsize) {
		this.albumartsize = albumartsize;
	}

	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Long getSongid() {
		return songid;
	}

	public void setSongid(Long songid) {
		this.songid = songid;
	}

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	public Long getAlbumid() {
		return albumid;
	}

	public void setAlbumid(Long albumid) {
		this.albumid = albumid;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Long getArtistid() {
		return artistid;
	}

	public void setArtistid(Long artistid) {
		this.artistid = artistid;
	}

	public Integer getArtistartflag() {
		return artistartflag;
	}

	public void setArtistartflag(Integer artistartflag) {
		this.artistartflag = artistartflag;
	}

	public Long getBillboardid() {
		return billboardid;
	}

	public void setBillboardid(Long billboardid) {
		this.billboardid = billboardid;
	}

	public Integer getBioflag() {
		return bioflag;
	}

	public void setBioflag(Integer bioflag) {
		this.bioflag = bioflag;
	}

	public Long getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Long updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getRegionflag() {
		return regionflag;
	}

	public void setRegionflag(Integer regionflag) {
		this.regionflag = regionflag;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPagecount() {
		return pagecount;
	}

	public void setPagecount(Integer pagecount) {
		this.pagecount = pagecount;
	}

	public Integer getAlbumartflag() {
		return albumartflag;
	}

	public void setAlbumartflag(Integer albumartflag) {
		this.albumartflag = albumartflag;
	}

	public Integer getAllcountflag() {
		return allcountflag;
	}

	public void setAllcountflag(Integer allcountflag) {
		this.allcountflag = allcountflag;
	}

}
