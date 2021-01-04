package mainScreen;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class LinkedList<S> {

    static Scanner reader;
    static FileWriter writer;

    public static void removeAtEnd() {
        //Chat3 to chat4
        try {
            writer = new FileWriter("C:\\Users\\Momin Khan\\Desktop\\Chat App Beta 3\\src\\file\\chat4.txt");
            reader = new Scanner(new FileReader("C:\\Users\\Momin Khan\\Desktop\\Chat App Beta 3\\src\\file\\chat3.txt"));
            while (reader.hasNext()) {
                String msg = reader.nextLine();

                writer.write(msg + System.lineSeparator());
                writer.flush();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        //Chat2 to Chat3
        try {
            writer = new FileWriter("C:\\Users\\Momin Khan\\Desktop\\Chat App Beta 3\\src\\file\\chat3.txt");
            reader = new Scanner(new FileReader("C:\\Users\\Momin Khan\\Desktop\\Chat App Beta 3\\src\\file\\chat2.txt"));
            while (reader.hasNext()) {
                String msg = reader.nextLine();
                writer.write(msg + System.lineSeparator());
                writer.flush();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        //Chat1 to Chat2
        try {
            writer = new FileWriter("C:\\Users\\Momin Khan\\Desktop\\Chat App Beta 3\\src\\file\\chat2.txt");
            reader = new Scanner(new FileReader("C:\\Users\\Momin Khan\\Desktop\\Chat App Beta 3\\src\\file\\chat1.txt"));
            while (reader.hasNext()) {
                String msg = reader.nextLine();
                writer.write(msg + System.lineSeparator());
                writer.flush();
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        //Recent to Chat1
        try {
            writer = new FileWriter("C:\\Users\\Momin Khan\\Desktop\\Chat App Beta 3\\src\\file\\chat1.txt");
            reader = new Scanner(new FileReader("C:\\Users\\Momin Khan\\Desktop\\Chat App Beta 3\\src\\file\\recent.txt"));
            while (reader.hasNext()) {
                String msg = reader.nextLine();
                writer.write(msg + System.lineSeparator());
                writer.flush();
            }
            FileWriter file = new FileWriter("C:\\Users\\Momin Khan\\Desktop\\Chat App Beta 3\\src\\file\\recent.txt");
            file.write("");
            file.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void add(String[] str, int i) {
    }
}
