package Main;

import Dao.*;
import Dashboard.*;
import Database.Database;
import Implementation.*;
import Model.*;
import Utility.CommandHandler;
import Utility.ExitHandler;
import Utility.InputUtility;

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
    // Utilities
    private final InputUtility inputUtility;
    private final ExitHandler exitHandler;
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

        // Utility
        this.inputUtility = new InputUtility();
        this.exitHandler = new ExitHandler(scanner);
    }
    public void start() {
        // System's Introduction
        System.out.println("Book Buddy: A Library Management System. [Version 1.0.0.0]");
        System.out.println("Javarian Corporation. All rights reserved.");

        CommandHandler commandHandler = new CommandHandler(adminDashboard, scanner);

        while (true) {
            // Show front dashboard and get user's choice
            String chooseDashboard = adminDashboard.frontDashboard();

            if(commandHandler.handleCommand(chooseDashboard)){
                continue;
            }
            switch (chooseDashboard) {
                case "1" -> {
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
                            inputUtility.InvalidInputs();
                        } else {
                            inputUtility.InvalidInputs();
                        }
                    }
                }
                case "2" -> {
                    while (true) {
                        superAdminDashboard.superAdmin();
                    }
                }
                case "3" -> {
                    adminDashboard.helpDashboard();
                }
                case "4" -> {
                    exitHandler.confirmExit();
                }
                default -> inputUtility.InvalidInputs();
            }
        }
    }
}