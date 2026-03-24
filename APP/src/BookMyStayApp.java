import java.util.*;
import java.io.*;

class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + " [" + roomType + "]";
    }
}

class HotelState implements Serializable {
    private static final long serialVersionUID = 1L;
    public Map<String, Integer> inventory = new HashMap<>();
    public List<Reservation> history = new ArrayList<>();
}

class PersistenceService {
    private static final String FILE_NAME = "hotel_data.ser";

    public void saveState(HotelState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(state);
            System.out.println("System state saved successfully to " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error saving state: " + e.getMessage());
        }
    }

    public HotelState loadState() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No existing data found. Starting with fresh state.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (HotelState) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Recovery failed: " + e.getMessage());
            return null;
        }
    }
}

public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {
        System.out.println("******************************************");
        System.out.println("Book My Stay App - Version 12.0 (Persistence)");
        System.out.println("******************************************");

        PersistenceService persistence = new PersistenceService();
        HotelState state = persistence.loadState();

        if (state == null) {
            state = new HotelState();
            state.inventory.put("Deluxe", 5);
            state.inventory.put("Standard", 10);
            System.out.println("Initial Inventory Set: " + state.inventory);
        } else {
            System.out.println("RECOVERED Inventory: " + state.inventory);
            System.out.println("RECOVERED Bookings: " + state.history);
        }

        // Simulate a new booking
        if (state.inventory.get("Deluxe") > 0) {
            state.history.add(new Reservation("User_" + System.currentTimeMillis() % 1000, "Deluxe"));
            state.inventory.put("Deluxe", state.inventory.get("Deluxe") - 1);
            System.out.println("New booking added. Saving state...");
            persistence.saveState(state);
        }

        System.out.println("Current Session Complete.");
        System.out.println("******************************************");
    }
}