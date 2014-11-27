package com.kascend.music2.api3.service.social.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.MusicBaseResponse;
import com.kascend.music2.api3.service.social.response.entry.DiscoveryAlbumOfSongEntry;
import com.kascend.music2.api3.service.social.response.entry.UserFollowStatusListEntry;
@XmlRootElement(name = "response")
@XmlType(propOrder = { "album", "userfollowstatuslist"})
public class DiscoveryAlbumOfSongResponse extends MusicBaseResponse {
	
	private DiscoveryAlbumOfSongEntry album;
	
	private UserFollowStatusListEntry userfollowstatuslist;
	
	@XmlElement
	public DiscoveryAlbumOfSongEntry getAlbum() {
		return album;
	}

	public void setAlbum(DiscoveryAlbumOfSongEntry album) {
		this.album = album;
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
