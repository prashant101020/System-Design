public class RestaurantService {
Display display=new Display();

    void addRestaurant(Restaurant restaurant) {
    display.restaurantList.add(restaurant);
    }
        void removeRestaurant(Restaurant restaurant) {
        display.restaurantList.remove(restaurant);
        }
        void updateRestaurant() {

        }
    Restaurant searchRestaurant(String name) {
            Restaurant s=null;
            for(Restaurant r: display.restaurantList){
                if(r.restaurantName.contains(name)){
                    s=r;
                    break;
                }

            }
        return s;

    }
    void listAll(){
        for(Restaurant r: display.restaurantList){
            System.out.println(r.restaurantName);
        }
    }
}
