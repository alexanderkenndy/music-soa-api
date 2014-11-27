package com.kascend.music2.api3.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kascend.music2.api3.entity.AlbumUdata;
import com.kascend.music2.api3.entity.Artist;
import com.kascend.music2.api3.entity.User;
import com.kascend.music2.api3.entity.UserActivityLog;
import com.kascend.music2.api3.entity.UserComment;
import com.kascend.music2.api3.entity.UserCommentContent;
import com.kascend.music2.api3.entity.UserMessage;
import com.kascend.music2.api3.entity.UserMetadata;
import com.kascend.music2.api3.entity.UserMv;
import com.kascend.music2.api3.entity.UserOpAlbum;
import com.kascend.music2.api3.entity.UserPhoto;
import com.kascend.music2.api3.entity.UserPlaylist;
import com.kascend.music2.api3.entity.UserPopularityLog;
import com.kascend.music2.api3.entity.UserShare;
import com.kascend.music2.api3.entity.UserShareContent;
import com.kascend.music2.api3.entity.UserSns;
import com.kascend.music2.api3.entity.UserSnsItem;
import com.kascend.music2.api3.entity.UserSong;
import com.kascend.music2.api3.entity.UserSongAlbum;
import com.kascend.music2.api3.entity.UserSongArtist;
import com.kascend.music2.api3.service.metadata.info.GetMvsOfPlaylistInfo;
import com.kascend.music2.api3.service.metadata.info.GetSongsOfPlaylistInfo;
import com.kascend.music2.api3.service.metadata.info.StatInfo;
import com.kascend.music2.api3.service.social.info.DeletePlaylistSongInfo;
import com.kascend.music2.api3.service.social.info.LeaveMessageInfo;
import com.kascend.music2.api3.service.social.info.ListUserInfo;
import com.kascend.music2.api3.service.social.info.RecommendUserInfo;
import com.kascend.music2.api3.service.social.info.RelevanceUidInfo;
import com.kascend.music2.api3.service.social.info.ArtistFollowStatusInfo;
import com.kascend.music2.api3.service.social.info.AttentionUsersInfo;
import com.kascend.music2.api3.service.social.info.FollowArtistInfo;
import com.kascend.music2.api3.service.social.info.FollowUserInfo;
import com.kascend.music2.api3.service.social.info.GetCommentsOfItemInfo;
import com.kascend.music2.api3.service.social.info.GetPlaylistsOfUserInfo;
import com.kascend.music2.api3.service.social.info.GetUserAlbumsInfo;
import com.kascend.music2.api3.service.social.info.GetUserAlbumsOfArtistInfo;
import com.kascend.music2.api3.service.social.info.GetUserArtistInfo;
import com.kascend.music2.api3.service.social.info.LikeInfo;
import com.kascend.music2.api3.service.social.info.MyAttentionAlbumInfo;
import com.kascend.music2.api3.service.social.info.MyAttentionInfo;
import com.kascend.music2.api3.service.social.info.SearchUserInfo;
import com.kascend.music2.api3.service.social.info.ShareInfo;
import com.kascend.music2.api3.service.social.info.SnsItemsInfo;
import com.kascend.music2.api3.service.social.info.UpdateUserInfo;
import com.kascend.music2.api3.service.social.info.UserFollowStatusInfo;
import com.kascend.music2.api3.service.social.info.UserInfo;
import com.kascend.music2.api3.service.social.info.UserSnsShareInfo;
import com.kascend.music2.api3.service.util.Constants;

public class SocialDao extends AbstractDao {
	

	public static final String NAME_SPACE="social"; 
	
	@Override
	protected String getNameSpace() {
		return NAME_SPACE;
	}
	
	public void updateUserInfo(UpdateUserInfo info){
		dao.update(super.getStatementName("updateUserInfo"), info);
	}
	
	public List<Artist> getArtistListByUids(List<Long> uidList){
		if(uidList.isEmpty()){
			return new ArrayList<Artist>();
		}
		return (List<Artist>)dao.queryForList(super.getStatementName("getArtistListByUids"), uidList);
	}
	public List<UserShare> getUserShareListByItemTypes(AttentionUsersInfo info) {
		List<UserShare> userShareList= (List<UserShare>)dao.queryForList(super.getStatementName("getUserShareListByItemTypes"), info);
		return getUserShareList(userShareList);
	}
	
	public UserShare getUserShareByShareId(long shareId){
		return (UserShare)dao.queryForObject(super.getStatementName("getUserShareByShareId"),shareId);
	}
	
	public void updateUserShare(long shareId,int countType){
		Map map=new HashMap();
		map.put("countType",countType);
		map.put("shareId",shareId);
		dao.update(super.getStatementName("updateUserShare"),map);
	}
	
	public List<UserShare> getUserShareListByItemTypeAndTargetUid(AttentionUsersInfo info) {
		List<UserShare> userShareList= (List<UserShare>)dao.queryForList(super.getStatementName("getUserShareListByItemTypeAndTargetUid"), info);
		return getUserShareList(userShareList);
	}
	
	public int getUserShareListByItemTypeAndTargetUidAllCount(AttentionUsersInfo info){
		return (Integer)dao.queryForObject(super.getStatementName("getUserShareListByItemTypeAndTargetUidAllCount"), info);
	}
	
	
	public List<UserShare> getUserShareListByUids(AttentionUsersInfo info){
		if(info.getUidList().isEmpty()){
			return new ArrayList<UserShare>();
		}
		List<UserShare> userShareList= (List<UserShare>)dao.queryForList(super.getStatementName("getUserShareListByUids"), info);
		return getUserShareList(userShareList);
	}
	
