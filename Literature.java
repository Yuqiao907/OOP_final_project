
public class Literature {
    private String title;
    private String doi;
    private String author;
    private int year;
    private String subject;

    public Literature(String title, String doi, String author, int year, String subject) {
        this.title = title;
        this.doi = doi;
        this.author = author;
        this.year = year;
        this.subject = subject;
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

    public String getSubject() {
        return subject;
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

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                "\nDOI: " + doi +
                "\nAuthor: " + author +
                "\nYear: " + year +
                "\nSubject: " + subject;
    }
}