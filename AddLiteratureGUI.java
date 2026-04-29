import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddLiteratureGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtDOI;
	private JTextField txtAuthor;
	private JTextField txtYear;
	private JTextField txtFolder;

	private JButton btnSave;
	private JButton btnReset;
	private JButton btnCancel;

	private LiteratureManager manager;

	public AddLiteratureGUI(LiteratureManager manager) {
		this.manager = manager;

		setTitle("Add New Literature");
		setBounds(150, 150, 520, 360);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(50, 35, 80, 20);
		contentPane.add(lblTitle);

		txtTitle = new JTextField();
		txtTitle.setBounds(160, 35, 280, 25);
		contentPane.add(txtTitle);

		JLabel lblDOI = new JLabel("DOI");
		lblDOI.setBounds(50, 80, 80, 20);
		contentPane.add(lblDOI);

		txtDOI = new JTextField();
		txtDOI.setBounds(160, 80, 280, 25);
		contentPane.add(txtDOI);

		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(50, 125, 80, 20);
		contentPane.add(lblAuthor);

		txtAuthor = new JTextField();
		txtAuthor.setBounds(160, 125, 280, 25);
		contentPane.add(txtAuthor);

		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(50, 170, 80, 20);
		contentPane.add(lblYear);

		txtYear = new JTextField();
		txtYear.setBounds(160, 170, 280, 25);
		contentPane.add(txtYear);

		JLabel lblFolder = new JLabel("Folder");
		lblFolder.setBounds(50, 215, 80, 20);
		contentPane.add(lblFolder);

		txtFolder = new JTextField();
		txtFolder.setBounds(160, 215, 280, 25);
		contentPane.add(txtFolder);

		btnSave = new JButton("Save");
		btnSave.setBounds(80, 270, 90, 30);
		contentPane.add(btnSave);

		btnReset = new JButton("Reset");
		btnReset.setBounds(210, 270, 90, 30);
		contentPane.add(btnReset);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(340, 270, 90, 30);
		contentPane.add(btnCancel);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveLiterature();
			}
		});

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFields();
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	void saveLiterature() {
		try {
			String title = txtTitle.getText();
			String doi = txtDOI.getText();
			String author = txtAuthor.getText();
			int year = Integer.parseInt(txtYear.getText());
			String folder = txtFolder.getText();

			Literature lit = new Literature(title, doi, author, year, folder);
			manager.addLiterature(lit);

			resetFields();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Year must be a number.");
		}
	}

	void resetFields() {
		txtTitle.setText("");
		txtDOI.setText("");
		txtAuthor.setText("");
		txtYear.setText("");
		txtFolder.setText("");
	}
}