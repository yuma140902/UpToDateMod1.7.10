package yuma140902.uptodatemod.launch.download;

import javax.swing.JDialog;

public interface IDownloadDisplay {
	/*void resetProgress(int sizeGuess);
	
	void setPokeThread(Thread currentThread);
	
	void updateProgress(int fullLength);
	
	boolean shouldStop();
	
	void updateProgressString(String string, Object... data);*/
	
	void changeDownloadingFile(String url, String fileName, int fullSizeKb);
	
	void updateProgress(int downloadedKb);
	
	JDialog getDialog();
	
	void showErrorDialog(String name, String url);
}
