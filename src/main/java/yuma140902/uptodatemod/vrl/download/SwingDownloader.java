package yuma140902.uptodatemod.vrl.download;

import org.apache.commons.codec.digest.DigestUtils;
import yuma140902.uptodatemod.vrl.VRLException;
import yuma140902.uptodatemod.vrl.ui.SwingDownloadProgressWindow;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.stream.Collectors;

public class SwingDownloader {

    private final List<DownloadTask> tasks;

    private final TrustManager[] everythingIsOkTrustManager = {new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // Nothing
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // Nothing
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }};

    public SwingDownloader(List<DownloadTask> tasks) {
        this.tasks = tasks;
    }

    public void start() {
        if(this.tasks.stream().allMatch(this::matchesHash)) {
            // すべてダウンロード済・ハッシュが一致するならウィンドウを開く必要がない
            System.out.println("skipped all downloads");
            return;
        }

        SwingDownloadProgressWindow frame = new SwingDownloadProgressWindow();
        frame.setDownloadTasks(this.tasks);
        frame.showDownloadTaskInProgressBar(0);
        frame.setVisible(true);

        for (final DownloadTask task : this.tasks) {
            frame.showDownloadTaskInProgressBar(task);
            try {
                download(task, frame);
            } catch (IOException | NoSuchAlgorithmException | KeyManagementException | VRLException e) {
                e.printStackTrace();
                task.setFailed(e);
                frame.notifyUpdate(task);
            }
        }

        List<DownloadTask> failedTasks = this.tasks.stream().filter(t ->
                t.getStatus() == DownloadTaskStatus.FAILED).collect(Collectors.toList());
        if(failedTasks.size() > 0) {
            frame.showMessageHTML(String.format("<html>" +
                    "<font color=\"red\">" +
                    "Failed to download %d resources.<br>" +
                    "Please download them manually and place in the right path.<br>" +
                    "You can close this window and the game will quit." +
                    "</font>" +
                    "</html>", failedTasks.size()
            ));
        }
        else{
            frame.setVisible(false);
            frame.dispose();
        }
    }

    private void download(DownloadTask task, SwingDownloadProgressWindow window) throws IOException, NoSuchAlgorithmException, KeyManagementException, VRLException {
        if (matchesHash(task)) {
            task.setStatus(DownloadTaskStatus.SKIPPED);
            window.notifyUpdate(task);
            return;
        }
        task.setStatus(DownloadTaskStatus.DOWNLOADING);
        window.notifyUpdate(task);

        HttpsURLConnection conn = createConnection(task.getUrl());
        conn.connect();

        if (conn.getResponseCode() / 100 != 2) {
            throw new VRLException(String.format("Response code was not 200 for URL %s", task.getUrl()));
        }

        int totalBytes = conn.getContentLength();
        task.setTotalBytes(totalBytes);
        window.notifyUpdate(task);

        Files.createDirectories(task.getLocalPath().getParent());

        try (InputStream input = conn.getInputStream()) {
            try (OutputStream output = Files.newOutputStream(task.getLocalPath().toFile().toPath())) {
                byte[] buf = new byte[1024 * 32]; // 32 KiB
                int readByte = 0;
                int downloadedByte = 0;
                while ((readByte = input.read(buf)) != -1) {
                    downloadedByte += readByte;
                    task.setDownloadedBytes(downloadedByte);
                    window.notifyUpdate(task);
                    output.write(buf, 0, readByte);
                }
            }
        }
        task.setStatus(DownloadTaskStatus.DOWNLOADED);
        window.notifyUpdate(task);
    }

    private boolean matchesHash(DownloadTask task) {
        if (!Files.exists(task.getLocalPath())) {
            return false;
        }
        // hashがnullのときハッシュチェックを行わない
        // 常に一致していると見なす
        if (task.getHash() == null) {
            return true;
        }

        String md5 = null;
        try (InputStream input = Files.newInputStream(task.getLocalPath().toFile().toPath())) {
            md5 = DigestUtils.md5Hex(input);
        } catch (IOException e) {
            return false;
        }
        return task.getHash().equals(md5);
    }

    private HttpsURLConnection createConnection(URL url) throws IOException, KeyManagementException, NoSuchAlgorithmException {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, this.everythingIsOkTrustManager, null);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setAllowUserInteraction(false);
        conn.setInstanceFollowRedirects(true);
        conn.setRequestMethod("GET");
        conn.setSSLSocketFactory(sslContext.getSocketFactory());
        return conn;
    }
}
