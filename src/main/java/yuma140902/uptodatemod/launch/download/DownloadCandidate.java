package yuma140902.uptodatemod.launch.download;

import java.util.List;
import yuma140902.uptodatemod.launch.model.Archive;

public class DownloadCandidate {
	
	private List<String> urls;
	private String fileName;
	private boolean useCache;
	
	private int currentIndex = 0;
	
	public String getUrl(int index) {
		return this.urls.get(index);
	}
	
	public String popUrl() {
		return this.urls.get(currentIndex++);
	}
	
	public boolean urlLeft() {
		return currentIndex < this.urls.size();
	}
	
	
	public String fileName() {
		return fileName;
	}
	
	public boolean useCache() {
		return useCache;
	}
	
	public DownloadCandidate(Archive archive) {
		this.urls = archive.urls;
		this.fileName = archive.filename;
		this.useCache = archive.cache;
	}
	
}
