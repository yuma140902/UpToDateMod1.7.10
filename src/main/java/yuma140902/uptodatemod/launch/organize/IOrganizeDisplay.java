package yuma140902.uptodatemod.launch.organize;

import javax.swing.JDialog;
import yuma140902.uptodatemod.launch.model.Copy;

public interface IOrganizeDisplay {
	void startNewCopy(Copy copy);
	
	void setMaxProgress(int max);
	
	JDialog getDialog();
	
	void showErrorDialog(Copy copy);
}
