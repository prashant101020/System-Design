import java.util.List;

public class Floor {
    int id;
    List<ParkingSpot> parkingSpotList;
    public Floor(int id, List<ParkingSpot> parkingSpotList) {
        this.id = id;
        this.parkingSpotList = parkingSpotList;
    }
    public int getId(){
        return this.id;
    }
    public List<ParkingSpot> getParkingSpotList(){
        return this.parkingSpotList;
    }
}
