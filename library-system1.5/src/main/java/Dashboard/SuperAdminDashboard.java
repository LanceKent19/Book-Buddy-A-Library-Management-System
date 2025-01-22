package Dashboard;

import Dao.AdminDAO;

import java.util.Scanner;

public class SuperAdminDashboard {
    private final Scanner scanner;
    private final AdminDAO adminDAO;
    private final AdminDashboard adminDashboard;

    public SuperAdminDashboard(Scanner scanner, AdminDAO adminDAO, AdminDashboard adminDashboard) {
        this.scanner = scanner;
        this.adminDAO = adminDAO;
        this.adminDashboard = adminDashboard;
    }

    public void superAdmin() {
        System.out.print("Super Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("Super Admin Password: ");
        String password = scanner.nextLine();
        if (adminDAO.validateSuperAdmin(username, password)) {
            System.out.println("-------------------------------");
            adminDashboard.registerDashboard();

        } else {
            System.out.println("Wrong Super Admin Credentials");
            System.out.println("-------------------------------");
        }
    }
}
