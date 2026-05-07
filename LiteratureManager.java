import java.util.ArrayList;
import java.util.HashMap;

public class LiteratureManager {

    /*
     * records stores all literature records in a dynamic list.
     * This follows the proposal's requirement that dynamic lists are used
     * for record storage.
     */
    private ArrayList<Literature> records;

    /*
     * folderMap stores folder names as keys and the list of literature records
     * in each folder as values.
     *
     * This follows the proposal's requirement that associative maps are used
     * for folder tracking.
     */
    private HashMap<String, ArrayList<Literature>> folderMap;

    public LiteratureManager() {
        records = new ArrayList<Literature>();
        folderMap = new HashMap<String, ArrayList<Literature>>();
    }

    public boolean addLiterature(Literature lit) {

        /*
         * This method implements high-fidelity record ingestion.
         * A new literature object is added only if it is not null
         * and has a non-empty title.
         */
        if (lit == null) {
            return false;
        }

        if (lit.getTitle() == null || lit.getTitle().trim().length() == 0) {
            return false;
        }

        records.add(lit);

        /*
         * The literature record is also mapped to its subject folder.
         * This supports the proposal's entity-to-category mapping requirement.
         */
        addRecordToFolderMap(lit, lit.getSubjectFolder());

        return true;
    }

    public ArrayList<Literature> getRecords() {
        return records;
    }

    public ArrayList<String> getFolderList() {

        /*
         * The GUI needs a simple list of folder names.
         * The folder names are the keys of the HashMap.
         */
        return new ArrayList<String>(folderMap.keySet());
    }

    public Literature findByTitle(String title) {

        /*
         * This method supports exact-title lookup.
         * It is used by edit, delete, and assignment operations.
         */
        if (title == null) {
            return null;
        }

        for (Literature lit : records) {
            if (lit.getTitle().equalsIgnoreCase(title.trim())) {
                return lit;
            }
        }

        return null;
    }

    public ArrayList<Literature> searchByTitleKeyword(String keyword) {

        /*
         * This method implements query-based information retrieval.
         * It searches title string patterns only, exactly as required
         * by the proposal.
         */
        ArrayList<Literature> result = new ArrayList<Literature>();

        if (keyword == null) {
            return result;
        }

        for (Literature lit : records) {
            if (lit.getTitle().toLowerCase().contains(keyword.toLowerCase().trim())) {
                result.add(lit);
            }
        }

        return result;
    }

    public boolean editByTitle(String oldTitle, String newTitle, String newDoi,
                               String newAuthor, int newYear, String newSubjectFolder) {

        /*
         * This method implements the Update part of CRUD.
         * The old title identifies the record to be updated.
         */
        Literature lit = findByTitle(oldTitle);

        if (lit == null) {
            return false;
        }

        String oldFolder = lit.getSubjectFolder();

        lit.setTitle(newTitle);
        lit.setDoi(newDoi);
        lit.setAuthor(newAuthor);
        lit.setYear(newYear);
        lit.setSubjectFolder(newSubjectFolder);

        /*
         * Because the folder may have changed, the folder map must be updated.
         * This keeps the literature object and folder tracking structure consistent.
         */
        removeRecordFromFolderMap(lit, oldFolder);
        addRecordToFolderMap(lit, newSubjectFolder);

        return true;
    }

    public boolean deleteByTitle(String title) {

        /*
         * This method implements the Delete part of CRUD.
         * It removes the literature record from the main list and from the folder map.
         */
        Literature lit = findByTitle(title);

        if (lit == null) {
            return false;
        }

        records.remove(lit);
        removeRecordFromFolderMap(lit, lit.getSubjectFolder());

        return true;
    }

