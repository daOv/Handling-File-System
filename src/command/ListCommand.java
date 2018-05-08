package command;

import java.io.File;

public class ListCommand {

    public static void list(File file) {
        String[] folderElementsList = file.list();
        System.out.println("File content :");
        System.out.println("--------------------------");
        for (String e : folderElementsList) {
            System.out.println(e);
        }
    }
}