	public List<UserShare> getUserShareListBySnsShareId(AttentionUsersInfo info){
		List<UserShare> userShareList= (List<UserShare>)dao.queryForList(super.getStatementName("getUserShareListBySnsShareId"), info);
		return getUserShareList(userShareList);
	}
	
	public List<UserComment> getUserCommentListByItemTypeAndId(GetCommentsOfItemInfo info){
		List<UserComment> userCommentList= (List<UserComment>)dao.queryForList(super.getStatementName("getUserCommentListByItemTypeAndId"), info);
		return getUserCommentList(userCommentList);
	}
	public int getUserCommentListByItemTypeAndIdAllCount(GetCommentsOfItemInfo info){
		return (Integer)dao.queryForObject(super.getStatementName("getUserCommentListByItemTypeAndIdAllCount"), info);
	}
	
	public List<UserComment> getReplyUserCommentListByCommendIdList(List<Long> commentIdList){
		List<UserComment> userCommentList= (List<UserComment>)dao.queryForList(super.getStatementName("getReplyUserCommentListByCommendIdList"), commentIdList);
		return getUserCommentList(userCommentList);
	}
	
	public UserComment getUserCommentListInfoByCommentId(long commentId){
		return (UserComment)dao.queryForObject(super.getStatementName("getUserCommentListInfoByCommentId"), commentId);
	}
	
	
	public List<UserComment> getUserCommentListByCommentIdList(List<Long> commentIdList){
		List<UserComment> userCommentList= (List<UserComment>)dao.queryForList(super.getStatementName("getUserCommentListByCommentIdList"), commentIdList);
		return getUserCommentList(userCommentList);
	}
	
	public List<UserComment> getUserCommentList(List<UserComment> userCommentList){
		
		List<Long> uidList=new ArrayList<Long>();
		List<Long> commentIds=new ArrayList<Long>();
		for(UserComment userComment:userCommentList){
			if(!uidList.contains(userComment.getUid())){
				uidList.add(userComment.getUid());
			}
			commentIds.add(userComment.getCommentId());
		}
		List<User> userList=getUserListByUids(uidList);
		List<Long> artistUids=new ArrayList<Long>();
		for(User user: userList){
			for(UserComment userComment:userCommentList){
				if(user.getUid()==userComment.getUid()){
					userComment.setUser(user);
				}
			}
			if(user.getUserType()==Constants.USER_TYPE_ARTIST){
				artistUids.add(user.getUid());
			}
		}
		List<Artist> artistUidMp=getArtistListByUids(artistUids);
		for(Artist artist: artistUidMp){
			for(User user: userList){
				if(user.getUid()==artist.getUid()){
					user.setArtistId(artist.getArtistId());
				}
			}
		}
		List<UserCommentContent> commentContentList=getUserCommentContentListByIds(commentIds);
		for(UserCommentContent userCommentContent: commentContentList ){
			for(UserComment userComment: userCommentList){
				if(userCommentContent.getCommentId()==userComment.getCommentId()){
					userComment.setContent(userCommentContent.getContent());
					break;
				}
			}
		}
		
		return userCommentList;
	}
	
	public List<UserShare> getUserShareList(List<UserShare> userShareList) {
		
		List<Long> uidList=new ArrayList<Long>();
		List<Long> shareIds=new ArrayList<Long>();
		for(UserShare userShare: userShareList){
			if(!uidList.contains(userShare.getUid())){
				uidList.add(userShare.getUid());
			}
			shareIds.add(userShare.getShareId());
		}
		List<User> userList=getUserListByUids(uidList);
		List<Long> artistUids=new ArrayList<Long>();
		for(User user: userList){
			for(UserShare userShare: userShareList){
				if(user.getUid()==userShare.getUid()){
					userShare.setUser(user);
				}
			}
			if(user.getUserType()==Constants.USER_TYPE_ARTIST){
				artistUids.add(user.getUid());
			}
		}
		List<Artist> artistUidMp=getArtistListByUids(artistUids);
		for(Artist artist: artistUidMp){
			for(User user: userList){
				if(user.getUid()==artist.getUid()){
					user.setArtistId(artist.getArtistId());
				}
			}
		}
		List<UserShareContent> shareContentList=getUserShareContentListByIds(shareIds);
		for(UserShareContent shareContent: shareContentList ){
			for(UserShare userShare: userShareList){
				if(userShare.getShareId()==shareContent.getShareId()){
					userShare.setContent(shareContent.getContent());
					break;
				}
			}
		}
		
		return userShareList;
	}

	public int getUserShareListAllCountByUids(AttentionUsersInfo info){
		return (Integer)dao.queryForObject(super.getStatementName("getUserShareListAllCountByUids"), info);
	}
	
	public List<UserShareContent> getUserShareContentListByIds(
			List<Long> shareIds) {
		
		if(shareIds.isEmpty()){
			return new ArrayList<UserShareContent>();
		}
		return  (List<UserShareContent>)dao.queryForList(super.getStatementName("getUserShareContentListByIds"), shareIds);
	}
	
	public List<UserCommentContent> getUserCommentContentListByIds(
			List<Long> commentIds) {
		if(commentIds.isEmpty()){
			return new ArrayList<UserCommentContent>();
		}
		return  (List<UserCommentContent>)dao.queryForList(super.getStatementName("getUserCommentContentListByIds"), commentIds);
	}
	public User getUserById(long uid) {
		return dao.queryForObject(super.getStatementName("getUserById"), uid);
	}
	
	public List<UserSns> getUserSnsListByUid(long uid){
		return (List<UserSns>)dao.queryForList(super.getStatementName("getUserSnsListByUid"),uid);
	}
	
	/**
	 * if exists return true, else return false 
	 * @return
	 */
	public boolean checkFollowArtist(FollowArtistInfo info){
		Integer check=dao.queryForObject(super.getStatementName("checkFollowArtist"), info);
		if(check>0){
			return true;
		}else{
			return false;
		}
	}
	
