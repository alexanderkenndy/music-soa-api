package com.kascend.music2.api3.service.metadata.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.entity.SubBillboard;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
@XmlType(propOrder = { "subbillboardid","subbillboardtitle"})
public class New_SubBillboardEntry extends MusicBaseEntry {
	
	
	private  SubBillboard subBillboard;
	
	public New_SubBillboardEntry(){
		
	}
	public New_SubBillboardEntry(SubBillboard subBillboard){
		this.subBillboard=subBillboard;
	}
	
	private Long subbillboardid;
	
	private String subbillboardtitle;
	@XmlElement
	public Long getSubbillboardid() {
		subbillboardid=this.subBillboard.getSubBillboardId();
		return subbillboardid;
	}
	public void setSubbillboardid(Long subbillboardid) {
		this.subbillboardid = subbillboardid;
	}
	@XmlElement
	public String getSubbillboardtitle() {
		subbillboardtitle=this.subBillboard.getSubBillboardTitle();
		return subbillboardtitle;
	}
	public void setSubbillboardtitle(String subbillboardtitle) {
		this.subbillboardtitle = subbillboardtitle;
	}
	

}
