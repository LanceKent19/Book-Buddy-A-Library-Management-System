package Dashboard;
import Dao.*;
import Model.*;

import java.util.List;
import java.util.Scanner;

public class DisplayDashboards {

    public void displayOne(BooksDashboard booksDashboard, BookDAO bookDao, Scanner scanner){
        while (true) {
            byte chooseBooksDashboard = booksDashboard.booksDashboard();
            if (chooseBooksDashboard == 1) {
                booksDashboard.displayBooks(bookDao.getBooks());
            }
            else if (chooseBooksDashboard == 2){
                System.out.print("Book ID: ");
                System.out.println(bookDao.getSpecificBook(scanner.nextByte()));// change this if ever the records of book reach hundreds
                // the adminDAOImplementation handles the rendering for input while in here, this handles itself's rendering
                System.out.println("-------------------------------");
            }
            else if (chooseBooksDashboard == 3) {
                Book book = booksDashboard.createBook(scanner);
                if(bookDao.addBook(book)) {
                    System.out.println("Added successfully");
                    System.out.println("-------------------------------");
                }else {
                    System.out.println("An error has occurred");
                    System.out.println("-------------------------------");
                }
            }
            else if (chooseBooksDashboard == 4){
                Book book = booksDashboard.createUpdateBook(scanner);
                if(bookDao.updateBook(book)) {
                    System.out.println("Book ID " + book.getId() + " Updated Successfully");
                    System.out.println("-------------------------------");
                }
                else {
                    System.out.println("An error has occurred");
                    System.out.println("-------------------------------");
                }
            }
            else if (chooseBooksDashboard == 5) {
                System.out.print("Delete Book ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("-------------------------------");
                if (bookDao.deleteBook(id)) {
                    System.out.println("Book ID " + id + " Deleted");
                    System.out.println("-------------------------------");
                } else {
                    System.out.println("An error has occurred");
                    System.out.println("-------------------------------");
                }
            }
            else if (chooseBooksDashboard == 6) {
                break;
            }
        }
    }
    public void displayTwo(AuthorsDashboard authorsDashboard, AuthorDAO authorDao, Scanner scanner){
        while(true){
            byte chooseAuthorsDashboard = authorsDashboard.authorsDashboard();
            if(chooseAuthorsDashboard == 1){
                List<Author> author = authorDao.getAuthors();
                authorsDashboard.displayAuthors(author);
            }
            else if (chooseAuthorsDashboard == 2){
                System.out.print("Author ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                Author author = authorDao.getSpecificAuthor(id);
                authorsDashboard.displaySpecificAuthor(author);
            }
            else if (chooseAuthorsDashboard == 3){
                System.out.print("Book ID: ");
                int id = scanner.nextInt();
                List<Author> author = authorDao.getAuthorBooks(id);
                authorsDashboard.displayAuthorBooks(author);

            }
            else if (chooseAuthorsDashboard == 4) {
                Author author = authorsDashboard.createAuthor(scanner);
                if(authorDao.addAuthor(author)) {
                    System.out.println("Author " + author.getName() + " Added Successfully");
                    System.out.println("-------------------------------");
                }
                else {
                    System.out.println("An error has occurred");
                    System.out.println("-------------------------------");
                }
            }
            else if (chooseAuthorsDashboard == 5) {
                Author author = authorsDashboard.createUpdateAuthor(scanner);
                if (authorDao.updateAuthor(author)) {
                    System.out.println("Author ID " + author.getId() + " Updated Successfully");
                    System.out.println("-------------------------------");
                } else {
                    System.out.println("An error has occurred");
                    System.out.println("-------------------------------");
                }
            }
            else if (chooseAuthorsDashboard == 6){
                System.out.print("Admin ID to be deleted: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                if(authorDao.deleteAuthor(id)) {
                    System.out.println("Author ID " + id + " Successfully deleted");
                    System.out.println("-------------------------------");
                }
                else {
                    System.out.println("An error has occurred");
                    System.out.println("-------------------------------");
                }
            }
            else if (chooseAuthorsDashboard == 7){
                break;
            }

        }
    }

    public void displayThree(PublishersDashboard publishersDashboard, PublisherDAO publisherDAO, Scanner scanner){
        while(true) {
            byte choicePublisherDashboard = publishersDashboard.publishersDashboard();
            if (choicePublisherDashboard == 1) {
                List<Publisher> publisher = publisherDAO.getPublishers();
                publishersDashboard.displayPublishers(publisher);
            }
            else if (choicePublisherDashboard == 2){
                System.out.print("Publisher ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                Publisher publisher = publisherDAO.getSpecificPublisher(id); // should i do this or
                publishersDashboard.displaySpecificPublisher(publisher);
            }
            else if (choicePublisherDashboard == 3){
                System.out.print("Publisher's ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                publishersDashboard.displayPublisherBooks(publisherDAO.getPublisherBooks(id));
            }
            else if (choicePublisherDashboard == 4){
                Publisher publisher = publishersDashboard.createPublisher(scanner); // should i do this?
                if(publisherDAO.addPublisher(publisher)) {
                    System.out.println("Publisher name " + publisher.getName() + " Inserted Successfully");
                    System.out.println("-------------------------------");
                }else {
                    System.out.println("An error has occurred");
                    System.out.println("-------------------------------");
                }
            }
            else if (choicePublisherDashboard == 5){
                Publisher publisher = publishersDashboard.createUpdatePublisher(scanner);
                if(publisherDAO.updatePublisher(publisher)) {
                    System.out.println("Publisher ID " + publisher.getId() + " Updated Successfully");
                    System.out.println("-------------------------------");
                }else{
                    System.out.println("An error has occurred");
                    System.out.println("-------------------------------");
                }
            }
            else if (choicePublisherDashboard == 6){
                System.out.print("Publisher ID to be deleted: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                if(publisherDAO.deletePublisher(id)) {
                    System.out.println("Publisher ID " + id + " Deleted successfully");
                    System.out.println("-------------------------------");
                }
                else{
                    System.out.println("An error has occurred");
                    System.out.println("-------------------------------");
                }
            }
            else if (choicePublisherDashboard == 7) {
                break;
            }
        }
    }
    public void displayFour(BorrowBooksDashboard borrowBooksDashboard, Scanner scanner, BorrowBookDAO borrowBookDAO, Admin admin){
        while(true){
            byte choiceBorrowBookDashboard = borrowBooksDashboard.borrowBooksDashboard(scanner);
            if(choiceBorrowBookDashboard == 1){
                List<BorrowBook> borrowedBooks = borrowBookDAO.borrowedBooks();
                borrowBooksDashboard.displayBorrowedBooks(borrowedBooks);
            }
            else if (choiceBorrowBookDashboard == 2){
                scanner.nextLine();
                System.out.print("Student ID: ");
                String studentId = scanner.nextLine();
                List<BorrowBook> borrowedBook = borrowBookDAO.specificBorrowedBook(studentId);
                borrowBooksDashboard.displayBorrowedBooks(borrowedBook);
            }
            else if (choiceBorrowBookDashboard == 3){
                scanner.nextLine();
                System.out.print("Book ID: ");
                int bookId = scanner.nextInt();
                scanner.nextLine();
                if(borrowBookDAO.checkAvailability(bookId)){
                    BorrowBook borrowBook = borrowBookDAO.createBorrowBook(scanner);
                    borrowBook.setBookId(bookId);
                    borrowBook.setAdminId(admin.getId());
                    if(borrowBookDAO.borrowBook(borrowBook)) {
                        borrowBookDAO.minusStack(bookId);
                        System.out.println("Book ID: " + bookId + " Successfully borrowed By Student ID: " + borrowBook.getStudentId());
                        System.out.println("-------------------------------");
                    }else{
                        System.out.println("An error has occurred");
                        System.out.println("-------------------------------");
                    }
                }
                else {
                    System.out.println("Book ID: " + bookId + " has no stocks");
                    System.out.println("-------------------------------");
                }

            }
            else if (choiceBorrowBookDashboard == 4){
                break;
            }
        }
    }
    public void displayFive(ReturnBookDashboard returnBookDashboard, Scanner scanner, ReturnBookDAO returnBookDAO, ReturnBook returnBook, Admin admin){
        while (true){
            byte returnBookChoice = returnBookDashboard.returnBooksDashboard(scanner);
            if(returnBookChoice == 1){
                returnBookDashboard.displayReturnedBooks(returnBookDAO.getReturnedBooks());
            }
            else if (returnBookChoice == 2) {
                scanner.nextLine();
                System.out.print("Transaction ID: ");
                byte transactionId = scanner.nextByte();
                scanner.nextLine();
                System.out.println("-------------------------------");
                returnBookDashboard.displayReturnedBooks(returnBookDAO.getSpecificReturnedBook(transactionId));
            }
            else if (returnBookChoice == 3){
                returnBook = returnBookDashboard.displayReturnBook(scanner, returnBook);
                if (returnBookDAO.checkTransactionId(returnBook.getTransactionId(), returnBook)) {
                    returnBook = returnBookDAO.returnBook(returnBook, admin.getId());
                    if (returnBook != null) {
                        returnBookDAO.plusStock(returnBook.getBookId()); // this doesn't work, why?
                        System.out.println(returnBook.getBookId());
                        System.out.println("-------------------------------");
                        System.out.println("Transaction ID: " + returnBook.getTransactionId() + " Returned Successfully");
                        System.out.println("-------------------------------");
                    }
                } else {
                    System.out.println("Transaction ID does not exist");
                    System.out.println("-------------------------------");
                }
            }
            else if (returnBookChoice == 4){
                break;
            }
        }
    }

    public boolean displaySix(Scanner scanner, Admin admin){
        while (true) {
            System.out.println("Are you sure you want to LOGOUT?");
            System.out.println("[1] YES");
            System.out.println("[2] NO");
            System.out.print("Enter your Option: ");
            byte choice = scanner.nextByte();
            System.out.println("-------------------------------");
            if(choice ==1){
                System.out.println(admin.getName() + " has been LOGGED OUT. ");
                System.out.println("-------------------------------");
                return true;
            } else if (choice ==2) {
                System.out.println("Back to the Main Menu");
                System.out.println("-------------------------------");
                return false;
            }
        }

    }

    public void superAdminDashboard(Scanner scanner, AdminDAO adminDAO, AdminDashboard adminDashboard){
        while (true) {
            System.out.print("Super Admin Username: ");
            String username = scanner.nextLine();
            System.out.print("Super Admin Password: ");
            String password = scanner.nextLine();
            if (adminDAO.validateSuperAdmin(username, password)) {
                System.out.println("-------------------------------");
                adminDashboard.registerDashboard();
                break;
            } else {
                System.out.println("Wrong Super Admin Credentials");
                System.out.println("-------------------------------");
            }
        }
    }
}
