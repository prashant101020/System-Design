public class Room {
    private int roomId;
    private RoomType roomType;
    private boolean isAvailable;
    private double pricePerNight;
    public Room(int roomId, RoomType roomType, double pricePerNight) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true; // By default, the room is available
    }
    public int getRoomId() {
        return roomId;
    }
    public RoomType getRoomType() {
        return roomType;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    public double getPricePerNight() {
        return pricePerNight;
    }
}
