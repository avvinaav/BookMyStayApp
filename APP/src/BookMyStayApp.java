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

    @Override
    public String toString() {
        return String.format("ID: %s | Guest: %-10s | Room: %-12s", roomId, guestName, roomType);
    }
}

class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    public void recordBooking(Reservation reservation) {
        history.add(reservation);
    }

    public List<Reservation> getFullHistory() {
        return new ArrayList<>(history);
    }
}

class ReportingService {
    public void generateSummaryReport(BookingHistory bookingHistory) {
        List<Reservation> records = bookingHistory.getFullHistory();
        System.out.println("\n--- Administrative Booking Report ---");
        System.out.println("Total Bookings Processed: " + records.size());

        if (records.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        Map<String, Integer> roomTypeCount = new HashMap<>();
        for (Reservation res : records) {
            System.out.println(res);
            roomTypeCount.put(res.getRoomType(), roomTypeCount.getOrDefault(res.getRoomType(), 0) + 1);
        }

        System.out.println("\n--- Popularity by Room Type ---");
        roomTypeCount.forEach((type, count) -> System.out.println(type + ": " + count + " bookings"));
    }
}

public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        System.out.println("******************************************");
        System.out.println("Book My Stay App - Version 8.0");
        System.out.println("******************************************");

        BookingHistory history = new BookingHistory();
        ReportingService reporter = new ReportingService();

        history.recordBooking(new Reservation("Alice", "Suite Room", "S-101"));
        history.recordBooking(new Reservation("Bob", "Single Room", "SR-101"));
        history.recordBooking(new Reservation("Charlie", "Single Room", "SR-102"));
        history.recordBooking(new Reservation("Diana", "Double Room", "DR-101"));

        reporter.generateSummaryReport(history);

        System.out.println("******************************************");
    }
}