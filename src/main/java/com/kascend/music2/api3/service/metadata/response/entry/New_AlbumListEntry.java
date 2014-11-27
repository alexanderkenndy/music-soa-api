package com.kascend.music2.api3.service.metadata.response.entry;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;

@XmlType(propOrder = { "allcount", "totalsize", "albumscount","billboardid","subbillboardid",
		"billboardtitle", "subbillboardtitle", "updatetime","subbillboardlist", "album" })
public class New_AlbumListEntry extends MusicBaseEntry{

	private Integer albumscount;
	private List<New_AlbumEntry> album;
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
	public Integer getAllcount() {
		return allcount;
	}

	public void setAllcount(Integer allcount) {
		this.allcount = allcount;
	}
	@XmlElement
	public New_SubBillboardListEntry getSubbillboardlist() {
		return subbillboardlist;
	}

	public void setSubbillboardlist(New_SubBillboardListEntry subbillboardlist) {
		this.subbillboardlist = subbillboardlist;
	}

	@XmlElement
	public String getSubbillboardtitle() {
		return subbillboardtitle;
	}

	public void setSubbillboardtitle(String subbillboardtitle) {
		this.subbillboardtitle = subbillboardtitle;
	}

	@XmlElement
	public Integer getTotalsize() {
		return totalsize;
	}

	public void setTotalsize(Integer totalsize) {
		this.totalsize = totalsize;
	}

	@XmlElement
	public String getBillboardtitle() {
		return billboardtitle;
	}

	public void setBillboardtitle(String billboardtitle) {
		this.billboardtitle = billboardtitle;
	}

	@XmlElement
	public Long getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Long updatetime) {
		this.updatetime = updatetime;
	}

	@XmlElement
	public Integer getAlbumscount() {
		return albumscount;
	}

	public void setAlbumscount(Integer albumscount) {
		this.albumscount = albumscount;
	}

	@XmlElement
	public List<New_AlbumEntry> getAlbum() {
		return album;
	}

	public void setAlbum(List<New_AlbumEntry> album) {
		this.album = album;
	}

}
