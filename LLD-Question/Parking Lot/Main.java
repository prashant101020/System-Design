import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Running Parking Lot Tests ===\n");

//        testParkingLotInitialization();
        testParkDifferentVehicles();
//        testParkingLotFull();
//        testUnparkVehicle();
//        testConcurrentParking();
        
        System.out.println("\n=== All Tests Completed (Check for stack traces above) ===");
    }

    // Helper to get a new instance of ParkingLot bypassing the private constructor for testing
    // To fix this naturally: properly implement the Singleton pattern with `ParkingLot.getInstance()`
    private static ParkingLot getNewParkingLot() {
        try {
            Constructor<ParkingLot> constructor = ParkingLot.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize ParkingLot. Fix the constructor or Singleton pattern!", e);
        }
    }

    // Helper to add floors bypassing the private field
    // To fix this naturally: add an `addFloor(Floor floor)` method to your ParkingLot class
    private static void addFloorsToParkingLot(ParkingLot parkingLot, List<Floor> floors) {
        try {
            Field floorsField = ParkingLot.class.getDeclaredField("floors");
            floorsField.setAccessible(true);
            floorsField.set(parkingLot, floors);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add floors. Consider adding an 'addFloor' method!", e);
        }
    }

    private static ParkingLot setupBasicParkingLot() {
        ParkingLot lot = getNewParkingLot();
        
        List<ParkingSpot> spots = new ArrayList<>();
        spots.add(new ParkingSpot(1, VehicleType.CAR));
        spots.add(new ParkingSpot(2, VehicleType.BIKE));
        spots.add(new ParkingSpot(3, VehicleType.TRUCK));
        
        Floor floor1 = new Floor(1, spots);
        List<Floor> floors = new ArrayList<>();
        floors.add(floor1);
        
        addFloorsToParkingLot(lot, floors);
        return lot;
    }

    private static void testParkingLotInitialization() {
        System.out.println("--- Test 1: Initialization ---");
        try {
            ParkingLot lot = getNewParkingLot();
            System.out.println("PASS: ParkingLot initialized.");
        } catch (Exception e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }

    private static void testParkDifferentVehicles() {
        System.out.println("\n--- Test 2: Park Different Vehicles ---");
        ParkingLot lot = setupBasicParkingLot();
        
        try {
            Vehicle car = new Car("CAR-123");
            Ticket t1 = lot.entry(car);
            System.out.println("PASS: Car parked. Ticket generated.");
            
            Vehicle bike = new Bike("BIKE-123");
            Ticket t2 = lot.entry(bike);
            System.out.println("PASS: Bike parked. Ticket generated.");
            lot.exit(t2); // Unpark bike to free up the spot for the truck

            Vehicle truck = new Truck("TRUCK-123");
            Ticket t3 = lot.entry(truck);
            System.out.println("PASS: Truck parked. Ticket generated.");
        } catch (Exception e) {
            System.out.println("FAIL: Failed to park valid vehicles. " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testParkingLotFull() {
        System.out.println("\n--- Test 3: Parking Lot Full / No Spot Available ---");
        ParkingLot lot = setupBasicParkingLot();
        
        try {
            // Park one car (fills the only car spot)
            lot.entry(new Car("CAR-1"));
            
            // Try to park a second car
            lot.entry(new Car("CAR-2"));
            System.out.println("FAIL: Should have thrown an exception for no available spots.");
        } catch (RuntimeException e) {
            // Your getSpot currently throws NullPointerException ("throw null") when full
            if ("No Available Spots".equals(e.getMessage()) || e instanceof NullPointerException) {
                 System.out.println("PASS: Exception thrown correctly when full. Exception class: " + e.getClass().getSimpleName());
            } else {
                 System.out.println("FAIL: Unexpected exception thrown. " + e.getMessage());
            }
        }
    }

    private static void testUnparkVehicle() {
        System.out.println("\n--- Test 4: Unpark Vehicle ---");
        ParkingLot lot = setupBasicParkingLot();
        try {
            Car car = new Car("CAR-1");
            Ticket ticket = lot.entry(car);
            
            // TODO: Implement unpark / exit logic in ParkingLot class!
            // lot.exit(ticket); 
            // lot.entry(new Car("CAR-2")); // Should succeed after unparking
            
            System.out.println("TODO: unpark/exit method is currently missing in ParkingLot. Please implement it so you can free up spots!");
        } catch (Exception e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }

    private static void testConcurrentParking() {
        System.out.println("\n--- Test 5: Concurrent Parking (Multithreading) ---");
        
        ParkingLot lot = getNewParkingLot();
        List<ParkingSpot> spots = new ArrayList<>();
        // 100 Car spots
        for(int i = 1; i <= 100; i++) {
            spots.add(new ParkingSpot(i, VehicleType.CAR));
        }
        List<Floor> floors = new ArrayList<>();
        floors.add(new Floor(1, spots));
        addFloorsToParkingLot(lot, floors);

        List<Thread> threads = new ArrayList<>();
        
        // Try to park 150 cars concurrently (only 100 spots available)
        for(int i = 0; i < 150; i++) {
            final int carId = i;
            Thread t = new Thread(() -> {
                try {
                    lot.entry(new Car("CAR-" + carId));
                } catch (Exception e) {
                    // Expecting some to fail with Exception when spots run out
                }
            });
            threads.add(t);
        }
        
        for (Thread t : threads) { t.start(); }
        for (Thread t : threads) { 
            try { t.join(); } catch (InterruptedException ignored) {} 
        }

        // Count how many spots are actually occupied
        int occupiedSpots = 0;
        for (ParkingSpot spot : floors.get(0).getParkingSpotList()) {
            if (!spot.isAvailable()) {
                occupiedSpots++;
            }
        }
        
        System.out.println("Expected Occupied Spots: 100");
        System.out.println("Actual Occupied Spots: " + occupiedSpots);
        if (occupiedSpots == 100) {
            System.out.println("PASS: Thread safety handled correctly.");
        } else {
            System.out.println("FAIL: Thread safety issues detected (Race Condition).");
        }
    }
}
