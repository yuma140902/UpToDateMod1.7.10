package yuma140902.uptodatemod.vrl.model;

import yuma140902.uptodatemod.vrl.extract.ExtractionTask;
import yuma140902.uptodatemod.vrl.extract.func.EnumExtractFunction;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Part {
    public String archive_id;
    public String part_id;
    public String src_dir;
    public String dst_dir;
    public List<File> files;

    public Stream<ExtractionTask> streamExtractionTasks(Path baseDir) {
        if (this.files == null) {
            System.out.printf("part_id=%s 's files are somehow null%n", part_id);
            return Stream.of();
        }
        return this.files.stream().filter(Objects::nonNull).map(f -> {
            String archive_id = this.archive_id;
            Path srcPath = Paths.get(src_dir + f.src);
            Path dstPath = baseDir.resolve(dst_dir + f.dst);
            EnumExtractFunction function;
            if(f.func == null) {
                function = EnumExtractFunction.COPY_ONLY;
            }
            else if(f.func.equals("rotate90")) {
                function = EnumExtractFunction.ROTATE90;
            }
            else if(f.func.equals("rotate180")) {
                function = EnumExtractFunction.ROTATE180;
            }
            else if(f.func.equals("rotate270")) {
                function = EnumExtractFunction.ROTATE270;
            }
            else if(f.func.equals("flip")) {
                function = EnumExtractFunction.FLIP;
            }
            else {
                function = EnumExtractFunction.COPY_ONLY;
            }
            return new ExtractionTask(archive_id, srcPath, dstPath, function);
        });
    }
}
