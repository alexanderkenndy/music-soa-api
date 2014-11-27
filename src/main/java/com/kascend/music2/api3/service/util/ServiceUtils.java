package com.kascend.music2.api3.service.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.kascend.common.util.MD5Digest;
import com.kascend.common.util.Validator;
import com.kascend.frameworkcommons.config.Configer;
import com.kascend.music2.api3.entity.Album;
import com.kascend.music2.api3.entity.AlbumUdata;
import com.kascend.music2.api3.entity.Artist;
import com.kascend.music2.api3.entity.Song;
import com.kascend.music2.api3.entity.SongMv;
import com.kascend.music2.api3.entity.User;
import com.kascend.music2.api3.entity.UserOpAlbum;
import com.kascend.music2.api3.entity.UserSns;
import com.kascend.music2.api3.entity.UserSong;
import com.kascend.music2.api3.entity.UserSongArtist;
import com.kascend.music2.api3.exception.MusicRcException;
import com.kascend.music2.api3.service.metadata.info.GetSimilarSongInfo;
import com.kascend.music2.api3.service.metadata.info.MusicInfo;
import com.kascend.music2.api3.service.metadata.info.PageInfo;
import com.kascend.music2.api3.service.metadata.response.entry.New_GetMtvInfoEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_AlbumEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_ArtistEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_MvEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_SongEntry;

public class ServiceUtils implements java.io.Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public static final Logger log = Logger.getLogger(ServiceUtils.class);

	public static void getStrParamLength(String name,int length){
		if(!Validator.isBlank(name)&&name.length()>length){
			throw new MusicRcException(MusicRcException.DATA_TOO_LONG);
		}
	}
	
	public static void getLenParamLength(long id,int length){
		if(id>0&&String.valueOf(id).length()>length){
			throw new MusicRcException(MusicRcException.DATA_TOO_LONG);
		}
	}
	
	
	public static String getMetaDataRegionByRegionId(int regionId){
		String region="未知";
		if(regionId==Constants.ARTIST_REGION_cn){
			region="国内";
		}else if(regionId==Constants.ARTIST_REGION_en_us){
			region="欧美";
		}else if(regionId==Constants.ARTIST_REGION_jp_kr){
			region="日韩";
		}else if(regionId==Constants.ARTIST_REGION_others){
			region="其它";
		}else{
			region="未知";
		}
		return region;
	}
	public static String getArtistGenderByGenderId(int genderId){
		String type="未知";
		if(genderId==Constants.ARTIST_TYPE_MALE){
			type="男歌手";
		}else if(genderId==Constants.ARTIST_TYPE_FEMALE){
			type="女歌手";
		}else if(genderId==Constants.ARTIST_TYPE_BANDGROUP){
			type="乐队";
		}else{
			type="未知";
		}
		return type;
	}
	
	public static String getOpType(int typeValue){
		String type="";
		if(typeValue==Constants.OP_TYPE_PLAY){
			type="试听";
		}else if(typeValue==Constants.OP_TYPE_COMMENT){
			type="评论";
		}else if(typeValue==Constants.OP_TYPE_LIKE){
			type="收藏";
		}else{
			type="";
		}
		return type;
	}
	
	public static String formatSubTitle(String subTitle, int opType) {
		if (Validator.isBlank(subTitle)) {
			subTitle="专辑";
		}
		if (opType == Constants.OP_TYPE_PLAY) {
			return "收听了\"" + subTitle + "\"";
		} else if (opType == Constants.OP_TYPE_COMMENT) {
			return "评论了\"" + subTitle + "\"";
		}
		if (opType == Constants.OP_TYPE_LIKE) {
			return "收藏了\"" + subTitle + "\"";
		}
		return null;
	}
	
	
	public static String formatSnsShareTitle(String itemTitle,int itemType){
		String prefix="分享了";
		if (itemType == Constants.ITEM_TYPE_SONG) {
			prefix+="歌曲\""+itemTitle+"\"";
		} else if (itemType == Constants.ITEM_TYPE_ARTIST) {
			prefix+="歌手\""+itemTitle+"\"";
		} else if (itemType == Constants.ITEM_TYPE_ALBUM) {
			prefix+="专辑\""+itemTitle+"\"";
		} else if (itemType == Constants.ITEM_TYPE_MV) {
			prefix+="MV\""+itemTitle+"\"";
		} else if (itemType == Constants.ITEM_TYPE_SONG_PLAYLIST){
			prefix+="音乐集\""+itemTitle+"\"";
		}else if(itemType == Constants.ITEM_TYPE_MV_PLAYLIST) {
			prefix+="MV集\""+itemTitle+"\"";
		} else if (itemType == Constants.ITEM_TYPE_SNS) {
			prefix+="微博\""+itemTitle+"\"";
		}
		return prefix;
	}
	
	
	public static String getUserGenderByGenderFlag(String genderValue){
		String type="未知";
		if(genderValue.equalsIgnoreCase("m")){
			type="男";
		}else if(genderValue.equalsIgnoreCase("f")){
			type="女";
		}else{
			type="未知";
		}
		return type;
	}

	
	public static  int getMvUriType(Integer dataFrom){
		int uriType=Constants.MV_CONSOLE_URI_TYPE;
		if(dataFrom!=null&&dataFrom.intValue()>0){
			if(dataFrom==Constants.MV_DATA_FROM_QQ){
				uriType=Constants.MV_QQ_URI_TYPE;
			}else if(dataFrom==Constants.MV_DATA_FROM_SINA){
				uriType=Constants.MV_SINA_URI_TYPE;
			}else if(dataFrom==Constants.MV_DATA_FROM_SOHU){
				uriType=Constants.MV_SOHU_URI_TYPE;
			}else if(dataFrom==Constants.MV_DATA_FROM_YOUKU){
				uriType=Constants.MV_YOUKU_URI_TYPE;
			}else if(dataFrom==Constants.MV_DATA_FROM_YINYUETAI){
				uriType=Constants.MV_YINYUETAI_URI_TYPE;
			}
		}
		return uriType;
	}
	


	public static String getPicUrl(String url) {
		String picurl = "";
		if (url != null && (!url.startsWith("http://"))) {
			picurl = Configer.getValueString("pics.server") + url;
		} else {
			picurl = url;
		}
		return picurl;
	}

	public static String returnURL(String url) {
		if (!Validator.isBlank(url)) {
			if(!url.startsWith("http")){
				url = getPicUrl(url);
			}
		} else {
			url = null;
		}
		return url;
	}

	public static Integer getPage(Integer page) {
		return page == null ? 1 : page;
	}

	public static Integer getPageCount(Integer pageCount) {
		if(pageCount!=null&&pageCount.intValue()>100){
			pageCount=Constants.MAX_PAGE_COUNT;
		}else if(pageCount==null){
			pageCount=Constants.COMMON_PAGE_COUNT;
		}
		return pageCount;
	}
	public static Integer getSpecialPageCount(Integer pageCount) {
		if(pageCount!=null&&pageCount.intValue()>500){
			pageCount=500;
		}else if(pageCount==null){
			pageCount=Constants.COMMON_PAGE_COUNT;
		}
		return pageCount;
	}
	public static void checkPage(Integer page) {
		if (page <= 0) {
			throw new MusicRcException(MusicRcException.INVALID_PAGE);
		}
	}

	public static void checkPageCount(Integer pageCount) {
		if (pageCount <= 0) {
			throw new MusicRcException(MusicRcException.INVALID_PAGE_COUNT);
		}
	}

	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}
	public static boolean isChinese(String strName) {
		strName = strName.trim();
		strName = strName.replaceAll("\\s.*?", "");
		boolean falg = true;
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			falg &= isChinese(c);
			if (!falg) {
				return falg;
			}
		}
		return falg;
	}
	
	public static PageInfo pageCompute(PageInfo info) {
		Integer page = getPage(info.getPage());
		Integer pageCount = getPageCount(info.getPagecount());
		checkPage(page);
		checkPageCount(pageCount);
		int start = (page - 1) * pageCount;
		info.setPage(page);
		info.setStart(start);
		if (info.getPagecount()  != 0) {
			info.setPagecount(pageCount);
		} else {
			info.setPagecount(Constants.COMMON_PAGE_COUNT);
		}
		return info;
	}
