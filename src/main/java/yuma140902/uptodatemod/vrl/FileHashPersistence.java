package yuma140902.uptodatemod.vrl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FileHashPersistence implements Closeable {
    private final Path hashFile;
    private final String currentHash;

    public FileHashPersistence(InputStream currentFile, Path hashFile) {
        this.hashFile = hashFile;
        String currentHash;
        try {
            currentHash = DigestUtils.md5Hex(currentFile);
        } catch (IOException e) {
            currentHash = null;
        }
        this.currentHash = currentHash;
    }

    public boolean hashMatches() {
        List<String> lines;
        try {
            lines = Files.readAllLines(this.hashFile);
        } catch (IOException e) {
            return false;
        }
        if(lines.size() == 0){
            return false;
        }
        String savedHash = lines.get(0);
        return StringUtils.equals(savedHash, currentHash);
    }

    @Override
    public void close() throws IOException {
        List<String> lines = Collections.singletonList(this.currentHash);
        Files.write(this.hashFile, lines, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
    }
}
