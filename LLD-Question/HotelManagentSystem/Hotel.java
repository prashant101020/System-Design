import java.util.List;

class Hotel{
    private int hotelId;
    private String hotelName;
    private String hotelAddress;
    private int hotelStar;
    private List<Room> hotelRooms;

    public Hotel(int hotelId, String hotelName, String hotelAddress, int hoteStar){
        this.hotelId=hotelId;
        this.hotelName=hotelName;
        this.hotelAddress=hotelAddress;
        this.hotelStar=hoteStar;
    }
    public Hotel(int hotelId, String hotelName, String hotelAddress, int hoteStar,List<Room> rooms){
        this.hotelId=hotelId;
        this.hotelName=hotelName;
        this.hotelAddress=hotelAddress;
        this.hotelStar=hoteStar;
        this.hotelRooms=rooms;
    }
    void setHotelRooms(List<Room> rooms){
        this.hotelRooms=rooms;
    }
    public int getHotelId() {
        return hotelId;
    }
    public String getHotelName() {
        return hotelName;
    }
    public String getHotelAddress() {
        return hotelAddress;
    }
    public int getHotelStar() {
        return hotelStar;
    }
    public List<Room> getHotelRooms() {
        return hotelRooms;
    }
    public void setHotelStart(int stars){
        this.hotelStar=stars;
    }
    public void setHotelAddress(String address){
        this.hotelAddress=address;
    }
}