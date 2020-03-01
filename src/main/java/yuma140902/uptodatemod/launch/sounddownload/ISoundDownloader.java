package yuma140902.uptodatemod.launch.sounddownload;

import java.io.IOException;
import java.util.List;
import yuma140902.uptodatemod.launch.model.Sound;

public interface ISoundDownloader {
	void downloadSounds(List<Sound> sounds) throws IOException;
}
