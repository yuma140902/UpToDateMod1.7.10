package yuma140902.uptodatemod.vrl.download;

public enum DownloadTaskStatus {
    NOT_STARTED,
    DOWNLOADING_FIRST_TIME,
    RE_DOWNLOADING_BECAUSE_HASH_DOES_NOT_MATCH,
    SKIPPED_BECAUSE_ALREADY_DOWNLOADED,
    DOWNLOADED,
    FAILED,
}
