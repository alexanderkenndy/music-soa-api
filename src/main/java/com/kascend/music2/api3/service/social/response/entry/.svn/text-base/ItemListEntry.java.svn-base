package com.kascend.music2.api3.service.social.response.entry;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
@XmlType(propOrder = { "allcount", "item"
		})
public class ItemListEntry extends MusicBaseEntry {

	private List<ItemEntry> item;
	
	private Integer allcount;
	
	
	@XmlElement
	public Integer getAllcount() {
		return allcount;
	}

	public void setAllcount(Integer allcount) {
		this.allcount = allcount;
	}

	public ItemListEntry(){}
	
	public ItemListEntry(List<ItemEntry> item){
		this.item=item;
	}
	@XmlElement
	public List<ItemEntry> getItem() {
		return item;
	}

	public void setItem(List<ItemEntry> item) {
		this.item = item;
	}


}
