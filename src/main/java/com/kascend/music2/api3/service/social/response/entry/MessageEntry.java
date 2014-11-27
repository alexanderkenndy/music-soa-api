package com.kascend.music2.api3.service.social.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.entity.UserMessage;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.ServiceUtils;
@XmlType(propOrder = { "uid", "nickname", "headicon", "messagetid",
		"messagecontent", "updatetime","replymessagelist"
})
public class MessageEntry extends MusicBaseEntry {
	
	private UserMessage message;
	private Long uid;	
	private String nickname;	
	private String headicon;	
	private Long messagetid;	
	private String messagecontent;	
	private Long updatetime;
	private MessageListEntry replymessagelist;
	
	public MessageEntry(){
		
	}
	
	public MessageEntry(UserMessage message){
		this.message=message;
	}
	
	@XmlElement
	public Long getUid() {
		uid=this.message.getFromUid();
		return uid;
	}
	@XmlElement
	public String getNickname() {
		nickname=this.message.getNicNname();
		return nickname;
	}
	@XmlElement
	public String getHeadicon() {
		headicon=ServiceUtils.returnURL(this.message.getHeadIcon());
		return headicon;
	}
	@XmlElement
	public Long getMessagetid() {
		messagetid=this.message.getMessagetId();
		return messagetid;
	}
	@XmlElement
	public String getMessagecontent() {
		messagecontent=this.message.getMessageContent();
		return messagecontent;
	}
	@XmlElement
	public Long getUpdatetime() {
		updatetime=this.message.getCreateTime();
		return updatetime;
	}
	@XmlElement
	public MessageListEntry getReplymessagelist() {
		return replymessagelist;
	}

	public void setReplymessagelist(MessageListEntry replymessagelist) {
		this.replymessagelist = replymessagelist;
	}
	
}
