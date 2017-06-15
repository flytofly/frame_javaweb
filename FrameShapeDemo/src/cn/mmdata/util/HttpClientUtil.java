package cn.mmdata.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

/**
 * @author administrator
 * 
 *         Http Client工具类 发起http client 请求
 * 
 *         窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public final class HttpClientUtil {

	private static HttpClientUtil instance;
	
	// 读取超时,单位毫秒
	private static int soTimeout=300000;
	
	// 连接超时,单位毫秒
	private static int connectionTimeout=30000;
	
	private HttpClientUtil() {
	}

	/**
	 * 使用默认的页面请求编码：utf-8
	 * 
	 * @return
	 */
	public static HttpClientUtil getInstance() {
		return getInstance("UTF-8");
	}

	public static HttpClientUtil getInstance(String urlCharset) {
		if (instance == null) {
			instance = new HttpClientUtil();
		}
		// 设置默认的url编码
		instance.setUrlCharset(urlCharset);
		return instance;
	}

	/**
	 * 请求编码，默认使用utf-8
	 */
	private String urlCharset = "UTF-8";

	/**
	 * @param urlCharset
	 *            要设置的 urlCharset。
	 */
	public void setUrlCharset(String urlCharset) {
		this.urlCharset = urlCharset;
	}

	/**
	 * 利用http client模拟发送http post请求
	 * 
	 * @param targetUrl
	 *            请求地址
	 * @param params
	 *            请求参数<paramName,paramValue>
	 * @return Object 返回的类型可能是：String 或者 byte[] 或者 outputStream
	 * @throws Exception
	 */
	public boolean setGetRequest(String targetUrl, Map params) {
		boolean result = false;
		if (StringUtils.isBlank(targetUrl)) {
			throw new IllegalArgumentException(
					"调用HttpClientUtil.setGetRequest方法，targetUrl不能为空!");
		}
		HttpClient client = null;
		GetMethod get = null;
		SimpleHttpConnectionManager connectionManager = null;
		try {
			connectionManager = new SimpleHttpConnectionManager();
			// 连接超时,单位毫秒
			connectionManager.getParams().setConnectionTimeout(connectionTimeout);
			// 读取超时,单位毫秒
			connectionManager.getParams().setSoTimeout(soTimeout);
			// 设置获取内容编码
			connectionManager.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, urlCharset);
			client = new HttpClient(new HttpClientParams(), connectionManager);
			get = new GetMethod(targetUrl);
			// 设置请求参数的编码
			get.getParams().setContentCharset(urlCharset);
			// 服务端完成返回后，主动关闭链接
			get.setRequestHeader("Connection", "close");
			int sendStatus = client.executeMethod(get);
			if (sendStatus == HttpStatus.SC_OK) {
				result = true;
			} else {
				System.err.println("***************************");
				System.err.println("HttpClientUtil.setGetRequest()-请求url："
						+ targetUrl + " 出错\n请求参数有：" + params + "！！！");
				System.err.println("***************************");
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放链接
			if (get != null) {
				get.releaseConnection();
			}
			// 关闭链接
			if (connectionManager != null) {
				// connectionManager.shutdown();
			}
		}

		return result;
	}

}