	public void addFollowArtist(FollowArtistInfo info) {
		dao.insert(super.getStatementName("addFollowArtist"), info);
	}
	
	public void deleteFollowArtist(FollowArtistInfo info) {
		dao.delete(super.getStatementName("deleteFollowArtist"), info);
	}
	
	public boolean checkFollowUser(FollowUserInfo info){
		Integer check=dao.queryForObject(super.getStatementName("checkFollowUser"), info);
		if(check>0){
			return true;
		}else{
			return false;
		}
	}
	public void addFollowUser(FollowUserInfo info) {
		dao.insert(super.getStatementName("addFollowUser"), info);
	}
	
	public void deleteFollowUser(FollowUserInfo info) {
		dao.delete(super.getStatementName("deleteFollowUser"), info);
	}
	
	public List<UserSnsItem> getUserSnsItemListByIds(List<Long> snsItemIdList) {
		if(snsItemIdList.isEmpty()){
			return new ArrayList<UserSnsItem>();
		}
		return  (List<UserSnsItem>)dao.queryForList(super.getStatementName("getUserSnsItemListByIds"), snsItemIdList);
	}
	
	public long getSongLike(LikeInfo info){
		long songId=0L;
		Long songIds=dao.queryForObject(super.getStatementName("getSongLike"), info);
		if(songIds!=null){
			songId=songIds;
		}
		return songId;
	}
	
	public void saveSongLike(LikeInfo info){
		dao.insert(super.getStatementName("saveSongLike"), info);
	}
	
	public void deleteSongLike(LikeInfo info){
		dao.delete(super.getStatementName("deleteSongLike"),info);
	}
	
	public void deleteFromUserSong(long songId,long playlistId,long uid){
		Map map=new HashMap();
		map.put("songId", songId);
		map.put("playlistId", playlistId);
		map.put("uid", uid);
		dao.delete(super.getStatementName("deleteFromUserSong"),map);
	}
	
	public long getArtistLike(LikeInfo info){
		long artistId=0L;
		Long artistIds=dao.queryForObject(super.getStatementName("getArtistLike"), info);
		if(artistIds!=null){
			artistId=artistIds;
		}
		return artistId;
	}
	
	public void saveArtistLike(LikeInfo info){
		dao.insert(super.getStatementName("saveArtistLike"), info);
	}
	
	public void deleteArtistLike(LikeInfo info){
		dao.delete(super.getStatementName("deleteArtistLike"),info);
	}
	
	
	public long getAlbumLike(LikeInfo info){
		long albumId=0L;
		Long albumIds=dao.queryForObject(super.getStatementName("getAlbumLike"), info);
		if(albumIds!=null){
			albumId=albumIds;
		}
		return albumId;
	}
	
	public void saveAlbumLike(LikeInfo info){
		dao.insert(super.getStatementName("saveAlbumLike"), info);
	}
	
	public void deleteAlbumLike(LikeInfo info){
		dao.delete(super.getStatementName("deleteAlbumLike"),info);
	}
	
	public long getUserPlaylistLike(LikeInfo info){
		long playlistId=0L;
		Long playlistIds=dao.queryForObject(super.getStatementName("getUserPlaylistLike"), info);
		if(playlistIds!=null){
			playlistId=playlistIds;
		}
		return playlistId;
	}
	
	public void saveUserPlaylistLike(LikeInfo info){
		dao.insert(super.getStatementName("saveUserPlaylistLike"), info);
	}
	
	public void deleteUserPlaylistLike(LikeInfo info){
		dao.delete(super.getStatementName("deleteUserPlaylistLike"),info);
	}
	
	public long getSongMvLike(LikeInfo info){
		long songMvId=0L;
		Long songMvIds=dao.queryForObject(super.getStatementName("getSongMvLike"), info);
		if(songMvIds!=null){
			songMvId=songMvIds;
		}
		return songMvId;
	}
	
	public void saveSongMvLike(LikeInfo info){
		dao.insert(super.getStatementName("saveSongMvLike"), info);
	}
	
	public void deleteSongMvLike(LikeInfo info){
		dao.delete(super.getStatementName("deleteSongMvLike"),info);
	}
	
	public Long saveUserShare(SnsItemsInfo info){
		return (Long)dao.insert(super.getStatementName("saveUserShare"), info);
	}
	
	
	public void saveUserShareContent(ShareInfo info){
		dao.insert(super.getStatementName("saveUserShareContent"), info);
	}
	
	public Long saveUserComment(SnsItemsInfo info){
		return (Long)dao.insert(super.getStatementName("saveUserComment"), info);
	}
	
	public Long saveUserCommentContent(SnsItemsInfo info){
		return (Long)dao.insert(super.getStatementName("saveUserCommentContent"), info);
	}
	
	public void updateUserKindCount(SnsItemsInfo info){
		dao.update(super.getStatementName("updateUserKindCount"), info);
	}
	
	public void updateSongUdata(SnsItemsInfo info){
		dao.update(super.getStatementName("updateSongUdata"), info);
	}
	
	public void updateArtistUdata(SnsItemsInfo info){
		dao.update(super.getStatementName("updateArtistUdata"), info);
	}
	
	public void updateAlbumUdata(SnsItemsInfo info){
		dao.update(super.getStatementName("updateAlbumUdata"), info);
	}
	public void updatePlaylistUdata(SnsItemsInfo info){
		dao.update(super.getStatementName("updatePlaylistUdata"), info);
	}
	public void updateSongMvUdata(SnsItemsInfo info){
		dao.update(super.getStatementName("updateSongMvUdata"), info);
	}
	
