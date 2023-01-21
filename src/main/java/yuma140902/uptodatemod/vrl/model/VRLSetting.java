package yuma140902.uptodatemod.vrl.model;

import yuma140902.uptodatemod.vrl.download.DownloadTask;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VRLSetting {
	public List<Download> downloads;
	public Extraction extraction;

	public Stream<DownloadTask> steamDownloadTasks() {
		return this.downloads.stream().map(d ->
				new DownloadTask(d.url, d.to, d.hash, d.id));
	}
}
