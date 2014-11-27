package com.kascend.music2.api3.service.metadata.response.entry;

import javax.xml.bind.annotation.XmlType;

import com.kascend.common.util.Validator;
import com.kascend.frameworkcommons.config.Configer;
import com.kascend.music2.api3.entity.Billboard;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.ServiceUtils;

@XmlType(propOrder = { "source", "title", "billboardthumb", "billboardid",
		"billboardtype", "totalsize", "updatetime","bio" })
public class New_BillboardEntry extends MusicBaseEntry{
	
	public New_BillboardEntry(){
		
	}
	
	public New_BillboardEntry(Billboard billboard){
		this.billboard=billboard;
	}
	
	private String source;
	private String title;
	private String billboardthumb;
	private Long billboardid;
	private Integer billboardtype;
	private Integer totalsize;
	private String bio;
	private Long updatetime;
	
	private Billboard billboard;
	
	public String getBio() {
		bio=this.billboard.getBillboardDescription();
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getSource() {
		source=this.billboard.getBillboardSource();
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTitle() {
		title=this.billboard.getBillboardTitle();
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBillboardthumb() {
		int width = 0;
		int height = 0;
		try {
			width = Configer.getValueInt("billboard.img.width");
			height = Configer.getValueInt("billboard.img.height");
		} catch (Exception e) {
			
		}
		billboardthumb=this.billboard.getBillboardThumb();
		if(!Validator.isBlank(billboardthumb)){
			StringBuffer bf = new StringBuffer(billboardthumb);
			bf.append(".");
			bf.append(width);
			bf.append("x");
			bf.append(height);
			bf.append(".jpg");
			billboardthumb=ServiceUtils.returnURL(bf.toString());
		}
		return billboardthumb;
	}

	public void setBillboardthumb(String billboardthumb) {
		this.billboardthumb = billboardthumb;
	}

	public Long getBillboardid() {
		billboardid=this.billboard.getBillboardId();
		return billboardid;
	}

	public void setBillboardid(Long billboardid) {
		this.billboardid = billboardid;
	}

	public Integer getBillboardtype() {
		billboardtype=this.billboard.getBillboardType();
		return billboardtype;
	}

	public void setBillboardtype(Integer billboardtype) {
		this.billboardtype = billboardtype;
	}

	public Integer getTotalsize() {
		totalsize=this.billboard.getBillboardSize();
		return totalsize;
	}

	public void setTotalsize(Integer totalsize) {
		this.totalsize = totalsize;
	}

	public Long getUpdatetime() {
		updatetime=this.billboard.getUpdateTime();
		return updatetime;
	}

	public void setUpdatetime(Long updatetime) {
		this.updatetime = updatetime;
	}
}