	public UserPlaylist getUserPlaylistByTitleAndUid(String playlistTitle,long uid){
		Map map=new HashMap();
		map.put("playlistTitle", playlistTitle);
		map.put("uid", uid);
		return (UserPlaylist)dao.queryForObject(super.getStatementName("getUserPlaylistByTitleAndUid"), map);
	}
	
	public List<UserPlaylist> getUserPlaylistsByTargetUid(GetPlaylistsOfUserInfo getPlaylistsOfUserInfo){
		return (List<UserPlaylist>)dao.queryForList(super.getStatementName("getUserPlaylistsByTargetUid"), getPlaylistsOfUserInfo);
	}
	
	public int getUserPlaylistsByTargetUidAllCount(long targetuid){
		return (Integer)dao.queryForObject(super.getStatementName("getUserPlaylistsByTargetUidAllCount"), targetuid);
	}
	
	public UserPlaylist getUserPlaylistByUidAndDataFrom(int dataFrom,long uid){
		Map map=new HashMap();
		map.put("dataFrom", dataFrom);
		map.put("uid", uid);
		return (UserPlaylist)dao.queryForObject(super.getStatementName("getUserPlaylistByUidAndDataFrom"), map);
	}
	
	public long saveDefaultOrUncategoriedUserPlaylist(int dataFrom,long uid){
		Map map=new HashMap();
		map.put("dataFrom", dataFrom);
		map.put("uid", uid);
		return (Long)dao.insert(super.getStatementName("saveDefaultOrUncategoriedUserPlaylist"),map);
	}
	
	public UserPlaylist getUserPlaylistByIdAndUid(long playlistId,long uid){
		Map map=new HashMap();
		map.put("playlistId", playlistId);
		map.put("uid", uid);
		return (UserPlaylist)dao.queryForObject(super.getStatementName("getUserPlaylistByIdAndUid"), map);
	}
	
	public long saveUserPlaylist(UserMetadata userMeta){
		return (Long)dao.insert(super.getStatementName("saveUserPlaylist"), userMeta);
	}
	
	public long saveUserSongList(UserSong userSong){
		return (Long)dao.insert(super.getStatementName("saveUserSongList"), userSong);
	}
	
	
	public void updateUserSongAfterCompare(List<UserSong> sampSongPathList){
		if(sampSongPathList.isEmpty())return;
		for(UserSong us:sampSongPathList){
			dao.update(super.getStatementName("updateUserSongAfterCompare"),us);
		}
	}
	
	public void deleteUserSongListByIdList(List<Long> idList){
		dao.delete(super.getStatementName("deleteUserSongListByIdList"),idList);
	}
	
	public void deleteUserSongListAll(long playlistId,long uid){
		Map map=new HashMap();
		map.put("playlistId", playlistId);
		map.put("uid", uid);
		dao.delete(super.getStatementName("deleteUserSongListAll"),map);
	}
	
	public List<AlbumUdata> getDiscoveryAlbumUdataList(MyAttentionInfo info){
		return (List<AlbumUdata>)dao.queryForList(super.getStatementName("getDiscoveryAlbumUdataList"),info);
	}
	
	public int getDiscoveryAlbumUdataListAllCount(MyAttentionInfo info){
		return (Integer)dao.queryForObject(super.getStatementName("getDiscoveryAlbumUdataListAllCount"), info);
	}

	
	
	public UserOpAlbum getFollowedAlbumOfUserByAlbumIdAndUid(long albumId,long uid){
		Map map=new HashMap();
		map.put("albumId", albumId);
		map.put("uid", uid);
		return (UserOpAlbum)dao.queryForObject(super.getStatementName("getFollowedAlbumOfUserByAlbumIdAndUid"), map);
	}
	
	
	public List<UserOpAlbum> getUserOpAlumInfo(long albumId,int pagecount,int type){
		Map map=new HashMap();
		map.put("albumId", albumId);
		map.put("pagecount", pagecount);
		map.put("type", type);
		return (List<UserOpAlbum>)dao.queryForList(super.getStatementName("getUserOpAlumInfo"),map);
	}
	
	public List<UserOpAlbum> getMyAttentionAlbumsByUidList(MyAttentionInfo info){
		return (List<UserOpAlbum>)dao.queryForList(super.getStatementName("getMyAttentionAlbumsByUidList"),info);
	}
	
	public int getMyAttentionAlbumsByUidListAllCount(MyAttentionInfo info){
		return (Integer)dao.queryForObject(super.getStatementName("getMyAttentionAlbumsByUidListAllCount"), info);
	}
	
	public int getSongCountOfUserSong(long uid){
		return (Integer)dao.queryForObject(super.getStatementName("getSongCountOfUserSong"), uid);
	}
	public int getAlbumCountOfUserSong(long uid){
		return (Integer)dao.queryForObject(super.getStatementName("getAlbumCountOfUserSong"), uid);
	}
	public int getArtistCountOfUserSong(long uid){
		return (Integer)dao.queryForObject(super.getStatementName("getArtistCountOfUserSong"), uid);
	}
	
	public void updateUserMetadatCount(long uid,int songCount,int artistCount,int albumCount){
		Map map=new HashMap();
		map.put("songCount", songCount);
		map.put("uid", uid);
		map.put("artistCount", artistCount);
		map.put("albumCount", albumCount);
		dao.update(super.getStatementName("updateUserMetadatCount"),map);
	}
	//
	public long getDefaultUserPlaylistidByUid(long uid,int dataFrom){
		long playlistId=0L;
		Map map=new HashMap();
		map.put("dataFrom", dataFrom);
		map.put("uid", uid);
		Long pId= (Long)dao.queryForObject(super.getStatementName("getDefaultUserPlaylistidByUid"), map);
		if(pId!=null){
			playlistId=pId;
		}
		return playlistId; 
	}
	
