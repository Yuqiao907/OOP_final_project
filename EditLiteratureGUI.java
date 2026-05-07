import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditLiteratureGUI extends JFrame {

    private JPanel contentPane;

    private JTextField txtOldTitle;
    private JTextField txtNewTitle;
    private JTextField txtNewDOI;
    private JTextField txtNewAuthor;
    private JTextField txtNewYear;
    private JTextField txtNewFolder;

    private JButton btnLoad;
    private JButton btnSave;
    private JButton btnReset;
    private JButton btnClose;

    private LiteratureManager manager;

    public EditLiteratureGUI(LiteratureManager manager) {

        /*
         * This GUI implements the Update part of CRUD.
         * It allows the user to modify only metadata fields allowed in the proposal.
         */
        this.manager = manager;

        setTitle("Edit Literature");
        setBounds(220, 220, 560, 450);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblOldTitle = new JLabel("Current Title");
        lblOldTitle.setBounds(45, 30, 110, 20);
        contentPane.add(lblOldTitle);

        txtOldTitle = new JTextField();
        txtOldTitle.setBounds(170, 30, 260, 25);
        contentPane.add(txtOldTitle);

        btnLoad = new JButton("Load");
        btnLoad.setBounds(445, 30, 80, 25);
        contentPane.add(btnLoad);

        JLabel lblNewTitle = new JLabel("New Title");
        lblNewTitle.setBounds(45, 85, 110, 20);
        contentPane.add(lblNewTitle);

        txtNewTitle = new JTextField();
        txtNewTitle.setBounds(170, 85, 280, 25);
        contentPane.add(txtNewTitle);

        JLabel lblNewDOI = new JLabel("New DOI");
        lblNewDOI.setBounds(45, 130, 110, 20);
        contentPane.add(lblNewDOI);

        txtNewDOI = new JTextField();
        txtNewDOI.setBounds(170, 130, 280, 25);
        contentPane.add(txtNewDOI);

        JLabel lblNewAuthor = new JLabel("New Author");
        lblNewAuthor.setBounds(45, 175, 110, 20);
        contentPane.add(lblNewAuthor);

        txtNewAuthor = new JTextField();
        txtNewAuthor.setBounds(170, 175, 280, 25);
        contentPane.add(txtNewAuthor);

        JLabel lblNewYear = new JLabel("New Year");
        lblNewYear.setBounds(45, 220, 110, 20);
        contentPane.add(lblNewYear);

        txtNewYear = new JTextField();
        txtNewYear.setBounds(170, 220, 280, 25);
        contentPane.add(txtNewYear);

        JLabel lblNewFolder = new JLabel("New Folder");
        lblNewFolder.setBounds(45, 265, 110, 20);
        contentPane.add(lblNewFolder);

        txtNewFolder = new JTextField();
        txtNewFolder.setBounds(170, 265, 280, 25);
        contentPane.add(txtNewFolder);

        btnSave = new JButton("Save Changes");
        btnSave.setBounds(65, 330, 130, 30);
        contentPane.add(btnSave);

        btnReset = new JButton("Reset");
        btnReset.setBounds(220, 330, 100, 30);
        contentPane.add(btnReset);

        btnClose = new JButton("Close");
        btnClose.setBounds(345, 330, 100, 30);
        contentPane.add(btnClose);

        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadLiterature();
            }
        });

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void loadLiterature() {

        String oldTitle = txtOldTitle.getText().trim();

        Literature lit = manager.findByTitle(oldTitle);

        if (lit == null) {
            JOptionPane.showMessageDialog(this, "Literature record not found.");
            return;
        }

        txtNewTitle.setText(lit.getTitle());
        txtNewDOI.setText(lit.getDoi());
        txtNewAuthor.setText(lit.getAuthor());
        txtNewYear.setText(String.valueOf(lit.getYear()));
        txtNewFolder.setText(lit.getSubjectFolder());
    }

    private void saveChanges() {

        try {
            String oldTitle = txtOldTitle.getText().trim();
            String newTitle = txtNewTitle.getText().trim();
            String newDoi = txtNewDOI.getText().trim();
            String newAuthor = txtNewAuthor.getText().trim();
            int newYear = Integer.parseInt(txtNewYear.getText().trim());
            String newFolder = txtNewFolder.getText().trim();

            boolean success = manager.editByTitle(oldTitle, newTitle, newDoi,
                    newAuthor, newYear, newFolder);

            if (success) {
                JOptionPane.showMessageDialog(this, "Literature record updated successfully.");
                resetFields();
            } else {
                JOptionPane.showMessageDialog(this, "Literature record not found.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Year must be a number.");
        }
    }

    private void resetFields() {
        txtOldTitle.setText("");
        txtNewTitle.setText("");
        txtNewDOI.setText("");
        txtNewAuthor.setText("");
        txtNewYear.setText("");
        txtNewFolder.setText("");
    }
}
