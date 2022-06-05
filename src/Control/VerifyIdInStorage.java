package Control;

import collection.Vehicle;
import utils.Parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class VerifyIdInStorage {
    public static long[] verify(Storage storage, String id){
        long longId;
        if (!Parser.parsLong(id).equals("")){
            return new long[]{-1,0};
        }else {
            longId=Long.parseLong(id);
        }
        List<Vehicle> vehicles = Arrays.asList(storage.getVehiclesArray());
        int index=-1;
        for (int i=0;i< vehicles.size();i++){
            if (vehicles.get(i).getId() ==longId){
                index=i;
                break;
            }
        }
        System.out.println(Arrays.toString(new long[]{index, longId}));
        return new long[]{index, longId};
    }
}
