package com.kascend.music2.api3.service.social.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.common.util.Validator;
import com.kascend.music2.api3.entity.User;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.Constants;
import com.kascend.music2.api3.service.util.ServiceUtils;

@XmlType(propOrder = { "uid","username", "nickname", "headicon", 
		 "followcount", "followartistcount","fanscount","sharecount","likecount", "popularity", 
		"activity", 
		"sex",
		"score","signature","updatetime","lastplaytime","similarityreson","lastsong","songscount","albumscount","artistscount","recentalbums"})

public class UserEntry extends MusicBaseEntry {
	
	private Long uid;
	private String username;
	private String nickname;
	private String headicon;
	private Integer followcount;
	private Integer followartistcount;
	
	private Integer fanscount;
	private Integer sharecount;
	private Integer likecount;
	
	private Integer popularity;
	private Integer activity;
	private Long updatetime;
	private String sex;
	private Integer score;
	private String signature;
	
	private String similarityreson;
	
	private LastSongEntry lastsong;
	
	private Long lastplaytime;
	
	private Integer songscount;
	private Integer albumscount;
	private Integer artistscount;
	private Integer recentalbums;
	
	@XmlElement
	public String getSimilarityreson() {
		return similarityreson;
	}
	@XmlElement
	public Integer getSongscount() {
		if(user.getSongCount()>0){
			songscount=user.getSongCount();
		}
		return songscount;
	}
	@XmlElement
	public Integer getAlbumscount() {
		if(user.getAlbumCount()>0){
			albumscount=user.getAlbumCount();
		}
		return albumscount;
	}
	@XmlElement
	public Integer getArtistscount() {
		if(user.getArtistCount()>0){
			artistscount=user.getArtistCount();
		}
		return artistscount;
	}
	@XmlElement
	public Integer getRecentalbums() {
		if(user.getRecentAlbumCount()>0){
			recentalbums=user.getRecentAlbumCount();
		}
		return recentalbums;
	}
	private User user;
	
	@XmlElement
	public Long getLastplaytime() {
		lastplaytime=user.getLastTime();
		return lastplaytime;
	}
	@XmlElement
	public Integer getFanscount() {
		fanscount=user.getFansCount();
		return fanscount;
	}
	@XmlElement
	public Integer getSharecount() {
		sharecount=null;
		//sharecount=user.getShareCount();
		return sharecount;
	}
	@XmlElement
	public Integer getLikecount() {
		likecount=null;
		//likecount=user.getLikeCount();
		return likecount;
	}
	public UserEntry(){
		
	}
	public UserEntry(User user){
		this.user=user;
	}
	
	@XmlElement
	public LastSongEntry getLastsong() {
		return lastsong;
	}
	public void setLastsong(LastSongEntry lastsong) {
		this.lastsong = lastsong;
	}
	@XmlElement
	public Long getUid() {
		uid=this.user.getUid();
		return uid;
	}
	@XmlElement
	public String getUsername() {
		username=ServiceUtils.hideUserNickName(user.getName());
		return username;
	}
	@XmlElement
	public String getNickname() {
		nickname=ServiceUtils.hideUserNickName(user.getNickname());
		return nickname;
	}
	@XmlElement
	public String getHeadicon() {
		headicon=ServiceUtils.returnURL(user.getHeadIcon());
		return headicon;
	}

	@XmlElement
	public Integer getFollowcount() {
		followcount=user.getFollowCount();
		return followcount;
	}
	@XmlElement
	public Integer getFollowartistcount() {
		followartistcount=null;
		//followartistcount=user.getFollowArtistCount();
		return followartistcount;
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
	public Long getUpdatetime() {
		if(user.getUpdateTime()!=null){
			updatetime=user.getUpdateTime().getTime()/1000;
		}
		return updatetime;
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
	public Integer getScore() {
		if(user.getScore()>0){
			score=user.getScore();
		}
		return score;
	}
	@XmlElement
	public String getSignature() {
		signature=user.getSignature();
		return signature;
	}
	
}