	public void deleteUserSongBySongIdAndPlaylistId(DeletePlaylistSongInfo info){
		dao.delete(super.getStatementName("deleteUserSongBySongIdAndPlaylistId"),info);
	}
	
	public int getNativeUserSnsCount(long uid,int snsSite){
		Map map=new HashMap();
		map.put("snsSite", snsSite);
		map.put("uid", uid);
		return (Integer)dao.queryForObject(super.getStatementName("getNativeUserSnsCount"),map);
	}
	
	
	public void saveNativeUserSns(UserSns userSns){
		dao.insert(super.getStatementName("saveNativeUserSns"),userSns);
	}
	public void deleteNativeUserSns(long uid,int snsSite){
		Map map=new HashMap();
		map.put("snsSite", snsSite);
		map.put("uid", uid);
		dao.delete(super.getStatementName("deleteNativeUserSns"),map);
	}
	
	public long saveUserSnsShare(UserSnsShareInfo info){
		return (Long)dao.insert(super.getStatementName("saveUserSnsShare"), info);
	}
	
	public void saveUserSnsContent(long snsShareId,String content){
		Map map=new HashMap();
		map.put("snsShareId", snsShareId);
		map.put("content", content);
		dao.insert(super.getStatementName("saveUserSnsContent"),map);
	}
	
	public void updateUserSnsShareStatus(long snsShareId){
		dao.update(super.getStatementName("updateUserSnsShareStatus"),snsShareId);
	}
	
	public void addUserFollowCount(long uid){
		dao.insert(super.getStatementName("addUserFollowCount"),uid);
	}
	
	public void delUserFollowCount(long uid){
		dao.update(super.getStatementName("delUserFollowCount"),uid);
	}
	
	public int getUserFansCount(long uid){
		return (Integer)dao.queryForObject(super.getStatementName("getUserFansCount"),uid);
	}
	
	public void updateUserFansCount(long uid,int fansCount){
		Map map=new HashMap();
		map.put("fansCount", fansCount);
		map.put("uid", uid);
		dao.update(super.getStatementName("updateUserFansCount"),map);
	}
	
	public List<Long> getUserFollowUserList(UserFollowStatusInfo info){
		return (List<Long>)dao.queryForList(super.getStatementName("getUserFollowUserList"),info);
	}
	
	public List<Long> getUserFollowArtistList(ArtistFollowStatusInfo info){
		return (List<Long>)dao.queryForList(super.getStatementName("getUserFollowArtistList"),info);
	}
	
	public void updateUserSongSortIndex(UserSong userSong){
		Map map=new HashMap();
		map.put("id", userSong.getId());
		map.put("sortIndex", userSong.getSortIndex());
		dao.update(super.getStatementName("updateUserSongSortIndex"),map);
	}
	
	public List<Long> getUserListenedArtistIdList(GetUserArtistInfo info){
		return (List<Long>)dao.queryForList(super.getStatementName("getUserListenedArtistIdList"),info);
	}
	public int getUserListenedArtistIdListAllCount(GetUserArtistInfo info){
		return (Integer)dao.queryForObject(super.getStatementName("getUserListenedArtistIdListAllCount"), info);
	}
	
	public List<Artist> getUserListenedArtistList(GetUserArtistInfo info){
		if(info.getArtistIdList().isEmpty()){
			return new ArrayList<Artist>();
		}
		return (List<Artist>)dao.queryForList(super.getStatementName("getUserListenedArtistList"),info);
	}
	public List<Artist> getUserListenedArtistAlbumCountList(GetUserArtistInfo info){
		if(info.getArtistIdList().isEmpty()){
			return new ArrayList<Artist>();
		}
		return (List<Artist>)dao.queryForList(super.getStatementName("getUserListenedArtistAlbumCountList"),info);
	}
	
	public List<Long> getUserListenedAlbumIdList(GetUserAlbumsInfo info){
		return (List<Long>)dao.queryForList(super.getStatementName("getUserListenedAlbumIdList"),info);
	}
	
	public int getUserListenedAlbumIdListAllCount(GetUserAlbumsInfo info){
		return (Integer)dao.queryForObject(super.getStatementName("getUserListenedAlbumIdListAllCount"), info);
	}
	
	public List<Long> getUserListenedAlbumIdListAll(GetUserAlbumsOfArtistInfo info){
		return (List<Long>)dao.queryForList(super.getStatementName("getUserListenedAlbumIdListAll"),info);
	}

	public int getUserListenedAlbumAllCount(GetUserAlbumsOfArtistInfo info){
		return (Integer)dao.queryForObject(super.getStatementName("getUserListenedAlbumAllCount"), info);
	}
	public List<Long> getUserUnlistenedAlbumIdList(GetUserAlbumsOfArtistInfo info){
		return (List<Long>)dao.queryForList(super.getStatementName("getUserUnlistenedAlbumIdList"),info);
	}
	public int getUserUnlistenedAlbumIdListAllCount(GetUserAlbumsOfArtistInfo info){
		return (Integer)dao.queryForObject(super.getStatementName("getUserUnlistenedAlbumIdListAllCount"), info);
	}
	
	public void updateUserPlaylistUpdatetimeAndImage(long playlistId,String imagePath){
		Map map=new HashMap();
		map.put("imagePath", imagePath);
		map.put("playlistId", playlistId);
		dao.update(super.getStatementName("updateUserPlaylistUpdatetimeAndImage"),map);
	}
	
	public List<Long> getTopActivityUids(ListUserInfo info){
		return (List<Long>)dao.queryForList(super.getStatementName("getTopActivityUids"),info);
	}
	
	public int getTopActivityUidsAllCount(){
		return (Integer)dao.queryForObject(super.getStatementName("getTopActivityUidsAllCount"));
	}
	
