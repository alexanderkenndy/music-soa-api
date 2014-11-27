package com.kascend.music2.api3.service.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.kascend.common.util.Validator;
import com.kascend.frameworkcommons.config.Configer;
import com.kascend.music2.api3.entity.UserMetadata;
import com.kascend.music2.api3.entity.UserPhoto;
import com.kascend.music2.api3.entity.UserSong;
import com.kascend.music2.api3.exception.MusicRcException;
import com.kascend.music2.api3.service.metadata.info.LookupMvInfo;

public class AnalysisZipFileUtil  implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Logger log = Logger.getLogger(ServiceUtils.class);
	@SuppressWarnings("rawtypes")
	public static UserMetadata analysisSyncPlaylist(long uid,String page){
		UserMetadata userMetadata=new UserMetadata();
		try {
		userMetadata.setUid(uid);
		userMetadata.setPlaylistType(Constants.USER_PLAYLIST_TYPE_SONG);
		Document doc = DocumentHelper.parseText(page);
		if (doc != null) {
			Element rootEle = doc.getRootElement();
			if (rootEle != null) {
				Element playlistpropEle = rootEle.element("playlistprop");
				int dataFrom=0;
				if(playlistpropEle!=null){
					String playlistpropStr=playlistpropEle.getTextTrim();
					try {
						dataFrom=Integer.parseInt(playlistpropStr);
					} catch (Exception e) {
						if (log.isDebugEnabled())
							log.debug(e.getMessage(), e);
						else
							log.error(e.getMessage());
					}
				}
				userMetadata.setDataFrom(dataFrom);
				Element playlistidEle = rootEle.element("playlistid");
				if(playlistidEle!=null){
					String playlistIdStr=playlistidEle.getTextTrim();
					if (!Validator.isBlank(playlistIdStr)) {
						try {
							userMetadata.setPlaylistid(Long.parseLong(playlistIdStr));
						} catch (Exception e) {
							if (log.isDebugEnabled())
								log.debug(e.getMessage(), e);
							else
								log.error(e.getMessage());
						}
					}
				}
				Element descriptionEle = rootEle.element("description");
				if(descriptionEle!=null){
					String description=null;
					description=descriptionEle.getTextTrim();
					userMetadata.setDescription(description);
				}
				
				Element playlisttitleEle = rootEle.element("playlisttitle");
				if(playlisttitleEle!=null){
					String playlisttitle=playlisttitleEle.getTextTrim();
					userMetadata.setPlaylistTitle(playlisttitle);
				}
				if(Validator.isBlank(userMetadata.getPlaylistTitle())){
					throw new MusicRcException(MusicRcException.NOT_EXISTED_PLAYLISTTITLE);
				}
				Element userLocalEle = rootEle.element("songslist");
				List<UserSong> listDateMap = new ArrayList<UserSong>();
				userMetadata.setClientUserSongList(listDateMap);
				if (userLocalEle != null) {
					List userListEle = userLocalEle.elements("song");
					Iterator iter = userListEle.iterator();
					int sortIndex=1;
					while (iter.hasNext()) {
						Element songEle = (Element) iter.next();
						if (songEle != null) {
							UserSong clientUserSong=new UserSong();
							clientUserSong.setSortIndex(sortIndex);
							clientUserSong.setDataFrom(Constants.USER_SONG_DATA_FROM_LOCAL);
							clientUserSong.setUid(uid);
							// songid
							Element songIdEle = songEle.element("songid");
							String songIdStr = songIdEle.getTextTrim();
							
							if (!Validator.isBlank(songIdStr)) {
								try {
									clientUserSong.setSongId(Long.parseLong(songIdStr));
								} catch (Exception e) {
									if (log.isDebugEnabled())
										log.debug(e.getMessage(), e);
									else
										log.error(e.getMessage());
								}
							}
							//songpath
							Element songPathEle = songEle.element("songpath");
							if(songPathEle!=null){
								String songPath=songPathEle.getTextTrim();
								clientUserSong.setSongPath(songPath);
							}
							//filename
							Element filenameEle = songEle.element("filename");
							if(filenameEle!=null){
								String fileName = filenameEle.getTextTrim();
								clientUserSong.setFileName(fileName);
							}
							//album
							Element albumEle = songEle.element("album");
							if(albumEle!=null){
								String album = albumEle.getTextTrim();
								clientUserSong.setAlbumTitle(album);
							}
							//artist
							Element artistEle = songEle.element("artist");
							if(artistEle!=null){
								String artist = artistEle.getTextTrim();
								clientUserSong.setArtistName(artist);
							}
							//songtitle
							Element titleEle = songEle.element("title");
							if(titleEle!=null){
								String title = titleEle.getTextTrim();
								clientUserSong.setSongTitle(title);
							}
							//duration
							Element durationEle = songEle.element("duration");
							if(durationEle!=null){
								try {
									String durationStr = durationEle.getTextTrim();
									clientUserSong.setDuration(Integer.parseInt(durationStr));
								} catch (Exception e) {
									if (log.isDebugEnabled())
										log.debug(e.getMessage(), e);
									else
										log.error(e.getMessage());
								}
							}
							listDateMap.add(clientUserSong);
							sortIndex++;
						}
					}
				}
			}
		}
		
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug(e.getMessage(), e);
			else
				log.error(e.getMessage());
		}
		return userMetadata;
	}
	
	/**
	 * lookupgroupmtvs
	 * 解析xml字符串
	 * @param xmlStr
	 * @return
	 */
	public static List<LookupMvInfo> getSearchInformationFromXMLStr(String xmlStr) {
		List<LookupMvInfo> musicInfoList = new ArrayList<LookupMvInfo>();
		xmlStr=xmlStr.trim();
		if (!Validator.isBlank(xmlStr)) {
			Document doc = null;
			try {
				doc = DocumentHelper.parseText(xmlStr);
				if (doc != null) {
					Element rootElement = doc.getRootElement();
					if (rootElement != null) {
						List<Element> eleList = rootElement
								.elements("metadata");
						if (eleList != null && eleList.size() > 0) {
							Iterator iter = eleList.iterator();
							while (iter.hasNext()) {
								Element eleMent = (Element) iter.next();
								if (eleMent != null) {
									LookupMvInfo info = new LookupMvInfo();
									Element songidEle = eleMent
											.element("songid");
									if (songidEle != null) {
										String songIdStr = songidEle
												.getTextTrim();
										if (!Validator.isBlank(songIdStr)) {
											try {
												Long songId = Long
														.parseLong(songIdStr);
												if (ServiceUtils
														.validateLongNotEquals(songId)) {
													info.setSongid(songId);
												}
											} catch (Exception e) {
												log.error(e.getMessage(), e);
											}

										}
									}
									Element songEle = eleMent.element("song");
									if (songEle != null) {
										String songTitle = songEle
												.getTextTrim();
										if (!Validator.isBlank(songTitle)) {
											info.setSong(songTitle);
										}
									}
									Element artistEle = eleMent
											.element("artist");
									if (artistEle != null) {
										String artistName = artistEle
												.getTextTrim();
										if (!Validator.isBlank(artistName)) {
											info.setArtist(artistName);
										}
									}
									Element albumEle = eleMent.element("album");
									if (albumEle != null) {
										String albumTitle = albumEle
												.getTextTrim();
										if (!Validator.isBlank(albumTitle)) {
											info.setAlbum(albumTitle);
										}
									}
									Element fileNameEle = eleMent
											.element("filename");
									if (fileNameEle != null) {
										String fileName = fileNameEle
												.getTextTrim();
										if (!Validator.isBlank(fileName)) {
											info.setFilename(fileName);
										}
									}
									musicInfoList.add(info);
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		return musicInfoList;
	}
	
	/**
	 * 打开压缩文件
	 * @param file
	 * @return
	 * @throws MusicRcException
	 */
	public static File openCompressZipFile(File file) throws MusicRcException{
		File loadFile=null;
		if(file.isFile()){
			String fileName=file.getName();
			String absoluteAllPath=file.getAbsolutePath();
			String parentPath=absoluteAllPath.substring(0, absoluteAllPath.indexOf(fileName));
			ZipFile zipFile=null;
			int length=0;
			byte []b=new byte[8096];
			
			try {
				zipFile = new ZipFile(file);
				Enumeration enumeration = zipFile.entries();
				ZipEntry zipEntry = null;
				while (enumeration.hasMoreElements()) {
					zipEntry = (ZipEntry) enumeration.nextElement();
					loadFile = new File(parentPath+zipEntry.getName());
					if (zipEntry.isDirectory()) {
						loadFile.mkdirs();
					} else {
						OutputStream outputStream=null;
						InputStream inputStream =null;
						try {
							outputStream = new FileOutputStream(loadFile);
							inputStream = zipFile.getInputStream(zipEntry);
							while ((length = inputStream.read(b)) > 0){
								outputStream.write(b,0,length);
							}
						} catch (Exception e) {
							if (log.isDebugEnabled())
								log.debug(e.getMessage(), e);
							else
								log.error(e.getMessage());
							throw new MusicRcException(MusicRcException.FILE_PRESSED_ERROR);
						}finally{
							if(outputStream!=null){
								outputStream.flush();
								outputStream.close();
								outputStream=null;
							}
							if(inputStream!=null){
								inputStream.close();
								inputStream=null;
							}
							
						}
					}
				}
				
				zipEntry=null;
				enumeration=null;
			} catch (IOException e) {
				
				if (log.isDebugEnabled())
					log.debug(e.getMessage(), e);
				else
					log.error(e.getMessage());
				throw new MusicRcException(MusicRcException.FILE_PRESSED_ERROR);
			}finally{
				if(zipFile!=null){
					try {
						zipFile.close();
						zipFile=null;
					} catch (IOException e) {
						if (log.isDebugEnabled())
							log.debug(e.getMessage(), e);
						else
							log.error(e.getMessage());
					}
				}
				delete(absoluteAllPath);
			}
			
		}
		return loadFile;
	}

	public static UserPhoto downloadUserPlaylistImage(String imgUri)
			throws Exception {
		UserPhoto userPhoto = null;
		if (!Validator.isBlank(imgUri)) {
			String albumImgPath = ImageUtil.saveNativeImageByWidthAndHeight(
					Constants.USER_PLAYLIST_COVER, imgUri,
					Configer.getValueInt(Constants.ALBUM_SMALL_IMG_WIDTH),
					Configer.getValueInt(Constants.ALBUM_SMALL_IMG_HEIGHT));
			if (albumImgPath == null) {
				return null;
			}
			userPhoto = new UserPhoto();
			userPhoto.setPhotoUri(albumImgPath);
			userPhoto.setSmallPhoto(ImageUtil.saveSmallImage(albumImgPath,
					Configer.getValueInt(Constants.ALBUM_SMALL_IMG_WIDTH),
					Configer.getValueInt(Constants.ALBUM_SMALL_IMG_HEIGHT)));
			userPhoto.setMiddlePhoto(ImageUtil.saveSmallImage(albumImgPath,
					Configer.getValueInt(Constants.ALBUM_MIDDLE_IMG_WIDTH),
					Configer.getValueInt(Constants.ALBUM_MIDDLE_IMG_HEIGHT)));
			log.debug("Download userplaylist cover is: "
					+ userPhoto.getPhotoUri());
		}
		delete(imgUri);
		return userPhoto;
	}
	public static String fileUploadUtil(File clientFile) {
		StringBuffer sb = new StringBuffer();
		if (clientFile.exists() && clientFile.isFile()) {
			File file = openCompressZipFile(clientFile);
			DataInputStream dis = null;
			try {
				dis = new DataInputStream(new FileInputStream(file));
				if (dis != null) {
					byte buf[] = new byte[1024];
					int size = 0;

					while ((size = dis.read(buf)) != -1) {
						sb.append( new String(buf, 0, size, "UTF-8"));
					}
				}
			} catch (Exception e) {
				if (log.isDebugEnabled())
					log.debug(e.getMessage(), e);
				else
					log.error(e.getMessage());
				throw new MusicRcException(MusicRcException.FILE_PRESSED_ERROR);
			} finally {
				if (dis != null) {
					try {
						dis.close();
					} catch (IOException e) {
						if (log.isDebugEnabled())
							log.debug(e.getMessage(), e);
						else
							log.error(e.getMessage());
					}
					dis = null;
				}
				String filePath = file.getAbsolutePath();
				delete(filePath);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 删除目录
	 * @param dir
	 * @return
	 */
	public static boolean deleteDirectory(String dir) {
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			} else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}
		if (!flag) {
			return false;
		}
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 删除文件和目录
	 * @param fileName
	 * @return
	 */
	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			return false;
		} else {
			if (file.isFile()) {
				return deleteFile(fileName);
			} else {
				return deleteDirectory(fileName);
			}
		}
	}
	
	/**
	 * 删除文件
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean  checkFile(File file,String filter){
		if(file.isDirectory()){
			return false;
		}else{
			String filePath=file.getAbsolutePath();
			long fileSize=file.length();
			long maxFileSize=3145728; //限制图片最大为3M
			if(fileSize > maxFileSize){
				return false;
			}else if(filter!=null && filePath.matches(filter)){
				return false;
			}else if(file.getName().toLowerCase().matches(".*\\.(jpg|jpeg|bmp|png|gif)")){
				return true;
			}
		}
		return true;
	}
	
}
