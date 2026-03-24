import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

abstract class Room {
    protected String type;
    protected double price;

    public Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public abstract void displayDetails();
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1500.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Type: " + type + " | Price: Rs." + price + " | Ideal for: Solo Travelers");
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2500.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Type: " + type + " | Price: Rs." + price + " | Ideal for: Couples");
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 5000.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Type: " + type + " | Price: Rs." + price + " | Ideal for: Families");
    }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public Map<String, Integer> getAllInventory() {
        return new HashMap<>(inventory);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

class SearchService {
    public void searchAvailableRooms(RoomInventory inventory, List<Room> roomTemplates) {
        System.out.println("--- Search Results: Available Rooms ---");
        boolean found = false;

        for (Room room : roomTemplates) {
            int count = inventory.getAvailability(room.getType());
            if (count > 0) {
                room.displayDetails();
                System.out.println("Status: " + count + " rooms left");
                System.out.println("---------------------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms currently available.");
        }
    }
}

public class UseCase4RoomSearch {
    public static void main(String[] args) {
        System.out.println("******************************************");
        System.out.println("Book My Stay App - Version 4.0");
        System.out.println("******************************************");

        RoomInventory hotelInventory = new RoomInventory();
        hotelInventory.addRoomType("Single Room", 5);
        hotelInventory.addRoomType("Double Room", 0); // Out of stock
        hotelInventory.addRoomType("Suite Room", 2);

        List<Room> roomTemplates = new ArrayList<>();
        roomTemplates.add(new SingleRoom());
        roomTemplates.add(new DoubleRoom());
        roomTemplates.add(new SuiteRoom());

        SearchService searchService = new SearchService();
        searchService.searchAvailableRooms(hotelInventory, roomTemplates);

        System.out.println("******************************************");
    }
}