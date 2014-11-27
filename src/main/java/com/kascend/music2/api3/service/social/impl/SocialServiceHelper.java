package com.kascend.music2.api3.service.social.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.arcsoft.e2e.osm.model.InfoWeibo;
import com.arcsoft.e2e.osm.userserver.sdk.E2eResponse;
import com.arcsoft.e2e.osm.userserver.sdk.E2eUser;
import com.arcsoft.e2e.osm.userserver.sdk.E2eUserService;
import com.kascend.common.util.Validator;
import com.kascend.e2e.osm.model.RequestParam;
import com.kascend.e2e.osm.socialserver.sdk.E2eSocialService;
import com.kascend.e2e.osm.socialserver.sdk.SocialResponse;
import com.kascend.frameworkcommons.config.Configer;
import com.kascend.music2.api3.dao.MetadataDao;
import com.kascend.music2.api3.dao.SocialDao;
import com.kascend.music2.api3.entity.Album;
import com.kascend.music2.api3.entity.AlbumUdata;
import com.kascend.music2.api3.entity.Artist;
import com.kascend.music2.api3.entity.ArtistPic;
import com.kascend.music2.api3.entity.Song;
import com.kascend.music2.api3.entity.SongMv;
import com.kascend.music2.api3.entity.User;
import com.kascend.music2.api3.entity.UserComment;
import com.kascend.music2.api3.entity.UserCommentContent;
import com.kascend.music2.api3.entity.UserMessage;
import com.kascend.music2.api3.entity.UserMetadata;
import com.kascend.music2.api3.entity.UserMv;
import com.kascend.music2.api3.entity.UserOpAlbum;
import com.kascend.music2.api3.entity.UserPhoto;
import com.kascend.music2.api3.entity.UserPlaylist;
import com.kascend.music2.api3.entity.UserShare;
import com.kascend.music2.api3.entity.UserSns;
import com.kascend.music2.api3.entity.UserSnsItem;
import com.kascend.music2.api3.entity.UserSong;
import com.kascend.music2.api3.entity.UserSongAlbum;
import com.kascend.music2.api3.entity.UserSongArtist;
import com.kascend.music2.api3.exception.MusicConst;
import com.kascend.music2.api3.exception.MusicRcException;
import com.kascend.music2.api3.service.enumeration.ActivityEnum;
import com.kascend.music2.api3.service.enumeration.PopularityEnum;
import com.kascend.music2.api3.service.metadata.impl.MetadataServiceHelper;
import com.kascend.music2.api3.service.metadata.info.GetAlbumsOfArtistInfo;
import com.kascend.music2.api3.service.metadata.info.GetMvsOfPlaylistInfo;
import com.kascend.music2.api3.service.metadata.info.GetSearchInfo;
import com.kascend.music2.api3.service.metadata.info.GetSongsOfAlbumInfo;
import com.kascend.music2.api3.service.metadata.info.GetSongsOfPlaylistInfo;
import com.kascend.music2.api3.service.metadata.info.PageInfo;
import com.kascend.music2.api3.service.metadata.info.SyncPlaylistInfo;
import com.kascend.music2.api3.service.metadata.response.New_AlbumInfoResponse;
import com.kascend.music2.api3.service.metadata.response.New_AlbumResponse;
import com.kascend.music2.api3.service.metadata.response.New_ArtistInfoResponse;
import com.kascend.music2.api3.service.metadata.response.New_ArtistListResponse;
import com.kascend.music2.api3.service.metadata.response.New_UserMvListResponse;
import com.kascend.music2.api3.service.metadata.response.New_UserSongListResponse;
import com.kascend.music2.api3.service.metadata.response.entry.New_AlbumEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_AlbumListEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_ArtistEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_ArtistListEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_MvEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_SongEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_SongListEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_UserEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_UserMvEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_UserMvListEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_UserSongEntry;
import com.kascend.music2.api3.service.metadata.response.entry.New_UserSongListEntry;
import com.kascend.music2.api3.service.response.entry.MusicBaseEntry;
import com.kascend.music2.api3.service.social.info.ArtistFollowStatusInfo;
import com.kascend.music2.api3.service.social.info.AttentionUsersInfo;
import com.kascend.music2.api3.service.social.info.CommentInfo;
import com.kascend.music2.api3.service.social.info.DeletePlaylistSongInfo;
import com.kascend.music2.api3.service.social.info.DiscoveryAlbumOfSongInfo;
import com.kascend.music2.api3.service.social.info.FollowArtistInfo;
import com.kascend.music2.api3.service.social.info.FollowUserInfo;
import com.kascend.music2.api3.service.social.info.GetCommentsOfItemInfo;
import com.kascend.music2.api3.service.social.info.GetLastSyncPlaylistTimeInfo;
import com.kascend.music2.api3.service.social.info.GetPlaylistsOfUserInfo;
import com.kascend.music2.api3.service.social.info.GetSnsShareInfo;
import com.kascend.music2.api3.service.social.info.GetTalentInfo;
import com.kascend.music2.api3.service.social.info.GetUserAlbumsInfo;
import com.kascend.music2.api3.service.social.info.GetUserAlbumsOfArtistInfo;
import com.kascend.music2.api3.service.social.info.GetUserArtistInfo;
import com.kascend.music2.api3.service.social.info.GetUserFansInfo;
import com.kascend.music2.api3.service.social.info.GetUserSongsOfAlbum;
import com.kascend.music2.api3.service.social.info.LeaveMessageInfo;
import com.kascend.music2.api3.service.social.info.LikeInfo;
import com.kascend.music2.api3.service.social.info.ListUserInfo;
import com.kascend.music2.api3.service.social.info.MyAttentionAlbumInfo;
import com.kascend.music2.api3.service.social.info.MyAttentionInfo;
import com.kascend.music2.api3.service.social.info.RecommendUserInfo;
import com.kascend.music2.api3.service.social.info.SearchUserInfo;
import com.kascend.music2.api3.service.social.info.ShareInfo;
import com.kascend.music2.api3.service.social.info.SnsAddItemInfo;
import com.kascend.music2.api3.service.social.info.SnsGetUserRelationListInfo;
import com.kascend.music2.api3.service.social.info.SnsInviteFriendInfo;
import com.kascend.music2.api3.service.social.info.SnsItemsInfo;
import com.kascend.music2.api3.service.social.info.SnsItemsOfArtistInfo;
import com.kascend.music2.api3.service.social.info.SnsWeiBoInfo;
import com.kascend.music2.api3.service.social.info.UpdateUserHeadIconInfo;
import com.kascend.music2.api3.service.social.info.UpdateUserInfo;
import com.kascend.music2.api3.service.social.info.UserFollowStatusInfo;
import com.kascend.music2.api3.service.social.info.UserFollowedArtistInfo;
import com.kascend.music2.api3.service.social.info.UserInfo;
import com.kascend.music2.api3.service.social.info.UserSnsShareInfo;
import com.kascend.music2.api3.service.social.response.CommentListResponse;
import com.kascend.music2.api3.service.social.response.CommentResponse;
import com.kascend.music2.api3.service.social.response.DeletePlaylistSongResponse;
import com.kascend.music2.api3.service.social.response.DiscoveryAlbumOfSongResponse;
import com.kascend.music2.api3.service.social.response.FollowResponse;
import com.kascend.music2.api3.service.social.response.ItemListResponse;
import com.kascend.music2.api3.service.social.response.LastSyncPlaylistTimeResponse;
import com.kascend.music2.api3.service.social.response.LeaveMessageResponse;
import com.kascend.music2.api3.service.social.response.LikeResponse;
import com.kascend.music2.api3.service.social.response.MyAttentionAlbumResponse;
import com.kascend.music2.api3.service.social.response.ShareResponse;
import com.kascend.music2.api3.service.social.response.UserListResponse;
import com.kascend.music2.api3.service.social.response.UserPlaylistResponse;
import com.kascend.music2.api3.service.social.response.UserResponse;
import com.kascend.music2.api3.service.social.response.UserSnsResponse;
import com.kascend.music2.api3.service.social.response.UserSnsShareResponse;
import com.kascend.music2.api3.service.social.response.WeiboBondAccountResponse;
import com.kascend.music2.api3.service.social.response.entry.ArtistFollowStatusEntry;
import com.kascend.music2.api3.service.social.response.entry.ArtistFollowStatusListEntry;
import com.kascend.music2.api3.service.social.response.entry.BoundSnsInfoEntry;
import com.kascend.music2.api3.service.social.response.entry.BoundSnsInfoEntryList;
import com.kascend.music2.api3.service.social.response.entry.CommentEntry;
import com.kascend.music2.api3.service.social.response.entry.CommentListEntry;
import com.kascend.music2.api3.service.social.response.entry.DiscoveryAlbumOfSongEntry;
import com.kascend.music2.api3.service.social.response.entry.ItemCommentEntry;
import com.kascend.music2.api3.service.social.response.entry.ItemEntry;
import com.kascend.music2.api3.service.social.response.entry.ItemListEntry;
import com.kascend.music2.api3.service.social.response.entry.LastSongEntry;
import com.kascend.music2.api3.service.social.response.entry.MessageEntry;
import com.kascend.music2.api3.service.social.response.entry.MessageListEntry;
import com.kascend.music2.api3.service.social.response.entry.MyAttentionAlbumEntry;
import com.kascend.music2.api3.service.social.response.entry.SnsInfoEntry;
import com.kascend.music2.api3.service.social.response.entry.SnsItemEntry;
import com.kascend.music2.api3.service.social.response.entry.SnsUserEntry;
import com.kascend.music2.api3.service.social.response.entry.SnsUserListEntry;
import com.kascend.music2.api3.service.social.response.entry.UserEntry;
import com.kascend.music2.api3.service.social.response.entry.UserFollowStatusEntry;
import com.kascend.music2.api3.service.social.response.entry.UserFollowStatusListEntry;
import com.kascend.music2.api3.service.social.response.entry.UserListEntry;
import com.kascend.music2.api3.service.social.response.entry.UserPlaylistEntry;
import com.kascend.music2.api3.service.social.response.entry.UserPlaylistListEntry;
import com.kascend.music2.api3.service.user.UserService;
import com.kascend.music2.api3.service.util.AnalysisZipFileUtil;
import com.kascend.music2.api3.service.util.Constants;
import com.kascend.music2.api3.service.util.ServiceUtils;
import com.kascend.music2.recommend.sdk.info.KasUserRecommendInfo;
import com.kascend.music2.recommend.sdk.response.KasUserRecommendResponse;
import com.kascend.music2.recommend.sdk.service.RecommendService;

public class SocialServiceHelper {

	private static final Logger log = Logger
			.getLogger(SocialServiceHelper.class);

	protected SocialDao socialDao;

	MetadataServiceHelper mmsHelper;

	protected MetadataDao metadataDao;

	protected RecommendService recommendService;

	UserService auth;

	public RecommendService getRecommendService() {
		return recommendService;
	}

	public void setRecommendService(RecommendService recommendService) {
		this.recommendService = recommendService;
	}

	public UserService getAuth() {
		return auth;
	}

	public void setAuth(UserService auth) {
		this.auth = auth;
	}

	public MetadataServiceHelper getMmsHelper() {
		return mmsHelper;
	}

	public void setMmsHelper(MetadataServiceHelper mmsHelper) {
		this.mmsHelper = mmsHelper;
	}

	public SocialDao getSocialDao() {
		return socialDao;
	}

	public void setSocialDao(SocialDao socialDao) {
		this.socialDao = socialDao;
	}

	public MetadataDao getMetadataDao() {
		return metadataDao;
	}

	public void setMetadataDao(MetadataDao metadataDao) {
		this.metadataDao = metadataDao;
	}

