package com.kascend.music2.api3.service.metadata.response.entry;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import com.kascend.music2.api3.entity.SongMv;



public class New_MvUriEntry implements java.io.Serializable{
	
	
	private SongMv songMv;
	private String uri;
	private String value;
	private Integer type;
	private Long songid;
	private Integer mvid;
	
	public New_MvUriEntry(){
		
	}
	public New_MvUriEntry(SongMv songMv){
		this.songMv=songMv;
	}
	
	@XmlAttribute
	public Integer getMvid() {
		mvid=this.songMv.getMvId();
		return mvid;
	}
	public void setMvid(Integer mvid) {
		this.mvid = mvid;
	}

	@XmlAttribute
	public Long getSongid() {
		songid=this.songMv.getSongId();
		return songid;
	}

	public void setSongid(Long songid) {
		this.songid = songid;
	}

	@XmlValue
	public String getUri() {
		uri=this.songMv.getMvUri();
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

	@XmlAttribute
	public Integer getType() {
		type=this.songMv.getUriType();
		return type;
	}
	@XmlAttribute
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public void setType(Integer type) {
		this.type = type;
	}

}
