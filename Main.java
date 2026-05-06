
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LiteratureManager manager = new LiteratureManager();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            printMenu();

            System.out.print("Choose an option: ");
            int choice = readInt(scanner);

            switch (choice) {
                case 1:
                    addLiterature(scanner, manager);
                    break;

                case 2:
                    manager.viewAllLiterature();
                    break;

                case 3:
                    editLiterature(scanner, manager);
                    break;

                case 4:
                    deleteLiterature(scanner, manager);
                    break;

                case 5:
                    searchLiterature(scanner, manager);
                    break;

                case 6:
                    manager.sortByTitle();
                    break;

                case 7:
                    manager.sortByYear();
                    break;

                case 8:
                    createSubjectFolder(scanner, manager);
                    break;

                case 9:
                    manager.viewSubjectFolders();
                    break;

                case 10:
                    viewBySubject(scanner, manager);
                    break;

                case 0:
                    running = false;
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    public static void printMenu() {
        System.out.println("\n===== Personal Literature Management System =====");
        System.out.println("1. Add Literature");
        System.out.println("2. View All Literature");
        System.out.println("3. Edit Literature");
        System.out.println("4. Delete Literature");
        System.out.println("5. Search Literature by Title");
        System.out.println("6. Sort Literature by Title");
        System.out.println("7. Sort Literature by Year");
        System.out.println("8. Create Subject Folder");
        System.out.println("9. View Subject Folders");
        System.out.println("10. View Literature by Subject");
        System.out.println("0. Exit");
    }

    public static void addLiterature(Scanner scanner, LiteratureManager manager) {
        scanner.nextLine();

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter DOI: ");
        String doi = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter year: ");
        int year = readInt(scanner);
        scanner.nextLine();

        System.out.print("Enter subject folder: ");
        String subject = scanner.nextLine();

        Literature literature = new Literature(title, doi, author, year, subject);
        manager.addLiterature(literature);

        System.out.println("Literature added successfully.");
    }

    public static void editLiterature(Scanner scanner, LiteratureManager manager) {
        scanner.nextLine();

        System.out.print("Enter the title of the literature you want to edit: ");
        String oldTitle = scanner.nextLine();

        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine();

        System.out.print("Enter new DOI: ");
        String newDoi = scanner.nextLine();

        System.out.print("Enter new author: ");
        String newAuthor = scanner.nextLine();

        System.out.print("Enter new year: ");
        int newYear = readInt(scanner);
        scanner.nextLine();

        System.out.print("Enter new subject folder: ");
        String newSubject = scanner.nextLine();

        boolean success = manager.editByTitle(oldTitle, newTitle, newDoi, newAuthor, newYear, newSubject);

        if (success) {
            System.out.println("Literature updated successfully.");
        } else {
            System.out.println("Literature not found.");
        }
    }

    public static void deleteLiterature(Scanner scanner, LiteratureManager manager) {
        scanner.nextLine();

        System.out.print("Enter the title of the literature you want to delete: ");
        String title = scanner.nextLine();

        boolean success = manager.deleteByTitle(title);

        if (success) {
            System.out.println("Literature deleted successfully.");
        } else {
            System.out.println("Literature not found.");
        }
    }

    public static void searchLiterature(Scanner scanner, LiteratureManager manager) {
        scanner.nextLine();

        System.out.print("Enter title keyword: ");
        String keyword = scanner.nextLine();

        manager.searchAndPrintByTitle(keyword);
    }

    public static void createSubjectFolder(Scanner scanner, LiteratureManager manager) {
        scanner.nextLine();

        System.out.print("Enter subject folder name: ");
        String subject = scanner.nextLine();

        manager.createSubjectFolder(subject);
    }

    public static void viewBySubject(Scanner scanner, LiteratureManager manager) {
        scanner.nextLine();

        System.out.print("Enter subject folder name: ");
        String subject = scanner.nextLine();

        manager.viewBySubject(subject);
    }

    public static int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
