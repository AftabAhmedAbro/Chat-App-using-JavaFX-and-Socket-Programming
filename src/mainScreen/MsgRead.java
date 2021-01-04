package mainScreen;

import java.io.*;
import java.util.*;

public class MsgRead {
    static Scanner read;
    static {
        try{
//            read = new Scanner(new FileReader("D:\\A A A\\Desktop\\recent.txt\\"));
            read = new Scanner(new FileReader("C:\\Users\\Momin Khan\\Desktop\\Chat App Beta 3\\src\\file\\recent.txt"));
        }
        catch (Exception e) {

        }
    }

    public static String readMsg(){
        while(read.hasNext()){
            System.out.println(read.nextLine());
        }
        return null;
    }

    public static ArrayList<String> readMsg(int i){
        String fileName="";
        ArrayList<String> msgs = new ArrayList<String>();;

        if(i==0){
//            fileName ="C:\\Users\\tjobn\\OneDrive\\Documents\\Chat App Beta 4\\src\\server\\recent.txt";
            fileName ="C:\\Users\\Momin Khan\\Desktop\\Chat App Beta 3\\src\\file\\recent.txt";

            System.out.println(fileName+ " reading");
        }
        else if(i>=1 && i<=4){
            String num = Integer.toString(i);
            fileName ="C:\\Users\\Momin Khan\\Desktop\\Chat App Beta 3\\src\\file\\chat"+num+".txt";
            System.out.println(fileName+ "reading");
        }


        try{
            read = new Scanner(new FileReader(fileName));
            while(read.hasNext()){
                msgs.add(read.nextLine());
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return msgs;
    }

    public static void main(String args[]){
        //System.out.print(readMsg(0));
//        for(String x:readMsg(3)){
//            System.out.println(x);
//        }
    }
}
