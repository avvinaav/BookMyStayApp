import java.util.*;

class Reservation {
    private String guestName;
    private String roomType;
    private String roomId;

    public Reservation(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
    public String getRoomId() { return roomId; }
}

class BookingSystem {
    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Reservation> activeBookings = new HashMap<>();
    private Stack<String> cancelledRoomIds = new Stack<>();

    public void addRoomType(String type, int count) {
        inventory.put(type, count);
    }

    public void confirmBooking(String guestName, String roomType, String resId) {
        int count = inventory.get(roomType);
        inventory.put(roomType, count - 1);

        String roomId = roomType.substring(0, 1) + "-101";
        Reservation res = new Reservation(guestName, roomType, roomId);
        activeBookings.put(resId, res);
        System.out.println("Confirmed: " + guestName + " in " + roomId + " (Booking ID: " + resId + ")");
    }

    public void cancelBooking(String resId) {
        if (!activeBookings.containsKey(resId)) {
            System.out.println("Error: Booking ID " + resId + " not found. Cancellation failed.");
            return;
        }

        Reservation res = activeBookings.remove(resId);
        String roomType = res.getRoomType();

        inventory.put(roomType, inventory.get(roomType) + 1);
        cancelledRoomIds.push(res.getRoomId());

        System.out.println("Cancelled: " + res.getGuestName() + "'s stay. Room " + res.getRoomId() + " returned to inventory.");
    }

    public void displayStatus() {
        System.out.println("\n--- Final System State ---");
        System.out.println("Inventory: " + inventory);
        System.out.println("Active Bookings: " + activeBookings.size());
        System.out.println("Recently Vacated Rooms (Stack): " + cancelledRoomIds);
    }
}

public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        System.out.println("******************************************");
        System.out.println("Book My Stay App - Version 10.0");
        System.out.println("******************************************");

        BookingSystem hotel = new BookingSystem();
        hotel.addRoomType("Single Room", 10);
        hotel.addRoomType("Suite Room", 2);

        hotel.confirmBooking("Alice", "Suite Room", "BK-001");
        hotel.confirmBooking("Bob", "Single Room", "BK-002");

        System.out.println("------------------------------------------");
        hotel.cancelBooking("BK-001");
        hotel.cancelBooking("BK-999"); // Test invalid ID

        hotel.displayStatus();
        System.out.println("******************************************");
    }
}