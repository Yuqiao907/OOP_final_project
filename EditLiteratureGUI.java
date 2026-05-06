import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditLiteratureGUI extends JFrame {

	private JPanel contentPane;

	private JTextField txtTitle;
	private JTextField txtDOI;
	private JTextField txtAuthor;
	private JTextField txtYear;
	private JTextField txtFolder;

	private JButton btnSave;
	private JButton btnCancel;

	private LiteratureManager manager;
	private Literature literature;
	private BrowseGUI browseGUI;

	public EditLiteratureGUI(LiteratureManager manager, Literature literature, BrowseGUI browseGUI) {
		this.manager = manager;
		this.literature = literature;
		this.browseGUI = browseGUI;

		setTitle("Edit Literature");
		setBounds(180, 180, 520, 360);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(50, 35, 80, 20);
		contentPane.add(lblTitle);

		txtTitle = new JTextField(literature.getTitle());
		txtTitle.setBounds(160, 35, 280, 25);
		contentPane.add(txtTitle);

		JLabel lblDOI = new JLabel("DOI");
		lblDOI.setBounds(50, 80, 80, 20);
		contentPane.add(lblDOI);

		txtDOI = new JTextField(literature.getDoi());
		txtDOI.setBounds(160, 80, 280, 25);
		contentPane.add(txtDOI);

		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(50, 125, 80, 20);
		contentPane.add(lblAuthor);

		txtAuthor = new JTextField(literature.getAuthor());
		txtAuthor.setBounds(160, 125, 280, 25);
		contentPane.add(txtAuthor);

		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(50, 170, 80, 20);
		contentPane.add(lblYear);

		txtYear = new JTextField(Integer.toString(literature.getYear()));
		txtYear.setBounds(160, 170, 280, 25);
		contentPane.add(txtYear);

		JLabel lblFolder = new JLabel("Folder");
		lblFolder.setBounds(50, 215, 80, 20);
		contentPane.add(lblFolder);

		txtFolder = new JTextField(literature.getSubject());
		txtFolder.setBounds(160, 215, 280, 25);
		contentPane.add(txtFolder);

		btnSave = new JButton("Save");
		btnSave.setBounds(130, 270, 90, 30);
		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(280, 270, 90, 30);
		contentPane.add(btnCancel);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveChanges();
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	void saveChanges() {
		try {
			literature.setTitle(txtTitle.getText());
			literature.setDoi(txtDOI.getText());
			literature.setAuthor(txtAuthor.getText());
			literature.setYear(Integer.parseInt(txtYear.getText()));
			literature.setSubject(txtFolder.getText());

			manager.createFolder(txtFolder.getText());

			browseGUI.refreshBrowseText();
			dispose();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Year must be a number.");
		}
	}
}