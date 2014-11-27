package com.kascend.music2.api3.service.metadata.response.entry;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;

@XmlType(propOrder = { "billboardscount", "billboard" })
public class New_BillboardListEntry extends MusicBaseEntry{
	private Integer billboardscount;
	
	private List<New_BillboardEntry> billboard;

	@XmlElement
	public Integer getBillboardscount() {
		return billboardscount;
	}

	public void setBillboardscount(Integer billboardscount) {
		this.billboardscount = billboardscount;
	}
	@XmlElement
	public List<New_BillboardEntry> getBillboard() {
		return billboard;
	}

	public void setBillboard(List<New_BillboardEntry> billboard) {
		this.billboard = billboard;
	}
	
	
	
}
