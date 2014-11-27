package com.kascend.music2.api3.service.social.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.common.util.Validator;
import com.kascend.music2.api3.entity.UserShare;
import com.kascend.music2.api3.service.metadata.response.entry.New_AlbumEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_ArtistEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_MvEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_SongEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_UserPlaylistEntry;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.ServiceUtils;


@XmlType(propOrder = { "uid", "artistid", "usertype", "nickname", "headicon", 
		"itemid","snssite", "itemtype", "itemcontent", "forwardtimes", "commenttimes", 
		"liketimes",  "updatetime",
		"itemdetail"
		})

public class ItemEntry extends MusicBaseEntry {
	
	private Long uid;
	private String nickname;
	private String headicon;
	private Long itemid;
	private Integer itemtype;
	private String itemcontent;
	private Integer forwardtimes;
	private Integer commenttimes;
	private Integer liketimes;
	private Long updatetime;
	
	private Integer snssite;
	private ItemDetailEntry itemdetail;
	
	private Long artistid;
	private Integer usertype;
	
	UserShare userShare;
	MusicBaseEntry item;
	
	public ItemEntry(){
		
	}
	
	public ItemEntry(UserShare userShare){
		this(userShare, null);
	}
	public ItemEntry(UserShare userShare, MusicBaseEntry item){
		this.userShare=userShare;
		this.item=item;
	}
	
	@XmlElement
	public Integer getSnssite() {
		if(this.userShare.getSnsSite()>0){
			snssite=this.userShare.getSnsSite();
		}
		return snssite;
	}

	@XmlElement
	public Long getUid() {
		uid=userShare.getUid();
		return uid;
	}
	@XmlElement
	public String getNickname() {
		if(userShare.getUser()!=null){
			nickname=ServiceUtils.hideUserNickName(userShare.getUser().getNickname());
		}
		
		return nickname;
	}
	@XmlElement
	public String getHeadicon() {
		headicon=ServiceUtils.returnURL(userShare.getUser().getHeadIcon());
		return headicon;
	}
	@XmlElement
	public Integer getItemtype() {
		itemtype=userShare.getItemType();
		return itemtype;
	}
	@XmlElement
	public String getItemcontent() {
		itemcontent=userShare.getContent();
		return itemcontent;
	}
	@XmlElement
	public Integer getForwardtimes() {
		if(userShare.getForwardTimes()>0){
			forwardtimes=userShare.getForwardTimes();
		}
		return forwardtimes;
	}
	@XmlElement
	public Integer getCommenttimes() {
		if(userShare.getCommentTimes()>0){
			commenttimes=userShare.getCommentTimes();
		}
		return commenttimes;
	}
	@XmlElement
	public Integer getLiketimes() {
		if(userShare.getLikeTimes()>0){
			liketimes=userShare.getLikeTimes();
		}
		return liketimes;
	}
	@XmlElement
	public Long getUpdatetime() {
		updatetime=(long)userShare.getUpdateTime();
		if(updatetime==0){
			updatetime=(long)userShare.getCreateTime();
		}
		return updatetime;
	}
	@XmlElement
	public ItemDetailEntry getItemdetail() {
		itemdetail=new ItemDetailEntry();
		if(item instanceof New_SongEntry){
			itemdetail.setSong((New_SongEntry)item);
		}else if(item instanceof New_ArtistEntry){
			itemdetail.setArtist((New_ArtistEntry)item);
		}else if(item instanceof New_AlbumEntry){
			itemdetail.setAlbum((New_AlbumEntry)item);
		}else if(item instanceof New_MvEntry){
			itemdetail.setMv((New_MvEntry)item);
		}else if(item instanceof SnsItemEntry){
			itemdetail.setSnsitem((SnsItemEntry)item);
		}else if(item instanceof UserPlaylistEntry){
			itemdetail.setPlaylist((UserPlaylistEntry)item);
		}else if(item instanceof ItemCommentEntry){
			itemdetail.setComment((ItemCommentEntry)item);
		}
		return itemdetail;
	}
	@XmlElement
	public Long getArtistid() {
		if(userShare.getUser()!=null && userShare.getUser().getArtistId()>0){
			artistid=userShare.getUser().getArtistId();
		}
		return artistid;
	}
	@XmlElement
	public Integer getUsertype() {
		if(userShare.getUser()!=null){
			usertype=userShare.getUser().getUserType();
		}
		return usertype;
	}
	@XmlElement
	public Long getItemid() {
		itemid=userShare.getShareId();
		return itemid;
	}
	
}
