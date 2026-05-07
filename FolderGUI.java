import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FolderGUI extends JFrame {

    private JPanel contentPane;

    private JTextField txtFolder;
    private JTextField txtTitleForAssign;
    private JTextField txtFolderForAssign;

    private JTextArea txtFolders;

    private JButton btnCreate;
    private JButton btnDeleteFolder;
    private JButton btnAssign;
    private JButton btnRefresh;
    private JButton btnClose;

    private LiteratureManager manager;

    public FolderGUI(LiteratureManager manager) {

        /*
         * This GUI implements hierarchical taxonomy administration.
         * Users can create folders, view folders, delete folder categories,
         * and assign literature records to subject folders.
         */
        this.manager = manager;

        setTitle("Manage Subject Folders");
        setBounds(220, 220, 560, 500);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblFolder = new JLabel("New Folder");
        lblFolder.setBounds(40, 30, 100, 20);
        contentPane.add(lblFolder);

        txtFolder = new JTextField();
        txtFolder.setBounds(150, 30, 220, 25);
        contentPane.add(txtFolder);

        btnCreate = new JButton("Create");
        btnCreate.setBounds(390, 30, 100, 25);
        contentPane.add(btnCreate);

        txtFolders = new JTextArea();
        txtFolders.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtFolders);
        scrollPane.setBounds(40, 80, 450, 150);
        contentPane.add(scrollPane);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(40, 250, 110, 30);
        contentPane.add(btnRefresh);

        btnDeleteFolder = new JButton("Delete Folder");
        btnDeleteFolder.setBounds(180, 250, 140, 30);
        contentPane.add(btnDeleteFolder);

        JLabel lblAssignTitle = new JLabel("Literature Title");
        lblAssignTitle.setBounds(40, 315, 120, 20);
        contentPane.add(lblAssignTitle);

        txtTitleForAssign = new JTextField();
        txtTitleForAssign.setBounds(170, 315, 280, 25);
        contentPane.add(txtTitleForAssign);

        JLabel lblAssignFolder = new JLabel("Assign to Folder");
        lblAssignFolder.setBounds(40, 355, 120, 20);
        contentPane.add(lblAssignFolder);

        txtFolderForAssign = new JTextField();
        txtFolderForAssign.setBounds(170, 355, 280, 25);
        contentPane.add(txtFolderForAssign);

        btnAssign = new JButton("Assign Literature");
        btnAssign.setBounds(95, 405, 150, 30);
        contentPane.add(btnAssign);

        btnClose = new JButton("Close");
        btnClose.setBounds(300, 405, 100, 30);
        contentPane.add(btnClose);

        refreshFolderText();

        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createFolder();
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshFolderText();
            }
        });

        btnDeleteFolder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteFolder();
            }
        });

        btnAssign.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                assignLiterature();
            }
        });

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void createFolder() {

        String folder = txtFolder.getText().trim();

        if (folder.length() == 0) {
            JOptionPane.showMessageDialog(this, "Folder name cannot be empty.");
            return;
        }

        manager.createFolder(folder);

        txtFolder.setText("");
        refreshFolderText();

        JOptionPane.showMessageDialog(this, "Folder created successfully.");
    }

    private void deleteFolder() {

        String folder = JOptionPane.showInputDialog(this,
                "Enter folder name to delete:\nRecords in this folder will become unassigned.");

        if (folder == null) {
            return;
        }

        boolean success = manager.deleteFolder(folder);

        if (success) {
            refreshFolderText();
            JOptionPane.showMessageDialog(this, "Folder deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Folder not found.");
        }
    }

    private void assignLiterature() {

        String title = txtTitleForAssign.getText().trim();
        String folder = txtFolderForAssign.getText().trim();

        if (title.length() == 0 || folder.length() == 0) {
            JOptionPane.showMessageDialog(this, "Title and folder cannot be empty.");
            return;
        }

        boolean success = manager.assignLiteratureToFolder(title, folder);

        if (success) {
            refreshFolderText();
            JOptionPane.showMessageDialog(this, "Literature assigned to folder successfully.");
            txtTitleForAssign.setText("");
            txtFolderForAssign.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Literature record not found.");
        }
    }

    private void refreshFolderText() {

        txtFolders.setText("");

        ArrayList<String> folders = manager.getFolderList();

        if (folders.size() == 0) {
            txtFolders.setText("No subject folders found.");
            return;
        }

        for (String folder : folders) {
            txtFolders.append("- " + folder + "\n");
        }
    }
}
