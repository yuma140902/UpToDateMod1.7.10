package yuma140902.uptodatemod.launch.ui;

import java.awt.Desktop;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import cpw.mods.fml.common.FMLCommonHandler;
import yuma140902.uptodatemod.ModUpToDateMod;

public class SwingDialogWithProgressBar extends JOptionPane {
	
	private JDialog container;
	private JLabel titleLabel;
	private JLabel subTitleLabel;
	private JLabel messageLabel;
	private JProgressBar progress;
	
	public SwingDialogWithProgressBar() {
		createDialog();
	}
	
	public void setTitle(String title) {
		this.titleLabel.setText(title);
		this.container.pack();
		this.container.setMinimumSize(this.container.getPreferredSize());
	}
	
	public void setSubTitle(String subTitle) {
		this.subTitleLabel.setText(subTitle);
		this.container.pack();
		this.container.setMinimumSize(this.container.getPreferredSize());
	}
	
	public void setText(String msg) {
		this.messageLabel.setText(msg);
	}
	
	public void setMaximum(int max) {
		this.progress.setMaximum(max);
	}
	
	public void updateProgress(int progress) {
		this.progress.getModel().setValue(progress);
	}
	
	public JDialog getDialog() {
		return this.container;
	}
	
	protected void requestClose(String message) {
		int shouldClose = JOptionPane.showConfirmDialog(
				container, message, "Are you sure you want to stop?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (shouldClose == JOptionPane.YES_OPTION) {
			container.dispose();
			FMLCommonHandler.instance().exitJava(0, false);
		}
	}
	
	private Box createProgressPanel() {
		Box box = Box.createVerticalBox();
		box.add(Box.createRigidArea(new Dimension(0, 10)));
		
		this.titleLabel = new JLabel("");
		this.titleLabel.setAlignmentY(LEFT_ALIGNMENT);
		box.add(this.titleLabel);
		
		this.subTitleLabel = new JLabel("");
		this.subTitleLabel.setAlignmentY(LEFT_ALIGNMENT);
		box.add(this.subTitleLabel);
		box.add(Box.createRigidArea(new Dimension(0, 10)));
		
		this.messageLabel = new JLabel("Currently doing ...");
		box.add(this.messageLabel);
		box.add(Box.createRigidArea(new Dimension(0, 10)));
		
		this.progress = new JProgressBar(0, 100);
		this.progress.setStringPainted(true);
		box.add(this.progress);
		box.add(Box.createRigidArea(new Dimension(0, 30)));
		
		return box;
	}
	
	
	private void createDialog() {
		
		setMessageType(JOptionPane.INFORMATION_MESSAGE);
		setMessage(createProgressPanel());
		setOptions(new Object[] {});
		addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getSource() == this && evt.getPropertyName() == VALUE_PROPERTY) {
					requestClose("This will stop minecraft from launching\nAre you sure you want to do this?");
				}
			}
		});
		this.container = new JDialog(null, ModUpToDateMod.MOD_NAME, ModalityType.MODELESS);
		this.container.setResizable(false);
		this.container.setLocationRelativeTo(null);
		this.container.add(this);
		this.updateUI();
		this.container.pack();
		this.container.setMinimumSize(this.container.getPreferredSize());
		this.container.setVisible(true);
		this.container.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.container.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				requestClose("Closing this window will stop minecraft from launching\nAre you sure you wish to do this?");
			}
		});
		
	}
	
	
	public void showError(String html, String title) {
		JEditorPane ep = new JEditorPane("text/html", html);
		
		ep.setEditable(false);
		ep.setOpaque(false);
		ep.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent event) {
				try {
					if (event.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED))
						Desktop.getDesktop().browse(event.getURL().toURI());
				} catch (Exception e) {}
			}
		});
		
		JOptionPane.showMessageDialog(null, ep, title, JOptionPane.ERROR_MESSAGE);
	}
}
