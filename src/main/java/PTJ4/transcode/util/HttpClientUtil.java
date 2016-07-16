package PTJ4.transcode.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author vwings
 *
 */
public class HttpClientUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	private final static String DEFAULT_ENCODE = "UTF-8";

	public static String post(String url, Map<String, String> paramsMap) {
		HttpClient httpClient = new DefaultHttpClient();
		X509TrustManager trustManager = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		try {
			SSLContext sslContext = SSLContext.getInstance(SSLSocketFactory.TLS);
			sslContext.init(null, new TrustManager[] { trustManager }, new SecureRandom());
			SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext);
			// 此处的443端口可被指定的URL替代
			httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));

			HttpPost httpPost = new HttpPost(url);
			logger.debug("HttpPost URL={}", url);

			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			// parameters
			if (paramsMap != null) {
				for (String key : paramsMap.keySet()) {
					parameters.add(new BasicNameValuePair(key, paramsMap.get(key)));
				}
				logger.debug("NameValuePair={}", parameters.toString());
			}

			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, DEFAULT_ENCODE);
			httpPost.setEntity(entity);

			HttpResponse response = httpClient.execute(httpPost);

			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				String str = EntityUtils.toString(response.getEntity(), DEFAULT_ENCODE);
				logger.info("HttpEntity={}", str);
				return str;
			} else {
				logger.error("HTTP response ERROR and statusCode={}", statusCode);
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error("SSLContext.getInstance ERROR", e);
		} catch (KeyManagementException e) {
			logger.error("sslContext.init ERROR", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("new UrlEncodedFormEntity ERROR", e);
		} catch (IOException e) {
			logger.error("httpClient.execute ERROR", e);
		} finally {
			// 关闭连接 ,释放资源
			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}

	
	/**
	 * 从网络获取视频
	 * @param url
	 * @return
	 */
	public static int getVideo(String url,String tmpSavePath) {
		HttpClient httpClient = new DefaultHttpClient();
		X509TrustManager trustManager = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		try {
			SSLContext sslContext = SSLContext.getInstance(SSLSocketFactory.TLS);
			sslContext.init(null, new TrustManager[] { trustManager }, new SecureRandom());
			SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext);
			// 此处的443端口可被指定的URL替代
			httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));

			HttpGet httpGet = new HttpGet(url);
			logger.debug("HttGet URL={}", url);


			HttpResponse response = httpClient.execute(httpGet);

			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				
				InputStream is = response.getEntity().getContent();
				int size = is.available();
				FileOutputStream fos = new FileOutputStream(new File(tmpSavePath));
				int inByte;
				while((inByte = is.read()) != -1)
				     fos.write(inByte);
				is.close();
				fos.close();
				
//				String str = EntityUtils.toString(response.getEntity(), DEFAULT_ENCODE);
				
				return size;
			} else {
				logger.error("HTTP response ERROR and statusCode={}", statusCode);
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error("SSLContext.getInstance ERROR", e);
		} catch (KeyManagementException e) {
			logger.error("sslContext.init ERROR", e);
		}  catch (IOException e) {
			logger.error("httpClient.execute ERROR", e);
		} finally {
			// 关闭连接 ,释放资源
			httpClient.getConnectionManager().shutdown();
		}
		return 0;
	}
	
	
}
