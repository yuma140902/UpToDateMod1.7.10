package yuma140902.uptodatemod.launch.download;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import javax.swing.JDialog;

public class DownloaderWithDisplay implements IDownloader {

	private IDownloadDisplay display;
	private Path cacheDirectory;
	private Path destDirectory;
	
	public DownloaderWithDisplay(IDownloadDisplay display, Path cacheDir, Path destDir) {
		this.display = display;
		this.cacheDirectory = cacheDir;
		this.destDirectory = destDir;
	}
	
	@Override
	public void downloadFiles(List<DownloadCandidate> candidates) {
		JDialog dialog = this.display.getDialog();
		dialog.setVisible(true);
		
		for(DownloadCandidate candidate : candidates) {
			boolean succeeded = false;
			while(candidate.urlLeft() && !succeeded) {
				String url = candidate.popUrl();
				String fileName = candidate.fileName();
				boolean useCache = candidate.useCache();
				try {
					if(alreadyDownloaded(fileName)) {
						succeeded = true;
						break;
					}
					
					if(useCache && cacheExists(fileName)) {
						applyCache(fileName);
						succeeded = true;
						break;
					}
					
					HttpURLConnection conn = newConnection(url);
					conn.connect();
					
					if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
						// 接続に失敗したら次の候補のURLをダウンロード
						continue;
					}
					
					int fullSizeKb = conn.getContentLength() / 1024;
					display.changeDownloadingFile(url, fileName, fullSizeKb);
					
					download(conn, fileName);
					
					if(useCache) {
						makeCache(fileName);
					}
					
					succeeded = true;
					
				} catch (IOException e) {
					e.printStackTrace();
					display.showErrorDialog(fileName, url);
					continue;
				}
			}
			
			if(!succeeded) {
				display.showErrorDialog(candidate.fileName(), "");
			}
		}
		
		dialog.dispose();
	}
	
	
	
	private boolean alreadyDownloaded(String fileName) {
		return Files.exists(this.destDirectory.resolve(fileName));
	}
	
	private boolean cacheExists(String fileName) {
		return Files.exists(this.cacheDirectory.resolve(fileName));
	}
	
	private void applyCache(String fileName) throws IOException {
		Path cached = this.cacheDirectory.resolve(fileName);
		Path dest = this.destDirectory.resolve(fileName);
		
		Files.copy(cached, dest);
	}
	
	private void makeCache(String fileName) throws IOException {
		Path dest = this.destDirectory.resolve(fileName);
		Path cache = this.cacheDirectory.resolve(fileName);
		Files.copy(dest, cache);
	}
	
	
	private HttpURLConnection newConnection(String url) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setAllowUserInteraction(false);
		conn.setInstanceFollowRedirects(true);
		conn.setRequestMethod("GET");
		
		return conn;
	}
	
	private void download(HttpURLConnection conn, String fileName) throws IOException {
		Path dest = destDirectory.resolve(fileName);
		
		DataInputStream input;
		DataOutputStream output;
		input = new DataInputStream(conn.getInputStream());
		output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dest.toString())));
		
		byte[] buf = new byte[4096]; // 4KB
    int readByte = 0;
    int downloadedByte = 0;
    while(-1 != (readByte = input.read(buf))){
    	downloadedByte += readByte;
    	display.updateProgress(downloadedByte/1024);
      output.write(buf, 0, readByte);
    }
    
    input.close();
    output.close();
    
	}
	
	
}
