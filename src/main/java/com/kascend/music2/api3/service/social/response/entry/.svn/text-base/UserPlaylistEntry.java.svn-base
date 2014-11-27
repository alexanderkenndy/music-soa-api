package com.kascend.music2.api3.service.social.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.entity.UserPlaylist;
import com.kascend.music2.api3.service.metadata.response.entry.New_UserSongListEntry;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.ServiceUtils;

@XmlType(propOrder = { "title", "playlistid", "playlistprop", "type", "itemscount", "bio","playlistart","updatetime","songslist"
})
public class UserPlaylistEntry extends MusicBaseEntry{
	
	private UserPlaylist userPlaylist;
	
	private String title;
	private Long playlistid;
	private Integer playlistprop;
	private Integer type;
	private Integer itemscount;
	private String bio;
	private String playlistart;
	private Long updatetime;
	
	private New_UserSongListEntry songslist;
	
	@XmlElement
	public New_UserSongListEntry getSongslist() {
		return songslist;
	}

	public void setSongslist(New_UserSongListEntry songslist) {
		this.songslist = songslist;
	}

	public UserPlaylistEntry(){
		
	}
	
	public UserPlaylistEntry(UserPlaylist userPlaylist){
		this.userPlaylist=userPlaylist;
	}
	@XmlElement
	public String getTitle() {
		title=this.userPlaylist.getTitle();
		return title;
	}
	@XmlElement
	public Long getPlaylistid() {
		playlistid=this.userPlaylist.getPlaylistId();
		return playlistid;
	}
	@XmlElement
	public Integer getPlaylistprop() {
		playlistprop=this.userPlaylist.getDataFrom();
		return playlistprop;
	}
	@XmlElement
	public Integer getType() {
		type=this.userPlaylist.getPlaylistType();
		return type;
	}
	@XmlElement
	public Integer getItemscount() {
		itemscount=this.userPlaylist.getItemCount();
		return itemscount;
	}
	@XmlElement
	public String getBio() {
		bio=this.userPlaylist.getDescription();
		return bio;
	}
	@XmlElement
	public String getPlaylistart() {
		playlistart=ServiceUtils.returnURL(this.userPlaylist.getThumb());
		return playlistart;
	}
	@XmlElement
	public Long getUpdatetime() {
		updatetime=new Long(this.userPlaylist.getUpdateTime());
		return updatetime;
	}

	
}