	public List<Long> generateRandomUids(RecommendUserInfo info){
		return (List<Long>)dao.queryForList(super.getStatementName("generateRandomUids"),info);
	}
	//----album----count--start-
	
	public int getUserSongCountOfAlbumByAlbumId(long playlistId,long albumId,long uid){
		Map map=new HashMap();
		map.put("albumId", albumId);
		map.put("playlistId", playlistId);
		map.put("uid", uid);
		return (Integer)dao.queryForObject(super.getStatementName("getUserSongCountOfAlbumByAlbumId"),map);
	}
	
	
	public List<UserSongAlbum> getUserPlaylistSongCountOfAlbum(long playlistId){
		return (List<UserSongAlbum>)dao.queryForList(super.getStatementName("getUserPlaylistSongCountOfAlbum"), playlistId);
	}
	
	public List<Long> getUserSongAlbumByPlaylistIdAndAlbumId(UserSongAlbum userSongAlbum){
		if(userSongAlbum.getAlbumIdList().isEmpty()){
			return new ArrayList<Long>();
		}
		return (List<Long>)dao.queryForList(super.getStatementName("getUserSongAlbumByPlaylistIdAndAlbumId"), userSongAlbum);
	}
	
	public void updateUserSongAlbumByPlaylistIdAndAlbumId(UserSongAlbum userSongAlbum){
		dao.update(super.getStatementName("updateUserSongAlbumByPlaylistIdAndAlbumId"),userSongAlbum);
	}
	
	public void addUserSongAlbumByPlaylistIdAndAlbumId(UserSongAlbum userSongAlbum){
		dao.insert(super.getStatementName("addUserSongAlbumByPlaylistIdAndAlbumId"),userSongAlbum);
	}
	public void clearUserSongAlbumByPlaylistId(long playlistId){
		dao.update(super.getStatementName("clearUserSongAlbumByPlaylistId"),playlistId);
	}
	//----album----count--end-
	
	//----artist----count--start-
	public int getUserSongCountOfArtistByArtistId(long playlistId,long artistId,long uid){
		Map map=new HashMap();
		map.put("artistId", artistId);
		map.put("playlistId", playlistId);
		map.put("uid", uid);
		return (Integer)dao.queryForObject(super.getStatementName("getUserSongCountOfArtistByArtistId"),map);
	}
	
	public int getUserAlbumCountOfArtistByArtistId(long playlistId,long artistId,long uid){
		Map map=new HashMap();
		map.put("artistId", artistId);
		map.put("uid", uid);
		map.put("playlistId", playlistId);
		return (Integer)dao.queryForObject(super.getStatementName("getUserAlbumCountOfArtistByArtistId"),map);
	}
	
	public List<UserSongArtist> getUserPlaylistSongCountOfArtist(long playlistId){
		return (List<UserSongArtist>)dao.queryForList(super.getStatementName("getUserPlaylistSongCountOfArtist"), playlistId);
	}
	
	public List<UserSongArtist> getUserPlaylistAlbumCountOfArtist(long playlistId){
		return (List<UserSongArtist>)dao.queryForList(super.getStatementName("getUserPlaylistAlbumCountOfArtist"), playlistId);
	}
	
	public List<Long> getUserSongArtistByPlaylistIdAndArtistId(UserSongArtist userSongArtist){
		return (List<Long>)dao.queryForList(super.getStatementName("getUserSongArtistByPlaylistIdAndArtistId"), userSongArtist);
	}
	
	public void updateUserSongArtistByPlaylistIdAndArtistId(UserSongArtist userSongArtist){
		dao.update(super.getStatementName("updateUserSongArtistByPlaylistIdAndArtistId"),userSongArtist);
	}
	
	public void addUserSongArtistByPlaylistIdAndArtistId(UserSongArtist userSongArtist){
		dao.insert(super.getStatementName("addUserSongArtistByPlaylistIdAndArtistId"),userSongArtist);
	}
	public void clearUserSongArtistByPlaylistId(long playlistId){
		dao.update(super.getStatementName("clearUserSongArtistByPlaylistId"),playlistId);
	}
	//----artist----count--end-
	//修改用户的活跃度和人气值
	//人气
	public int getUserPopularityCount(long uid){
		int popuCount=0;
		Integer popularityCount= (Integer)dao.queryForObject(super.getStatementName("getUserPopularityCount"),uid);
		if(popularityCount!=null){
			popuCount=popularityCount;
		}
		return popuCount;
	}
	public void updateUserPopularityCount(long uid,int popularityCount){
		Map map=new HashMap();
		map.put("uid", uid);
		map.put("popularityCount", popularityCount);
		dao.update(super.getStatementName("updateUserPopularityCount"),map);
	}
	public UserPopularityLog getUserPopularityLogByUidAndType(long uid,int type){
		Map map=new HashMap();
		map.put("uid", uid);
		map.put("type", type);
		return (UserPopularityLog)dao.queryForObject(super.getStatementName("getUserPopularityLogByUidAndType"),map);
	}
	public void addUserPopularityLog(long uid,int type,int popularityCount){
		Map map=new HashMap();
		map.put("uid", uid);
		map.put("type", type);
		map.put("popularityCount", popularityCount);
		dao.insert(super.getStatementName("addUserPopularityLog"),map);
	}

	public void updateUserPopularityLog(long uid,int type,int popularityCount){
		Map map=new HashMap();
		map.put("uid", uid);
		map.put("type", type);
		map.put("popularityCount", popularityCount);
		dao.insert(super.getStatementName("updateUserPopularityLog"),map);
	}
	//活跃度
	public int getUserActivityCount(long uid){
		return (Integer)dao.queryForObject(super.getStatementName("getUserActivityCount"),uid);
	}
	public void updateUserActivityCount(long uid,int activityCount){
		Map map=new HashMap();
		map.put("uid", uid);
		map.put("activityCount", activityCount);
		dao.update(super.getStatementName("updateUserActivityCount"),map);
	}
	
