package com.kascend.music2.api3.service.social.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.MusicBaseResponse;
import com.kascend.music2.api3.service.social.response.entry.BoundSnsInfoEntryList;
import com.kascend.music2.api3.service.social.response.entry.UserEntry;
import com.kascend.music2.api3.service.social.response.entry.UserFollowStatusListEntry;

@XmlRootElement(name = "response")
@XmlType(propOrder = { "user", "userfollowstatuslist","boundsnslist"})
public class UserResponse extends MusicBaseResponse {
	
	private UserEntry user;
	
	private UserFollowStatusListEntry userfollowstatuslist;
	
	private BoundSnsInfoEntryList boundsnslist;
	
	public BoundSnsInfoEntryList getBoundsnslist() {
		return boundsnslist;
	}

	public void setBoundsnslist(BoundSnsInfoEntryList boundsnslist) {
		this.boundsnslist = boundsnslist;
	}

	@XmlElement
	public UserEntry getUser() {
		return user;
	}

	public void setUser(UserEntry user) {
		this.user = user;
	}
	
	@XmlElement
	public UserFollowStatusListEntry getUserfollowstatuslist() {
		return userfollowstatuslist;
	}

	public void setUserfollowstatuslist(
			UserFollowStatusListEntry userfollowstatuslist) {
		this.userfollowstatuslist = userfollowstatuslist;
	}

}
