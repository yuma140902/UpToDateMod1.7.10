package yuma140902.uptodatemod.launch.sounddownload;

import javax.swing.JDialog;
import yuma140902.uptodatemod.launch.model.Sound;

public interface ISoundDownloadDisplay {
	void startNewSound(Sound sound);
	
	void setMax(int max);
	
	JDialog getDialog();
	
	void showErrorDialog(Sound sound);
}
