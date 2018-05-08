package command;

import java.io.File;
import java.util.Scanner;

public class DeleteComannd {

    public static void delete(Scanner scanner, String address) {
        System.out.println("Enter the name of file:");
        scanner = new Scanner(System.in);
        File fileToDelete = new File(address + "\\" +scanner.nextLine() );
        if (fileToDelete.exists()) {
            fileToDelete.deleteOnExit();
            System.out.println("File successfuly deleted !!");
        } else {
            System.out.println("File doesn't exist !!");
        }
    }
}
