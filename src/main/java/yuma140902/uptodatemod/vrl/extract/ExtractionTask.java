package yuma140902.uptodatemod.vrl.extract;

import yuma140902.uptodatemod.vrl.extract.func.EnumExtractFunction;

import java.nio.file.Path;

public class ExtractionTask {
    private final String archiveId;
    private final Path sourcePathInArchive;
    private final Path destPath;
    private final EnumExtractFunction function;
    private ExtractionTaskStatus status;
    private Throwable failureReason;

    public ExtractionTask(String archiveId, Path sourcePathInArchive, Path destPath, EnumExtractFunction function) {
        this.archiveId = archiveId;
        this.sourcePathInArchive = sourcePathInArchive;
        this.destPath = destPath;
        this.function = function == null ? EnumExtractFunction.COPY_ONLY : function;
        this.status = ExtractionTaskStatus.NOT_STARTED;
    }

    public String getArchiveId() {
        return archiveId;
    }

    public Path getSourcePathInArchive() {
        return sourcePathInArchive;
    }

    public Path getDestPath() {
        return destPath;
    }

    public EnumExtractFunction getFunction() {
        return function;
    }

    public ExtractionTaskStatus getStatus() {
        return status;
    }

    public void setStatus(ExtractionTaskStatus status){
        this.status = status;
    }

    public Throwable getFailureReason() {
        return failureReason;
    }

    public void setFailed(Throwable failureReason){
        this.status = ExtractionTaskStatus.FAILED;
        this.failureReason = failureReason;
    }

}
