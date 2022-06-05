import Client.Client;
import Client.PathHandler;

import java.net.DatagramSocket;
import java.util.Scanner;

public class Mainclient {
    public static void main(String[] args) throws Exception {
        String fileName;
        if (args.length >0){
            fileName=args[0];
            System.out.println(fileName);
        }else {
            fileName="mydata.csv";
        }
        Scanner scanner = new Scanner(System.in);
        DatagramSocket datagramSocket =new DatagramSocket();
        datagramSocket.setSoTimeout(5000);
        String path = PathHandler.getPath(fileName);
        Client client =new Client(datagramSocket,scanner,path);
        client.start();

    }







}
