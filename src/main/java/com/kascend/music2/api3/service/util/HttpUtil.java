package com.kascend.music2.api3.service.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.MultipartPostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kascend.common.util.MD5Digest;
import com.kascend.frameworkcommons.config.Configer;
import com.kascend.music2.api3.service.util.Constants;

@SuppressWarnings("deprecation")
public class HttpUtil {
	
	private static final Log log = LogFactory.getLog(HttpUtil.class);

	public static String getPhotoByHttp(String url, String destPath) {
		return downloadFile(url, destPath);
	}
	
	public static String downloadLyric(String uri, String charset){
		String lyric = ConnectionUtil.Connect(uri, null, charset);
		return lyric;
	}
	
	
	public static String downloadLyric(String uri,NameValuePair[] params, String charset){
		String lyric = ConnectionUtil.Connect(uri, params, charset);
		return lyric;
	}
	
	public static String downloadSongUri(String url, String destPath) {
		
		return downloadFile(url, destPath);
	}
	
	
	public static String downloadFile(String url, String destPath) {
		
		FileOutputStream fos = null;
		GetMethod getMethod=null;
		try {
			HttpClient httpClient = new HttpClient();
			
			HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams(); 
			managerParams.setConnectionTimeout(30000); 
			managerParams.setSoTimeout(120000); 
			
			getMethod = new GetMethod(url);
//			User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.4) Gecko/20100611 Firefox/3.6.4
			
			getMethod.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.4) Gecko/20100611 Firefox/3.6.4");
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, true));
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				destPath = null;
			}else{
				fos = new FileOutputStream(destPath);
				InputStream reader = getMethod.getResponseBodyAsStream();
				byte buf[]=new byte[1024];
				int size=0;
				while ((size=reader.read(buf))!=-1) {
					fos.write(buf, 0, size);
				}
			}

		} catch (Exception e) {
			destPath = null;
			log.error(e.getMessage(), e);
		} finally {
			if(getMethod!=null)
				getMethod.releaseConnection();
			try {
				if(fos != null){
					fos.flush();
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return destPath;
	}
	
	public static String downloadAlbumImgs(String url, String destPath) {
		
		return downloadFile(url, destPath);
	}

	
	public static int uploadFileToServer(String filePath, String savePath){
		MultipartPostMethod mPost = null;
		try {
			HttpClient client = new HttpClient();
	        mPost = new MultipartPostMethod(Configer.getValueString(Constants.FILE_UPLOAD_SERVICE));
	        mPost.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,	new DefaultHttpMethodRetryHandler(2, true));
	        File f1 = new File(filePath);
	        long t=new Date().getTime();
	        mPost.setRequestHeader("Cookie","t="+t +"; token="+MD5Digest.getInstance(Constants.FILE_UPLOAD_SERVICE_SECRET+t).toString());

	        mPost.addParameter("file", f1);
	        mPost.addParameter("path", savePath);

	        int statusCode = client.executeMethod(mPost);
			if (statusCode == HttpStatus.SC_OK) {
				String rt=new String(mPost.getResponseBody());
				return Integer.parseInt(rt);
				
			}else{
				return 3;
			}

		}catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			if(mPost!=null)
				mPost.releaseConnection();
		}
		return 4;

	}
	
	public static String formatURI(String uri){
		StringBuffer sb=new StringBuffer();
		 for(int i=0;i<uri.length();i++){
			 String ch=uri.substring(i,i+1);
             if(!ch.matches("[\\w:/\\.\\?\\&=%#@\\~\\$]+")){
                 try {
					sb.append(URLEncoder.encode(ch, "utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					return uri;
				}
             }else{  
                 sb.append(ch);  
             }       
         }
		 return sb.toString();
	}
	
}
