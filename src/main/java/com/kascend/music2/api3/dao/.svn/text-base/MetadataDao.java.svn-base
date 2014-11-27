package com.kascend.music2.api3.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.kascend.music2.api3.service.metadata.info.CommonArtistInfo;
import com.kascend.music2.api3.service.metadata.info.CommonGetAlbumInfo;
import com.kascend.music2.api3.service.metadata.info.DiscoveryInfo;
import com.kascend.music2.api3.service.metadata.info.GetAlbumInfo;
import com.kascend.music2.api3.service.metadata.info.GetAlbumsOfArtistInfo;
import com.kascend.music2.api3.service.metadata.info.GetAlbumsOfSongInfo;
import com.kascend.music2.api3.service.metadata.info.GetDownloadUriInfo;
import com.kascend.music2.api3.service.metadata.info.GetRandomSongInfo;
import com.kascend.music2.api3.service.metadata.info.GetSearchKeywordInfo;
import com.kascend.music2.api3.service.metadata.info.GetSimilarSongInfo;
import com.kascend.music2.api3.service.metadata.info.GetSongInfo;
import com.kascend.music2.api3.service.metadata.info.GetSongsOfAlbumInfo;
import com.kascend.music2.api3.service.metadata.info.GetSongsOfArtistInfo;
import com.kascend.music2.api3.service.metadata.info.ListBillboardInfo;
import com.kascend.music2.api3.service.metadata.info.ListMvInfo;
import com.kascend.music2.api3.service.metadata.info.LookupMvInfo;
import com.kascend.music2.api3.service.metadata.info.RecommendArtistInfo;
import com.kascend.music2.api3.service.metadata.info.RecommendalbumInfo;
import com.kascend.music2.api3.service.metadata.info.SearchAlbumInfo;
import com.kascend.music2.api3.service.metadata.info.SearchArtistInfo;
import com.kascend.music2.api3.service.metadata.info.SongUriListInfo;
import com.kascend.music2.api3.service.metadata.info.TopInfo;
import com.kascend.music2.api3.service.metadata.info.TopMvInfo;
import com.kascend.music2.api3.service.util.Constants;
import com.kascend.music2.api3.service.util.ServiceUtils;


public class MetadataDao extends AbstractDao {
	
	public static final String NAME_SPACE="metadata"; 

	@Override
	protected String getNameSpace() {
		return NAME_SPACE;
	}
	//-----------------billboard start-------------------------
	public int getSubBillboardSongCount(long subBillboardId){
		return (Integer)this.dao.queryForObject(super.getStatementName("getSubBillboardSongCount"), subBillboardId);
	}
	public List<Billboard> getBillboardList(ListBillboardInfo listBillboardInfo){
		return (List<Billboard>)this.dao.queryForList(super.getStatementName("getBillboardList"), listBillboardInfo);
	}
	public List<Billboard> getBillboardGroupList(ListBillboardInfo listBillboardInfo){
		return (List<Billboard>)this.dao.queryForList(super.getStatementName("getBillboardGroupList"), listBillboardInfo);
	}
	public List<SubBillboard> getSubBillboardListByBillboardId(TopInfo topInfo){
		return (List<SubBillboard>)this.dao.queryForList(super.getStatementName("getSubBillboardListByBillboardId"),topInfo);
	}
	public int getBillboardGroupSubBillboardCounts(long billboardGroupId){
		return (Integer)this.dao.queryForObject(super.getStatementName("getBillboardGroupSubBillboardCounts"), billboardGroupId);
	}
	public int getBillboardGrouIdByGroupCode(ListBillboardInfo listBillboardInfo){
		int result=0;
		Integer resutInteger=(Integer)this.dao.queryForObject(super.getStatementName("getBillboardGrouIdByGroupCode"), listBillboardInfo);
		if(resutInteger!=null){
			result=resutInteger.intValue();
		}
		return result;
	}
	public Long getBillboardIdBySubBillboardId(TopInfo topInfo){
		Long billboardId= (Long)this.dao.queryForObject(super.getStatementName("getBillboardIdBySubBillboardId"),topInfo);
		if(billboardId==null){
			billboardId= 0L;
		}
		return billboardId;
	}
	public Long getSubBillboardIdByBillboardId(TopInfo topInfo){
		Long subBillboardId= (Long)this.dao.queryForObject(super.getStatementName("getSubBillboardIdByBillboardId"),topInfo);
		if(subBillboardId==null){
			subBillboardId= 0L;
		}
		return subBillboardId;
	}
	public SubBillboard getSubBillboardBySubBillboardId(TopInfo topInfo){
		return (SubBillboard)this.dao.queryForObject(super.getStatementName("getSubBillboardBySubBillboardId"), topInfo);
	}
	public SubBillboard getSubBillboardBySubBillboardIdOfMv(TopInfo topInfo){
		return (SubBillboard)this.dao.queryForObject(super.getStatementName("getSubBillboardBySubBillboardIdOfMv"), topInfo);
	}

