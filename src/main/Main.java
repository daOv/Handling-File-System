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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {


    private static String address;

    private static final String LIST = "list";
    private static final String INFO = "info";
    private static final String CREATE_DIR = "create_dir";
    private static final String RENAME = "rename";
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
            String command = scanner.next().toLowerCase();

            switch (command) {

                case LIST:
                    String[] folderElementsList = file.list();
                    System.out.println("File content :");
                    System.out.println("--------------------------");
                    for (String e : folderElementsList) {
                        System.out.println(e);
                    }
                    break;

                case INFO:
                    System.out.println("Info of file java.txt:");
                    System.out.println("----------------------");
                    System.out.println("Name of file is: " + file.getName());
                    System.out.println("Path of file is: " + file.getPath());
                    System.out.println("Length of file is: " + file.length());
                    Instant instant = Instant.ofEpochMilli(file.lastModified());
                    LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MMM yyyy : HH mm ss");
                    System.out.println("File " + file.getName() + " last modify: " + dateTime.format(format));
                    break;

                case CREATE_DIR:
                    if (file.isDirectory()) {
                        System.out.println("Enter the name of file you want to create: ");
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
                    break;

                case RENAME:
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
                    break;

                case COPY:
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
                    break;

                case MOVE:
                    String path = "";
                    String destination = "";

                    try (BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in))) {
                        System.out.println("Enter the name of file:");
                        path = address + "\\" +bufferRead.readLine();
                        System.out.println("Enter new address: ");
                        destination = bufferRead.readLine();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                    File firstFile = new File(path);
                    File secondFile = new File(destination + "\\" + firstFile.getName());
                    try (FileInputStream inStream = new FileInputStream(firstFile);
                         FileOutputStream outStream = new FileOutputStream(secondFile)) {
                        byte[] buffer = new byte[1024];
                        int length;

                        while ((length = inStream.read(buffer)) > 0) {
                            outStream.write(buffer, 0, length);
                        }
                        System.out.println("File successfully moved !!");
                        firstFile.deleteOnExit();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                    break;

                case DELETE:
                    File fileToDelete = new File(address);
                    if (fileToDelete.exists()) {
                        fileToDelete.delete();
                        System.out.println("File successfuly deleted !!");
                    } else {
                        System.out.println("File doesn't exist !!");
                    }
                    break;
                default:
                    System.out.println("Command not defined !!");
            }
        } else {
            System.out.println("File doesn't exist !!");
        }
    }
}

