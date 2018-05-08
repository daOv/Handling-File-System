package main;

import command.*;

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
                    ListCommand.list(file);
                    break;

                case INFO:
                    InfoCommand.info(file);
                    break;

                case CREATE_DIR:
                    CreateCommand.create(scanner, address, file);
                    break;

                case RENAME:
                    RenameCommand.rename(scanner, address);
                    break;

                case COPY:
                    CopyCommand.copy(scanner, address);
                    break;

                case MOVE:
                    MoveCommand.move(address);
                    break;

                case DELETE:
                    DeleteComannd.delete(scanner, address);
                    break;

                default:
                    System.out.println("Command not defined !!");
            }
        } else {
            System.out.println("File doesn't exist !!");
        }
    }
}

