import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchLiteratureGUI extends JFrame {

    private JPanel contentPane;
    private JTextField txtKeyword;
    private JTextArea txtResult;

    private JButton btnSearch;
    private JButton btnReset;
    private JButton btnClose;

    private LiteratureManager manager;

    public SearchLiteratureGUI(LiteratureManager manager) {

        /*
         * This GUI implements title-based retrieval.
         * It searches only the title field, matching the proposal's search requirement.
         */
        this.manager = manager;

        setTitle("Search Literature by Title");
        setBounds(200, 200, 600, 430);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblKeyword = new JLabel("Title Keyword");
        lblKeyword.setBounds(40, 35, 110, 20);
        contentPane.add(lblKeyword);

        txtKeyword = new JTextField();
        txtKeyword.setBounds(155, 35, 260, 25);
        contentPane.add(txtKeyword);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(430, 35, 100, 25);
        contentPane.add(btnSearch);

        txtResult = new JTextArea();
        txtResult.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtResult);
        scrollPane.setBounds(40, 85, 490, 230);
        contentPane.add(scrollPane);

        btnReset = new JButton("Reset");
        btnReset.setBounds(165, 335, 100, 30);
        contentPane.add(btnReset);

        btnClose = new JButton("Close");
        btnClose.setBounds(315, 335, 100, 30);
        contentPane.add(btnClose);

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchLiterature();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtKeyword.setText("");
                txtResult.setText("");
            }
        });

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void searchLiterature() {

        String keyword = txtKeyword.getText().trim();

        ArrayList<Literature> result = manager.searchByTitleKeyword(keyword);

        if (result.size() == 0) {
            txtResult.setText("No matching literature found.");
            return;
        }

        String text = "";

        for (int i = 0; i < result.size(); i++) {
            text += "Result " + (i + 1) + "\n";
            text += result.get(i).toDisplayString() + "\n";
            text += "------------------------------\n";
        }

        txtResult.setText(text);
    }
}
