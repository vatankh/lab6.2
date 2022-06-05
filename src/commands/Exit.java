package commands;

import collection.Vehicle;
import utils.ConvertorFromVehicleToCvs;

import java.io.*;

public class Exit extends AbsCommand implements Serializable {
    File file;
    public Exit(String filepath){
        file=new File(filepath);
    }
    @Override
    public String work() throws Exception {
        Vehicle[] vehicles = storage.getVehiclesArray();
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if (vehicles.length == 0) {
                bufferedWriter.write("");
            } else {
                for (Vehicle vehicle : vehicles) {
                    bufferedWriter.write(ConvertorFromVehicleToCvs.convert(vehicle));
                }
            }
            bufferedWriter.close();
            return messege;
        }catch (FileNotFoundException fileNotFoundException){
            return "access denied";
        }
    }
}
