package com.kascend.music2.api3.service.metadata.info;

import com.kascend.music2.api3.service.info.MusicBaseInfo;

public class CacheFlushInfo extends MusicBaseInfo {
	
	private int flushcode;

	public int getFlushcode() {
		return flushcode;
	}

	public void setFlushcode(int flushcode) {
		this.flushcode = flushcode;
	}

}
