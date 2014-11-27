package com.kascend.music2.api3.web.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import com.kascend.common.util.MD5Digest;
import com.kascend.common.util.XmlUtil;
import com.kascend.music2.api3.service.metadata.response.New_AlbumInfoResponse;
import com.kascend.music2.api3.service.metadata.response.New_AlbumResponse;
import com.kascend.music2.api3.service.metadata.response.New_ArtistInfoResponse;
import com.kascend.music2.api3.service.metadata.response.New_ArtistListResponse;
import com.kascend.music2.api3.service.metadata.response.New_BillboardListResponse;
import com.kascend.music2.api3.service.metadata.response.New_DownloadUriResponse;
import com.kascend.music2.api3.service.metadata.response.New_SongListResponse;
import com.kascend.music2.api3.service.util.ServiceUtils;

public class MusicWebServer implements java.io.Serializable{
	static HttpClient httpClient = null;
	private static String urlConfig = "";
	private String appKey;
	private String secret;

	private static final String PARAM_APPSIG = "appsig";// 参数appsig
	private static final String PARAM_APPKEY = "appkey";// 参数appkey
	private static final String PARAM_METHOD = "method";// 参数method
	private static final String RETURNVALUE = "returnValue";
	static {
		try {
			String properPath = getAppPath(MusicWebServer.class);
			InputStream inUrl;
			inUrl = new BufferedInputStream(new FileInputStream(properPath
					+ "/urlconfig.properties"));
			Properties pUrl = new Properties();
			pUrl.load(inUrl);
			urlConfig = pUrl.getProperty("postMusicURL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param appKey
	 * @param secret
	 * @throws Exception
	 */
	public MusicWebServer(String appKey, String secret) throws Exception {
		if (appKey == null || secret == null)
			throw new Exception("AppKey is invalid!" + ",appKey:" + appKey
					+ ",secret:" + secret);
		this.appKey = appKey;
		this.secret = secret;
		String serverSecret = "";
		// 初始化httpclient，多线程的安全性
		MultiThreadedHttpConnectionManager multiHttpConnManager = new MultiThreadedHttpConnectionManager();
		multiHttpConnManager.closeIdleConnections(30000);
		multiHttpConnManager.deleteClosedConnections();

		HttpConnectionManagerParams managerParams = new HttpConnectionManagerParams();
		managerParams.setConnectionTimeout(30000); // 设置连接超时时间(单位毫秒)
		managerParams.setSoTimeout(120000); // 设置读数据超时时间(单位毫秒)
		multiHttpConnManager.setParams(managerParams);
		httpClient = new HttpClient(multiHttpConnManager);

	}

	private static String getAppPath(Class cls) {
		// 检查用户传入的参数是否为空
		if (cls == null)
			throw new java.lang.IllegalArgumentException("参数不能为空！");
		ClassLoader loader = cls.getClassLoader();
		// 获得类的全名，包括包名
		String clsName = cls.getName() + ".class";
		// 获得传入参数所在的包
		Package pack = cls.getPackage();
		String path = "";
		// 如果不是匿名包，将包名转化为路径
		if (pack != null) {
			String packName = pack.getName();
			// 此处简单判定是否是Java基础类库，防止用户传入JDK内置的类库
			if (packName.startsWith("java.") || packName.startsWith("javax."))
				throw new java.lang.IllegalArgumentException("不要传送系统类！");
			// 在类的名称中，去掉包名的部分，获得类的文件名
			clsName = clsName.substring(packName.length() + 1);
			// 判定包名是否是简单包名，如果是，则直接将包名转换为路径，
			if (packName.indexOf(".") < 0)
				path = packName + "/";
			else {// 否则按照包名的组成部分，将包名转换为路径
				int start = 0, end = 0;
				end = packName.indexOf(".");
				while (end != -1) {
					path = path + packName.substring(start, end) + "/";
					start = end + 1;
					end = packName.indexOf(".", start);
				}
				path = path + packName.substring(start) + "/";
			}
		}
		// 调用ClassLoader的getResource方法，传入包含路径信息的类文件名
		java.net.URL url = loader.getResource(path + clsName);
		// 从URL对象中获取路径信息
		String realPath = url.getPath();
		// 去掉路径信息中的协议名"file:"
		int pos = realPath.indexOf("file:");
		if (pos > -1)
			realPath = realPath.substring(pos + 5);
		// 去掉路径信息最后包含类文件信息的部分，得到类所在的路径
		pos = realPath.indexOf(path + clsName);
		realPath = realPath.substring(0, pos - 1);
		// 如果类文件被打包到JAR等文件中时，去掉对应的JAR等打包文件名
		if (realPath.endsWith("!"))
			realPath = realPath.substring(0, realPath.lastIndexOf("/"));
		try {
			realPath = java.net.URLDecoder.decode(realPath, "utf-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return realPath;
	}// getAppPath定义结束

	public final static boolean isBlank(String str) {
		return StringUtils.isBlank(str);
	}

	private String getAppsig(String url) {
		String[] args =ServiceUtils.splitStrArray(url, "&");
		Map<String, String> map = new HashMap<String, String>();
		for (String par : args) {
			String[] m =ServiceUtils.splitStrArray(par, "=");
			map.put(m[0], m[1]);
		}

		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
		StringBuffer sb = new StringBuffer();
		for (String key : keys) {
			if (!key.equals(PARAM_APPSIG) && !key.equals(RETURNVALUE)) {
				sb.append("&");
				sb.append(key);
				sb.append("=");
				sb.append(map.get(key));
			}
		}
		String appsig = sb.toString().replaceFirst("\\&", "");
		// md5
		String s = secret + "&" + appsig;
		MD5Digest md5 = MD5Digest.getInstance(s);
		return md5.toString();
	}

	private Serializable excutePost(MusicWebInfo info, String postMethod,
			Serializable response) throws Exception {

		StringBuffer urlSb = encryptURL(info, postMethod);// 参用户传入的参数进行加密
		GetMethod getMethod = new GetMethod(urlSb.toString());// 创建GET方法的实例,
		// 可同时对请求的头
		// ,等等，做相应的设置
		String resContent = null;
		Serializable retRes = response;

		try {
			// 使用系统提供的默认的恢复策略
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			int statusCode = httpClient.executeMethod(getMethod);// 执行getMethod,
			// 返回响应码
			if (statusCode != HttpStatus.SC_OK) {

			}
			resContent = convertStreamToString(getMethod
					.getResponseBodyAsStream());
			resContent = resContent.replaceAll("[\\s*]+", " ");
			// ParseResContent(retRes, resContent);// 解析返回结果
			JAXBContext context = JAXBContext.newInstance(response.getClass());
			Unmarshaller um = context.createUnmarshaller();
			retRes = (Serializable) um.unmarshal(new ByteArrayInputStream(
					resContent.getBytes("UTF-8")));
			retRes = XmlUtil.toEntity(response.getClass(),
					new ByteArrayInputStream(resContent.getBytes("UTF-8")));

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();// 释放连接
		}
		return retRes;
	}

	private static String convertStreamToString(InputStream is)
			throws UnsupportedEncodingException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				"UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	private StringBuffer encryptURL(MusicWebInfo inputUser, String postMethod)
			throws Exception {
		if (inputUser == null) {// 入口参数为空
			throw new Exception("input parameter is null!");
		}
		if (postMethod == null || ("".equalsIgnoreCase(postMethod))) {// 请求方法为空
			throw new Exception("postMethod is null!");
		}
		// 将传入的参数放入map后，可以a-z的顺序进行重新排序处理
		Map<String, String> objectMap = BeanUtils.describe(inputUser);
		objectMap.put(PARAM_METHOD, postMethod);
		objectMap.put(PARAM_APPKEY, this.appKey);

		// 对参数进行加密
		StringBuffer paramsSb = getParamStr(objectMap);
		String clearStr = paramsSb.toString().replaceFirst("\\&", "");
		String totalParamStr = this.secret + "&" + clearStr;// 加入了参数的加密串
		// 组成新的请求串
		StringBuffer urlSb = new StringBuffer(urlConfig);// 参数序列
		String url = urlSb.append("?").append(clearStr).toString();
		String appkey = getAppsig(clearStr);
		urlSb.append("&").append(PARAM_APPSIG).append("=").append(appkey);
		return urlSb;
	}
	private StringBuffer getParamStr(Map<String, String> params) {
		StringBuffer sb = new StringBuffer();
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
		// 对参数去掉appsig和returnvalue键值后，生成新参数串，用于后面md5校对
		for (String key : keys) {
			if (!key.equals("class") && !key.equals(PARAM_APPSIG)
					&& (params.get(key) != null)) {
				sb.append("&");
				sb.append(key);
				sb.append("=");
				sb.append(params.get(key));
			}
		}
		return sb;
	}

	private static void excutePostXML(String url, String xmlContent) {
		PostMethod post = new PostMethod(url);
		// 使用StringRequestEntity POST XML
		try {
			// 初始化httpclient，多线程的安全性
			MultiThreadedHttpConnectionManager multiHttpConnManager = new MultiThreadedHttpConnectionManager();
			multiHttpConnManager.closeIdleConnections(30000);
			multiHttpConnManager.deleteClosedConnections();
			HttpConnectionManagerParams managerParams = new HttpConnectionManagerParams();
			managerParams.setConnectionTimeout(30000); // 设置连接超时时间(单位毫秒)
			managerParams.setSoTimeout(120000); // 设置读数据超时时间(单位毫秒)
			multiHttpConnManager.setParams(managerParams);
			HttpClient httpPostClient = new HttpClient(multiHttpConnManager);
			httpPostClient.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			post.addParameter("mobileDevice", xmlContent);
			// 使用系统提供的默认的恢复策略
			post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			int statusCode = httpPostClient.executeMethod(post);// 执行getMethod,
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("post xml data failed!");
			}
			String resContent = convertStreamToString(post
					.getResponseBodyAsStream());
			System.out.println(resContent);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Serializable executeListBillBoards(MusicWebInfo info)
			throws Exception {
		New_BillboardListResponse response = new New_BillboardListResponse();
		return excutePost(info, "tp.listbillboards", response);
	}

	public Serializable executeTopsongs(MusicWebInfo info) throws Exception {
		New_SongListResponse response = new New_SongListResponse();
		return excutePost(info, "tp.topsongs", response);
	}

	public Serializable executeTopAlbum(MusicWebInfo info) throws Exception {
		New_AlbumResponse response = new New_AlbumResponse();
		return excutePost(info, "tp.topalbums", response);
	}

	public Serializable executeTopArtists(MusicWebInfo info) throws Exception {
		New_ArtistListResponse response = new New_ArtistListResponse();
		return excutePost(info, "tp.topartists", response);
	}

	public Serializable executeGetAlbumsofArtist(MusicWebInfo info)
			throws Exception {
		New_AlbumResponse response = new New_AlbumResponse();
		return excutePost(info, "tp.getalbumsofartist", response);
	}

	public Serializable executeGetSongsofAlbum(MusicWebInfo info)
			throws Exception {
		New_SongListResponse response = new New_SongListResponse();
		return excutePost(info, "tp.getsongsofalbum", response);
	}

	public Serializable executeGetSongUrl(MusicWebInfo info) throws Exception {
		New_DownloadUriResponse response = new New_DownloadUriResponse();
		return excutePost(info, "tp.getdownloaduri", response);
	}

	public Serializable executeSearchSongs(MusicWebInfo info) throws Exception {
		New_SongListResponse response = new New_SongListResponse();
		if (info.getKeyname() != null) {
			try {
				String keyname = info.getKeyname();
				keyname = URLEncoder.encode(keyname, "utf-8");
				info.setKeyname(keyname);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return excutePost(info, "tp.searchsongs", response);
	}

	public Serializable executeGetArtistInfo(MusicWebInfo info)
			throws Exception {
		New_ArtistInfoResponse response = new New_ArtistInfoResponse();
		return excutePost(info, "tp.getartistinfo", response);

	}

	public Serializable executeGetAlbumInfo(MusicWebInfo info) throws Exception {
		New_AlbumInfoResponse response = new New_AlbumInfoResponse();
		return excutePost(info, "tp.getalbuminfo", response);

	}

}