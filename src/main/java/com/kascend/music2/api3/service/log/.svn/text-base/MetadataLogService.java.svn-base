package com.kascend.music2.api3.service.log;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kascend.common.util.Validator;
import com.kascend.music2.api3.exception.MusicConst;
import com.kascend.music2.api3.exception.MusicRcException;
import com.kascend.music2.api3.service.log.action.GetSongInfoLogAction;
import com.kascend.music2.api3.service.log.action.LogAction;
import com.kascend.music2.api3.service.log.action.SearchKeywordLogAction;
import com.kascend.music2.api3.service.metadata.info.FeedBackSongInfo;
import com.kascend.music2.api3.service.metadata.info.GetMvInfo;
import com.kascend.music2.api3.service.metadata.info.GetMvUriInfo;
import com.kascend.music2.api3.service.metadata.info.GetSongInfo;
import com.kascend.music2.api3.service.metadata.info.MusicInfo;
import com.kascend.music2.api3.service.metadata.info.SearchAlbumInfo;
import com.kascend.music2.api3.service.metadata.info.SearchArtistInfo;
import com.kascend.music2.api3.service.metadata.info.SearchSongInfo;
import com.kascend.music2.api3.service.metadata.info.StatInfo;
import com.kascend.music2.api3.service.metadata.info.TopInfo;
import com.kascend.music2.api3.service.metadata.response.New_AlbumResponse;
import com.kascend.music2.api3.service.metadata.response.New_ArtistListResponse;
import com.kascend.music2.api3.service.metadata.response.New_GetSongInfoResponse;
import com.kascend.music2.api3.service.metadata.response.New_SongListResponse;
import com.kascend.music2.api3.service.metadata.response.entry.New_AlbumEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_ArtistEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_GetSongInfoEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_SongEntry;
import com.kascend.music2.api3.service.util.Constants;


public class MetadataLogService extends BaseLogService {
	
	
	public void saveBillboardLog(TopInfo info,int resultSize){
		if(resultSize>0){
			Map<Object,Object> map = new HashMap<Object,Object>();
			Integer appid = this.getAppId(info.getAppkey());
			int appids = (appid == null ? 1 : appid);
			map.put("appid", appids);
			map.put("billboardid", info.getBillboardid());
			map.put("subbillboardid", info.getSubbillboardid());
			map.put("uid",info.getUid());
			map.put("logTime", new Date().getTime()/1000);
			LogAction logAction=new LogAction("metadata_log.saveBillboardLog", map);
			logCommitExecutor.add(logAction);
		}
	}
	

	private Integer getAppId(String appkey) {
		Integer appid = this.getDao().queryForObject("metadata_log.getAppId", appkey);
		return appid;
	}

	public void saveSongLog(Integer uid,long songId,int operateType){
		if(uid==null)uid=1;
		if( songId > 0){
			Map<Object,Object>  map = new HashMap<Object,Object>();
			map.put("uid", uid);
			map.put("songid", songId);
			map.put("action", operateType);
			map.put("createTime", new Date());
			LogAction logAction=new LogAction("metadata_log.SavesongLog", map);
			logCommitExecutor.add(logAction);
		}
	}
	
	public void saveStatLogAction(StatInfo info){
		
		if(info.getSongid()>0){
			info.setCreateTime(new Date());
			LogAction logAction=new LogAction("metadata_log.SaveStatLog", info);
			logCommitExecutor.add(logAction);
		}
		
	}
	public void saveFeedBackSongAction(FeedBackSongInfo info){
		if(info.getSongid()>0){
			info.setCreateTime(new Date());
			LogAction logAction=new LogAction("metadata_log.SaveFeedBackSongLog", info);
			logCommitExecutor.add(logAction);
		}
	}
	/**
	 * songlog 保存getsonginfo日志信息
	 * */
	public void saveSongLog4GetSongInfo(GetSongInfo info,int operateType) {
		int uid =1;
		if (info.getUid() != null) {
			uid = info.getUid();
		}
		if (info.getSongid() > 0) {
			saveSongLog(uid,info.getSongid(),operateType);
		}
	}
	public void saveSongLog4GetMvInfo(GetMvInfo info,int operateType){
		int uid =1;
		if (info.getUid() != null) {
			uid = info.getUid();
		}
		if (info.getSongid() > 0) {
			saveSongLog(uid,info.getSongid(),operateType);
		}
	}
	/**
	 * 保存下载日志
	 * */
	public void saveSongdownloadlog(MusicInfo info) {
		Map<Object,Object> downmap = new HashMap<Object,Object>();
		Integer uid = 1;
		if (info.getUid() != null) {
			uid = info.getUid();
		}
		downmap.put("uid", uid);
		downmap.put("songid", info.getSongid());
		downmap.put("action", Constants.OPERATOR_DOWNLOAD);
		LogAction logAction=new LogAction("metadata_log.saveDownLoadLog", downmap);
		logCommitExecutor.add(logAction);
	}
	
