package com.kascend.music2.api3.service.social.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.entity.UserSns;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
@XmlType(propOrder = { "snssite", "boundstatus"
		})
public class BoundSnsInfoEntry extends MusicBaseEntry {
	private Integer snssite;
	private Integer boundstatus;
	
	private UserSns userSns;
	public BoundSnsInfoEntry(UserSns userSns){
		this.userSns=userSns;
	}
	public BoundSnsInfoEntry(){
		
	}
	@XmlElement
	public Integer getSnssite() {
		snssite=this.userSns.getSnsSite();
		return snssite;
	}
	public void setSnssite(Integer snssite) {
		this.snssite = snssite;
	}
	public Integer getBoundstatus() {
		//boundstatus=this.userSns.getStatus();
		boundstatus=1;
		return boundstatus;
	}
	public void setBoundstatus(Integer boundstatus) {
		this.boundstatus = boundstatus;
	}
	
}
