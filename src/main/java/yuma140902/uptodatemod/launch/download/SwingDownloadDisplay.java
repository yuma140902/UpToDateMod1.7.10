package yuma140902.uptodatemod.launch.download;

import javax.swing.JDialog;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.launch.ui.SwingDialogWithProgressBar;

public class SwingDownloadDisplay implements IDownloadDisplay {
	
	private SwingDialogWithProgressBar display;
	
	public SwingDownloadDisplay() {
		this.display = new SwingDialogWithProgressBar();
		this.display.setTitle("<html><b><font size='+1'>" + ModUpToDateMod.MOD_NAME + " is downloading resources.</font></b></html>");
		this.display.setSubTitle("<html>Please wait, " + ModUpToDateMod.MOD_NAME + " need to download resources.</html>");
	}
	
	
	@Override
	public void changeDownloadingFile(String url, String fileName, int fullSizeKb) {
		this.display.setMaximum(fullSizeKb);
		
		this.display.setText(String.format("<html>Downloading <code>%s</code> from %s (0 / %d KB)</html>", fileName, url, fullSizeKb));
	}

	@Override
	public void updateProgress(int downloadedKb) {
		this.display.updateProgress(downloadedKb);
	}

	@Override
	public JDialog getDialog() {
		return this.display.getDialog();
	}
	
	@Override
	public void showErrorDialog(String name, String url) {
		String html =
				"<html>" + ModUpToDateMod.MOD_NAME + " was unable to download required resources " + name
						+ "<br>Check your internet connection and try restarting or download it manually from" + "<br><a href=\""
						+ url + "\">" + url + "</a> and put it in the mods folder"
						+ "</html>";
		String title = "A download error has occured";
		
		this.display.showError(html, title);
	}
	
}
