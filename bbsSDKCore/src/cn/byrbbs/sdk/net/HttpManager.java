package cn.byrbbs.sdk.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import android.graphics.Bitmap;

import cn.byrbbs.sdk.exception.BBSException;
import cn.byrbbs.sdk.exception.BBSHttpException;

class HttpManager
{
	private static final String BOUNDARY = getBoundry();
	private static final String MP_BOUNDARY = "--" + BOUNDARY;
	private static final String END_MP_BOUNDARY = "--" + BOUNDARY + "--";
	private static final String MULTIPART_FORM_DATA = "multipart/form-data";
	
	private static final String HTTP_METHOD_POST = "POST";
	private static final String HTTP_METHOD_GET = "GET";

	private static final int CONNECTION_TIMEOUT = 5000;
	private static final int SOCKET_TIMEOUT = 20000;
	private static final int BUFFER_SIZE = 8192;
	private static org.apache.http.conn.ssl.SSLSocketFactory sSSLSocketFactory;

	public static String openUrl(String url, String method, BBSParameters params) 
				throws BBSException, BBSHttpException {
		HttpResponse response = requestHttpExecute(url, method, params);
		return readRsponse(response);
	}

	private static HttpResponse requestHttpExecute(String url, String method, BBSParameters params) 
			throws BBSHttpException {
		HttpResponse response = null;
		try {
			HttpClient client = getNewHttpClient();
			client.getParams().setParameter("http.route.default-proxy", NetStateManager.getAPN());

			HttpUriRequest request = null;
			ByteArrayOutputStream baos = null;

			// select HTTP method
			if (method.equals("GET")) {
				url = url + "?" + params.encodeUrl();
				request = new HttpGet(url);
			} else if (method.equals("POST")) {
				HttpPost post = new HttpPost(url);
				request = post;

				baos = new ByteArrayOutputStream();
				if (params.hasBinaryData()) {
					post.setHeader("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
					buildParams(baos, params);
				} else {
					Object value = params.get("content-type");
					if ((value != null) && ((value instanceof String))) {
						params.remove("content-type");
						post.setHeader("Content-Type", (String)value);
					} else {
						post.setHeader("Content-Type", "application/x-www-form-urlencoded");
					}

					String postParam = params.encodeUrl();
					baos.write(postParam.getBytes("UTF-8"));
				}
				post.setEntity(new ByteArrayEntity(baos.toByteArray()));
				baos.close();
			} else if (method.equals("DELETE")) {
				request = new HttpDelete(url);
			}

			response = client.execute(request);
			StatusLine status = response.getStatusLine();
			int statusCode = status.getStatusCode();

			// TODO
			if (statusCode != 200) {
				String result = readRsponse(response);
				throw new BBSHttpException(result, statusCode);
			}
		} catch (IOException e) {
			throw new BBSException(e);
		}

		return response;
	}

	private static HttpClient getNewHttpClient() {
		try {
			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, "UTF-8");

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			registry.register(new Scheme("https", getSSLSocketFactory(), 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
			HttpConnectionParams.setConnectionTimeout(params, 5000);
			HttpConnectionParams.setSoTimeout(params, 20000);
			return new DefaultHttpClient(ccm, params);
		} catch (Exception e) { }
		return new DefaultHttpClient();
	}

	private static void buildParams(OutputStream baos, BBSParameters params)
			throws BBSException {
		try {
			Set<String> keys = params.keySet();
			
			for (String key : keys) {
				Object value = params.get(key);
				if ((value instanceof String)) {
					StringBuilder sb = new StringBuilder(100);
					sb.setLength(0);
					sb.append(MP_BOUNDARY).append("\r\n");
					sb.append("content-disposition: form-data; name=\"").append(key).append("\"\r\n\r\n");
					sb.append(params.get(key)).append("\r\n");

					baos.write(sb.toString().getBytes());
				}

			}

			for (String key : keys) {
				Object value = params.get(key);
				if ((value instanceof Bitmap)) {
					StringBuilder sb = new StringBuilder();
					sb.append(MP_BOUNDARY).append("\r\n");
					sb.append("content-disposition: form-data; name=\"").append(key).append("\"; filename=\"file\"\r\n");
					sb.append("Content-Type: application/octet-stream; charset=utf-8\r\n\r\n");
					baos.write(sb.toString().getBytes());

					Bitmap bmp = (Bitmap)value;
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
					byte[] bytes = stream.toByteArray();

					baos.write(bytes);
					baos.write("\r\n".getBytes());
				} else if ((value instanceof ByteArrayOutputStream)) {
					StringBuilder sb = new StringBuilder();
					sb.append(MP_BOUNDARY).append("\r\n");
					sb.append("content-disposition: form-data; name=\"").append(key).append("\"; filename=\"file\"\r\n");
					sb.append("Content-Type: application/octet-stream; charset=utf-8\r\n\r\n");
					baos.write(sb.toString().getBytes());

					ByteArrayOutputStream stream = (ByteArrayOutputStream)value;
					baos.write(stream.toByteArray());
					baos.write("\r\n".getBytes());
					stream.close();
				}
			}
			baos.write(("\r\n" + END_MP_BOUNDARY).getBytes());
		} catch (IOException e) {
			throw new BBSException(e);
		}
	}

	private static String readRsponse(HttpResponse response)
			throws BBSException {
		if (response == null) {
			return null;
		}

		HttpEntity entity = response.getEntity();
		InputStream inputStream = null;
		ByteArrayOutputStream content = new ByteArrayOutputStream();
		try {
			inputStream = entity.getContent();
			Header header = response.getFirstHeader("Content-Encoding");
			if ((header != null) && (header.getValue().toLowerCase().indexOf("gzip") > -1)) {
				inputStream = new GZIPInputStream(inputStream);
			}

			int readBytes = 0;
			byte[] buffer = new byte[8192];
			while ((readBytes = inputStream.read(buffer)) != -1) {
				content.write(buffer, 0, readBytes);
			}

			return new String(content.toByteArray(), "UTF-8");
		} catch (IOException e) {
			throw new BBSException(e);
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	private static String getBoundry() {
		StringBuffer sb = new StringBuffer();
		for (int t = 1; t < 12; t++) {
			long time = System.currentTimeMillis() + t;
			if (time % 3L == 0L)
				sb.append((char)(int)time % '\t');
			else if (time % 3L == 1L)
				sb.append((char)(int)(65L + time % 26L));
			else {
				sb.append((char)(int)(97L + time % 26L));
			}
		}
		return sb.toString();
	}

	private static org.apache.http.conn.ssl.SSLSocketFactory getSSLSocketFactory() {
		if (sSSLSocketFactory == null) {
			InputStream caInput = null;
			try
			{
				caInput = HttpManager.class.getResourceAsStream("cacert.cer");

				String keyStoreType = KeyStore.getDefaultType();
				KeyStore keyStore = KeyStore.getInstance(keyStoreType);
				keyStore.load(caInput, null);
				sSSLSocketFactory = new MySSLSocketFactory(keyStore);
			} catch (Exception e) {
				e.printStackTrace();

				sSSLSocketFactory = MySSLSocketFactory.getSocketFactory();

				if (caInput != null)
					try {
						caInput.close();
					}
				catch (IOException localIOException) { }
			}
			finally {
				if (caInput != null)
					try {
						caInput.close();
					} catch (IOException localIOException1) { }
			}
		}
		return sSSLSocketFactory;
	}


	private static class MySSLSocketFactory extends org.apache.http.conn.ssl.SSLSocketFactory {
		SSLContext sslContext = SSLContext.getInstance("TLS");

		public MySSLSocketFactory(KeyStore truststore) 
				throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
			super(truststore);

			TrustManager[] tms = createTrustManagers(truststore);

			this.sslContext.init(null, tms, null);
		}

		private TrustManager[] createTrustManagers(KeyStore keystore) throws KeyStoreException, NoSuchAlgorithmException {
			if (keystore == null) {
				throw new IllegalArgumentException("Keystore may not be null");
			}
			TrustManagerFactory tmfactory = TrustManagerFactory.getInstance(
					TrustManagerFactory.getDefaultAlgorithm());
			tmfactory.init(keystore);
			return tmfactory.getTrustManagers();
		}

		public Socket createSocket(Socket socket, String host, int port, boolean autoClose)
				throws IOException, UnknownHostException {
			return this.sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
				}

		public Socket createSocket() throws IOException {
			return this.sslContext.getSocketFactory().createSocket();
		}
	} // private class
}// class end