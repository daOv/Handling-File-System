package command;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class InfoCommand {

    public static void info(File file){
        System.out.println("Info of file java.txt:");
        System.out.println("----------------------");
        System.out.println("Name of file is: " + file.getName());
        System.out.println("Path of file is: " + file.getPath());
        System.out.println("Length of file is: " + file.length());
        Instant instant = Instant.ofEpochMilli(file.lastModified());
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MMM yyyy : HH mm ss");
        System.out.println("File " + file.getName() + " last modify: " + dateTime.format(format));
    }
}
