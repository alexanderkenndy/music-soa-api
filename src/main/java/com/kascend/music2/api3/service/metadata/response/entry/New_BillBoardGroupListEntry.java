package com.kascend.music2.api3.service.metadata.response.entry;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class New_BillBoardGroupListEntry implements Serializable {
	private List<New_BillBoardGroupEntry> billboardgroup;

	@XmlElement
	public List<New_BillBoardGroupEntry> getBillboardgroup() {
		return billboardgroup;
	}

	public void setBillboardgroup(List<New_BillBoardGroupEntry> billboardgroup) {
		this.billboardgroup = billboardgroup;
	}

}
