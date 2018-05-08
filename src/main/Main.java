package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {


    private static String address;

    private static final String LIST = "list";
    private static final String INFO = "info";
    private static final String CREATE_DIR = "create_dir";
    private static final String RENAME = "renam";
    private static final String COPY = "copy";
    private static final String MOVE = "move";
    private static final String DELETE = "delete";

    public static void main(String[] args) {
        Scanner scanner;
        System.out.println("Enter the address of file/folder which you want to manipulate:");
        scanner = new Scanner(System.in);
        address = scanner.nextLine();
        File file = new File(address);

        if (file.isAbsolute() && file.exists()) {
            System.out.println("Enter command: (list, info, create_dir, rename, copy, move, delete) ");
            scanner = new Scanner(System.in);
            String command = scanner.next();

            if (command.toLowerCase().equals(LIST)) {
                String [] folderElemtsList = file.list();
                System.out.println("File content :");
                System.out.println("--------------------------");
                for (int i = 0; i < folderElemtsList.length; i++) {
                    System.out.println(folderElemtsList[i]);
                }

            } else if (command.toLowerCase().equals(INFO) ) {
                System.out.println("Info of file java.txt:");
                System.out.println("----------------------");
                System.out.println("Name of file is: " + file.getName());
                System.out.println("Path of file is: " + file.getPath());
                System.out.println("Length of file is: " + file.length());
                Instant instant = Instant.ofEpochMilli(file.lastModified());
                LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MMM yyyy : HH mm ss");
                System.out.println("File " + file.getName() + " last modify: " + dateTime.format(format));
                return;

                //CREATE_DIR
            } else if (command.toLowerCase().equals(CREATE_DIR) && file.isDirectory()) {
                System.out.println("Enter the name of file you want to create : ");
                scanner = new Scanner(System.in);
                String imeFajla = scanner.nextLine();
                File file1 = new File(address.concat("\\" + imeFajla));
                try {
                    if (!file1.exists()) {
                        file1.mkdir();
                        System.out.println("File " + file1.getName() + " successfuly created !! ");
                    } else {
                        System.out.println("Folder already exists!!");
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }

                //RENAME
            } else if (command.toLowerCase().equals(RENAME)) {
                System.out.println("Enter file you want to rename:");
                scanner = new Scanner(System.in);
                File file1 = new File(address.concat("\\" + scanner.nextLine()));
                if (file1.exists()) {
                    System.out.println("Ukucajte novi naziv fajla!!");
                    scanner = new Scanner(System.in);
                    String newName = scanner.nextLine();
                    File fileNovoIme = new File(address.concat("\\" + newName));
                    if (fileNovoIme.exists()) {
                        System.out.println("File already exists!!");
                        return;
                    }
                    if (file1.renameTo(fileNovoIme)) {
                        System.out.println("File name changed !!");
                    } else {
                        System.out.println("Changing name fail !!");
                    }
                } else {
                    System.out.println("File doesn't exist !!");
                }
            }

            //COPY
            else if ((command.toLowerCase().equals(COPY))) {
                System.out.println("Enter file name you want to copy: ");
                scanner = new Scanner(System.in);
                String copyFile = scanner.nextLine();
                File file1 = new File(address.concat("\\" + copyFile));

                if (file1.exists()) {
                    System.out.println("Enter new location: ");
                    scanner = new Scanner(System.in);
                    String newAddress = scanner.nextLine();
                    File file2 = new File(newAddress);
                    if (file2.isAbsolute()) {

                        File afile = new File(address.concat("\\" + copyFile));
                        File bfile = new File(newAddress.concat("\\" + copyFile));

                        try (FileInputStream inStream = new FileInputStream(afile);
                             FileOutputStream outStream = new FileOutputStream(bfile);) {
                            byte[] buffer = new byte[1024];
                            int length;
                            while ((length = inStream.read(buffer)) > 0) {
                                outStream.write(buffer, 0, length);
                            }

                            System.out.println("File is copied !!");

                        } catch (IOException exc) {
                            System.out.println(exc);
                        }
                    } else {
                        System.out.println("File doesn't exist !!");
                    }
                } else {
                    System.out.println("File doesn't exist !!");
                }
            }
            //MOVE
            else if ((command.toLowerCase().equals(MOVE) )) {

                String path = "";
                String destination = "";

                try (BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));) {

                    System.out.println("Enter the adress of file: ");
                    path = bufferRead.readLine();

                    System.out.println("Enter new adress: ");
                    destination = bufferRead.readLine();

                } catch (IOException e) {
                    System.out.println(e);
                }

                File firstFile = new File(path);
                File secondFile = new File(destination + "\\" + firstFile.getName());

                try (FileInputStream inStream = new FileInputStream(firstFile);
                     FileOutputStream outStream = new FileOutputStream(secondFile);) {
                    byte[] buffer = new byte[1024];
                    int length;

                    while ((length = inStream.read(buffer)) > 0) {
                        outStream.write(buffer, 0, length);
                    }
                    System.out.println("File successfully moved !!");
                    firstFile.delete();
                } catch (IOException exc) {
                    System.out.println(exc);
                }
            }

            //DELETE
            else if (command.toLowerCase().equals(DELETE)) {
                File file4 = new File(address);
                if (file4.exists()) {
                    file4.delete();
                    System.out.println("File successfuly deleted !!");
                } else {
                    System.out.println("File doesn't exist !!");
                }

            } else {
                System.out.println("Command not defined !!");
            }
        } else {
            System.out.println("File doesn't exist !!");
        }

    }

}
