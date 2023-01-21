package yuma140902.uptodatemod.vrl.model;

import yuma140902.uptodatemod.vrl.download.DownloadTask;
import yuma140902.uptodatemod.vrl.download.DownloadTaskStatus;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VRLSetting {
	public List<Archive> archives;
	public List<Download> downloads;
	public Extraction extraction;

	public Stream<DownloadTask> steamDownloadTasks(Path basePath) {
		return this.downloads.stream().map(d ->
		{
			URL url = null;
			Path path = basePath.resolve(d.to);
			String hash = d.hash;
			try {
				url = new URL(d.url);
			} catch (MalformedURLException e) {
				try {
					DownloadTask task = new DownloadTask(new URL("http://example.com/"), path, hash);
					task.setStatus(DownloadTaskStatus.FAILED);
					task.setFailureReason(e);
					return task;
				} catch (MalformedURLException ex) {
					ex.printStackTrace();
					return null;
				}
			}
			return new DownloadTask(url, path, hash);
		}).filter(Objects::nonNull);
	}
}
