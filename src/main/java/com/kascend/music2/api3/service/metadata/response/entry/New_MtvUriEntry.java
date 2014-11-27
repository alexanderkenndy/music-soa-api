package com.kascend.music2.api3.service.metadata.response.entry;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import com.kascend.music2.api3.entity.SongMv;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;


public class New_MtvUriEntry implements java.io.Serializable{

	private String uri;
	private String value;
	private Integer type;
	private Long songid;
	private Integer mtvid;
	
	public New_MtvUriEntry(){
		
	}
	@XmlAttribute
	public Integer getMtvid() {
		return mtvid;
	}
	public void setMtvid(Integer mtvid) {
		this.mtvid = mtvid;
	}

	@XmlAttribute
	public Long getSongid() {
		return songid;
	}

	public void setSongid(Long songid) {
		this.songid = songid;
	}

	@XmlValue
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

	@XmlAttribute
	public Integer getType() {
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
