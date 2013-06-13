package ua.edu.vntu;


import java.io.*;
import java.nio.*;

public class MakeAdditions {
    public static void main(String[] args) throws Exception{

        String path = "D:\\VNTU\\bachelor thesis\\ChessStyleAnalysisSoftware\\src\\main\\java\\ua\\edu\\vntu\\Main.java";

        String data = readFile(new File(path));
        writeFile(data);

        System.out.println(data);


    }

    private static void writeFile(String data) throws Exception{
        FileWriter writer = new FileWriter("/out.txt");
        PrintWriter out = new PrintWriter(writer);
        out.println(data);
    }

    private static String readFile(File file) throws Exception{
        FileInputStream fis = new FileInputStream(file);
        byte [] bytes = new byte[fis.available()];
        fis.read(bytes);
        return new String(bytes);
    }

}
