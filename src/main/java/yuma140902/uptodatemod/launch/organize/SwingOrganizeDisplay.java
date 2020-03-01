package yuma140902.uptodatemod.launch.organize;

import javax.swing.JDialog;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.launch.model.Copy;
import yuma140902.uptodatemod.launch.ui.SwingDialogWithProgressBar;

public class SwingOrganizeDisplay implements IOrganizeDisplay {

	private SwingDialogWithProgressBar display;
	private int max;
	private int current = 0;
	
	public SwingOrganizeDisplay() {
		this.display = new SwingDialogWithProgressBar();
		this.display.setTitle("<html><b><font size='+1'>" + ModUpToDateMod.MOD_NAME + " is organizing resources.</font></b></html>");
		this.display.setSubTitle("<html>Please wait, " + ModUpToDateMod.MOD_NAME + " need to organizing resources.</html>");
	}
	
	
	@Override
	public void startNewCopy(Copy copy) {
		++this.current;
		this.display.setText(String.format("<html><code>[%d / %d]</code>Organizing %s -> %s from %s (func: %s)</html>", this.current, this.max, copy.src, copy.dest, copy.ar, copy.func));
		this.display.updateProgress(current);
	}

	@Override
	public void setMaxProgress(int max) {
		this.max = max;
		this.display.setMaximum(max);
	}

	@Override
	public JDialog getDialog() {
		return this.display.getDialog();
	}

	@Override
	public void showErrorDialog(Copy copy) {
		String copyStr = String.format("{ 'ar': '%s', 'src': '%s', 'dest': '%s', 'func': '%s' }", copy.ar, copy.src, copy.dest, copy.func);
		String html =
				"<html>" + ModUpToDateMod.MOD_NAME + " was unable to organize a required resource:<br>"
				+ copyStr	+ "</html>";
		String title = "A organizing error has occured";
		
		this.display.showError(html, title);
	}
	
}
