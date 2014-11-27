package com.kascend.music2.api3.service.metadata.response.entry;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import com.kascend.music2.api3.entity.SongFile;
import com.kascend.music2.api3.entity.SongMv;
import com.kascend.music2.api3.entity.SongUri;
import com.kascend.music2.api3.service.util.Constants;


public class New_SongUriEntry implements java.io.Serializable{
	
	
	private SongUri songUri;
	private String uri;
	private Integer type;
	private Long songid;
	
	private SongFile songFile;
	
	public New_SongUriEntry(){
		
	}
	public New_SongUriEntry(SongUri songUri){
		this.songUri=songUri;
	}
	
	public New_SongUriEntry(SongFile songFile){
		this.songFile=songFile;
	}

	@XmlAttribute
	public Long getSongid() {
		if(songUri!=null)
			songid=this.songUri.getSongId();
		else if(this.songFile!=null)
			songid=this.songFile.getSongId();
		
		return songid;
	}

	public void setSongid(Long songid) {
		this.songid = songid;
	}

	@XmlValue
	public String getUri() {
		if(songUri!=null)
			uri=this.songUri.getSongUri();
		else if(this.songFile!=null)
			uri=this.songFile.getUri();
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

	@XmlAttribute
	public Integer getType() {
		if(songUri!=null){
			if(this.songUri.getDataFrom()==Constants.DATA_FROM_SINA_WEIBO
					|| this.songUri.getDataFrom()==Constants.DATA_FROM_SINA_YUEKU){
				type=Constants.SONG_URI_TYPE_SINA;
			}else if(this.songUri.getDataFrom()==Constants.DATA_FROM_GOOGLE){
				type=Constants.SONG_URI_TYPE_GOOGLE;
			}else if(this.songUri.getDataFrom()==Constants.DATA_FROM_XIAMI){
				type=Constants.SONG_URI_TYPE_XIAMI;
			}else if(this.songUri.getDataFrom()==Constants.DATA_FROM_BAIDU){
				type=Constants.SONG_URI_TYPE_BAIDU;
			}
		}else if(songFile!=null){
			type=Constants.SONG_URI_TYPE_KASCEND;
		}
		
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}

}