	//-----------------billboard end-------------------------
	public int getSearchAlbumInfoByKeyNameAllCount(SearchAlbumInfo searchAlbumInfo){
		return (Integer)this.dao.queryForObject(super.getStatementName("getSearchAlbumInfoByKeyNameAllCount"), searchAlbumInfo);
	}
	
	public int getSearchArtistInfoArtistsCount(SearchArtistInfo searchArtistInfo){
		return (Integer)this.dao.queryForObject(super.getStatementName("getSearchArtistInfoArtistsCount"), searchArtistInfo);
	}
	
	public void clearRecommendUser(){
		this.dao.update(super.getStatementName("clearRecommendUser"));
	}
	
	public void insertRecommendUser(){
		this.dao.insert(super.getStatementName("insertRecommendUser"));
	}
	
	public int getArtistChineseRegion(long artistId){
		Integer region=(Integer)this.dao.queryForObject(super.getStatementName("getArtistChineseRegion"), artistId);
		if(region==null)region=0;
		return region;
	}
	public int getTopMvCounts(long billboardId){
		int result=0;
		Integer resultInteger=(Integer)this.dao.queryForObject(super.getStatementName("getTopMvCounts"), billboardId);
		if(resultInteger!=null){
			result=resultInteger.intValue();
		}
		return result;
	}
	
	public List<String> getSearchKeyWordList(GetSearchKeywordInfo getSearchKeywordInfo){
		return (List<String>)this.dao.queryForList(super.getStatementName("getSearchKeyWordList"), getSearchKeywordInfo);
	}
	
	public List<String> getArtistartListByArtistId(long artistId){
		return (List<String>)this.dao.queryForList(super.getStatementName("getArtistartListByArtistId"), artistId);
	}
	
	public String getLyricBySongId(long songId){
		return (String)this.dao.queryForObject(super.getStatementName("getLyricBySongId"),songId);
	}
	
	public int getAlbumListByArtistIdAllCount(long artistId){
		return (Integer)this.dao.queryForObject(super.getStatementName("getAlbumListByArtistIdAllCount"), artistId);
	}
	
	public int getAlbumListBySongIdAllCount(GetAlbumsOfSongInfo getAlbumsOfSongInfo){
		return (Integer)this.dao.queryForObject(super.getStatementName("getAlbumListBySongIdAllCount"), getAlbumsOfSongInfo);
	}

	public long checkSongIdIfInSongUdata(long songId){
		long songid=0L;
		Long songIds=dao.queryForObject(super.getStatementName("checkSongIdIfInSongUdata"), songId);
		if(songIds!=null){
			songid=songIds;
		}
		return songid;
	}
	
	public void saveSongUdata(long songId){
		dao.insert(super.getStatementName("saveSongUdata"),songId);
	}
	public void updateSongUdataPlayTimes(long songId){
		dao.update(super.getStatementName("updateSongUdataPlayTimes"), songId);
	}
	
	public void updateSongUdataDownloadTimes(long songId){
		dao.update(super.getStatementName("updateSongUdataDownloadTimes"), songId);
	}

	public List<SongFile>  getSongFileIdList(GetDownloadUriInfo getDownloadUriInfo){
		return (List<SongFile>)this.dao.queryForList(super.getStatementName("getSongFileIdList"), getDownloadUriInfo);
	}
	
	public List<Lyric> getLyricListBySongIdList(List<Long> songIdList){
		return (List<Lyric>)this.dao.queryForList(super.getStatementName("getLyricListBySongIdList"), songIdList);
	}

	public Long getRandomSubBillboardId(){
		return (Long)this.dao.queryForObject(super.getStatementName("getRandomSubBillboardId"));
	}
	
