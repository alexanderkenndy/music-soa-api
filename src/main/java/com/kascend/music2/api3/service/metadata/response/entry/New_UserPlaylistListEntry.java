package com.kascend.music2.api3.service.metadata.response.entry;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlType(propOrder = { "allcount","playlistscount","totalsize",  "billboardtitle",
		"subbillboardtitle","picstatus","updatetime","playlist"})
public class New_UserPlaylistListEntry implements Serializable{
	private Integer allcount;
	private Integer playlistscount;
	private List<New_UserPlaylistEntry> playlist;
	private Integer totalsize;
	private String billboardtitle;
	private String subbillboardtitle;
	private Long updatetime;
	private Integer picstatus; //表示榜期是否有图片，默认0有，1无
	
	
	@XmlElement
	public Integer getAllcount() {
		return allcount;
	}
	public void setAllcount(Integer allcount) {
		this.allcount = allcount;
	}

	@XmlElement
	public Integer getTotalsize() {
		return totalsize;
	}
	@XmlElement
	public Integer getPlaylistscount() {
		return playlistscount;
	}
	public void setPlaylistscount(Integer playlistscount) {
		this.playlistscount = playlistscount;
	}
	@XmlElement
	public List<New_UserPlaylistEntry> getPlaylist() {
		return playlist;
	}
	public void setPlaylist(List<New_UserPlaylistEntry> playlist) {
		this.playlist = playlist;
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
	public Integer getPicstatus() {
		return picstatus;
	}
	public void setPicstatus(Integer picstatus) {
		this.picstatus = picstatus;
	}
	
}
