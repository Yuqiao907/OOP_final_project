import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewLiteratureGUI extends JFrame {

    private JPanel contentPane;
    private JTextArea txtRecords;

    private JButton btnRefresh;
    private JButton btnSortTitle;
    private JButton btnSortYearAsc;
    private JButton btnSortYearDesc;
    private JButton btnClose;

    private LiteratureManager manager;

    public ViewLiteratureGUI(LiteratureManager manager) {

        /*
         * This GUI implements Read and Sort operations.
         * It provides aggregated metadata visualization, as required by the proposal.
         */
        this.manager = manager;

        setTitle("View and Sort Literature");
        setBounds(180, 180, 620, 520);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtRecords = new JTextArea();
        txtRecords.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtRecords);
        scrollPane.setBounds(30, 30, 540, 320);
        contentPane.add(scrollPane);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(30, 375, 110, 30);
        contentPane.add(btnRefresh);

        btnSortTitle = new JButton("Sort by Title");
        btnSortTitle.setBounds(155, 375, 130, 30);
        contentPane.add(btnSortTitle);

        btnSortYearAsc = new JButton("Year Asc");
        btnSortYearAsc.setBounds(300, 375, 110, 30);
        contentPane.add(btnSortYearAsc);

        btnSortYearDesc = new JButton("Year Desc");
        btnSortYearDesc.setBounds(425, 375, 120, 30);
        contentPane.add(btnSortYearDesc);

        btnClose = new JButton("Close");
        btnClose.setBounds(240, 425, 110, 30);
        contentPane.add(btnClose);

        refreshRecords();

        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshRecords();
            }
        });

        btnSortTitle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manager.sortByTitle();
                refreshRecords();
            }
        });

        btnSortYearAsc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manager.sortByYearAscending();
                refreshRecords();
            }
        });

        btnSortYearDesc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manager.sortByYearDescending();
                refreshRecords();
            }
        });

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void refreshRecords() {
        txtRecords.setText(manager.getAllLiteratureText());
    }
}
