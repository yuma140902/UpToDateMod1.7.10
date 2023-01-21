package yuma140902.uptodatemod.vrl.download;

import java.net.URL;
import java.nio.file.Path;

public class DownloadTask {
    private final URL url;
    private final Path localPath;
    private final String hash;
    private final String id;
    private int totalBytes;
    private int downloadedBytes;
    private DownloadTaskStatus status;
    private Throwable failureReason;

    public DownloadTask(URL url, Path localPath, String hash, String id) {
        this.url = url;
        this.localPath = localPath;
        this.hash = hash;
        this.id = id;
        this.totalBytes = -1;
        this.downloadedBytes = 0;
        this.status = DownloadTaskStatus.NOT_STARTED;
    }

    public URL getUrl() {
        return url;
    }

    public Path getLocalPath() {
        return localPath;
    }

    public String getHash() {
        return hash;
    }

    public String getId() {
        return id;
    }

    public int getTotalBytes() {
        return totalBytes;
    }

    public void setTotalBytes(int totalBytes) {
        this.totalBytes = totalBytes;
    }

    public int getDownloadedBytes(){
        return downloadedBytes;
    }

    public void setDownloadedBytes(int downloadedBytes) {
        this.downloadedBytes = downloadedBytes;
    }

    public DownloadTaskStatus getStatus() {
        return status;
    }

    public void setStatus(DownloadTaskStatus status) {
        this.status = status;
    }

    public Throwable getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(Throwable failureReason) {
        this.failureReason = failureReason;
    }
}