package com.kascend.music2.api3.service.social.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.kascend.music2.api3.service.response.MusicBaseResponse;
import com.kascend.music2.api3.service.social.response.entry.ItemListEntry;

@XmlRootElement(name = "response")
public class ItemListResponse extends MusicBaseResponse {
	
	private ItemListEntry itemslist;

	@XmlElement
	public ItemListEntry getItemslist() {
		return itemslist;
	}

	public void setItemslist(ItemListEntry itemslist) {
		this.itemslist = itemslist;
	}

}
