package com.kascend.music2.api3.service.metadata.info;

public class LookupMvInfo extends GetSearchInfo {
	private int artistmvflag;
	private int albummvflag;
	private int artistmtvflag;
	private int albummtvflag;
	private String xml;
	
	private int pagecount=10;
	
	public int getPagecount() {
		return pagecount;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public int getArtistmvflag() {
		return artistmvflag;
	}
	public void setArtistmvflag(int artistmvflag) {
		this.artistmvflag = artistmvflag;
	}
	public int getAlbummvflag() {
		return albummvflag;
	}
	public void setAlbummvflag(int albummvflag) {
		this.albummvflag = albummvflag;
	}
	public int getArtistmtvflag() {
		return artistmtvflag;
	}
	public void setArtistmtvflag(int artistmtvflag) {
		this.artistmtvflag = artistmtvflag;
	}
	public int getAlbummtvflag() {
		return albummtvflag;
	}
	public void setAlbummtvflag(int albummtvflag) {
		this.albummtvflag = albummtvflag;
	}

}
