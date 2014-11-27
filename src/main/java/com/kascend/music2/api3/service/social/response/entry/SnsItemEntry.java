package com.kascend.music2.api3.service.social.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.entity.UserSnsItem;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.Constants;
import com.kascend.music2.api3.service.util.ServiceUtils;


@XmlType(propOrder = { "snscontent", "snsitemid", "snssite", "picthumb", "pic", 
		 "updatetime"
		})

public class SnsItemEntry extends MusicBaseEntry {
	
	private String snscontent;
	private Long snsitemid;
	private String snssite;
	private String picthumb;
	private String pic;
	private Long updatetime;
	
	private UserSnsItem userSnsItem;
	public SnsItemEntry(){}
	
	public SnsItemEntry(UserSnsItem userSnsItem){
		this.userSnsItem=userSnsItem;
	}
	
	@XmlElement
	public String getSnscontent() {
		snscontent=userSnsItem.getContent();
		return snscontent;
	}
	@XmlElement
	public Long getSnsitemid() {
		snsitemid=userSnsItem.getItemId();
		return snsitemid;
	}
	@XmlElement
	public String getSnssite() {
		if(userSnsItem.getSnsSite()==Constants.SNS_SITE_SINAWEIBO){
			snssite="新浪微博";
		}
		return snssite;
	}
	@XmlElement
	public String getPicthumb() {
		picthumb=ServiceUtils.returnURL(userSnsItem.getPicThumb());
		return picthumb;
	}
	@XmlElement
	public String getPic() {
		pic=ServiceUtils.returnURL(userSnsItem.getPic());
		return pic;
	}
	@XmlElement
	public Long getUpdatetime() {
		updatetime=(long)userSnsItem.getUpdateTime()*1000;
		return updatetime;
	}
}
