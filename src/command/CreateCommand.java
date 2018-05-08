package command;

import com.sun.istack.internal.NotNull;
import com.sun.jndi.cosnaming.IiopUrl;

import java.io.File;
import java.util.Scanner;

public class CreateCommand {

    public static void create(Scanner scanner, String address,File file) {
        if (file.isDirectory()) {
            System.out.println("Enter the name of folder you want to create: ");
            scanner = new Scanner(System.in);
            String fileName = scanner.nextLine();
            File fileToCreate = new File(address.concat("\\" + fileName));
            try {
                if (!fileToCreate.exists()) {
                    fileToCreate.mkdir();
                    System.out.println("File " + fileToCreate.getName() + " successfully created!! ");
                } else {
                    System.out.println("Folder already exists!!");
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
