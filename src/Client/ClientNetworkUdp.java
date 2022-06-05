package Client;

import Control.SerializerDeserializer;

import java.io.IOException;
import java.net.*;

public class ClientNetworkUdp {
    DatagramSocket socket;
    public ClientNetworkUdp(DatagramSocket datagramSocket){
        this.socket=datagramSocket;
    }
    public void sendobj(Object object) throws IOException {
        byte[] serilzedObj= SerializerDeserializer.serilizeobj(object);
//        InetAddress client0 = InetAddress.getByName("localhost");
        InetAddress client = InetAddress.getLocalHost();

        DatagramPacket packet = new DatagramPacket(serilzedObj, serilzedObj.length, client, 80);
        socket.send(packet);
    }
    public  Object reciveObj() throws IOException {
        try {
            byte[] data = new byte[65535];
            DatagramPacket packet = new DatagramPacket(data, data.length );
            socket.receive(packet);
            return SerializerDeserializer.deserilizObj(data);
        } catch(SocketTimeoutException e) {
            System.out.println("the server is unavilable right now try again later");
        }catch (ClassNotFoundException e){
            System.out.println("class not found");
        }
        return null;
    }

}
