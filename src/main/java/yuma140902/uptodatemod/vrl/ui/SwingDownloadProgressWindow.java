package yuma140902.uptodatemod.vrl.ui;

import cpw.mods.fml.common.FMLCommonHandler;
import yuma140902.uptodatemod.vrl.download.DownloadTask;
import yuma140902.uptodatemod.vrl.download.DownloadTaskStatus;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class SwingDownloadProgressWindow extends JFrame {

    private final List<DownloadTask> tasks = new ArrayList<DownloadTask>();

    private int taskIndexToShowProgressBars;

    private boolean shouldCloseMinecraftWhenClosing = true;

    private final JEditorPane urlEp = new JEditorPane("text/html", "");
    private final JEditorPane localPathEp = new JEditorPane("text/plain", "");
    private final JProgressBar bytesProgressBar = new JProgressBar();
    private final JProgressBar filesProgressBar = new JProgressBar();
    private final JEditorPane messageEp = new JEditorPane("text/html", "");
    private final DefaultTableModel tableModel = new DefaultTableModel();

    public SwingDownloadProgressWindow() {
        setTitle("VRL");
        setSize(800, 400);
        setLocationRelativeTo(null);

        Box box = Box.createVerticalBox();

        box.add(Box.createRigidArea(new Dimension(0, 10)));

        Box boxDlFrom = Box.createHorizontalBox();
        boxDlFrom.add(new JLabel("Downloading from:"));
        boxDlFrom.add(Box.createRigidArea(new Dimension(10, 0)));
        this.urlEp.setEditable(false);
        this.urlEp.setOpaque(false);
        this.urlEp.addHyperlinkListener(e -> {
            if(HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                try {
                    Desktop.getDesktop().browse(e.getURL().toURI());
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });
        boxDlFrom.add(this.urlEp);
        box.add(boxDlFrom);

        Box boxDlTo = Box.createHorizontalBox();
        boxDlTo.add(new JLabel("Downloading to:"));
        boxDlTo.add(Box.createRigidArea(new Dimension(10, 0)));
        this.localPathEp.setEditable(false);
        this.localPathEp.setOpaque(false);
        boxDlTo.add(this.localPathEp);
        box.add(boxDlTo);

        box.add(Box.createRigidArea(new Dimension(0, 10)));

        this.bytesProgressBar.setStringPainted(true);
        box.add(this.bytesProgressBar);

        box.add(Box.createRigidArea(new Dimension(0, 10)));

        this.filesProgressBar.setStringPainted(true);
        box.add(this.filesProgressBar);

        box.add(Box.createRigidArea(new Dimension(0, 10)));

        this.messageEp.setEditable(false);
        this.messageEp.setOpaque(false);
        box.add(this.messageEp);

        box.add(Box.createRigidArea(new Dimension(0, 10)));

        this.tableModel.setColumnIdentifiers(new String[]{"#", "Status", "Downloaded (B)", "Total (B)", "URL", "Path"});
        JTable table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumn("#").setPreferredWidth(20);
        table.getColumn("Status").setPreferredWidth(100);
        table.getColumn("Downloaded (B)").setPreferredWidth(60);
        table.getColumn("Total (B)").setPreferredWidth(60);
        table.getColumn("URL").setPreferredWidth(279);
        table.getColumn("Path").setPreferredWidth(279);
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(800, 400));
        box.add(sp);

        Box tableButtons = Box.createHorizontalBox();
        JButton btnCopyUrl = new JButton("Copy selected URL");
        btnCopyUrl.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row < 0 || this.tasks.size() <= row) return;
            DownloadTask selectedTask = this.tasks.get(row);

            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection ss = new StringSelection(selectedTask.getUrl().toString());
            clipboard.setContents(ss, null);
        });
        JButton btnCopyPath = new JButton("Copy selected Path");
        btnCopyPath.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row < 0 || this.tasks.size() <= row) return;
            DownloadTask selectedTask = this.tasks.get(row);

            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection ss = new StringSelection(selectedTask.getLocalPath().toString());
            clipboard.setContents(ss, null);
        });
        tableButtons.add(btnCopyUrl);
        tableButtons.add(btnCopyPath);
        box.add(tableButtons);

        box.add(Box.createRigidArea(new Dimension(800, 30)));

        this.add(box);

        this.pack();

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(shouldCloseMinecraftWhenClosing) {
                    int shouldClose = JOptionPane.showConfirmDialog(
                            null, "Are you sure you want to stop?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (shouldClose == JOptionPane.YES_OPTION) {
                        FMLCommonHandler.instance().exitJava(0, false);
                    }
                }
            }
        });
    }

    public void setDownloadTasks(List<DownloadTask> tasks) {
        this.tasks.addAll(tasks);
        int i = 0;
        for(final DownloadTask task : tasks) {
            ++i;
            this.tableModel.addRow(taskToRow(i, task));
        }
    }

    private String[] taskToRow(int number, DownloadTask task) {
        int downloadedBytes = task.getDownloadedBytes();
        int totalBytes = task.getTotalBytes();
        return new String[] {
                Integer.toString(number),
                task.getStatus().toString(),
                downloadedBytes < 0 ? "" : Integer.toString(downloadedBytes),
                totalBytes < 0 ? "" : Integer.toString(totalBytes),
                task.getUrl().toString(),
                task.getLocalPath().toString()
        };
    }

    public void showDownloadTaskInProgressBar(DownloadTask task) {
        int index = this.tasks.indexOf(task);
        showDownloadTaskInProgressBar(index);
    }

    public void showDownloadTaskInProgressBar(int index){
        if(index < 0 || this.tasks.size() <= index) return;
        this.taskIndexToShowProgressBars = index;
        notifyUpdate(this.tasks.get(index));
    }

    public void notifyUpdate(DownloadTask task) {
        int index = this.tasks.indexOf(task);
        if(index < 0)
            return;

        String[] row = taskToRow(index+1, task);
        for(int column = 0; column < 6; ++column) {
            this.tableModel.setValueAt(row[column], index, column);
        }

        if(index != this.taskIndexToShowProgressBars)
            return;

        updateFilesProgressBar();
        switch (task.getStatus()){
            case NOT_STARTED:
                clearUrl();
                clearLocalPath();
                clearBytesProgressBar();
                break;
            case DOWNLOADING:
            case SKIPPED:
            case FAILED:
            case DOWNLOADED:
                setUrl(task.getUrl().toString());
                setLocalPath(task.getLocalPath().toString());
                updateBytesProgressBar(task.getDownloadedBytes(), task.getTotalBytes());
                break;
        }
    }

    private void clearUrl() {
        this.urlEp.setText("");
    }

    private void setUrl(String url){
        this.urlEp.setText(String.format("<html><a href=\"%s\">%s</a></html>", url, url));
    }

    private void clearLocalPath() {
        this.localPathEp.setText("");
    }

    private void setLocalPath(String localPath) {
        this.localPathEp.setText(localPath);
    }

    private void clearBytesProgressBar() {
        this.bytesProgressBar.setValue(0);
        this.bytesProgressBar.setString("");
    }

    private void updateBytesProgressBar(int downloadedBytes, int totalBytes) {
        this.bytesProgressBar.setMaximum(totalBytes);
        this.bytesProgressBar.setValue(downloadedBytes);
        this.bytesProgressBar.setString(String.format("%d / %d bytes", downloadedBytes, totalBytes));
    }

    private void updateFilesProgressBar() {
        int numAllFiles = this.tasks.size();
        int numDoneFiles = (int) this.tasks.stream().filter(t ->
                t.getStatus() == DownloadTaskStatus.SKIPPED ||
                t.getStatus() == DownloadTaskStatus.DOWNLOADED ||
                t.getStatus() == DownloadTaskStatus.FAILED
            ).count();
        this.filesProgressBar.setMaximum(numAllFiles);
        this.filesProgressBar.setValue(numDoneFiles);
        this.filesProgressBar.setString(String.format("%d / %d files", numAllFiles, numDoneFiles));
    }

    public void setShouldCloseMinecraftWhenClosing(boolean b){
        this.shouldCloseMinecraftWhenClosing = b;
    }

    public void showMessageHTML(String message) {
        this.messageEp.setText(message);
    }

}
