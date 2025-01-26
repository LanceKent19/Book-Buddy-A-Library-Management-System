package Dashboard;
import Dao.AdminDAO;
import Implementation.AdminDAOImplementation;
import Model.Admin;
import java.util.Scanner;

public class AdminDashboard {
    private final Scanner scanner;
    private final AdminDAO adminDAO;

    public AdminDashboard(AdminDAO adminDAO, Scanner scanner) {
        this.adminDAO = adminDAO;
        this.scanner = scanner;
    }

    public byte frontDashboard() {
        System.out.println("[1] Login");
        System.out.println("[2] Register");
        System.out.println("[3] Close the Program");
        System.out.println("[4] Help");
        System.out.print("Enter your Option: ");
        byte choice = scanner.nextByte();
        scanner.nextLine();
        System.out.println("-------------------------------");
        return choice;
    }

    public Admin loginDashboard() {
        System.out.println("Users Login Dashboard");
        while (true) {
            System.out.print("Username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Password: ");
            String password = scanner.nextLine().trim();
            System.out.println("-------------------------------");
            Admin admin = adminDAO.logIn(username, password);
            if (admin != null) {
                boolean isActive = adminDAO.isAccountActive(admin.getUsername());
                if (!isActive) {
                    System.out.println("Your Account is Deactivated. Please Try Again or Contact the Admin.");
                    System.out.println("-------------------------------");
                    return null;  // Return null to go back to front dashboard
                }
                System.out.println("Login Successful");
                System.out.println("-------------------------------");
                System.out.println("Welcome to Book Buddy, A Library Management System! ");
                System.out.println("Admin Name: " + admin.getName());
                System.out.println("Admin ID: " + admin.getId());
                System.out.println("System Version: [1.0.0.0]\n");
                return admin;
            }
        }

    }

    public void helpDashboard() {
        System.out.println("General Information can be found at ");
        System.out.println("http://localhost/Portfolio-Magollado/lance/index.php\n");

        System.out.println("List of all client commands: ");
        System.out.println("/h or help    - Displaying the Help Command");
        System.out.println("/x or exit    - Exiting the Program");
        System.out.println("/q or back    - Back Command");
        System.out.println("-------------------------------");
    }

    private String handleCommands(String input) {
        input = input.trim().toLowerCase();
        switch (input) {
            case "/q":
                System.out.println("Going back to the previous menu...");
                return null;
            case "/exit":
            case "/x":
                System.out.println("Are you sure you want to exit? [y/n]");
                String confirmExit = scanner.nextLine().trim().toLowerCase();
                if (confirmExit.equals("y")) {
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                }
                return null;
            case "/h":
            case "help":
                helpDashboard();
                return null;
            default:
                return input; // Return valid user input for further processing
        }
}
    public void registerDashboard(){
        System.out.println("Create Account Dashboard");
        while (true) {
            System.out.print("Full Name:");
            String name = scanner.nextLine().toLowerCase();
            System.out.print("Username: ");
            String username = scanner.nextLine().toLowerCase().trim();
            System.out.print("Password: ");
            String password = scanner.nextLine().toLowerCase().trim();
            System.out.print("Re-Password: ");
            String rePassword = scanner.nextLine().toLowerCase().trim();
            System.out.println("-------------------------------");
            if (!name.matches("^[a-zA-Z\\s]+$")) {
                System.out.println("Your Full Name has special characters. Please Try Again!");
                System.out.println("-------------------------------");
                continue;
            }
            if (password.length() < 12) {
                System.out.println("Your Password is weak. Please Try Again. ");
                System.out.println("-------------------------------");
                continue;
            }
            if (!password.equals(rePassword)) {
                System.out.println("Password doesn't matched. Please Try Again.");
                System.out.println("-------------------------------");
                continue;
            }

            Admin admin = new Admin(name, username, password);
           if(AdminDAOImplementation.isUsernameExists(admin.getUsername())){
                System.out.println("Username Already Exists. Please Try Again.");
               System.out.println("-------------------------------");
                continue;
            }
            adminDAO.register(admin);
            break;
        }
    }
    public byte adminDashboard(boolean isSuperAdmin){
        System.out.println("++Main Menu++");
        System.out.println("[1] Books");
        System.out.println("[2] Authors");
        System.out.println("[3] Publishers");
        System.out.println("[4] Borrow Books");
        System.out.println("[5] Return Books");
        System.out.println("[6] Logout");
        if(isSuperAdmin)
            System.out.println("[7] Account Settings");
        System.out.print("Enter your Option: ");
        byte choice = scanner.nextByte();
        scanner.nextLine();
        System.out.println("-------------------------------");
        return choice;

    }

}
