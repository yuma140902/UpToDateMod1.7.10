package yuma140902.uptodatemod.launch.sounddownload;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
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
import yuma140902.uptodatemod.launch.model.Sound;

public class SoundDownloaderWithDisplay implements ISoundDownloader {

	private ISoundDownloadDisplay display;
	private Path cacheDir;
	private Path assetDir;
	
	public SoundDownloaderWithDisplay(ISoundDownloadDisplay display, Path cacheDir, Path assetDir) {
		this.display = display;
		this.cacheDir = cacheDir;
		this.assetDir = assetDir;
	}
	
	@Override
	public void downloadSounds(List<Sound> sounds) {
		JDialog dialog = this.display.getDialog();
		dialog.setVisible(true);
		
		display.setMax(sounds.size());
		
		for(Sound sound : sounds) {
			display.startNewSound(sound);
			try {
				handle(sound);
			} catch (IOException e) {
				e.printStackTrace();
				display.showErrorDialog(sound);
			}
		}
		
		dialog.dispose();
	}
	
	private HttpsURLConnection newConnection(String url) throws IOException {
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

		HttpsURLConnection conn = (HttpsURLConnection) new URL(url).openConnection();
		conn.setAllowUserInteraction(false);
		conn.setInstanceFollowRedirects(true);
		conn.setRequestMethod("GET");
		conn.setSSLSocketFactory(sslcontext.getSocketFactory());
		
		return conn;
	}
	
	private void download(HttpURLConnection conn, Path dest) throws IOException {
		InputStream input;
		OutputStream output;
		input = conn.getInputStream();
		output = new BufferedOutputStream(new FileOutputStream(dest.toString()));
		
		byte[] buf = new byte[4096]; // 4KB
    int readByte = 0;
    while(-1 != (readByte = input.read(buf))){
      output.write(buf, 0, readByte);
    }
    
    input.close();
    output.close();
    
	}
	
	private void handle(Sound sound) throws IOException {
		
		Path dest = assetDir.resolve(sound.dest);
		System.out.println("handling sound " + sound.dest);
		if(cacheExists(sound.dest)) {
			System.out.println("sound " + sound.dest + " exists. skipping...");
			applyCache(sound.dest);
			return;
		}
		System.out.println("downloading sound " + sound.dest);
		
		HttpsURLConnection conn = newConnection(sound.url);
		conn.connect();
		
		if(conn.getResponseCode() != HttpsURLConnection.HTTP_OK) {
			this.display.showErrorDialog(sound);
			return;
		}
		
		Files.createDirectories(dest.getParent());
		download(conn, dest);
		
		makeCache(sound.dest);
	}
	
	private boolean cacheExists(String fileName) {
		return Files.exists(this.cacheDir.resolve(fileName));
	}
	
	private void applyCache(String fileName) throws IOException {
		Path cached = this.cacheDir.resolve(fileName);
		Path dest = this.assetDir.resolve(fileName);
		if(!Files.exists(dest)) {
			Files.copy(cached, dest);
		}
	}
	
	private void makeCache(String fileName) throws IOException {
		Path dest = this.assetDir.resolve(fileName);
		Path cache = this.cacheDir.resolve(fileName);
		Files.createDirectories(cache.getParent());
		Files.copy(dest, cache);
	}
	
}
