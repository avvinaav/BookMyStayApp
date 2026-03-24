import java.util.HashMap;
import java.util.Map;

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

    public abstract void displayDetails();
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1500.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Type: " + type + " | Price: Rs." + price);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2500.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Type: " + type + " | Price: Rs." + price);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 5000.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Type: " + type + " | Price: Rs." + price);
    }
}

class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " rooms available");
        }
    }
}

public class UseCase3InventorySetup {
    public static void main(String[] args) {
        System.out.println("******************************************");
        System.out.println("Book My Stay App - Version 3.0");
        System.out.println("******************************************");

        RoomInventory hotelInventory = new RoomInventory();

        hotelInventory.addRoomType("Single Room", 10);
        hotelInventory.addRoomType("Double Room", 5);
        hotelInventory.addRoomType("Suite Room", 2);

        hotelInventory.displayInventory();
        System.out.println("******************************************");

        String checkType = "Double Room";
        System.out.println("Checking availability for " + checkType + ": " +
                hotelInventory.getAvailability(checkType));
        System.out.println("******************************************");
    }
}