	public int getClientBehaviorFileStatus(Map map){
		int result=0;
		Integer resultInteger=(Integer)this.dao.queryForObject(super.getStatementName("getClientBehaviorFileStatus"), map);
		if(resultInteger!=null){
			result=resultInteger.intValue();
		}
		return result;
	}
	
	public void insertClientBehaviorFileStatus(Map map){
		this.dao.insert(super.getStatementName("insertClientBehaviorFileStatus"),map);
	}
	
	public void insertClientUserData(Map map){
		this.dao.insert(super.getStatementName("insertClientUserData"),map);
	}
	
	public void insertClientUserBehavior(Map map){
		this.dao.insert(super.getStatementName("insertClientUserBehavior"),map);
	}
	
	public void updateClientBehaviorFileStatus(Map map){
		this.dao.update(super.getStatementName("updateClientBehaviorFileStatus"),map);
	}
	
	public void saveUploadMetadata(Map map){
		this.dao.insert(super.getStatementName("saveUploadMetadata"),map);
	}
	
	public long getAlbumIdBySongId(long songId){
		long albumId=0;
		Long albumid=(Long)dao.queryForObject(super.getStatementName("getAlbumIdBySongId"), songId);
		if(albumid!=null){
			albumId=albumid;
		}
		return albumId;
	}
	public int getUserOpAlbum(long uid,long albumId,int opType){
		Map map=new HashMap();
		map.put("uid", uid);
		map.put("albumId", albumId);
		map.put("opType", opType);
		return (Integer)dao.queryForObject(super.getStatementName("getUserOpAlbum"),map);
	}
	public int getUsersCountOfUserOpAlbum(long albumId){
		return (Integer)dao.queryForObject(super.getStatementName("getUsersCountOfUserOpAlbum"),albumId);
	}
	public void saveUserOpAlbum(long uid,long albumId,long songId,int opType,long commentId){
		Map map=new HashMap();
		map.put("uid", uid);
		map.put("albumId", albumId);
		map.put("songId", songId);
		map.put("opType", opType);
		map.put("commentId", commentId);
		dao.insert(super.getStatementName("saveUserOpAlbum"),map);
	}
	public void updateUserOpAlbum(long uid,long albumId,long songId,int opType,long commentId){
		Map map=new HashMap();
		map.put("uid", uid);
		map.put("albumId", albumId);
		map.put("songId", songId);
		map.put("opType", opType);
		map.put("commentId", commentId);
		dao.insert(super.getStatementName("updateUserOpAlbum"),map);
	}
	public int getLastListenedAlbumCount(long uid,long passDate,long nowTime){
		Map map=new HashMap();
		map.put("uid", uid);
		map.put("passDate", passDate);
		map.put("nowTime", nowTime);
		return (Integer)dao.queryForObject(super.getStatementName("getLastListenedAlbum"),map);
	}
	public int getAlbumUdataCountByAlbumId(long albumId){
		return (Integer)dao.queryForObject(super.getStatementName("getAlbumUdataCountByAlbumId"),albumId);
	}
	
	public void addAlbumUdata(long albumId,int userCount,int opType){
		Map map=new HashMap();
		map.put("albumId", albumId);
		map.put("userCount", userCount);
		map.put("opType", opType);
		dao.insert(super.getStatementName("addAlbumUdata"),map);
	}
	public void updateAlbumUdata(long albumId,int userCount,int opType){
		Map map=new HashMap();
		map.put("albumId", albumId);
		map.put("userCount", userCount);
		map.put("opType", opType);
		dao.update(super.getStatementName("updateAlbumUdata"),map);
	}
	public void updateUserCountInfo(long uid,long songId,int recentAlbumCount){
		Map map=new HashMap();
		map.put("uid", uid);
		map.put("songId", songId);
		map.put("recentAlbumCount", recentAlbumCount);
		dao.update(super.getStatementName("updateUserCountInfo"),map);
	}

	public List<SongUri> getSongUriListBySongId(SongUriListInfo info) {
		return (List<SongUri>)this.dao.queryForList(super.getStatementName("getSongUriListBySongId"), info);
	}
	
	
	public List<AlbumPic> getAlbumPicsByAlbumIdList(List<Long> albumIdList){
		if(albumIdList.isEmpty()){
			return new ArrayList<AlbumPic>();
		}
		return (List<AlbumPic>)this.dao.queryForList(super.getStatementName("getAlbumPicsByAlbumIdList"), albumIdList);
	}
	
