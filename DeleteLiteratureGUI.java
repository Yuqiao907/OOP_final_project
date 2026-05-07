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

public class DeleteLiteratureGUI extends JFrame {

    private JPanel contentPane;
    private JTextField txtTitle;
    private JTextArea txtPreview;

    private JButton btnFind;
    private JButton btnDelete;
    private JButton btnClose;

    private LiteratureManager manager;

    public DeleteLiteratureGUI(LiteratureManager manager) {

        /*
         * This GUI implements the Delete part of CRUD.
         * A preview is shown before deletion to reduce accidental deletion.
         */
        this.manager = manager;

        setTitle("Delete Literature");
        setBounds(230, 230, 560, 390);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Title");
        lblTitle.setBounds(45, 35, 80, 20);
        contentPane.add(lblTitle);

        txtTitle = new JTextField();
        txtTitle.setBounds(130, 35, 260, 25);
        contentPane.add(txtTitle);

        btnFind = new JButton("Find");
        btnFind.setBounds(410, 35, 90, 25);
        contentPane.add(btnFind);

        txtPreview = new JTextArea();
        txtPreview.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtPreview);
        scrollPane.setBounds(45, 85, 455, 170);
        contentPane.add(scrollPane);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(140, 285, 100, 30);
        contentPane.add(btnDelete);

        btnClose = new JButton("Close");
        btnClose.setBounds(300, 285, 100, 30);
        contentPane.add(btnClose);

        btnFind.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findLiterature();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteLiterature();
            }
        });

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void findLiterature() {

        String title = txtTitle.getText().trim();

        Literature lit = manager.findByTitle(title);

        if (lit == null) {
            txtPreview.setText("Literature record not found.");
            return;
        }

        txtPreview.setText(lit.toDisplayString());
    }

    private void deleteLiterature() {

        String title = txtTitle.getText().trim();

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this literature record?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        boolean success = manager.deleteByTitle(title);

        if (success) {
            JOptionPane.showMessageDialog(this, "Literature record deleted successfully.");
            txtTitle.setText("");
            txtPreview.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Literature record not found.");
        }
    }
}
