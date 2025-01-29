package Utility;
import java.util.Scanner;

public class ExitHandler {
    private final Scanner scanner;
    private final InputUtility inputUtility;

    public ExitHandler(Scanner scanner) {
        this.scanner = scanner;
        this.inputUtility = new InputUtility();
    }
    public void confirmExit(){
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
        } else {
            inputUtility.InvalidInputs();
            confirmExit();
        }
    }
}
