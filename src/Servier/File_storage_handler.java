package Servier;

import Control.Storage;
import collection.Vehicle;
import utils.CvsReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

public class File_storage_handler {
    public static Storage getStorage(String filePath) throws IOException {
        File file =new File(filePath);
        return getStorage(file);
    }
    public static Storage getStorage(File file) throws IOException {
        Stack<Vehicle> stack = CvsReader.getInStack(readFile(file));
        return new Storage(stack);
    }
    public   static String  readFile(File file)
            throws IOException {
        InputStream is = new FileInputStream(file);
        StringBuilder sb = new StringBuilder();
        for (int ch; (ch = is.read()) != -1; ) {
            sb.append((char) ch);
        }

        return sb.toString();
    }

}
