package com.kascend.music2.api3.service.metadata.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.metadata.response.entry.New_BillBoardGroupListEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_BillboardListEntry;
import com.kascend.music2.api3.service.response.MusicBaseResponse;


@XmlRootElement(name = "response")
@XmlType(propOrder = { "billboardslist", "billboardgroupslist" })
public class New_BillboardListResponse extends MusicBaseResponse{
	private New_BillboardListEntry billboardslist;
	private New_BillBoardGroupListEntry billboardgroupslist;
	@XmlElement
	public New_BillboardListEntry getBillboardslist() {
		return billboardslist;
	}

	public void setBillboardslist(New_BillboardListEntry billboardslist) {
		this.billboardslist = billboardslist;
	}

	public New_BillBoardGroupListEntry getBillboardgroupslist() {
		return billboardgroupslist;
	}

	public void setBillboardgroupslist(New_BillBoardGroupListEntry billboardgroupslist) {
		this.billboardgroupslist = billboardgroupslist;
	}
	
	
}
