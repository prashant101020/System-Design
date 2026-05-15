public class ParkingSpot {
    int id;
    VehicleType vehicleType;
    Vehicle parkedVehicle;

    public ParkingSpot(int id, VehicleType vehicleType) {
        this.id = id;
        this.vehicleType = vehicleType;
    }
    public synchronized boolean isAvailable(){
        return parkedVehicle==null;
    }
    public synchronized void park(Vehicle vehicle){
        this.parkedVehicle=vehicle;
    }
    public synchronized void unPark(){
        this.parkedVehicle=null;
    }
    public VehicleType getSpotType(){
        return vehicleType;
    }

}
