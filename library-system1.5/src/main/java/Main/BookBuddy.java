package Main;

import Dao.*;
import Dashboard.*;
import Database.Database;
import Implementation.*;
import Model.*;

import java.util.Scanner;

public class BookBuddy {

    // Dependencies
    private final Database database;
    private final BookDAO bookDao;
    private ReturnBook returnBook;
    private final AuthorDAO authorDao;
    private final PublisherDAO publisherDAO;
    private final AdminDAO adminDAO;
    private final BorrowBookDAO borrowBookDAO;
    private final ReturnBookDAO returnBookDAO;
    private final Scanner scanner;

    // Dashboards
    private final AdminDashboard adminDashboard;
    private final DisplayDashboards displayDashboards;
    private final BooksDashboard booksDashboard;
    private final AuthorsDashboard authorsDashboard;
    private final PublishersDashboard publishersDashboard;
    private final BorrowBooksDashboard borrowBooksDashboard;
    private final ReturnBookDashboard returnBookDashboard;
    private final AccountSettingDashboard accountSettingDashboard;
    private final SuperAdminDashboard superAdminDashboard;
    // Constructor initializes all dependencies
    public BookBuddy() {
        // Core dependencies
        this.database = new Database();
        this.scanner = new Scanner(System.in);

        // DAOs
        this.bookDao = new BookDAOImplementation(database);
        this.returnBook = new ReturnBook();
        this.authorDao = new AuthorDAOImplementation(database);
        this.publisherDAO = new PublisherDAOImplementation(database);
        this.adminDAO = new AdminDAOImplementation(database);
        this.borrowBookDAO = new BorrowBookDAOImplementation(database, adminDAO);
        this.returnBookDAO = new ReturnBookDAOImplementation(database);

        // Dashboards
        this.adminDashboard = new AdminDashboard(adminDAO, scanner);
        this.displayDashboards = new DisplayDashboards();
        this.booksDashboard = new BooksDashboard();
        this.authorsDashboard = new AuthorsDashboard();
        this.publishersDashboard = new PublishersDashboard();
        this.borrowBooksDashboard = new BorrowBooksDashboard();
        this.returnBookDashboard = new ReturnBookDashboard(adminDAO);
        this.accountSettingDashboard = new AccountSettingDashboard(adminDAO, scanner);
        this.superAdminDashboard = new SuperAdminDashboard(scanner, adminDAO, adminDashboard);
    }

    public void start() {
        // System's Introduction
        System.out.println("Book Buddy: A Library Management System. [Version 1.0.0.0]");
        System.out.println("Javarian Corporation. All rights reserved.");

        while (true) {
            // Show front dashboard and get user's choice
            byte chooseDashboard = adminDashboard.frontDashboard();

            if (chooseDashboard == 1) {
                // Try login
                Admin admin = adminDashboard.loginDashboard();
                if (admin == null) {
                    continue; // If login fails (either wrong credentials or deactivated account), show the front dashboard again
                }
                // Login successful, proceed with the admin dashboard
                while (true) {
                    boolean isSuperAdmin = adminDAO.isSuperAdmin(admin);
                    byte choiceAdminDashboard = adminDashboard.adminDashboard(isSuperAdmin);
                    if (choiceAdminDashboard == 1) {
                        displayDashboards.displayOne(booksDashboard, bookDao, scanner);
                    } else if (choiceAdminDashboard == 2) {
                        displayDashboards.displayTwo(authorsDashboard, authorDao, scanner);
                    } else if (choiceAdminDashboard == 3) {
                        displayDashboards.displayThree(publishersDashboard, publisherDAO, scanner);
                    } else if (choiceAdminDashboard == 4) {
                        displayDashboards.displayFour(borrowBooksDashboard, scanner, borrowBookDAO, admin);
                    } else if (choiceAdminDashboard == 5) {
                        displayDashboards.displayFive(returnBookDashboard, scanner, returnBookDAO, returnBook, admin);
                    } else if (choiceAdminDashboard == 6) {
                        boolean isLoggedOut = displayDashboards.displaySix(scanner, admin);
                        if (isLoggedOut)
                            break;
                    } else if (choiceAdminDashboard == 7 && isSuperAdmin) {
                        accountSettingDashboard.displayAccountSettings();
                    } else if (choiceAdminDashboard == 7) {
                        InvalidInputs();
                    } else {
                        InvalidInputs();
                    }
                }
            } else if (chooseDashboard == 2) {
                // Registration logic (if needed)
                while (true) {
                    superAdminDashboard.superAdmin();
                }
            } else if (chooseDashboard == 3) {
                // Exit program
                System.out.println("Are you sure you want to exit the program? ");
                System.out.println("[1] YES");
                System.out.println("[2] NO");
                System.out.print("Enter your Option: ");
                byte choice = scanner.nextByte();
                scanner.nextLine();
                System.out.println("-------------------------------");
                if (choice == 1) {
                    System.out.println("Thank you for using our system. Goodbye!!");
                    System.out.println("-------------------------------");
                    System.exit(0); // Exit the program
                } else if (choice == 2) {
                    System.out.println("Going back to the Home Page");
                    System.out.println("-------------------------------");
                    // Return to the Menu
                } else {
                    InvalidInputs();
                }
            } else {
                InvalidInputs();
            }
        }
    }
    public void InvalidInputs(){
        System.out.println("Invalid Inputs. Please Try Again.");
        System.out.println("-------------------------------");
    }

}