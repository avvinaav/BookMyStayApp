import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "Reservation [Guest: " + guestName + ", Room: " + roomType + "]";
    }
}

class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Enqueued: " + reservation.getGuestName() + " for " + reservation.getRoomType());
    }

    public void displayQueue() {
        System.out.println("\n--- Current Booking Request Queue (FIFO) ---");
        if (requestQueue.isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            for (Reservation res : requestQueue) {
                System.out.println(res);
            }
        }
    }

    public Reservation processNextRequest() {
        return requestQueue.poll();
    }
}

public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {
        System.out.println("******************************************");
        System.out.println("Book My Stay App - Version 5.0");
        System.out.println("******************************************");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        bookingQueue.addRequest(new Reservation("Alice", "Suite Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Single Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Double Room"));

        bookingQueue.displayQueue();

        System.out.println("\n--- Preparing for Processing ---");
        Reservation next = bookingQueue.processNextRequest();
        if (next != null) {
            System.out.println("Next request to be processed: " + next.getGuestName());
        }

        System.out.println("******************************************");
    }
}