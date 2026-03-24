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

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();

    public void addRoomType(String type, int count) {
        inventory.put(type, count);
        allocatedRooms.put(type, new HashSet<>());
    }

    public boolean isAvailable(String type) {
        return inventory.getOrDefault(type, 0) > 0;
    }

    public String allocateRoom(String type) {
        int currentCount = inventory.get(type);
        inventory.put(type, currentCount - 1);

        String roomId = type.substring(0, 1).toUpperCase() + "-" + (100 + allocatedRooms.get(type).size() + 1);
        allocatedRooms.get(type).add(roomId);
        return roomId;
    }

    public void displayStatus() {
        System.out.println("\n--- Current Hotel Status ---");
        for (String type : inventory.keySet()) {
            System.out.println(type + " | Available: " + inventory.get(type) + " | Booked IDs: " + allocatedRooms.get(type));
        }
    }
}

public class UseCase6RoomAllocationService {
    public static void main(String[] args) {
        System.out.println("******************************************");
        System.out.println("Book My Stay App - Version 6.0");
        System.out.println("******************************************");

        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 2);
        inventory.addRoomType("Suite Room", 1);

        Queue<Reservation> queue = new LinkedList<>();
        queue.add(new Reservation("Alice", "Single Room"));
        queue.add(new Reservation("Bob", "Single Room"));
        queue.add(new Reservation("Charlie", "Single Room")); // Should fail (No Single Rooms left)
        queue.add(new Reservation("Diana", "Suite Room"));

        while (!queue.isEmpty()) {
            Reservation request = queue.poll();
            System.out.print("Processing " + request.getGuestName() + "... ");

            if (inventory.isAvailable(request.getRoomType())) {
                String id = inventory.allocateRoom(request.getRoomType());
                System.out.println("SUCCESS! Room Assigned: " + id);
            } else {
                System.out.println("FAILED. No " + request.getGuestName() + " rooms available.");
            }
        }

        inventory.displayStatus();
        System.out.println("******************************************");
    }
}