	public List<ArtistPic> getArtistPicsByArtistList(List<Long> artistIdList){
		if(artistIdList.isEmpty()){
			return new ArrayList<ArtistPic>();
		}
		return (List<ArtistPic>)this.dao.queryForList(super.getStatementName("getArtistPicsByArtistList"), artistIdList);
	}
	public List<ArtistPic> getArtistPicsByArtistId(long artistId){
		return (List<ArtistPic>)this.dao.queryForList(super.getStatementName("getArtistPicsByArtistId"), artistId);
	}
	
	public SongFile getSongFileById(long songid, int rate) {
		Map map=new HashMap();
		map.put("songid", songid);
		map.put("rate", rate);
		return this.dao.queryForObject(super.getStatementName("getSongFileById"),  map);
	}
	
	//下载此歌曲和专辑下的其他歌曲
	public void downloadSongFileOfAlbumBySongId(List<Long> songIdList){
		List<Long> songIds=getSongListOfAlbumBySongId(songIdList);
		songIdList.addAll(songIds);
		songIdList=ServiceUtils.removeIterativeOnIdList(songIdList);
		downloadSongFile(songIdList);
	}
	
	public void downloadSongFile(List<Long> songIds){
		if(songIds.isEmpty())return;
		List<Song> songList=this.getUnDownloadSongList(songIds);
		for(int i=0;i<songList.size();i++){
			Song song=songList.get(i);
			long songId=song.getSongId();
			if(song.getSongProperties()==Constants.SONG_DOWNLOAD_NOTIN_LINKLIST){
				//没下载过，添加到下载队列
				//插入up_predown_song
				int preId=this.saveSongDownloadLinkList(songId);
				//修改song的song_properties=1
				if(preId>0){ //插入成功
					this.updateSongProperties(songId, Constants.SONG_DOWNLOAD_IN_LINKLIST);
				}
			}else if(song.getSongProperties()==Constants.SONG_DOWNLOAD_IN_LINKLIST){
				updateSongDownloadLinkList(songId);
			}
		}
	}
	
	public int saveSongDownloadLinkList(long songId){
		int upPredownId=0;
		//确定下载优先级
		Map map=new HashMap();
		map.put("songId", songId);
		map.put("prior",Constants.SONG_DOWNLOAD_PRIOR);
		Integer id=(Integer)dao.insert(super.getStatementName("saveSongDownloadLinkList"), map);
		if(id!=null){
			upPredownId=id;
		}
		return upPredownId;
	}
	//修改下载优先级
	public void updateSongDownloadLinkList(long songId){
		//确定下载优先级
		Map map=new HashMap();
		map.put("songId", songId);
		map.put("prior",Constants.SONG_DOWNLOAD_PRIOR);
		dao.update(super.getStatementName("updateSongDownloadLinkList"), map);
	}
	public void updateSongProperties(long songId,int songProperties){
		Map map=new HashMap();
		map.put("songId", songId);
		map.put("songProperties",songProperties);
		dao.update(super.getStatementName("updateSongProperties"), map);
	}
	
	//--------------List<Long> start-----------------------------
	public List<Long> getRandomArtistId(RecommendArtistInfo recommendArtistInfo){
		return (List<Long>)this.dao.queryForList(super.getStatementName("getRandomArtistId"), recommendArtistInfo);
	}
	
	public List<Long> getSongListOfAlbumBySongId(List<Long> songIdList){
		if(songIdList.isEmpty())return new ArrayList<Long>();
		return (List<Long>)dao.queryForList(super.getStatementName("getSongListOfAlbumBySongId"),songIdList);
	}
	
	public List<Long> generateRandomsSongIds(int mvflag,int pageCount,int albumartflag){
		Map map = new HashMap();
		map.put("mvflag", mvflag);
		map.put("pagecount", 2*pageCount);
		map.put("albumartflag",albumartflag);
		return (List<Long>)this.dao.queryForList(super.getStatementName("generateRandomsSongIds"), map);
	}
	
