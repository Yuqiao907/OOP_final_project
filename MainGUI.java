import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {

    private JPanel contentPane;

    private JButton btnAdd;
    private JButton btnView;
    private JButton btnSearch;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnFolders;
    private JButton btnExit;

    private LiteratureManager manager;

    public MainGUI(LiteratureManager manager) {

        /*
         * MainGUI is the central interaction layer.
         * It gives the user access to all major project use cases from one window.
         */
        this.manager = manager;

        setTitle("Personal Literature Management System");
        setBounds(100, 100, 520, 430);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Personal Literature Management System");
        lblTitle.setBounds(105, 25, 330, 30);
        contentPane.add(lblTitle);

        btnAdd = new JButton("Add Literature");
        btnAdd.setBounds(155, 75, 190, 35);
        contentPane.add(btnAdd);

        btnView = new JButton("View / Sort Literature");
        btnView.setBounds(155, 120, 190, 35);
        contentPane.add(btnView);

        btnSearch = new JButton("Search Literature");
        btnSearch.setBounds(155, 165, 190, 35);
        contentPane.add(btnSearch);

        btnEdit = new JButton("Edit Literature");
        btnEdit.setBounds(155, 210, 190, 35);
        contentPane.add(btnEdit);

        btnDelete = new JButton("Delete Literature");
        btnDelete.setBounds(155, 255, 190, 35);
        contentPane.add(btnDelete);

        btnFolders = new JButton("Manage Folders");
        btnFolders.setBounds(155, 300, 190, 35);
        contentPane.add(btnFolders);

        btnExit = new JButton("Exit");
        btnExit.setBounds(155, 345, 190, 35);
        contentPane.add(btnExit);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddLiteratureGUI frame = new AddLiteratureGUI(manager);
                frame.setVisible(true);
            }
        });

        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewLiteratureGUI frame = new ViewLiteratureGUI(manager);
                frame.setVisible(true);
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SearchLiteratureGUI frame = new SearchLiteratureGUI(manager);
                frame.setVisible(true);
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditLiteratureGUI frame = new EditLiteratureGUI(manager);
                frame.setVisible(true);
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteLiteratureGUI frame = new DeleteLiteratureGUI(manager);
                frame.setVisible(true);
            }
        });

        btnFolders.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FolderGUI frame = new FolderGUI(manager);
                frame.setVisible(true);
            }
        });

        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
