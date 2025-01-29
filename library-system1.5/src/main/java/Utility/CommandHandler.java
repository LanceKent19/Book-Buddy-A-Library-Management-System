package Utility;

import Dashboard.AdminDashboard;

import java.util.Scanner;

public class CommandHandler {

    private final AdminDashboard adminDashboard;
    private final ExitHandler exitHandler;

    public CommandHandler(AdminDashboard adminDashboard, Scanner scanner) {
        this.adminDashboard = adminDashboard;
        this.exitHandler = new ExitHandler(scanner);
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
                exitHandler.confirmExit();
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
