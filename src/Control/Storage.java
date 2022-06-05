package Control;

import collection.Vehicle;

import java.util.Comparator;
import java.util.Date;
import java.util.Stack;

public class Storage {
    private Stack<Vehicle> vehicles = new Stack<>();
    public Date date;
    public static long id=1;

    public Storage(Stack<Vehicle> vehicles){
        this.vehicles=vehicles;
        this.date=new Date(System.currentTimeMillis());

        if (vehicles.size() >1) {
            long max=1;
             max = vehicles.stream().max((a, b) -> {
                return Long.compare(a.getId(), b.getId());
            }).get().getId();
             id =max;
        }

    }

    public Stack<Vehicle> getVehicles() {
        return vehicles;
    }
    public void add(Vehicle vehicle){
        id++;
        vehicle.setId(id);
        vehicles.push(vehicle);
    }
    public void updateId(Vehicle vehicle, int index){
        vehicles.setElementAt(vehicle,index);

    }
    public void removeAtIndex(int index){
        vehicles.removeElementAt(index);
    }
    public Vehicle[] getVehiclesArray(){
        return vehicles.stream().sorted(Comparator.comparingLong(value -> value.getId())).toArray(Vehicle[]::new);
    }
    public void insert_at_index(int index,Vehicle vehicle){
        id++;
        vehicle.setId(id);
        vehicles.insertElementAt(vehicle,index);
    }
    public  int count_greater_than_engine_power(Double power){
        return (int) vehicles.stream().filter(vehicle -> vehicle.getEnginePower() > power).count();
    }

    public String filter_by_engine_power(Double power){
        StringBuilder stringBuilder=new StringBuilder();
        vehicles.stream().filter((vehicle -> vehicle.getEnginePower().equals(power))).sorted(Comparator.comparingLong(value -> value.getId())).forEach((vehicle -> stringBuilder.append(vehicle.toString()).append("\n")));
        return stringBuilder.toString();
    }
    public String filter_greater_than_engine_power(Double power){
        StringBuilder stringBuilder=new StringBuilder();
        vehicles.stream().filter(vehicle -> vehicle.getEnginePower() > power).sorted(Comparator.comparingLong(value -> value.getId())).forEach(vehicle -> stringBuilder.append(vehicle.toString()).append("\n"));
        return stringBuilder.toString();
    }
    public void setVehicles(Stack<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
