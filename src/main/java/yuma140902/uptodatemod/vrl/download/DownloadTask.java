package yuma140902.uptodatemod.vrl.download;

public class DownloadTask {
    private final String url;
    private final String localPath;
    private int totalBytes;
    private int downloadedBytes;
    private DownloadTaskStatus status;
    private Throwable failureReason;

    public DownloadTask(String url, String localPath) {
        this.url = url;
        this.localPath = localPath;
        this.totalBytes = -1;
        this.downloadedBytes = 0;
        this.status = DownloadTaskStatus.NOT_STARTED;
    }

    public String getUrl() {
        return url;
    }

    public String getLocalPath() {
        return localPath;
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
