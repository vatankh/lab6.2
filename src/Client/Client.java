package Client;
import commands.*;
import java.net.*;
import java.util.Scanner;

public class Client<T extends AbsCommand> {
    DatagramSocket socket;
    ClientNetworkUdp networkUdp;
    CommandHandler commandHandler;
    Scanner scanner;
    String filepath;


    public Client(DatagramSocket datagramSocket,Scanner scanner,String filepath)  {
        this.socket=datagramSocket;
        this.networkUdp=new ClientNetworkUdp(socket);
        this.scanner=scanner;
        this.filepath=filepath;
        this.commandHandler=new CommandHandler(networkUdp,this.scanner,this.filepath);
    }

    public void start() throws Exception {
        HandelObject work=new HandelObject(true,true);
        boolean executing_script=false;
        networkUdp.sendobj(filepath);
        while (work.work){
            if (scanner.hasNext()){
                String command =scanner.nextLine();
                work=commandHandler.handle(command);
                if (work.Continue){
                    if (work.inputStream != null){
                        executing_script=true;
                        scanner=new Scanner(work.inputStream);
                        commandHandler =new CommandHandler(networkUdp,scanner,filepath);
                    }else{
                        String message = String.valueOf(networkUdp.reciveObj());
                        if (!message.equals("null")){
                            System.out.println(message);
                        }else {
                            System.out.println("exiting");
                            work.work=false;
                        }
                    }
                }
            }
            else if (executing_script) {

                 scanner =new Scanner(System.in);
                 commandHandler=new CommandHandler(networkUdp,scanner,filepath);
                 executing_script=false;
            }
        }
    }
}
