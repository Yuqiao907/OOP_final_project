import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

        /*
         * This GUI implements the Create part of CRUD.
         * It collects only the five metadata fields allowed by the proposal.
         */
        this.manager = manager;

        setTitle("Add New Literature");
        setBounds(150, 150, 520, 360);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Title");
        lblTitle.setBounds(50, 35, 100, 20);
        contentPane.add(lblTitle);

        txtTitle = new JTextField();
        txtTitle.setBounds(170, 35, 280, 25);
        contentPane.add(txtTitle);

        JLabel lblDOI = new JLabel("DOI");
        lblDOI.setBounds(50, 80, 100, 20);
        contentPane.add(lblDOI);

        txtDOI = new JTextField();
        txtDOI.setBounds(170, 80, 280, 25);
        contentPane.add(txtDOI);

        JLabel lblAuthor = new JLabel("Author");
        lblAuthor.setBounds(50, 125, 100, 20);
        contentPane.add(lblAuthor);

        txtAuthor = new JTextField();
        txtAuthor.setBounds(170, 125, 280, 25);
        contentPane.add(txtAuthor);

        JLabel lblYear = new JLabel("Year");
        lblYear.setBounds(50, 170, 100, 20);
        contentPane.add(lblYear);

        txtYear = new JTextField();
        txtYear.setBounds(170, 170, 280, 25);
        contentPane.add(txtYear);

        JLabel lblFolder = new JLabel("Subject Folder");
        lblFolder.setBounds(50, 215, 100, 20);
        contentPane.add(lblFolder);

        txtFolder = new JTextField();
        txtFolder.setBounds(170, 215, 280, 25);
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

    private void saveLiterature() {

        try {
            String title = txtTitle.getText().trim();
            String doi = txtDOI.getText().trim();
            String author = txtAuthor.getText().trim();
            int year = Integer.parseInt(txtYear.getText().trim());
            String folder = txtFolder.getText().trim();

            Literature lit = new Literature(title, doi, author, year, folder);

            boolean success = manager.addLiterature(lit);

            if (success) {
                JOptionPane.showMessageDialog(this, "Literature record added successfully.");
                resetFields();
            } else {
                JOptionPane.showMessageDialog(this, "Title cannot be empty.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Year must be a number.");
        }
    }

    private void resetFields() {
        txtTitle.setText("");
        txtDOI.setText("");
        txtAuthor.setText("");
        txtYear.setText("");
        txtFolder.setText("");
    }
}
