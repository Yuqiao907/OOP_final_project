import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortingGUI extends JFrame {

	private JPanel contentPane;

	private JButton btnSortTitle;
	private JButton btnSortYear;
	private JButton btnClose;

	private LiteratureManager manager;

	public SortingGUI(LiteratureManager manager) {
		this.manager = manager;

		setTitle("Sorting");
		setBounds(230, 230, 260, 220);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnSortTitle = new JButton("Sort by Title");
		btnSortTitle.setBounds(55, 35, 140, 30);
		contentPane.add(btnSortTitle);

		btnSortYear = new JButton("Sort by Year");
		btnSortYear.setBounds(55, 90, 140, 30);
		contentPane.add(btnSortYear);

		btnClose = new JButton("Close");
		btnClose.setBounds(55, 145, 140, 30);
		contentPane.add(btnClose);

		btnSortTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.sortByTitle();
			}
		});

		btnSortYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.sortByYear();
			}
		});

		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}