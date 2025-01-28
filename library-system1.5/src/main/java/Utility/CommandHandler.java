package Utility;

import Dashboard.AdminDashboard;

public class CommandHandler {

    private final AdminDashboard adminDashboard;

    public CommandHandler(AdminDashboard adminDashboard) {
        this.adminDashboard = adminDashboard;
    }

    public boolean handleCommand(String input) {
        if(input == null || input.trim().isEmpty()){
            return false;
        }
        switch (input.toLowerCase().trim()) {
            case "/q" -> {
                System.out.println("-------------------------------");
                System.out.println("Going back to the previous menu...");
                return true; // Command handled
            }
            case "/x", "/exit" -> {
                System.out.println("Exiting the system. Goodbye!");
                System.exit(0);
            }
            case "/h", "/help" -> {
                adminDashboard.helpDashboard();
                return true; // Command handled
            }
            default -> {
                return false; // Command not handled
            }
        }
        return false;
    }
}
