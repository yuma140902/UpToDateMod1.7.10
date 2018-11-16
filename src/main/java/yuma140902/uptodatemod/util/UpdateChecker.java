package yuma140902.uptodatemod.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import yuma140902.uptodatemod.ModUpToDateMod;

public class UpdateChecker {
	private UpdateChecker() {}
	
	public static final UpdateChecker INSTANCE = new UpdateChecker();
	
	public Version3 currentVersion = Version3.FromString(ModUpToDateMod.MOD_VERSION);
	public Version3 availableNewVersion = Version3.FromString(ModUpToDateMod.MOD_VERSION);
	public HashMap<Version3, String> versions = null;
	
	private static String getFromUrl(String urlStr) {
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
	
	
	private static HashMap<Version3, String> getVersionsTable(String tsv){
		HashMap<Version3, String> hashMap = new HashMap<>();
		
		if(tsv == null || tsv.isEmpty()) return hashMap;
		
		String[] lines = tsv.split("[\\n\\r]");
		for (String line : lines) {
			String[] tmp = line.split("\\t");
			if(tmp.length < 2) continue;
			Version3 version = Version3.FromString(tmp[0]);
			hashMap.put(version, tmp[1]);
		}
		
		return hashMap;
	}
	
	public void checkForUpdates() {
		String versionsTsv = getFromUrl(ModUpToDateMod.MOD_VERSIONS_TSV_URL);
		if(versionsTsv == null || versionsTsv.isEmpty()) return;
		
		System.out.println("versionsTsv:");
		System.out.print(versionsTsv);
		
		this.versions = getVersionsTable(versionsTsv);
		
		if(versions.keySet().contains(Version3.RECOMMENDED)) {
			Version3 recommendedVersion = Version3.FromString(versions.get(Version3.RECOMMENDED));
			if(recommendedVersion.isLaterThan(currentVersion)) {
				this.availableNewVersion = recommendedVersion;
				return;
			}
		}
		
		if(versions.keySet().contains(Version3.LATEST)) {
			Version3 latestVersion = Version3.FromString(versions.get(Version3.LATEST));
			if(latestVersion.isLaterThan(currentVersion)) {
				this.availableNewVersion = latestVersion;
				return;
			}
		}
	}
	
	public boolean hasNewVersionAvailable() {
		return availableNewVersion.isLaterThan(currentVersion);
	}
}
