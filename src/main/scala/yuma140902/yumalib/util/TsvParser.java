package yuma140902.yumalib.util;

import java.util.HashMap;

public class TsvParser {
    public static HashMap<String, String> getVersionsTable(String tsv) {
        HashMap<String, String> hashMap = new HashMap<>();

        if (tsv == null || tsv.isEmpty()) return hashMap;

        String[] lines = tsv.split("[\\n\\r]");
        for (String line : lines) {
            String[] tmp = line.split("\\t");
            if (tmp.length < 2) continue;
            hashMap.put(tmp[0], tmp[1]);
        }

        return hashMap;
    }
}
