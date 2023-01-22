package yuma140902.uptodatemod.vrl.model;

import yuma140902.uptodatemod.vrl.extract.EnumTextureStyle;
import yuma140902.uptodatemod.vrl.extract.ExtractionTask;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Extraction {
    public TextureStyles texture_styles;
    public List<Part> parts;

    public List<ExtractionTask> createExtractionTaskList(EnumTextureStyle style, Path baseDir) {
        List<String> partList;
        if (style == EnumTextureStyle.BEFORE12) {
            partList = texture_styles.before12;
        }
        else {
            return Collections.emptyList();
        }

        return this.parts.stream()
                .filter(p -> partList.contains(p.part_id))
                .flatMap(p -> p.streamExtractionTasks(baseDir)).collect(Collectors.toList());
    }
}
