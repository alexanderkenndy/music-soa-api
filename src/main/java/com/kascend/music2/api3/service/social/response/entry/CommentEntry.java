package com.kascend.music2.api3.service.social.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.common.util.Validator;
import com.kascend.music2.api3.entity.UserComment;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.ServiceUtils;
@XmlType(propOrder = { "uid", "nickname", "usertype", "artistid", "headicon","commentid", 
		"itemid", "itemtype","subtitle", "commentcontent", "updatetime","replycommentslist"
		})
public class CommentEntry extends MusicBaseEntry {
	private Long uid;
	private Long artistid;
	private Integer usertype;
	private String nickname;
	private String headicon;
	private Long commentid;
	private Long itemid;
	private Integer itemtype;
	
	private String subtitle;
	
	private String commentcontent;
	private Long updatetime;
	
	private UserComment userComment;
	
	private CommentListEntry replycommentslist;
	
	public CommentEntry(){
		
	}
	
	public CommentEntry(UserComment userComment){
		this.userComment=userComment;
	}
	
	
	@XmlElement
	public CommentListEntry getReplycommentslist() {
		return replycommentslist;
	}

	public void setReplycommentslist(CommentListEntry replycommentslist) {
		this.replycommentslist = replycommentslist;
	}

	@XmlElement
	public String getSubtitle() {
		subtitle=this.userComment.getSubTitle();
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	@XmlElement
	public Long getUid() {
		uid=userComment.getUid();
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	@XmlElement
	public Long getArtistid() {
		if(userComment.getUser()!=null && userComment.getUser().getArtistId()>0){
			artistid=userComment.getUser().getArtistId();
		}
		return artistid;
	}
	public void setArtistid(Long artistid) {
		this.artistid = artistid;
	}
	@XmlElement
	public Integer getUsertype() {
		usertype=userComment.getUser().getUserType();
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	@XmlElement
	public String getNickname() {
		nickname=ServiceUtils.hideUserNickName(userComment.getUser().getNickname());
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@XmlElement
	public String getHeadicon() {
		headicon=ServiceUtils.returnURL(userComment.getUser().getHeadIcon());
		return headicon;
	}
	public void setHeadicon(String headicon) {
		this.headicon = headicon;
	}
	@XmlElement
	public Long getCommentid() {
		commentid=userComment.getCommentId();
		return commentid;
	}
	public void setCommentid(Long commentid) {
		this.commentid = commentid;
	}
	@XmlElement
	public Long getItemid() {
		itemid=userComment.getItemId();
		return itemid;
	}
	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}
	@XmlElement
	public Integer getItemtype() {
		itemtype=userComment.getItemType();
		return itemtype;
	}
	public void setItemtype(Integer itemtype) {
		this.itemtype = itemtype;
	}
	@XmlElement
	public String getCommentcontent() {
		commentcontent=userComment.getContent();
		return commentcontent;
	}
	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}
	@XmlElement
	public Long getUpdatetime() {
		updatetime=userComment.getUpdateTime();
		return updatetime;
	}
	public void setUpdatetime(Long updatetime) {
		this.updatetime = updatetime;
	}
	
	
	
}