	public List<Long> checkGetSongInfoBySongIdByFilter(GetSongInfo getSongInfo){
		return  (List<Long>) this.dao.queryForList(super.getStatementName("checkGetSongInfoBySongIdByFilter"), getSongInfo);
	}
	public List<Long> getSearchSongIdList(Map map){
		return  (List<Long>) this.dao.queryForList(super.getStatementName("getSearchSongIdList"), map);
	}
	public List<Long> getSearchSongIdListWithStatus(Map map){
		return  (List<Long>) this.dao.queryForList(super.getStatementName("getSearchSongIdListWithStatus"), map);
	}
	public List<Long> getSearchSongIdListOfGetSongInfo(Map map){
		return  (List<Long>) this.dao.queryForList(super.getStatementName("getSearchSongIdListOfGetSongInfo"), map);
	}
	public List<Long> getSearchMaxSongIdList(Map map){
		return  (List<Long>) this.dao.queryForList(super.getStatementName("getSearchMaxSongIdList"), map);
	}
	//--------------List<Long> end-----------------------------
	
	//------------List<Song> start-------------------
	public List<Song> getSubBillboardSongList(TopInfo topInfo){
		 return (List<Song>) this.dao.queryForList(super.getStatementName("getSubBillboardSongList"), topInfo);
	}
	public List<Song> getRandomSongInfo(GetRandomSongInfo getRandomSongInfo){
		return (List<Song>)this.dao.queryForList(super.getStatementName("getRandomSongInfo"), getRandomSongInfo);
	}
	public List<Song> getCommonSongListBySongIdList(GetSimilarSongInfo getSimilarSongInfo){
		return (List<Song>)this.dao.queryForList(super.getStatementName("getCommonSongListBySongIdList"), getSimilarSongInfo);
	}
	public int getCommonSongListBySongIdListAllCount(GetSimilarSongInfo getSimilarSongInfo){
		return (Integer)this.dao.queryForObject(super.getStatementName("getCommonSongListBySongIdListAllCount"), getSimilarSongInfo);
	}
	//根据歌手id获取歌手的歌曲信息
	public List<Song> getCommonSongListByArtistId(GetSimilarSongInfo getSimilarSongInfo){
		return (List<Song>)this.dao.queryForList(super.getStatementName("getCommonSongListByArtistId"), getSimilarSongInfo);
	}
	public List<Song> getMaxSongListBySongIdList(GetSimilarSongInfo getSimilarSongInfo){
		return (List<Song>)this.dao.queryForList(super.getStatementName("getMaxSongListBySongIdList"), getSimilarSongInfo);
	}
	public List<Song> getSongInfoListByArtistId(GetSongsOfArtistInfo getSongsOfArtistInfo){
		return (List<Song>)this.dao.queryForList(super.getStatementName("getSongInfoListByArtistId"), getSongsOfArtistInfo);
	}
	public List<Song> getHotSongInfoListByArtistId(GetSongsOfArtistInfo getSongsOfArtistInfo){
		return (List<Song>)this.dao.queryForList(super.getStatementName("getHotSongInfoListByArtistId"), getSongsOfArtistInfo);
	}
	public int getHotSongInfoListByArtistIdAllCount(GetSongsOfArtistInfo getSongsOfArtistInfo){
		return (Integer)this.dao.queryForObject(super.getStatementName("getHotSongInfoListByArtistIdAllCount"), getSongsOfArtistInfo);
	}
	public List<Song>  getSongInfoByAlbumId(GetSongsOfAlbumInfo getSongsOfAlbumInfo){
		return (List<Song>)this.dao.queryForList(super.getStatementName("getSongInfoByAlbumId"), getSongsOfAlbumInfo);
	}
	public List<Song>  getSpecialSongInfoByAlbumId(GetSongsOfAlbumInfo getSongsOfAlbumInfo){
		return (List<Song>)this.dao.queryForList(super.getStatementName("getSpecialSongInfoByAlbumId"), getSongsOfAlbumInfo);
	}
	public List<Song>  getSongInfoByAlbumIdList(List<Long> albumIdList){
		if(albumIdList.isEmpty()){
			return new ArrayList<Song>();
		}
		return (List<Song>)this.dao.queryForList(super.getStatementName("getSongInfoByAlbumIdList"),albumIdList);
	}
	public List<Song> getSongListByIds(List<Long> songIds){
		if(songIds.isEmpty()){
			return new ArrayList<Song>();
		}
		return (List<Song>)this.dao.queryForList(super.getStatementName("getSongListByIds"), songIds);
	}
	public List<Song> getSongListByIdsWithStatus(List<Long> songIds){
		if(songIds.isEmpty()){
			return new ArrayList<Song>();
		}
		return (List<Song>)this.dao.queryForList(super.getStatementName("getSongListByIdsWithStatus"), songIds);
	}
	public List<Song> getSongInfoBySongIdList(GetSongInfo getSongInfo){
		return (List<Song>)this.dao.queryForList(super.getStatementName("getSongInfoBySongIdList"), getSongInfo);
	}
	public List<Song> getUnDownloadSongList(List<Long> songIdList){
		if(songIdList.isEmpty()){
			return new ArrayList<Song>();
		}
		return (List<Song>)dao.queryForList(super.getStatementName("getUnDownloadSongList"),songIdList);
	}
	//------------List<Song> end-------------------
	
