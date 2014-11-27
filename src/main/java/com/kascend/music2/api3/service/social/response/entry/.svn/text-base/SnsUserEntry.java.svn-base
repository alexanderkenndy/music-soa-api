package com.kascend.music2.api3.service.social.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.common.util.Validator;
import com.kascend.music2.api3.entity.UserSns;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.ServiceUtils;
@XmlType(propOrder = { "snsuid","snsusername","headicon","location","gender","description",
		"itemcount","friendcount","followercount", "favoritecount"
})
public class SnsUserEntry extends MusicBaseEntry{
	
	private UserSns userSns;
	
	private Long snsuid;
	private String snsusername;
	private String headicon;
	private String location;
	private String gender;
	private String description;
	private Integer itemcount;
	private Integer friendcount;
	private Integer followercount;
	private Integer favoritecount;
	
	public SnsUserEntry(){
		
	}
	
	public SnsUserEntry(UserSns userSns){
		this.userSns=userSns;
	}
	@XmlElement
	public Long getSnsuid() {
		if(!Validator.isBlank(this.userSns.getSnsUid())){
			snsuid=Long.parseLong(this.userSns.getSnsUid());
		}
		return snsuid;
	}
	
	@XmlElement
	public String getSnsusername() {
		snsusername=this.userSns.getSnsUserName();
		return snsusername;
	}
	
	@XmlElement
	public String getHeadicon() {
		headicon=ServiceUtils.returnURL(this.userSns.getSnsHeadIcon());
		return headicon;
	}
	
	@XmlElement
	public String getLocation() {
		location=this.userSns.getLocation();
		return location;
	}
	
	@XmlElement
	public String getGender() {
		gender=ServiceUtils.getUserGenderByGenderFlag(this.userSns.getGender());
		return gender;
	}
	
	@XmlElement
	public String getDescription() {
		description=this.userSns.getDescription();
		return description;
	}
	
	@XmlElement
	public Integer getItemcount() {
		itemcount=this.userSns.getItemcount();
		return itemcount;
	}
	
	@XmlElement
	public Integer getFriendcount() {
		friendcount=this.userSns.getFriendcount();
		return friendcount;
	}
	
	@XmlElement
	public Integer getFollowercount() {
		followercount=this.userSns.getFollowercount();
		return followercount;
	}
	
	@XmlElement
	public Integer getFavoritecount() {
		favoritecount=this.userSns.getFavoritecount();
		return favoritecount;
	}

}
