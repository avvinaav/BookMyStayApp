import java.util.*;

class InvalidRoomTypeException extends Exception {
    public InvalidRoomTypeException(String message) {
        super(message);
    }
}

class InsufficientInventoryException extends Exception {
    public InsufficientInventoryException(String message) {
        super(message);
    }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public void addRoomType(String type, int count) {
        inventory.put(type, count);
    }

    public void validateAndAllocate(String type) throws InvalidRoomTypeException, InsufficientInventoryException {
        if (!inventory.containsKey(type)) {
            throw new InvalidRoomTypeException("Error: Room type '" + type + "' does not exist in our system.");
        }

        int available = inventory.get(type);
        if (available <= 0) {
            throw new InsufficientInventoryException("Error: No inventory left for '" + type + "'.");
        }

        inventory.put(type, available - 1);
        System.out.println("Allocation Successful: 1 " + type + " reserved.");
    }

    public void displayStatus() {
        System.out.println("Current Inventory: " + inventory);
    }
}

public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        System.out.println("******************************************");
        System.out.println("Book My Stay App - Version 9.0");
        System.out.println("******************************************");

        RoomInventory hotel = new RoomInventory();
        hotel.addRoomType("Single Room", 1);
        hotel.addRoomType("Suite Room", 2);

        String[] testRequests = {"Single Room", "Single Room", "Penthouse", "Suite Room"};

        for (String request : testRequests) {
            System.out.println("Processing request for: " + request);
            try {
                hotel.validateAndAllocate(request);
            } catch (InvalidRoomTypeException | InsufficientInventoryException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }
            System.out.println("------------------------------------------");
        }

        hotel.displayStatus();
        System.out.println("******************************************");
    }
}