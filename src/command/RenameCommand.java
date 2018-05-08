package command;

import java.io.File;
import java.util.Scanner;

public class RenameCommand {

    public static void rename(Scanner scanner, String address) {
        System.out.println("Enter file you want to rename:");
        scanner = new Scanner(System.in);
        File fileToRename = new File(address.concat("\\" + scanner.nextLine()));
        if (fileToRename.exists()) {
            System.out.println("Enter new name for the file!");
            scanner = new Scanner(System.in);
            String newName = scanner.nextLine();
            File fileWithNewName = new File(address.concat("\\" + newName));
            if (fileWithNewName.exists()) {
                System.out.println("File already exists!!");
                return;
            }
            if (fileToRename.renameTo(fileWithNewName)) {
                System.out.println("File name changed !!");
            } else {
                System.out.println("Changing name fail !!");
            }
        } else {
            System.out.println("File doesn't exist !!");
        }
    }

}