	//------------List<Album> end-------------------
	public List<Album> getSubBillboardAlbumList(TopInfo topInfo){
		 return (List<Album>) this.dao.queryForList(super.getStatementName("getSubBillboardAlbumList"), topInfo);
	}
	public int getSubBillboardAlbumCount(long subBillboardId){
		return (Integer)this.dao.queryForObject(super.getStatementName("getSubBillboardAlbumCount"), subBillboardId);
	}
	public List<Album> getSearchAlbumInfoByKeyName(SearchAlbumInfo searchAlbumInfo){
		 return (List<Album>) this.dao.queryForList(super.getStatementName("getSearchAlbumInfoByKeyName"), searchAlbumInfo);
	}
	public List<Album> getAlbumListByArtistId(GetAlbumsOfArtistInfo getAlbumsOfArtistInfo){
		return (List<Album>)this.dao.queryForList(super.getStatementName("getAlbumListByArtistId"), getAlbumsOfArtistInfo);
	}
	public List<Album> getAlbumListAndAllSongsByArtistId(GetAlbumsOfArtistInfo getAlbumsOfArtistInfo){
		return (List<Album>)this.dao.queryForList(super.getStatementName("getAlbumListAndAllSongsByArtistId"), getAlbumsOfArtistInfo);
	}
	public List<Album> getAlbumListBySongId(GetAlbumsOfSongInfo getAlbumsOfSongInfo){
		return (List<Album>)this.dao.queryForList(super.getStatementName("getAlbumListBySongId"), getAlbumsOfSongInfo);
	}
	public List<Album> getAlbumListByIds(List<Long> albumIdList){
		if(albumIdList.isEmpty()){
			return new ArrayList<Album>();
		}
		CommonGetAlbumInfo commonInfo=new CommonGetAlbumInfo();
		commonInfo.setAlbumIdList(albumIdList);
		return (List<Album>)this.dao.queryForList(super.getStatementName("getCommonAlbumList"), commonInfo);
	}
	public List<Album> getAlbumListInfoByIds(List<Long> albumIdList){
		if(albumIdList.isEmpty()){
			return new ArrayList<Album>();
		}
		CommonGetAlbumInfo commonInfo=new CommonGetAlbumInfo();
		commonInfo.setAlbumIdList(albumIdList);
		commonInfo.setSongcountflag(Constants.ALBUM_SONG_COUNT_FLAG);
		return (List<Album>)this.dao.queryForList(super.getStatementName("getCommonAlbumList"), commonInfo);
	}
	public List<Album> getAlbumListInfoByIdsWithStatus(List<Long> albumIdList){
		if(albumIdList.isEmpty()){
			return new ArrayList<Album>();
		}
		CommonGetAlbumInfo commonInfo=new CommonGetAlbumInfo();
		commonInfo.setAlbumIdList(albumIdList);
		commonInfo.setSongcountflag(Constants.ALBUM_SONG_COUNT_FLAG);
		commonInfo.setStatusflag(Constants.ALBUM_STATUS_FLAG);
		return (List<Album>)this.dao.queryForList(super.getStatementName("getCommonAlbumList"), commonInfo);
	}
	
