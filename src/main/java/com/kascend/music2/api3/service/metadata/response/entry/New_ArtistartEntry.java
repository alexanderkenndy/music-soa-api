package com.kascend.music2.api3.service.metadata.response.entry;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.ServiceUtils;

@XmlType(propOrder = { "artistartnum", "artistart" })
public class New_ArtistartEntry extends MusicBaseEntry{

	private Integer artistartnum;
	private List<String> artistart;

	@XmlElement
	public Integer getArtistartnum() {
		return artistartnum;
	}

	public void setArtistartnum(Integer artistartnum) {
		this.artistartnum = artistartnum;
	}

	@XmlElement
	public List<String> getArtistart() {
		return artistart;
	}

	public void setArtistart(List<String> artistart) {
		this.artistart = artistart;
	}

}
