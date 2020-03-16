package yuma140902.yumalib_ee.api.update;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.config.ModConfigCore;

public class TsvUpdateChecker implements IUpdateChecker {
	public static final String LATEST_STR = "latest";
	public static final String RECOMMENDED_STR = "recommended";
	
	//public String config_updateChannel = RECOMMENDED_STR;
	//public boolean config_doCheckUpdate = true;
	
	public String modName;
	public String homePageUrl;
	public String versionsTsvUrl;
	
	public String updateChannel;
	
	public String currentVersion;
	public String availableNewVersion;
	public HashMap<String, String> versions = null;
	
	public TsvUpdateChecker(String modName, String homePageUrl, String versionsTsvUrl, String currentVersion, String updateChannel) {
		this.modName = modName;
		this.homePageUrl = homePageUrl;
		this.versionsTsvUrl = versionsTsvUrl;
		this.currentVersion = currentVersion;
		this.availableNewVersion = currentVersion;
		this.updateChannel = updateChannel;
	}
	
	private static String getFromUrl(String urlStr) {
		boolean hasError = false;
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
			isr = new InputStreamReader(is, "UTF8");
			br = new BufferedReader(isr);
		
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line).append('\n');
			}
		}
		catch(RuntimeException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			hasError = true;
		}
		finally {
			try {
				is.close();
			}
			catch (Exception e) {
				e.printStackTrace();
				hasError = true;
			}
			try {
				isr.close();
			}
			catch (Exception e) {
				e.printStackTrace();
				hasError = true;
			}
			try {
				br.close();
			}
			catch (Exception e) {
				e.printStackTrace();
				hasError = true;
			}
		}
		
		return hasError ? null : sb.toString();
	}
	
	
	private static HashMap<String, String> getVersionsTable(String tsv){
		HashMap<String, String> hashMap = new HashMap<>();
		
		if(tsv == null || tsv.isEmpty()) return hashMap;
		
		String[] lines = tsv.split("[\\n\\r]");
		for (String line : lines) {
			String[] tmp = line.split("\\t");
			if(tmp.length < 2) continue;
			hashMap.put(tmp[0], tmp[1]);
		}
		
		return hashMap;
	}
	
	@Override
	public void checkForUpdates() {
		String versionsTsv = getFromUrl(this.versionsTsvUrl);
		if(versionsTsv == null || versionsTsv.isEmpty()) return;
		
		if(ModConfigCore.debug_mode) {
			ModUpToDateMod.LOGGER.info("versionsTsv:");
			ModUpToDateMod.LOGGER.info(versionsTsv);
		}
		
		this.versions = getVersionsTable(versionsTsv);
		
		String newestVersionStr = LATEST_STR.equals(this.updateChannel) ? LATEST_STR : RECOMMENDED_STR;
		
		if(versions.keySet().contains(newestVersionStr)) {
			String newestVersion = versions.get(newestVersionStr);
			if(Version3.isLaterThan(newestVersion, currentVersion)) {
				this.availableNewVersion = newestVersion;
				return;
			}
		}
	}
	
	public boolean hasNewVersionAvailable() {
		return Version3.isLaterThan(availableNewVersion, currentVersion);
	}
	
	public String getNewVersionUrl() {
		if(versions == null) return homePageUrl;
		
		if(versions.keySet().contains(availableNewVersion)) {
			return versions.get(availableNewVersion);
		}
		else return homePageUrl;
	}

	@Override
	public String getAvailableNewVersion() {
		return this.availableNewVersion;
	}
	
	@Override
	public String getModName() {
		return this.modName;
	}
}
