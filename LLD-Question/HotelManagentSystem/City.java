import java.util.List;

public class City {
    private int cityId;
    private String cityName;
    private String state;
    private List<Hotel> hotels;

    public City(int cityId, String cityName, String state) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.state = state;
    }
    public City(int cityId, String cityName, String state,List<Hotel> hotels) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.state = state;
        this.hotels=hotels;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getState() {
        return state;
    }
    public List<Hotel> getAllHotels(){
        return this.hotels;
    }
    public void setHotels(List<Hotel> hotels){
        this.hotels=hotels;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public void setState(String state) {
        this.state = state;
    }

}
