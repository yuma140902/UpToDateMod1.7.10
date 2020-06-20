package yuma140902.uptodatemod.launch.sounddownload;

import javax.swing.JDialog;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.launch.model.Sound;
import yuma140902.uptodatemod.launch.ui.SwingDialogWithProgressBar;

public class SwingSoundDownloadDisplay implements ISoundDownloadDisplay {
	
	private SwingDialogWithProgressBar display;
	private int max;
	private int current = 0;

	public SwingSoundDownloadDisplay() {
		this.display = new SwingDialogWithProgressBar();
		this.display.setTitle("<html><b><font size='+1'>" + ModUpToDateMod.MOD_NAME + " is downloading sounds.</font></b></html>");
		this.display.setSubTitle("<html>Please wait, " + ModUpToDateMod.MOD_NAME + " need to download sounds.</html>");
	}
	
	@Override
	public void startNewSound(Sound sound) {
		++this.current;
		this.display.setText(String.format("<html><code>[%d / %d]</code>Downloading from %s -> %s</html>", this.current, this.max, sound.url, sound.dest));
		this.display.updateProgress(current);
	}

	@Override
	public void setMax(int max) {
		this.max = max;
		this.display.setMaximum(max);
	}

	@Override
	public JDialog getDialog() {
		return this.display.getDialog();
	}

	@Override
	public void showErrorDialog(Sound sound) {
		String html =
				"<html>" + ModUpToDateMod.MOD_NAME + " was unable to download required sound " + sound.dest
						+ "<br>Check your internet connection and try restarting or download it manually from" + "<br><a href=\""
						+ sound.url + "\">" + sound.url + "</a> and put it at uptodatemod/dl-cache/" + sound.dest
						+ "</html>";
		String title = "A download error has occurred";
		
		this.display.showError(html, title);
	}
	
}
