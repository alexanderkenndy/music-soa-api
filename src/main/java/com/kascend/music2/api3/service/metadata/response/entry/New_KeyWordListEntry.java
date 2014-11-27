package com.kascend.music2.api3.service.metadata.response.entry;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;

@XmlType(propOrder = { "keywordscount", "keyword" })
public class New_KeyWordListEntry extends MusicBaseEntry {
	private Integer keywordscount;
	private List<String> keyword;

	public Integer getKeywordscount() {
		return keywordscount;
	}

	public void setKeywordscount(Integer keywordscount) {
		this.keywordscount = keywordscount;
	}

	public List<String> getKeyword() {
		return keyword;
	}

	public void setKeyword(List<String> keyword) {
		this.keyword = keyword;
	}

}
