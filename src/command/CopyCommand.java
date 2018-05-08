package command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class CopyCommand {

    public static void copy(Scanner scanner, String address ){
        System.out.println("Enter file name you want to copy: ");
        scanner = new Scanner(System.in);
        String fileAddress = scanner.nextLine();
        File fileForCopy = new File(address.concat("\\" + fileAddress));
        if (fileForCopy.exists()) {
            System.out.println("Enter new location: ");
            scanner = new Scanner(System.in);
            String newAddress = scanner.nextLine();
            File newFile = new File(newAddress);
            if (newFile.isAbsolute()) {

                File firstFile = new File(address.concat("\\" + fileAddress));
                File secondFile = new File(newAddress.concat("\\" + fileAddress));

                try (FileInputStream inStream = new FileInputStream(firstFile);
                     FileOutputStream outStream = new FileOutputStream(secondFile);) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inStream.read(buffer)) > 0) {
                        outStream.write(buffer, 0, length);
                    }

                    System.out.println("File is copied !!");

                } catch (IOException e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("File doesn't exist !!");
            }
        } else {
            System.out.println("File doesn't exist !!");
        }
    }

}
