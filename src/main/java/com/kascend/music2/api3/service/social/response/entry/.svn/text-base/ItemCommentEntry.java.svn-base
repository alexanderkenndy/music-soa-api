package com.kascend.music2.api3.service.social.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.kascend.music2.api3.service.metadata.response.entry.New_AlbumEntry;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
@XmlType(propOrder = { "commentid", "commentcontent", "album"})
public class ItemCommentEntry extends MusicBaseEntry {
	
	private Long commentid;
	private String commentcontent;
	private New_AlbumEntry album;
	@XmlElement
	public Long getCommentid() {
		return commentid;
	}
	public void setCommentid(Long commentid) {
		this.commentid = commentid;
	}
	@XmlElement
	public String getCommentcontent() {
		return commentcontent;
	}
	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}
	@XmlElement
	public New_AlbumEntry getAlbum() {
		return album;
	}
	public void setAlbum(New_AlbumEntry album) {
		this.album = album;
	}
	
}
