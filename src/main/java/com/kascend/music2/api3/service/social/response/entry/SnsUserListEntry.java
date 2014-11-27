package com.kascend.music2.api3.service.social.response.entry;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
@XmlType(propOrder = { "allcount","snsuser"
})
public class SnsUserListEntry extends MusicBaseEntry{
	
	private List<SnsUserEntry> snsuser;
	
	private Integer allcount;
	
	
	@XmlElement
	public Integer getAllcount() {
		return allcount;
	}

	public void setAllcount(Integer allcount) {
		this.allcount = allcount;
	}

	@XmlElement
	public List<SnsUserEntry> getSnsuser() {
		return snsuser;
	}

	public void setSnsuser(List<SnsUserEntry> snsuser) {
		this.snsuser = snsuser;
	}
	
	
}
