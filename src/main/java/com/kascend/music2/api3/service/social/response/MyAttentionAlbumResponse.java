package com.kascend.music2.api3.service.social.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.MusicBaseResponse;
import com.kascend.music2.api3.service.social.response.entry.MyAttentionAlbumEntry;
import com.kascend.music2.api3.service.social.response.entry.UserFollowStatusListEntry;
@XmlRootElement(name = "response")
@XmlType(propOrder = { "attentionalbum", "userfollowstatuslist"})
public class MyAttentionAlbumResponse extends MusicBaseResponse {
	
	private MyAttentionAlbumEntry attentionalbum;
	private UserFollowStatusListEntry userfollowstatuslist;
	
	@XmlElement
	public UserFollowStatusListEntry getUserfollowstatuslist() {
		return userfollowstatuslist;
	}

	public void setUserfollowstatuslist(
			UserFollowStatusListEntry userfollowstatuslist) {
		this.userfollowstatuslist = userfollowstatuslist;
	}

	@XmlElement
	public MyAttentionAlbumEntry getAttentionalbum() {
		return attentionalbum;
	}

	public void setAttentionalbum(MyAttentionAlbumEntry attentionalbum) {
		this.attentionalbum = attentionalbum;
	}
	
}
