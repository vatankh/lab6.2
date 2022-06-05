package Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class PathHandler {
    public  static String getPath(String file) throws  IOException {
        String path;
        System.out.println("specify your file path [1]");
        System.out.println("specify directory path, and we make the file [2]");
        Scanner scanner =new Scanner(System.in);
        String val =scanner.nextLine();
        while (val.length() != 1 || (val.charAt(0) != '1' && val.charAt(0) != '2')){
            System.out.println("please choose 1 or two");
            val=scanner.nextLine();
        }
        if (val.equals("1")){
            path =handleFile();
        }
        else {
            path =handleDir() +"/"+file;
            File file1=new File(path);
            if (!file1.exists()){
                file1.createNewFile();
            }
        }
        return path;
    }
    public static String handleFile( ) throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("specify file path (with the filename):");
        String path =scanner.nextLine();
        File file1 = new File(path);
        while (!file1.exists() || !file1.canRead() ||!file1.canWrite()) {
            while (!file1.exists()) {
                System.out.println("file does not exist");
                System.out.println("specify file path (with the filename):");
                path = scanner.nextLine();
                file1 = new File(path);
            }
            while (!file1.canRead()) {
                System.out.println("no premisions for reading file please choose another file");
                System.out.println("specify file path (with the filename):");
                path = scanner.nextLine();
                file1 = new File(path);
            }
            while (!file1.canWrite()) {
                System.out.println("no premisions for writing to file please choose another file");
                System.out.println("specify file path (with the filename):");
                path = scanner.nextLine();
                file1 = new File(path);
            }
        }
        for (String line :
                readFile(file1).split("\n")) {
            if (line.split(",").length != 8) {
                System.out.println("file have data already . choose another file please" );
                path=handleFile();
            }
        }

        return path;
    }
    public static String handleDir(){
        Scanner scanner =new Scanner(System.in);
        System.out.println("specify directory path, and we make the file:");
        String dir=scanner.nextLine();
        File dirFile = new File(dir);
        while (!dirFile.exists() || !dirFile.canExecute()|| !dirFile.canRead()) {
            while (!dirFile.exists()) {
                System.out.println("dirictory does not exist");
                System.out.println("specify directory path:");
                dir = scanner.nextLine();
                dirFile = new File(dir);
            }
            while (!dirFile.canExecute()) {
                System.out.println("no premisions for executing  choose another directory");
                System.out.println("specify directory path");
                dir = scanner.nextLine();
                dirFile = new File(dir);
            }
            while (!dirFile.canWrite()) {
                System.out.println("no premisions for writing to directory please choose another directory");
                System.out.println("specify directory path");
                dir = scanner.nextLine();
                dirFile = new File(dir);
            }
        }
        return dir;
    }
    public static   String  readFile(File file)
            throws IOException {
        InputStream is = new FileInputStream(file);
        StringBuilder sb = new StringBuilder();
        for (int ch; (ch = is.read()) != -1; ) {
            sb.append((char) ch);
        }

        return sb.toString();
    }
}
