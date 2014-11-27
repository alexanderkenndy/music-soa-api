package com.kascend.music2.api3.service.metadata.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.entity.User;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.Constants;
import com.kascend.music2.api3.service.util.ServiceUtils;

@XmlType(propOrder = { "uid","nickname", 
		"headicon","followcount","fanscount","popularity","activity","sex"})

public class New_UserEntry  extends MusicBaseEntry{
	
	private User user;
	
	public New_UserEntry(){
		
	}
	
	public New_UserEntry(User user){
		this.user=user;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long uid;
	
	private String nickname;
	
	private String headicon;
	
	private Integer followcount;
	
	private Integer fanscount;
	
	private Integer popularity;
	private Integer activity;
	private String sex;

	
	
	@XmlElement
	public Integer getFollowcount() {
		followcount=user.getFollowCount();
		return followcount;
	}
	@XmlElement
	public Integer getFanscount() {
		fanscount=user.getFansCount();
		return fanscount;
	}

	@XmlElement
	public Integer getPopularity() {
		popularity=user.getPopularity();
		return popularity;
	}
	@XmlElement
	public Integer getActivity() {
		activity=user.getActivity();
		return activity;
	}

	@XmlElement
	public String getSex() {
		if(user.getSex()==Constants.USER_SEX_UNKNOW){
			sex="保密";
		}else if(user.getSex()==Constants.USER_SEX_MALE){
			sex="男";
		}else if(user.getSex()==Constants.USER_SEX_FEMALE){
			sex="女";
		}
		return sex;
	}


	@XmlElement
	public Long getUid() {
		uid=this.user.getUid();
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	@XmlElement
	public String getNickname() {
		nickname=ServiceUtils.hideUserNickName(this.user.getNickname());
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@XmlElement
	public String getHeadicon() {
		headicon=ServiceUtils.returnURL(this.user.getHeadIcon());
		return headicon;
	}

	public void setHeadicon(String headicon) {
		this.headicon = headicon;
	}

}
