package com.kascend.music2.api3.service.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class ConnectionUtil {
	
	public static String Connect(String url) {
		return Connect(url, null, null);
	}
	public static String Connect(String url, String charset) {
		return Connect(url, null, charset);
	}
	
	public static String Connect(String url, NameValuePair[] params, String charset) {
		GetMethod method=null;
		String responseText=null;
		try {
			HttpClient httpClient = new HttpClient();
			httpClient
			.getParams()
			.setParameter(
					HttpMethodParams.USER_AGENT,
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6 (.NET CLR 3.5.30729)");
			HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams(); 
			managerParams.setConnectionTimeout(30000); 
			managerParams.setSoTimeout(120000); 
			method = new GetMethod(HttpUtil.formatURI(url));
			//method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,120000);  
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,	new DefaultHttpMethodRetryHandler(2, true));
			
			if(params!=null)
			{
				for(int i=0;i<params.length;i++)
				{
					method.setRequestHeader(params[i].getName(), params[i].getValue());
				}
			}
			StringBuffer response = new StringBuffer();
			
			int statusCode = httpClient.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
				if(charset==null){
					charset=method.getResponseCharSet();
				}
				InputStreamReader reader = new InputStreamReader(method.getResponseBodyAsStream(), charset);
				char buf[]=new char[1024];
				int size=0;
				while ((size=reader.read(buf))!=-1) {
					response.append(new String(buf, 0, size));
				}
				reader.close();
			}
			responseText=response.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(method!=null)
				method.releaseConnection();
		}
		return responseText;
	}
	/**
	 * @param url
	 * @param params
	 * @param charset
	 * @return false : uri invalid
	 * 			true: available
	 */
	public static boolean checkUri(String url, NameValuePair[] params) {
		GetMethod method=null;
		try {
			HttpClient httpClient = new HttpClient();
			httpClient
			.getParams()
			.setParameter(
					HttpMethodParams.USER_AGENT,
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6 (.NET CLR 3.5.30729)");
			HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams(); 
			managerParams.setConnectionTimeout(30000); 
			managerParams.setSoTimeout(30000); 
			method = new GetMethod(HttpUtil.formatURI(url));
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,	new DefaultHttpMethodRetryHandler(3, true));
			
			if(params!=null)
			{
				for(int i=0;i<params.length;i++)
				{
					method.setRequestHeader(params[i].getName(), params[i].getValue());
				}
			}
			int statusCode = httpClient.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
				Header header=method.getResponseHeader("Content-Type");
				Header contentLengthHeader=method.getResponseHeader("Content-Length");
				if(header!=null){
					String contentType=header.getValue();
					if(contentType.indexOf("text/html")!=-1){
						return false;
					}
				}
				if(contentLengthHeader!=null){
					String value=contentLengthHeader.getValue();
					//it is invalid if length less than 500KB 
					if(Long.parseLong(value)<500*1024){
						return false;
					}
				}
				method.abort();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(method!=null)
				method.releaseConnection();
		}
		return false;
	}
	
	public static String VideoConnect(String url)
	 {		
			StringBuilder log = new StringBuilder();
			HttpClient httpClient = new HttpClient();
			httpClient
			.getParams()
			.setParameter(
					HttpMethodParams.USER_AGENT,
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6 (.NET CLR 3.5.30729)");
			HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams(); 
			managerParams.setConnectionTimeout(30000); 
			managerParams.setSoTimeout(120000); 
			int statusCode = 0;
			log.append("::::::VideoConnection::URL=" + url);
			String url2 = url;
			do
			{
				GetMethod2 method = new GetMethod2(url2);
				try { 
					statusCode = httpClient.executeMethod(method);
					log.append("statusCode="+ statusCode);
					if(statusCode>=HttpStatus.SC_MULTIPLE_CHOICES && statusCode<=HttpStatus.SC_TEMPORARY_REDIRECT)
					{
						Header header = method.getResponseHeader("location");
						url2 = header.getValue();
						log.append(",url="+ url2 + "\r\n");
						
					}
				} catch (Exception e) {
					System.out.println(log.toString());
					throw new RuntimeException(e);
				} 
				finally
				{
					method.abort();
				}
			}
			while(statusCode>=HttpStatus.SC_MULTIPLE_CHOICES && statusCode<=HttpStatus.SC_TEMPORARY_REDIRECT);
			if(url2.equals(url)){
				url2 = url;
				System.out.println(log.toString());
			}
			return url2;
		}
	
	private static class GetMethod2 extends GetMethod{
		
		public GetMethod2(String uri){
			super(uri);
			getParams().setParameter(HttpMethodParams.SO_TIMEOUT,120000);  
			getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, true));
			setFollowRedirects(false);
		}
		@Override
		public boolean getFollowRedirects() {
			return false;
		}
		
	}
	
	public static URLConnection getURLConnection(String uri) throws MalformedURLException, IOException{
		URLConnection urlConn=new URL(uri).openConnection();
		urlConn.setConnectTimeout(120000);
		urlConn.setReadTimeout(120000);
		urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6 (.NET CLR 3.5.30729)");
		return urlConn;
	}
}
