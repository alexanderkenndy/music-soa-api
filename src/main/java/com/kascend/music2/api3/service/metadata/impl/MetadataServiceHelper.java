package com.kascend.music2.api3.service.metadata.impl;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.kascend.common.util.Validator;
import com.arcsoft.e2e.osm.download.sdk.E2eDownloadService;
import com.arcsoft.e2e.osm.model.DownloadModel;
import com.arcsoft.e2e.osm.service.app.response.DownloadResponse;
import com.kascend.frameworkcommons.config.Configer;
import com.kascend.frameworkcommons.service.BaseService;
import com.kascend.jlucene.search.JLuceneSearchServer;
import com.kascend.music2.api3.dao.MetadataDao;
import com.kascend.music2.api3.dao.SocialDao;
import com.kascend.music2.api3.entity.Album;
import com.kascend.music2.api3.entity.AlbumPic;
import com.kascend.music2.api3.entity.Artist;
import com.kascend.music2.api3.entity.ArtistPic;
import com.kascend.music2.api3.entity.Billboard;
import com.kascend.music2.api3.entity.Lyric;
import com.kascend.music2.api3.entity.Song;
import com.kascend.music2.api3.entity.SongFile;
import com.kascend.music2.api3.entity.SongMv;
import com.kascend.music2.api3.entity.SongUri;
import com.kascend.music2.api3.entity.SubBillboard;
import com.kascend.music2.api3.entity.UserMetadata;
import com.kascend.music2.api3.entity.UserPlaylist;
import com.kascend.music2.api3.entity.UserSong;
import com.kascend.music2.api3.exception.MusicConst;
import com.kascend.music2.api3.exception.MusicRcException;
import com.kascend.music2.api3.service.enumeration.ActivityEnum;
import com.kascend.music2.api3.service.metadata.info.FeedBackSongInfo;
import com.kascend.music2.api3.service.metadata.info.GetAlbumInfo;
import com.kascend.music2.api3.service.metadata.info.GetAlbumsOfArtistInfo;
import com.kascend.music2.api3.service.metadata.info.GetAlbumsOfSongInfo;
import com.kascend.music2.api3.service.metadata.info.GetArtistInfo;
import com.kascend.music2.api3.service.metadata.info.GetDownloadUriInfo;
import com.kascend.music2.api3.service.metadata.info.GetLyricInfo;
import com.kascend.music2.api3.service.metadata.info.GetMvInfo;
import com.kascend.music2.api3.service.metadata.info.GetMvUriInfo;
import com.kascend.music2.api3.service.metadata.info.GetRandomSongInfo;
import com.kascend.music2.api3.service.metadata.info.GetSearchInfo;
import com.kascend.music2.api3.service.metadata.info.GetSearchKeywordInfo;
import com.kascend.music2.api3.service.metadata.info.GetSimilarSongInfo;
import com.kascend.music2.api3.service.metadata.info.GetSongInfo;
import com.kascend.music2.api3.service.metadata.info.GetSongsOfAlbumInfo;
import com.kascend.music2.api3.service.metadata.info.GetSongsOfArtistInfo;
import com.kascend.music2.api3.service.metadata.info.ListArtistInfo;
import com.kascend.music2.api3.service.metadata.info.ListBillboardInfo;
import com.kascend.music2.api3.service.metadata.info.ListMvInfo;
import com.kascend.music2.api3.service.metadata.info.LookupMvInfo;
import com.kascend.music2.api3.service.metadata.info.LookupSongInfo;
import com.kascend.music2.api3.service.metadata.info.MusicInfo;
import com.kascend.music2.api3.service.metadata.info.RecommendArtistInfo;
import com.kascend.music2.api3.service.metadata.info.RecommendalbumInfo;
import com.kascend.music2.api3.service.metadata.info.SearchAlbumInfo;
import com.kascend.music2.api3.service.metadata.info.SearchArtistInfo;
import com.kascend.music2.api3.service.metadata.info.SearchSongInfo;
import com.kascend.music2.api3.service.metadata.info.SongUriListInfo;
import com.kascend.music2.api3.service.metadata.info.StatClientUsageInfo;
import com.kascend.music2.api3.service.metadata.info.StatInfo;
import com.kascend.music2.api3.service.metadata.info.TopAlbumInfo;
import com.kascend.music2.api3.service.metadata.info.TopArtistInfo;
import com.kascend.music2.api3.service.metadata.info.TopMvInfo;
import com.kascend.music2.api3.service.metadata.info.TopSongInfo;
import com.kascend.music2.api3.service.metadata.info.UploadmetadataInfo;
import com.kascend.music2.api3.service.metadata.response.New_GetMtvInfoResponse;
import com.kascend.music2.api3.service.metadata.response.New_AlbumInfoResponse;
import com.kascend.music2.api3.service.metadata.response.New_AlbumResponse;
import com.kascend.music2.api3.service.metadata.response.New_ArtistInfoResponse;
import com.kascend.music2.api3.service.metadata.response.New_ArtistListResponse;
import com.kascend.music2.api3.service.metadata.response.New_BillboardListResponse;
import com.kascend.music2.api3.service.metadata.response.New_DownloadUriResponse;
import com.kascend.music2.api3.service.metadata.response.New_GetLyricResponse;
import com.kascend.music2.api3.service.metadata.response.New_GetMvInfoResponse;
import com.kascend.music2.api3.service.metadata.response.New_GetSongInfoResponse;
import com.kascend.music2.api3.service.metadata.response.New_KeyWordListResponse;
import com.kascend.music2.api3.service.metadata.response.New_MtvResponse;
import com.kascend.music2.api3.service.metadata.response.New_MtvUriListResponse;
import com.kascend.music2.api3.service.metadata.response.New_MvResponse;
import com.kascend.music2.api3.service.metadata.response.New_MvUriListResponse;
import com.kascend.music2.api3.service.metadata.response.New_SongListResponse;
import com.kascend.music2.api3.service.metadata.response.New_SongUriListResponse;
import com.kascend.music2.api3.service.metadata.response.New_StatResponse;
import com.kascend.music2.api3.service.metadata.response.entry.New_GetMtvInfoEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_AlbumEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_AlbumListEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_ArtistEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_ArtistListEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_ArtistartEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_BillboardEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_BillboardListEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_GetSongInfoEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_KeyWordListEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_LyricEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_MtvEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_MtvEntryList;
import com.kascend.music2.api3.service.metadata.response.entry.New_MtvUriEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_MvEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_MvListEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_MvUriEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_SongEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_SongListEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_SongUriEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_SongUriListEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_SubBillboardEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_SubBillboardListEntry;
import com.kascend.music2.api3.service.social.impl.SocialServiceHelper;
import com.kascend.music2.api3.service.social.info.LikeInfo;
import com.kascend.music2.api3.service.social.response.LikeResponse;
import com.kascend.music2.api3.service.util.AnalysisZipFileUtil;
import com.kascend.music2.api3.service.util.Constants;
import com.kascend.music2.api3.service.util.ServiceUtils;
import com.kascend.osm.solr.sdk.SolrAlbum;
import com.kascend.osm.solr.sdk.SolrArtist;
import com.kascend.osm.solr.sdk.SolrSong;

public class MetadataServiceHelper extends BaseService {

	private List<String> reglist;// 正则过滤集合
	private static final Logger log = Logger
			.getLogger(MetadataServiceHelper.class);

	MetadataDao metadataDao;
	SocialServiceHelper sosHelper;
	SocialDao socialDao;
	
	JLuceneSearchServer jluceneSearch;
	
	
	public SocialServiceHelper getSosHelper() {
		return sosHelper;
	}

	public void setSosHelper(SocialServiceHelper sosHelper) {
		this.sosHelper = sosHelper;
	}

	public SocialDao getSocialDao() {
		return socialDao;
	}

	public void setSocialDao(SocialDao socialDao) {
		this.socialDao = socialDao;
	}

	public JLuceneSearchServer getJluceneSearch() {
		return jluceneSearch;
	}

	public void setJluceneSearch(JLuceneSearchServer jluceneSearch) {
		this.jluceneSearch = jluceneSearch;
	}

	private String getSearchKeyName(String keyname) {
		if (keyname != null) {
			if(keyname.equals("S.H.E")){
				return keyname;
			}
			try {
				String name = URLDecoder.decode(keyname, "utf-8");
				if (reglist != null) {
					for (String regrule : reglist) {
						name = name.replaceAll(regrule, "");
					}
				}
				if (ServiceUtils.isChinese(name)) {
					name = name.replaceAll("\\s.*?", "");
				}
				keyname = name.trim();

			} catch (Exception e) {
				log.error(e.getMessage());
				if (reglist != null) {
					for (String regrule : reglist) {
						keyname = keyname.replaceAll(regrule, "");
					}
				}
				if (ServiceUtils.isChinese(keyname)) {
					keyname = keyname.replaceAll("\\s.*?", "");
				}
				keyname = keyname.trim();
			}
		}
		return keyname;
	}

	public List<Long> getMTVInfoSongIdList(MusicInfo info) {
		GetSearchInfo getSearchInfo = new GetSearchInfo();
		getSearchInfo.setSong(info.getSong());
		getSearchInfo.setArtist(info.getArtist());
		getSearchInfo.setAlbum(info.getAlbum());
		getSearchInfo.setFilename(info.getFilename());
		return getSearchSongIdList(getSearchInfo);
	}

