package command;

import java.io.*;

public class MoveCommand {

    public static void move(String address){
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
    }
}