    public void sortByTitle() {

        /*
         * This method implements algorithmic metadata sorting
         * by alphabetical title order.
         */
        records.sort((a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
    }

    public void sortByYearAscending() {

        /*
         * This method implements chronological sorting from oldest to newest.
         */
        records.sort((a, b) -> a.getYear() - b.getYear());
    }

    public void sortByYearDescending() {

        /*
         * This method implements chronological sorting from newest to oldest.
         */
        records.sort((a, b) -> b.getYear() - a.getYear());
    }

    public void createFolder(String folder) {

        /*
         * This method implements hierarchical taxonomy administration.
         * It creates a new subject folder if it does not already exist.
         */
        if (folder == null) {
            return;
        }

        folder = folder.trim();

        if (folder.length() > 0 && !folderMap.containsKey(folder)) {
            folderMap.put(folder, new ArrayList<Literature>());
        }
    }

    public boolean deleteFolder(String folder) {

        /*
         * This method deletes a subject folder category.
         *
         * Important design choice:
         * It does NOT delete literature records inside that folder.
         * The proposal describes the system as a metadata repository.
         * Therefore, deleting a folder should not destroy stored bibliographic metadata.
         *
         * Instead, records in the deleted folder become unassigned.
         */
        if (folder == null) {
            return false;
        }

        folder = folder.trim();

        if (!folderMap.containsKey(folder)) {
            return false;
        }

        for (Literature lit : records) {
            if (lit.getSubjectFolder().equalsIgnoreCase(folder)) {
                lit.setSubjectFolder("");
            }
        }

        folderMap.remove(folder);
        return true;
    }

    public boolean assignLiteratureToFolder(String title, String folder) {

        /*
         * This method implements entity-to-category mapping.
         * A literature record can be assigned to a selected subject folder.
         */
        Literature lit = findByTitle(title);

        if (lit == null) {
            return false;
        }

        String oldFolder = lit.getSubjectFolder();

        lit.setSubjectFolder(folder);

        removeRecordFromFolderMap(lit, oldFolder);
        addRecordToFolderMap(lit, folder);

        return true;
    }

    public ArrayList<Literature> getRecordsByFolder(String folder) {

        /*
         * This method retrieves all literature records inside one folder.
         * It supports rapid retrieval of categorized academic assets.
         */
        ArrayList<Literature> result = new ArrayList<Literature>();

        if (folder == null) {
            return result;
        }

        folder = folder.trim();

        if (folderMap.containsKey(folder)) {
            result.addAll(folderMap.get(folder));
        }

        return result;
    }

    public String getAllLiteratureText() {

        /*
         * This method implements aggregated metadata visualization.
         * It returns all records in a readable text format for the GUI.
         */
        if (records.size() == 0) {
            return "No literature records found.";
        }

        String text = "";

        for (int i = 0; i < records.size(); i++) {
            text += "Record " + (i + 1) + "\n";
            text += records.get(i).toDisplayString() + "\n";
            text += "------------------------------\n";
        }

        return text;
    }

    public String getFolderText() {

        /*
         * This method returns all subject folders for folder auditing.
         */
        ArrayList<String> folders = getFolderList();

        if (folders.size() == 0) {
            return "No subject folders found.";
        }

        String text = "";

        for (String folder : folders) {
            text += "- " + folder + "\n";
        }

        return text;
    }

    private void addRecordToFolderMap(Literature lit, String folder) {

        /*
         * Private helper method.
         * It centralizes folder insertion logic so public methods do not duplicate code.
         */
        if (folder == null) {
            return;
        }

        folder = folder.trim();

        if (folder.length() == 0) {
            return;
        }

        createFolder(folder);

        ArrayList<Literature> list = folderMap.get(folder);

        if (!list.contains(lit)) {
            list.add(lit);
        }
    }

    private void removeRecordFromFolderMap(Literature lit, String folder) {

        /*
         * Private helper method.
         * It keeps the HashMap consistent when a record is edited, deleted,
         * or reassigned.
         */
        if (folder == null) {
            return;
        }

        folder = folder.trim();

        if (folder.length() == 0) {
            return;
        }

        if (folderMap.containsKey(folder)) {
            folderMap.get(folder).remove(lit);
        }
    }
}