	public List<Long> getSearchSongIdListInLucene(GetSearchInfo info) {
		List<Long> songIdList = new ArrayList<Long>();
		ServiceUtils.pageCompute(info);
		SolrSong solrSong=new SolrSong();
		solrSong.setStart(new Long(info.getStart()));
		solrSong.setPagecount(new Long(info.getPagecount()));
		solrSong.setSongtitle(info.getSong());
		solrSong.setArtistname(info.getArtist());
		solrSong.setAlbumtitle(info.getAlbum());
		try {
			songIdList=jluceneSearch.searchSong(solrSong);
			if(songIdList.size()==0){
				solrSong.setAlbumtitle(null);
				songIdList=jluceneSearch.searchSong(solrSong);
			}
			if(songIdList.size()==0){
				solrSong.setArtistname(null);
				songIdList=jluceneSearch.searchSong(solrSong);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return songIdList;
	}

	public List<Long> getSearchSongIdListOfGetSongInfo(GetSearchInfo info) {

		List<Long> songIdList = new ArrayList<Long>();
		ServiceUtils.pageCompute(info);
		int count = 100;
		String songTitle =getSearchKeyName(info.getSong());
		String artistName = getSearchKeyName(info.getArtist());
		String albumTitle = getSearchKeyName(info.getAlbum());
		String filename = getSearchKeyName(info.getFilename());
		if (songIdList.size() == 0 && !Validator.isBlank(songTitle)
				&& !Validator.isBlank(artistName)
				&& !Validator.isBlank(albumTitle)) {
			Map map = new HashMap();
			map.put("song", songTitle);
			map.put("artist", artistName);
			map.put("album", albumTitle);
			map.put("pagecount", count);
			songIdList = metadataDao.getSearchSongIdListOfGetSongInfo(map);
		}
		if (songIdList.size() == 0 && !Validator.isBlank(songTitle)
				&& !Validator.isBlank(artistName)) {
			Map map = new HashMap();
			map.put("artist", artistName);
			map.put("song", songTitle);
			map.put("pagecount", count);
			songIdList = metadataDao.getSearchSongIdListOfGetSongInfo(map);
		}
		if (songIdList.size() == 0 && !Validator.isBlank(songTitle)
				&& !Validator.isBlank(albumTitle)) {
			Map map = new HashMap();
			map.put("album", albumTitle);
			map.put("song", songTitle);
			map.put("pagecount", count);
			songIdList = metadataDao.getSearchSongIdListOfGetSongInfo(map);
		}
		if (songIdList.size() == 0 && !Validator.isBlank(filename)
				&& !Validator.isBlank(artistName)
				&& !Validator.isBlank(albumTitle)) {
			Map map = new HashMap();
			map.put("song", filename);
			map.put("artist", artistName);
			map.put("album", albumTitle);
			map.put("pagecount", count);
			songIdList = metadataDao.getSearchSongIdListOfGetSongInfo(map);
		}
		if ((songIdList.size() == 0) && !Validator.isBlank(filename)
				&& !Validator.isBlank(albumTitle)) {
			Map map = new HashMap();
			map.put("song", filename);
			map.put("album", albumTitle);
			map.put("pagecount", count);
			songIdList = metadataDao.getSearchSongIdListOfGetSongInfo(map);
		}
		if ((songIdList.size() == 0)
				&& (!Validator.isBlank(filename) && !Validator
						.isBlank(artistName))) {
			Map map = new HashMap();
			map.put("artist", artistName);
			map.put("song", filename);
			map.put("pagecount", count);
			songIdList = metadataDao.getSearchSongIdListOfGetSongInfo(map);
		}
		if (songIdList.size() == 0) {
			SolrSong solrSong=new SolrSong();
			solrSong.setSongtitle(info.getSong());
			solrSong.setStart(new Long(info.getStart()));
			solrSong.setPagecount(new Long(info.getPagecount()));
			solrSong.setArtistname(info.getArtist());
			solrSong.setAlbumtitle(info.getAlbum());
			try {
				songIdList=jluceneSearch.searchSong(solrSong);
				if(songIdList.size()==0){
					solrSong.setAlbumtitle(null);
					songIdList=jluceneSearch.searchSong(solrSong);
				}
				if(songIdList.size()==0){
					solrSong.setArtistname(null);
					songIdList=jluceneSearch.searchSong(solrSong);
				}
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		}
		return songIdList;

	}
	public List<Long> getSearchSongIdList(GetSearchInfo info) {
		List<Long> songIdList = new ArrayList<Long>();
		ServiceUtils.pageCompute(info);
		int count = 100;
		String songTitle =getSearchKeyName(info.getSong());
		String artistName = getSearchKeyName(info.getArtist());
		String albumTitle = getSearchKeyName(info.getAlbum());
		String filename = getSearchKeyName(info.getFilename());
		if (songIdList.size() == 0 && !Validator.isBlank(songTitle)
				&& !Validator.isBlank(artistName)
				&& !Validator.isBlank(albumTitle)) {
			Map map = new HashMap();
			map.put("song", songTitle);
			map.put("artist", artistName);
			map.put("album", albumTitle);
			map.put("pagecount", count);
			songIdList = metadataDao.getSearchSongIdList(map);
		}
		if (songIdList.size() == 0 && !Validator.isBlank(songTitle)
				&& !Validator.isBlank(artistName)) {
			Map map = new HashMap();
			map.put("artist", artistName);
			map.put("song", songTitle);
			map.put("pagecount", count);
			songIdList = metadataDao.getSearchSongIdList(map);
		}
		if (songIdList.size() == 0 && !Validator.isBlank(songTitle)
				&& !Validator.isBlank(albumTitle)) {
			Map map = new HashMap();
			map.put("album", albumTitle);
			map.put("song", songTitle);
			map.put("pagecount", count);
			songIdList = metadataDao.getSearchSongIdList(map);
		}
		if (songIdList.size() == 0 && !Validator.isBlank(filename)
				&& !Validator.isBlank(artistName)
				&& !Validator.isBlank(albumTitle)) {
			Map map = new HashMap();
			map.put("song", filename);
			map.put("artist", artistName);
			map.put("album", albumTitle);
			map.put("pagecount", count);
			songIdList = metadataDao.getSearchSongIdList(map);
		}
		if ((songIdList.size() == 0) && !Validator.isBlank(filename)
				&& !Validator.isBlank(albumTitle)) {
			Map map = new HashMap();
			map.put("song", filename);
			map.put("album", albumTitle);
			map.put("pagecount", count);
			songIdList = metadataDao.getSearchSongIdList(map);
		}
		if ((songIdList.size() == 0)
				&& (!Validator.isBlank(filename)&& !Validator
						.isBlank(artistName))) {
			Map map = new HashMap();
			map.put("artist", artistName);
			map.put("song", filename);
			map.put("pagecount", count);
			songIdList = metadataDao.getSearchSongIdList(map);
		}
		if (songIdList.size() == 0) {
			SolrSong solrSong=new SolrSong();
			solrSong.setSongtitle(info.getSong());
			solrSong.setStart(new Long(info.getStart()));
			solrSong.setPagecount(new Long(info.getPagecount()));
			solrSong.setArtistname(info.getArtist());
			solrSong.setAlbumtitle(info.getAlbum());
			try {
				songIdList=jluceneSearch.searchSong(solrSong);
				if(songIdList.size()==0){
					solrSong.setAlbumtitle(null);
					songIdList=jluceneSearch.searchSong(solrSong);
				}
				if(songIdList.size()==0){
					solrSong.setArtistname(null);
					songIdList=jluceneSearch.searchSong(solrSong);
				}
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		}
		return songIdList;
	}
	//status=0,1,6
	public List<Long> getSearchSongIdListWithStatus(GetSearchInfo info) {
		List<Long> songIdList = new ArrayList<Long>();
		ServiceUtils.pageCompute(info);
		int count = 100;
		String songTitle =getSearchKeyName(info.getSong());
		String artistName = getSearchKeyName(info.getArtist());
		String albumTitle = getSearchKeyName(info.getAlbum());
		String filename = getSearchKeyName(info.getFilename());
		if (songIdList.size() == 0 && !Validator.isBlank(songTitle)
				&& !Validator.isBlank(artistName)
				&& !Validator.isBlank(albumTitle)) {
			Map map = new HashMap();
			map.put("song", songTitle);
			map.put("artist", artistName);
			map.put("album", albumTitle);
			map.put("pagecount", count);
			songIdList = metadataDao.getSearchSongIdListWithStatus(map);
		}
		if (songIdList.size() == 0 && !Validator.isBlank(songTitle)
				&& !Validator.isBlank(artistName)) {
			Map map = new HashMap();
			map.put("artist", artistName);
			map.put("song", songTitle);
			map.put("pagecount", count);
			songIdList = metadataDao.getSearchSongIdListWithStatus(map);
		}
		if (songIdList.size() == 0 && !Validator.isBlank(songTitle)
				&& !Validator.isBlank(albumTitle)) {
			Map map = new HashMap();
			map.put("album", albumTitle);
			map.put("song", songTitle);
			map.put("pagecount", count);
			songIdList = metadataDao.getSearchSongIdListWithStatus(map);
		}
		if (songIdList.size() == 0 && !Validator.isBlank(filename)
				&& !Validator.isBlank(artistName)
				&& !Validator.isBlank(albumTitle)) {
			Map map = new HashMap();
			map.put("song", filename);
			map.put("artist", artistName);
			map.put("album", albumTitle);
			map.put("pagecount", count);
			songIdList = metadataDao.getSearchSongIdListWithStatus(map);
		}
		if ((songIdList.size() == 0) && !Validator.isBlank(filename)
				&& !Validator.isBlank(albumTitle)) {
			Map map = new HashMap();
			map.put("song", filename);
			map.put("album", albumTitle);
			map.put("pagecount", count);
			songIdList = metadataDao.getSearchSongIdListWithStatus(map);
		}
		if ((songIdList.size() == 0)
				&& (!Validator.isBlank(filename)&& !Validator
						.isBlank(artistName))) {
			Map map = new HashMap();
			map.put("artist", artistName);
			map.put("song", filename);
			map.put("pagecount", count);
			songIdList = metadataDao.getSearchSongIdListWithStatus(map);
		}
		if (songIdList.size() == 0) {
			SolrSong solrSong=new SolrSong();
			solrSong.setSongtitle(info.getSong());
			solrSong.setStart(new Long(info.getStart()));
			solrSong.setPagecount(new Long(info.getPagecount()));
			solrSong.setArtistname(info.getArtist());
			solrSong.setAlbumtitle(info.getAlbum());
			try {
				songIdList=jluceneSearch.searchSong(solrSong);
				if(songIdList.size()==0){
					solrSong.setAlbumtitle(null);
					songIdList=jluceneSearch.searchSong(solrSong);
				}
				if(songIdList.size()==0){
					solrSong.setArtistname(null);
					songIdList=jluceneSearch.searchSong(solrSong);
					songIdList=getCorrectResultOfSong(songIdList,info.getArtist());
				}
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		}
		return songIdList;
	}

	public List<Long> getCorrectResultOfSong(List<Long> songIdList,String artistName){
		List<Long> idList=new ArrayList<Long>();
		if(songIdList.isEmpty())return idList;
		if(Validator.isBlank(artistName))return songIdList;
		long songId=songIdList.get(0);
		int resultCount=metadataDao.checkSongBySongIdAndArtistName(songId, artistName);
		if(resultCount >0){
			idList.add(songId);
		}
		return idList;
	}

	/**
	 * 3.1 searchsongs 返回回给客户端含有关键字的songsList.
	 * 
	 */
	public New_SongListResponse searchsongs(SearchSongInfo info) {
		if(Validator.isBlank(info.getKeyname())){
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		New_SongListResponse response = new New_SongListResponse();
		New_SongListEntry result = new New_SongListEntry();
		response.setSongslist(result);
		ServiceUtils.pageCompute(info);
		String keyName = getSearchKeyName(info.getKeyname());
		if (!Validator.isBlank(keyName)) {
			if (keyName.length() == 1 && keyName.matches("\\w{1}")) {
				return response;
			}
			List<Long> songIdList=new ArrayList<Long>();
			try {
				songIdList=jluceneSearch.searchKeyWord(keyName, info.getStart(), info.getPagecount());
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
			if (songIdList.size() > 0) {
				GetSimilarSongInfo getSimilarSongInfo = new GetSimilarSongInfo();
				getSimilarSongInfo.setAlbumartflag(info.getAlbumartflag());
				getSimilarSongInfo.setSongIdList(songIdList);
				if(info.getAppkey().equals(Constants.APPKEY_V2)){
					getSimilarSongInfo.setSongFileFlag(1);//2.0用户显示已上传的歌曲
				}
				List<Song> songList = metadataDao
						.getMaxSongListBySongIdList(getSimilarSongInfo);
				if (songList.size() > 0) {
					songList = ServiceUtils.removeIterativeOnSongList(songList);
					songList=ServiceUtils.sortSongRandomList(songIdList, songList);
					if (info.getAllcountflag() == 1) {
						int allCount = songList.size();
						result.setAllcount(allCount);
					}
					List<New_SongEntry> listEntry = new ArrayList<New_SongEntry>();
					for (int i = 0; i < songList.size(); i++) {
						New_SongEntry songEntry = new New_SongEntry(
								songList.get(i));
						listEntry.add(i, songEntry);
					}
//					listEntry = ServiceUtils.sortNewSongListEntry(listEntry,
//							keyName);
					// by yejz
//					listEntry = ServiceUtils.outSidePaging(listEntry,
//							info.getPagecount(), info.getPage());
					result.setSong(listEntry);
					result.setSongscount(listEntry.size());
				}

			}
		}
		return response;
	}

	public New_SongListResponse searchsongsforlucene(SearchSongInfo info) {
		New_SongListResponse response = new New_SongListResponse();
		New_SongListEntry result = new New_SongListEntry();
		response.setSongslist(result);
		ServiceUtils.pageCompute(info);
		String keyName = this.getSearchKeyName(info.getKeyname());
		if (!Validator.isBlank(keyName)) {
			if (keyName.length() == 1 && keyName.matches("\\w{1}")) {
				return response;
			}
			List<Long> songIdList;
			try {
				songIdList = jluceneSearch.searchKeyWord(keyName, info.getStart(), info.getPagecount());
		
			if (songIdList.size() > 0) {
				GetSimilarSongInfo getSimilarSongInfo = new GetSimilarSongInfo();
				getSimilarSongInfo.setAlbumartflag(info.getAlbumartflag());
				getSimilarSongInfo.setSongIdList(songIdList);
				List<Song> songList = metadataDao
						.getMaxSongListBySongIdList(getSimilarSongInfo);
				if (songList.size() > 0) {
					songList = ServiceUtils.removeIterativeOnSongList(songList);
					songList=ServiceUtils.sortSongRandomList(songIdList, songList);
					if (info.getAllcountflag() == 1) {
						int allCount = songList.size();
						result.setAllcount(allCount);
					}

					List<New_SongEntry> listEntry = new ArrayList<New_SongEntry>();
					for (int i = 0; i < songList.size(); i++) {
						New_SongEntry songEntry = new New_SongEntry(
								songList.get(i));
						listEntry.add(i, songEntry);
					}
//					listEntry = ServiceUtils.sortNewSongListEntry(listEntry,
//							keyName);
					// by yejz
//					listEntry = ServiceUtils.outSidePaging(listEntry,
//							info.getPagecount(), info.getPage());
					result.setSong(listEntry);
					result.setSongscount(listEntry.size());
				}

			 }
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		}
		return response;
	}

	/**
	 * 3.2 searchalbums
	 * */
	public New_AlbumResponse searchalbums(SearchAlbumInfo info) {
		if(Validator.isBlank(info.getKeyname())){
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		New_AlbumResponse response = new New_AlbumResponse();
		New_AlbumListEntry result = new New_AlbumListEntry();
		response.setAlbumslist(result);
		ServiceUtils.pageCompute(info);
		String keyword = this.getSearchKeyName(info.getKeyname());
		if (!Validator.isBlank(keyword)) {
			if (keyword.length() == 1 && keyword.matches("\\w{1}")) {
				return response;
			}
			String keyName = "%" + keyword + "%";
			info.setKeyname(keyName);
			List<Album> albumList = metadataDao
					.getSearchAlbumInfoByKeyName(info);
			if (albumList.size() > 0) {
				List<New_AlbumEntry> albumEntryList = new ArrayList<New_AlbumEntry>();
				for (int i = 0; i < albumList.size(); i++) {
					New_AlbumEntry albumEntry = new New_AlbumEntry(
							albumList.get(i));
					albumEntryList.add(i, albumEntry);
				}
				albumEntryList = ServiceUtils.sortNewAlbumListEntry(
						albumEntryList, keyword);// 排序
				result.setAlbum(albumEntryList);
				result.setAlbumscount(albumEntryList.size());
				if (info.getAllcountflag() == 1) {
					int allCount = metadataDao
							.getSearchAlbumInfoByKeyNameAllCount(info);
					result.setAllcount(allCount);
				}
			}
			info.setKeyname(keyword);
		}
		return response;
	}

	/**
	 * 3.3 searchartists
	 * */

	public New_ArtistListResponse searchartists(SearchArtistInfo info) {
		if(Validator.isBlank(info.getKeyname())){
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		New_ArtistListResponse response = new New_ArtistListResponse();
		New_ArtistListEntry result = new New_ArtistListEntry();
		response.setArtistslist(result);
		ServiceUtils.pageCompute(info);
		String keyword = this.getSearchKeyName(info.getKeyname());
		if (!Validator.isBlank(keyword)) {
			if (keyword.length() == 1 && keyword.matches("\\w{1}")) {
				return response;
			}
			String keyName = "%" + keyword + "%";
			info.setKeyname(keyName);
			List<Artist> artistList = metadataDao
					.getSearchArtistInfoByKeyName(info);
			if (artistList.size() > 0) {
				List<New_ArtistEntry> artistListEntry = new ArrayList<New_ArtistEntry>();
				for (int i = 0; i < artistList.size(); i++) {
					New_ArtistEntry artistEntry = new New_ArtistEntry(
							artistList.get(i));
					artistListEntry.add(i, artistEntry);
				}
				artistListEntry = ServiceUtils.sortNewArtistListEntry(
						artistListEntry, keyword);// 排序
				if (info.getAllcountflag() == 1) {
					int allcount = metadataDao
							.getSearchArtistInfoArtistsCount(info);// 进行查询
					result.setAllcount(allcount);
				}
				result.setArtistscount(artistListEntry.size());
				result.setArtist(artistListEntry);
			} else {
				result.setArtistscount(0);
			}
			info.setKeyname(keyword);
		}
		return response;
	}

	/**
	 * 3.4 lookupsongs
	 * */
	public New_SongListResponse lookupsongs(LookupSongInfo info) {
		New_SongListResponse response = new New_SongListResponse();
		New_SongListEntry result = new New_SongListEntry();
		response.setSongslist(result);
		ServiceUtils.pageCompute(info);
		List<Long> songIdList = this.getSearchSongIdList(info);
		if (songIdList.size() > 0) {
			GetSimilarSongInfo getSimilarSongInfo = new GetSimilarSongInfo();
			getSimilarSongInfo.setPagecount(info.getPagecount());
			getSimilarSongInfo.setStart(info.getStart());
			getSimilarSongInfo.setAlbumartflag(info.getAlbumartflag());
			getSimilarSongInfo.setSongIdList(songIdList);
			List<Song> songList = metadataDao
					.getCommonSongListBySongIdList(getSimilarSongInfo);
			if (songList.size() > 0) {
				songList=ServiceUtils.sortSongRandomList(songIdList, songList);
				List<New_SongEntry> songListEntry = new ArrayList<New_SongEntry>();
				for (int i = 0; i < songList.size(); i++) {
					New_SongEntry entry = new New_SongEntry(songList.get(i));
					songListEntry.add(i, entry);
				}
				result.setSong(songListEntry);
				if (info.getAllcountflag() == 1) {
					int allCount = metadataDao
							.getCommonSongListBySongIdListAllCount(getSimilarSongInfo);
					result.setAllcount(allCount);
				}
			}
			result.setSongscount(songList.size());
		}
		return response;
	}

	/**
	 * 接口3.5 getsongkeywords
	 * */
	public New_KeyWordListResponse getsongkeywords(GetSearchKeywordInfo info) {
		New_KeyWordListResponse response = new New_KeyWordListResponse();
		ServiceUtils.pageCompute(info);
		List<String> keyWordList = metadataDao.getSearchKeyWordList(info);
		New_KeyWordListEntry result = new New_KeyWordListEntry();
		result.setKeyword(keyWordList);
		result.setKeywordscount(keyWordList.size());
		response.setKeywordslist(result);
		return response;
	}

	public New_KeyWordListResponse getsearchkeywords(GetSearchKeywordInfo info) {
		return getsongkeywords(info);
	}

	/**
	 * 接口4.1 ListBillboards 获取榜单
	 * */
	public New_BillboardListResponse listbillboards(ListBillboardInfo info) {
		New_BillboardListResponse response = new New_BillboardListResponse();
		if ((info.getBillboardgroupid() == 0)
				&& (Validator.isBlank(info.getBillboardgroupcode()))) {
			info.setBillboardgroupcode(Constants.DEFAULT_BILLBOARD_GROUPCODE);
		}
		ServiceUtils.pageCompute(info);
		New_BillboardListEntry result = new New_BillboardListEntry();
		List<Billboard> billboardGroupList = new ArrayList<Billboard>();
		if ((info.getBillboardgroupid() == 0)
				&& (!Validator.isBlank(info
						.getBillboardgroupcode()))) {
			int parentGroupId = metadataDao.getBillboardGrouIdByGroupCode(info);
			if (parentGroupId == 0) {
				info.setBillboardgroupcode(Constants.DEFAULT_BILLBOARD_GROUPCODE);
				parentGroupId = metadataDao.getBillboardGrouIdByGroupCode(info);
			}
			info.setBillboardgroupid(parentGroupId);
			billboardGroupList = metadataDao.getBillboardGroupList(info);
		}
		// song
		info.setBillboardtype(Constants.TOP_TYPE_SONG);
		List<Billboard> songBillboardList = metadataDao.getBillboardList(info);
		// album
		info.setBillboardtype(Constants.TAB_TYPE_ALBUM);
		List<Billboard> albumBillboardList = metadataDao.getBillboardList(info);
		// artist
		info.setBillboardtype(Constants.TAB_TYPE_ARTIST);
		List<Billboard> artistBillboardList = metadataDao
				.getBillboardList(info);
		// mtv
		info.setBillboardtype(Constants.TAB_TYPE_MV);
		List<Billboard> mtvBillboardList = metadataDao.getBillboardList(info);
		result.setBillboardscount(songBillboardList.size()
				+ albumBillboardList.size() + artistBillboardList.size()
				+ mtvBillboardList.size() + billboardGroupList.size());
		List<Billboard> billboardList = ServiceUtils.sortMultiListEntry(
				songBillboardList, albumBillboardList, artistBillboardList,
				mtvBillboardList);
		List<New_BillboardEntry> billboardEntryList = new ArrayList<New_BillboardEntry>();
		if (billboardList.size() > 0) {
			for (int i = 0; i < billboardList.size(); i++) {
				Billboard billboard = billboardList.get(i);
				New_BillboardEntry billboardEntry = new New_BillboardEntry(
						billboard);
				billboardEntryList.add(i, billboardEntry);
			}
		}
		List<New_BillboardEntry> billboardGroupEntryList = new ArrayList<New_BillboardEntry>();
		if (billboardGroupList.size() > 0) {
			for (int i = 0; i < billboardGroupList.size(); i++) {

				Billboard billboardGroup = billboardGroupList.get(i);
				int totalSize = metadataDao
						.getBillboardGroupSubBillboardCounts(billboardGroup
								.getBillboardId());
				billboardGroup.setBillboardSize(totalSize);
				billboardGroup.setBillboardSource("billboardgroup");
				billboardGroup.setBillboardType(3);
				New_BillboardEntry billboardGroupEntry = new New_BillboardEntry(
						billboardGroup);
				billboardGroupEntryList.add(i, billboardGroupEntry);
			}
		}

		List<New_BillboardEntry> bentry = ServiceUtils.sortListEntry(
				billboardEntryList, billboardGroupEntryList);

		result.setBillboard(bentry);
		response.setBillboardslist(result);
		return response;
	}

	/**
	 * 接口4.2 获得歌曲榜单
	 * */
	public New_SongListResponse topsongs(TopSongInfo info) {
		if (info.getBillboardid() == 0 && info.getSubbillboardid() == 0) {
			throw new MusicRcException(MusicRcException.ERRORBILLBOARDID);
		}
		New_SongListResponse response = new New_SongListResponse();
		New_SongListEntry result = new New_SongListEntry();
		response.setSongslist(result);
		int songcount = 0;
		info.setType(Constants.TOP_TYPE_SONG);
		long billboardId = 0L;
		long subBillboardId = 0L;
		if (info.getSubbillboardid() > 0) {
			subBillboardId = info.getSubbillboardid();
			billboardId = metadataDao.getBillboardIdBySubBillboardId(info);
		} else {
			billboardId = info.getBillboardid();
			subBillboardId = metadataDao.getSubBillboardIdByBillboardId(info);
		}
		info.setSubbillboardid(subBillboardId);
		info.setBillboardid(billboardId);
		// 查询榜期列表
		List<SubBillboard> subBillboardList = metadataDao
				.getSubBillboardListByBillboardId(info);
		New_SubBillboardListEntry subResult = new New_SubBillboardListEntry();
		if (subBillboardList.size() > 0) {
			List<New_SubBillboardEntry> subBillboardListEntry = new ArrayList<New_SubBillboardEntry>();
			for (int i = 0; i < subBillboardList.size(); i++) {
				New_SubBillboardEntry entry = new New_SubBillboardEntry(
						subBillboardList.get(i));
				subBillboardListEntry.add(i, entry);
			}
			subResult.setSubbillboard(subBillboardListEntry);

		}
		// 查询已发布榜期
		SubBillboard subBillboard = metadataDao
				.getSubBillboardBySubBillboardId(info);
		if (subBillboard != null) {
			int songPicStatus = subBillboard.getHasListPic();
			if (songPicStatus == 1) {
				info.setAlbumartflag(0);
			}
			songcount = metadataDao.getSubBillboardSongCount(subBillboard
					.getSubBillboardId());

			ServiceUtils.pageCompute(info);

			if (info.getStart() >= subBillboard.getSize()
					|| info.getStart() >= songcount) {
				return response;
			} else if ((info.getStart() + info.getPagecount()) >= subBillboard
					.getSize()) {
				int pagecount = subBillboard.getSize() - info.getStart();
				info.setPagecount(pagecount);
			}

			info.setSubbillboardid(subBillboard.getSubBillboardId());
			List<Song> songList = metadataDao.getSubBillboardSongList(info);

			if (songList.size() > 0) {
				result.setSongscount(songList.size());
				List<New_SongEntry> songEntryList = new ArrayList<New_SongEntry>();
				for (int i = 0; i < songList.size(); i++) {
					New_SongEntry songEntry = new New_SongEntry(songList.get(i));
					songEntryList.add(i, songEntry);
				}
				result.setSong(songEntryList);
				result.setSubbillboardlist(subResult);
				result.setBillboardid(billboardId);
				result.setSubbillboardid(subBillboardId);
				result.setBillboardtitle(subBillboard.getBillboardTitle());
				result.setSubbillboardtitle(subBillboard.getSubBillboardTitle());
				result.setPicstatus(subBillboard.getHasListPic());
				result.setUpdatetime(subBillboard.getUpdateTime().getTime());
				if (songcount < subBillboard.getSize()) {
					result.setTotalsize(songcount);
				} else {
					result.setTotalsize(subBillboard.getSize());
				}
			}
		}

		return response;
	}

	/**
	 * 接口4.2 获得专辑榜单
	 * */
	public New_AlbumResponse topalbums(TopAlbumInfo info) {
		New_AlbumResponse response = new New_AlbumResponse();
		if (info.getBillboardid() == 0 && info.getSubbillboardid() == 0) {
			throw new MusicRcException(MusicRcException.ERRORBILLBOARDID);
		}
		info.setType(Constants.TAB_TYPE_ALBUM);
		New_AlbumListEntry result = new New_AlbumListEntry();
		response.setAlbumslist(result);
		int albumcount = 0;
		info.setType(Constants.TAB_TYPE_ALBUM);
		long billboardId = 0L;
		long subBillboardId = 0L;
		if (info.getSubbillboardid() > 0) {
			subBillboardId = info.getSubbillboardid();
			billboardId = metadataDao.getBillboardIdBySubBillboardId(info);
		} else {
			billboardId = info.getBillboardid();
			subBillboardId = metadataDao.getSubBillboardIdByBillboardId(info);
		}
		info.setSubbillboardid(subBillboardId);
		info.setBillboardid(billboardId);
		// 查询榜期列表
		List<SubBillboard> subBillboardList = metadataDao
				.getSubBillboardListByBillboardId(info);
		New_SubBillboardListEntry subResult = new New_SubBillboardListEntry();
		if (subBillboardList.size() > 0) {
			List<New_SubBillboardEntry> subBillboardListEntry = new ArrayList<New_SubBillboardEntry>();
			for (int i = 0; i < subBillboardList.size(); i++) {
				New_SubBillboardEntry entry = new New_SubBillboardEntry(
						subBillboardList.get(i));
				subBillboardListEntry.add(i, entry);
			}
			subResult.setSubbillboard(subBillboardListEntry);

		}
		// 查询已发布榜期
		SubBillboard subBillboard = metadataDao
				.getSubBillboardBySubBillboardId(info);
		if (subBillboard != null) {
			albumcount = metadataDao.getSubBillboardAlbumCount(subBillboard
					.getSubBillboardId());
			ServiceUtils.pageCompute(info);
			if (info.getStart() >= subBillboard.getSize()
					|| info.getStart() >= albumcount) {
				return response;
			} else if ((info.getStart() + info.getPagecount()) >= subBillboard
					.getSize()) {
				int pagecount = subBillboard.getSize() - info.getStart();
				info.setPagecount(pagecount);
			}
			info.setSubbillboardid(subBillboard.getSubBillboardId());
			List<Album> albumList = metadataDao.getSubBillboardAlbumList(info);
			if (albumList.size() > 0) {
				result.setAlbumscount(albumList.size());
				List<New_AlbumEntry> albumEntryList = new ArrayList<New_AlbumEntry>();
				for (int i = 0; i < albumList.size(); i++) {
					New_AlbumEntry albumEntry = new New_AlbumEntry(
							albumList.get(i));
					albumEntryList.add(i, albumEntry);
				}
				result.setAlbum(albumEntryList);
				result.setSubbillboardlist(subResult);
				result.setBillboardid(billboardId);
				result.setSubbillboardid(subBillboardId);
				result.setBillboardtitle(subBillboard.getBillboardTitle());
				result.setSubbillboardtitle(subBillboard.getSubBillboardTitle());
				result.setUpdatetime(subBillboard.getUpdateTime().getTime());
				if (albumcount < subBillboard.getSize()) {
					result.setTotalsize(albumcount);
				} else {
					result.setTotalsize(subBillboard.getSize());
				}
			}
		}
		return response;
	}

	public New_ArtistListResponse topartists(TopArtistInfo info) {
		if (info.getBillboardid() == 0 && info.getSubbillboardid() == 0) {
			throw new MusicRcException(MusicRcException.ERRORBILLBOARDID);
		}
		New_ArtistListResponse response = new New_ArtistListResponse();
		New_ArtistListEntry result = new New_ArtistListEntry();
		response.setArtistslist(result);
		int artistcount = 0;
		info.setType(Constants.TAB_TYPE_ARTIST);
		long billboardId = 0L;
		long subBillboardId = 0L;
		if (info.getSubbillboardid() > 0) {
			subBillboardId = info.getSubbillboardid();
			billboardId = metadataDao.getBillboardIdBySubBillboardId(info);
		} else {
			billboardId = info.getBillboardid();
			subBillboardId = metadataDao.getSubBillboardIdByBillboardId(info);
		}
		info.setSubbillboardid(subBillboardId);
		info.setBillboardid(billboardId);
		// 查询榜期列表
		List<SubBillboard> subBillboardList = metadataDao
				.getSubBillboardListByBillboardId(info);
		New_SubBillboardListEntry subResult = new New_SubBillboardListEntry();
		if (subBillboardList.size() > 0) {
			List<New_SubBillboardEntry> subBillboardListEntry = new ArrayList<New_SubBillboardEntry>();
			for (int i = 0; i < subBillboardList.size(); i++) {
				New_SubBillboardEntry entry = new New_SubBillboardEntry(
						subBillboardList.get(i));
				subBillboardListEntry.add(i, entry);
			}
			subResult.setSubbillboard(subBillboardListEntry);
		}
		// 查询已发布榜期
		SubBillboard subBillboard = metadataDao
				.getSubBillboardBySubBillboardId(info);
		if (subBillboard != null) {
			artistcount = metadataDao.getSubBillboardArtistCount(subBillboard
					.getSubBillboardId());

			ServiceUtils.pageCompute(info);

			if (info.getStart() >= subBillboard.getSize()
					|| info.getStart() >= artistcount) {
				return response;
			} else if ((info.getStart() + info.getPagecount()) >= subBillboard
					.getSize()) {
				int pagecount = subBillboard.getSize() - info.getStart();
				info.setPagecount(pagecount);
			}
			info.setSubbillboardid(subBillboard.getSubBillboardId());
			List<Artist> artistList = metadataDao
					.getSubBillboardArtistList(info);
			if (artistList.size() > 0) {
				result.setArtistscount(artistList.size());
				List<New_ArtistEntry> artistEntryList = new ArrayList<New_ArtistEntry>();
				for (int i = 0; i < artistList.size(); i++) {
					New_ArtistEntry artistEntry = new New_ArtistEntry(
							artistList.get(i));
					artistEntryList.add(i, artistEntry);
				}
				result.setArtist(artistEntryList);
				result.setSubbillboardlist(subResult);
				result.setBillboardid(billboardId);
				result.setSubbillboardid(subBillboardId);
				result.setBillboardtitle(subBillboard.getBillboardTitle());
				result.setSubbillboardtitle(subBillboard.getSubBillboardTitle());
				result.setUpdatetime(subBillboard.getUpdateTime().getTime());
				if (artistcount < subBillboard.getSize()) {
					result.setTotalsize(artistcount);
				} else {
					result.setTotalsize(subBillboard.getSize());
				}
			}
		}
		return response;
	}

	@Deprecated
	public New_MtvResponse topmtvs(TopMvInfo info) {
 		New_MtvResponse response=new New_MtvResponse();
		New_MtvEntryList result=new New_MtvEntryList();
		List<New_MtvEntry> mtvEntryList=new ArrayList<New_MtvEntry>();
		result.setMtv(mtvEntryList);
		response.setMtvlist(result);
		New_MvResponse mvResponse=this.topmvs(info);
		New_MvListEntry mvListEntry=mvResponse.getMvlist();
		List<New_MvEntry> mvEntryList=mvListEntry.getMv();
		for(int i=0;i<mvEntryList.size();i++){
			New_MvEntry mvEntry=mvEntryList.get(i);
			SongMv songMv=this.mv2mtvEntryCopy(mvEntry);
			New_MtvEntry mtvEntry=new New_MtvEntry(songMv);
			mtvEntryList.add(i, mtvEntry);
		}
		result.setTotalsize(mvListEntry.getTotalsize());
		result.setSubbillboardlist(mvListEntry.getSubbillboardlist());
		result.setBillboardtitle(mvListEntry.getBillboardtitle());
		result.setBillboardid(mvListEntry.getBillboardid());
		result.setSubbillboardid(mvListEntry.getSubbillboardid());
		result.setSubbillboardtitle(mvListEntry.getSubbillboardtitle());
		result.setPicstatus(mvListEntry.getPicstatus());
		result.setUpdatetime(mvListEntry.getUpdatetime());
		return response;
	}
	public New_MvResponse topmvs(TopMvInfo info) {
		if (info.getBillboardid() == 0 && info.getSubbillboardid() == 0) {
			throw new MusicRcException(MusicRcException.ERRORBILLBOARDID);
		}
		New_MvResponse response = new New_MvResponse();
		List<New_MvEntry> mvEntryList = new ArrayList<New_MvEntry>();
		New_MvListEntry result = new New_MvListEntry();
		result.setMv(mvEntryList);
		response.setMvlist(result);
		ServiceUtils.pageCompute(info);
		info.setType(Constants.TAB_TYPE_MV);
		long billboardId = 0L;
		long subBillboardId = 0L;
		if (info.getSubbillboardid() > 0) {
			subBillboardId = info.getSubbillboardid();
			billboardId = metadataDao.getBillboardIdBySubBillboardId(info);
		} else {
			billboardId = info.getBillboardid();
			subBillboardId = metadataDao.getSubBillboardIdByBillboardId(info);
		}
		info.setSubbillboardid(subBillboardId);
		info.setBillboardid(billboardId);
		// 查询榜期列表
		List<SubBillboard> subBillboardList = metadataDao
				.getSubBillboardListByBillboardId(info);
		New_SubBillboardListEntry subResult = new New_SubBillboardListEntry();
		if (subBillboardList.size() > 0) {
			List<New_SubBillboardEntry> subBillboardListEntry = new ArrayList<New_SubBillboardEntry>();
			for (int i = 0; i < subBillboardList.size(); i++) {
				New_SubBillboardEntry entry = new New_SubBillboardEntry(
						subBillboardList.get(i));
				subBillboardListEntry.add(i, entry);
			}
			subResult.setSubbillboard(subBillboardListEntry);

		}
		// 查询已发布榜期
		SubBillboard subBillboard = metadataDao
				.getSubBillboardBySubBillboardIdOfMv(info);
		int mvcount = 0;
		if (subBillboard != null) {
			mvcount = metadataDao.getTopMvCounts(subBillboard
					.getSubBillboardId());
			info.setSubbillboardid(subBillboard.getSubBillboardId());
			// 页码计算规则
			if (info.getStart() >= subBillboard.getSize()
					|| info.getStart() >= mvcount) {
				return response;
			} else if ((info.getStart() + info.getPagecount()) >= subBillboard
					.getSize()) {
				int pagecount = subBillboard.getSize() - info.getStart();
				info.setPagecount(pagecount);
			}
			List<SongMv> songMvList = metadataDao.topMvList(info);
			if (songMvList.size() > 0) {
				for (int i = 0; i < songMvList.size(); i++) {
					New_MvEntry entry = new New_MvEntry(songMvList.get(i));
					mvEntryList.add(i, entry);
				}
			}
			if (mvcount < subBillboard.getSize()) {
				result.setTotalsize(mvcount);
			} else {
				result.setTotalsize(subBillboard.getSize());
			}
			result.setSubbillboardlist(subResult);
			result.setBillboardtitle(subBillboard.getBillboardTitle());
			result.setBillboardid(billboardId);
			result.setSubbillboardid(subBillboardId);
			result.setSubbillboardtitle(subBillboard.getSubBillboardTitle());
			result.setPicstatus(subBillboard.getHasListPic());
			result.setUpdatetime(subBillboard.getUpdateTime().getTime());
		} else {
			result.setTotalsize(0);
		}
		return response;
	}

	/**
	 * 接口6.1 getartistinfo
	 * */
	public New_ArtistInfoResponse getartistinfo(GetArtistInfo info) {
		New_ArtistInfoResponse response = new New_ArtistInfoResponse();
		if ((info.getArtistid() <= 0)
				&& Validator.isBlank(info.getArtist())) {
			throw new MusicRcException(MusicRcException.ERRORARTIST);
		}
		Artist artist = null;
		if (info.getArtistid() > 0) {
			artist = metadataDao.getArtistInfoByArtistIdOrName(
					info.getArtistid(), null);
		} else {
			artist = metadataDao.getArtistInfoByArtistIdOrName(0l,
					info.getArtist());
		}

		if (artist != null) {
			artist.setArtistThumbnail(null);// 这里返回list，遂单个图片不用
			New_ArtistEntry result = new New_ArtistEntry(artist);
			New_ArtistartEntry artistArtEntry = new New_ArtistartEntry();
			
			List<String> artistImageList =getArtistPicsByArtistId(artist.getArtistId(),info.getArtistartsize());
			artistArtEntry.setArtistart(artistImageList);
			artistArtEntry.setArtistartnum(artistImageList.size());
			result.setArtistartlist(artistArtEntry);
			response.setArtist(result);
		} else {
			response.setMessage("NOT FOUND");
		}
		return response;
	}

	/**
	 * 接口6.2 getartistart
	 * */
	public New_ArtistInfoResponse getartistart(GetArtistInfo info) {
		New_ArtistInfoResponse response = new New_ArtistInfoResponse();
		if ((info.getArtistid() <= 0)
				&& Validator.isBlank(info.getArtist())) {
			throw new MusicRcException(MusicRcException.ERRORARTIST);
		}
		Artist artist = null;
		if (info.getArtistid() > 0) {
			artist = metadataDao.getArtistInfoByArtistIdOrName(
					info.getArtistid(), null);
		} else {
			artist = metadataDao.getArtistInfoByArtistIdOrName(0l,
					info.getArtist());
		}

		if (artist != null) {
			artist.setArtistThumbnail(null);// 这里返回list，遂单个图片不用
			New_ArtistEntry result = new New_ArtistEntry(artist);
			New_ArtistartEntry artistArtEntry = new New_ArtistartEntry();
			
			List<String> artistImageList =getArtistPicsByArtistIdAndSize(artist.getArtistId(),info.getArtistartsize());
			artistArtEntry.setArtistart(artistImageList);
			artistArtEntry.setArtistartnum(artistImageList.size());
			result.setArtistartlist(artistArtEntry);
			response.setArtist(result);
		} else {
			response.setMessage("NOT FOUND");
		}
		return response;
	}

	/**
	 * 6.3 getalbumsofartist 获得歌手专辑
	 * */
	public New_AlbumResponse getalbumsofartist(GetAlbumsOfArtistInfo info) {
		New_AlbumResponse response = new New_AlbumResponse();
		New_AlbumListEntry result = new New_AlbumListEntry();
		response.setAlbumslist(result);
		if (ServiceUtils.validateLongEquals(info.getArtistid())
				&& Validator.isBlank(info.getArtist())) {
			throw new MusicRcException(MusicRcException.ERRORARTIST);
		}
		ServiceUtils.pageCompute(info);
		Artist artist = null;
		if (info.getArtistid() > 0) {
			artist = metadataDao.getArtistInfoByArtistIdOrName(
					info.getArtistid(), null);
		} else {
			artist = metadataDao.getArtistInfoByArtistIdOrName(0l,
					info.getArtist());
		}
		if (artist != null) {
			List<New_AlbumEntry> albumListEntry = new ArrayList<New_AlbumEntry>();
			info.setArtistid(artist.getArtistId());
			List<Album> albumList = metadataDao.getAlbumListByArtistId(info);
			if (albumList.size() > 0) {
				for (int i = 0; i < albumList.size(); i++) {
					New_AlbumEntry entry = new New_AlbumEntry(albumList.get(i));
					albumListEntry.add(i, entry);
				}
				result.setAlbum(albumListEntry);
				result.setAlbumscount(albumList.size());
			}
			if (info.getAllcountflag() == 1) {
				int allCount = metadataDao.getAlbumListByArtistIdAllCount(artist.getArtistId());
				result.setAllcount(allCount);
			}
		}
		return response;
	}

	/**
	 * 6.4 getsongsofalbum 获得专辑下的歌曲
	 * */
	public New_SongListResponse getsongsofalbum(GetSongsOfAlbumInfo info) {
		New_SongListResponse response = new New_SongListResponse();
		New_SongListEntry result = new New_SongListEntry();
		ServiceUtils.pageCompute(info);
		response.setSongslist(result);
		Album album = null;
		if (info.getAlbumid() > 0) {
			album = metadataDao.getAlbumInfoById(info.getAlbumid());
		} else {
			if (Validator.isBlank(info.getAlbum())) {
				throw new MusicRcException(MusicRcException.ERRORALBUM);
			}
			if (Validator.isBlank(info.getArtist())) {
				throw new MusicRcException(MusicRcException.ERRORARTIST);
			}
			GetAlbumInfo getAlbumInfo = new GetAlbumInfo();
			getAlbumInfo.setAlbumartflag(info.getAlbumartflag());
			getAlbumInfo.setAlbum(info.getAlbum());
			getAlbumInfo.setArtist(info.getArtist());
			album = metadataDao.getAlbumInfoByArtistAndAlbumTitle(getAlbumInfo);
		}
		if (album != null) {
			info.setAlbumid(album.getAlbumId());
			if(info.getAppkey().equals(Constants.APPKEY_V2)){
				info.setSongFileFlag(1);
			}
			List<Song> songList = metadataDao.getSongInfoByAlbumId(info);
			if (songList.size() > 0) {
				List<New_SongEntry> songEntryList = new ArrayList<New_SongEntry>();
				for (int i = 0; i < songList.size(); i++) {
					New_SongEntry songEntry = new New_SongEntry(songList.get(i));
					songEntryList.add(i, songEntry);
				}
				result.setSong(songEntryList);
				result.setSongscount(songList.size());
			} else {
				result.setSongscount(0);
			}
		}
		return response;
	}

	/**
	 * 6.5 getalbumsofsong 根据歌曲获得专辑信息
	 * */
	public New_AlbumResponse getalbumsofsong(GetAlbumsOfSongInfo info) {
		if ((info.getSongid() <= 0)
				&& Validator.isBlank(info.getSong())) {
			throw new MusicRcException(MusicRcException.ERRORARTIST);
		}
		New_AlbumResponse response = new New_AlbumResponse();
		New_AlbumListEntry result = new New_AlbumListEntry();
		response.setAlbumslist(result);
		ServiceUtils.pageCompute(info);
		Song song = null;
		if (info.getSongid() > 0) {
			song = metadataDao.getSongInfoBySongId(info.getSongid(), 1, 0);
		} else {
			if (Validator.isBlank(info.getSong())) {
				throw new MusicRcException(MusicRcException.ERRORTITLE);
			}
			if (Validator.isBlank(info.getArtist())) {
				throw new MusicRcException(MusicRcException.ERRORTITLE);
			}
			song = metadataDao.getSongInfoByArtistAndSong(info.getSong(),
					info.getArtist(), 1);
		}
		if (song != null) {
			info.setSongid(song.getSongId());
			List<Album> albumList = metadataDao.getAlbumListBySongId(info);
			List<New_AlbumEntry> albumListEntry = new ArrayList<New_AlbumEntry>();
			if (albumList.size() > 0) {
				for (int i = 0; i < albumList.size(); i++) {
					New_AlbumEntry entry = new New_AlbumEntry(albumList.get(i));
					albumListEntry.add(i, entry);
				}
				result.setAlbum(albumListEntry);
				result.setAlbumscount(albumList.size());
			}
			if (info.getAllcountflag() == 1) {
				int allCount = metadataDao.getAlbumListBySongIdAllCount(info);
				result.setAllcount(allCount);
			}
		}
		return response;
	}

	/**
	 * 6.6 getalbuminfo 获得专辑信息
	 * */
	public New_AlbumInfoResponse getalbuminfo(GetAlbumInfo info) {
		New_AlbumInfoResponse response = new New_AlbumInfoResponse();
		Album album = null;
		info.setAlbumartflag(0);
		if (info.getAlbumid() > 0) {
			album = metadataDao.getAlbumInfoById(info.getAlbumid());
		} else {
			if (Validator.isBlank(info.getAlbum())) {
				throw new MusicRcException(MusicRcException.ERRORALBUM);
			}
			if (Validator.isBlank(info.getArtist())) {
				throw new MusicRcException(MusicRcException.ERRORARTIST);
			}
			album = metadataDao.getAlbumInfoByArtistAndAlbumTitle(info);
		}
		if (album != null) {
			List<Album> albumList=new ArrayList<Album>();
			albumList.add(album);
			getAlbumPicsByAlbumList(albumList,info.getAlbumartsize());
			New_AlbumEntry albumEntry = new New_AlbumEntry(album);
			response.setAlbum(albumEntry);
		} else {
			response.setMessage("NOT FOUND");
		}
		return response;
	}

	/**
	 * 6.7 getalbumart 获得专辑图片
	 * */

	public New_AlbumInfoResponse getalbumart(GetAlbumInfo info) {
		return getalbuminfo(info);
	}

	/**
	 * 6.9 getlyric 获得歌词
	 * */
	public New_GetLyricResponse getlyric(GetLyricInfo info) {
		New_GetLyricResponse response = new New_GetLyricResponse();
		New_LyricEntry result = new New_LyricEntry();
		Song song = null;
		if (info.getSongid() > 0) {
			song = metadataDao.getSongInfoBySongId(info.getSongid(), 1, 0);
		} else {
			if (Validator.isBlank(info.getSong())) {
				throw new MusicRcException(MusicRcException.ERRORTITLE);
			}
			if (Validator.isBlank(info.getArtist())) {
				throw new MusicRcException(MusicRcException.ERRORARTIST);
			}
			song = metadataDao.getSongInfoByArtistAndSong(info.getSong(),
					info.getArtist(), 0);
		}
		if (song != null) {
			String lyric = metadataDao.getLyricBySongId(song.getSongId());
			if (!Validator.isBlank(lyric)) {
				result.setLyric(lyric);
			}
			result.setSongid(song.getSongId());
			result.setTitle(song.getSongTitle());
		} else {
			response.setMessage("NOT FOUND");
		}
		response.setSong(result);
		return response;
	}

	public New_GetMvInfoResponse getmvinfo(GetMvInfo info) {
		New_GetMvInfoResponse response = new New_GetMvInfoResponse();
		List<New_MvEntry> result = new ArrayList<New_MvEntry>();
		response.setMvinfo(result);
		ServiceUtils.pageCompute(info);
		if(info.getMvid()==0&&info.getSongid()==0&&(Validator.isBlank(info.getSong())&&Validator.isBlank(info.getArtist())&&Validator.isBlank(info.getAlbum())&&Validator.isBlank(info.getFilename()))){
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		List<SongMv> songMvList = new ArrayList<SongMv>();
		List<SongMv> songMvListLast = new ArrayList<SongMv>();
		if (info.getMvid() > 0) {
			SongMv songMv = metadataDao.getSongMvInfoByMvId(info.getMvid());
			songMvList.add(songMv);
		} else {
			List<Long> songIdList = new ArrayList<Long>();
			if (info.getSongid() > 0) {
				songIdList.add(info.getSongid());
			} else {
				info.setMvflag(1);
				songIdList = this.getSearchSongIdList(info);
			}
			if (songIdList.size() > 0) {
				LookupMvInfo lookupMvInfo = new LookupMvInfo();
				lookupMvInfo.setSongIdList(songIdList);
				songMvList = metadataDao
						.getSongMvInfoBySongIdList(lookupMvInfo);
			}
		}
		if (songMvList.size() > 0) {
			if (!Validator.isBlank(info.getArtist())) {
				for (SongMv songMv : songMvList) {
					if (songMv.getArtistName().indexOf(info.getArtist()) != -1) {
						songMvListLast.add(songMv);
					}
				}
				if (songMvListLast.size() == 0) {
					songMvListLast = songMvList;
				}
			} else {
				songMvListLast = songMvList;
			}
		}
		if (songMvListLast.size() > 0) {
			for (int i = 0; i < songMvListLast.size(); i++) {
				New_MvEntry entry = new New_MvEntry(songMvListLast.get(i));
				result.add(i, entry);
			}
		}
		return response;
	}
	public List<String> getArtistPicsByArtistId(long artistId,int artistartsize){
		List<String> artistArtList=new ArrayList<String>(); 
		if(artistId==0)return artistArtList;
		List<ArtistPic> artistPicList=metadataDao.getArtistPicsByArtistId(artistId);
		if(artistPicList.size()>0){
			for(ArtistPic artistPic:artistPicList){
				String pictrue="";
				if(artistartsize!=120){
					if(!Validator.isBlank(artistPic.getMiddlePic())){
						pictrue=artistPic.getMiddlePic();
					}else{
						pictrue=artistPic.getPicUri();
					}
				}else{
					if(!Validator.isBlank(artistPic.getSmallPic())){
						pictrue=artistPic.getSmallPic();
					}else{
						pictrue=artistPic.getPicUri();
					}
				}
				artistArtList.add(ServiceUtils.returnURL(pictrue));
			}
		}
		return artistArtList;
	}
	public List<String> getArtistPicsByArtistIdAndSize(long artistId,int artistartsize){
		List<String> artistArtList=new ArrayList<String>(); 
		if(artistId==0)return artistArtList;
		List<ArtistPic> artistPicList=metadataDao.getArtistPicsByArtistId(artistId);
		if(artistPicList.size()>0){
			for(ArtistPic artistPic:artistPicList){
				String pictrue="";
				if(artistartsize==120){
					if(!Validator.isBlank(artistPic.getSmallPic())){
						pictrue=artistPic.getSmallPic();
					}
				}else if(artistartsize==320||artistartsize==0){
					if(!Validator.isBlank(artistPic.getMiddlePic())){
						pictrue=artistPic.getMiddlePic();
					}
				}
//				if(artistartsize!=120){
//					if(!Validator.isBlank(artistPic.getMiddlePic())){
//						pictrue=artistPic.getMiddlePic();
//					}else{
//						pictrue=artistPic.getPicUri();
//					}
//				}else{
//					if(!Validator.isBlank(artistPic.getSmallPic())){
//						pictrue=artistPic.getSmallPic();
//					}else{
//						pictrue=artistPic.getPicUri();
//					}
//				}
				if(!Validator.isBlank(pictrue)){
					artistArtList.add(ServiceUtils.returnURL(pictrue));
				}
				
			}
		}
		return artistArtList;
	}
	public void getArtistPicsByArtistList(List<Artist> artistList,int artistartsize){
		if(artistList.isEmpty())return;
		List<Long> artistIdList=new ArrayList<Long>();
		for(Artist artist:artistList){
			artistIdList.add(artist.getArtistId());
		}
		List<ArtistPic> artistPicList=metadataDao.getArtistPicsByArtistList(artistIdList);
		if(artistPicList.size()>0){
			for(Artist artist:artistList){
				for(ArtistPic artistPic:artistPicList){
					if(artist.getArtistId()==artistPic.getArtistId()){
						String pictrue="";
						if(artistartsize!=120){
							if(!Validator.isBlank(artistPic.getMiddlePic())){
								pictrue=artistPic.getMiddlePic();
							}else{
								pictrue=artistPic.getPicUri();
							}
						}else{
							if(!Validator.isBlank(artistPic.getSmallPic())){
								pictrue=artistPic.getSmallPic();
							}else{
								pictrue=artistPic.getPicUri();
							}
						}
						
						artist.setArtistThumbnail(pictrue);
					}
				}
			}
		}
	}
	public void getArtistPicsBySongList(List<Song> songList,int artistartsize){
		if(songList.isEmpty())return;
		List<Long> artistIdList=new ArrayList<Long>();
		for(Song song:songList){
			artistIdList.add(song.getArtistId());
		}
		List<ArtistPic> artistPicList=metadataDao.getArtistPicsByArtistList(artistIdList);
		if(artistPicList.size()>0){
			for(Song song:songList){
				for(ArtistPic artistPic:artistPicList){
					if(song.getArtistId()==artistPic.getArtistId()){
						String pictrue="";
						if(artistartsize!=120){
							if(!Validator.isBlank(artistPic.getMiddlePic())){
								pictrue=artistPic.getMiddlePic();
							}else{
								pictrue=artistPic.getPicUri();
							}
						}else{
							if(!Validator.isBlank(artistPic.getSmallPic())){
								pictrue=artistPic.getSmallPic();
							}else{
								pictrue=artistPic.getPicUri();
							}
						}
						
						song.setArtistArt(pictrue);
					}
				}
			}
		}
	}
	public void getAlbumPicsByAlbumList(List<Album> albumList,int albumartsize){
		if(albumList.isEmpty())return;
		List<Long> albumIdList=new ArrayList<Long>();
		for(Album album:albumList){
			albumIdList.add(album.getAlbumId());
		}
		List<AlbumPic> albumPicList=metadataDao.getAlbumPicsByAlbumIdList(albumIdList);
		
		if(albumPicList.size()>0){
			for(Album album:albumList){
				for(AlbumPic albumPic:albumPicList){
					if(album.getAlbumId()==albumPic.getAlbumId()){
						String picture="";
						if(albumartsize!=120){
							if(!Validator.isBlank(albumPic.getMiddlePic())){
								picture=albumPic.getMiddlePic();
							}else{
								picture=albumPic.getPicUri();
							}
						}else{
							if(!Validator.isBlank(albumPic.getSmallPic())){
								picture=albumPic.getSmallPic();
							}else{
								picture=albumPic.getPicUri();
							}
						}
						album.setAlbumThumbUri(picture);
					}
				}
			}
		}
	}
	public void getAlbumPicsBySongList(List<Song> songList,int albumartsize){
		if(songList.isEmpty())return;
		List<Long> albumIdList=new ArrayList<Long>();
		for(Song song:songList){
			albumIdList.add(song.getAlbumId());
		}
		List<AlbumPic> albumPicList=metadataDao.getAlbumPicsByAlbumIdList(albumIdList);
		
		if(albumPicList.size()>0){
			for(Song song:songList){
				for(AlbumPic albumPic:albumPicList){
					if(song.getAlbumId()==albumPic.getAlbumId()){
						String picture="";
						if(albumartsize!=120){
							if(!Validator.isBlank(albumPic.getMiddlePic())){
								picture=albumPic.getMiddlePic();
							}else{
								picture=albumPic.getPicUri();
							}
						}else{
							if(!Validator.isBlank(albumPic.getSmallPic())){
								picture=albumPic.getSmallPic();
							}else{
								picture=albumPic.getPicUri();
							}
						}
						song.setSongThumbUri(picture);
					}
				}
			}
		}
	}
	
	/**
	 * 6.8 getsonginfo
	 * */
	public New_GetSongInfoResponse getsonginfo(GetSongInfo info) {
		New_GetSongInfoResponse response = new New_GetSongInfoResponse();
		List<New_GetSongInfoEntry> songinfoList = new ArrayList<New_GetSongInfoEntry>();
		response.setSonginfo(songinfoList);
		List<Long> songIdList = new ArrayList<Long>();
		ServiceUtils.pageCompute(info);
		if (info.getSongid() > 0) {
			songIdList.add(info.getSongid());
		} else {
			songIdList = getSearchSongIdListOfGetSongInfo(info);
		}
		if (songIdList.size() > 0) {
			info.setSongIdList(songIdList);
			songIdList = metadataDao.checkGetSongInfoBySongIdByFilter(info);
		}
		if (songIdList.size() > 0) {
			info.setSongIdList(songIdList);
			List<Song> songList = metadataDao.getSongInfoBySongIdList(info);
			if (songList.size() > 0) {
				List<Song> songLists = new ArrayList<Song>();
				if (!Validator.isBlank(info.getArtist())) {
					for (Song song : songList) {
						if (song.getArtistName().equals(info.getArtist())) {
							songLists.add(song);
						}
					}
					if (songLists.size() > 0) {
						songList = songLists;
					}
				}
				songList = ServiceUtils.removeIterativeOnSongList(songList);
				if(info.getAlbumartflag()==1){
					getAlbumPicsBySongList(songList,info.getAlbumartsize());
				}
				if(info.getArtistartflag()==1){
					getArtistPicsBySongList(songList,info.getArtistartsize());
				}
				songList=ServiceUtils.sortSongRandomList(songIdList,songList);
				if(songList.size()>0){
					List<Long> lyricSongIdList = new ArrayList<Long>();
					for (int i=0;i<songList.size();i++){
						New_GetSongInfoEntry songInfoEntry = new New_GetSongInfoEntry(
								songList.get(i));// song基本信息初始化
						songinfoList.add(songinfoList.size(),songInfoEntry);
						lyricSongIdList.add(i,songList.get(i).getSongId());
					}
					if (info.getLyricflag() == 1) {
						if (lyricSongIdList.size() > 0) {
							List<Lyric> lyricList = metadataDao
									.getLyricListBySongIdList(lyricSongIdList);
							if (lyricList.size() > 0) {
								for (int i = 0; i < songinfoList.size(); i++) {
									New_GetSongInfoEntry songInfoEntry = songinfoList
											.get(i);
									for (int j = 0; j < lyricList.size(); j++) {
										Lyric lyric = lyricList.get(j);
										if (lyric.getSongId() == songInfoEntry
												.getSongid()) {
											songInfoEntry.setLyric(lyric
													.getLyricText());
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return response;
	}

	/**
	 * 6.8 getsonginfo
	 * */
	public New_GetSongInfoResponse getsonginfoforlucene(GetSongInfo info) {
		New_GetSongInfoResponse response = new New_GetSongInfoResponse();
		List<New_GetSongInfoEntry> songinfoList = new ArrayList<New_GetSongInfoEntry>();
		response.setSonginfo(songinfoList);
		List<Long> songIdList = new ArrayList<Long>();
		ServiceUtils.pageCompute(info);
		if (info.getSongid() > 0) {
			songIdList.add(info.getSongid());
		} else {
			songIdList = getSearchSongIdListInLucene(info);
		}
		if (songIdList.size() > 0) {
			info.setSongIdList(songIdList);
			songIdList = metadataDao.checkGetSongInfoBySongIdByFilter(info);
		}
		if (songIdList.size() > 0) {
			info.setSongIdList(songIdList);
			List<Song> songList = metadataDao.getSongInfoBySongIdList(info);
			if (songList.size() > 0) {
				List<Song> songLists = new ArrayList<Song>();
				if (!Validator.isBlank(info.getArtist())) {
					for (Song song : songList) {
						if (song.getArtistName().equals(info.getArtist())) {
							songLists.add(song);
						}
					}
					if (songLists.size() > 0) {
						songList = songLists;
					}
				}
				songList = ServiceUtils.removeIterativeOnSongList(songList);
				if(info.getAlbumartflag()==1){
					getAlbumPicsBySongList(songList,info.getAlbumartsize());
				}
				if(info.getArtistartflag()==1){
					getArtistPicsBySongList(songList,info.getArtistartsize());
				}
				songList=ServiceUtils.sortSongRandomList(songIdList,songList);
				if(songList.size()>0){
					List<Long> lyricSongIdList = new ArrayList<Long>();
					for (int i=0;i<songList.size();i++){
						New_GetSongInfoEntry songInfoEntry = new New_GetSongInfoEntry(
								songList.get(i));// song基本信息初始化
						songinfoList.add(songinfoList.size(),songInfoEntry);
						lyricSongIdList.add(i,songList.get(i).getSongId());
					}
					if (info.getLyricflag() == 1) {
						if (lyricSongIdList.size() > 0) {
							List<Lyric> lyricList = metadataDao
									.getLyricListBySongIdList(lyricSongIdList);
							if (lyricList.size() > 0) {
								for (int i = 0; i < songinfoList.size(); i++) {
									New_GetSongInfoEntry songInfoEntry = songinfoList
											.get(i);
									for (int j = 0; j < lyricList.size(); j++) {
										Lyric lyric = lyricList.get(j);
										if (lyric.getSongId() == songInfoEntry
												.getSongid()) {
											songInfoEntry.setLyric(lyric
													.getLyricText());
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return response;
	}
	
	
	public New_SongUriListResponse getsongurilist(SongUriListInfo info) {
		
		if ( info.getSongid() == 0) {
			throw new MusicRcException(MusicRcException.ERRORSONGID);
		}
		
		New_SongUriListResponse response = new New_SongUriListResponse();
		New_SongUriListEntry urilist=new New_SongUriListEntry();
		response.setUrilist(urilist);
		info.setPagecount(5);
		List<New_SongUriEntry> songUriEntryList=new ArrayList<New_SongUriEntry>();
		if(info.getRate()>0||info.getAppkey().equals(Constants.APPKEY_WEB)){
			nativeDownloadUriList(info,songUriEntryList);
			theThridDownloadUriList(info,songUriEntryList);
		}else{
			theThridDownloadUriList(info,songUriEntryList);
			nativeDownloadUriList(info,songUriEntryList);
		}
		if(songUriEntryList.size()==0){
			GetDownloadUriInfo downloadInfo=new GetDownloadUriInfo();
			downloadInfo.setSongid(info.getSongid());
			downloadInfo.setRate(info.getRate());
			New_DownloadUriResponse downloadResponse=getsampleuri(downloadInfo);
			if(!Validator.isBlank(downloadResponse.getUri())){
				SongFile songfile=new SongFile();
				songfile.setSongId(downloadResponse.getSongid());
				songfile.setUri(downloadResponse.getUri());
				New_SongUriEntry songUriEntry=new New_SongUriEntry(songfile);
				songUriEntryList.add(songUriEntry);
			}
		}
		urilist.setUri(songUriEntryList);
		
		return response;
	}
	public void nativeDownloadUriList(SongUriListInfo info,List<New_SongUriEntry> songUriEntryList){
		SongFile songFile = metadataDao.getSongFileById(info.getSongid(), info.getRate());
		if(songFile!=null){
			E2eDownloadService e2eDownloadService = new E2eDownloadService(
					Configer.getValueString(MusicConst.DOWNLOAD_SERVER_APPKEY),
					Configer.getValueString(MusicConst.DOWNLOAD_SERVER_SECRET));
			DownloadModel dlu = new DownloadModel();
			dlu.setToken(Configer.getValueString(MusicConst.DOWNLOAD_SERVER_TOKEN));
			dlu.setAppkey(Configer
					.getValueString(MusicConst.DOWNLOAD_SERVER_APPKEY));
			dlu.setRestype(E2eDownloadService.RES_TYPE_MUSIC);
			dlu.setAppid(songFile.getSongFileId());
			DownloadResponse res = null;
			try {
				res = e2eDownloadService.execute(dlu,
						E2eDownloadService.METHOD_GET_STATIC_DOWNLOAD_URI);
			} catch (Exception e) {
				if (log.isDebugEnabled())
					log.debug(e.getMessage(), e);
				else
					log.error(e.getMessage());
			}
			if (res.getRc() == 0) {
				songFile.setUri(res.getUri());
				New_SongUriEntry songUriEntry=new New_SongUriEntry(songFile);
				songUriEntryList.add(songUriEntryList.size(), songUriEntry);
			}
		}
	}
	
	public void theThridDownloadUriList(SongUriListInfo info,List<New_SongUriEntry> songUriEntryList){

		List<SongUri> songUriList=metadataDao.getSongUriListBySongId(info);
		List<SongUri> googleUriList=new ArrayList<SongUri>();
		for(int i=0; i<songUriList.size(); i++ ){
			SongUri songUri=songUriList.get(i);
			if(songUri.getDataFrom()==2){
				googleUriList.add(songUri);
			}else{
				New_SongUriEntry songUriEntry=new New_SongUriEntry(songUri);
				songUriEntryList.add(songUriEntryList.size(), songUriEntry);
			}
		}
		for(int i=0; i<googleUriList.size(); i++ ){
			New_SongUriEntry songUriEntry=new New_SongUriEntry(googleUriList.get(i));
			songUriEntryList.add(songUriEntryList.size(), songUriEntry);
		}
	}
	/**
	 * 7.3 getdownloaduri 获得下载服务器的歌曲下载地址
	 * */
	public New_DownloadUriResponse getdownloaduri(GetDownloadUriInfo info) {
		return getNewUriInfo(info, false);
	}

	/**
	 * 7.4 getsampleuri 获得下载服务器的歌曲试听地址
	 * */

	public New_DownloadUriResponse getsampleuri(GetDownloadUriInfo info) {
		return getNewUriInfo(info, true);
	}

	public New_DownloadUriResponse getNewUriInfo(GetDownloadUriInfo info,
			boolean sequenceFlag) {
		New_DownloadUriResponse response = new New_DownloadUriResponse();
		if (info.getSongid() <= 0) {
			throw new MusicRcException(MusicRcException.ERRORSONGID);
		}
		Long filesongid = null;
		List<Integer> rateList = new ArrayList<Integer>();
		rateList.add(0);
		rateList.add(6);
		if (info.getRate() != null && info.getRate() >= 0) {
			rateList.add(info.getRate());
		}
		info.setRateList(rateList);
		List<SongFile> songFileList = metadataDao.getSongFileIdList(info);
		if (info.getRate() != null && info.getRate() >= 0) {
			for (int i = 0; i < songFileList.size(); i++) {
				SongFile sf = songFileList.get(i);
				int rate = sf.getRate();
				long songFileId = sf.getSongFileId();
				if (rate == info.getRate()) {
					filesongid = songFileId;
					break;
				}
			}
		}
		int songRateFirst = 0;
		int songRateSecond = 6;
		if (sequenceFlag) {
			songRateFirst = Constants.SONG_RATE_AAC;
			songRateSecond = Constants.SONG_RATE_MP3;
		}
		if (filesongid == null) {
			for (int i = 0; i < songFileList.size(); i++) {
				SongFile sf = songFileList.get(i);
				int rate = sf.getRate();
				long songFileId = sf.getSongFileId();
				if (rate == songRateFirst) {
					filesongid = songFileId;
					break;
				}
			}
		}
		if (filesongid == null) {
			for (int i = 0; i < songFileList.size(); i++) {
				SongFile sf = songFileList.get(i);
				int rate = sf.getRate();
				long songFileId = sf.getSongFileId();
				if (rate == songRateSecond) {
					filesongid = songFileId;
					break;
				}
			}
		}
		if (filesongid == null) {
			throw new MusicRcException(MusicRcException.SONGNOTEXISTS);
		}
		E2eDownloadService e2eDownloadService = new E2eDownloadService(
				Configer.getValueString(MusicConst.DOWNLOAD_SERVER_APPKEY),
				Configer.getValueString(MusicConst.DOWNLOAD_SERVER_SECRET));
		DownloadModel dlu = new DownloadModel();
		dlu.setToken(Configer.getValueString(MusicConst.DOWNLOAD_SERVER_TOKEN));
		dlu.setAppkey(Configer
				.getValueString(MusicConst.DOWNLOAD_SERVER_APPKEY));
		dlu.setRestype(E2eDownloadService.RES_TYPE_MUSIC);
		dlu.setAppid(filesongid);
		DownloadResponse res = null;
		try {
			res = e2eDownloadService.execute(dlu,
					E2eDownloadService.METHOD_GET_STATIC_DOWNLOAD_URI);
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug(e.getMessage(), e);
			else
				log.error(e.getMessage());
		}
		if (res.getRc() == 0) {
			response.setFilename(res.getFileName());
			response.setUri(res.getUri());
			response.setSongid(info.getSongid());
		} else {
			response.setRc(MusicRcException.SONGNOTEXISTS);
		}
		return response;
	}

	/**
	 * 8.1 recommendartists 列出服务器推荐的Artists
	 * */
	public New_ArtistListResponse recommendartists(RecommendArtistInfo info) {
		return getrandomartists(info);
	}

	public New_ArtistListResponse getrandomartists(RecommendArtistInfo info) {
		New_ArtistListResponse response = new New_ArtistListResponse();
		New_ArtistListEntry result = new New_ArtistListEntry();
		response.setArtistslist(result);
		int region = 21;
		ServiceUtils.pageCompute(info);
		if (info.getArtistid() > 0 || !Validator.isBlank(info.getArtist())) {
			if (info.getArtistid() > 0) {
				Artist artist = metadataDao.getArtistInfoByArtistIdOrName(
						info.getArtistid(), null);
				if (artist != null) {
					region = artist.getArtistRegion();
				}
			} else {
				Artist artist = metadataDao.getArtistInfoByArtistIdOrName(0l,
						info.getArtist());
				if (artist != null) {
					region = artist.getArtistRegion();
				}
			}
		}
		info.setRegion(region);
		List<Long> randomArtistIdList = metadataDao.getRandomArtistId(info);
		if (randomArtistIdList.size() > 0) {
			info.setArtistIdList(randomArtistIdList);
			List<Artist> artistList = metadataDao
					.getArtistListByArtistIdList(info);
			if (artistList.size() > 0) {
				List<New_ArtistEntry> artistListEntry = new ArrayList<New_ArtistEntry>();
				for (int i = 0; i < artistList.size(); i++) {
					New_ArtistEntry entry = new New_ArtistEntry(
							artistList.get(i));
					artistListEntry.add(i,entry);
				}
				result.setArtist(artistListEntry);
				result.setArtistscount(artistListEntry.size());
				result.setArtist(artistListEntry);
				if (info.getAllcountflag() == 1) {
					result.setAllcount(artistListEntry.size());
				}
			}
		}
		return response;
	}

	public New_ArtistListResponse recommendartists2Native(
			RecommendArtistInfo info) {
		New_ArtistListResponse response = new New_ArtistListResponse();
		New_ArtistListEntry result = new New_ArtistListEntry();
		response.setArtistslist(result);
		ServiceUtils.pageCompute(info);
		long subBillboardId = metadataDao.getRandomSubBillboardId();
		TopArtistInfo topInfo = new TopArtistInfo();
		topInfo.setBioflag(info.getBioflag());
		topInfo.setArtistartflag(info.getArtistartflag());
		topInfo.setStart(info.getStart());
		topInfo.setPagecount(info.getPagecount());
		topInfo.setSubbillboardid(subBillboardId);
		List<Artist> artistList = metadataDao
				.getSubBillboardArtistList(topInfo);
		if (artistList.size() > 0) {
			List<New_ArtistEntry> artistListEntry = new ArrayList<New_ArtistEntry>();
			for (int i = 0; i < artistList.size(); i++) {
				New_ArtistEntry entry = new New_ArtistEntry(artistList.get(i));
				artistListEntry.add(i,entry);
			}
			result.setArtist(artistListEntry);
			if (info.getAllcountflag() == 1) {
				result.setAllcount(info.getPagecount());
			}
		}
		return response;
	}

	/**
	 * 8.2 recommendalbums 列出服务器推荐的albums
	 * */
	public New_AlbumResponse recommendalbums(RecommendalbumInfo info) {
		return getrandomalbums(info);
	}

	public New_AlbumResponse getrandomalbums(RecommendalbumInfo info) {
		New_AlbumResponse response = new New_AlbumResponse();
		New_AlbumListEntry result = new New_AlbumListEntry();
		response.setAlbumslist(result);
		int region = 21;
		if (info.getAlbumid() > 0) {
			Artist artist = metadataDao.getArtistInfoByAlbumId(info
					.getAlbumid());
			if (artist != null) {
				region = artist.getArtistRegion();
			}
		} else {
			if (Validator.isBlank(info.getArtist())) {
				throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
			}
			if (Validator.isBlank(info.getAlbum())) {
				throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
			}
			// 这里只需要根据歌手名进行推荐就可以了
			Artist artist = metadataDao.getArtistInfoByArtistIdOrName(0L,
					info.getArtist());
			if (artist != null) {
				region = artist.getArtistRegion();
			}
		}

		RecommendArtistInfo recommendArtistInfo = new RecommendArtistInfo();
		recommendArtistInfo.setRegion(region);
		List<Long> randomArtistIdList = metadataDao
				.getRandomArtistId(recommendArtistInfo);
		if (randomArtistIdList.size() > 0) {
			info.setArtistIdList(randomArtistIdList);
			List<Album> albumList = metadataDao
					.getAlbumListInfoByArtistIdList(info);
			if (albumList.size() > 0) {
				result.setAlbumscount(albumList.size());
				List<New_AlbumEntry> albumEntryList = new ArrayList<New_AlbumEntry>();
				for (int i = 0; i < albumList.size(); i++) {
					New_AlbumEntry albumEntry = new New_AlbumEntry(
							albumList.get(i));
					albumEntryList.add(i, albumEntry);
				}
				result.setAlbum(albumEntryList);
				if (info.getAllcountflag() == 1) {
					result.setAllcount(info.getPagecount());
				}
			}
		}
		return response;
	}

	/**
	 * 8.3 getsimilarsongs DLL测试方法
	 * */


	/**
	 * 8.3 getsimilarsongs
	 * */
	public New_SongListResponse getsimilarsongs(GetSimilarSongInfo info) {
		return getsimilarsongsnative(info);
	}

	/**
	 * 接口8.3 getsimilarsongs 调用本地流程方法
	 * */
	public New_SongListResponse getsimilarsongsnative(GetSimilarSongInfo info) {
		New_SongListResponse response = new New_SongListResponse();
		New_SongListEntry result = new New_SongListEntry();
		Song song = null;
		if (info.getSongid() > 0) {
			song = metadataDao.getSimilarSongNative(info);
		} else {
			if (info.getArtist() == null) {
				throw new MusicRcException(MusicRcException.ERRORARTIST);
			}
			if (info.getSong() == null) {
				throw new MusicRcException(MusicRcException.ERRORTITLE);
			}
			song = metadataDao.getSimilarSongNative(info);
			if (song == null) {
				GetSimilarSongInfo newinfo = new GetSimilarSongInfo();
				newinfo.setArtist(info.getArtist());
				song = metadataDao.getSimilarSongNative(newinfo);
			}
		}
		if (song != null) {
			info.setArtistid(song.getArtistId());
			List<Song> songList = metadataDao.getCommonSongListByArtistId(info);
			if (songList.size() > 0) {
				List<New_SongEntry> songEntryListLast = new ArrayList<New_SongEntry>();
				List<New_SongEntry> songEntryList = new ArrayList<New_SongEntry>();
				songList = ServiceUtils.removeSongListByCondition(songList);// 删除artist或albumid=0的数据
				for (int i = 0; i < songList.size(); i++) {
					New_SongEntry songEntry = new New_SongEntry(songList.get(i));
					songEntryList.add(i, songEntry);
				}
				if (songEntryList.size() < info.getPagecount()) {
					GetRandomSongInfo getRandomSongInfo = new GetRandomSongInfo();
					getRandomSongInfo.setAlbumartflag(info.getAlbumartflag());
					getRandomSongInfo.setAlbumartsize(info.getAlbumartsize());
					New_SongListResponse randomSongResponse = getrandomsongs(getRandomSongInfo);
					New_SongListEntry randomSongListEntry = randomSongResponse
							.getSongslist();
					List<New_SongEntry> newSongEntryList = randomSongListEntry
							.getSong();
					songEntryList.addAll(newSongEntryList);
					// 排除自己，去掉重复
					songEntryList = ServiceUtils
							.removeIterativeOfSongListAndSelf(newSongEntryList,
									info);
					songEntryListLast.addAll(songEntryList.subList(0,
							info.getPagecount()));
				} else {
					songEntryListLast = songEntryList;
				}
				result.setSong(songEntryListLast);
				result.setSongscount(songEntryListLast.size());
			} else {
				GetRandomSongInfo getRandomSongInfo = new GetRandomSongInfo();
				getRandomSongInfo.setAlbumartflag(info.getAlbumartflag());
				getRandomSongInfo.setAlbumartsize(info.getAlbumartsize());
				return getrandomsongs(getRandomSongInfo);
			}
		} else {
			GetRandomSongInfo getRandomSongInfo = new GetRandomSongInfo();
			getRandomSongInfo.setAlbumartflag(info.getAlbumartflag());
			getRandomSongInfo.setAlbumartsize(info.getAlbumartsize());
			return getrandomsongs(getRandomSongInfo);
		}
		response.setSongslist(result);
		return response;
	}

	public New_SongListResponse getrandomsongs(GetRandomSongInfo info) {
		New_SongListResponse response = new New_SongListResponse();
		List<Song> songList = new ArrayList<Song>();
		ServiceUtils.pageCompute(info);
		List<Long> songIdList = metadataDao.generateRandomsSongIds(
				info.getMvflag(), info.getPagecount(), info.getAlbumartflag());
		New_SongListEntry result = new New_SongListEntry();
		response.setSongslist(result);
		if (songIdList.size() > 0) {
			info.setSongIdList(songIdList);
			songList = metadataDao.getRandomSongInfo(info);
			songList = ServiceUtils.sortSongRandomList(songIdList, songList);
			if (songList.size() > 0) {
				List<New_SongEntry> songEntryList = new ArrayList<New_SongEntry>();
				for (int i = 0; i < songList.size(); i++) {
					New_SongEntry songEntry = new New_SongEntry(songList.get(i));
					songEntryList.add(i, songEntry);
				}
				result.setSong(songEntryList);
				result.setSongscount(songList.size());
			} else {
				result.setSongscount(0);
			}
		} else {
			result.setSongscount(0);
		}
		return response;
	}

	/**
	 * 接口9.1 likesong
	 * 
	 * */

	public LikeResponse  likesong(MusicInfo info) {
		LikeInfo likeInfo=new LikeInfo();
		likeInfo.setId(info.getSongid());
		likeInfo.setLike(info.getLike());
		likeInfo.setItemtype(Constants.ITEM_TYPE_SONG);
		likeInfo.setUid(info.getUid());
		likeInfo.setToken(info.getToken());
		return sosHelper.like(likeInfo);
	}

	/**
	 * 10.2 stat 输入歌曲songid 服务端统计当前歌曲播放过的
	 * */
	public void updateUserSongUdata(long songId,long uid) {
		Long id= socialDao.checkSongIdIfInUserSong(songId, uid);
		if (id!=null&&id > 0) {
			int count = socialDao.getUserSongUdataId(id);
			if (count == 0) {
				socialDao.saveUserSongUdata(id);
			}else{
				socialDao.updateUserSongUdata(id);
			}
		}else{
			long playlistId=0l;
			//新建播放列表 未分类播放列表，并将次信息插入
			UserPlaylist userPlaylist=socialDao.getUserPlaylistByUidAndDataFrom(Constants.USER_PLAYLIST_DATA_FROM_UNCATEGORIED,uid);
			if(userPlaylist==null){
				//uid,title,thumb,description,data_from,create_time,update_time
				UserMetadata userMeta=new UserMetadata();
				userMeta.setUid(uid);
				userMeta.setDataFrom(Constants.USER_PLAYLIST_DATA_FROM_UNCATEGORIED);
				userMeta.setPlaylistTitle(Constants.UNCAT_PLAYLIST_TITLE);
				userMeta.setPlaylistType(Constants.USER_PLAYLIST_TYPE_SONG);
				userMeta.setPlaylistThumb("");
				userMeta.setDescription("");
				playlistId=socialDao.saveUserPlaylist(userMeta);
			}else{
				playlistId=userPlaylist.getPlaylistId();
			}
			Song song=metadataDao.getSongInfoBySongIdNoCondition(songId);
			if(song!=null){
				UserSong metaData=new UserSong();
				metaData.setPlaylistId(playlistId);
				metaData.setUid(uid);
				metaData.setSongId(songId);
				metaData.setSongTitle(song.getSongTitle());
				metaData.setArtistName(song.getArtistName());
				metaData.setAlbumTitle(song.getAlbumTitle());
				metaData.setArtistId(song.getArtistId());
				metaData.setSongThumb(song.getSongThumbUri());
				metaData.setAlbumId(song.getAlbumId());
				metaData.setDuration(song.getSongDuration());
				metaData.setDataFrom(Constants.USER_SONG_DATA_FROM_ONLINE);
				long userSongId=socialDao.saveUserSongList(metaData);
				socialDao.saveUserSongUdata(userSongId);
			}
			
		}
	}
	
	public void updateUserMetaData(StatInfo info){
		
		Long id=socialDao.checkMetadataIfInUserSong(info);
		
		if(id!=null&&id>0){
			int count = socialDao.getUserSongUdataId(id);
			if (count == 0) {
				socialDao.saveUserSongUdata(id);
			}else{
				socialDao.updateUserSongUdata(id);
			}
		}else{
			long playlistId=0l;
			//新建播放列表 未分类播放列表，并将次信息插入
			UserPlaylist userPlaylist=socialDao.getUserPlaylistByUidAndDataFrom(Constants.USER_PLAYLIST_DATA_FROM_UNCATEGORIED, info.getUid());
			if(userPlaylist==null){
				//uid,title,thumb,description,data_from,create_time,update_time
				UserMetadata userMeta=new UserMetadata();
				userMeta.setUid(info.getUid());
				userMeta.setDataFrom(Constants.USER_PLAYLIST_DATA_FROM_UNCATEGORIED);
				userMeta.setPlaylistTitle(Constants.UNCAT_PLAYLIST_TITLE);
				userMeta.setPlaylistType(Constants.USER_PLAYLIST_TYPE_SONG);
				userMeta.setPlaylistThumb("");
				userMeta.setDescription("");
				playlistId=socialDao.saveUserPlaylist(userMeta);
				
			}else{
				playlistId=userPlaylist.getPlaylistId();
			}
			UserSong metaData=new UserSong();
			metaData.setPlaylistId(playlistId);
			metaData.setUid(info.getUid());
			metaData.setSongId(info.getSongid());
			metaData.setSongTitle(info.getSong());
			metaData.setArtistName(info.getArtist());
			metaData.setAlbumTitle(info.getAlbum());
			metaData.setDataFrom(Constants.USER_SONG_DATA_FROM_ONLINE);
			long userSongId=socialDao.saveUserSongList(metaData);
			socialDao.saveUserSongUdata(userSongId);
		}
	}
	public New_StatResponse feedbacksong(FeedBackSongInfo info) {
		if(info.getSongid()<=0){
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		New_StatResponse response = new New_StatResponse();
		return response;
	}
	public New_StatResponse stat(StatInfo info) {
		New_StatResponse response = new New_StatResponse();
		long songId =0l;
		int stattype = info.getStattype();
		if (stattype != 1 && stattype != 2) {
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		if (info.getSongid() == 0) {
//			GetSearchInfo getSearchInfo = new GetSearchInfo();
//			getSearchInfo.setStart(0);
//			getSearchInfo.setPagecount(5);
//			getSearchInfo.setArtist(info.getArtist());
//			getSearchInfo.setAlbum(info.getAlbum());
//			getSearchInfo.setSong(info.getSong());
//			getSearchInfo.setFilename(info.getFilename());
//			List<Long> songIdList = this.getSearchSongIdList(getSearchInfo);
//			if (songIdList.size() > 0) {
//				songId = songIdList.get(0);
//			}
//			if (info.getUserstatus() == Constants.USER_STATUS_NORMAL
//					|| info.getUserstatus() == Constants.USER_STATUS_INACTIVE) {
//				info.setSongid(songId);
//				updateUserMetaData(info);
//			}
		}else{
			songId=metadataDao.checkSongIdIfExist(info.getSongid());
		}
		// 根据songid统计user_op_album
		if (songId > 0) {
			long songUdataId = metadataDao.checkSongIdIfInSongUdata(songId);
			if (songUdataId == 0) {
				metadataDao.saveSongUdata(songId);
			}
			if (stattype == 1) {
				metadataDao.updateSongUdataPlayTimes(songId);
				
				if (info.getUserstatus() == Constants.USER_STATUS_NORMAL
						|| info.getUserstatus() == Constants.USER_STATUS_INACTIVE) {
					
					updateUserSongUdata(songId,info.getUid());
					
					long albumId = metadataDao.getAlbumIdBySongId(songId);
					if (albumId > 0) {
						operateUserOpAlbumAndAlbumUdata(info.getUid(), albumId, songId, Constants.OP_TYPE_PLAY,0);
					}
//					long pastDate = ServiceUtils.getPastDate(Constants.PAST_DATE);
//					long nowTime = new Date().getTime() / 1000;
//					int recentAlbumCount = metadataDao.getLastListenedAlbumCount(
//							info.getUid(), pastDate, nowTime);
					int recentAlbumCount=0;
					metadataDao.updateUserCountInfo(info.getUid(), songId,
							recentAlbumCount);
				}
				/*---------------检查歌曲下载文件是否存在------*/
				List<Long> songIds=new ArrayList<Long>();
				songIds.add(songId);
				metadataDao.downloadSongFileOfAlbumBySongId(songIds);
				int score=ActivityEnum.listenedSong.getValue();
				int type=ActivityEnum.listenedSong.getType();
				sosHelper.addUserActivity(info.getUid(), score);
				sosHelper.addUserActivityLog(info.getUid(), score, type);
				
			} else if (stattype == 2) {
				metadataDao.updateSongUdataDownloadTimes(songId);
			}
		}
		return response;
	}

	public void operateUserOpAlbumAndAlbumUdata(long uid,long albumId,long songId,int opType,long commentId){
		int albumInUdataCount = metadataDao
				.getAlbumUdataCountByAlbumId(albumId);
		if (albumInUdataCount == 0) {
			metadataDao.addAlbumUdata(albumId,0,opType);
		}
		int albumCount = metadataDao.getUserOpAlbum(uid,
				albumId,opType);
		if (albumCount == 0) {
			metadataDao.saveUserOpAlbum(uid, albumId,songId,opType,commentId);
		} else {
			metadataDao.updateUserOpAlbum(uid, albumId,songId,opType,commentId);
		}
		int userCount = metadataDao
				.getUsersCountOfUserOpAlbum(albumId);
		metadataDao.updateAlbumUdata(albumId, userCount,opType);
	}
	
	public New_SongListResponse getsongsofartist(GetSongsOfArtistInfo info) {
		if ((info.getArtistid() <= 0)
				&& Validator.isBlank(info.getArtist())) {
			throw new MusicRcException(MusicRcException.ERRORARTIST);
		}
		New_SongListResponse response = new New_SongListResponse();
		New_SongListEntry result = new New_SongListEntry();
		response.setSongslist(result);
		ServiceUtils.pageCompute(info);
		Artist artist = null;
		if (info.getArtistid() > 0) {
			artist = metadataDao.getArtistInfoByArtistIdOrName(
					info.getArtistid(), null);
		} else {
			artist = metadataDao.getArtistInfoByArtistIdOrName(0,
					info.getArtist());
		}
		if (artist != null) {
			info.setArtistid(artist.getArtistId());
			if(info.getAppkey().equals(Constants.APPKEY_V2)){
				info.setSongFileFlag(1);
			}
			List<Song> songList = metadataDao.getSongInfoListByArtistId(info);
			List<New_SongEntry> songEntryList = new ArrayList<New_SongEntry>();
			if (songList.size() > 0) {
				for (int i = 0; i < songList.size(); i++) {
					New_SongEntry entry = new New_SongEntry(songList.get(i));
					songEntryList.add(i, entry);
				}
			}
			result.setSongscount(songEntryList.size());
			result.setSong(songEntryList);
			if (info.getAllcountflag() == 1) {
				int allCount = metadataDao
						.getSongInfoListByArtistIdAllCount(info);
				result.setAllcount(allCount);
			}
		}
		return response;
	}

	public New_SongListResponse gethotsongsofartist(GetSongsOfArtistInfo info) {
		if ((info.getArtistid() <= 0)
				&& Validator.isBlank(info.getArtist())) {
			throw new MusicRcException(MusicRcException.ERRORARTIST);
		}
		New_SongListResponse response = new New_SongListResponse();
		New_SongListEntry result = new New_SongListEntry();
		response.setSongslist(result);
		ServiceUtils.pageCompute(info);
		Artist artist = null;
		if (info.getArtistid() > 0) {
			artist = metadataDao.getArtistInfoByArtistIdOrName(
					info.getArtistid(), null);
		} else {
			artist = metadataDao.getArtistInfoByArtistIdOrName(0L,
					info.getArtist());
		}

		if (artist != null) {
			info.setArtistid(artist.getArtistId());
			if(info.getAppkey().equals(Constants.APPKEY_V2)){
				info.setSongFileFlag(1);
			}
			List<Song> songList = metadataDao
					.getHotSongInfoListByArtistId(info);
			List<New_SongEntry> songEntryList = new ArrayList<New_SongEntry>();
			if (songList.size() > 0) {
				for (int i = 0; i < songList.size(); i++) {
					New_SongEntry entry = new New_SongEntry(songList.get(i));
					songEntryList.add(i, entry);
				}
			}
			result.setSongscount(songEntryList.size());
			result.setSong(songEntryList);
			if (info.getAllcountflag() == 1) {
				int allCount = metadataDao
						.getHotSongInfoListByArtistIdAllCount(info);
				result.setAllcount(allCount);
			}
		}
		return response;
	}


	public void getSongMvArtistIdAndName(List<SongMv> mtvlist) {
		if (mtvlist != null && mtvlist.size() > 0) {
			for (SongMv e : mtvlist) {
				ServiceUtils.setArtistAttribute4SongMv(e);
			}
		}
	}

	public void uploadMetadata(File clientFile, int uid) {
		String page = AnalysisZipFileUtil.fileUploadUtil(clientFile);
		List<Map> listDateMap = new ArrayList<Map>();
		if (!Validator.isBlank(page)) {
			try {
				Document doc = DocumentHelper.parseText(page);
				if (doc != null) {
					Element rootEle = doc.getRootElement();
					if (rootEle != null) {
						Element userLocalEle = rootEle.element("songslist");
						if (userLocalEle != null) {
							List userListEle = userLocalEle.elements("song");
							Iterator iter = userListEle.iterator();
							while (iter.hasNext()) {
								Element songEle = (Element) iter.next();
								if (songEle != null) {
									Map fileMap = new HashMap();
									fileMap.put("uid", uid);
									Element songIdEle = songEle
											.element("songid");
									String songIdStr = songIdEle.getTextTrim();
									long songid = 0l;
									if (!Validator.isBlank(songIdStr)) {
										try {
											Long songId = Long
													.parseLong(songIdStr);
											if (songId != null) {
												songid = songId;
											}
										} catch (Exception e) {
											if (log.isDebugEnabled())
												log.debug(e.getMessage(), e);
											else
												log.error(e.getMessage());
										}
									}
									fileMap.put("songId", songid);
									// songid
									Element filenameEle = songEle
											.element("filename");
									if(filenameEle!=null){
										String filename = filenameEle.getTextTrim();
										fileMap.put("fileName", filename);
									}
									// songid
									Element albumEle = songEle.element("album");
									if(albumEle!=null){
										String album = albumEle.getTextTrim();
										fileMap.put("albumTitle", album);
									}
									// songid
									Element artistEle = songEle
											.element("artist");
									if(artistEle!=null){
										String artist = artistEle.getTextTrim();
										fileMap.put("artistName", artist);
									}
									
									// songid
									Element titleEle = songEle.element("title");
									if(titleEle!=null){
										String title = titleEle.getTextTrim();
										fileMap.put("songTitle", title);
									}
									listDateMap.add(fileMap);
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
		}
		if (listDateMap.size() > 0) {
			for (int i = 0; i < listDateMap.size(); i++) {
				metadataDao.saveUploadMetadata(listDateMap.get(i));
			}
		}
	}

	private void getInfoFromClientFile(File clientFile, final Integer uid,
			String fileName) throws MusicRcException {
		String page = AnalysisZipFileUtil.fileUploadUtil(clientFile);
		List<Map> listUserMap = new ArrayList<Map>();
		List<Map> listDateMap = new ArrayList<Map>();
		if (page != null) {
			try {
				Document doc = DocumentHelper.parseText(page);
				if (doc != null) {
					Element rootEle = doc.getRootElement();
					if (rootEle != null) {
						Element userLocalEle = rootEle.element("localfiles");
						if (userLocalEle != null) {
							List userListEle = userLocalEle.elements("date");
							Iterator iter = userListEle.iterator();
							while (iter.hasNext()) {
								Map dataMap = new HashMap();
								Element dataEle = (Element) iter.next();
								Attribute idAttr = dataEle.attribute("id");
								Attribute countAttr = dataEle
										.attribute("count");
								String idStr = idAttr.getValue();
								String countStr = countAttr.getValue();
								int id = 0;
								int count = 0;
								try {
									id = Integer.parseInt(idStr);
									count = Integer.parseInt(countStr);
								} catch (Exception e) {
									if (log.isDebugEnabled())
										log.debug(e.getMessage(), e);
									else
										log.error(e.getMessage());
									throw new MusicRcException(
											MusicRcException.FILE_ANA);
								}
								dataMap.put("id", id);
								dataMap.put("count", count);
								listUserMap.add(dataMap);
							}
						}
						List dateListEles = rootEle.elements("seg");
						Iterator dateIter = dateListEles.iterator();
						while (dateIter.hasNext()) {
							Map dateMap = new HashMap();
							Element dateEles = (Element) dateIter.next();
							Attribute dateAttr = dateEles.attribute("date");
							String day = dateAttr.getValue();
							List dataList = dateEles.elements("date");
							Iterator iter = dataList.iterator();
							List<Map> dataListMap = new ArrayList<Map>();
							while (iter.hasNext()) {
								Map dataMap = new HashMap();
								Element dataEle = (Element) iter.next();
								Attribute idAttr = dataEle.attribute("id");
								Attribute countAttr = dataEle
										.attribute("count");
								String idStr = idAttr.getValue();
								String countStr = countAttr.getValue();
								int id = 0;
								int count = 0;
								try {
									id = Integer.parseInt(idStr);
									count = Integer.parseInt(countStr);
								} catch (Exception e) {
									if (log.isDebugEnabled())
										log.debug(e.getMessage(), e);
									else
										log.error(e.getMessage());
									throw new MusicRcException(
											MusicRcException.FILE_ANA);
								}
								dataMap.put("id", id);
								dataMap.put("count", count);
								dataMap.put("day", day);
								dataListMap.add(dataMap);
							}
							dateMap.put("list", dataListMap);
							listDateMap.add(dateMap);
						}
					}
				}
			} catch (Exception e) {
				if (log.isDebugEnabled())
					log.debug(e.getMessage(), e);
				else
					log.error(e.getMessage());
				throw new MusicRcException(MusicRcException.FILE_ANA);
			}
		}
		try {
			// 查询数据库，决定是否进行插入操作
			Map map = new HashMap();
			map.put("fileName", fileName);
			map.put("uid", uid);
			int status = metadataDao.getClientBehaviorFileStatus(map);
			if (status == Constants.CLIENT_NOT_HAVE_FILE) {
				metadataDao.insertClientBehaviorFileStatus(map);
				for (int i = 0; i < listUserMap.size(); i++) {
					Map userMap = listUserMap.get(i);
					userMap.put("uid", uid);
					metadataDao.insertClientUserData(userMap);
				}
				for (int i = 0; i < listDateMap.size(); i++) {
					Map dateMap = listDateMap.get(i);
					List dataList = (List) dateMap.get("list");
					for (int j = 0; j < dataList.size(); j++) {
						Map dataMap = (Map) dataList.get(j);
						dataMap.put("uid", uid);
						metadataDao.insertClientUserBehavior(dataMap);
					}

				}
				map.put("status", Constants.CLIENT_HAVE_FILE);
				// 执行完毕，修改状态
				metadataDao.updateClientBehaviorFileStatus(map);
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug(e.getMessage(), e);
			else
				log.error(e.getMessage());
			throw new MusicRcException(MusicRcException.OPERATE_DB_ERROR);
		}
	}

	public List<String> getReglist() {
		return reglist;
	}

	public void setReglist(List<String> reglist) {
		this.reglist = reglist;
	}

	public New_MvListEntry getSongMvEntryList(LookupMvInfo info) {
		New_MvListEntry result = new New_MvListEntry();
		List<New_MvEntry> entryList = new ArrayList<New_MvEntry>();
		result.setMv(entryList);
		ServiceUtils.pageCompute(info);
		List<Long> songIdList = new ArrayList<Long>();
		List<SongMv> songMvList = new ArrayList<SongMv>();
		if(info.getSongid()==0&&(Validator.isBlank(info.getSong())&&Validator.isBlank(info.getArtist())&&Validator.isBlank(info.getAlbum())&&Validator.isBlank(info.getFilename()))){
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		if (info.getSongid() > 0) {
			songIdList.add(info.getSongid());
		} else {
			info.setMvflag(1);
			songIdList = this.getSearchSongIdList(info);
		}
		if (songIdList.size() > 0) {
			info.setSongIdList(songIdList);
			songMvList = metadataDao.getSongMvInfoBySongIdList(info);
		}
		List<SongMv> songMvListIncludeArtist = new ArrayList<SongMv>();
		if (songMvList.size() > 0) {
			// 过滤
			if (!Validator.isBlank(info.getArtist())) {
				for (SongMv songMV : songMvList) {
					String artistName = songMV.getArtistName();
					if (!Validator.isBlank(artistName)
							&& artistName.indexOf(info.getArtist()) != -1) {
						songMvListIncludeArtist.add(songMV);
					}
				}
				if (songMvListIncludeArtist.size() == 0) {
					songMvListIncludeArtist = songMvList;
				}
			} else {
				songMvListIncludeArtist = songMvList;
			}
		}
		long artistId = 0l;
		if (info.getArtistmvflag() == 1) {
			if (!Validator.isBlank(info.getArtist())) {
				Artist artist = metadataDao.getArtistInfoByArtistIdOrName(0L,
						info.getArtist());
				if (artist != null) {
					artistId = artist.getArtistId();
				}
			}
			if (songMvListIncludeArtist.size() > 0 && artistId == 0) {
				artistId = songMvListIncludeArtist.get(0).getArtistId();
			}
			info.setArtistid(artistId);
			List<SongMv> artistSongMvList = new ArrayList<SongMv>();
			if (songIdList.size() > 0) {
				info.setSongIdList(songIdList);
				artistSongMvList = metadataDao
						.getSongMvInfoByArtistIdExceptSongIdList(info);
			}
			if (artistSongMvList.size() > 0) {
				songMvListIncludeArtist = ServiceUtils.sortListEntry(
						songMvListIncludeArtist, artistSongMvList);
			}
		}
		if (info.getAlbummvflag() == 1) {
			String artistName = null;
			String albumTitle = null;
			if (!Validator.isBlank(info.getAlbum())
					&& !Validator.isBlank(info.getArtist())) {
				artistName = info.getArtist();
				albumTitle = info.getAlbum();
			} else {
				if(songIdList.size()>0){
					long songId = songIdList.get(0);
					Song song = metadataDao.getSongInfoBySongId(songId, 1, 0);
					if (song != null) {
						albumTitle = song.getAlbumTitle();
						artistName = song.getArtistName();
					}
				}
			}
			if (!Validator.isBlank(albumTitle)
					&& !Validator.isBlank(artistName)) {
				info.setArtist(artistName);
				info.setAlbum(albumTitle);
				info.setSongIdList(songIdList);
				List albumSongMvList = metadataDao
						.getSongMvInfoByArtistAndAlbumExceptSongIdList(info);
				if (albumSongMvList.size() > 0) {
					songMvListIncludeArtist = ServiceUtils.sortListEntry(
							songMvListIncludeArtist, albumSongMvList);
				}
			}
		}
		if (songMvListIncludeArtist.size() > 0) {
			for (int i = 0; i < songMvListIncludeArtist.size(); i++) {
				New_MvEntry mvEntry = new New_MvEntry(
						songMvListIncludeArtist.get(i));
				entryList.add(i, mvEntry);
			}
			entryList = ServiceUtils.removeIterativeSongIdForMvEntry(entryList);
			entryList = ServiceUtils.removeIterativeMvIdForMvEntry(entryList);
			if (info.getAllcountflag() == 1) {
				result.setAllcount(entryList.size());
			}
			entryList = ServiceUtils.outSidePaging(entryList,
					info.getPagecount(), info.getPage());
		}
		result.setMv(entryList);
		return result;
	}

	public New_MvResponse lookupmvs(LookupMvInfo info) {
		New_MvResponse response = new New_MvResponse();
		response.setMvlist(getSongMvEntryList(info));
		return response;
	}

	public MetadataDao getMetadataDao() {
		return metadataDao;
	}

	public void setMetadataDao(MetadataDao metadataDao) {
		this.metadataDao = metadataDao;
	}
	@Deprecated
	public New_MtvUriListResponse getmtvuri(GetMvUriInfo info) {
		New_MtvUriListResponse response=new New_MtvUriListResponse();
		List<New_MtvUriEntry> mtvUriEntryList=new ArrayList<New_MtvUriEntry>();
		response.setUri(mtvUriEntryList);
		info.setMvid(info.getMtvid());
		New_MvUriListResponse mvResponse=this.getmvuri(info);
		List<New_MvUriEntry> mvUriEntryList=mvResponse.getUri();
		for(int i=0;i<mvUriEntryList.size();i++){
			New_MvUriEntry mvUriEntry=mvUriEntryList.get(i);
			New_MtvUriEntry mtvUriEntry=new New_MtvUriEntry();
			mtvUriEntry.setMtvid(mvUriEntry.getMvid());
			mtvUriEntry.setSongid(mvUriEntry.getSongid());
			mtvUriEntry.setType(mvUriEntry.getType());
			mtvUriEntry.setUri(mvUriEntry.getUri());
			mtvUriEntry.setValue(mvUriEntry.getValue());
			mtvUriEntryList.add(mtvUriEntry);
		}
		return response;
	}
	// new mv
	public New_MvUriListResponse getmvuri(GetMvUriInfo info) {
		New_MvUriListResponse response = new New_MvUriListResponse();
		List<New_MvUriEntry> result = new ArrayList<New_MvUriEntry>();
		response.setUri(result);
		long songId = info.getSongid();
		int mvId = info.getMvid();
		if (songId == 0 && mvId == 0) {
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		SongMv songMv=null;
		if (mvId > 0) {
			songMv = metadataDao.getMvUriDownloadInfoByMvId(mvId);
		} else if (ServiceUtils.validateLongNotEquals(songId)) {
			long mvIds=0;
			int [] sequenceType={8,5,4,6,7,1,2,3};
			List<SongMv> songMvList = metadataDao.getMvIdAndTypeListBySongId(songId);
			boolean flag=false;
			for(int i=0;i<sequenceType.length;i++){
				if(!flag){
					for(int j=0;j<songMvList.size();j++){
						if(songMvList.get(j).getMvType()==sequenceType[i]){
							mvIds=songMvList.get(j).getMvId();
							flag=true;
							break;
						}
					}
				}
			
			}
			songMv = metadataDao.getMvUriDownloadInfoByMvId(mvIds);
		}
		if(songMv!=null){
			int uriType = ServiceUtils.getMvUriType(songMv.getDataFrom());
			songMv.setUriType(uriType);
			New_MvUriEntry due = new New_MvUriEntry(songMv);
			result.add(due);
		}
		return response;
	}

	/**
	 * 老的mv接口
	 */
	@Deprecated
	public New_GetMtvInfoResponse getmtvinfo(GetMvInfo info) {
		New_GetMtvInfoResponse response = new New_GetMtvInfoResponse();
		List<New_GetMtvInfoEntry> result=new ArrayList<New_GetMtvInfoEntry>();
		response.setMtvinfo(result);
		info.setMvid(info.getMtvid());
		info.setMvartsize(info.getMtvartsize());
		New_GetMvInfoResponse mvResponse=this.getmvinfo(info);
		List<New_MvEntry> mvEntryList=mvResponse.getMvinfo();
		for(int i=0;i<mvEntryList.size();i++){
			New_MvEntry mvEntry=mvEntryList.get(i);
			New_GetMtvInfoEntry getMTVInfoEntry=new New_GetMtvInfoEntry();
			getMTVInfoEntry.setArtist(mvEntry.getArtist());
			getMTVInfoEntry.setArtistid(mvEntry.getArtistid());
			getMTVInfoEntry.setDuration(mvEntry.getDuration());
			getMTVInfoEntry.setMtvart(mvEntry.getMvart());
			getMTVInfoEntry.setMtvid(mvEntry.getMvid());
			getMTVInfoEntry.setSongid(mvEntry.getSongid());
			getMTVInfoEntry.setTitle(mvEntry.getTitle());
			result.add(getMTVInfoEntry);
		}
		return response;
	}


	public New_MvResponse listmvs(ListMvInfo info) {
		New_MvResponse response = new New_MvResponse();
		New_MvListEntry result = new New_MvListEntry();
		response.setMvlist(result);
		ServiceUtils.specialPageCompute(info);
		long artistId = 0l;
		if (info.getArtistid() > 0) {
			artistId = info.getArtistid();
		} else if (!Validator.isBlank(info.getArtist())) {
			Artist artist = metadataDao.getArtistInfoByArtistIdOrName(0,
					info.getArtist());
			if(artist!=null){
				artistId = artist.getArtistId();
			}
		}
		info.setArtistid(artistId);
		List<New_MvEntry> mvEntryList = new ArrayList<New_MvEntry>();
		List<SongMv> songMvList = metadataDao.getSongMvInfoByArtistId(info);
		if (songMvList.size() > 0) {
			for (int i = 0; i < songMvList.size(); i++) {
				New_MvEntry entry = new New_MvEntry(songMvList.get(i));
				mvEntryList.add(i, entry);
			}
		}
		result.setMv(mvEntryList);
		int totalSize = metadataDao.getSongMvInfoAllCount(info);
		result.setTotalsize(totalSize);
		return response;
	}
	
	@Deprecated
	public New_MtvResponse listmtvs(ListMvInfo info){
		New_MtvResponse response = new New_MtvResponse();
		New_MtvEntryList result=new New_MtvEntryList();
		response.setMtvlist(result);
		List<New_MtvEntry> mtvEntryList=new ArrayList<New_MtvEntry>();
		result.setMtv(mtvEntryList);
		New_MvResponse mvResponse=this.listmvs(info);
		List<New_MvEntry> mvEntryList=mvResponse.getMvlist().getMv();
		for(int i=0;i<mvEntryList.size();i++){
			New_MvEntry mvEntry=mvEntryList.get(i);
			SongMv songMv=mv2mtvEntryCopy(mvEntry);
			New_MtvEntry mtvEntry=new New_MtvEntry(songMv);
			mtvEntryList.add(i, mtvEntry);
		}
		int allCount=mvResponse.getMvlist().getTotalsize();
		result.setTotalsize(allCount);
		return response;
	}


	@Deprecated
	public New_MtvResponse lookupmtvs(LookupMvInfo info) {
		New_MtvResponse response = new New_MtvResponse();
		New_MtvEntryList result=new New_MtvEntryList();
		response.setMtvlist(result);
		List<New_MtvEntry> mtvEntryList=new ArrayList<New_MtvEntry>();
		result.setMtv(mtvEntryList);
		info.setArtistmvflag(info.getArtistmtvflag());
		info.setAlbummvflag(info.getAlbummtvflag());
		New_MvResponse mvResponse=this.lookupmvs(info);
		Integer allCount=mvResponse.getMvlist().getAllcount();
		result.setAllcount(allCount);
		List<New_MvEntry> mvEntryList=mvResponse.getMvlist().getMv();
		for(int i=0;i<mvEntryList.size();i++){
			New_MvEntry mvEntry=mvEntryList.get(i);
			SongMv songMv=mv2mtvEntryCopy(mvEntry);
			New_MtvEntry mtvEntry=new New_MtvEntry(songMv);
			mtvEntryList.add(i, mtvEntry);
		}
		return response;
	}

	public SongMv mv2mtvEntryCopy(New_MvEntry mvEntry){
		SongMv songMv=new SongMv();
		songMv.setArtistName(mvEntry.getArtist());
		songMv.setArtistId(mvEntry.getArtistid());
		songMv.setSongId(mvEntry.getSongid());
		songMv.setDuration(mvEntry.getDuration());
		songMv.setSongMvThumb(mvEntry.getMvart());
		songMv.setMvId(mvEntry.getMvid());
		songMv.setMvTitle(mvEntry.getTitle());
		return songMv;
	}

	public New_ArtistListResponse listartists(ListArtistInfo info) {
		New_ArtistListResponse response = new New_ArtistListResponse();
		New_ArtistListEntry result = new New_ArtistListEntry();
		response.setArtistslist(result);
		ServiceUtils.specialPageCompute(info);
		int sort = info.getSort();
		// 做为 人气、最新的查询条件
		if (sort ==Constants.LIST_ARTIST_SORT_POPULARITY) {
			info.setSort(Constants.LIST_ARTIST_SORT_POPULARITY);
		} else {
			info.setSort(Constants.LIST_ARTIST_SORT_NEWEST);
		}
		List<Integer> styleList = new ArrayList<Integer>();
		if (!Validator.isBlank(info.getStyles())) {
			styleList = ServiceUtils.splitInteger(info.getStyles());
		}
		if (styleList.size() > 0) {
			info.setStyleList(styleList);
		}
		List<Integer> regionList = new ArrayList<Integer>();
		if (!Validator.isBlank(info.getRegions())) {
			regionList = ServiceUtils.splitInteger(info.getRegions());
		}
		if (regionList.size() > 0) {
			info.setRegionList(regionList);
		}
		List<Artist> artistList = metadataDao.getListArtistInfo(info);
		if (artistList.size() > 0) {
			List<New_ArtistEntry> artistListEntry = new ArrayList<New_ArtistEntry>();
			for (int i = 0; i < artistList.size(); i++) {
				New_ArtistEntry entry = new New_ArtistEntry(artistList.get(i));
				artistListEntry.add(i,entry);
			}
			result.setArtist(artistListEntry);
			if (info.getAllcountflag() == 1) {
				// int allCount=metadataDao.getListArtistInfoAllCount(info);
				result.setAllcount(info.getPagecount());
			}
		}
		return response;
	}

	public New_StatResponse statclientusage(StatClientUsageInfo info) {
		New_StatResponse baseResponse = new New_StatResponse();
		String fileName = info.getFilename().trim();
		if (Validator.isBlank(fileName)) {
			throw new MusicRcException(MusicRcException.INVALID_FILENAME);
		}
		if (info.getFile() != null && info.getFile().length > 0) {
			File[] clientFile = info.getFile();
			for (int i = 0; i < clientFile.length; i++) {
				getInfoFromClientFile(clientFile[i], info.getUid(), fileName);
			}
		}
		return baseResponse;
	}

	public New_StatResponse uploadmetadatas(UploadmetadataInfo info) {
		New_StatResponse response = new New_StatResponse();
		if (info.getFile() != null && info.getFile().length > 0) {
			File[] clientFile = info.getFile();
			for (int i = 0; i < clientFile.length; i++) {
				uploadMetadata(clientFile[i], info.getUid());
			}
		}
		return response;
	}
	
	public New_SongListResponse jlucenesearchsongs(GetSongInfo info) {
		New_SongListResponse response=new New_SongListResponse();
		New_SongListEntry result=new New_SongListEntry();
		response.setSongslist(result);
		ServiceUtils.pageCompute(info);
		SolrSong solrSong=new SolrSong();
		solrSong.setStart(new Long(info.getStart()));
		solrSong.setPagecount(new Long(info.getPagecount()));
		solrSong.setSongtitle(info.getSong());
		solrSong.setArtistname(info.getArtist());
		solrSong.setAlbumtitle(info.getAlbum());
		try {
			List<Long> songIdList=jluceneSearch.searchSong(solrSong);
			List<Song> songList=metadataDao.getSongListByIds(songIdList);
			songList=ServiceUtils.sortSongRandomList(songIdList,songList);
			if(songList.size()>0){
				List<New_SongEntry> listEntry = new ArrayList<New_SongEntry>();
				for (int i = 0; i < songList.size(); i++) {
					New_SongEntry songEntry = new New_SongEntry(
							songList.get(i));
					listEntry.add(i, songEntry);
				}
				result.setSongscount(listEntry.size());
				result.setSong(listEntry);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return response;
	}
	public New_ArtistListResponse jlucenesearchartists(GetSongInfo info) {
		New_ArtistListResponse response=new New_ArtistListResponse();
		New_ArtistListEntry result=new New_ArtistListEntry();
		response.setArtistslist(result);
		ServiceUtils.pageCompute(info);
		if(!Validator.isBlank(info.getArtist())){
			SolrArtist solrArtist=new SolrArtist();
			solrArtist.setArtistname(info.getArtist());
			solrArtist.setStart(new Long(info.getStart()));
			solrArtist.setPagecount(new Long(info.getPagecount()));
			try {
				List<New_ArtistEntry> artistListEntry=new ArrayList<New_ArtistEntry>();
				result.setArtist(artistListEntry);
				List<Long> artistIdList=jluceneSearch.searchArtist(solrArtist);
				List<Artist> artistList=metadataDao.getArtistListByIds(artistIdList);
				for(int i=0;i<artistList.size();i++){
					New_ArtistEntry entry=new New_ArtistEntry(artistList.get(i));
					artistListEntry.add(i, entry);
				}
				result.setArtistscount(artistListEntry.size());
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		}
		return response;
	}
	public New_AlbumResponse jlucenesearchalbums(GetSongInfo info) {
		New_AlbumResponse response=new New_AlbumResponse();
		New_AlbumListEntry result=new New_AlbumListEntry();
		response.setAlbumslist(result);
		ServiceUtils.pageCompute(info);
		if(!Validator.isBlank(info.getAlbum())){
			try {
				List<New_AlbumEntry> albumEntryList=new ArrayList<New_AlbumEntry>();
				result.setAlbum(albumEntryList);
				SolrAlbum solrAlbum=new SolrAlbum();
				solrAlbum.setAlbumtitle(info.getAlbum());
				solrAlbum.setStart(new Long(info.getStart()));
				solrAlbum.setPagecount(new Long(info.getPagecount()));
				List<Long> albumIdList=jluceneSearch.searchAlbum(solrAlbum);
				List<Album> albumList=metadataDao.getAlbumListByIds(albumIdList);
				for(int i=0;i<albumList.size();i++){
					New_AlbumEntry entry=new New_AlbumEntry(albumList.get(i));
					albumEntryList.add(i, entry);
				}
				result.setAllcount(albumEntryList.size());
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		}
		return response;
	}

	public New_SongListResponse jlucenesearchkeywords(SearchSongInfo info) {
		New_SongListResponse response=new New_SongListResponse();
		New_SongListEntry result=new New_SongListEntry();
		response.setSongslist(result);
		SolrSong solrSong=new SolrSong();
		ServiceUtils.pageCompute(info);
		try {
			List<Long> songIdList=jluceneSearch.searchKeyWord(info.getKeyname(), info.getStart(), info.getPagecount());
			List<Song> songList=metadataDao.getSongListByIds(songIdList);
			songList=ServiceUtils.sortSongRandomList(songIdList,songList);
			if(songList.size()>0){
				List<New_SongEntry> listEntry = new ArrayList<New_SongEntry>();
				for (int i = 0; i < songList.size(); i++) {
					New_SongEntry songEntry = new New_SongEntry(
							songList.get(i));
					listEntry.add(i, songEntry);
				}
				result.setSongscount(listEntry.size());
				result.setSong(listEntry);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return response;
	}
}
