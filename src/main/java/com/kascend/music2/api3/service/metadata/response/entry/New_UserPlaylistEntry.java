package com.kascend.music2.api3.service.metadata.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.entity.UserPlaylist;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.ServiceUtils;
@XmlType(propOrder = { "title", "playlistid","type", "itemscount","bio","playlistart", "updatetime"})
public class New_UserPlaylistEntry extends MusicBaseEntry{

	
	private UserPlaylist userPlayList;
	
	public New_UserPlaylistEntry(){
		
	}
	
	public New_UserPlaylistEntry(UserPlaylist userPlayList){
		this.userPlayList=userPlayList;
	}
	
	private String title;
	private Long playlistid;
	private Integer type;
	private Integer itemscount;
	private String bio;
	private String playlistart;
	private Integer updatetime;
	
	@XmlElement
	public String getTitle() {
		title=this.userPlayList.getTitle();
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@XmlElement
	public Long getPlaylistid() {
		playlistid=this.userPlayList.getPlaylistId();
		return playlistid;
	}

	public void setPlaylistid(Long playlistid) {
		this.playlistid = playlistid;
	}
	@XmlElement
	public Integer getType() {
		type=this.userPlayList.getPlaylistType();
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	@XmlElement
	public Integer getItemscount() {
		itemscount=this.userPlayList.getItemCount();
		return itemscount;
	}

	public void setItemscount(Integer itemscount) {
		this.itemscount = itemscount;
	}
	@XmlElement
	public String getBio() {
		bio=this.userPlayList.getDescription();
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	@XmlElement
	public String getPlaylistart() {
		playlistart=ServiceUtils.returnURL(this.userPlayList.getThumb());
		return playlistart;
	}

	public void setPlaylistart(String playlistart) {
		this.playlistart = playlistart;
	}
	@XmlElement
	public Integer getUpdatetime() {
		updatetime=this.userPlayList.getUpdateTime();
		return updatetime;
	}

	public void setUpdatetime(Integer updatetime) {
		this.updatetime = updatetime;
	}

}
