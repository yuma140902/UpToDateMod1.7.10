package yuma140902.uptodatemod.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	private HttpUtil() {}
	
	public static String getFromUrl(String urlStr) {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setInstanceFollowRedirects(true);
			conn.setRequestMethod("GET");
			conn.connect();
		
			is = conn.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
		
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line).append('\n');
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				is.close();
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			try {
				isr.close();
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			try {
				br.close();
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		return sb.toString();
	}
}
