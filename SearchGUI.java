import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private JTextArea txtResult;

	private JButton btnSearch;
	private JButton btnReset;
	private JButton btnCancel;

	private LiteratureManager manager;

	public SearchGUI(LiteratureManager manager) {
		this.manager = manager;

		setTitle("Search Literature");
		setBounds(180, 180, 560, 360);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSearch = new JLabel("Title Keyword");
		lblSearch.setBounds(40, 35, 100, 20);
		contentPane.add(lblSearch);

		txtSearch = new JTextField();
		txtSearch.setBounds(150, 35, 230, 25);
		contentPane.add(txtSearch);

		btnSearch = new JButton("Search");
		btnSearch.setBounds(400, 35, 100, 25);
		contentPane.add(btnSearch);

		btnReset = new JButton("Reset");
		btnReset.setBounds(40, 80, 100, 25);
		contentPane.add(btnReset);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(160, 80, 100, 25);
		contentPane.add(btnCancel);

		txtResult = new JTextArea();
		txtResult.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(txtResult);
		scrollPane.setBounds(40, 125, 460, 160);
		contentPane.add(scrollPane);

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchLiterature();
			}
		});

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearch.setText("");
				txtResult.setText("");
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	void searchLiterature() {
		String keyword = txtSearch.getText();
		txtResult.setText("");

		ArrayList<Literature> result = manager.searchByTitleKeyword(keyword);

		if (result.size() == 0) {
			txtResult.setText("No matching reference found.");
			return;
		}

		for (Literature lit : result) {
			txtResult.append(formatLiterature(lit));
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