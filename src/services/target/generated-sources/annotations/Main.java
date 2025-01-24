import models.Room;

import java.util.Scanner;

public class Main {
    private static HotelManager hotelManager = new HotelManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1 -> addRoom(scanner);
                case 2 -> viewRooms();
                case 3 -> updateRoom(scanner);
                case 4 -> deleteRoom(scanner);
                case 5 -> bookRoom(scanner);
                case 6 -> searchRooms(scanner);
                case 7 -> generateReport(scanner);
                case 8 -> saveRooms(scanner);
                case 9 -> loadRooms(scanner);
                case 0 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Hotel Booking Management System ---");
        System.out.println("1. Add Room");
        System.out.println("2. View Rooms");
        System.out.println("3. Update Room");
        System.out.println("4. Delete Room");
        System.out.println("5. Book Room");
        System.out.println("6. Search Rooms");
        System.out.println("7. Generate Report");
        System.out.println("8. Save Rooms to File");
        System.out.println("9. Load Rooms from File");
        System.out.println("0. Exit");
        System.out.println("---------------------------------------");
    }



    private static void viewRooms() {
        hotelManager.viewRooms();
    }

    private static void updateRoom(Scanner scanner) {
        System.out.print("Enter room number to update: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        System.out.print("Enter new room type (Single/Double/Suite): ");
        String type = scanner.nextLine();

        System.out.print("Enter new room price: ");
        double price = scanner.nextDouble();

        System.out.print("Is the room available? (true/false): ");
        boolean isAvailable = scanner.nextBoolean();

        hotelManager.updateRoom(roomNumber, type, price, isAvailable);
    }

    private static void deleteRoom(Scanner scanner) {
        System.out.print("Enter room number to delete: ");
        int roomNumber = scanner.nextInt();
        hotelManager.deleteRoom(roomNumber);
    }

    private static void bookRoom(Scanner scanner) {
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        System.out.print("Enter booking start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();

        System.out.print("Enter booking end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();

        hotelManager.bookRoom(customerName, roomNumber, startDate, endDate);
    }

    private static void searchRooms(Scanner scanner) {
        System.out.print("Enter room type to filter (or leave blank): ");
        String type = scanner.nextLine().trim();
        if (type.isEmpty()) type = null;

        System.out.print("Enter minimum price: ");
        double minPrice = scanner.nextDouble();

        System.out.print("Enter maximum price: ");
        double maxPrice = scanner.nextDouble();

        System.out.print("Filter by availability? (true/false or press Enter to skip): ");
        scanner.nextLine(); // Consume the newline character
        String availabilityInput = scanner.nextLine().trim();
        Boolean isAvailable = null;
        if (!availabilityInput.isEmpty()) {
            isAvailable = Boolean.parseBoolean(availabilityInput);
        }

        hotelManager.searchRooms(type, minPrice, maxPrice, isAvailable);
    }


    private static void generateReport(Scanner scanner) {
        System.out.print("Enter report start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();

        System.out.print("Enter report end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();

        hotelManager.generateReport(startDate, endDate);
    }

    private static void saveRooms(Scanner scanner) {
        System.out.print("Enter file path to save rooms: ");
        String filePath = scanner.nextLine();
        hotelManager.saveRooms(filePath);
    }

    private static void addRoom(Scanner scanner) {
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        System.out.print("Enter room type (Single/Double/Suite): ");
        String type = scanner.nextLine();

        System.out.print("Enter room price: ");
        double price = scanner.nextDouble();

        System.out.print("Is the room available? (true/false): ");
        boolean isAvailable = scanner.nextBoolean();

        Room room = new Room(roomNumber, type, price, isAvailable);
        hotelManager.addRoom(room);
        System.out.println("Room added successfully!");
    }

    private static void loadRooms(Scanner scanner) {
        System.out.print("Enter file path to load rooms: ");
        String filePath = scanner.nextLine();
        hotelManager.loadRooms(filePath);
    }
}
