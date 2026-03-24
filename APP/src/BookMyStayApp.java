abstract class Room {
    protected String type;
    protected double price;

    public Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public abstract void displayDetails();
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1500.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Type: " + type + " | Price: Rs." + price + " | Capacity: 1 Adult");
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2500.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Type: " + type + " | Price: Rs." + price + " | Capacity: 2 Adults");
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 5000.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Type: " + type + " | Price: Rs." + price + " | Capacity: 2 Adults + 2 Children");
    }
}

public class UseCase2RoomInitialization {
    public static void main(String[] args) {
        System.out.println("******************************************");
        System.out.println("Book My Stay App - Version 2.0");
        System.out.println("******************************************");

        Room single = new SingleRoom();
        Room dual = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailability = 10;
        int doubleAvailability = 5;
        int suiteAvailability = 2;

        single.displayDetails();
        System.out.println("Available: " + singleAvailability);
        System.out.println("------------------------------------------");

        dual.displayDetails();
        System.out.println("Available: " + doubleAvailability);
        System.out.println("------------------------------------------");

        suite.displayDetails();
        System.out.println("Available: " + suiteAvailability);
        System.out.println("******************************************");
    }
}