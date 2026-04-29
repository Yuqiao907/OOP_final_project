import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FolderGUI extends JFrame {

	private JPanel contentPane;

	private JTextField txtFolder;
	private JTextArea txtFolders;

	private JButton btnCreate;
	private JButton btnDeleteFolder;
	private JButton btnClose;

	private LiteratureManager manager;

	public FolderGUI(LiteratureManager manager) {
		this.manager = manager;

		setTitle("Manage Folders");
		setBounds(220, 220, 460, 360);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFolder = new JLabel("Folder");
		lblFolder.setBounds(40, 35, 80, 20);
		contentPane.add(lblFolder);

		txtFolder = new JTextField();
		txtFolder.setBounds(110, 35, 180, 25);
		contentPane.add(txtFolder);

		btnCreate = new JButton("Create");
		btnCreate.setBounds(305, 35, 100, 25);
		contentPane.add(btnCreate);

		txtFolders = new JTextArea();
		txtFolders.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(txtFolders);
		scrollPane.setBounds(40, 85, 360, 140);
		contentPane.add(scrollPane);

		btnDeleteFolder = new JButton("Delete Folder");
		btnDeleteFolder.setBounds(70, 250, 130, 30);
		contentPane.add(btnDeleteFolder);

		btnClose = new JButton("Close");
		btnClose.setBounds(250, 250, 100, 30);
		contentPane.add(btnClose);

		refreshFolderText();

		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createFolder();
			}
		});

		btnDeleteFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteFolder();
			}
		});

		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	void createFolder() {
		String folder = txtFolder.getText();
		manager.createFolder(folder);

		txtFolder.setText("");
		refreshFolderText();
	}

	void deleteFolder() {
		String folder = JOptionPane.showInputDialog(this,
				"Enter folder name to delete:\nReferences in this folder will also be deleted.");

		if (folder == null) {
			return;
		}

		boolean success = manager.deleteFolder(folder);

		if (success) {
			refreshFolderText();
		} else {
			JOptionPane.showMessageDialog(this, "Folder not found.");
		}
	}

	void refreshFolderText() {
		txtFolders.setText("");

		ArrayList<String> folders = manager.getFolderList();

		if (folders.size() == 0) {
			txtFolders.setText("No folders found.");
			return;
		}

		for (String folder : folders) {
			txtFolders.append("- " + folder + "\n");
		}
	}
}