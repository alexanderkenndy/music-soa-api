package com.kascend.music2.api3.interceptor;

import com.kascend.interfaceframework.controller.Action;
import com.kascend.interfaceframework.controller.Interceptor;
import com.kascend.interfaceframework.controller.annotation.Condition;
import com.kascend.interfaceframework.controller.annotation.Conditions;
import com.kascend.music2.api3.exception.MusicRcException;
import com.kascend.music2.api3.service.util.Constants;

public class UserLoginValidateInterceptor implements Interceptor{
	@Override
	@Conditions( {
//			@Condition(service = "auth", method = "login,register,reset"),
			//3
			@Condition(service = "tp", method = "searchsongs"),
			@Condition(service = "tp", method = "searchalbums"),
			@Condition(service = "tp", method = "searchartists"),
			@Condition(service = "tp", method = "lookupsongs"),
			@Condition(service = "tp", method = "getsearchkeywords"),
			@Condition(service = "tp", method = "lookupmvs"),
			@Condition(service = "tp", method = "lookupgroupmvs"),
			@Condition(service = "tp", method = "lookupmtvs"),
			@Condition(service = "tp", method = "lookupgroupmtvs"),
			//4
			@Condition(service = "tp", method = "listbillboards"),
			@Condition(service = "tp", method = "topsongs"),
			@Condition(service = "tp", method = "topartists"),
			@Condition(service = "tp", method = "topalbums"),
			@Condition(service = "tp", method = "topmtvs"),
			@Condition(service = "tp", method = "topmvs"),
			//5
			@Condition(service = "tp", method = "listartists"),
			@Condition(service = "tp", method = "listmtvs"),
			@Condition(service = "tp", method = "listmvs"),
			//6
			@Condition(service = "tp", method = "getartistinfo"),
			@Condition(service = "tp", method = "getartistart"),
			@Condition(service = "tp", method = "getalbumsofartist"),
			@Condition(service = "tp", method = "getsongsofalbum"),
			@Condition(service = "tp", method = "getsongsofartist"),
			@Condition(service = "tp", method = "gethotsongsofartist"),
			@Condition(service = "tp", method = "getalbumsofsong"),
			@Condition(service = "tp", method = "getalbuminfo"),
			@Condition(service = "tp", method = "getalbumart"),
			@Condition(service = "tp", method = "getlyric"),
			@Condition(service = "tp", method = "getsonginfo"),
			@Condition(service = "tp", method = "getmtvinfo"),
			@Condition(service = "tp", method = "getmvinfo"),
			//7
			@Condition(service = "tp", method = "getsampleurilist"),
			@Condition(service = "tp", method = "getsongurilist"),
			@Condition(service = "tp", method = "getsampleuri"),
			@Condition(service = "tp", method = "getdownloaduri"),
			@Condition(service = "tp", method = "getmtvuri"),
			@Condition(service = "tp", method = "getmvuri"),
			//8
			@Condition(service = "tp", method = "recommendartists"),
			@Condition(service = "tp", method = "recommendalbums"),
			@Condition(service = "tp", method = "getsimilarsongs"),
			@Condition(service = "tp", method = "getrandomsongs"),
			//9
			@Condition(service = "so", method = "getcommentsofitem"),
			@Condition(service = "so", method = "getsnsitemsofartist"),
			@Condition(service = "so", method = "getuserfollowedartists"),
			@Condition(service = "so", method = "getuserfollowedusers"),
			@Condition(service = "so", method = "getuserfans"),
			@Condition(service = "so", method = "getitemsofuser"),
			@Condition(service = "so", method = "gettalentusers"),
			@Condition(service = "so", method = "gettalentinfo"),
			@Condition(service = "so", method = "getplaylistsofuser"),
			@Condition(service = "so", method = "getsongsofplaylist"),
			@Condition(service = "so", method = "getmvsofplaylist"),
			@Condition(service = "so", method = "getuserrecentlyplayed"),
			@Condition(service = "so", method = "getusermostplayed"),
			@Condition(service = "so", method = "getuserlikesongs"),
			@Condition(service = "so", method = "getuserartists"),
			@Condition(service = "so", method = "getuseralbumsofartist"),
			@Condition(service = "so", method = "getuseralbums"),
			@Condition(service = "so", method = "getusersongsofalbum"),
			@Condition(service = "so", method = "listusers"),
			@Condition(service = "so", method = "discoverysongs"),
			@Condition(service = "so", method = "discoveryalbumofsong"),
			
			
			@Condition(service = "so", method = "boundweiboaccount"),
			@Condition(service = "so", method = "removeweiboaccount"),
			@Condition(service = "so", method = "snsgetuserrelationlist"),
			
			
			@Condition(service = "so", method = "getlastsyncplaylisttime"),
			@Condition(service = "so", method = "getleavemessages"),
			@Condition(service = "so", method = "recommendusers"),
			@Condition(service = "so", method = "searchusers"),
			@Condition(service = "so", method = "getsharesnsitem"),
			
			@Condition(service = "tp", method = "jlucenesearchsongs"),
			@Condition(service = "tp", method = "jlucenesearchkeywords"),
			@Condition(service = "tp", method = "jlucenesearchartists"),
			@Condition(service = "tp", method = "jlucenesearchalbums"),
			
			//10
			@Condition(service = "tp", method = "uploadmetadatas"),
			@Condition(service = "tp", method = "stat"),
			@Condition(service = "tp", method = "feedbacksong"),
			@Condition(service = "tp", method = "statclientusage")
			})
	
	public void before(Action a) {
		int userStatus=-1;
		try{
			if (a.getParameters().get("userstatus") != null
					&& a.getParameters().get("userstatus").length > 0) {
				userStatus = Integer.parseInt(a.getParameters().get("userstatus")[0].toString());
			}
		}catch(Exception e){
			
		}
		if (userStatus!=Constants.USER_STATUS_INACTIVE&&userStatus!=Constants.USER_STATUS_NORMAL) {
			throw new MusicRcException(MusicRcException.LOGIN_OR_PASSWORD_ERROR);
		}
		
	}
	
	@Override
	public void after(Action arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
