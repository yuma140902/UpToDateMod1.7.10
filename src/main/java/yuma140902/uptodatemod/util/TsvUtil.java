package yuma140902.uptodatemod.util;

import java.util.HashMap;

public class TsvUtil {
	private TsvUtil() {}
	
	public static HashMap<Version3, String> getTable(String tsv){
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
}
