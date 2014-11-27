package com.kascend.music2.api3.service.metadata.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.metadata.response.entry.New_ArtistListEntry;
import com.kascend.music2.api3.service.response.MusicBaseResponse;
import com.kascend.music2.api3.service.social.response.entry.ArtistFollowStatusListEntry;
@XmlRootElement(name="response")
@XmlType(propOrder = { "artistslist", "artistfollowstatuslist" })
public class New_ArtistListResponse extends MusicBaseResponse{
	
	private New_ArtistListEntry artistslist;
	
	private ArtistFollowStatusListEntry artistfollowstatuslist;
	
	@XmlElement
	public ArtistFollowStatusListEntry getArtistfollowstatuslist() {
		return artistfollowstatuslist;
	}

	public void setArtistfollowstatuslist(
			ArtistFollowStatusListEntry artistfollowstatuslist) {
		this.artistfollowstatuslist = artistfollowstatuslist;
	}

	@XmlElement
	public New_ArtistListEntry getArtistslist() {
		return artistslist;
	}

	public void setArtistslist(New_ArtistListEntry artistslist) {
		this.artistslist = artistslist;
	}
	
	
}
