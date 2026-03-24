import java.util.*;

class Service {
    private String name;
    private double cost;

    public Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() { return name; }
    public double getCost() { return cost; }

    @Override
    public String toString() {
        return name + " (Rs." + cost + ")";
    }
}

class AddOnManager {
    private Map<String, List<Service>> reservationAddOns = new HashMap<>();

    public void addServiceToReservation(String reservationId, Service service) {
        reservationAddOns.putIfAbsent(reservationId, new ArrayList<>());
        reservationAddOns.get(reservationId).add(service);
        System.out.println("Added " + service.getName() + " to Reservation: " + reservationId);
    }

    public double calculateTotalAddOnCost(String reservationId) {
        List<Service> services = reservationAddOns.getOrDefault(reservationId, new ArrayList<>());
        double total = 0;
        for (Service s : services) {
            total += s.getCost();
        }
        return total;
    }

    public void displayAddOns(String reservationId) {
        List<Service> services = reservationAddOns.get(reservationId);
        if (services != null && !services.isEmpty()) {
            System.out.println("Add-ons for " + reservationId + ": " + services);
        } else {
            System.out.println("No add-ons for " + reservationId);
        }
    }
}

public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        System.out.println("******************************************");
        System.out.println("Book My Stay App - Version 7.0");
        System.out.println("******************************************");

        String resId1 = "RES-101";
        String resId2 = "RES-102";

        Service breakfast = new Service("Breakfast Buffet", 500.0);
        Service wifi = new Service("High-Speed WiFi", 200.0);
        Service spa = new Service("Spa Treatment", 1500.0);

        AddOnManager manager = new AddOnManager();

        manager.addServiceToReservation(resId1, breakfast);
        manager.addServiceToReservation(resId1, wifi);
        manager.addServiceToReservation(resId2, spa);

        System.out.println("\n--- Summary ---");
        manager.displayAddOns(resId1);
        System.out.println("Total Add-on Cost for " + resId1 + ": Rs." + manager.calculateTotalAddOnCost(resId1));

        System.out.println("------------------------------------------");
        manager.displayAddOns(resId2);
        System.out.println("Total Add-on Cost for " + resId2 + ": Rs." + manager.calculateTotalAddOnCost(resId2));

        System.out.println("******************************************");
    }
}
