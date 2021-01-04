package mainScreen;

import java.io.*;

public class MsgWrite {
    private static int i=0;
    static String fileName = "C:\\Users\\Momin Khan\\Desktop\\Chat App Beta 3\\src\\file\\recent.txt";
    static FileWriter writer;

    public static void write(String msg){
        try{
            writer = new FileWriter(fileName,true);
            writer.append(msg+System.lineSeparator());
            System.out.println(msg);
            writer.flush();
        }
        catch(Exception e){
            System.out.println(e);
        }


    }
    public static void main(String args[]){

    }
}
