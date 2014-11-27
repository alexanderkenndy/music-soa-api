package com.kascend.music2.api3.service.social.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.MusicBaseResponse;
import com.kascend.music2.api3.service.social.response.entry.UserFollowStatusListEntry;
import com.kascend.music2.api3.service.social.response.entry.UserListEntry;

@XmlRootElement(name = "response")
@XmlType(propOrder = { "userslist", "userfollowstatuslist"})
public class UserListResponse extends MusicBaseResponse {
	
	private UserListEntry userslist;
	
	private UserFollowStatusListEntry userfollowstatuslist;
	@XmlElement
	public UserListEntry getUserslist() {
		return userslist;
	}

	public void setUserslist(UserListEntry userslist) {
		this.userslist = userslist;
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
