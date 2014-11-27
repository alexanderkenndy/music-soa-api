package com.kascend.music2.api3.service.social.response.entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.arcsoft.e2e.osm.model.InfoWeibo;
import com.arcsoft.e2e.osm.userserver.sdk.E2eUser;
import com.kascend.common.util.Validator;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.util.ServiceUtils;
@XmlType(propOrder = { "snsuserid", "snsusername", "snsuserheadicon", "weibosecret", "weibosource",
		 "weibostate", "weibotoken"})
public class SnsInfoEntry extends MusicBaseEntry {
	
	
	InfoWeibo infoWeibo;
	
	private String snsuserid;
	private String snsusername;
	private String snsuserheadicon;
	private String weibosecret;
	private Integer weibosource;
	private String weibostate;
	private String weibotoken;
	
	public SnsInfoEntry(){
		
	}
	
	public SnsInfoEntry(InfoWeibo infoWeibo){
		this.infoWeibo=infoWeibo;
	}
	@XmlElement
	public String getSnsuserid() {
		snsuserid=this.infoWeibo.getSnsUserID();
		return snsuserid;
	}
	public void setSnsuserid(String snsuserid) {
		this.snsuserid = snsuserid;
	}
	@XmlElement
	public String getSnsusername() {
		snsusername=this.infoWeibo.getSnsUserName();
		return snsusername;
	}
	public void setSnsusername(String snsusername) {
		this.snsusername = snsusername;
	}
	
	@XmlElement
	public String getSnsuserheadicon() {
		snsuserheadicon=ServiceUtils.returnURL(this.infoWeibo.getSnsUserHeadicon());
		return snsuserheadicon;
	}
	public void setSnsuserheadicon(String snsuserheadicon) {
		this.snsuserheadicon = snsuserheadicon;
	}
	
	@XmlElement
	public String getWeibosecret() {
		weibosecret=this.infoWeibo.getWeiboSecret();
		return weibosecret;
	}
	public void setWeibosecret(String weibosecret) {
		this.weibosecret = weibosecret;
	}
	
	@XmlElement
	public Integer getWeibosource() {
		weibosource=this.infoWeibo.getWeiboSource();
		return weibosource;
	}
	public void setWeibosource(Integer weibosource) {
		this.weibosource = weibosource;
	}
	
	@XmlElement
	public String getWeibostate() {
		weibostate=String.valueOf(this.infoWeibo.getWeiboState());
		return weibostate;
	}
	public void setWeibostate(String weibostate) {
		this.weibostate = weibostate;
	}
	
	@XmlElement
	public String getWeibotoken() {
		weibotoken=this.infoWeibo.getWeiboToken();
		return weibotoken;
	}
	public void setWeibotoken(String weibotoken) {
		this.weibotoken = weibotoken;
	}

}
