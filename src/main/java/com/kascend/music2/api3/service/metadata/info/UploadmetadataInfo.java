package com.kascend.music2.api3.service.metadata.info;

import java.io.File;

import com.kascend.music2.api3.service.info.MusicBaseInfo;

public class UploadmetadataInfo extends MusicBaseInfo {
	private File[] file;
	
	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}
}
