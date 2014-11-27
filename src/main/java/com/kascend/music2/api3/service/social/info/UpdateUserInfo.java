package com.kascend.music2.api3.service.social.info;

import com.kascend.music2.api3.service.info.MusicBaseInfo;

public class UpdateUserInfo extends MusicBaseInfo {
	
	private String nickname;
	private String bio;
	private String signature;
	private int sex;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	

}
