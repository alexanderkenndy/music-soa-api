package com.kascend.music2.api3.service.metadata.response.entry;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
@XmlType(propOrder = { "allcount", "totalsize", "artistscount","billboardid","subbillboardid",
		"billboardtitle", "subbillboardtitle", "updatetime", "subbillboardlist","artist" })
public class New_ArtistListEntry extends MusicBaseEntry{
	private Integer artistscount;
	private List<New_ArtistEntry> artist;
	protected New_SubBillboardListEntry subbillboardlist;
	private String billboardtitle;
	private String subbillboardtitle;
	private Long updatetime;
	private Integer totalsize;
	private Integer allcount;
	private Long billboardid;
	private Long subbillboardid;
	
	@XmlElement
	public Long getBillboardid() {
		return billboardid;
	}
	public void setBillboardid(Long billboardid) {
		this.billboardid = billboardid;
	}
	@XmlElement
	public Long getSubbillboardid() {
		return subbillboardid;
	}
	public void setSubbillboardid(Long subbillboardid) {
		this.subbillboardid = subbillboardid;
	}
	@XmlElement
	public New_SubBillboardListEntry getSubbillboardlist() {
		return subbillboardlist;
	}
	public void setSubbillboardlist(New_SubBillboardListEntry subbillboardlist) {
		this.subbillboardlist = subbillboardlist;
	}
	@XmlElement
	public Integer getArtistscount() {
		return artistscount;
	}
	public void setArtistscount(Integer artistscount) {
		this.artistscount = artistscount;
	}
	@XmlElement
	public List<New_ArtistEntry> getArtist() {
		return artist;
	}
	public void setArtist(List<New_ArtistEntry> artist) {
		this.artist = artist;
	}
	@XmlElement
	public String getBillboardtitle() {
		return billboardtitle;
	}
	public void setBillboardtitle(String billboardtitle) {
		this.billboardtitle = billboardtitle;
	}
	@XmlElement
	public String getSubbillboardtitle() {
		return subbillboardtitle;
	}
	public void setSubbillboardtitle(String subbillboardtitle) {
		this.subbillboardtitle = subbillboardtitle;
	}
	@XmlElement
	public Long getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Long updatetime) {
		this.updatetime = updatetime;
	}
	@XmlElement
	public Integer getTotalsize() {
		return totalsize;
	}
	public void setTotalsize(Integer totalsize) {
		this.totalsize = totalsize;
	}
	@XmlElement
	public Integer getAllcount() {
		return allcount;
	}
	public void setAllcount(Integer allcount) {
		this.allcount = allcount;
	}
	
}