/**
 * 
 * @param info
 * @return
 */

	public static PageInfo specialPageCompute(PageInfo info) {
		Integer page = getPage(info.getPage());
		Integer pageCount = getSpecialPageCount(info.getPagecount());
		checkPage(page);
		checkPageCount(pageCount);
		int start = (page - 1) * pageCount;
		info.setPage(page);
		info.setStart(start);
		if (info.getPagecount()!= 0) {
			info.setPagecount(pageCount);
		} else {
			info.setPagecount(Constants.COMMON_PAGE_COUNT);
		}
		return info;
	}
	

	/**
	 * 去除重复mvid的
	 * @param songlist
	 * @return
	 */
	public static List<New_GetMtvInfoEntry> removeIterativeOnMTVListGetMTVInfoEntryAfter(List<New_GetMtvInfoEntry> mtvlist){
		List<New_GetMtvInfoEntry> list=new ArrayList<New_GetMtvInfoEntry>();
		Set<Integer> set=new HashSet<Integer>();
		for(New_GetMtvInfoEntry e:mtvlist){
			Integer mtvId=e.getMtvid();
			int size=set.size();
			set.add(mtvId);
			if(set.size()>size){
				list.add(e);
			}
		}
		return list;
	}
	public static List<Album> removeIterativeOnAlbumList(List<Album> albumList){
		List<Album> list=new ArrayList<Album>();
		Set<Long> set=new HashSet<Long>();
		for(Album album:albumList){
			long albumId=album.getAlbumId();
			int size=set.size();
			set.add(albumId);
			if(set.size()>size){
				list.add(album);
			}
		}
		return list;
	}
	
	/**
	 * 去除重复mvid的
	 * @param songlist
	 * @return
	 */
	public static List<New_MvEntry> removeIterativeMvIdForMvEntry(List<New_MvEntry> mtvlist){
		List<New_MvEntry> list=new ArrayList<New_MvEntry>();
		Set<Integer> set=new HashSet<Integer>();
		for(New_MvEntry e:mtvlist){
			Integer mtvId=e.getMvid();
			int size=set.size();
			set.add(mtvId);
			if(set.size()>size){
				list.add(e);
			}
		}
		return list;
	}
	
	public static List<New_MvEntry> removeIterativeSongIdForMvEntry(List<New_MvEntry> songlist) {
		List<New_MvEntry> list=new ArrayList<New_MvEntry>();
		Set<Long> set=new HashSet<Long>();
		for(New_MvEntry e:songlist){
			Long songId=e.getSongid();
			int size=set.size();
			set.add(songId);
			if(set.size()>size){
				list.add(e);
			}
		}
		return list;
	}
	//排除自身
	public static List<New_SongEntry> removeIterativeOfSongListAndSelf(List<New_SongEntry> songList,GetSimilarSongInfo info){
		List<New_SongEntry> list=removeIterativeOnSongListNewSongEntry(songList);
		List<New_SongEntry> delList=new ArrayList<New_SongEntry>();
		for(New_SongEntry e:list){
			if (!Validator.isBlank(info.getSong())
					&& !Validator.isBlank(e.getTitle())) {
				if (e.getTitle().equals(info.getSong())) {
					delList.add(e);
				}
			} else if (info.getSongid() > 0 
					&& (e.getSongid()== info.getSongid())) {
				delList.add(e);
			}
		}
		if (delList.size() != 0) {
			list.removeAll(delList);
		}
		return list;
	}
	public static List<New_SongEntry> removeIterativeOnSongListNewSongEntry(List<New_SongEntry> songlist) {
		List<New_SongEntry> list=new ArrayList<New_SongEntry>();
		Set<Long> set=new HashSet<Long>();
		for(New_SongEntry e:songlist){
			Long songId=e.getSongid();
			int size=set.size();
			set.add(songId);
			if(set.size()>size){
				list.add(e);
			}
		}
		return list;
	}
	public static List<Song> removeIterativeOnSongList(List<Song> songlist) {
		List<Song> list=new ArrayList<Song>();
		Set<Long> set=new HashSet<Long>();
		for(Song e:songlist){
			Long songId=e.getSongId();
			int size=set.size();
			set.add(songId);
			if(set.size()>size){
				list.add(e);
			}
		}
		return list;
	}
	public static List<Long> removeIterativeOnIdList(List<Long> idList) {
		List<Long> list=new ArrayList<Long>();
		Set<Long> set=new HashSet<Long>();
		for(Long songId:idList){
			int size=set.size();
			set.add(songId);
			if(set.size()>size){
				list.add(songId);
			}
		}
		return list;
	}
	public static List<UserSong> removeIterativeUserSongList(List<UserSong> songlist) {
		List<UserSong> list=new ArrayList<UserSong>();
		if(songlist.size()>0){
			Set<Long> set=new HashSet<Long>();
			for(UserSong e:songlist){
				if(e.getSongId()>0){
					Long songId=e.getSongId();
					int size=set.size();
					set.add(songId);
					if(set.size()>size){
						list.add(e);
					}
				}else{
					list.add(e);
				}
				
			}
		}
		return list;
	}
	public static String formatUserNameAsSns(String snsUserNames){
		String nameContent="";
		String [] userNameArray=splitStrArray(snsUserNames,Constants.SPLIT_DOT);
		for(int i=0;i<userNameArray.length;i++){
			nameContent+=Constants.WEIBO_PREFIX+userNameArray[i]+"	";
		}
		return nameContent;
	}
	
	
	public static List<String> splitStr(String str,String regEx){
		List<String> list=new ArrayList<String>();
		if(!Validator.isBlank(str)){
			StringTokenizer token=new StringTokenizer(str,regEx);
			while(token.hasMoreTokens()){
				list.add(token.nextToken());
			}
		}
		return list;
	}
	public static String [] splitStrArray(String str,String delimeter){
		StringTokenizer token=new StringTokenizer(str,delimeter);
		String[] array = new String[token.countTokens()];  
		int i = 0;  
		while (token.hasMoreTokens()){  
		    array[i++] =token.nextToken();  
		}
		return array;
	}
	/**
	 * 按|分割
	 * @return
	 */
	public static List<Integer> splitInteger(String str){
		List<Integer> list=new ArrayList<Integer>();
		List<String> strList=splitStr(str,Constants.SPLIT_STR);
		for(int i=0;i<strList.size();i++){
			int result=0;
			try {
				result=Integer.parseInt(strList.get(i));
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
			list.add(result);
		}
		return list;
	}
	/**
	 * 按|分割
	 * @return
	 */
	public static List<Long> splitLong(String str){
		List<Long> list=new ArrayList<Long>();
		List<String> strList=splitStr(str,Constants.SPLIT_STR);
		for(int i=0;i<strList.size();i++){
			long result=0l;
			try {
				result=Long.parseLong(strList.get(i));
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
			list.add(result);
		}
		return list;
	}
	/**
	 * 解析页面uri
	 * 
	 * @param responseUri
	 * @return
	 */
	public static String getRequestUriFromTop100(String productId) {
		productId = productId.trim();
		String secret = "b29inviiiyk32ajryo879gb8xeqevelg";
		String url = "http://partner.top100.cn/api/rest/?method=Top100.Track.GetTrackPlayUrl&productID="
				+ productId + "&api_key=0nwdwq8blt0szd1gjnytb2zsyr440f3d";
		String[] strs = url.substring(url.indexOf("?") + 1).split("\\&");
		Map<String, String> params = new HashMap<String, String>();
		for (String str : strs) {
			String[] ss = str.split("=");
			if (ss.length == 2) {
				params.put(ss[0], ss[1]);
			} else {
				params.put(ss[0], "");
			}
		}
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
		StringBuffer sb = new StringBuffer();
		for (String key : keys) {
			if (key.equals("api_key") || key.equals("method"))
				continue;
			sb.append(key);
			try {
				sb.append(URLDecoder.decode(params.get(key), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				if (log.isDebugEnabled())
					log.debug(e.getMessage(), e);
				else
					log.error(e.getMessage());
			}
		}

		String appsig = sb.toString().replaceFirst("\\&", "");
		// md5
		String s = secret + "" + appsig;
		MD5Digest md5 = MD5Digest.getInstance(s);
		String response = url + "&api_sig=" + md5.toString();
		return response;
	}
	/**
	 * 解析页面uri
	 * 
	 * @param responseUri
	 * @return
	 */
	public static String getResponsePageFromTop100(String feed) {
		String responseContent = null;
		GetMethod getMethod = null;
		try {
			HttpClient httpClient = new HttpClient();
			HttpConnectionManagerParams managerParams = httpClient
					.getHttpConnectionManager().getParams();
			managerParams.setConnectionTimeout(30000);
			managerParams.setSoTimeout(120000);
			getMethod = new GetMethod(feed);
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, true));
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			} else {
				InputStream reader = getMethod.getResponseBodyAsStream();
				byte buf[] = new byte[1024];
				int size = 0;
				while ((size = reader.read(buf)) != -1) {
					responseContent = new String(buf, "iso-8859-1");
				}
				if (responseContent.startsWith("<")) {
					return responseContent;
				} else {
					responseContent = responseContent.trim().replaceFirst(
							"^([\\W]+)<", "<");
				}
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug(e.getMessage(), e);
			else
				log.error(e.getMessage());
		}
		return responseContent;
	}

	/**
	 * 解析页面uri
	 * 
	 * @param responseUri
	 * @return
	 */
	public static String getDownloadUriFromTop100(String trackId) {
		String fileDownloadUri = null;
		String responseUri = getRequestUriFromTop100(trackId);
		String responsePage = getResponsePageFromTop100(responseUri);
		try {
			if (responsePage != null && !"".equals(responsePage)) {
				Document document = null;
				try {
					document = DocumentHelper.parseText(responsePage);
				} catch (DocumentException e) {
					log.debug(e.getMessage());
				}
				Element root = document.getRootElement();
				if (root != null) {
					Attribute stateAttr = root.attribute("stat");
					if (stateAttr != null && "ok".equals(stateAttr.getText())) {
						Element trackUrl = root.element("TrackUrl");
						Element trackID = trackUrl.element("TrackID");
						if (trackID != null && trackID.getText() != null
								&& trackId.equals(trackID.getText())) {
							Element auditionUrl = trackUrl
									.element("AuditionUrl");
							if (auditionUrl != null
									&& auditionUrl.getText() != null) {
								fileDownloadUri = auditionUrl.getText();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return fileDownloadUri;
	}

	/**
	 * 验证是否存在
	 * @param id
	 * @return
	 */
	public static boolean validateLongEquals(Long id) {
		if (id == null || id.longValue() == 0) {
			return true;
		}
		return false;
	}
	/**
	 * 验证是否存在
	 * @param id
	 * @return
	 */
	public static boolean validateLongNotEquals(Long id) {
		if (id != null && id.longValue() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 拼接字符串
	 * @param list
	 * @return
	 */
	public static String getQueryString4Long(List<Long> list) {
		String queryStr = "0";
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				queryStr += ",";
				queryStr += String.valueOf(list.get(i));
			}
		}
		
		queryStr = "(" + queryStr + ")";
		return queryStr;
	}


	/**
	 * 外部分页方法
	 * @param entry
	 * @param info
	 * @return
	 */
	public static List outSidePaging(List entry,MusicInfo info){
		int lstart = (info.getPage() - 1) * info.getPagecount();
		int lend = info.getPage() * info.getPagecount();
		if (entry != null) {
			entry = entry.subList((lstart > entry.size() ? entry.size()
					: lstart), (lend > entry.size() ? entry.size() : lend));
		}
		return entry;
	}
	public static List outSidePaging(List entry,int pageCount,int page){
		int lstart = (page - 1) * pageCount;
		int lend = page * pageCount;
		if (entry != null) {
			entry = entry.subList((lstart > entry.size() ? entry.size()
					: lstart), (lend > entry.size() ? entry.size() : lend));
		}
		return entry;
	}


	/**
	 * 包含keyword的排在前面
	 * @param entryList
	 * @param keyword
	 * @return
	 */	
	public static List<New_SongEntry> sortNewSongListEntry(List<New_SongEntry> entryList, String keyword) {
		List<New_SongEntry> entrys1 = new ArrayList<New_SongEntry>();
		List<New_SongEntry> entrys2 = new ArrayList<New_SongEntry>();
		List<New_SongEntry> entrys3 = new ArrayList<New_SongEntry>();
		List<New_SongEntry> entrys = new ArrayList<New_SongEntry>();
		int sequene1 = 0;
		int sequene2 = 0;
		int sequene3 = 0;
		for (int i = 0; i < entryList.size(); i++) {
			New_SongEntry entry = entryList.get(i);
			String artistName = entry.getTitle();
			if ((null != artistName && (artistName.equals(keyword)))){
				entrys1.add(sequene1, entry);
				sequene1++;
			}
			else if ((null != artistName && (artistName.indexOf(keyword) != -1))) {
				entrys2.add(sequene2, entry);
				sequene2++;
				
			} else {
				entrys3.add(sequene3, entry);
				sequene3++;
			}
		}
		for (int i = 0; i < entrys1.size(); i++) {
			entrys.add(i, entrys1.get(i));
		}
		for (int j = entrys1.size(); j < entrys1.size() + entrys2.size(); j++) {
			entrys.add(j, entrys2.get(j - entrys1.size()));
		}
		for(int k=entrys1.size() + entrys2.size();k<entrys1.size() + entrys2.size()+entrys3.size();k++){
			entrys.add(k, entrys3.get(k - entrys1.size()-entrys2.size()));
		}
		return entrys;
	}
	public static List<New_AlbumEntry> sortNewAlbumListEntry(List<New_AlbumEntry> entryList, String keyword) {
		List<New_AlbumEntry> entrys1 = new ArrayList<New_AlbumEntry>();
		List<New_AlbumEntry> entrys2 = new ArrayList<New_AlbumEntry>();
		List<New_AlbumEntry> entrys3 = new ArrayList<New_AlbumEntry>();
		List<New_AlbumEntry> entrys = new ArrayList<New_AlbumEntry>();
		int sequene1 = 0;
		int sequene2 = 0;
		int sequene3 = 0;
		for (int i = 0; i < entryList.size(); i++) {
			New_AlbumEntry entry = entryList.get(i);
			String albumTitle = entry.getTitle();
			if ((null != albumTitle && (albumTitle.equals(keyword)))){
				entrys1.add(sequene1, entry);
				sequene1++;
			}
			else if ((null != albumTitle && (albumTitle.indexOf(keyword) != -1))) {
				entrys2.add(sequene2, entry);
				sequene2++;
				
			} else {
				entrys3.add(sequene3, entry);
				sequene3++;
			}
		}
		for (int i = 0; i < entrys1.size(); i++) {
			entrys.add(i, entrys1.get(i));
		}
		for (int j = entrys1.size(); j < entrys1.size() + entrys2.size(); j++) {
			entrys.add(j, entrys2.get(j - entrys1.size()));
		}
		for(int k=entrys1.size() + entrys2.size();k<entrys1.size() + entrys2.size()+entrys3.size();k++){
			entrys.add(k, entrys3.get(k - entrys1.size()-entrys2.size()));
		}
		return entrys;
	}
	/**
	 * 包含keyword的排在前面
	 * @param entryList
	 * @param keyword
	 * @return
	 */
	public static List<New_ArtistEntry> sortNewArtistListEntry(List<New_ArtistEntry> entryList, String keyword) {
		List<New_ArtistEntry> entrys1 = new ArrayList<New_ArtistEntry>();
		List<New_ArtistEntry> entrys2 = new ArrayList<New_ArtistEntry>();
		List<New_ArtistEntry> entrys3 = new ArrayList<New_ArtistEntry>();
		List<New_ArtistEntry> entrys = new ArrayList<New_ArtistEntry>();
		int sequene1 = 0;
		int sequene2 = 0;
		int sequene3 = 0;
		for (int i = 0; i < entryList.size(); i++) {
			New_ArtistEntry entry = entryList.get(i);
			String artistName = entry.getTitle();
			if ((null != artistName && (artistName.equals(keyword)))){
				entrys1.add(sequene1, entry);
				sequene1++;
			}
			else if ((null != artistName && (artistName.indexOf(keyword) != -1))) {
				entrys2.add(sequene2, entry);
				sequene2++;
				
			} else {
				entrys3.add(sequene3, entry);
				sequene3++;
			}
		}
		for (int i = 0; i < entrys1.size(); i++) {
			entrys.add(i, entrys1.get(i));
		}
		for (int j = entrys1.size(); j < entrys1.size() + entrys2.size(); j++) {
			entrys.add(j, entrys2.get(j - entrys1.size()));
		}
		for(int k=entrys1.size() + entrys2.size();k<entrys1.size() + entrys2.size()+entrys3.size();k++){
			entrys.add(k, entrys3.get(k - entrys1.size()-entrys2.size()));
		}
		return entrys;
	}

	/**
	 * 将2个list排序，第一个放前面
	 * @param firstList
	 * @param secondList
	 * @return
	 */
	public static List sortListEntry(List firstList,List secondList){
		List entrys = new ArrayList();
		if(firstList!=null&&firstList.size()>0){
			for (int i = 0; i < firstList.size(); i++) {
				entrys.add(i, firstList.get(i));
			}
			if(secondList!=null&&secondList.size()>0){
				for (int j = firstList.size(); j < firstList.size() + secondList.size(); j++) {
					entrys.add(j, secondList.get(j - firstList.size()));
				}
			}
		
		}else{
			if(secondList!=null&&secondList.size()>0){
				entrys.addAll(secondList);
			}
		}
		return entrys;
	}
	public static List sortMultiListEntry(List fristList,List secondList,List thirdList,List fourthList){
		List fiveList=sortListEntry(fristList,secondList);
		List sixList=sortListEntry(thirdList,fourthList);
		return sortListEntry(fiveList,sixList);
	}

	/**
	 * Entry
	 * 随机返回list中的数据
	 * @param entry
	 * @param info
	 * @return
	 */
	public static List randomAccessList(List entry){
		List resultEntry=new ArrayList();
		if(entry!=null&&entry.size()>0){
			resultEntry=getRandomList(entry,entry.size());
		}
		return resultEntry;
	}
	/**
	 * 取随机list公共方法
	 * @param entry
	 * @param maxSize
	 * @return
	 */
	public static List getRandomList(List entry,int maxSize){
		Set set=new HashSet();
		List resultEntry=new ArrayList();
		if(entry.size()==0)return resultEntry;
		while (resultEntry.size() < maxSize) {
			int random = (int) (Math.random() * entry.size());
			int size = set.size();
			set.add(entry.get(random));
			if (size != set.size()) {
				resultEntry.add(entry.get(random));
			}
		}
		return resultEntry;
	}
	
	public static List<Song> removeSongListByCondition(List<Song> entry){
		List<Song> delEntry=new ArrayList<Song>();
		if(entry.size()>0){
			for(Song song:entry){
				if(song.getSongId()==0||song.getArtistId()==0){
					delEntry.add(song);
				}
			}
			if(delEntry.size()>0){
				entry.removeAll(delEntry);
			}
		}
		return entry;
	}
	
	/**
	 * User
	 * 按第一个list的顺序，对第二个进行排序
	 * @param firstList
	 * @param secondList
	 * @return
	 */
	public static List<User> sortUserRandomList(List<Long> firstList,List<User> secondList){
		List<User> userList=new ArrayList<User>();
		if(firstList!=null&&firstList.size()>0){
			if(secondList!=null&&secondList.size()>0){
				int seq=0;
				for(int i=0;i<firstList.size();i++){
					for(int j=0;j<secondList.size();j++){
						if(secondList.get(j).getUid()==firstList.get(i).longValue()){
							userList.add(seq,secondList.get(j));
							seq++;
						}
					}
				}
			}
		}
		return userList;
	}
	/**
	 * Song
	 * 按第一个list的顺序，对第二个进行排序
	 * @param firstList
	 * @param secondList
	 * @return
	 */
	public static List<Song> sortSongRandomList(List<Long> firstList,List<Song> secondList){
		List<Song> entry=new ArrayList<Song>();
		if(firstList!=null&&firstList.size()>0){
			if(secondList!=null&&secondList.size()>0){
				int seq=0;
				for(int i=0;i<firstList.size();i++){
					for(int j=0;j<secondList.size();j++){
						if(secondList.get(j).getSongId()==firstList.get(i).longValue()){
							entry.add(seq,secondList.get(j));
							seq++;
						}
					}
				}
			}
		}
		return entry;
	}
	/**
	 * Album
	 * 按第一个list的顺序，对第二个进行排序
	 * @param firstList
	 * @param secondList
	 * @return
	 */
	public static List<Album> sortAlbumSeqList(List<Long> firstList,List<Album> secondList){
		List<Album> entry=new ArrayList<Album>();
		if(firstList!=null&&firstList.size()>0){
			if(secondList!=null&&secondList.size()>0){
				int seq=0;
				for(int i=0;i<firstList.size();i++){
					for(int j=0;j<secondList.size();j++){
						if(secondList.get(j).getAlbumId()==firstList.get(i).longValue()){
							entry.add(seq,secondList.get(j));
							seq++;
						}
					}
				}
			}
		}
		return entry;
	}
	
	/**
	 * Album
	 * 按第一个list的顺序，对第二个进行排序
	 * @param firstList
	 * @param secondList
	 * @return
	 */
	public static List<Long> sortLongSeqList(List<Long> firstList,List<Long> secondList){
		List<Long> entry=new ArrayList<Long>();
		if(firstList!=null&&firstList.size()>0){
			if(secondList!=null&&secondList.size()>0){
				int seq=0;
				for(int i=0;i<firstList.size();i++){
					for(int j=0;j<secondList.size();j++){
						if(secondList.get(j)==firstList.get(i).longValue()){
							entry.add(seq,secondList.get(j));
							seq++;
						}
					}
				}
			}
		}
		return entry;
	}
	/**
	 * Artist
	 * 按第一个list的顺序，对第二个进行排序
	 * @param firstList
	 * @param secondList
	 * @return
	 */
	public static List<Artist> sortArtistSeqList(List<Long> firstList,List<Artist> secondList){
		List<Artist> entry=new ArrayList<Artist>();
		if(firstList!=null&&firstList.size()>0){
			if(secondList!=null&&secondList.size()>0){
				int seq=0;
				for(int i=0;i<firstList.size();i++){
					for(int j=0;j<secondList.size();j++){
						if(secondList.get(j).getArtistId()==firstList.get(i).longValue()){
							entry.add(seq,secondList.get(j));
							seq++;
						}
					}
				}
			}
		}
		return entry;
	}

	public static void setArtistAttribute4SongMv(SongMv e){
		Long artistId=null;
		String artistName=null;
		String artistIds=e.getArtistIds();
		String artistNames=e.getArtistNames();
		if(!Validator.isBlank(artistIds)&&!Validator.isBlank(artistNames)){
			if(artistIds.indexOf(Constants.SPLIT_STR)!=-1){
				artistIds=artistIds.substring(0,artistIds.indexOf(Constants.SPLIT_STR));
			}
			if(artistNames.indexOf(Constants.SPLIT_STR)!=-1){
				artistNames=artistNames.substring(0,artistNames.indexOf(Constants.SPLIT_STR));
			}
		}
		if(!Validator.isBlank(artistIds)&&!Validator.isBlank(artistNames)){
			artistId=Long.parseLong(artistIds);
			e.setArtistId(artistId);
			artistName=artistNames;
			e.setArtistName(artistName);
		}
		e.setArtistIds(null);
		e.setArtistNames(null);
	}

	/**
	 * GetMTVInfoEntry
	 * 设置歌手和歌手id
	 * @param e
	 */
	public static List<Integer> getOtherGenreIdList(int genreType){
		List<Integer> genreIdList=new ArrayList<Integer>();
		if(genreType==0){//歌曲和专辑
			
		}else if(genreType==1){//歌手
			
		}else if(genreType==2){//播放列表
		   for(int i=140;i<161;i++){
			   genreIdList.add(i);
		   }
		}
		return genreIdList;
	}

	 //获取过去指定天数的日期
	 public static long getPastDate(int dayCount){
		 Date date=new Date();//取时间
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(Calendar.DATE,dayCount);//把日期往后增加一天.整数往后推,负数往前移动
		 date=calendar.getTime(); //这个时间就是日期往后推一天的结果     
		 return date.getTime()/1000;
	 }
	 
	public static List<Album> sortAlbumList(List<Album> albumList,List<AlbumUdata> albumUdataList){
		List<Album> resultList=new ArrayList<Album>();
		for(int i=0;i<albumUdataList.size();i++){
			AlbumUdata au=albumUdataList.get(i);
			for(int j=0;j<albumList.size();j++){
				Album album=albumList.get(j);
				if(au.getAlbumId()==album.getAlbumId()){
					album.setLastPlayTime(au.getLastPlayTime());
					album.setListenedUsersCount(au.getUserTimes());
					album.setLastOpType(au.getLastOpType());
					resultList.add(album);
				}
			}
		}
		return resultList;
	}
		
	
	public static List<Song> getSongListByPlayTimes(List<Song> songList,int resultCount){
		List<Song> resultList=new ArrayList<Song>();
		if(songList.isEmpty()){
			return resultList;
		}
		ComparatorSong comparator=new ComparatorSong();
		Collections.sort(songList, comparator);
		if(songList.size()<resultCount){
			resultList=songList;
		}else{
			for(int i=0;i<resultCount;i++){
				resultList.add(songList.get(i));
			}
		}
		return resultList;
	}
	
	
	public class ComparatorUserOpAlbum implements Comparator{

		@Override
		public int compare(Object o1, Object o2) {
			UserOpAlbum first=(UserOpAlbum)o1;
			UserOpAlbum second=(UserOpAlbum)o2;
			if(first.getLastTime()>second.getLastTime()){
				return -1;
			}else{
				return 1;
			}
		}
		
	}
	
	public static class ComparatorSong implements Comparator{

		@Override
		public int compare(Object o1, Object o2) {
			Song first=(Song)o1;
			Song second=(Song)o2;
			if(first.getPlayTimes()>second.getPlayTimes()){
				return -1;
			}else if((first.getPlayTimes()==second.getPlayTimes())){
				if(first.getSortIndex()<second.getSortIndex()){
					return -1;
				}else{
					return 1;
				}
			}else{
				return 1;
			}
		}
	}
	//客户端要插入的类
	public static List<UserSong> getDifferFromServer(List<UserSong> sameUserSongList,List<UserSong> metaList){
		if(sameUserSongList.size()>0){
			boolean flag=metaList.removeAll(sameUserSongList);
			if(flag){
				log.debug("Remove Success");
			}
		}
		return metaList;
	}
	//客户端和server不同的地方
	public static List<UserSong> getDiffFromClient(List<UserSong> userSongList,List<UserSong> sameUserSongList){
		if(sameUserSongList.size()>0){
			boolean flag=userSongList.removeAll(sameUserSongList);
			if(flag){
				log.debug("Remove Success");
			}
		}
		return userSongList;
	}
	
	public static boolean compareUserMetadata(UserSong metadata,UserSong userSong){
			if(!Validator.isBlank(metadata.getSongTitle())){
				if(metadata.getSongTitle().equals(userSong.getSongTitle())){
					if(!Validator.isBlank(metadata.getArtistName())){
						if(!Validator.isBlank(userSong.getArtistName())){
							if(metadata.getArtistName().equals(userSong.getArtistName())){
								if(!Validator.isBlank(metadata.getAlbumTitle())){
									if(metadata.getAlbumTitle().equals(userSong.getAlbumTitle())){
										return true;
									}
								}else{
									if(Validator.isBlank(userSong.getAlbumTitle())){
										return true;
									}
								}
							}
						}
					}else{
						if(Validator.isBlank(userSong.getArtistName())){
							if(!Validator.isBlank(metadata.getAlbumTitle())){
								if(metadata.getAlbumTitle().equals(userSong.getAlbumTitle())){
									return true;
								}
							}else{
								if(Validator.isBlank(userSong.getAlbumTitle())){
									return true;
								}
							}
						}
					}
				}
			}else{
				if(Validator.isBlank(userSong.getSongTitle())){

					if(!Validator.isBlank(metadata.getArtistName())){
						if(!Validator.isBlank(userSong.getArtistName())){
							if(metadata.getArtistName().equals(userSong.getArtistName())){
								if(!Validator.isBlank(metadata.getAlbumTitle())){
									if(metadata.getAlbumTitle().equals(userSong.getAlbumTitle())){
										return true;
									}
								}else{
									if(Validator.isBlank(userSong.getAlbumTitle())){
										return true;
									}
								}
							}
						}
					}else{
						if(Validator.isBlank(userSong.getArtistName())){
							if(!Validator.isBlank(metadata.getAlbumTitle())){
								if(metadata.getAlbumTitle().equals(userSong.getAlbumTitle())){
									return true;
								}
							}else{
								if(Validator.isBlank(userSong.getAlbumTitle())){
									return true;
								}
							}
						}
					}
				
				}
			}
		return false;
	}
	
	
	public static List<UserSong> getSameSongsOfPlaylist(List<UserSong> metaList,List<UserSong> userSongList){
		List<UserSong> sameList=new ArrayList<UserSong>();
		for(UserSong metaData:metaList){
			for(UserSong userSong:userSongList){
				if(!userSong.isCompareFlag()&&!metaData.isBeCompared()){
					if(metaData.getSongId()==0){
						if(compareUserMetadata(metaData,userSong)){
							userSong.setSortIndex(metaData.getSortIndex());
							sameList.add(userSong);
						}
					}else{
						if(metaData.getSongId()==userSong.getSongId()){
							userSong.setSortIndex(metaData.getSortIndex());
							sameList.add(userSong);
						}
					}
				}
			}
		}
		return sameList;
	}

	public static List<UserSong> getSameMetadatasOfPlaylist(List<UserSong> metaList,List<UserSong> userSongList){
		List<UserSong> sameList=new ArrayList<UserSong>();
		for(UserSong metaData:metaList){
			for(UserSong userSong:userSongList){
				if(!userSong.isCompareFlag2()&&!metaData.isBeCompared2()){
					if(metaData.getSongId()==0){
						if(compareUserMetadata(metaData,userSong)){
							userSong.setSortIndex(metaData.getSortIndex());
							sameList.add(metaData);
							userSong.setCompareFlag2(true);
							metaData.setBeCompared2(true);
						}
					}else{
						if(metaData.getSongId()==userSong.getSongId()){
							userSong.setSortIndex(metaData.getSortIndex());
							sameList.add(metaData);
							userSong.setCompareFlag2(true);
							metaData.setBeCompared2(true);
						}
					}
				}
			}
		}
		return sameList;
	}
	
	public static List<UserSongArtist> getSameUserSongArtist(List<UserSongArtist> songCountArtistList,List<UserSongArtist> albumCountArtistList){
		List<UserSongArtist> sameList=new ArrayList<UserSongArtist>();
		for(int i=0;i<songCountArtistList.size();i++){
			UserSongArtist usaSong=songCountArtistList.get(i);
			for(int j=0;j<albumCountArtistList.size();j++){
				UserSongArtist usaAlbum=albumCountArtistList.get(j);
				if(usaSong.getArtistId()==usaAlbum.getArtistId()){
					usaSong.setAlbumCount(usaAlbum.getAlbumCount());
					sameList.add(usaSong);
				}
			}
		}
		return sameList;
	}
	public static List<UserSongArtist> getDiffUserSongCountOfArtist(List<UserSongArtist> songCountArtistList,List<UserSongArtist> sameUserSongtArtistList){
		List<UserSongArtist> diffList=new ArrayList<UserSongArtist>();
		for(UserSongArtist userSongArtist:songCountArtistList){
			for(UserSongArtist sameUserSongArtist:sameUserSongtArtistList){
				if(userSongArtist.getArtistId()==sameUserSongArtist.getArtistId()){
					userSongArtist.setContainsFlag(true);
				}
			}
		}
		for(UserSongArtist userSongArtist:songCountArtistList){
			if(!userSongArtist.isContainsFlag()){
				diffList.add(userSongArtist);
			}
		}
		return diffList;
	}
	//将手机号码中间4位隐藏
	public static String hideUserNickName(String nickName){
		if(!Validator.isBlank(nickName)){
			if(isMobileNO(nickName)){
				String first=nickName.substring(0, 3);
				String last=nickName.substring(7, 11);
				nickName=first+"****"+last;
			}
		}
		return nickName;
	}
	public static boolean isMobileNO(String mobiles){
		Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	public static List<UserSns> beanCopyOfUserSns(List<com.kascend.e2e.osm.model.UserInfo> userSnsList){
		List<UserSns> userList=new ArrayList<UserSns>();
		if(userSnsList!=null){
			for(int i=0;i<userSnsList.size();i++){
				com.kascend.e2e.osm.model.UserInfo userInfo=userSnsList.get(i);
				UserSns userSns=new UserSns();
				userSns.setDescription(userInfo.getDescription());
				if(userInfo.getFavoriteCount()!=null){
					userSns.setFavoritecount(userInfo.getFavoriteCount());
				}
				if(userInfo.getFollowerCount()!=null){
					userSns.setFollowercount(userInfo.getFollowerCount());
				}
				if(userInfo.getFriendCount()!=null){
					userSns.setFriendcount(userInfo.getFriendCount());
				}
				if(userInfo.getItemCount()!=null){
					userSns.setItemcount(userInfo.getItemCount());
				}
				userSns.setGender(userInfo.getGender());
				userSns.setLocation(userInfo.getLocation());
				userSns.setSnsHeadIcon(userInfo.getHeadicon());
				userSns.setSnsUid(userInfo.getUserId());
				userSns.setSnsUserName(userInfo.getUserName());
				userList.add(userSns);
			}
		}
		return userList;
	}
	
	public static String  getBlackMenuFilter(){
		return "军车进京,启东,江苏启东,tc,TC,天朝,革命,袭击,共产党,国民党万岁,薄熙来,王立军,爆炸,枪支弹药,军火,政府,反共,毛泽东,江青,64事件,八九学潮,89学潮";
	}
	
	public static boolean checkMessageBeSend(String message){
		boolean beSend=true;
		String blackStr =getBlackMenuFilter();
		String[] blackStrList =splitStrArray(blackStr,
				Constants.SPLIT_DOT);
		for (int i = 0; i < blackStrList.length; i++) {
			String str = blackStrList[i];
			if (message.indexOf(str) != -1) {
				beSend = false;
				break;
			}
		}
		return beSend;
	}
}