	public List<Album> getAlbumListInfoByArtistIdList(RecommendalbumInfo recommendalbumInfo){
		return (List<Album>)this.dao.queryForList(super.getStatementName("getAlbumListInfoByArtistIdList"), recommendalbumInfo);
	}
	//------------List<Album> end-------------------
	//------------List<Artist> start-------------------
	public List<Artist> getSubBillboardArtistList(TopInfo topInfo){
		 return (List<Artist>) this.dao.queryForList(super.getStatementName("getSubBillboardArtistList"), topInfo);
	}
	public int getSubBillboardArtistCount(long subBillboardId){
		return (Integer)this.dao.queryForObject(super.getStatementName("getSubBillboardArtistCount"), subBillboardId);
	}
	public List<Artist> getSearchArtistInfoByKeyName(SearchArtistInfo searchArtistInfo){
		 return (List<Artist>) this.dao.queryForList(super.getStatementName("getSearchArtistInfoByKeyName"), searchArtistInfo);
	}
	public List<Artist> getListArtistInfo(DiscoveryInfo discoveryInfo){
		return (List<Artist>)this.dao.queryForList(super.getStatementName("getListArtistInfo"),discoveryInfo);
	}
	public int getListArtistInfoAllCount(DiscoveryInfo discoveryInfo){
		return (Integer)this.dao.queryForObject(super.getStatementName("getListArtistInfoAllCount"), discoveryInfo);
	}
	public List<Artist>  getArtistListByIds(List<Long> ids){
		if(ids.isEmpty()){
			return new ArrayList<Artist>();
		}
		return (List<Artist>)this.dao.queryForList(super.getStatementName("getArtistListByIds"), ids);
	}
	public List<Artist>  getCommonArtistListByIdList(CommonArtistInfo	 commonArtistInfo){
		return (List<Artist>)this.dao.queryForList(super.getStatementName("getCommonArtistListByIdList"), commonArtistInfo);
	}
	public List<Artist> getArtistListByArtistIdList(RecommendArtistInfo recommendArtistInfo){
		return (List<Artist>)this.dao.queryForList(super.getStatementName("getArtistListByArtistIdList"), recommendArtistInfo);
	}
	//------------List<Artist> end-------------------
	
	//-----------Artist start------------------
	public Artist getArtistInfoByArtistIdOrName(long artistId,String artistName){
		Map paramMap=new HashMap();
		paramMap.put("artistId", artistId);
		paramMap.put("artistName", artistName);
		return (Artist)this.dao.queryForObject(super.getStatementName("getArtistInfoByArtistIdOrName"), paramMap);
	}
	public Artist getArtistInfoByArtistIdOrNameWithStatus(long artistId,String artistName){
		Map paramMap=new HashMap();
		paramMap.put("artistId", artistId);
		paramMap.put("artistName", artistName);
		return (Artist)this.dao.queryForObject(super.getStatementName("getArtistInfoByArtistIdOrNameWithStatus"), paramMap);
	}
	public Artist getArtistInfoByAlbumId(long albumId){
		return (Artist)this.dao.queryForObject(super.getStatementName("getArtistInfoByAlbumId"),albumId);
	}
	//-----------Artist end------------------
	
	//------------Album start------------------
	public Album getAlbumInfoById(long albumId){
		return (Album)this.dao.queryForObject(super.getStatementName("getAlbumInfoById"),albumId);
	}
	public Album getAlbumInfoByIdRealSongCount(long albumId){
		return (Album)this.dao.queryForObject(super.getStatementName("getAlbumInfoByIdRealSongCount"),albumId);
	}
	public Album getAlbumInfoByIdNoCondition(long albumId){
		return (Album)this.dao.queryForObject(super.getStatementName("getAlbumInfoByIdNoCondition"),albumId);
	}
	public Album getAlbumInfoByArtistAndAlbumTitle(GetAlbumInfo getAlbumInfo){
		return (Album)this.dao.queryForObject(super.getStatementName("getAlbumInfoByArtistAndAlbumTitle"),getAlbumInfo);
	}
	public Album getAlbumInfoByArtistAndAlbumTitleWithStatus(GetAlbumInfo getAlbumInfo){
		return (Album)this.dao.queryForObject(super.getStatementName("getAlbumInfoByArtistAndAlbumTitleWithStatus"),getAlbumInfo);
	}
	//-----------------album end-------------------
	//-------------song start------------------
	
	public Song getSimilarSongNative(GetSimilarSongInfo getSimilarSongInfo){
		return (Song)this.dao.queryForObject(super.getStatementName("getSimilarSongNative"), getSimilarSongInfo);
	}
	
