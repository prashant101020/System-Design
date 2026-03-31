package StructuralDesignPattern;

import java.util.HashMap;
import java.util.Map;

interface DatabaseService{
    String fetchData(String query);
}

class RealDatabseService implements DatabaseService{
    @Override
    public String fetchData(String query) {
       try{
           Thread.sleep(2000); // Simulate time-consuming operation
         }catch (InterruptedException e){
             Thread.currentThread().interrupt();
       }
        // Simulate fetching data from a database
        return "Data for query: " + query;
    }
}

class CachingDatabaseProxy implements DatabaseService{
    RealDatabseService realService;
    private Map<String,String> cache = new HashMap<>();
     public CachingDatabaseProxy(RealDatabseService realService) {
         this.realService = realService;
     }

   public  String fetchData(String query){
         if(cache.containsKey(query)){
             System.out.println("Returning cached data for query: " + query);
             return cache.get(query);
         }
         String result = realService.fetchData(query);
         cache.put(query,result);
         return result;
     }

}
public class Proxy {
    public static void main(String[] args) {
        RealDatabseService realService = new RealDatabseService();
        CachingDatabaseProxy proxy = new CachingDatabaseProxy(realService);

        System.out.println(proxy.fetchData("SELECT * FROM users"));
        System.out.println(proxy.fetchData("SELECT * FROM users")); // This will return cached data
    }
}