	public  String getSubTitleOfItem(String prefix,int itemType,long itemId){
		String title=prefix;
		if(itemType==Constants.ITEM_TYPE_SONG){
			title+=Constants.SHARE_TITLE_SONG;
			Song song=metadataDao.getSongInfoBySongIdNoCondition(itemId);
			if(song!=null){
				String subTitle=song.getSongTitle();
				title+="\"" + subTitle + "\"";
			}
		}else if(itemType==Constants.ITEM_TYPE_ALBUM){
			title+=Constants.SHARE_TITLE_ALBUM;
			Album album=metadataDao.getAlbumInfoById(itemId);
			if(album!=null){
				String subTitle=album.getAlbumTitle();
				title+="\"" + subTitle + "\"";
			}
		}else if(itemType==Constants.ITEM_TYPE_ARTIST){
			title+=Constants.SHARE_TITLE_ARTIST;
			Artist artist=metadataDao.getArtistInfoByArtistIdOrName(itemId, null);
			if(artist!=null){
				String subTitle=artist.getArtistName();
				title+="\"" + subTitle + "\"";
			}
		}else if(itemType==Constants.ITEM_TYPE_SONG_PLAYLIST){
			title+=Constants.SHARE_TITLE_SONG_PLAYLIST;//TODO 未加入各类的title
		}else if(itemType==Constants.ITEM_TYPE_MV){
			title+=Constants.SHARE_TITLE_MV;
		}else if(itemType==Constants.ITEM_TYPE_SNS){
			title+=Constants.SHARE_TITLE_WEIBO;
		}else if(itemType==Constants.ITEM_TYPE_MV_PLAYLIST){
			title+=Constants.SHARE_TITLE_MV_PLAYLIST;
		}
		return title;
	}	
	public ShareResponse share(ShareInfo info) {
		//参数判断
		if(info.getId()<=0||info.getItemtype()<=0){
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		ServiceUtils.getStrParamLength(info.getTitle(),240);
		ServiceUtils.getLenParamLength(info.getId(), 19);
		ShareResponse response = new ShareResponse();
		// 插入user_share
		if(info.getDataFrom()==0){
			info.setDataFrom(Constants.SHARE_DATAFROM_SHARE);
		}
		if(info.getShareType()==0){
			info.setShareType(Constants.SHARE_TYPE_SHARE);
		}
		long forwardShareId=info.getForwardshareid();
		boolean beForwarded=false;
		if(forwardShareId>0){
			info.setShareType(Constants.SHARE_TYPE_FORWARD);
			UserShare userShare=socialDao.getUserShareByShareId(forwardShareId);
			if(userShare!=null){
				info.setForwardshareid(userShare.getShareId());
				info.setForwarduid(userShare.getUid());
				info.setIsForward(1);
				beForwarded=true;
			}else{
				info.setForwardshareid(0);
				info.setForwarduid(0);
			}
		}
		Long shareId = socialDao.saveUserShare(info);
		info.setCountType(Constants.COUNT_TYPE_SHARE);
		socialDao.updateUserKindCount(info);
		updateItemsCounts(info);
		//保存成功，则修改各个字段(暂时不在updateItemsCounts中，因为数据类别暂时不对应，独立出来)
		if(beForwarded){
			socialDao.updateUserShare(forwardShareId,Constants.COUNT_TYPE_FORWARD);
		}
		// 保存share评论
		String title = info.getTitle();
		if (Validator.isBlank(title)) {
			title = getSubTitleOfItem(Constants.SHARE_TITLE,info.getItemtype(),info.getId());
		}
		info.setTitle(title);
		info.setShareId(shareId);
		socialDao.saveUserShareContent(info);
		//用户选择是否搜藏
		//TODO
		if(info.getLike()==1&&info.getItemtype()==Constants.ITEM_TYPE_SONG){
			LikeInfo likeInfo=new LikeInfo();
			likeInfo.setUid(info.getUid());
			likeInfo.setLike(1);
			likeInfo.setId(info.getId());
			likeInfo.setItemtype(info.getItemtype());
			this.like(likeInfo);
		}
		return response;
	}

	// 活跃度
	public void addUserActivity(long uid, int score) {
		int activityCount = socialDao.getUserActivityCount(uid);
		int lastCount = activityCount + score;
		socialDao.updateUserActivityCount(uid, lastCount);
	}

	public void addUserActivityLog(long uid, int score, int type) {
		socialDao.addUserActivityLog(uid, type, score);
	}

	// public void deleteUserActivity(long uid,int score){
	// int activityCount=socialDao.getUserActivityCount(uid);
	// int lastCount=activityCount-score;
	// if(lastCount<0)lastCount=0;
	// socialDao.updateUserActivityCount(uid, lastCount);
	// }
	// public void addUserActivityLog(long uid,int score,int type){
	// socialDao.addUserActivityLog(uid, type, score);
	// UserActivityLog
	// userActivityLog=socialDao.getUserActivityLogByUidAndType(uid, type);
	// if(userActivityLog!=null){
	// int activityCount=userActivityLog.getActivity();
	// int lastCount=activityCount+score;
	// socialDao.updateUserActivityLog(uid, type, lastCount);
	// }else{
	// socialDao.addUserActivityLog(uid, type, score);
	// }
	// }

	// public void deleteUserActivityLog(long uid,int score,int type){
	// UserActivityLog
	// userActivityLog=socialDao.getUserActivityLogByUidAndType(uid, type);
	// if(userActivityLog!=null){
	// int activityCount=userActivityLog.getActivity();
	// int lastCount=activityCount-score;
	// if(lastCount<0)lastCount=0;
	// socialDao.updateUserActivityLog(uid, type, lastCount);
	// }
	// }

	// 人气
	public void addUserPopularity(long uid, int score) {
		int popularityCount = socialDao.getUserPopularityCount(uid);
		int lastCount = popularityCount + score;
		socialDao.updateUserPopularityCount(uid, lastCount);
	}

	public void addUserPopularityLog(long uid, int score, int type) {
		socialDao.addUserPopularityLog(uid, type, score);
	}

	// public void deleteUserPopularity(long uid,int score){
	// int popularityCount=socialDao.getUserPopularityCount(uid);
	// int lastCount=popularityCount-score;
	// if(lastCount<0)lastCount=0;
	// socialDao.updateUserPopularityCount(uid, lastCount);
	// }

	// public void addUserPopularityLog(long uid,int score,int type){
	// socialDao.addUserPopularityLog(uid, type, score);
	// UserPopularityLog
	// userPopularityLog=socialDao.getUserPopularityLogByUidAndType(uid, type);
	// if(userPopularityLog!=null){
	// int popularityCount=userPopularityLog.getPopularity();
	// int lastCount=popularityCount+score;
	// socialDao.updateUserPopularityLog(uid, type, lastCount);
	// }else{
	// socialDao.addUserPopularityLog(uid, type, score);
	// }
	// }
	// public void deleteUserPopularityLog(long uid,int score,int type){
	// UserPopularityLog
	// userPopularityLog=socialDao.getUserPopularityLogByUidAndType(uid, type);
	// if(userPopularityLog!=null){
	// int popularityCount=userPopularityLog.getPopularity();
	// int lastCount=popularityCount-score;
	// if(lastCount<0)lastCount=0;
	// socialDao.updateUserPopularityLog(uid, type, lastCount);
	// }
	// }

	public LikeResponse like(LikeInfo info) {
		//参数判断
		if(info.getId()<=0||info.getItemtype()<=0||info.getLike()<0||info.getShare()<0){
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		LikeResponse response = new LikeResponse();
		if (info.getItemtype() == Constants.ITEM_TYPE_SONG) {
			long playlistId = 0l;
			UserPlaylist userPlaylist = socialDao
					.getUserPlaylistByUidAndDataFrom(
							Constants.USER_PLAYLIST_DATA_FROM_SYSTEM,
							info.getUid());
			if (userPlaylist == null) {
				UserMetadata userMetadata = new UserMetadata();
				userMetadata
						.setDataFrom(Constants.USER_PLAYLIST_DATA_FROM_SYSTEM);
				userMetadata.setPlaylistTitle(Constants.DEFAULT_PLAYLIST_TITLE);
				userMetadata.setPlaylistType(Constants.USER_PLAYLIST_TYPE_SONG);
				userMetadata.setPlaylistThumb("");
				userMetadata.setUid(info.getUid());
				userMetadata.setDescription("");
				playlistId = socialDao.saveUserPlaylist(userMetadata);
			} else {
				playlistId = userPlaylist.getPlaylistId();
				socialDao.updateUserPlaylistUpdatetimeAndImage(playlistId,null);
				// 修改updatetime
			}
			long songId = socialDao.getSongLike(info);
			int type = ActivityEnum.likeSong.getType();
			int score = ActivityEnum.likeSong.getValue();
			if (info.getLike() == Constants.ITEM_LIKE) {
				if (songId == 0) {
					Song song = metadataDao.getSongInfoBySongIdNoCondition(info
							.getId());
					if (song != null) {
						// 增加活跃度
						this.addUserActivity(info.getUid(), score);
						this.addUserActivityLog(info.getUid(), score, type);
						info.setId(song.getSongId());
						socialDao.saveSongLike(info);
						List<Long> songIds = new ArrayList<Long>();
						songIds.add(song.getSongId());
						// 修改user_op_album & album_udata
						if (song.getAlbumId() > 0) {
							mmsHelper
									.operateUserOpAlbumAndAlbumUdata(
											info.getUid(), song.getAlbumId(),
											song.getSongId(),
											Constants.OP_TYPE_LIKE, 0);
						}
						metadataDao.downloadSongFileOfAlbumBySongId(songIds);
						// 收藏歌曲，活跃度增加5
						long usId = socialDao
								.getUserSongBySongIdAndPlaylistId(
										song.getSongId(), playlistId,
										info.getUid());
						if (usId > 0) {
							// 修改datafrom=喜欢
							socialDao
									.updateUserSongDataFrom(
											Constants.USER_SONG_DATA_FROM_FAVOURTE,
											song.getSongId(), playlistId,
											info.getUid());
						} else {
							UserSong metaData = new UserSong();
							metaData.setPlaylistId(playlistId);
							metaData.setUid(info.getUid());
							metaData.setSongId(song.getSongId());
							metaData.setSongTitle(song.getSongTitle());
							metaData.setArtistName(song.getArtistName());
							metaData.setAlbumTitle(song.getAlbumTitle());
							metaData.setSongThumb(song.getSongThumbUri());
							metaData.setArtistId(song.getArtistId());
							metaData.setAlbumId(song.getAlbumId());
							metaData.setDuration(song.getSongDuration());
							metaData.setDataFrom(Constants.USER_SONG_DATA_FROM_FAVOURTE);
							socialDao.saveUserSongList(metaData);
							computeLikeCount(playlistId, info.getUid(),
									song.getSongId());
						}
						//用户选择是否分享，分享的是歌曲
						//TODO
						if(info.getShare()==1){
							ShareInfo shareInfo=new ShareInfo();
							shareInfo.setUid(info.getUid());
							shareInfo.setId(song.getSongId());
							shareInfo.setDataFrom(Constants.SHARE_DATAFROM_LIKE);
							shareInfo.setShareType(Constants.SHARE_TYPE_LIKE);
							shareInfo.setItemtype(Constants.ITEM_TYPE_SONG);
							shareInfo.setTitle("收藏了歌曲\"" + song.getSongTitle() + "\"");
							this.share(shareInfo);
						}
					}
				}
			} else if (info.getLike() == Constants.ITEM_UNLIKE) {
				if (songId > 0) {
					// this.deleteUserActivity(info.getUid(),score);
					// this.deleteUserActivityLog(info.getUid(), score, type);
					socialDao.deleteSongLike(info);
					socialDao.updateUserSongDataFrom(
							Constants.USER_SONG_DATA_FROM_LOCAL, songId,
							playlistId, info.getUid());
					// socialDao.deleteFromUserSong(songId, playlistId,
					// info.getUid());
				}
			}
			updateUserNativePlaylistCount(info.getUid());
			// operateUserSongCount(playlistId);
		} else if (info.getItemtype() == Constants.ITEM_TYPE_ALBUM) {
			long albumId = socialDao.getAlbumLike(info);
			if (info.getLike() == Constants.ITEM_LIKE) {
				if (albumId == 0) {
					socialDao.saveAlbumLike(info);
				}
			} else if (info.getLike() == Constants.ITEM_UNLIKE) {
				if (albumId > 0) {
					socialDao.deleteAlbumLike(info);
				}
			}
		} else if (info.getItemtype() == Constants.ITEM_TYPE_ARTIST) {
			long artistId = socialDao.getArtistLike(info);
			if (info.getLike() == Constants.ITEM_LIKE) {
				if (artistId == 0) {
					socialDao.saveArtistLike(info);
				}
			} else if (info.getLike() == Constants.ITEM_UNLIKE) {
				if (artistId > 0) {
					socialDao.deleteArtistLike(info);
				}
			}
		} else if (info.getItemtype() == Constants.ITEM_TYPE_SONG_PLAYLIST) {
			long playlistId = socialDao.getUserPlaylistLike(info);
			if (info.getLike() == Constants.ITEM_LIKE) {
				if (playlistId == 0) {
					socialDao.saveUserPlaylistLike(info);
				}
			} else if (info.getLike() == Constants.ITEM_UNLIKE) {
				if (playlistId > 0) {
					socialDao.deleteUserPlaylistLike(info);
				}
			}
		} else if (info.getItemtype() == Constants.ITEM_TYPE_MV) {
			long songMvId = socialDao.getSongMvLike(info);
			if (info.getLike() == Constants.ITEM_LIKE) {
				if (songMvId == 0) {
					socialDao.saveSongMvLike(info);
				}
			} else if (info.getLike() == Constants.ITEM_UNLIKE) {
				if (songMvId > 0) {
					socialDao.deleteSongMvLike(info);
				}
			}
		} else if (info.getItemtype() == Constants.ITEM_TYPE_SNS) {
			
			if (info.getLike() == Constants.ITEM_LIKE) {

			} else if (info.getLike() == Constants.ITEM_UNLIKE) {

			}
		} else if (info.getItemtype() == Constants.ITEM_TYPE_MV_PLAYLIST) {
			long playlistId = socialDao.getUserPlaylistLike(info);
			if (info.getLike() == Constants.ITEM_LIKE) {
				if (playlistId == 0) {
					socialDao.saveUserPlaylistLike(info);
				}
			} else if (info.getLike() == Constants.ITEM_UNLIKE) {
				if (playlistId > 0) {
					socialDao.deleteUserPlaylistLike(info);
				}
			}
		}
//		// 插入user_share
//		info.setDataFrom(Constants.SHARE_DATAFROM_LIKE);
//		info.setShareType(Constants.SHARE_TYPE_LIKE);
//		socialDao.saveUserShare(info);

		info.setCountType(Constants.COUNT_TYPE_LIKE);
		socialDao.updateUserKindCount(info);
		updateItemsCounts(info);
		return response;
	}

	public void updateItemsCounts(SnsItemsInfo info) {

		if (info.getItemtype() == Constants.ITEM_TYPE_SONG) {
			socialDao.updateSongUdata(info);
		} else if (info.getItemtype() == Constants.ITEM_TYPE_ALBUM) {
			socialDao.updateAlbumUdata(info);
		} else if (info.getItemtype() == Constants.ITEM_TYPE_ARTIST) {
			socialDao.updateArtistUdata(info);
		} else if (info.getItemtype() == Constants.ITEM_TYPE_SONG_PLAYLIST||info.getItemtype() == Constants.ITEM_TYPE_MV_PLAYLIST) {
			socialDao.updatePlaylistUdata(info);
		} else if (info.getItemtype() == Constants.ITEM_TYPE_MV) {
			socialDao.updateSongMvUdata(info);
		} else if (info.getItemtype() == Constants.ITEM_TYPE_SNS) {

		} 
	}

	public ItemListResponse attentionusers(AttentionUsersInfo info) {
		ItemListResponse itemListResponse = new ItemListResponse();
		List<Long> attentionUidList = new ArrayList<Long>();
		if (info.getType() == Constants.USER_TYPE_ARTIST) {
			attentionUidList = socialDao.getUserFollowedArtistUids(info
					.getUid());

		} else if (info.getType() == Constants.USER_TYPE_GENERAL
				|| info.getType() == Constants.USER_TYPE_TALENT) {

			attentionUidList = socialDao.getUserFollowedUids(info.getUid());

		} else if (info.getType() == Constants.USER_TYPE_ALL) {
			attentionUidList = socialDao.getUserFollowedUids(info.getUid());
			attentionUidList.addAll(socialDao.getUserFollowedArtistUids(info
					.getUid()));
		}
		info.setUidList(attentionUidList);
		List<ItemEntry> itemList = getItemListbyUids(info);
		ItemListEntry result = new ItemListEntry(itemList);
		if (info.getAllcountflag() == 1) {
			int allCount = socialDao.getUserShareListAllCountByUids(info);
			result.setAllcount(allCount);
		}
		itemListResponse.setItemslist(result);

		return itemListResponse;
	}

	public ItemListResponse plaza(AttentionUsersInfo info) {
		ItemListResponse itemListResponse = new ItemListResponse();
		// share_type=1
		info.setShareType(Constants.SHARE_TYPE_SHARE);
		List<ItemEntry> itemList = getItemListbyItemTypes(info);
		itemListResponse.setItemslist(new ItemListEntry(itemList));
		return itemListResponse;
	}

	public ItemListResponse getitemsofuser(AttentionUsersInfo info) {
		if (info.getTargetuid()<= 0) {
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		ItemListResponse itemListResponse = new ItemListResponse();
		ItemListEntry itemListEntry = getItemListEntrybyTargetUidAndItemTypes(info);
		itemListResponse.setItemslist(itemListEntry);
		return itemListResponse;
	}

	protected List<ItemEntry> getItemListbyItemTypes(AttentionUsersInfo info) {
		ServiceUtils.pageCompute(info);
		List<UserShare> userShareList = socialDao
				.getUserShareListByItemTypes(info);
		return getItemList(userShareList);
	}


	protected ItemListEntry getItemListEntrybyTargetUidAndItemTypes(AttentionUsersInfo info) {
		ItemListEntry itemListEntry=new ItemListEntry();
		ServiceUtils.pageCompute(info);
		List<UserShare> userShareList = socialDao.getUserShareListByItemTypeAndTargetUid(info);
		List<ItemEntry> itemEntryList= getItemList(userShareList);
		itemListEntry.setItem(itemEntryList);
		if(info.getAllcountflag()==1){
			int allcount=socialDao.getUserShareListByItemTypeAndTargetUidAllCount(info);
			itemListEntry.setAllcount(allcount);
		}
		return itemListEntry;
	}
	
	protected List<ItemEntry> getItemListbyUids(AttentionUsersInfo info) {
		ServiceUtils.pageCompute(info);
		List<UserShare> userShareList = socialDao.getUserShareListByUids(info);
		return getItemList(userShareList);
	}

	protected List<ItemEntry> getUserShareListBySnsShareId(
			AttentionUsersInfo info) {
		ServiceUtils.pageCompute(info);
		List<UserShare> userShareList = socialDao
				.getUserShareListBySnsShareId(info);
		return getItemList(userShareList);
	}

	protected List<ItemEntry> getItemList(List<UserShare> userShareList) {
		List<ItemEntry> itemList = new ArrayList<ItemEntry>();
		/**
		 * 
		 * 获取item type对应的song, artist,album,mv, sns weibo等数据
		 */
		List<Long> snsItemIdList = new ArrayList<Long>();
		List<Long> songIdList = new ArrayList<Long>();
		List<Long> artistIdList = new ArrayList<Long>();
		List<Long> albumIdList = new ArrayList<Long>();
		List<Long> playlistIdList = new ArrayList<Long>();
		List<Long> mvIdList = new ArrayList<Long>();
		List<Long> commentIdList = new ArrayList<Long>();
		for (int i = 0; i < userShareList.size(); i++) {
			UserShare userShare = userShareList.get(i);
			if (userShare.getItemType() == Constants.ITEM_TYPE_SONG) {
				songIdList.add(userShare.getItemId());
			} else if (userShare.getItemType() == Constants.ITEM_TYPE_ARTIST) {
				artistIdList.add(userShare.getItemId());
			} else if (userShare.getItemType() == Constants.ITEM_TYPE_ALBUM) {
				albumIdList.add(userShare.getItemId());
			} else if (userShare.getItemType() == Constants.ITEM_TYPE_MV) {
				mvIdList.add(userShare.getItemId());
			} else if (userShare.getItemType() == Constants.ITEM_TYPE_SONG_PLAYLIST
					|| userShare.getItemType() == Constants.ITEM_TYPE_MV_PLAYLIST) {
				playlistIdList.add(userShare.getItemId());
			} else if (userShare.getItemType() == Constants.ITEM_TYPE_SNS) {
				snsItemIdList.add(userShare.getItemId());
			}else if (userShare.getItemType() == Constants.ITEM_TYPE_COMMENT) {
				commentIdList.add(userShare.getItemId());
			}
		}

		List<UserSnsItem> snsItemList = new ArrayList<UserSnsItem>();
		List<Song> songList = new ArrayList<Song>();
		List<Artist> artistList = new ArrayList<Artist>();
		List<Album> albumList = new ArrayList<Album>();
		List<UserPlaylist> playlistList = new ArrayList<UserPlaylist>();
		List<SongMv> mvList = new ArrayList<SongMv>();
		
		List<UserComment> commentList=new ArrayList<UserComment>();

		if (!snsItemIdList.isEmpty()) {
			snsItemList = socialDao.getUserSnsItemListByIds(snsItemIdList);
		}
		if (!songIdList.isEmpty()) {
			songList = metadataDao.getSongListByIds(songIdList);
		}
		if (!artistIdList.isEmpty()) {
			artistList = metadataDao.getArtistListByIds(artistIdList);
		}
		if (!albumIdList.isEmpty()) {
			albumList = metadataDao.getAlbumListByIds(albumIdList);
		}
		if (!playlistIdList.isEmpty()) {
			playlistList = socialDao.getUserPlaylistListByIds(playlistIdList);
		}
		if (!mvIdList.isEmpty()) {
			mvList = metadataDao.getSongMvListByIds(mvIdList);
		}
		if(!commentIdList.isEmpty()){
			commentList=socialDao.getUserCommentListByCommentIdList(commentIdList);
		}
		for (int i = 0; i < userShareList.size(); i++) {
			UserShare userShare = userShareList.get(i);
			MusicBaseEntry item = null;
			if (userShare.getItemType() == Constants.ITEM_TYPE_SONG) {
				for (Song song : songList) {
					if (song.getSongId() == userShare.getItemId()) {
						item =getAlbumEntryByAlbumId(song.getAlbumId());
						break;
					}
				}

			} else if (userShare.getItemType() == Constants.ITEM_TYPE_ARTIST) {
				for (Artist artist : artistList) {
					if (artist.getArtistId() == userShare.getItemId()) {
						item = new New_ArtistEntry(artist);
						break;
					}
				}

			} else if (userShare.getItemType() == Constants.ITEM_TYPE_ALBUM) {
				for (Album album : albumList) {
					if (album.getAlbumId() == userShare.getItemId()) {
						item = new New_AlbumEntry(album);
						break;
					}
				}
			} else if (userShare.getItemType() == Constants.ITEM_TYPE_MV) {
				for (SongMv mv : mvList) {
					if (mv.getMvId() == userShare.getItemId()) {
						item = new New_MvEntry(mv);
						break;
					}
				}
			} else if (userShare.getItemType() == Constants.ITEM_TYPE_SONG_PLAYLIST
					|| userShare.getItemType() == Constants.ITEM_TYPE_MV_PLAYLIST) {
				for (UserPlaylist playlist : playlistList) {
					if (playlist.getPlaylistId() == userShare.getItemId()) {
						item = getUserPlaylistEntry(playlist);
						break;
					}
				}
			} else if (userShare.getItemType() == Constants.ITEM_TYPE_SNS) {
				for (UserSnsItem snsItem : snsItemList) {
					if (snsItem.getItemId() == userShare.getItemId()) {
						item = new SnsItemEntry(snsItem);
						break;
					}
				}
			}else if(userShare.getItemType() == Constants.ITEM_TYPE_COMMENT){
				for(UserComment userComment:commentList){
					if(userComment.getCommentId()==userShare.getItemId()){
						item=getItemCommentEntry(userComment);
						break;
					}
				}
			}
			ItemEntry itemEntry = new ItemEntry(userShare, item);
			itemList.add(i, itemEntry);
		}
		return itemList;
	}

	public New_AlbumEntry getAlbumEntryByAlbumId(long sourceAlbumId){
		Album album = metadataDao.getAlbumInfoByIdNoCondition(sourceAlbumId);
		if(album!=null){
			New_AlbumEntry albumEntry=new New_AlbumEntry(album);
			long albumId = album.getAlbumId();
			List<Song> songList = getSongListByAlbumId(albumId,
					Constants.MY_ATTENTION_SONGCOUNT);
			New_SongListEntry listenSongListEntry = new New_SongListEntry();
			List<New_SongEntry> listenEntryList = new ArrayList<New_SongEntry>();
			for (int j = 0; j < songList.size(); j++) {
				Song song = songList.get(j);
				song.setAlbumId(0);
				song.setAlbumTitle(null);
				New_SongEntry songEntry = new New_SongEntry(song);
				listenEntryList.add(j, songEntry);
			}
			listenSongListEntry.setSong(listenEntryList);
			albumEntry.setSongslist(listenSongListEntry);
			return albumEntry;
		}
		return new New_AlbumEntry();
	}
	
	public UserPlaylistEntry getUserPlaylistEntry(UserPlaylist userPlaylist){
		UserPlaylistEntry userPlaylistEntry=new UserPlaylistEntry(userPlaylist);
		if(userPlaylist.getPlaylistType()==1){
			New_UserSongListEntry userSongListEntry=new New_UserSongListEntry();
			long playlistId=userPlaylist.getPlaylistId();
			List<New_UserSongEntry> userSongEntryList=new ArrayList<New_UserSongEntry>();
			userSongListEntry.setSong(userSongEntryList);
			GetSongsOfPlaylistInfo getSongsOfPlaylistInfo=new GetSongsOfPlaylistInfo();
			getSongsOfPlaylistInfo.setPlaylistid(playlistId);
			getSongsOfPlaylistInfo.setSort(1);//播放次数
			getSongsOfPlaylistInfo.setPagecount(3);
			List<UserSong> userSongList=socialDao.getUserSongListByPlaylistId(getSongsOfPlaylistInfo);
			for(int i=0;i<userSongList.size();i++){
				New_UserSongEntry entry=new New_UserSongEntry(userSongList.get(i));
				userSongEntryList.add(i,entry);
			}
			userPlaylistEntry.setSongslist(userSongListEntry);
		}else if(userPlaylist.getPlaylistType()==2){
			
		}
		
		return userPlaylistEntry;
	}
	
	public ItemCommentEntry getItemCommentEntry(UserComment userComment){
		ItemCommentEntry itemCommentEntry=new ItemCommentEntry();
		itemCommentEntry.setCommentcontent(userComment.getContent());
		itemCommentEntry.setCommentid(userComment.getCommentId());
		long commentAlbumId=0L;
		if(userComment.getItemType()==Constants.ITEM_TYPE_SONG){
			Song song=metadataDao.getSongInfoBySongIdNoCondition(userComment.getItemId());
			if(song!=null){
				commentAlbumId=song.getAlbumId();
			}
		}else if(userComment.getItemType()==Constants.ITEM_TYPE_ALBUM){
			commentAlbumId=userComment.getItemId();
		}
		New_AlbumEntry albumEntry=getAlbumEntryByAlbumId(commentAlbumId);
		itemCommentEntry.setAlbum(albumEntry);
		return itemCommentEntry;
	}
	
	public UserResponse getuserinfo(UserInfo info) {
		UserResponse userResponse = new UserResponse();
		User user = socialDao.getUserById((long) info.getUid());
		if (user != null) {
			UserEntry userEntry = new UserEntry(user);
			BoundSnsInfoEntryList boundEntry = new BoundSnsInfoEntryList();
			List<BoundSnsInfoEntry> snsList = new ArrayList<BoundSnsInfoEntry>();
			List<UserSns> userSnsList = socialDao.getUserSnsListByUid(info
					.getUid());
			if (userSnsList.size() > 0) {
				for (UserSns userSns : userSnsList) {
					BoundSnsInfoEntry boundSnsInfoEntry = new BoundSnsInfoEntry(
							userSns);
					snsList.add(boundSnsInfoEntry);
				}
				boundEntry.setSns(snsList);
				userResponse.setBoundsnslist(boundEntry);
			}
			userResponse.setUser(userEntry);
		}
		return userResponse;
	}

	public UserListResponse gettalentusers(UserInfo info) {
		UserListResponse response = new UserListResponse();
		UserListEntry result = new UserListEntry();
		ServiceUtils.pageCompute(info);
		response.setUserslist(result);
		List<User> userList = socialDao.getTalentUsers(info);
		if (userList.size() > 0) {
			List<UserEntry> entryList = new ArrayList<UserEntry>();
			for (int i = 0; i < userList.size(); i++) {
				UserEntry userEntry = new UserEntry(userList.get(i));
				entryList.add(i, userEntry);
			}
			result.setUser(entryList);
			if (info.getAllcountflag() == 1) {
				result.setAllcount(socialDao.getTalentUsersAllcount(info));
			}
		}
		return response;
	}

	public UserListResponse getuserfollowedusers(UserInfo info) {
		UserListResponse response = new UserListResponse();
		UserListEntry result = new UserListEntry();
		ServiceUtils.pageCompute(info);
		response.setUserslist(result);
		long uid = info.getUid();
		if (info.getTargetuid() > 0) {
			uid = info.getTargetuid();
		}
		List<Long> uidList = socialDao.getUserFollowedUids(uid);
		if (uidList.size() > 0) {
			info.setUidList(uidList);
			List<User> userList = socialDao.getUserFollowedUsers(info);
			List<UserEntry> userEntryList = getUserLastSongList(userList);
			result.setUser(userEntryList);

			if (info.getAllcountflag() == 1) {
				int allcount = socialDao.getUserFollowedUsersAllcount(info);
				result.setAllcount(allcount);
			}
		}

		return response;
	}

	public FollowResponse followartist(FollowArtistInfo info) {
		if (info.getArtistid()<=0) {
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		FollowResponse followResponse = new FollowResponse();
		if (info.getType() == Constants.USER_FOLLOW) {
			if (!socialDao.checkFollowArtist(info)) {
				Artist artist = metadataDao.getArtistInfoByArtistIdOrName(
						info.getArtistid(), null);
				if (artist == null) {
					return followResponse;
				}
				info.setArtistuid(artist.getUid());

				socialDao.addFollowArtist(info);
			}
		} else if (info.getType() == Constants.USER_FOLLOW_CANCEL) {
			socialDao.deleteFollowArtist(info);
		}
		return followResponse;
	}

	public FollowResponse followuser(FollowUserInfo info) {
		if (info.getFollowuid()<=0) {
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		FollowResponse followResponse = new FollowResponse();
		if (info.getUid() != info.getFollowuid()) {
			if (info.getType() == Constants.USER_FOLLOW) {
				if (!socialDao.checkFollowUser(info)) {
					User user = socialDao.getUserById(info.getFollowuid());
					if (user == null) {
						return followResponse;
					}
					// 关注一个好友，则你的活跃度增加，他的人气值增加
					int fansCount = socialDao.getUserFansCount(info
							.getFollowuid());
					fansCount = fansCount + 1;
					info.setFollowuid(user.getUid());
					socialDao.addFollowUser(info);
					socialDao.addUserFollowCount(info.getUid());
					socialDao.updateUserFansCount(info.getFollowuid(),
							fansCount);
					int followType = ActivityEnum.followUser.getType();
					int followScore = ActivityEnum.followUser.getValue();
					this.addUserActivity(info.getUid(), followScore);
					this.addUserActivityLog(info.getUid(), followScore,
							followType);
					int popularityType = PopularityEnum.addFans.getType();
					int popularityScore = PopularityEnum.addFans.getValue();
					this.addUserPopularity(info.getFollowuid(), popularityScore);
					this.addUserPopularityLog(info.getFollowuid(),
							popularityScore, popularityType);
				}
			} else if (info.getType() == Constants.USER_FOLLOW_CANCEL) {
				if (socialDao.checkFollowUser(info)) {
					int fansCount = socialDao.getUserFansCount(info
							.getFollowuid());
					fansCount = fansCount - 1;
					if (fansCount >= 0) {
						socialDao.updateUserFansCount(info.getFollowuid(),
								fansCount);
						socialDao.deleteFollowUser(info);
						socialDao.delUserFollowCount(info.getUid());
					}
				}
			}
		}
		return followResponse;
	}

	public UserResponse updateuserinfo(UpdateUserInfo info) {
		UserResponse response = new UserResponse();
		User user = socialDao.getUserById(info.getUid());
		if (user != null) {
			socialDao.updateUserInfo(info);
		}
		return response;
	}

	public UserResponse updateuserheadicon(UpdateUserHeadIconInfo info) {
		UserResponse response = new UserResponse();
		if (info.getHeadicon() != null && info.getHeadicon().length > 0) {
			File imageFile[] = info.getHeadicon();
			for (int i = 0; i < imageFile.length; i++) {

			}
		}
		return response;
	}

	public ItemListResponse getsnsitemsofartist(SnsItemsOfArtistInfo info) {
		ItemListResponse itemListResponse = new ItemListResponse();
		Artist artist = metadataDao.getArtistInfoByArtistIdOrName(
				info.getArtistid(), null);
		if (artist.getUid() > 0) {
			AttentionUsersInfo attentionUsersInfo = new AttentionUsersInfo();
			List<Long> artistUids = new ArrayList<Long>();
			artistUids.add(artist.getUid());
			attentionUsersInfo.setUidList(artistUids);
			List<ItemEntry> itemList = getItemListbyUids(attentionUsersInfo);
			itemListResponse.setItemslist(new ItemListEntry(itemList));
		}
		return itemListResponse;
	}

	public ArtistFollowStatusListEntry getArtistFollowStatusList(
			List<Long> artistIdList, long uid) {
		ArtistFollowStatusListEntry statusEntry = new ArtistFollowStatusListEntry();
		if (artistIdList.isEmpty()) {
			return statusEntry;
		}
		ArtistFollowStatusInfo info = new ArtistFollowStatusInfo();
		info.setArtistIdList(artistIdList);
		info.setUid(uid);
		List<Long> artistFollowedList = socialDao.getUserFollowArtistList(info);
		List<ArtistFollowStatusEntry> artistFollowStatusEntryList = new ArrayList<ArtistFollowStatusEntry>();
		for (int i = 0; i < artistIdList.size(); i++) {
			long artistId = artistIdList.get(i);
			ArtistFollowStatusEntry artistFollowEntry = new ArtistFollowStatusEntry();
			artistFollowEntry.setArtistid(artistId);
			if (artistFollowedList.contains(artistId)) {
				artistFollowEntry.setFollowed(1);
			} else {
				artistFollowEntry.setFollowed(0);
			}
			artistFollowStatusEntryList.add(artistFollowEntry);
		}
		statusEntry.setArtist(artistFollowStatusEntryList);
		return statusEntry;
	}

	public UserListResponse listusers(ListUserInfo info) {
		UserListResponse response = new UserListResponse();
		UserListEntry result = new UserListEntry();
		response.setUserslist(result);
		ServiceUtils.pageCompute(info);
		List<Long> uidList = socialDao.getTopActivityUids(info);
		info.setUidList(uidList);
		List<User> userList = socialDao.getListUserList(info);
		userList = ServiceUtils.sortUserRandomList(uidList, userList);
		List<UserEntry> userEntryList = getUserLastSongList(userList);
		result.setUser(userEntryList);
		if (info.getAllcountflag() == 1) {
			int allcount = socialDao.getTopActivityUidsAllCount();
			result.setAllcount(allcount);
		}
		return response;
	}

	public New_ArtistListResponse getuserfollowedartists(
			UserFollowedArtistInfo info) {
		New_ArtistListResponse response = new New_ArtistListResponse();
		New_ArtistListEntry result = new New_ArtistListEntry();
		response.setArtistslist(result);
		ServiceUtils.pageCompute(info);
		long uid = info.getUid();
		if (info.getTargetuid() > 0) {
			uid = info.getTargetuid();
		}
		List<Long> artistIds = socialDao.getUserFollowedArtistIds(uid);
		List<Artist> artistList = metadataDao.getArtistListByIds(artistIds);
		List<New_ArtistEntry> artistListEntry = new ArrayList<New_ArtistEntry>();
		for (int i = 0; i < artistList.size(); i++) {
			New_ArtistEntry entry = new New_ArtistEntry(artistList.get(i));
			artistListEntry.add(i, entry);
		}
		result.setArtist(artistListEntry);
		return response;
	}

	public void getArtistPicsBySongList(List<UserSong> userSongList,
			int artistartsize) {
		if (userSongList.isEmpty())
			return;
		List<Long> artistIdList = new ArrayList<Long>();
		for (UserSong song : userSongList) {
			artistIdList.add(song.getArtistId());
		}
		List<ArtistPic> artistPicList = metadataDao
				.getArtistPicsByArtistList(artistIdList);
		if (artistPicList.size() > 0) {
			for (UserSong song : userSongList) {
				for (ArtistPic artistPic : artistPicList) {
					if (song.getArtistId() == artistPic.getArtistId()) {
						String pictrue = "";
						if (artistartsize != 120) {
							if (!Validator.isBlank(artistPic.getMiddlePic())) {
								pictrue = artistPic.getMiddlePic();
							} else {
								pictrue = artistPic.getPicUri();
							}
						} else {
							if (!Validator.isBlank(artistPic.getSmallPic())) {
								pictrue = artistPic.getSmallPic();
							} else {
								pictrue = artistPic.getPicUri();
							}
						}
						song.setArtistArt(pictrue);
					}
				}
			}
		}
	}

	public New_UserSongListResponse syncplaylist(SyncPlaylistInfo info) {
		New_UserSongListResponse response = new New_UserSongListResponse();
		New_UserSongListEntry result = new New_UserSongListEntry();
		response.setSongslist(result);
		File[] dataFiles = info.getFile();
		File[] imageFiles = info.getCover();
		if (dataFiles != null && dataFiles.length > 0) {
			File dataFile=dataFiles[0];
			File imageFile=null;
			if (imageFiles != null && imageFiles.length > 0) {
				imageFile=imageFiles[0];
			}
			syncUserPlaylist(dataFile,imageFile,info, result);
		}
		return response;
	}

	public void syncUserPlaylist(File dataFile,File imageFile,SyncPlaylistInfo info,
			New_UserSongListEntry result) {
		List<New_UserSongEntry> userSongListEntry = new ArrayList<New_UserSongEntry>();
		result.setSong(userSongListEntry);
		List<UserSong> finalUserSongList = new ArrayList<UserSong>();
		long playlistId = 0l;
		String playlistTitle=null;
		long uid = info.getUid();
		String imagePath=null;
		UserPhoto userPhoto=null;
		//首先判断文件是否正确，不正确直接中断请求
		if (imageFile != null) {
			if(AnalysisZipFileUtil.checkFile(imageFile, null)){
				String imgUri = imageFile.getAbsolutePath();
				try {
					userPhoto= AnalysisZipFileUtil
								.downloadUserPlaylistImage(imgUri);
				} catch (Exception e) {
					if (log.isDebugEnabled())
						log.debug(e.getMessage(), e);
					else
						log.error(e.getMessage());
					throw new MusicRcException(MusicRcException.FILE_UPLOAD_FAILURE);
				}
			}else{
				throw new MusicRcException(MusicRcException.FILE_UPLOAD_FAILURE);
			}
		}
		String page = AnalysisZipFileUtil.fileUploadUtil(dataFile);
		if (Validator.isBlank(page))return;
		try {
			UserMetadata userMetadata = AnalysisZipFileUtil
					.analysisSyncPlaylist(uid, page);
			if (userMetadata != null && userMetadata.getPlaylistid() > 0) {
				UserPlaylist userPlaylist = socialDao
						.getUserPlaylistByIdAndUid(
								userMetadata.getPlaylistid(), uid);
				if (userPlaylist != null) {
					playlistId = userPlaylist.getPlaylistId();
					playlistTitle=userPlaylist.getTitle();
				}
			}
			if (playlistId == 0) {
				UserPlaylist userPlaylist = null;
				if (userMetadata.getDataFrom() == Constants.USER_PLAYLIST_DATA_FROM_SYSTEM
						|| userMetadata.getDataFrom() == Constants.USER_PLAYLIST_DATA_FROM_UNCATEGORIED) {
					userPlaylist = socialDao.getUserPlaylistByUidAndDataFrom(
							userMetadata.getDataFrom(), uid);
				} else {
					userPlaylist = socialDao.getUserPlaylistByTitleAndUid(
							userMetadata.getPlaylistTitle(), uid);
				}
				if (userPlaylist == null) {
					playlistId = socialDao.saveUserPlaylist(userMetadata);
					playlistTitle=userMetadata.getPlaylistTitle();
				} else {
					playlistId = userPlaylist.getPlaylistId();
					playlistTitle=userPlaylist.getTitle();
				}
			}
			GetSongsOfPlaylistInfo getSongsOfPlaylistInfo=new GetSongsOfPlaylistInfo();
			getSongsOfPlaylistInfo.setPlaylistid(playlistId);
			getSongsOfPlaylistInfo.setPageFlag(1);
			List<UserSong> userSongList = socialDao
					.getUserSongListByPlaylistId(getSongsOfPlaylistInfo);

			if (userMetadata.getClientUserSongList() != null
					&& userMetadata.getClientUserSongList().size() > 0) {
				List<UserSong> metaList = userMetadata.getClientUserSongList();
				metaList = ServiceUtils.removeIterativeUserSongList(metaList);
				addMetadataInfo(metaList);
				metaList = ServiceUtils.removeIterativeUserSongList(metaList);
				if (metaList.size() > 0) {
					for (UserSong us : metaList) {
						us.setUid(uid);
						us.setPlaylistId(playlistId);
						if (us.isClientNoSongId()) {
							finalUserSongList.add(us);
						}
					}
				}
				if (userSongList.size() == 0) {
					int addSongCount = 0;
					if (metaList.size() > 0) {
						// 直接插入
						List<Long> songIdList = new ArrayList<Long>();
						for (UserSong mt : metaList) {
							if (mt.isClientHas()) {
								songIdList.add(mt.getSongId());
							}
						}
						addUserSongInfo(metaList, songIdList);
						for (UserSong mt : metaList) {
							if ((mt.getSongId() > 0)
									&& Validator.isBlank(mt.getSongTitle()))
								continue;
							addSongCount++;
							socialDao.saveUserSongList(mt);
							mt.setSongsyncstatus(Constants.CLIENT_DIFF_SERVER);
							if (!mt.isClientNoSongId()) {
								finalUserSongList.add(mt);
							}
						}
					}
					result.setAddedsongs(addSongCount);
				} else {
					// 首先比较songpath
					List<UserSong> sampSongPathList = compareSongPathOfUserSong(
							metaList, userSongList);
					// 修改server端songid为客户端songid
					socialDao.updateUserSongAfterCompare(sampSongPathList);
					// 进行比较
					List<UserSong> sameUserSongList = ServiceUtils
							.getSameSongsOfPlaylist(metaList, userSongList);
					List<UserSong> sameMetadataList = ServiceUtils
							.getSameMetadatasOfPlaylist(metaList, userSongList);

					// 相同的结果，修改顺序,顺序不维护
					// if(sameUserSongList.size()>0){
					// for(UserSong userSong:sameUserSongList){
					// //userSong.setSongsyncstatus(Constants.CLIENT_SAME_2_SERVER);
					// socialDao.updateUserSongSortIndex(userSong);
					// //finalUserSongList.add(userSong);
					// }
					// }
					// 1.待插入
					int addSongCount = 0;
					List<UserSong> addMetadataList = ServiceUtils
							.getDifferFromServer(sameMetadataList, metaList);
					if (addMetadataList.size() > 0) {
						List<Long> songIdList = new ArrayList<Long>();
						for (UserSong mt : addMetadataList) {
							if (mt.isClientHas()) {
								songIdList.add(mt.getSongId());
							}
						}
						addUserSongInfo(addMetadataList, songIdList);
						for (UserSong mt : addMetadataList) {
							if ((mt.getSongId() > 0)
									&& Validator.isBlank(mt.getSongTitle()))
								continue;
							socialDao.saveUserSongList(mt);
							addSongCount++;
							mt.setSongsyncstatus(Constants.CLIENT_DIFF_SERVER);
							if (mt.getSongId() > 0 && (!mt.isClientNoSongId())) {
								finalUserSongList.add(mt);
							}
						}
					}
					result.setAddedsongs(addSongCount);
					// 2.Server比客户端多余的部分
					List<UserSong> delUserSongList = ServiceUtils
							.getDiffFromClient(userSongList, sameUserSongList);
					if (delUserSongList.size() > 0) {
						for (UserSong us : delUserSongList) {
							us.setSongsyncstatus(Constants.SERVER_DIFF_CLIENT);
							if (us.getSongId() > 0) {
								finalUserSongList.add(us);
							}
						}
					}
				}
			} else {
				result.setAddedsongs(0);
				for (UserSong us : userSongList) {
					us.setSongsyncstatus(Constants.SERVER_DIFF_CLIENT);
					if (us.getSongId() > 0) {
						finalUserSongList.add(us);
					}
				}
			}

		} catch (Exception e) {
			if (e instanceof MusicRcException) {
				throw (MusicRcException) e;
			}
			if (log.isDebugEnabled())
				log.debug(e.getMessage(), e);
			else
				log.error(e.getMessage());
		}
		if(userPhoto!=null){
			imagePath=userPhoto.getSmallPhoto();
			userPhoto.setUid(uid);
			userPhoto.setPhotoType(3);//3 mean the image of userplaylist
			socialDao.saveUserPhoto(userPhoto);
		}
		//修改updatetime和图片
		socialDao.updateUserPlaylistUpdatetimeAndImage(playlistId,imagePath);
		//确定是否分享
		//用户选择是否分享，分享的是歌曲
		//TODO
		if(info.getShare()==1){
			ShareInfo shareInfo=new ShareInfo();
			shareInfo.setUid(info.getUid());
			shareInfo.setId(playlistId);
			shareInfo.setDataFrom(Constants.SHARE_DATAFROM_SHARE);
			shareInfo.setShareType(Constants.SHARE_TYPE_SHARE);
			shareInfo.setItemtype(Constants.ITEM_TYPE_SONG_PLAYLIST);
			shareInfo.setTitle("分享了播放列表\"" + playlistTitle + "\"");
			this.share(shareInfo);
		}
		result.setPlaylistid(playlistId);
		updateUserNativePlaylistCount(info.getUid());
		operateUserSongCount(playlistId);
		if (finalUserSongList.size() > 0) {
			finalUserSongList = ServiceUtils
					.removeIterativeUserSongList(finalUserSongList);
			this.getArtistPicsBySongList(finalUserSongList, 120);
			for (int i = 0; i < finalUserSongList.size(); i++) {
				UserSong userSong = finalUserSongList.get(i);
				if (!userSong.isClientHas()) {
					New_UserSongEntry userSongEntry = new New_UserSongEntry(
							userSong);
					userSongListEntry.add(userSongListEntry.size(),
							userSongEntry);
				}
			}
		}
		int addSongsCount = result.getAddedsongs();
		if (addSongsCount > 0) {
			int score = ActivityEnum.addSong.getValue();
			int type = ActivityEnum.addSong.getType();
			int totalScore = addSongsCount * score;
			this.addUserActivity(uid, totalScore);
			this.addUserActivityLog(uid, totalScore, type);
		}
	}

	public List<UserSong> compareSongPathOfUserSong(
			List<UserSong> clientUserSongList, List<UserSong> serverUserSongList) {
		List<UserSong> samePathList = new ArrayList<UserSong>();
		List<Long> songIdList = new ArrayList<Long>();
		for (int i = 0; i < clientUserSongList.size(); i++) {
			UserSong clientUs = clientUserSongList.get(i);
			for (int j = 0; j < serverUserSongList.size(); j++) {
				UserSong serverUs = serverUserSongList.get(j);
				if (!Validator.isBlank(serverUs.getSongPath())
						&& serverUs.getSongPath()
								.equals(clientUs.getSongPath())) {
					if (serverUs.getSongId() != clientUs.getSongId()) {
						clientUserSongList.remove(clientUs);
						serverUserSongList.remove(serverUs);
						samePathList.add(clientUs);
						songIdList.add(clientUs.getSongId());
					}
				}
			}
		}
		addUserSongInfo(samePathList, songIdList);
		return samePathList;
	}

	public void matchUserSongInfo(List<UserSong> clientUserSongList, long uid,
			long playlistId) {
		List<Long> songIds = new ArrayList<Long>();
		List<Long> songIdsDownload = new ArrayList<Long>();
		for (UserSong clientUs : clientUserSongList) {
			clientUs.setUid(uid);
			clientUs.setPlaylistId(playlistId);
			if (clientUs.getSongId() > 0) {
				clientUs.setClientHas(true);
				songIdsDownload.add(clientUs.getSongId());
			} else {
				GetSearchInfo getSearchInfo = new GetSearchInfo();
				getSearchInfo.setStart(0);
				getSearchInfo.setPagecount(2);
				getSearchInfo.setArtist(clientUs.getArtistName());
				getSearchInfo.setAlbum(clientUs.getAlbumTitle());
				getSearchInfo.setSong(clientUs.getSongTitle());
				getSearchInfo.setFilename(clientUs.getFileName());
				List<Long> songIdList = mmsHelper
						.getSearchSongIdListWithStatus(getSearchInfo);
				if (songIdList.size() > 0) {
					long songId = songIdList.get(0);
					clientUs.setSongId(songId);
					clientUs.setSearchMatched(true);
					songIds.add(songId);
					songIdsDownload.add(songId);
				}
			}
		}
		// ----------------songList------下载
		/*---------------检查歌曲下载文件是否存在------*/
		metadataDao.downloadSongFileOfAlbumBySongId(songIdsDownload);
		/*----------------------------------------*/
	}

	public void addMetadataInfo(List<UserSong> metaList) {
		List<Long> songIds = new ArrayList<Long>();
		List<Long> songIdsDownload = new ArrayList<Long>();
		for (UserSong metaData : metaList) {
			if (metaData.getSongId() > 0) {
				metaData.setClientHas(true);
				songIdsDownload.add(metaData.getSongId());
			} else {
				GetSearchInfo getSearchInfo = new GetSearchInfo();
				getSearchInfo.setStart(0);
				getSearchInfo.setPagecount(5);
				getSearchInfo.setArtist(metaData.getArtistName());
				getSearchInfo.setAlbum(metaData.getAlbumTitle());
				getSearchInfo.setSong(metaData.getSongTitle());
				getSearchInfo.setFilename(metaData.getFileName());
				List<Long> songIdList = mmsHelper
						.getSearchSongIdListWithStatus(getSearchInfo);
				if (songIdList.size() > 0) {
					long songId = songIdList.get(0);
					metaData.setSongId(songId);
					metaData.setClientNoSongId(true);
					songIds.add(songId);
					songIdsDownload.add(songId);
				}
			}
		}
		addUserSongInfo(metaList, songIds);
		// for(UserSong metaData:metaList){
		// if(metaData.getSongId()==0){
		// String artistName=metaData.getArtistName();
		// if(!Validator.isBlank(artistName)){
		// Artist artist=metadataDao.getArtistInfoByArtistIdOrNameWithStatus(0,
		// artistName);
		// if(artist!=null){
		// metaData.setArtistId(artist.getArtistId());
		// metaData.setArtistName(artist.getArtistName());
		// }
		// }
		// String albumTitle=metaData.getAlbumTitle();
		// if(!Validator.isBlank(albumTitle)){
		// GetAlbumInfo getAlbumInfo=new GetAlbumInfo();
		// getAlbumInfo.setAlbum(albumTitle);
		// getAlbumInfo.setArtist(artistName);
		// getAlbumInfo.setAlbumartflag(1);
		// Album album=
		// metadataDao.getAlbumInfoByArtistAndAlbumTitleWithStatus(getAlbumInfo);
		// if(album!=null){
		// metaData.setAlbumId(album.getAlbumId());
		// metaData.setAlbumTitle(album.getAlbumTitle());
		// metaData.setSongThumb(album.getAlbumThumbUri());
		// }
		// }
		// }
		// }
		// ----------------songList------下载
		/*---------------检查歌曲下载文件是否存在------*/
		metadataDao.downloadSongFileOfAlbumBySongId(songIdsDownload);
		/*----------------------------------------*/
	}

	public void addUserSongInfo(List<UserSong> metaList, List<Long> songIdList) {
		if (songIdList.isEmpty())
			return;
		List<Song> songList = metadataDao
				.getSongListByIdsWithStatus(songIdList);
		for (UserSong metaData : metaList) {
			for (int i = 0; i < songList.size(); i++) {
				Song song = songList.get(i);
				if (song.getSongId() == metaData.getSongId()) {
					metaData.setArtistId(song.getArtistId());
					metaData.setArtistName(song.getArtistName());

					metaData.setAlbumId(song.getAlbumId());
					metaData.setAlbumTitle(song.getAlbumTitle());

					metaData.setSongId(song.getSongId());
					metaData.setSongTitle(song.getSongTitle());

					if (metaData.getDuration() == 0) {
						metaData.setDuration(song.getSongDuration());
					}
					metaData.setSongThumb(song.getSongThumbUri());
					metaData.setHasMv(song.getHasMv());
				}
			}
		}
	}

	public New_UserSongListResponse getsongsofplaylist(
			GetSongsOfPlaylistInfo info) {
		New_UserSongListResponse response = new New_UserSongListResponse();
		New_UserSongListEntry result = new New_UserSongListEntry();
		ServiceUtils.specialPageCompute(info);
		response.setSongslist(result);
		long uid = info.getUid();
		if (info.getTargetuid() > 0) {
			uid = info.getTargetuid();
			User user = socialDao.getUserById(uid);
			if (user != null) {
				int score = PopularityEnum.beWatched.getValue();
				int type = PopularityEnum.beWatched.getType();
				this.addUserPopularity(info.getTargetuid(), score);
				this.addUserPopularityLog(info.getTargetuid(), score, type);
			}
		}
		long playlistId = info.getPlaylistid();
		if (playlistId == 0) {
			playlistId = socialDao.getDefaultUserPlaylistidByUid(uid,
					Constants.USER_PLAYLIST_DATA_FROM_SYSTEM);
		}
		UserPlaylist userPlayList = socialDao
				.getUserPlaylistListById(playlistId);
		if (userPlayList != null) {
			User user = socialDao.getUserById(userPlayList.getUid());
			if (user != null) {
				New_UserEntry userEntry = new New_UserEntry(user);
				result.setUser(userEntry);
			}
			info.setPlaylistid(userPlayList.getPlaylistId());
			List<UserSong> userSongList = socialDao
					.getUserSongListByPlaylistId(info);
			List<New_UserSongEntry> songEntryList = new ArrayList<New_UserSongEntry>();
			if (userSongList.size() > 0) {
				for (int i = 0; i < userSongList.size(); i++) {
					New_UserSongEntry entry = new New_UserSongEntry(
							userSongList.get(i));
					songEntryList.add(i, entry);
				}
				result.setSong(songEntryList);
			}
			if (info.getAllcountflag() == 1) {
				result.setAllcount(socialDao
						.getUserSongListByPlaylistIdAllCount(info));
			}
			result.setBio(userPlayList.getDescription());
			result.setPlaylistid(userPlayList.getPlaylistId());
			result.setPlaylisttitle(userPlayList.getTitle());
			result.setPlaylistart(ServiceUtils.returnURL(userPlayList
					.getThumb()));
		}
		return response;
	}

	public New_UserSongListResponse getuserrecentlyplayed(
			GetSongsOfPlaylistInfo info) {
		New_UserSongListResponse response = new New_UserSongListResponse();
		New_UserSongListEntry result = new New_UserSongListEntry();
		ServiceUtils.specialPageCompute(info);
		response.setSongslist(result);
		long uid = info.getUid();
		if (info.getTargetuid() > 0) {
			uid = info.getTargetuid();
		}
		User user = socialDao.getUserById(uid);
		if (user != null) {
			New_UserEntry userEntry = new New_UserEntry(user);
			result.setUser(userEntry);
		}
		info.setSort(Constants.USER_SONG_PLAY_RECENT);
		List<UserSong> userSongList = socialDao
				.getUserMostAndRecentPlayedSongList(info);
		List<New_UserSongEntry> songEntryList = new ArrayList<New_UserSongEntry>();
		if (userSongList.size() > 0) {
			for (int i = 0; i < userSongList.size(); i++) {
				New_UserSongEntry entry = new New_UserSongEntry(
						userSongList.get(i));
				songEntryList.add(i, entry);
			}
			result.setSong(songEntryList);
		}
		if (info.getAllcountflag() == 1) {
			result.setAllcount(socialDao
					.getUserMostAndRecentPlayedSongListAllCount(info));
		}
		return response;
	}

	public New_UserSongListResponse getusermostplayed(
			GetSongsOfPlaylistInfo info) {
		New_UserSongListResponse response = new New_UserSongListResponse();
		New_UserSongListEntry result = new New_UserSongListEntry();
		ServiceUtils.specialPageCompute(info);
		response.setSongslist(result);
		long uid = info.getUid();
		if (info.getTargetuid() > 0) {
			uid = info.getTargetuid();
		}
		User user = socialDao.getUserById(uid);
		if (user != null) {
			New_UserEntry userEntry = new New_UserEntry(user);
			result.setUser(userEntry);
		}
		info.setSort(Constants.USER_SONG_PLAY_MOST);
		List<UserSong> userSongList = socialDao
				.getUserMostAndRecentPlayedSongList(info);
		List<New_UserSongEntry> songEntryList = new ArrayList<New_UserSongEntry>();
		if (userSongList.size() > 0) {
			for (int i = 0; i < userSongList.size(); i++) {
				New_UserSongEntry entry = new New_UserSongEntry(
						userSongList.get(i));
				songEntryList.add(i, entry);
			}
			result.setSong(songEntryList);
		}
		if (info.getAllcountflag() == 1) {
			result.setAllcount(socialDao
					.getUserMostAndRecentPlayedSongListAllCount(info));
		}
		return response;
	}

	public New_UserSongListResponse getuserlikesongs(GetSongsOfPlaylistInfo info) {
		New_UserSongListResponse response = new New_UserSongListResponse();
		New_UserSongListEntry result = new New_UserSongListEntry();
		ServiceUtils.specialPageCompute(info);
		response.setSongslist(result);
		long uid = info.getUid();
		if (info.getTargetuid() > 0) {
			uid = info.getTargetuid();
		}
		User user = socialDao.getUserById(uid);
		if (user != null) {
			New_UserEntry userEntry = new New_UserEntry(user);
			result.setUser(userEntry);
		}
		info.setDataFrom(Constants.USER_SONG_DATA_FROM_FAVOURTE);
		List<UserSong> userSongList = socialDao
				.getUserSongListByDataFrom(info);
		List<New_UserSongEntry> songEntryList = new ArrayList<New_UserSongEntry>();
		if (userSongList.size() > 0) {
			for (int i = 0; i < userSongList.size(); i++) {
				New_UserSongEntry entry = new New_UserSongEntry(
						userSongList.get(i));
				songEntryList.add(i, entry);
			}
			result.setSong(songEntryList);
		}
		if (info.getAllcountflag() == 1) {
			result.setAllcount(socialDao
					.getUserSongListByDataFromAllCount(info));
		}
		return response;
	}

	public New_UserMvListResponse getmvsofplaylist(GetMvsOfPlaylistInfo info) {
		if (info.getPlaylistid() <= 0) {
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		New_UserMvListResponse response = new New_UserMvListResponse();
		New_UserMvListEntry result = new New_UserMvListEntry();
		response.setMvlist(result);
		ServiceUtils.specialPageCompute(info);
		long userPlayListId = info.getPlaylistid();
		UserPlaylist userPlayList = socialDao
				.getUserPlaylistListById(userPlayListId);
		if (userPlayList != null) {
			User user = socialDao.getUserById(userPlayList.getUid());
			if (user != null) {
				New_UserEntry userEntry = new New_UserEntry(user);
				result.setUser(userEntry);
			}
			info.setPlaylistid(userPlayList.getPlaylistId());
			List<UserMv> userMvList = socialDao.getUserMvList(info);
			if (userMvList.size() > 0) {
				List<New_UserMvEntry> mvEntryList = new ArrayList<New_UserMvEntry>();
				for (int i = 0; i < userMvList.size(); i++) {
					New_UserMvEntry entry = new New_UserMvEntry(
							userMvList.get(i));
					mvEntryList.add(i, entry);
				}
				result.setMv(mvEntryList);
			}
			if (info.getAllcountflag() == 1) {
				result.setAllcount(socialDao.getUserMvListAllCount(info));
			}
			result.setBio(userPlayList.getDescription());
			result.setPlaylistid(userPlayList.getPlaylistId());
			result.setPlaylisttitle(userPlayList.getTitle());
			result.setPlaylistart(ServiceUtils.returnURL(userPlayList
					.getThumb()));
		}
		return response;
	}

	public New_AlbumResponse discoverysongs(MyAttentionInfo info) {
		New_AlbumResponse response = new New_AlbumResponse();
		New_AlbumListEntry result = new New_AlbumListEntry();
		response.setAlbumslist(result);
		ServiceUtils.pageCompute(info);
		List<New_AlbumEntry> albumEntryList = new ArrayList<New_AlbumEntry>();
		result.setAlbum(albumEntryList);
		info.setPassDate(ServiceUtils.getPastDate(Constants.PAST_DATE));
		List<AlbumUdata> albumUdataList = socialDao
				.getDiscoveryAlbumUdataList(info);
		if (albumUdataList.size() > 0) {
			if (info.getAllcountflag() == 1) {
				result.setAllcount(socialDao
						.getDiscoveryAlbumUdataListAllCount(info));
			}
			List<Long> albumIdList = new ArrayList<Long>();
			for (int j = 0; j < albumUdataList.size(); j++) {
				AlbumUdata albumUdata = albumUdataList.get(j);
				albumIdList.add(albumUdata.getAlbumId());
			}
			info.setAlbumIdList(albumIdList);
			List<Album> albumListResult = metadataDao
					.getAlbumListInfoByIdsWithStatus(albumIdList);
			List<Song> songList = metadataDao
					.getSongInfoByAlbumIdList(albumIdList);
			List<Album> albumList = ServiceUtils.sortAlbumList(albumListResult,
					albumUdataList);
			for (int i = 0; i < albumList.size(); i++) {
				Album album = albumList.get(i);
				for (int j = 0; j < albumUdataList.size(); j++) {
					AlbumUdata albumUdata = albumUdataList.get(j);
					if (album.getAlbumId() == albumUdata.getAlbumId()) {
						album.setLastOpType(albumUdata.getLastOpType());
						break;
					}
				}
				album.setAlbumBio(null);
				New_AlbumEntry albumEntry = new New_AlbumEntry(album);
				List<Song> albumSongList = new ArrayList<Song>();
				if (songList.size() > 0) {
					for (int j = 0; j < songList.size(); j++) {
						Song song = songList.get(j);
						if (song.getAlbumId() == album.getAlbumId()) {
							albumSongList.add(song);
						}
					}
				}

				List<Song> newSongList = ServiceUtils.getSongListByPlayTimes(
						albumSongList, Constants.MY_ATTENTION_SONGCOUNT);
				if (newSongList.size() > 0) {
					New_SongListEntry songListEntry = new New_SongListEntry();
					List<New_SongEntry> entryList = new ArrayList<New_SongEntry>();
					for (int j = 0; j < newSongList.size(); j++) {
						Song song = newSongList.get(j);
						song.setAlbumId(0);
						song.setAlbumTitle(null);
						New_SongEntry songEntry = new New_SongEntry(song);
						entryList.add(j, songEntry);
					}
					songListEntry.setSong(entryList);
					albumEntry.setSongslist(songListEntry);
				}

				List<UserOpAlbum> userOpAlbumList = socialDao
						.getUserOpAlumInfo(album.getAlbumId(),
								Constants.MY_ATTENTION_USERCOUNT, -1);

				if (userOpAlbumList.size() > 0) {
					UserListEntry userListEntry = new UserListEntry();
					List<UserEntry> userEntryList = new ArrayList<UserEntry>();
					List<Long> uidList = new ArrayList<Long>();
					for (UserOpAlbum userOpAlbum : userOpAlbumList) {
						uidList.add(userOpAlbum.getUid());
					}
					List<User> userListResult = socialDao
							.getUserListByUids(uidList);
					List<User> userList = new ArrayList<User>();
					for (int j = 0; j < userOpAlbumList.size(); j++) {
						UserOpAlbum userOpAlbum = userOpAlbumList.get(j);
						for (int k = 0; k < userListResult.size(); k++) {
							User user = userListResult.get(k);
							if (user.getUid() == userOpAlbum.getUid()) {
								user.setLastTime(userOpAlbum.getLastTime());
								userList.add(user);
								UserEntry userEntry = new UserEntry(user);
								userEntryList.add(userEntryList.size(),
										userEntry);
							}
						}
					}
					userListEntry.setUser(userEntryList);
					albumEntry.setUserslist(userListEntry);
				}
				albumEntryList.add(albumEntryList.size(), albumEntry);
			}
			result.setAlbumscount(albumEntryList.size());
		}
		return response;
	}

	public New_AlbumResponse myattention(MyAttentionInfo info) {
		New_AlbumResponse response = new New_AlbumResponse();
		New_AlbumListEntry result = new New_AlbumListEntry();
		response.setAlbumslist(result);
		ServiceUtils.pageCompute(info);
		// 获取我关注的用户，获取关注用户的专辑，获取歌曲
		List<Long> uidList = socialDao.getUserFollowedUids(info.getUid());
		if (uidList.isEmpty())
			return response;
		info.setUidList(uidList);
		List<UserOpAlbum> userOpAlbumList = socialDao
				.getMyAttentionAlbumsByUidList(info);
		if (userOpAlbumList.isEmpty())
			return response;
		if (info.getAllcountflag() == 1) {
			result.setAllcount(socialDao
					.getMyAttentionAlbumsByUidListAllCount(info));
		}
		List<Long> userIdList = new ArrayList<Long>();
		List<Long> albumIdList = new ArrayList<Long>();
		List<Long> commentIdList = new ArrayList<Long>();
		List<Long> songIdList = new ArrayList<Long>();
		for (UserOpAlbum userOpAlbum : userOpAlbumList) {
			userIdList.add(userOpAlbum.getUid());
			albumIdList.add(userOpAlbum.getAlbumId());
			if (userOpAlbum.getType() == Constants.OP_TYPE_COMMENT
					&& userOpAlbum.getLastCommentId() > 0) {
				commentIdList.add(userOpAlbum.getLastCommentId());
			}
			if (userOpAlbum.getLastSongId() > 0) {
				songIdList.add(userOpAlbum.getLastSongId());
			}
		}
		songIdList = ServiceUtils.removeIterativeOnIdList(songIdList);
		List<Song> lastSongList = metadataDao.getSongListByIds(songIdList);
		info.setAlbumIdList(albumIdList);
		List<Album> albumList = metadataDao
				.getAlbumListInfoByIdsWithStatus(albumIdList);
		if (albumList.isEmpty())
			return response;
		List<UserCommentContent> userCommentContentList = socialDao
				.getUserCommentContentListByIds(commentIdList);
		List<User> userListResult = socialDao.getUserListByUids(userIdList);
		List<Song> songListResult = metadataDao
				.getSongInfoByAlbumIdList(albumIdList);
		List<New_AlbumEntry> albumEntryList = new ArrayList<New_AlbumEntry>();
		result.setAlbum(albumEntryList);
		for (int i = 0; i < userOpAlbumList.size(); i++) {
			UserOpAlbum userOpAlbum = userOpAlbumList.get(i);
			if (userOpAlbum.getLastSongId() > 0) {
				for (int j = 0; j < lastSongList.size(); j++) {
					if (userOpAlbum.getLastSongId() == lastSongList.get(j)
							.getSongId()) {
						String subTitle = ServiceUtils.formatSubTitle(
								lastSongList.get(j).getSongTitle(),
								userOpAlbum.getType());
						userOpAlbum.setSubTitle(subTitle);
					}
				}
			} else {
				String subTitle = ServiceUtils.formatSubTitle(null,
						userOpAlbum.getType());
				userOpAlbum.setSubTitle(subTitle);
			}

			New_AlbumEntry albumEntry = null;
			long uid = userOpAlbum.getUid();
			long albumId = userOpAlbum.getAlbumId();
			for (int j = 0; j < albumList.size(); j++) {
				Album sourceAlbum = albumList.get(j);
				if (userOpAlbum.getAlbumId() == sourceAlbum.getAlbumId()) {
					Album album = formatMyAttentionAlbum(sourceAlbum,
							userOpAlbum, userCommentContentList);
					albumEntry = new New_AlbumEntry(album);
					break;
				}
			}
			if (albumEntry == null)
				continue;
			if (songListResult.size() > 0) {
				List<Song> albumSongList = new ArrayList<Song>();
				for (int j = 0; j < songListResult.size(); j++) {
					Song song = songListResult.get(j);
					if (song.getAlbumId() == albumId) {
						albumSongList.add(song);
					}
				}
				List<Song> newSongList = ServiceUtils.getSongListByPlayTimes(
						albumSongList, Constants.MY_ATTENTION_SONGCOUNT);
				if (newSongList.size() > 0) {
					New_SongListEntry songListEntry = new New_SongListEntry();
					List<New_SongEntry> entryList = new ArrayList<New_SongEntry>();
					for (int j = 0; j < newSongList.size(); j++) {
						Song song = newSongList.get(j);
						song.setAlbumId(0);
						song.setAlbumTitle(null);
						New_SongEntry songEntry = new New_SongEntry(song);
						entryList.add(entryList.size(), songEntry);
					}
					songListEntry.setSong(entryList);
					albumEntry.setSongslist(songListEntry);
				}
			}

			if (userListResult.size() > 0) {
				List<User> albumUserList = new ArrayList<User>();
				for (int j = 0; j < userListResult.size(); j++) {
					User user = userListResult.get(j);
					if (user.getUid() == uid) {
						albumUserList.add(user);
					}
				}
				if (albumUserList.size() > 0) {
					UserListEntry userListEntry = new UserListEntry();
					List<UserEntry> userEntryList = new ArrayList<UserEntry>();
					for (int j = 0; j < albumUserList.size(); j++) {
						User albumUser = albumUserList.get(j);
						User newUser = formatMyAttentionUser(albumUser,
								userOpAlbum, userCommentContentList);
						UserEntry userEntry = new UserEntry(newUser);
						userEntryList.add(userEntry);
					}
					userListEntry.setUser(userEntryList);
					albumEntry.setUserslist(userListEntry);
				}
			}
			albumEntryList.add(albumEntry);
			result.setAlbumscount(albumEntryList.size());
		}
		return response;
	}

	public Album formatMyAttentionAlbum(Album sourceAlbum, UserOpAlbum uoa,
			List<UserCommentContent> userCommentContentList) {
		/**
		 * <title>我从草原来 新歌+精选</title> <albumid>10132449</albumid>
		 * <artist>凤凰传奇</artist> <artistid>1059681</artistid> <albumart>
		 * http://www
		 * .kascend.com:9090/music_pics/album/20110725/13/13115729110261712.
		 * jpg.120x120.jpg </albumart> <bio>歌手：凤凰传奇；语言：国语；发行时间：2010年9月30日</bio>
		 * <publishtime>2010-10-05</publishtime> <songscount>12</songscount>
		 * <lastplaytime>1341824879</lastplaytime> <lastoptype>0</lastoptype>
		 */
		Album album = new Album();
		album.setAlbumTitle(sourceAlbum.getAlbumTitle());
		album.setAlbumId(sourceAlbum.getAlbumId());
		album.setArtistName(sourceAlbum.getArtistName());
		album.setArtistId(sourceAlbum.getArtistId());
		album.setAlbumThumbUri(sourceAlbum.getAlbumThumbUri());
		album.setAlbumBio(sourceAlbum.getAlbumBio());
		album.setAlbumPublishTime(sourceAlbum.getAlbumPublishTime());
		album.setPublishedSongs(sourceAlbum.getPublishedSongs());
		album.setLastPlayTime(uoa.getLastTime());
		// album.setLastOpType(uoa.getType());
		album.setLastOp(uoa.getSubTitle());
		if (uoa.getType() == Constants.OP_TYPE_COMMENT) {
			for (int i = 0; i < userCommentContentList.size(); i++) {
				if (uoa.getLastCommentId() == userCommentContentList.get(i)
						.getCommentId()) {
					album.setLastComment(userCommentContentList.get(i)
							.getContent());
				}
			}
		}
		return album;
	}

	public User formatMyAttentionUser(User sourceUser, UserOpAlbum uoa,
			List<UserCommentContent> userCommentContentList) {
		/**
		 * <uid>200749468</uid> <username>jean</username>
		 * <nickname>jean</nickname> <followcount>0</followcount>
		 * <fanscount>0</fanscount> <popularity>0</popularity>
		 * <activity>463</activity> <sex>男</sex> <signature/>
		 * <updatetime>1341824879</updatetime>
		 * <lastplaytime>1341825040</lastplaytime> <lastop>评论</lastop>
		 * <lastcomment>相亲相爱是一首我最喜欢的歌曲3</lastcomment>
		 */
		User user = new User();
		user.setActivity(sourceUser.getActivity());
		user.setUid(sourceUser.getUid());
		user.setName(sourceUser.getName());
		user.setNickname(sourceUser.getNickname());
		user.setFollowCount(sourceUser.getFollowCount());
		user.setFansCount(sourceUser.getFansCount());
		user.setPopularity(sourceUser.getPopularity());
		user.setSex(sourceUser.getSex());
		user.setHeadIcon(sourceUser.getHeadIcon());
		user.setSignature(sourceUser.getSignature());
		user.setUpdateTime(sourceUser.getUpdateTime());
		user.setLastTime(uoa.getLastTime());
		user.setLastSongPlayTime(sourceUser.getLastSongPlayTime());
		return user;
	}

	// public New_AlbumResponse myattention(MyAttentionInfo info) {
	// New_AlbumResponse response=new New_AlbumResponse();
	// New_AlbumListEntry result=new New_AlbumListEntry();
	// response.setAlbumslist(result);
	// ServiceUtils.pageCompute(info);
	// //获取我关注的用户，获取关注用户的专辑，获取歌曲
	// List<Long> uidList=socialDao.getUserFollowedUids(info.getUid());
	// if(uidList.size()>0){
	// // info.setPassDate(ServiceUtils.getPastDate(Constants.PAST_DATE));
	// info.setUidList(uidList);
	// List<UserOpAlbum>
	// userOpAlbumList=socialDao.getMyAttentionAlbumsByUidList(info);
	// if(userOpAlbumList.size()>0){
	// if(info.getAllcountflag()==1){
	// result.setAllcount(socialDao.getMyAttentionAlbumsByUidListAllCount(info));
	// }
	// List<Long> userIdList=new ArrayList<Long>();
	// List<Long> albumIdList=new ArrayList<Long>();
	// List<Long> commentIdList=new ArrayList<Long>();
	// for(UserOpAlbum userOpAlbum:userOpAlbumList){
	// userIdList.add(userOpAlbum.getUid());
	// albumIdList.add(userOpAlbum.getAlbumId());
	// if(userOpAlbum.getType()==Constants.OP_TYPE_COMMENT&&userOpAlbum.getLastCommentId()>0){
	// commentIdList.add(userOpAlbum.getLastCommentId());
	// }
	// }
	// info.setAlbumIdList(albumIdList);
	// List<Album>
	// albumListResult=metadataDao.getAlbumListInfoByIdsWithStatus(albumIdList);
	// if(albumListResult.size()>0){
	// List<UserCommentContent>
	// userCommentContentList=socialDao.getUserCommentContentListByIds(commentIdList);
	// List<User> userListResult=socialDao.getUserListByUids(userIdList);
	// List<Song>
	// songListResult=metadataDao.getSongInfoByAlbumIdList(albumIdList);
	// List<New_AlbumEntry> albumEntryList=new ArrayList<New_AlbumEntry>();
	// for(int i=0;i<userOpAlbumList.size();i++){
	// long albumId=userOpAlbumList.get(i).getAlbumId();
	// long uid=userOpAlbumList.get(i).getUid();
	// New_AlbumEntry albumEntry=null;
	// for(int j=0;j<albumListResult.size();j++){
	// Album sourceAlbum=albumListResult.get(j);
	// if(userOpAlbumList.get(i).getAlbumId()==sourceAlbum.getAlbumId()){
	// Album album=this.formatMyAttentionAlbum(sourceAlbum,
	// userOpAlbumList.get(i));
	// album.setLastPlayTime(userOpAlbumList.get(i).getLastTime());
	// albumEntry=new New_AlbumEntry(album);
	// break;
	// }
	// }
	// if(albumEntry!=null){
	// if(songListResult.size()>0){
	// List<Song> albumSongList=new ArrayList<Song>();
	// for(int j=0;j<songListResult.size();j++){
	// Song song=songListResult.get(j);
	// if(song.getAlbumId()==albumId){
	// albumSongList.add(song);
	// }
	// }
	// List<Song>
	// newSongList=ServiceUtils.getSongListByPlayTimes(albumSongList,Constants.MY_ATTENTION_SONGCOUNT);
	// if(newSongList.size()>0){
	// New_SongListEntry songListEntry=new New_SongListEntry();
	// List<New_SongEntry> entryList=new ArrayList<New_SongEntry>();
	// for(int j=0;j<newSongList.size();j++){
	// Song song=newSongList.get(j);
	// song.setAlbumId(0);
	// song.setAlbumTitle(null);
	// New_SongEntry songEntry=new New_SongEntry(song);
	// entryList.add(entryList.size(), songEntry);
	// }
	// songListEntry.setSong(entryList);
	// albumEntry.setSongslist(songListEntry);
	// }
	// }
	//
	// if(userListResult.size()>0){
	// UserListEntry userListEntry=new UserListEntry();
	// List<UserEntry> userEntryList=new ArrayList<UserEntry>();
	// for(int j=0;j<userListResult.size();j++){
	// User user=userListResult.get(j);
	// if(user.getUid()==uid){
	// User lastUser=this.formatMyAttentionUser(user, userOpAlbumList.get(i),
	// userCommentContentList);
	// UserEntry userEntry=new UserEntry(lastUser);
	// userEntryList.add(userEntryList.size(),userEntry);
	// }
	// }
	// userListEntry.setUser(userEntryList);
	// albumEntry.setUserslist(userListEntry);
	// }
	// albumEntryList.add(albumEntryList.size(), albumEntry);
	// }
	// }
	// result.setAlbum(albumEntryList);
	// result.setAlbumscount(albumEntryList.size());
	// }
	// }
	// }
	// return response;
	// }

	public UserFollowStatusListEntry getUserFollowStatusList(
			List<Long> followUidList, long uid) {
		UserFollowStatusListEntry statusEntry = new UserFollowStatusListEntry();
		if (followUidList.isEmpty()) {
			return statusEntry;
		}
		UserFollowStatusInfo userFollowStatusInfo = new UserFollowStatusInfo();
		userFollowStatusInfo.setUid(uid);
		userFollowStatusInfo.setFollowUidList(followUidList);
		List<Long> userFollowUserList = socialDao
				.getUserFollowUserList(userFollowStatusInfo);
		List<UserFollowStatusEntry> userFollowStatusEntryList = new ArrayList<UserFollowStatusEntry>();
		for (int i = 0; i < followUidList.size(); i++) {
			long followUid = followUidList.get(i);
			UserFollowStatusEntry userFollowEntry = new UserFollowStatusEntry();
			userFollowEntry.setUid(followUid);
			if (userFollowUserList.contains(followUid)) {
				userFollowEntry.setFollowed(1);
			} else {
				userFollowEntry.setFollowed(0);
			}
			userFollowStatusEntryList.add(userFollowEntry);
		}
		statusEntry.setUser(userFollowStatusEntryList);
		return statusEntry;
	}

	public DiscoveryAlbumOfSongResponse discoveryalbumofsong(
			DiscoveryAlbumOfSongInfo info) {
		if (info.getAlbumid() <= 0) {
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		DiscoveryAlbumOfSongResponse response = new DiscoveryAlbumOfSongResponse();
		ServiceUtils.pageCompute(info);
		Album album = metadataDao.getAlbumInfoByIdNoCondition(info.getAlbumid());
		if (album != null) {
			DiscoveryAlbumOfSongEntry result = new DiscoveryAlbumOfSongEntry(
					album);
			response.setAlbum(result);
			long albumId = album.getAlbumId();
			List<Song> songList = getSongListByAlbumId(albumId,
					Constants.MAX_PAGE_COUNT);
			New_SongListEntry listenSongListEntry = new New_SongListEntry();
			List<New_SongEntry> listenEntryList = new ArrayList<New_SongEntry>();
			for (int j = 0; j < songList.size(); j++) {
				Song song = songList.get(j);
				song.setAlbumId(0);
				song.setAlbumTitle(null);
				New_SongEntry songEntry = new New_SongEntry(song);
				listenEntryList.add(j, songEntry);
			}
			listenSongListEntry.setSong(listenEntryList);
			result.setSongslist(listenSongListEntry);
			List<UserOpAlbum> listenedUserOpAlbumList = socialDao
					.getUserOpAlumInfo(albumId, Constants.SPECIAL_PAGE_COUNT,
							Constants.OP_TYPE_PLAY);
			List<Long> listenedUidList = new ArrayList<Long>();
			for (UserOpAlbum userOpAlbum : listenedUserOpAlbumList) {
				listenedUidList.add(userOpAlbum.getUid());
			}

			List<UserOpAlbum> likedUserOpAlbumList = socialDao
					.getUserOpAlumInfo(albumId, Constants.SPECIAL_PAGE_COUNT,
							Constants.OP_TYPE_LIKE);
			List<Long> likedUidList = new ArrayList<Long>();
			for (UserOpAlbum userOpAlbum : likedUserOpAlbumList) {
				likedUidList.add(userOpAlbum.getUid());
			}
			listenedUidList.addAll(likedUidList);
			listenedUidList = ServiceUtils
					.removeIterativeOnIdList(listenedUidList);
			List<User> userList = socialDao
					.getUserListByUidsWithStatus(listenedUidList);

			UserListEntry listenedUserListEntry = new UserListEntry();
			List<UserEntry> listenedEntryList = new ArrayList<UserEntry>();

			for (int j = 0; j < listenedUserOpAlbumList.size(); j++) {
				UserOpAlbum userOpAlbum = listenedUserOpAlbumList.get(j);
				for (int k = 0; k < userList.size(); k++) {
					User user = userList.get(k);
					if (userOpAlbum.getUid() == user.getUid()) {
						user.setLastTime(userOpAlbum.getLastTime());
						UserEntry userEntry = new UserEntry(user);
						listenedEntryList.add(listenedEntryList.size(),
								userEntry);
					}
				}
			}
			listenedUserListEntry.setUser(listenedEntryList);
			result.setListeneduserslist(listenedUserListEntry);

			UserListEntry likedUserListEntry = new UserListEntry();
			List<UserEntry> likedEntryList = new ArrayList<UserEntry>();
			for (int j = 0; j < likedUserOpAlbumList.size(); j++) {
				UserOpAlbum userOpAlbum = likedUserOpAlbumList.get(j);
				for (int k = 0; k < userList.size(); k++) {
					User user = userList.get(k);
					if (userOpAlbum.getUid() == user.getUid()) {
						user.setLastTime(userOpAlbum.getLastTime());
						UserEntry userEntry = new UserEntry(user);
						likedEntryList.add(likedEntryList.size(), userEntry);
					}
				}
			}
			likedUserListEntry.setUser(likedEntryList);
			result.setLikeduserslist(likedUserListEntry);
			result.setLikeduserscount(likedEntryList.size());
		}
		return response;
	}

	public List<Song> getSongListByAlbumId(long albumId, int pageCount) {
		GetSongsOfAlbumInfo getSongsOfAlbumInfo = new GetSongsOfAlbumInfo();
		// 每个专辑3条song，按热度排序
		getSongsOfAlbumInfo.setPagecount(pageCount);
		getSongsOfAlbumInfo.setSort(Constants.MY_ATTENTION_SORT);
		getSongsOfAlbumInfo.setAlbumid(albumId);
		return metadataDao.getSpecialSongInfoByAlbumId(getSongsOfAlbumInfo);
	}

	public New_AlbumInfoResponse getusersongsofalbum(GetUserSongsOfAlbum info) {
		if (info.getTargetuid() <= 0 || info.getAlbumid() <= 0) {
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		New_AlbumInfoResponse response = new New_AlbumInfoResponse();
		long uid = info.getUid();
		if (info.getTargetuid() > 0) {
			uid = info.getTargetuid();
		}
		long albumId = info.getAlbumid();
		Album album = metadataDao.getAlbumInfoByIdRealSongCount(albumId);
		if (album != null) {
			albumId = album.getAlbumId();
			New_AlbumEntry albumEntry = new New_AlbumEntry(album);
			List<Song> songList = getSongListByAlbumId(albumId,
					Constants.MAX_PAGE_COUNT);
			if (songList.size() > 0) {
				List<Long> songIdList = new ArrayList<Long>();
				for (Song song : songList) {
					songIdList.add(song.getSongId());
				}
				MyAttentionAlbumInfo myAttentionAlbumInfo = new MyAttentionAlbumInfo();
				myAttentionAlbumInfo.setSongIdList(songIdList);
				myAttentionAlbumInfo.setTargetuid(uid);
				List<Long> listenSongIdList = socialDao
						.getListenedSongInUserSongAndPlaylist(myAttentionAlbumInfo);
				List<Song> listenSongList = new ArrayList<Song>();
				List<Song> otherSongList = new ArrayList<Song>();
				// modified by yejz
				for (Song song : songList) {
					boolean listened = false;
					for (Long songId : listenSongIdList) {
						if (song.getSongId() == songId) {
							listened = true;
							break;
						}
					}
					if (listened) {
						listenSongList.add(song);
					} else {
						otherSongList.add(song);
					}
				}
				New_SongListEntry listenSongListEntry = new New_SongListEntry();
				List<New_SongEntry> listenEntryList = new ArrayList<New_SongEntry>();
				for (int j = 0; j < listenSongList.size(); j++) {
					Song song = listenSongList.get(j);
					song.setAlbumId(0);
					song.setAlbumTitle(null);
					New_SongEntry songEntry = new New_SongEntry(song);
					listenEntryList.add(j, songEntry);
				}
				listenSongListEntry.setSong(listenEntryList);
				albumEntry.setListenedsongslist(listenSongListEntry);
				New_SongListEntry otherSongListEntry = new New_SongListEntry();
				List<New_SongEntry> otherEntryList = new ArrayList<New_SongEntry>();
				for (int j = 0; j < otherSongList.size(); j++) {
					Song song = otherSongList.get(j);
					song.setAlbumId(0);
					song.setAlbumTitle(null);
					New_SongEntry songEntry = new New_SongEntry(song);
					otherEntryList.add(j, songEntry);
				}
				otherSongListEntry.setSong(otherEntryList);
				albumEntry.setOthersongslist(otherSongListEntry);
			}
			response.setAlbum(albumEntry);
		}
		return response;
	}

	public MyAttentionAlbumResponse myattentionalbum(MyAttentionAlbumInfo info) {
		MyAttentionAlbumResponse response = new MyAttentionAlbumResponse();
		ServiceUtils.pageCompute(info);
		if (info.getTargetuid() <= 0 || info.getAlbumid() <= 0) {
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		long albumId = info.getAlbumid();
		Album album = metadataDao.getAlbumInfoByIdRealSongCount(albumId);
		if (album != null) {
			albumId = album.getAlbumId();
			MyAttentionAlbumEntry result = new MyAttentionAlbumEntry(album);
			response.setAttentionalbum(result);
			UserOpAlbum userOpAlbum = socialDao
					.getFollowedAlbumOfUserByAlbumIdAndUid(albumId,
							info.getTargetuid());
			if (userOpAlbum != null) {
				User user = socialDao.getUserById(userOpAlbum.getUid());
				if (user != null) {
					user.setLastTime(userOpAlbum.getLastTime());
					UserEntry userEntry = new UserEntry(user);
					result.setUser(userEntry);
				}
			}
			List<Song> songList = getSongListByAlbumId(albumId,
					Constants.MAX_PAGE_COUNT);
			if (songList.size() > 0) {
				List<Long> songIdList = new ArrayList<Long>();
				for (Song song : songList) {
					songIdList.add(song.getSongId());
				}
				info.setSongIdList(songIdList);
				List<Long> listenSongIdList = socialDao
						.getListenedSongInUserSong(info);

				List<Song> listenSongList = new ArrayList<Song>();
				List<Song> otherSongList = new ArrayList<Song>();
				// modified by yejz
				for (Song song : songList) {
					boolean listened = false;
					for (Long songId : listenSongIdList) {
						if (song.getSongId() == songId) {
							listened = true;
							break;
						}
					}
					if (listened) {
						listenSongList.add(song);
					} else {
						otherSongList.add(song);
					}
				}

				New_SongListEntry listenSongListEntry = new New_SongListEntry();
				List<New_SongEntry> listenEntryList = new ArrayList<New_SongEntry>();
				for (int j = 0; j < listenSongList.size(); j++) {
					Song song = listenSongList.get(j);
					song.setAlbumId(0);
					song.setAlbumTitle(null);
					New_SongEntry songEntry = new New_SongEntry(song);
					listenEntryList.add(j, songEntry);
				}
				listenSongListEntry.setSong(listenEntryList);
				result.setListenedsongslist(listenSongListEntry);
				New_SongListEntry otherSongListEntry = new New_SongListEntry();
				List<New_SongEntry> otherEntryList = new ArrayList<New_SongEntry>();
				for (int j = 0; j < otherSongList.size(); j++) {
					Song song = otherSongList.get(j);
					song.setAlbumId(0);
					song.setAlbumTitle(null);
					New_SongEntry songEntry = new New_SongEntry(song);
					otherEntryList.add(j, songEntry);
				}
				otherSongListEntry.setSong(otherEntryList);
				result.setOthersongslist(otherSongListEntry);
			}
		}
		return response;
	}

	public UserListResponse getuserfans(GetUserFansInfo info) {
		UserListResponse response = new UserListResponse();
		UserListEntry result = new UserListEntry();
		response.setUserslist(result);
		ServiceUtils.pageCompute(info);
		long uid = info.getUid();
		if (info.getTargetuid() > 0) {
			uid = info.getTargetuid();
		}
		List<Long> userFansIds = socialDao.getUserFansIds(uid, info.getStart(),
				info.getPagecount());
		List<User> userList = socialDao
				.getUserListByUidsWithStatusAll(userFansIds);
		if (info.getAllcountflag() == 1) {
			result.setAllcount(socialDao.getUserFansIdsAllCount(uid));
		}
		List<UserEntry> userEntryList = getUserLastSongList(userList);
		result.setUser(userEntryList);
		return response;
	}

	protected List<UserEntry> getUserLastSongList(List<User> userList) {
		List<UserEntry> userEntryList = new ArrayList<UserEntry>();
		if (userList.isEmpty())
			return userEntryList;
		List<Long> songIdList = new ArrayList<Long>();
		for (User user : userList) {
			songIdList.add(user.getLastSongId());
		}
		List<Song> songList = metadataDao.getSongListByIds(songIdList);
		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
			UserEntry userEntry = new UserEntry(user);
			for (Song song : songList) {
				if (user.getLastSongId() == song.getSongId()) {
					song.setLastPlayTime(user.getLastSongPlayTime());
					LastSongEntry lastSongEntry = new LastSongEntry(song);
					userEntry.setLastsong(lastSongEntry);
				}
			}
			userEntryList.add(userEntryList.size(), userEntry);
		}
		return userEntryList;
	}

	public UserResponse gettalentinfo(GetTalentInfo info) {
		UserResponse response = new UserResponse();
		if (info.getTargetuid() == 0) {
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		User user = socialDao.getTalentUserById(info.getTargetuid());
		if (user != null) {
			UserEntry entry = new UserEntry(user);
			response.setUser(entry);
		}
		return response;
	}

	public UserSnsResponse snsgetuserrelationlist(
			SnsGetUserRelationListInfo info) {
		UserSnsResponse response = new UserSnsResponse();
		SnsUserListEntry result = new SnsUserListEntry();
		response.setSnsuserslist(result);
		SocialResponse socialResponse = null;
		try {
			E2eSocialService socialService = new E2eSocialService(
					Configer.getValueString(MusicConst.USER_SERVER_APPKEY),
					Configer.getValueString(MusicConst.USER_SERVER_SECRET));
			RequestParam paramSocial = new RequestParam();
			paramSocial.setToken(info.getToken());
			paramSocial.setAccessToken(info.getAccesstoken());
			paramSocial.setSourceId(info.getWeibosource());
			paramSocial.setType(info.getType());
			paramSocial.setPnum(info.getPage());
			paramSocial.setPcount(info.getPagecount());
			if (!Validator.isBlank(info.getSnsuserid())) {
				paramSocial.setUserId(info.getSnsuserid());
			}
			socialResponse = socialService.excutePost(paramSocial,
					"social.getUserRelationList");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (socialResponse != null) {
			int rcCode = socialResponse.getRc();
			if (rcCode == 0) {
				List<com.kascend.e2e.osm.model.UserInfo> userSnsList = socialResponse
						.getUserInfo();
				List<UserSns> snsList = ServiceUtils
						.beanCopyOfUserSns(userSnsList);
				if (snsList.size() > 0) {
					List<SnsUserEntry> snsuserEntryList = new ArrayList<SnsUserEntry>();
					for (int i = 0; i < snsList.size(); i++) {
						SnsUserEntry entry = new SnsUserEntry(snsList.get(i));
						snsuserEntryList.add(i, entry);
					}
					result.setSnsuser(snsuserEntryList);
				}
				if (info.getAllcountflag() == 1) {
					Long allCount = socialResponse.getCount();
					if (allCount != null) {
						result.setAllcount(allCount.intValue());
					}
				}
			}else{
				throw new MusicRcException(rcCode);
			}
		}
		return response;
	}

	public UserSnsResponse snsinvitefriend(SnsInviteFriendInfo info) {
		UserSnsResponse response = new UserSnsResponse();
		if (!Validator.isBlank(info.getSnsusernames())) {
			String userNameContent = ServiceUtils.formatUserNameAsSns(info
					.getSnsusernames());
			String ads = Configer.getValueString(Constants.SOCIAL_KASCEND_ADS);
			String requestContent = userNameContent + ads;
			SocialResponse socialResponse = null;
			try {
				E2eSocialService socialService = new E2eSocialService(
						Configer.getValueString(MusicConst.USER_SERVER_APPKEY),
						Configer.getValueString(MusicConst.USER_SERVER_SECRET));
				RequestParam paramSocial = new RequestParam();
				paramSocial.setToken(info.getToken());
				paramSocial.setAccessToken(info.getAccesstoken());
				paramSocial.setSourceId(info.getWeibosource());
				paramSocial.setContent(requestContent);
				paramSocial.setType(info.getType());
				socialResponse = socialService.excutePost(paramSocial,
						"social.addSnsitem");
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			if (socialResponse != null) {
				int rcCode = socialResponse.getRc();
				if (rcCode == 0) {
					List<String> snsUidList = ServiceUtils.splitStr(
							info.getSnsuids(), Constants.SPLIT_DOT);
					List<String> snsUserNameList = ServiceUtils.splitStr(
							info.getSnsusernames(), Constants.SPLIT_DOT);
					if (snsUidList.size() == snsUserNameList.size()) {
						for (int i = 0; i < snsUidList.size(); i++) {
							if (!Validator.isBlank(snsUidList.get(i))
									&& !Validator.isBlank(snsUserNameList
											.get(i))) {
								socialDao.insertUserSnsInvite(info.getUid(),
										info.getWeibosource(),
										snsUidList.get(i),
										snsUserNameList.get(i));
							}
						}
					}
				}else{
					throw new MusicRcException(rcCode);
				}
			}
		}
		return response;
	}

	public UserSnsShareResponse snsshare(UserSnsShareInfo info) {
		UserSnsShareResponse response = new UserSnsShareResponse();
		if (info.getId() == 0 || info.getItemtype() == 0) {
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		String title = info.getTitle();
		boolean beSend = true;
		if (!Validator.isBlank(title)) {
			beSend = ServiceUtils.checkMessageBeSend(title);
		}
		String itemTitle = getItemTitleByItemTypeAndItemId(info.getId(),
				info.getItemtype());
		String shareInfo = ServiceUtils.formatSnsShareTitle(itemTitle,
				info.getItemtype());
		if (!beSend) {
			title = shareInfo;
		} else {
			title = shareInfo + "  " + title;
		}
		// 修改sharetimes
		SnsItemsInfo snsItemsInfo = new SnsItemsInfo();
		snsItemsInfo.setId(info.getId());
		snsItemsInfo.setItemtype(info.getItemtype());
		snsItemsInfo.setCountType(Constants.COUNT_TYPE_SHARE);
		updateItemsCounts(snsItemsInfo);
		info.setSnsId(0);
		long snsShareId = socialDao.saveUserSnsShare(info);
		String content = title + " "
				+ Configer.getValueString(Constants.SOCIAL_KASCEND_SNS_SHARE)
				+ snsShareId;//
		// 保存content
		socialDao.saveUserSnsContent(snsShareId, content);
		SocialResponse socialResponse = null;
		try {
			E2eSocialService socialService = new E2eSocialService(
					Configer.getValueString(MusicConst.USER_SERVER_APPKEY),
					Configer.getValueString(MusicConst.USER_SERVER_SECRET));
			RequestParam paramSocial = new RequestParam();
			paramSocial.setToken(info.getToken());
			paramSocial.setAccessToken(info.getAccesstoken());
			paramSocial.setSourceId(info.getWeibosource());
			paramSocial.setContent(content);
			paramSocial.setType(info.getType());
			socialResponse = socialService.excutePost(paramSocial,
					"social.addSnsitem");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (socialResponse != null) {
			int rcCode = socialResponse.getRc();
			if (rcCode != 0) {
				socialDao.updateUserSnsShareStatus(snsShareId);
				throw new MusicRcException(rcCode);
			}
		}
		return response;
	}

	public String getItemTitleByItemTypeAndItemId(long itemid, int itemtype) {
		String itemTitle = "";
		/**
		 * 
		 * 获取item type对应的song, artist,album,mv, sns weibo等数据
		 */
		List<Long> snsItemIdList = new ArrayList<Long>();
		List<Long> songIdList = new ArrayList<Long>();
		List<Long> artistIdList = new ArrayList<Long>();
		List<Long> albumIdList = new ArrayList<Long>();
		List<Long> playlistIdList = new ArrayList<Long>();
		List<Long> mvIdList = new ArrayList<Long>();
		if (itemtype == Constants.ITEM_TYPE_SONG) {
			songIdList.add(itemid);
		} else if (itemtype == Constants.ITEM_TYPE_ARTIST) {
			artistIdList.add(itemid);
		} else if (itemtype == Constants.ITEM_TYPE_ALBUM) {
			albumIdList.add(itemid);
		} else if (itemtype == Constants.ITEM_TYPE_MV) {
			mvIdList.add(itemid);
		} else if (itemtype == Constants.ITEM_TYPE_SONG_PLAYLIST
				|| itemtype == Constants.ITEM_TYPE_MV_PLAYLIST) {
			playlistIdList.add(itemid);
		} else if (itemtype == Constants.ITEM_TYPE_SNS) {
			snsItemIdList.add(itemid);
		}
		List<UserSnsItem> snsItemList = new ArrayList<UserSnsItem>();
		List<Song> songList = new ArrayList<Song>();
		List<Artist> artistList = new ArrayList<Artist>();
		List<Album> albumList = new ArrayList<Album>();
		List<UserPlaylist> playlistList = new ArrayList<UserPlaylist>();
		List<SongMv> mvList = new ArrayList<SongMv>();

		if (!snsItemIdList.isEmpty()) {
			snsItemList = socialDao.getUserSnsItemListByIds(snsItemIdList);
			if (snsItemList.size() > 0) {
				itemTitle = snsItemList.get(0).getContent();
			}
		}
		if (!songIdList.isEmpty()) {
			songList = metadataDao.getSongListByIds(songIdList);
			if (songList.size() > 0) {
				itemTitle = songList.get(0).getSongTitle();
			}
		}
		if (!artistIdList.isEmpty()) {
			artistList = metadataDao.getArtistListByIds(artistIdList);
			if (artistList.size() > 0) {
				itemTitle = artistList.get(0).getArtistName();
			}
		}
		if (!albumIdList.isEmpty()) {
			albumList = metadataDao.getAlbumListByIds(albumIdList);
			if (albumList.size() > 0) {
				itemTitle = albumList.get(0).getAlbumTitle();
			}
		}
		if (!playlistIdList.isEmpty()) {
			playlistList = socialDao.getUserPlaylistListByIds(playlistIdList);
			if (playlistList.size() > 0) {
				itemTitle = playlistList.get(0).getTitle();
			}
		}
		if (!mvIdList.isEmpty()) {
			mvList = metadataDao.getSongMvListByIds(mvIdList);
			if (mvList.size() > 0) {
				itemTitle = mvList.get(0).getMvTitle();
			}
		}
		return itemTitle;
	}

	public UserSnsResponse snsadditem(SnsAddItemInfo info) {
		UserSnsResponse response = new UserSnsResponse();
		if (Validator.isBlank(info.getContent()))
			return response;
		if (!ServiceUtils.checkMessageBeSend(info.getContent()))
			return response;
		SocialResponse socialResponse = null;
		try {
			E2eSocialService socialService = new E2eSocialService(
					Configer.getValueString(MusicConst.USER_SERVER_APPKEY),
					Configer.getValueString(MusicConst.USER_SERVER_SECRET));
			RequestParam paramSocial = new RequestParam();
			paramSocial.setToken(info.getToken());
			paramSocial.setAccessToken(info.getAccesstoken());
			paramSocial.setSourceId(info.getWeibosource());
			paramSocial.setContent(info.getContent());
			paramSocial.setType(info.getType());
			paramSocial.setLatitude(info.getLatitude());
			paramSocial.setLongitude(info.getLongitude());
			socialResponse = socialService.excutePost(paramSocial,
					"social.addSnsitem");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (socialResponse != null) {
			int rcCode = socialResponse.getRc();
			if (rcCode == 0) {
				
			}else{
				throw new MusicRcException(rcCode);
			}
		}
		return response;
	}

	public WeiboBondAccountResponse bondweiboaccount(SnsWeiBoInfo info) {
		WeiboBondAccountResponse response = new WeiboBondAccountResponse();
		E2eResponse retRes = null;
		try {
			E2eUserService userService;
			userService = new E2eUserService(
					Configer.getValueString(MusicConst.USER_SERVER_APPKEY),
					Configer.getValueString(MusicConst.USER_SERVER_SECRET),
					30000, 30000, 30000);
			E2eUser inputUser = new E2eUser();
			inputUser.setToken(info.getToken());// 用户token值
			inputUser.setAccessToken(info.getAccesstoken());
			inputUser.setAccessSecret(info.getAccesssecret());
			inputUser.setSnsUserID(info.getSnsuserid());
			inputUser.setWeiboSource(String.valueOf(info.getWeibosource()));
			retRes = userService.excutebondWeiboAccount(inputUser);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (retRes != null) {
			if (retRes.getRetValue() == 0) {
				List<InfoWeibo> weiboList = retRes.getSnsInfo();
				if (weiboList.size() > 0) {
					saveUserSnsInfo(weiboList, info.getUid());
					InfoWeibo infoWeibo = weiboList.get(0);
					SnsInfoEntry result = new SnsInfoEntry(infoWeibo);
					response.setSnsinfo(result);
				}
			} else{
				throw new MusicRcException(retRes.getRetValue());
			}
		}
		return response;
	}

	public void saveUserSnsInfo(List<InfoWeibo> weiboList, long uid) {
		if (weiboList.size() > 0) {
			for (InfoWeibo infoWeibo : weiboList) {
				int source = 0;
				try {
					Integer sourceId = infoWeibo.getWeiboSource();
					if (sourceId != null) {
						source = sourceId;
					}
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
				int userWeiboCount = socialDao.getNativeUserSnsCount(uid,
						source);
				if (userWeiboCount == 0) {
					UserSns userSns = new UserSns();
					userSns.setUid(uid);
					userSns.setSnsSite(source);
					userSns.setSnsAccount(infoWeibo.getWeiboAccount());
					userSns.setSnsUserName(infoWeibo.getSnsUserName());
					userSns.setSnsPassword(infoWeibo.getWeiboPassword());
					userSns.setSnsUid(infoWeibo.getSnsUserID());
					userSns.setStatus(infoWeibo.getWeiboState());
					userSns.setSnsHeadIcon(infoWeibo.getSnsUserHeadicon());
					socialDao.saveNativeUserSns(userSns);
				}
			}
		}

	}

	public WeiboBondAccountResponse removeweiboaccount(SnsWeiBoInfo info) {
		WeiboBondAccountResponse response = new WeiboBondAccountResponse();
		E2eResponse retRes = null;
		try {
			E2eUserService userService;
			userService = new E2eUserService(
					Configer.getValueString(MusicConst.USER_SERVER_APPKEY),
					Configer.getValueString(MusicConst.USER_SERVER_SECRET),
					30000, 30000, 30000);
			E2eUser inputUser = new E2eUser();
			inputUser.setToken(info.getToken());// 用户token值
			inputUser.setWeiboSource(String.valueOf(info.getWeibosource()));
			retRes = userService.excuteRemoveWeiboAccount(inputUser);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (retRes != null) {
			if (retRes.getRetValue() == 0) {
				socialDao.deleteNativeUserSns(info.getUid(),
						info.getWeibosource());
			} else {
				throw new MusicRcException(retRes.getRetValue());
			} 
		}
		return response;
	}

	public UserPlaylistResponse getplaylistsofuser(GetPlaylistsOfUserInfo info) {
		UserPlaylistResponse response = new UserPlaylistResponse();
		UserPlaylistListEntry result = new UserPlaylistListEntry();
		ServiceUtils.pageCompute(info);
		response.setPlaylists(result);
		List<UserPlaylist> userPlaylist = socialDao
				.getUserPlaylistsByTargetUid(info);
		if (userPlaylist.size() > 0) {
			List<UserPlaylistEntry> playlistEntryList = new ArrayList<UserPlaylistEntry>();
			for (int i = 0; i < userPlaylist.size(); i++) {
				UserPlaylistEntry entry = new UserPlaylistEntry(
						userPlaylist.get(i));
				playlistEntryList.add(i, entry);
			}
			if (info.getAllcountflag() == 1) {
				int allcount = socialDao
						.getUserPlaylistsByTargetUidAllCount(info
								.getTargetuid());
				result.setAllcount(allcount);
			}
			result.setPlaylist(playlistEntryList);
		}
		return response;
	}

	public New_ArtistListResponse getuserartists(GetUserArtistInfo info) {
		New_ArtistListResponse response = new New_ArtistListResponse();
		New_ArtistListEntry result = new New_ArtistListEntry();
		response.setArtistslist(result);
		ServiceUtils.pageCompute(info);
		long uid = info.getUid();
		if (info.getTargetuid() > 0) {
			uid = info.getTargetuid();
		}
		// 设置返回的歌曲是曲库来的
		info.setUserPlaylistDataFrom(Constants.USER_PLAYLIST_DATA_FROM_SYSTEM);
		UserPlaylist userPlaylist = socialDao.getUserPlaylistByUidAndDataFrom(
				Constants.USER_PLAYLIST_DATA_FROM_SYSTEM, uid);
		if (userPlaylist != null) {
			long playlistId = userPlaylist.getPlaylistId();
			info.setPlaylistid(playlistId);
			List<New_ArtistEntry> artistEntryList = new ArrayList<New_ArtistEntry>();
			List<Long> artistIdList = socialDao
					.getUserListenedArtistIdList(info);
			info.setArtistIdList(artistIdList);
			List<Artist> artistList = socialDao.getUserListenedArtistList(info);
			if (artistList.size() > 0) {
				List<Artist> artistAlbumCount = socialDao
						.getUserListenedArtistAlbumCountList(info);
				if (artistAlbumCount.size() > 0) {
					for (int i = 0; i < artistList.size(); i++) {
						Artist artistInfo = artistList.get(i);
						for (int j = 0; j < artistAlbumCount.size(); j++) {
							Artist albumCountInfo = artistAlbumCount.get(j);
							if (artistInfo.getArtistId() == albumCountInfo
									.getArtistId()) {
								artistInfo.setPublishedAlbums(albumCountInfo
										.getPublishedAlbums());
							}
						}
					}
				}
				artistList = ServiceUtils.sortArtistSeqList(artistIdList,
						artistList);
				for (int i = 0; i < artistList.size(); i++) {
					New_ArtistEntry artistEntry = new New_ArtistEntry(
							artistList.get(i));
					artistEntryList.add(i, artistEntry);
				}
				result.setArtist(artistEntryList);
				if (info.getAllcountflag() == 1) {
					int allCount = socialDao
							.getUserListenedArtistIdListAllCount(info);
					result.setAllcount(allCount);
				}
			}
		}
		return response;
	}

	public New_ArtistInfoResponse getuseralbumsofartist(
			GetUserAlbumsOfArtistInfo info) {
		if (info.getArtistid() <= 0) {
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		New_ArtistInfoResponse response = new New_ArtistInfoResponse();
		ServiceUtils.pageCompute(info);
		Artist artist = metadataDao.getArtistInfoByArtistIdOrName(
				info.getArtistid(), null);
		if (artist != null) {
			artist.setPublishedSongs(0);
			artist.setPublishedAlbums(0);
			New_ArtistEntry artistEntry = new New_ArtistEntry(artist);
			New_AlbumListEntry albumEntry = new New_AlbumListEntry();
			// 获取用听过的所有专辑
			artistEntry.setAlbumslist(albumEntry);
			int listenedCount = socialDao.getUserListenedAlbumAllCount(info);
			int albumAllCount = metadataDao.getAlbumListByArtistIdAllCount(info
					.getArtistid());
			int unListenedAlbumCount = albumAllCount - listenedCount;
			artistEntry.setUnlistenedalbums(unListenedAlbumCount);
			List<Album> albumList = new ArrayList<Album>();
			if (info.getListenedflag() == Constants.USER__ALBUM_ALL) {
				GetAlbumsOfArtistInfo getAlbumsOfArtistInfo = new GetAlbumsOfArtistInfo();
				getAlbumsOfArtistInfo.setArtistid(artist.getArtistId());
				getAlbumsOfArtistInfo.setAlbumartflag(1);
				getAlbumsOfArtistInfo.setBioflag(1);
				getAlbumsOfArtistInfo.setStart(info.getStart());
				getAlbumsOfArtistInfo.setPagecount(info.getPagecount());
				albumList = metadataDao
						.getAlbumListAndAllSongsByArtistId(getAlbumsOfArtistInfo);
				albumList = ServiceUtils.removeIterativeOnAlbumList(albumList);
			} else {
				List<Long> albumIdList = new ArrayList<Long>();
				List<Long> listenedAlbumIdList = socialDao
						.getUserListenedAlbumIdListAll(info);
				info.setListenedAlbumIdList(listenedAlbumIdList);
				if (info.getListenedflag() == Constants.USER_ALBUM_LISTENED) {
					albumIdList = listenedAlbumIdList;
				} else if (info.getListenedflag() == Constants.USER_ALBUM_UNLISTENED) {
					albumIdList = socialDao.getUserUnlistenedAlbumIdList(info);
				}
				albumList = metadataDao
						.getAlbumListInfoByIdsWithStatus(albumIdList);
			}
			if (albumList.size() > 0) {
				List<New_AlbumEntry> albumListEntry = new ArrayList<New_AlbumEntry>();
				for (int i = 0; i < albumList.size(); i++) {
					albumList.get(i).setAlbumBio(null);
					New_AlbumEntry entry = new New_AlbumEntry(albumList.get(i));
					albumListEntry.add(i, entry);
				}
				albumEntry.setAlbum(albumListEntry);
			}
			response.setArtist(artistEntry);
		}
		return response;
	}

	public New_AlbumResponse getuseralbums(GetUserAlbumsInfo info) {
		New_AlbumResponse response = new New_AlbumResponse();
		New_AlbumListEntry result = new New_AlbumListEntry();
		response.setAlbumslist(result);
		ServiceUtils.pageCompute(info);
		info.setUserPlaylistDataFrom(Constants.USER_PLAYLIST_DATA_FROM_SYSTEM);
		List<Long> albumIdList = socialDao.getUserListenedAlbumIdList(info);
		List<Album> albumList = metadataDao
				.getAlbumListInfoByIdsWithStatus(albumIdList);
		if (albumList.size() > 0) {
			albumList = ServiceUtils.sortAlbumSeqList(albumIdList, albumList);
			List<New_AlbumEntry> albumListEntry = new ArrayList<New_AlbumEntry>();
			for (int i = 0; i < albumList.size(); i++) {
				albumList.get(i).setAlbumBio(null);
				New_AlbumEntry entry = new New_AlbumEntry(albumList.get(i));
				albumListEntry.add(i, entry);
			}
			if (info.getAllcountflag() == 1) {
				int allCount = socialDao
						.getUserListenedAlbumIdListAllCount(info);
				result.setAllcount(allCount);
			}
			result.setAlbumscount(albumListEntry.size());
			result.setAlbum(albumListEntry);
		}
		return response;
	}

	public UserResponse syncuser(PageInfo info) {
		UserResponse response = new UserResponse();
		auth.checkLoginWithUserServer(info.getToken(), null);
		return response;
	}

	public LastSyncPlaylistTimeResponse getlastsyncplaylisttime(
			GetLastSyncPlaylistTimeInfo info) {
		LastSyncPlaylistTimeResponse response = new LastSyncPlaylistTimeResponse();
		UserPlaylist userPlaylist = null;
		if (info.getPlaylistid() == 0) {
			userPlaylist = socialDao.getUserPlaylistByUidAndDataFrom(
					Constants.USER_PLAYLIST_DATA_FROM_SYSTEM, info.getUid());
		} else {
			userPlaylist = socialDao.getUserPlaylistByIdAndUid(
					info.getPlaylistid(), info.getUid());
		}
		if (userPlaylist != null) {
			int updateTime = userPlaylist.getUpdateTime();
			response.setLastsynctime(updateTime);
			response.setWrongrang(300);
		}
		return response;
	}

	public DeletePlaylistSongResponse deleteplaylistsongs(
			DeletePlaylistSongInfo info) {
		DeletePlaylistSongResponse response = new DeletePlaylistSongResponse();
		long playlistId = info.getPlaylistid();
		if (playlistId == 0) {
			playlistId = socialDao.getDefaultUserPlaylistidByUid(info.getUid(),
					Constants.USER_PLAYLIST_DATA_FROM_SYSTEM);
		}
		info.setpId(playlistId);
		List<Long> songIdList = null;
		if (!Validator.isBlank(info.getSongs())) {
			songIdList = ServiceUtils.splitLong(info.getSongs());
		} else {
			if (!Validator.isBlank(info.getSong())
					|| !Validator.isBlank(info.getFilename())) {
				GetSearchInfo getSearchInfo = new GetSearchInfo();
				getSearchInfo.setStart(0);
				getSearchInfo.setPagecount(5);
				getSearchInfo.setArtist(info.getArtist());
				getSearchInfo.setAlbum(info.getAlbum());
				if (Validator.isBlank(info.getSong())) {
					getSearchInfo.setSong(info.getFilename());
				} else {
					getSearchInfo.setSong(info.getSong());
				}
				getSearchInfo.setFilename(info.getFilename());
				List<Long> songsList = mmsHelper
						.getSearchSongIdList(getSearchInfo);
				if (songsList.size() > 0) {
					songIdList = new ArrayList<Long>();
					songIdList.add(songsList.get(0));
				}
			}
		}
		if (songIdList != null && songIdList.size() > 0) {
			info.setSongIdList(songIdList);
			computeDeleteCount(info);
			updateUserNativePlaylistCount(info.getUid());
		}
		return response;
	}

	// 根据专辑，用户，播放列表id计算其songcount
	public void computeAlbumAllCount(long playlistId, long uid, long albumId) {
		if (albumId > 0) {
			int songCount = socialDao.getUserSongCountOfAlbumByAlbumId(
					playlistId, albumId, uid);
			UserSongAlbum userSongAlbumParam = new UserSongAlbum();
			userSongAlbumParam.setPlaylistId(playlistId);
			userSongAlbumParam.setAlbumId(albumId);
			List<Long> albumIdList = new ArrayList<Long>();
			albumIdList.add(albumId);
			userSongAlbumParam.setAlbumIdList(albumIdList);
			userSongAlbumParam.setSongCount(songCount);
			List<Long> dbAlbumIdList = socialDao
					.getUserSongAlbumByPlaylistIdAndAlbumId(userSongAlbumParam);
			if (dbAlbumIdList.size() == 0) {
				// 直接插入
				socialDao
						.addUserSongAlbumByPlaylistIdAndAlbumId(userSongAlbumParam);
			} else {
				// 修改
				socialDao
						.updateUserSongAlbumByPlaylistIdAndAlbumId(userSongAlbumParam);
			}
		}
	}

	// 根据歌手，用户，播放列表id计算其albumcount和songcount
	public void computeArtistsAllCount(long playlistId, long uid, long artistId) {
		if (artistId > 0) {
			int songCount = socialDao.getUserSongCountOfArtistByArtistId(
					playlistId, artistId, uid);
			int albumCount = socialDao.getUserAlbumCountOfArtistByArtistId(
					playlistId, artistId, uid);
			UserSongArtist userSongArtistParam = new UserSongArtist();
			userSongArtistParam.setPlaylistId(playlistId);
			List<Long> artistIdList = new ArrayList<Long>();
			artistIdList.add(artistId);
			userSongArtistParam.setArtistId(artistId);
			userSongArtistParam.setArtistIdList(artistIdList);
			List<Long> dbArtistIdList = socialDao
					.getUserSongArtistByPlaylistIdAndArtistId(userSongArtistParam);
			userSongArtistParam.setSongCount(songCount);
			userSongArtistParam.setAlbumCount(albumCount);
			if (dbArtistIdList.size() == 0) {
				// 直接插入
				socialDao
						.addUserSongArtistByPlaylistIdAndArtistId(userSongArtistParam);
			} else {
				// 修改
				socialDao
						.updateUserSongArtistByPlaylistIdAndArtistId(userSongArtistParam);
			}
		}
	}

	// 计算调用删除接口后，曲库中受影响的歌曲和专辑数量
	@SuppressWarnings("rawtypes")
	public void computeDeleteCount(DeletePlaylistSongInfo info) {
		List<UserSong> userSongList = socialDao.getUserSongByCondition(info);
		Set<Long> artistIdsList = new HashSet<Long>();
		Set<Long> albumIdsList = new HashSet<Long>();
		for (int i = 0; i < userSongList.size(); i++) {
			long albumId = userSongList.get(i).getAlbumId();
			if (albumId > 0) {
				albumIdsList.add(albumId);
			}
			long artistId = userSongList.get(i).getArtistId();
			if (artistId > 0) {
				artistIdsList.add(artistId);
			}
		}
		socialDao.deleteUserSongBySongIdAndPlaylistId(info);
		// 歌曲删除后，计算受影响的专辑和歌手中的歌曲和专辑数量
		Iterator iter = artistIdsList.iterator();
		while (iter.hasNext()) {
			long artistId = (Long) iter.next();
			computeArtistsAllCount(info.getpId(), info.getUid(), artistId);
		}
		Iterator iterAlbum = albumIdsList.iterator();
		while (iterAlbum.hasNext()) {
			long albumId = (Long) iterAlbum.next();
			computeAlbumAllCount(info.getpId(), info.getUid(), albumId);
		}
	}

	// 计算调用like接口后，受影响的歌曲和专辑数量
	public void computeLikeCount(long playlistId, long uid, long songId) {
		DeletePlaylistSongInfo deletePlaylistSongInfo = new DeletePlaylistSongInfo();
		deletePlaylistSongInfo.setpId(playlistId);
		deletePlaylistSongInfo.setUid(new Long(uid).intValue());
		List<Long> songIdList = new ArrayList<Long>();
		songIdList.add(songId);
		deletePlaylistSongInfo.setSongIdList(songIdList);
		List<UserSong> userSongList = socialDao
				.getUserSongByCondition(deletePlaylistSongInfo);
		if (userSongList.size() > 0) {
			UserSong userSong = userSongList.get(0);
			long albumId = userSong.getAlbumId();
			computeAlbumAllCount(playlistId, uid, albumId);
			long artistId = userSong.getArtistId();
			computeArtistsAllCount(playlistId, uid, artistId);
		}
	}

	// 计算用户曲库中所有的歌曲，专辑歌手数量，适用与sycnplaylist接口
	public void operateUserSongCount(long playlistId) {
		updateUserSongArtistCount(playlistId);
		updateUserSongAlbumCount(playlistId);
	}

	// 计算用户曲库中所有的歌曲，专辑歌手数量，适用与sycnplaylist、like、deleteplaylistsongs接口
	public void updateUserNativePlaylistCount(long uid) {
		// 同步user表songcount,artistcount,albumcount
		int songCount = socialDao.getSongCountOfUserSong(uid);
		int albumCount = socialDao.getAlbumCountOfUserSong(uid);
		int artistCount = socialDao.getArtistCountOfUserSong(uid);
		socialDao.updateUserMetadatCount(uid, songCount, artistCount,
				albumCount);
	}

	// 计算用户曲库中所有的歌曲，专辑歌手数量，适用与sycnplaylist接口
	public void updateUserSongArtistCount(long playlistId) {
		List<UserSongArtist> allList = new ArrayList<UserSongArtist>();
		List<UserSongArtist> userSongOfArtistList = socialDao
				.getUserPlaylistSongCountOfArtist(playlistId);
		List<UserSongArtist> userSongOfAlbumOfArtistList = socialDao
				.getUserPlaylistAlbumCountOfArtist(playlistId);
		List<UserSongArtist> sameUserSongArtist = ServiceUtils
				.getSameUserSongArtist(userSongOfArtistList,
						userSongOfAlbumOfArtistList);
		allList.addAll(sameUserSongArtist);
		List<UserSongArtist> leftUserSongArtist = ServiceUtils
				.getDiffUserSongCountOfArtist(userSongOfArtistList,
						sameUserSongArtist);
		allList.addAll(leftUserSongArtist);
		List<UserSongArtist> rightUserSongArtist = ServiceUtils
				.getDiffUserSongCountOfArtist(userSongOfAlbumOfArtistList,
						sameUserSongArtist);
		allList.addAll(rightUserSongArtist);
		List<Long> artistIdList = new ArrayList<Long>();
		for (int i = 0; i < allList.size(); i++) {
			artistIdList.add(allList.get(i).getArtistId());
		}
		if (artistIdList.size() == 0) {
			// 播放列表里面没有数据，那么将count归0，暂不删除
			socialDao.clearUserSongArtistByPlaylistId(playlistId);
		} else {
			UserSongArtist userSongArtistParam = new UserSongArtist();
			userSongArtistParam.setPlaylistId(playlistId);
			userSongArtistParam.setArtistIdList(artistIdList);
			List<Long> dbArtistIdList = socialDao
					.getUserSongArtistByPlaylistIdAndArtistId(userSongArtistParam);
			if (dbArtistIdList.size() == 0) {
				// 全部插入
				for (int i = 0; i < allList.size(); i++) {
					socialDao.addUserSongArtistByPlaylistIdAndArtistId(allList
							.get(i));
				}
			} else {
				for (int i = 0; i < allList.size(); i++) {
					UserSongArtist usa = allList.get(i);
					for (long dbArtistId : dbArtistIdList) {
						if (usa.getArtistId() == dbArtistId) {
							usa.setIncludeFlag(true);
						}
					}
				}
				for (int i = 0; i < allList.size(); i++) {
					UserSongArtist usa = allList.get(i);
					if (usa.isIncludeFlag()) {
						// 修改
						socialDao
								.updateUserSongArtistByPlaylistIdAndArtistId(usa);
					} else {
						// 插入
						socialDao.addUserSongArtistByPlaylistIdAndArtistId(usa);
					}
				}
			}
		}
	}

	// 计算用户曲库中所有的歌曲，专辑歌手数量，适用与sycnplaylist接口
	public void updateUserSongAlbumCount(long playlistId) {
		List<UserSongAlbum> userSongAlbumList = socialDao
				.getUserPlaylistSongCountOfAlbum(playlistId);
		List<Long> albumIdList = new ArrayList<Long>();
		for (int i = 0; i < userSongAlbumList.size(); i++) {
			albumIdList.add(userSongAlbumList.get(i).getAlbumId());
		}
		if (albumIdList.size() == 0) {
			// 播放列表里面没有数据，那么将count归0，暂不删除
			socialDao.clearUserSongAlbumByPlaylistId(playlistId);
		} else {
			UserSongAlbum userSongAlbumParam = new UserSongAlbum();
			userSongAlbumParam.setPlaylistId(playlistId);
			userSongAlbumParam.setAlbumIdList(albumIdList);
			List<Long> dbAlbumIdList = socialDao
					.getUserSongAlbumByPlaylistIdAndAlbumId(userSongAlbumParam);
			if (dbAlbumIdList.size() == 0) {
				// 全部插入
				for (int i = 0; i < userSongAlbumList.size(); i++) {
					socialDao
							.addUserSongAlbumByPlaylistIdAndAlbumId(userSongAlbumList
									.get(i));
				}
			} else {
				for (int i = 0; i < userSongAlbumList.size(); i++) {
					UserSongAlbum usa = userSongAlbumList.get(i);
					for (long dbAlbumId : dbAlbumIdList) {
						if (usa.getAlbumId() == dbAlbumId) {
							usa.setIncludeFlag(true);
						}
					}
				}
				for (int i = 0; i < userSongAlbumList.size(); i++) {
					UserSongAlbum usa = userSongAlbumList.get(i);
					if (usa.isIncludeFlag()) {
						// 修改
						socialDao
								.updateUserSongAlbumByPlaylistIdAndAlbumId(usa);
					} else {
						// 插入
						socialDao.addUserSongAlbumByPlaylistIdAndAlbumId(usa);
					}
				}

			}
		}
	}

	public UserListResponse recommendusers(RecommendUserInfo info) {
		UserListResponse response = new UserListResponse();
		UserListEntry result = new UserListEntry();
		response.setUserslist(result);
		List<Long> followedUidList = socialDao.getUserFollowedUids(info
				.getUid());
		// 不能推荐自己和我已经关注的人
		followedUidList.add(new Long(info.getUid()));
		info.setUidList(followedUidList);
		List<Long> uidList = new ArrayList<Long>();
		List<com.kascend.music2.recommend.sdk.entity.User> recommendUserList = getRecommendUsers(info);
		List<Long> sourceUidList = new ArrayList<Long>();
		for (int i = 0; i < recommendUserList.size(); i++) {
			sourceUidList.add(i, recommendUserList.get(i).getUid());
		}
		List<Long> recUidList = sourceUidList;
		if (recUidList.size() > 0) {
			recUidList.removeAll(followedUidList);
		}
		List<Long> seqUidList = ServiceUtils.sortLongSeqList(sourceUidList,
				recUidList);
		uidList = ServiceUtils.outSidePaging(seqUidList, info.getPagecount(),
				info.getPage());
		if (uidList.size() < info.getPagecount()) {
			int lastSize = info.getPagecount() - uidList.size();
			info.setPagecount(lastSize);
			followedUidList.addAll(uidList);
			info.setUidList(followedUidList);
			List<Long> lastUidList = socialDao.generateRandomUids(info);
			uidList.addAll(lastUidList);
		}
		List<User> userList = socialDao.getUserListByUids(uidList);
		userList = ServiceUtils.sortUserRandomList(uidList, userList);
		List<UserEntry> userEntryList = getUserLastSongList(userList);
		result.setUser(userEntryList);
		return response;
	}

	// public UserListResponse recommendusers(RecommendUserInfo info) {
	// UserListResponse response=new UserListResponse();
	// UserListEntry result=new UserListEntry();
	// response.setUserslist(result);
	// List<Long> followedUidList=socialDao.getUserFollowedUids(info.getUid());
	// //不能推荐自己和我已经关注的人
	// followedUidList.add(new Long(info.getUid()));
	// info.setUidList(followedUidList);
	// List<Long> uidList=socialDao.generateRandomUids(info);
	// List<User> userList=socialDao.getUserListByUids(uidList);
	// userList=ServiceUtils.sortUserRandomList(uidList, userList);
	// List<UserEntry> userEntryList=getUserLastSongList(userList);
	// result.setUser(userEntryList);
	// return response;
	// }

	public List<com.kascend.music2.recommend.sdk.entity.User> getRecommendUsers(
			RecommendUserInfo info) {
		List<com.kascend.music2.recommend.sdk.entity.User> userList = new ArrayList<com.kascend.music2.recommend.sdk.entity.User>();
		KasUserRecommendInfo recommendInfo = new KasUserRecommendInfo();
		recommendInfo.setPagecount(100);
		recommendInfo.setAppkey("test");
		recommendInfo.setToken(info.getToken());
		recommendInfo.setUid(info.getUid());
		KasUserRecommendResponse retRes = recommendService
				.getrecommendusers(recommendInfo);
		if (retRes != null) {
			int rcCode = retRes.getRc();
			if (rcCode == 0) {
				userList = retRes.getUserList();
			} else {
				throw new MusicRcException(rcCode);
			} 
		}
		return userList;
	}

	public UserListResponse searchusers(SearchUserInfo info) {
		if(Validator.isBlank(info.getNickname())||Validator.isBlank(info.getKeyname())){
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		UserListResponse response = new UserListResponse();
		UserListEntry result = new UserListEntry();
		response.setUserslist(result);
		ServiceUtils.pageCompute(info);
		String searchName = info.getNickname();
		if (Validator.isBlank(searchName)) {
			searchName = info.getKeyname();
		}
		if (!Validator.isBlank(searchName)) {
			String keyName = "%" + searchName + "%";
			info.setKeyname(keyName);
			List<User> userList = socialDao.getUserListByKeyname(info);
			List<UserEntry> userEntryList = getUserLastSongList(userList);
			result.setUser(userEntryList);
		}
		return response;
	}

	public CommentResponse comment(CommentInfo info) {
		ServiceUtils.getStrParamLength(info.getComment(),240);
		ServiceUtils.getLenParamLength(info.getId(), 19);
		CommentResponse response = new CommentResponse();
		if(info.getId()<=0||info.getItemtype()<=0){
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		if (Validator.isBlank(info.getComment())) {
			throw new MusicRcException(MusicRcException.NOTNULLCOMMENTS);
		}
		if (!ServiceUtils.checkMessageBeSend(info.getComment()))
			return response;
		if (info.getReplycommentid() > 0
				&& !checkUserCommentExisted(info.getReplycommentid())) {
			throw new MusicRcException(MusicRcException.NOT_EXISTED_MESSAGEID);
		}
		if (info.getReplycommentid() > 0) {
			UserComment uc = socialDao.getUserCommentListInfoByCommentId(info
					.getReplycommentid());
			if(uc!=null){
				info.setReplyUid(uc.getUid());
				info.setIsReply(1);
			}
		}
		// itemtype=3,id=albumid,seconditemid=songid
		Long commentId = socialDao.saveUserComment(info);
		info.setCountType(Constants.COUNT_TYPE_COMMENT);
		updateItemsCounts(info);
		//修改评论次数
		if(info.getItemtype()==Constants.ITEM_TYPE_COMMENT){
			socialDao.updateUserShare(info.getId(),Constants.COUNT_TYPE_COMMENT);
		}
		String comment = info.getComment();
		if (!Validator.isBlank(comment)) {
			if (comment.length() > 280) {
				comment = comment.substring(0, 280);
			}
			info.setComment(comment);
			info.setCommentId(commentId);
			socialDao.saveUserCommentContent(info);
		}
		if (info.getItemtype() == Constants.ITEM_TYPE_ALBUM) {
			if (info.getId() > 0) {
				mmsHelper.operateUserOpAlbumAndAlbumUdata(info.getUid(),
						info.getId(), info.getSeconditemid(),
						Constants.OP_TYPE_COMMENT, commentId);
			}
		}
		//用户评论，默认进入share //TODO
		if(info.getReplycommentid()==0){
			if(info.getItemtype()==Constants.ITEM_TYPE_SONG){
				long songId=info.getId();
				Song song=metadataDao.getSongInfoBySongIdNoCondition(songId);
				if(song!=null){
					ShareInfo shareInfo=new ShareInfo();
					shareInfo.setUid(info.getUid());
					shareInfo.setId(commentId);
					shareInfo.setShareType(Constants.SHARE_TYPE_SHARE);
					shareInfo.setDataFrom(Constants.SHARE_DATAFROM_COMMENT);
					shareInfo.setTitle("评论了歌曲\"" + song.getSongTitle() + "\"");
					shareInfo.setItemtype(Constants.ITEM_TYPE_COMMENT);
					this.share(shareInfo);
				}
			}else if (info.getItemtype()==Constants.ITEM_TYPE_ALBUM&&info.getSeconditemid()>0){
				long songId=info.getSeconditemid();
				Song song=metadataDao.getSongInfoBySongIdNoCondition(songId);
				if(song!=null){
					ShareInfo shareInfo=new ShareInfo();
					shareInfo.setUid(info.getUid());
					shareInfo.setId(commentId);
					shareInfo.setShareType(Constants.SHARE_TYPE_SHARE);
					shareInfo.setDataFrom(Constants.SHARE_DATAFROM_COMMENT);
					shareInfo.setTitle("评论了歌曲\"" + song.getSongTitle() + "\"");
					shareInfo.setItemtype(Constants.ITEM_TYPE_COMMENT);
					this.share(shareInfo);
				}
			}else if(info.getItemtype()==Constants.ITEM_TYPE_ALBUM){
				long albumId=info.getId();
				Album album=metadataDao.getAlbumInfoByIdNoCondition(albumId);
				if(album!=null){
					ShareInfo shareInfo=new ShareInfo();
					shareInfo.setUid(info.getUid());
					shareInfo.setId(commentId);
					shareInfo.setShareType(Constants.SHARE_TYPE_SHARE);
					shareInfo.setDataFrom(Constants.SHARE_DATAFROM_COMMENT);
					shareInfo.setTitle("评论了专辑\"" + album.getAlbumTitle() + "\"");
					shareInfo.setItemtype(Constants.ITEM_TYPE_COMMENT);
					this.share(shareInfo);
				}
			}
		}
		return response;
	}

	// 只处理单一itemtype的评论数据
	public void operateUserComment(List<UserComment> userCommentList,
			GetCommentsOfItemInfo info) {
		List<Long> idList = new ArrayList<Long>();
		List<Long> secondIdList = new ArrayList<Long>();
		for (int i = 0; i < userCommentList.size(); i++) {
			idList.add(userCommentList.get(i).getItemId());
			secondIdList.add(userCommentList.get(i).getSecondItemId());
		}
		idList = ServiceUtils.removeIterativeOnIdList(idList);
		secondIdList = ServiceUtils.removeIterativeOnIdList(secondIdList);
		if (info.getItemtype() == Constants.ITEM_TYPE_SONG) {
			// list是songid
			List<Song> songList = metadataDao.getSongListByIds(idList);
			for (int i = 0; i < userCommentList.size(); i++) {
				UserComment uc = userCommentList.get(i);
				for (int j = 0; j < songList.size(); j++) {
					Song song = songList.get(j);
					if (uc.getItemId() == song.getSongId()) {
						uc.setSubTitle(ServiceUtils.formatSubTitle(
								song.getSongTitle(), Constants.OP_TYPE_COMMENT));
					}
				}
			}
		} else if (info.getItemtype() == Constants.ITEM_TYPE_ALBUM) {
			// list是songid
			List<Song> songList = metadataDao.getSongListByIds(secondIdList);
			for (int i = 0; i < userCommentList.size(); i++) {
				UserComment uc = userCommentList.get(i);
				for (int j = 0; j < songList.size(); j++) {
					Song song = songList.get(j);
					if (uc.getSecondItemId() == song.getSongId()) {
						uc.setSubTitle(ServiceUtils.formatSubTitle(
								song.getSongTitle(), Constants.OP_TYPE_COMMENT));
					}
				}
			}
		} else if (info.getItemtype() == Constants.ITEM_TYPE_ARTIST) {
			List<Artist> artistList = metadataDao.getArtistListByIds(idList);
			for (int i = 0; i < userCommentList.size(); i++) {
				UserComment uc = userCommentList.get(i);
				for (int j = 0; j < artistList.size(); j++) {
					Artist artist = artistList.get(j);
					if (uc.getItemId() == artist.getArtistId()) {
						uc.setSubTitle(ServiceUtils.formatSubTitle(
								artist.getArtistName(),
								Constants.OP_TYPE_COMMENT));
					}
				}
			}
		} else if (info.getItemtype() == Constants.ITEM_TYPE_SONG_PLAYLIST) {

		} else if (info.getItemtype() == Constants.ITEM_TYPE_MV) {

		} else if (info.getItemtype() == Constants.ITEM_TYPE_SNS) {

		} else if (info.getItemtype() == Constants.ITEM_TYPE_MV_PLAYLIST) {

		}
	}

	public CommentListResponse getcommentsofitem(GetCommentsOfItemInfo info) {
		if (info.getId() <= 0 || info.getItemtype() <= 0) {
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		CommentListResponse response = new CommentListResponse();
		CommentListEntry result = new CommentListEntry();
		response.setCommentslist(result);
		ServiceUtils.pageCompute(info);
		List<UserComment> userCommentList = socialDao
				.getUserCommentListByItemTypeAndId(info);
		if (userCommentList.size() > 0) {
			List<Long> commendIdList = new ArrayList<Long>();
			for (int i = 0; i < userCommentList.size(); i++) {
				commendIdList.add(userCommentList.get(i).getCommentId());
			}
			List<UserComment> dataUserComments = new ArrayList<UserComment>();
			List<UserComment> replyUserCommentList = socialDao
					.getReplyUserCommentListByCommendIdList(commendIdList);
			dataUserComments.addAll(replyUserCommentList);
			dataUserComments.addAll(userCommentList);
			operateUserComment(dataUserComments, info);
			List<CommentEntry> entryList = new ArrayList<CommentEntry>();
			for (int i = 0; i < userCommentList.size(); i++) {

				CommentEntry entry = new CommentEntry(userCommentList.get(i));
				if (replyUserCommentList.size() > 0) {
					CommentListEntry replyCommentsEntry = new CommentListEntry();
					List<CommentEntry> replyEntryList = new ArrayList<CommentEntry>();
					for (int j = 0; j < replyUserCommentList.size(); j++) {
						if (userCommentList.get(i).getCommentId() == replyUserCommentList
								.get(j).getReplyCommentId()) {
							CommentEntry replyEntry = new CommentEntry(
									replyUserCommentList.get(j));
							replyEntryList.add(replyEntryList.size(),
									replyEntry);
						}
					}
					replyCommentsEntry.setComment(replyEntryList);
					entry.setReplycommentslist(replyCommentsEntry);
				}
				entryList.add(i, entry);
			}
			result.setComment(entryList);
			if (info.getAllcountflag() == 1) {
				int allCount = socialDao
						.getUserCommentListByItemTypeAndIdAllCount(info);
				result.setAllcount(allCount);
			}
		}
		return response;
	}

	public LeaveMessageResponse leavemessage(LeaveMessageInfo info) {
		ServiceUtils.getStrParamLength(info.getMessage(),240);
		LeaveMessageResponse response = new LeaveMessageResponse();
		if (!ServiceUtils.checkMessageBeSend(info.getMessage()))
			return response;
		if (info.getTouid() == 0) {
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		if (Validator.isBlank(info.getMessage())) {
			throw new MusicRcException(MusicRcException.NULL_MESSAGE);
		}
		if (info.getReplymessageid() > 0
				&& !checkUserMessageExisted(info.getReplymessageid())) {
			throw new MusicRcException(MusicRcException.NOT_EXISTED_MESSAGEID);
		}

		if (info.getReplymessageid() > 0) {
			info.setIsReply(1);
		}
		long messageId = socialDao.insertUserMessage(info);
		info.setMessageId(messageId);
		socialDao.insertUserMessageContent(info);
		return response;
	}

	public boolean checkUserMessageExisted(long messageId) {
		int messageCount = socialDao.getMessageCount(messageId);
		if (messageCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkUserCommentExisted(long commentId) {
		int commentCount = socialDao.getCommentCount(commentId);
		if (commentCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	public LeaveMessageResponse getleavemessages(LeaveMessageInfo info) {
		LeaveMessageResponse response = new LeaveMessageResponse();
		MessageListEntry result = new MessageListEntry();
		response.setMessagelist(result);
		ServiceUtils.pageCompute(info);
		long uid = info.getUid();
		if (info.getTargetuid() > 0) {
			uid = info.getTargetuid();
		}
		List<UserMessage> userMessageList = socialDao.getUserMessageByUid(uid,
				info.getStart(), info.getPagecount());
		if (userMessageList.size() > 0) {
			List<Long> uidList = new ArrayList<Long>();
			List<Long> messageIdList = new ArrayList<Long>();
			for (int i = 0; i < userMessageList.size(); i++) {
				UserMessage um = userMessageList.get(i);
				uidList.add(um.getFromUid());
				messageIdList.add(um.getMessagetId());
			}
			List<UserMessage> replyUserMessageList = socialDao
					.getUserMessageByMessageId(messageIdList);
			for (int i = 0; i < replyUserMessageList.size(); i++) {
				UserMessage um = replyUserMessageList.get(i);
				uidList.add(um.getFromUid());
			}
			uidList = ServiceUtils.removeIterativeOnIdList(uidList);
			List<User> userList = socialDao.getUserListByUids(uidList);
			for (int i = 0; i < userMessageList.size(); i++) {
				UserMessage um = userMessageList.get(i);
				for (int j = 0; j < userList.size(); j++) {
					User user = userList.get(j);
					if (um.getFromUid() == user.getUid()) {
						um.setHeadIcon(user.getHeadIcon());
						um.setNicNname(user.getNickname());
						break;
					}
				}
			}
			for (int i = 0; i < replyUserMessageList.size(); i++) {
				UserMessage um = replyUserMessageList.get(i);
				for (int j = 0; j < userList.size(); j++) {
					User user = userList.get(j);
					if (um.getFromUid() == user.getUid()) {
						um.setHeadIcon(user.getHeadIcon());
						um.setNicNname(user.getNickname());
						break;
					}
				}
			}
			List<MessageEntry> messageEntryList = new ArrayList<MessageEntry>();
			for (int i = 0; i < userMessageList.size(); i++) {
				UserMessage um = userMessageList.get(i);
				MessageEntry entry = new MessageEntry(um);
				if (replyUserMessageList.size() > 0) {
					MessageListEntry replyListEntry = new MessageListEntry();
					List<MessageEntry> replyMmessageEntryList = new ArrayList<MessageEntry>();
					for (int j = 0; j < replyUserMessageList.size(); j++) {
						UserMessage replyUm = replyUserMessageList.get(j);
						if (replyUm.getReplyMessageId() == um.getMessagetId()) {
							MessageEntry replyEntry = new MessageEntry(replyUm);
							replyMmessageEntryList.add(
									replyMmessageEntryList.size(), replyEntry);
						}
					}
					replyListEntry.setMessage(replyMmessageEntryList);
					entry.setReplymessagelist(replyListEntry);
				}
				messageEntryList.add(i, entry);
			}
			result.setMessage(messageEntryList);
			if (info.getAllcountflag() == 1) {
				int allcount = socialDao.getUserMessageAllCountByUid(uid);
				result.setAllcount(allcount);
			}
		}
		return response;
	}

	public UserSnsShareResponse getsharesnsitem(GetSnsShareInfo info) {
		if(info.getShareid()<=0){
			throw new MusicRcException(MusicRcException.PARAMEMTERNULL);
		}
		UserSnsShareResponse response = new UserSnsShareResponse();
		AttentionUsersInfo attentionUserInfo = new AttentionUsersInfo();
		attentionUserInfo.setShareId(info.getShareid());
		List<ItemEntry> entryList = getUserShareListBySnsShareId(attentionUserInfo);
		if (entryList.size() > 0) {
			response.setSnsitem(entryList.get(0));
		}
		return response;
	}
}
