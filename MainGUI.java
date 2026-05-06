
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnAddNew;
	private JButton btnSearch;
	private JButton btnBrowse;
	private JButton btnManageFolders;
	private JButton btnSorting;

	private LiteratureManager manager = new LiteratureManager();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainGUI() {
		setTitle("Literature Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 260, 390);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnAddNew = new JButton("Add New");
		btnAddNew.setBounds(55, 35, 140, 30);
		contentPane.add(btnAddNew);

		btnSearch = new JButton("Search");
		btnSearch.setBounds(55, 90, 140, 30);
		contentPane.add(btnSearch);

		btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(55, 145, 140, 30);
		contentPane.add(btnBrowse);

		btnManageFolders = new JButton("Manage Folders");
		btnManageFolders.setBounds(55, 200, 140, 30);
		contentPane.add(btnManageFolders);

		btnSorting = new JButton("Sorting");
		btnSorting.setBounds(55, 255, 140, 30);
		contentPane.add(btnSorting);

		btnAddNew.setFocusable(false);
		btnSearch.setFocusable(false);
		btnBrowse.setFocusable(false);
		btnManageFolders.setFocusable(false);
		btnSorting.setFocusable(false);

		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddLiteratureGUI addGUI = new AddLiteratureGUI(manager);
				addGUI.setVisible(true);
			}
		});

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchGUI searchGUI = new SearchGUI(manager);
				searchGUI.setVisible(true);
			}
		});

		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BrowseGUI browseGUI = new BrowseGUI(manager);
				browseGUI.setVisible(true);
			}
		});

		btnManageFolders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FolderGUI folderGUI = new FolderGUI(manager);
				folderGUI.setVisible(true);
			}
		});

		btnSorting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SortingGUI sortingGUI = new SortingGUI(manager);
				sortingGUI.setVisible(true);
			}
		});
	}
}