	/**
	 * 保存下载日志
	 * */
	public void saveSongDownloadLog(int uid,long songId) {
		Map<Object,Object> downmap = new HashMap<Object,Object>();
		downmap.put("uid", uid);
		downmap.put("songid", songId);
		downmap.put("action", Constants.OPERATOR_DOWNLOAD);
		LogAction logAction=new LogAction("metadata_log.saveDownLoadLog", downmap);
		logCommitExecutor.add(logAction);
	}
	public void saveGetMvInfoLog(GetMvInfo info,long songId){
		if(songId>0){
			info.setSongid(songId);
			saveSongLog4GetMvInfo(info, Constants.OPERATOR_DOWNLOAD_PIC);
		}
	}
	@SuppressWarnings("unchecked")
	public void saveGetSongInfoLog(GetSongInfo info,New_GetSongInfoResponse response,long songId){
		if (songId > 0) {
			if (info.getLyricflag()==1) {//TODO
				info.setSongid(songId);
				this.saveSongLog4GetSongInfo(info, Constants.OPERATOR_DOWNLOAD_LYRIC);
			}
			if ((info.getAlbumartflag() == 1)
					|| (info.getArtistartflag() == 1)) {
				info.setSongid(songId);
				this.saveSongLog4GetSongInfo(info, Constants.OPERATOR_DOWNLOAD_PIC);
			}
		}
		List<New_GetSongInfoEntry> songinfolist=response.getSonginfo();
		if (info.getSongid()==0) {
			int resultSize = songinfolist.size();
			Map countMap = new HashMap();
			String songTitle = info.getSong();
			String albumTitle = info.getAlbum();
			String artistName = info.getArtist();
			String fileName = info.getFilename();
			if (Validator.isBlank(songTitle)) {
				songTitle = null;
			}
			if (Validator.isBlank(info.getArtist())) {
				artistName = null;
			}
			if (Validator.isBlank(info.getFilename())) {
				fileName = null;
			}
			if (Validator.isBlank(info.getAlbum())) {
				albumTitle = null;
			}
			countMap.put("songTitle", songTitle);
			countMap.put("artistName", artistName);
			countMap.put("albumTitle", albumTitle);
			countMap.put("fileName", fileName);
			countMap.put("resultSize", resultSize);
			GetSongInfoLogAction logAction=new GetSongInfoLogAction(countMap);
			logCommitExecutor.add(logAction);
		}
		songinfolist = null;
	}

	private void saveSearchKeyWord(String keyword, int searchtype) {
		if (!Validator.isBlank(keyword)) {
			Map m = new HashMap();
			m.put("keyword", keyword);
			m.put("searchtype", searchtype);
			m.put("createTime", new Date());
			SearchKeywordLogAction logAction=new SearchKeywordLogAction(m);
			logCommitExecutor.add(logAction);
		}
	}
	
	public void saveSearchSongsLog(SearchSongInfo info,New_SongListResponse response){
		String keyword = info.getKeyname();
		if (keyword != null) {
			if(response.getSongslist()!=null&&response.getSongslist().getSong()!=null){
				List<New_SongEntry> songlist=response.getSongslist().getSong();
				if (songlist != null && songlist.size() > 0) {
					this.saveSearchKeyWord(keyword,
							MusicConst.MUSIC_SEARCH_TYPE_SONG);
				}
			}
		}
	}
	
	public void saveSearchArtistlog(SearchArtistInfo info,New_ArtistListResponse response){
		String keyword = info.getKeyname();
		if (keyword != null) {
			List<New_ArtistEntry> artistlist=response.getArtistslist().getArtist();
			if (artistlist != null && artistlist.size() > 0) {
				this.saveSearchKeyWord(keyword, MusicConst.MUSIC_SEARCH_TYPE_ARTIST);
			}
		}
	}


	
	public void saveSearchAlbumLog(SearchAlbumInfo info,New_AlbumResponse response){
		String keyword = info.getKeyname();
		if (!Validator.isBlank(keyword)) {
			if(response!=null&&response.getAlbumslist()!=null&&response.getAlbumslist().getAlbum()!=null){
				List<New_AlbumEntry> albumEntryList=response.getAlbumslist().getAlbum();
				if(albumEntryList.size()>0){
					this.saveSearchKeyWord(keyword, MusicConst.MUSIC_SEARCH_TYPE_ALBUM);
				}
			}
		}
	}
	
	public void saveGetMvUriLog(GetMvUriInfo info){
		 long songId=info.getSongid();
		 int mvId=info.getMvid();
		 Map logMap=new HashMap();
		 Integer uid = 1;
		 if (info.getUid() != null) {
				uid = info.getUid();
		 }
		 logMap.put("uid", uid);
		 logMap.put("songId", songId);
		 logMap.put("mvId",mvId);
		 logMap.put("logTime", new Date().getTime()/1000);
		 LogAction logAction=new LogAction("metadata_log.saveSongMvLog", logMap);
	     logCommitExecutor.add(logAction);
	}

	
	public void saveStatLog(StatInfo info){
		Integer stattype = info.getStattype();
		if (stattype != 1 && stattype != 2) {
			throw new MusicRcException(MusicRcException.STATTYPEERROR);
		}
		if (info.getSongid()>0) {
			if (stattype == 1) {
				info.setOperateType(Constants.OPERATOR_PLAY);
				saveStatLogAction(info);
			} else if (stattype == 2) {
				info.setOperateType(Constants.OPERATOR_DOWNLOAD);
				saveSongDownloadLog(info.getUid(),info.getSongid());
				saveStatLogAction(info);
			}
		}
	}
	
	public void saveFeedBackSongLog(FeedBackSongInfo info){
		if(info.getSongid()>0){
			String param=info.getErrorcode()+"|"+info.getUritype()+"|"+info.getUri();
			info.setParam(param);
			info.setOperateType(Constants.OPERATOR_PLAY);
			saveFeedBackSongAction(info);
		}
	}
}
