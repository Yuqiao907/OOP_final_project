public class Literature {

    /*
     * These five fields follow the exact metadata schema in the proposal:
     * Title, DOI, Author Name, Year, and Subject Folder mapping.
     *
     * The system does not store PDFs, citation formats, login data, cloud data,
     * or external API data because those are outside the project scope.
     */
    private String title;
    private String doi;
    private String author;
    private int year;
    private String subjectFolder;

    public Literature(String title, String doi, String author, int year, String subjectFolder) {
        this.title = title;
        this.doi = doi;
        this.author = author;
        this.year = year;
        this.subjectFolder = subjectFolder;
    }

    public String getTitle() {
        return title;
    }

    public String getDoi() {
        return doi;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getSubjectFolder() {
        return subjectFolder;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSubjectFolder(String subjectFolder) {
        this.subjectFolder = subjectFolder;
    }

    public String toDisplayString() {

        /*
         * This method creates a readable display of one literature record.
         * It is used by the GUI when showing search results or all records.
         */
        return "Title: " + title
                + "\nDOI: " + doi
                + "\nAuthor: " + author
                + "\nYear: " + year
                + "\nSubject Folder: " + subjectFolder;
    }

    @Override
    public String toString() {
        return title + " | " + doi + " | " + author + " | " + year + " | " + subjectFolder;
    }
}
