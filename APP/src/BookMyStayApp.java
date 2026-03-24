import java.util.*;

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

class BookingSystem {
    private Map<String, Integer> inventory = new HashMap<>();
    private List<String> confirmedBookings = new ArrayList<>();

    public void addRoomType(String type, int count) {
        inventory.put(type, count);
    }

    public synchronized boolean processBooking(Reservation res) {
        String type = res.getRoomType();
        int available = inventory.getOrDefault(type, 0);

        if (available > 0) {
            // Simulate processing delay to increase chance of race conditions if not synchronized
            try { Thread.sleep(10); } catch (InterruptedException e) {}

            inventory.put(type, available - 1);
            confirmedBookings.add(res.getGuestName() + " (" + type + ")");
            return true;
        }
        return false;
    }

    public void displayFinalReport() {
        System.out.println("\n--- Final Booking Report ---");
        System.out.println("Inventory Remaining: " + inventory);
        System.out.println("Total Confirmed: " + confirmedBookings.size());
        System.out.println("Guests: " + confirmedBookings);
    }
}

class GuestThread extends Thread {
    private BookingSystem system;
    private Reservation reservation;

    public GuestThread(BookingSystem system, String name, String type) {
        this.system = system;
        this.reservation = new Reservation(name, type);
    }

    @Override
    public void run() {
        boolean success = system.processBooking(reservation);
        if (success) {
            System.out.println("[SUCCESS] " + reservation.getGuestName() + " secured a room.");
        } else {
            System.out.println("[FAILED] " + reservation.getGuestName() + " - No rooms available.");
        }
    }
}

public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("******************************************");
        System.out.println("Book My Stay App - Version 11.0 (Concurrent)");
        System.out.println("******************************************");

        BookingSystem hotel = new BookingSystem();
        // Only 2 rooms available for 5 eager guests
        hotel.addRoomType("Suite", 2);

        Thread[] guests = {
                new GuestThread(hotel, "Alice", "Suite"),
                new GuestThread(hotel, "Bob", "Suite"),
                new GuestThread(hotel, "Charlie", "Suite"),
                new GuestThread(hotel, "Diana", "Suite"),
                new GuestThread(hotel, "Edward", "Suite")
        };

        // Start all threads "simultaneously"
        for (Thread t : guests) t.start();

        // Wait for all threads to finish
        for (Thread t : guests) t.join();

        hotel.displayFinalReport();
        System.out.println("******************************************");
    }
}