	public UserActivityLog getUserActivityLogByUidAndType(long uid,int type){
		Map map=new HashMap();
		map.put("uid", uid);
		map.put("type", type);
		return (UserActivityLog)dao.queryForObject(super.getStatementName("getUserActivityLogByUidAndType"),map);
	}
	public void addUserActivityLog(long uid,int type,int activityCount){
		Map map=new HashMap();
		map.put("uid", uid);
		map.put("type", type);
		map.put("activityCount", activityCount);
		dao.insert(super.getStatementName("addUserActivityLog"),map);
	}
	public void updateUserActivityLog(long uid,int type,int activityCount){
		Map map=new HashMap();
		map.put("uid", uid);
		map.put("type", type);
		map.put("activityCount", activityCount);
		dao.update(super.getStatementName("updateUserActivityLog"),map);
	}
	
	public void insertUserSnsInvite(long uid,int weiboSource,String snsUid,String snsUserName){
		Map map=new HashMap();
		map.put("uid", uid);
		map.put("weiboSource", weiboSource);
		map.put("snsUid", snsUid);
		map.put("snsUserName", snsUserName);
		dao.insert(super.getStatementName("insertUserSnsInvite"),map);
	}
	
	public int getMessageCount(long messageId){
		return (Integer)dao.queryForObject(super.getStatementName("getMessageCount"),messageId);
	}
	
	public int getCommentCount(long commentId){
		return (Integer)dao.queryForObject(super.getStatementName("getCommentCount"),commentId);
	}
	
	public long insertUserMessage(LeaveMessageInfo info){
		return (Long)dao.insert(super.getStatementName("insertUserMessage"),info);
	}
	
	public void insertUserMessageContent(LeaveMessageInfo info){
		dao.insert(super.getStatementName("insertUserMessageContent"),info);
	}
	
	public List<UserMessage> getUserMessageByUid(long uid,int start,int pagecount){
		Map map=new HashMap();
		map.put("uid", uid);
		map.put("start", start);
		map.put("pagecount", pagecount);
		return (List<UserMessage>)dao.queryForList(super.getStatementName("getUserMessageByUid"),map);
	}
	
	public int getUserMessageAllCountByUid(long uid){
		return (Integer)dao.queryForObject(super.getStatementName("getUserMessageAllCountByUid"),uid);
	}
	
	public List<UserMessage> getUserMessageByMessageId(List<Long> messageIdList){
		if(messageIdList.isEmpty())return new ArrayList<UserMessage>();
		return (List<UserMessage>)dao.queryForList(super.getStatementName("getUserMessageByMessageId"),messageIdList);
	}
	
	public void saveUserPhoto(UserPhoto userPhoto){
		dao.insert(super.getStatementName("saveUserPhoto"), userPhoto);
	}
	
	
	//----------------copy from metadatadao.java---------
	
	public List<UserPlaylist> getUserPlaylistListByIds(List<Long> userPlaylistIds){
		return (List<UserPlaylist>)this.dao.queryForList(super.getStatementName("getUserPlaylistListByIds"), userPlaylistIds);
	}
	
	public UserPlaylist getUserPlaylistListById(long userPlaylistId){
		return (UserPlaylist)this.dao.queryForObject(super.getStatementName("getUserPlaylistListById"),userPlaylistId);
	}
	
	public List<UserMv> getUserMvList(GetMvsOfPlaylistInfo getMvsOfPlaylistInfo){
		return (List<UserMv>)this.dao.queryForList(super.getStatementName("getUserMvList"), getMvsOfPlaylistInfo);
	}
	public int getUserMvListAllCount(GetMvsOfPlaylistInfo getMvsOfPlaylistInfo){
		return (Integer)this.dao.queryForObject(super.getStatementName("getUserMvListAllCount"), getMvsOfPlaylistInfo);
	}
	
	public long checkSongIdIfInUserSong(long songId,long uid){
		long id=0l;
		Map map=new HashMap();
		map.put("songId", songId);
		map.put("uid", uid);
		Long Ids=dao.queryForObject(super.getStatementName("checkSongIdIfInUserSong"), map);
		if(Ids!=null){
			id=Ids;
		}
		return id;
	}
	
	public long checkMetadataIfInUserSong(StatInfo info){
		long id=0l;
		Long Ids=dao.queryForObject(super.getStatementName("checkMetadataIfInUserSong"), info);
		if(Ids!=null){
			id=Ids;
		}
		return id;
	}
	
	public long getUserSongBySongIdAndPlaylistId(long songId,long playlistId,long uid){
		Map map=new HashMap();
		map.put("songId", songId);
		map.put("playlistId", playlistId);
		map.put("uid", uid);
		long id=0l;
		Long userSongId= (Long)this.dao.queryForObject(super.getStatementName("getUserSongBySongIdAndPlaylistId"),map);
		if(userSongId!=null){
			id=userSongId;
		}
		return id;
	}
	
	public void updateUserSongDataFrom(int dataFrom,long songId,long playlistId,long uid){
		Map map=new HashMap();
		map.put("songId", songId);
		map.put("playlistId", playlistId);
		map.put("uid", uid);
		map.put("dataFrom", dataFrom);
		this.dao.update(super.getStatementName("updateUserSongDataFrom"),map);
	}
	
	public int getUserSongUdataId(long id){
		return (Integer)dao.queryForObject(super.getStatementName("getUserSongUdataId"), id);
	}
	
	public void updateUserSongUdata(long id){
		dao.update(super.getStatementName("updateUserSongUdata"),id);
	}
	
	public void saveUserSongUdata(long id){
		dao.insert(super.getStatementName("saveUserSongUdata"),id);
	}
	//-----------------------List<UserSong> start------------------------------
	
