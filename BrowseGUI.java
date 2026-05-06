import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseGUI extends JFrame {

	private JPanel contentPane;
	private JTextArea txtBrowse;

	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnClose;

	private LiteratureManager manager;

	public BrowseGUI(LiteratureManager manager) {
		this.manager = manager;

		setTitle("Browse Literature");
		setBounds(200, 200, 660, 440);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtBrowse = new JTextArea();
		txtBrowse.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(txtBrowse);
		scrollPane.setBounds(40, 30, 560, 240);
		contentPane.add(scrollPane);

		btnDelete = new JButton("Delete by Title");
		btnDelete.setBounds(55, 310, 140, 30);
		contentPane.add(btnDelete);

		btnEdit = new JButton("Edit by Title");
		btnEdit.setBounds(255, 310, 140, 30);
		contentPane.add(btnEdit);

		btnClose = new JButton("Close");
		btnClose.setBounds(455, 310, 100, 30);
		contentPane.add(btnClose);

		refreshBrowseText();

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteReference();
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editReference();
			}
		});

		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	void deleteReference() {
		String title = JOptionPane.showInputDialog(this, "Enter title to delete:");

		if (title == null) {
			return;
		}

		boolean success = manager.deleteByTitle(title);

		if (success) {
			refreshBrowseText();
		} else {
			JOptionPane.showMessageDialog(this, "Reference not found.");
		}
	}

	void editReference() {
		String title = JOptionPane.showInputDialog(this, "Enter title to edit:");

		if (title == null) {
			return;
		}

		Literature lit = manager.findByTitle(title);

		if (lit != null) {
			EditLiteratureGUI editGUI = new EditLiteratureGUI(manager, lit, this);
			editGUI.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "Reference not found.");
		}
	}

	public void refreshBrowseText() {
		txtBrowse.setText("");

		ArrayList<Literature> records = manager.getRecords();

		if (records.size() == 0) {
			txtBrowse.setText("No literature records found.");
			return;
		}

		for (int i = 0; i < records.size(); i++) {
			txtBrowse.append("Record " + (i + 1) + "\n");
			txtBrowse.append(formatLiterature(records.get(i)));
		}
	}

	String formatLiterature(Literature lit) {
		return "Title: " + lit.getTitle() + "\n"
				+ "DOI: " + lit.getDoi() + "\n"
				+ "Author: " + lit.getAuthor() + "\n"
				+ "Year: " + lit.getYear() + "\n"
				+ "Folder: " + lit.getSubject() + "\n"
				+ "-----------------------------\n";
	}
}