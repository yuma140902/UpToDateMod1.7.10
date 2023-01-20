package yuma140902.uptodatemod.launch.download;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
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

					System.out.println("creating connection");
					HttpsURLConnection conn = newConnection(url);
					System.out.println("created connection");
					conn.connect();
					System.out.println("connected");
					
					if(conn.getResponseCode() != HttpsURLConnection.HTTP_OK) {
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
	
	
	private HttpsURLConnection newConnection(String url) throws IOException {
		System.out.println("creating connection to " + url);
		URL urlobj = new URL(url);
		System.out.println("created urlobj " + urlobj);
		TrustManager[] tm = { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
			@Override
			public void checkClientTrusted(X509Certificate[] chain,
										   String authType) throws CertificateException {
			}
			@Override
			public void checkServerTrusted(X509Certificate[] chain,
										   String authType) throws CertificateException {
			}
		} };
		SSLContext sslcontext = null;
		try {
			sslcontext = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		try {
			sslcontext.init(null, tm, null);
		} catch (KeyManagementException e) {
			throw new RuntimeException(e);
		}

		HttpsURLConnection conn = (HttpsURLConnection) urlobj.openConnection();
		System.out.println("created");
		conn.setAllowUserInteraction(false);
		conn.setInstanceFollowRedirects(true);
		conn.setRequestMethod("GET");
		conn.setSSLSocketFactory(sslcontext.getSocketFactory());
		
		return conn;
	}
	
	private void download(HttpsURLConnection conn, String fileName) throws IOException {
		Path dest = destDirectory.resolve(fileName);
		
		InputStream input;
		OutputStream output;
		input = conn.getInputStream();
		output = new BufferedOutputStream(new FileOutputStream(dest.toString()));
		
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
