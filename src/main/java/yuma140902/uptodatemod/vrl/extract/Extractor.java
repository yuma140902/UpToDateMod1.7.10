package yuma140902.uptodatemod.vrl.extract;

import yuma140902.uptodatemod.vrl.VRLException;
import yuma140902.uptodatemod.vrl.archives.ArchiveFileSystemStore;
import yuma140902.uptodatemod.vrl.extract.func.IExtractFunction;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Extractor {
    private final List<ExtractionTask> tasks = new ArrayList<ExtractionTask>();
    private final ArchiveFileSystemStore archiveStore;

    public Extractor(List<ExtractionTask> tasks, ArchiveFileSystemStore archiveStore) {
        this.tasks.addAll(tasks);
        this.archiveStore = archiveStore;
    }

    public void start() throws VRLException {
        for(final ExtractionTask task : this.tasks) {
            try {
                extract(task);
            } catch (IOException e) {
                task.setFailed(e);
            }
        }

        List<ExtractionTask> failedTasks = this.tasks.stream().filter(t -> t.getStatus() == ExtractionTaskStatus.FAILED).collect(Collectors.toList());
        for(final ExtractionTask t : failedTasks) {
            Throwable e = t.getFailureReason();
            if(e != null) e.printStackTrace();
        }
        if(failedTasks.size() > 0) {
            throw new VRLException(String.format("%d extraction tasks failed", failedTasks.size()));
        }
    }

    private void extract(ExtractionTask task) throws IOException {
        FileSystem fs = this.archiveStore.getFileSystem(task.getArchiveId());
        Path src = fs.getPath(task.getSourcePathInArchive().toString());
        Path dst = task.getDestPath();

        task.setStatus(ExtractionTaskStatus.EXTRACTING);

        if(!Files.exists(src)) {
            System.out.printf("[%s] %s was not found. Skipping.%n", task.getArchiveId(), src);
            task.setFailed(new VRLException("source path not found"));
            return;
        }

        Files.createDirectories(dst.getParent());

        IExtractFunction function = task.getFunction().getFunction();
        function.handle(src, dst);

        task.setStatus(ExtractionTaskStatus.DONE);
    }
}