	public Song getSongInfoByArtistAndSong(String song,String artist,int albumartflag){
		Map map=new HashMap();
		map.put("song", song);
		map.put("artist", artist);
		map.put("albumartflag", albumartflag);
		return (Song)this.dao.queryForObject(super.getStatementName("getSongInfoByArtistAndSong"), map);
	}
	public Song getSongInfoBySongId(long songId,int albumartflag,int mvflag){
		Map map=new HashMap();
		map.put("songid",songId);
		map.put("albumartflag", albumartflag);
		map.put("mvflag",mvflag);
		return (Song)this.dao.queryForObject(super.getStatementName("getSongInfoBySongId"), map);
	}
	//0,1,6
	public Song getSongInfoBySongIdWithStatus(long songId){
		return (Song)this.dao.queryForObject(super.getStatementName("getSongInfoBySongIdWithStatus"), songId);
	}
	public Song getSongInfoBySongIdNoCondition(long songId){
		return (Song)this.dao.queryForObject(super.getStatementName("getSongInfoBySongIdNoCondition"), songId);
	}
	//-------------song end------------------
	//-------------SongMv start---------------------------------
	public SongMv getSongMvInfoByMvId(int mvId){
		return (SongMv)this.dao.queryForObject(super.getStatementName("getSongMvInfoByMvId"), mvId);
	}
	public SongMv getMvUriDownloadInfoByMvId(long mvId){
		return (SongMv)this.dao.queryForObject(super.getStatementName("getMvUriDownloadInfoByMvId"),mvId);
	}
	public List<SongMv> getMvIdAndTypeListBySongId(long songId){
		return (List<SongMv>)this.dao.queryForList(super.getStatementName("getMvIdAndTypeListBySongId"), songId);
	}
	public List<SongMv> topMvList(TopMvInfo topMvInfo){
		return (List<SongMv>)this.dao.queryForList(super.getStatementName("topMvList"), topMvInfo);
	}
	public List<SongMv> getSongMvInfoBySongIdList(LookupMvInfo lookupMvInfo){
		return (List<SongMv>)this.dao.queryForList(super.getStatementName("getSongMvInfoBySongIdList"), lookupMvInfo);
	}
	public List<SongMv> getSongMvInfoByArtistIdExceptSongIdList(LookupMvInfo lookupMvInfo){
		return (List<SongMv>)this.dao.queryForList(super.getStatementName("getSongMvInfoByArtistIdExceptSongIdList"), lookupMvInfo);
	}
	public List<SongMv> getSongMvInfoByArtistAndAlbumExceptSongIdList(LookupMvInfo info){
		return (List<SongMv>)this.dao.queryForList(super.getStatementName("getMtvInfoByArtistAndAlbumExceptSongIdList"), info);
	}
	
	public List<SongMv> getSongMvListByIds(List<Long> songMvIds){
		return (List<SongMv>)this.dao.queryForList(super.getStatementName("getSongMvListByIds"), songMvIds);
	}
	public List<SongMv> getSongMvInfoByArtistId(ListMvInfo listMvInfo){
		return (List<SongMv>)this.dao.queryForList(super.getStatementName("getSongMvInfoByArtistId"), listMvInfo);
	}
	public int getSongMvInfoAllCount(ListMvInfo listMvInfo){
		return (Integer)this.dao.queryForObject(super.getStatementName("getSongMvInfoAllCount"), listMvInfo);
	}
	//-------------SongMv end---------------------------------
	//---------------song about start-----------------------------------------
	public long checkSongIdIfExist(long songId){
		long songid=0L;
		Long songIds=dao.queryForObject(super.getStatementName("checkSongIdIfExist"), songId);
		if(songIds!=null){
			songid=songIds;
		}
		return songid;
	}
	public int checkSongBySongIdAndArtistName(long songId,String artistName){
		Map map=new HashMap();
		map.put("songid",songId);
		map.put("artistName", artistName);
		return (Integer)dao.queryForObject(super.getStatementName("checkSongBySongIdAndArtistName"),map);
	}
	public int getSongInfoListByArtistIdAllCount(GetSongsOfArtistInfo getSongsOfArtistInfo){
		return (Integer)this.dao.queryForObject(super.getStatementName("getSongInfoListByArtistIdAllCount"), getSongsOfArtistInfo);
	}
	public void clearRandomSong(){
		this.dao.update(super.getStatementName("clearRandomSong"));
	}
	public void insertRandomSong(){
		this.dao.insert(super.getStatementName("insertRandomSong"));
	}
	
	//---------------song about end-----------------------------------------
}