	public List<UserSong> getUserSongListByPlaylistId(GetSongsOfPlaylistInfo getSongsOfPlaylistInfo){
		return (List<UserSong>)this.dao.queryForList(super.getStatementName("getUserSongListByPlaylistId"), getSongsOfPlaylistInfo);
	}
	
	public int getUserSongListByPlaylistIdAllCount(GetSongsOfPlaylistInfo getSongsOfPlaylistInfo){
		return (Integer)this.dao.queryForObject(super.getStatementName("getUserSongListByPlaylistIdAllCount"), getSongsOfPlaylistInfo);
	}
	
	public List<UserSong> getUserSongListByDataFrom(GetSongsOfPlaylistInfo getSongsOfPlaylistInfo){
		return (List<UserSong>)this.dao.queryForList(super.getStatementName("getUserSongListByDataFrom"), getSongsOfPlaylistInfo);
	}
	
	public int getUserSongListByDataFromAllCount(GetSongsOfPlaylistInfo getSongsOfPlaylistInfo){
		return (Integer)this.dao.queryForObject(super.getStatementName("getUserSongListByDataFromAllCount"), getSongsOfPlaylistInfo);
	}
	
	public List<UserSong> getUserSongByCondition(DeletePlaylistSongInfo info){
		return (List<UserSong>)dao.queryForList(super.getStatementName("getUserSongByCondition"),info);
	}
	
	public List<UserSong> getUserMostAndRecentPlayedSongList(GetSongsOfPlaylistInfo getSongsOfPlaylistInfo){
		return (List<UserSong>)this.dao.queryForList(super.getStatementName("getUserMostAndRecentPlayedSongList"), getSongsOfPlaylistInfo);
	}
	
	public int getUserMostAndRecentPlayedSongListAllCount(GetSongsOfPlaylistInfo getSongsOfPlaylistInfo){
		return (Integer)this.dao.queryForObject(super.getStatementName("getUserMostAndRecentPlayedSongListAllCount"), getSongsOfPlaylistInfo);
	}
	//--------------------List<UserSong> end------------------------
	//--------------------List<Long> start------------------------
	public List<Long> getUserFollowedArtistUids(long uid) {
		return (List<Long>)dao.queryForList(super.getStatementName("getUserFollowedArtistUids"), uid);
	}
	
	public List<Long> getUserFollowedArtistIds(long uid) {
		return (List<Long>)dao.queryForList(super.getStatementName("getUserFollowedArtistIds"), uid);
	}
	public List<Long> getUserFollowedUids(long uid) {
		return (List<Long>)dao.queryForList(super.getStatementName("getUserFollowedUids"), uid);
	}
	
	public List<Long>  getListenedSongInUserSong(MyAttentionAlbumInfo info){
		return (List<Long>)dao.queryForList(super.getStatementName("getListenedSongInUserSong"),info);
	}
	
	public List<Long>  getListenedSongInUserSongAndPlaylist(MyAttentionAlbumInfo info){
		return (List<Long>)dao.queryForList(super.getStatementName("getListenedSongInUserSongAndPlaylist"),info);
	}
	
	public List<Long> getUserFansIds(long uid,int start,int pagecount){
		Map map =new HashMap();
		map.put("uid", uid);
		map.put("start", start);
		map.put("pagecount", pagecount);
		return (List<Long>)dao.queryForList(super.getStatementName("getUserFansIds"),map);
	}
	
	public int getUserFansIdsAllCount(long uid){
		return (Integer)dao.queryForObject(super.getStatementName("getUserFansIdsAllCount"), uid);
	}
	//--------------------List<Long> end------------------------
	//--------------------List<User> start------------------------
	public List<User> getUserListByUids(List<Long> uidList) {
		if(uidList.isEmpty()){
			return new ArrayList<User>();
		}
		return  (List<User>)dao.queryForList(super.getStatementName("getUserListByUids"), uidList);
	}
	
	public List<User> getListUserList(ListUserInfo info) {
		if(info.getUidList().isEmpty()){
			return new ArrayList<User>();
		}
		return  (List<User>)dao.queryForList(super.getStatementName("getListUserList"), info);
	}
	public List<User> getUserListByUidsWithStatus(List<Long> uidList) {
		if(uidList.isEmpty()){
			return new ArrayList<User>();
		}
		return  (List<User>)dao.queryForList(super.getStatementName("getUserListByUidsWithStatus"), uidList);
	}
	public List<User> getUserListByUidsWithStatusAll(List<Long> uidList) {
		if(uidList.isEmpty()){
			return new ArrayList<User>();
		}
		return  (List<User>)dao.queryForList(super.getStatementName("getUserListByUidsWithStatusAll"), uidList);
	}
	
	public List<User> getUserListByKeyname(SearchUserInfo info) {
		return  (List<User>)dao.queryForList(super.getStatementName("getUserListByKeyname"), info);
	}
	public User getTalentUserById(long uid) {
		return dao.queryForObject(super.getStatementName("getTalentUserById"), uid);
	}
	
	public List<User> getTalentUsers(UserInfo info){
		return (List<User>)dao.queryForList(super.getStatementName("getTalentUsers"),info);
	}
	
	
	public int getTalentUsersAllcount(UserInfo info){
		return (Integer)dao.queryForObject(super.getStatementName("getTalentUsersAllcount"),info);
	}
	
	public List<User> getUserFollowedUsers(UserInfo info){
		return (List<User>)dao.queryForList(super.getStatementName("getUserFollowedUsers"),info);
	}
	
	public int getUserFollowedUsersAllcount(UserInfo info){
		return (Integer)dao.queryForObject(super.getStatementName("getUserFollowedUsersAllcount"),info);
	}
	//--------------------List<User> end------------------------
}
