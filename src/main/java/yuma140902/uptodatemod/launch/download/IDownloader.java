package yuma140902.uptodatemod.launch.download;

import java.util.List;

public interface IDownloader {
	public void downloadFiles(List<DownloadCandidate> candidates);
}
