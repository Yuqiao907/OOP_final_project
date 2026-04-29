
import java.util.ArrayList;

public class LiteratureManager {

	private ArrayList<Literature> records;
	private ArrayList<String> folderList;

	public LiteratureManager() {
		records = new ArrayList<Literature>();
		folderList = new ArrayList<String>();
	}

	public void addLiterature(Literature lit) {
		records.add(lit);

		String folder = lit.getSubject();
		if (folder.length() > 0 && !folderList.contains(folder)) {
			folderList.add(folder);
		}
	}

	public ArrayList<Literature> getRecords() {
		return records;
	}

	public ArrayList<String> getFolderList() {
		return folderList;
	}

	public Literature findByTitle(String title) {
		for (Literature lit : records) {
			if (lit.getTitle().equalsIgnoreCase(title)) {
				return lit;
			}
		}
		return null;
	}

	public ArrayList<Literature> searchByTitleKeyword(String keyword) {
		ArrayList<Literature> result = new ArrayList<Literature>();

		for (Literature lit : records) {
			if (lit.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
				result.add(lit);
			}
		}

		return result;
	}

	public boolean deleteByTitle(String title) {
		Literature lit = findByTitle(title);

		if (lit != null) {
			records.remove(lit);
			return true;
		}

		return false;
	}

	public void createFolder(String folder) {
		if (folder.length() > 0 && !folderList.contains(folder)) {
			folderList.add(folder);
		}
	}

	public boolean deleteFolder(String folder) {
		if (!folderList.contains(folder)) {
			return false;
		}

		folderList.remove(folder);

		for (int i = records.size() - 1; i >= 0; i--) {
			if (records.get(i).getSubject().equalsIgnoreCase(folder)) {
				records.remove(i);
			}
		}

		return true;
	}

	public void sortByTitle() {
		records.sort((a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
	}

	public void sortByYear() {
		records.sort((a, b) -> b.getYear() - a.getYear());
	}

	// These methods are kept for the old Main.java CLI version

	public void viewAllLiterature() {
		if (records.size() == 0) {
			System.out.println("No literature records found.");
			return;
		}

		for (int i = 0; i < records.size(); i++) {
			System.out.println("\nRecord " + (i + 1));
			System.out.println(records.get(i));
		}
	}

	public void searchAndPrintByTitle(String keyword) {
		ArrayList<Literature> result = searchByTitleKeyword(keyword);

		if (result.size() == 0) {
			System.out.println("No matching literature found.");
			return;
		}

		for (Literature lit : result) {
			System.out.println("\n" + lit);
		}
	}

	public boolean editByTitle(String oldTitle, String newTitle, String newDoi, String newAuthor, int newYear,
			String newSubject) {
		Literature lit = findByTitle(oldTitle);

		if (lit == null) {
			return false;
		}

		lit.setTitle(newTitle);
		lit.setDoi(newDoi);
		lit.setAuthor(newAuthor);
		lit.setYear(newYear);
		lit.setSubject(newSubject);

		createFolder(newSubject);

		return true;
	}

	public void createSubjectFolder(String subject) {
		createFolder(subject);
	}

	public void viewSubjectFolders() {
		if (folderList.size() == 0) {
			System.out.println("No subject folders found.");
			return;
		}

		for (String folder : folderList) {
			System.out.println("- " + folder);
		}
	}

	public void viewBySubject(String subject) {
		boolean found = false;

		for (Literature lit : records) {
			if (lit.getSubject().equalsIgnoreCase(subject)) {
				System.out.println("\n" + lit);
				found = true;
			}
		}

		if (!found) {
			System.out.println("No literature found in this folder.");
		}
	}
}