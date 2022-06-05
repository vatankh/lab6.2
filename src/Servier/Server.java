package Servier;
import Control.Storage;
import commands.AbsCommand;
import commands.Exit;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.channels.DatagramChannel;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {
    private final static Logger lOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    Storage storage;
    ServerNetworkUdp2 networkudp;
    DatagramChannel channel;

    public Server(DatagramChannel datagramChannel) {
        this.channel=datagramChannel;
        this.networkudp = new ServerNetworkUdp2(this.channel);

    }

    public void start() throws Exception {
        String path =null;
        while (path ==null ||path.equals("null")){
            path = String.valueOf(networkudp.receiveObj());

        }
        lOGGER.log(Level.INFO,"received path ="+path);
        storage = File_storage_handler.getStorage(path);
        lOGGER.log(Level.INFO,"server started working");
        lOGGER.log(Level.INFO,"server made a storage with date "+storage.date);

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));



        while (true) {
            if (br.ready() && br.readLine().equals("save")){
                Exit save =new Exit(path);
                save.loadStorage(storage);
                save.work();
            }else {
                Object obj = networkudp.receiveObj();

                if (obj != null && obj.getClass().getName().split("\\.")[0].toLowerCase().equals("commands")) {
                    AbsCommand absCommand = (AbsCommand) obj;
                    lOGGER.log(Level.INFO,"recived command of type ="+absCommand.getClass().getName());
                    absCommand.loadStorage(storage);
                    String message = absCommand.work();
                    lOGGER.log(Level.INFO,"command execution result ="+message);
                    networkudp.sendObj(message);
                }else if (br.ready() && br.readLine().equals("save")) {
                    Exit save = new Exit(path);
                    save.loadStorage(storage);
                    save.work();
                    lOGGER.log(Level.INFO,"server saved file successfully");
                }else if (obj != null && obj.getClass().getName().toLowerCase().split("\\.").length >2 &&obj.getClass().getName().toLowerCase().split("\\.")[2].equals("string")) {
                    path = String.valueOf(obj);
                    lOGGER.log(Level.INFO,"received path ="+path);
                    storage = File_storage_handler.getStorage(path);
                }
            }
            }

        }
}



