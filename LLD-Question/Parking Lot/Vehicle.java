
public abstract class Vehicle{
    private String lisencePlate;
    private VehicleType type;
    public Vehicle(String lisencePlate, VehicleType type){
        this.lisencePlate=lisencePlate;
        this.type=type;
    }
    public String getLisencePlate(){
        return lisencePlate;
    }
    public VehicleType getType(){
        return type;
    }
}

class Car extends Vehicle{

    public Car(String lisencePlate){
        super(lisencePlate,VehicleType.CAR);
    }
}

class Truck extends Vehicle{
    public Truck(String lisencePlate){
        super(lisencePlate,VehicleType.TRUCK);
    }
}

class Bike extends Vehicle{
    public Bike(String lisencePlate){
        super(lisencePlate,VehicleType.BIKE);
    }
}