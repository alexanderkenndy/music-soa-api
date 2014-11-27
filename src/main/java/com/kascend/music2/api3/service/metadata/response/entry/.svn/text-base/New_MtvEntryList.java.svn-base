package com.kascend.music2.api3.service.metadata.response.entry;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;


@XmlType(propOrder = { "allcount","mtvscount","totalsize", "billboardid","subbillboardid", "billboardtitle",
		"subbillboardtitle","picstatus","updatetime","subbillboardlist","mtv"})
public class New_MtvEntryList extends MusicBaseEntry{
	
	private Integer allcount;
	private Integer mtvscount;
	private List<New_MtvEntry> mtv;
	protected New_SubBillboardListEntry subbillboardlist;
	private Integer totalsize;
	private String billboardtitle;
	private String subbillboardtitle;
	private Long billboardid;
	private Long subbillboardid;
	private Long updatetime;
	private Integer picstatus; //表示榜期是否有图片，默认0有，1无
	
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
	public Integer getMtvscount() {
		return mtvscount;
	}
	public void setMtvscount(Integer mtvscount) {
		this.mtvscount = mtvscount;
	}
	@XmlElement
	public Integer getAllcount() {
		return allcount;
	}
	public void setAllcount(Integer allcount) {
		this.allcount = allcount;
	}


	@XmlElement
	public List<New_MtvEntry> getMtv() {
		return mtv;
	}
	public void setMtv(List<New_MtvEntry> mtv) {
		this.mtv = mtv;
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
