import models.Booking;
import models.Room;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HotelManager {
    private List<Room> rooms = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    // Adds a new room to the system
    public void addRoom(Room room) {
        rooms.add(room);
        System.out.println("Room added successfully: " + room);
    }


    // Displays all rooms
    public void viewRooms() {
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
        } else {
            rooms.forEach(System.out::println);
        }
    }

    // Updates a room's details
    public void updateRoom(int roomNumber, String type, double price, boolean isAvailable) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setType(type);
                room.setPrice(price);
                room.setAvailable(isAvailable);
                System.out.println("Room updated successfully: " + room);
                return;
            }
        }
        System.out.println("Room not found with number: " + roomNumber);
    }

    // Deletes a room by its room number
    public void deleteRoom(int roomNumber) {
        boolean removed = rooms.removeIf(room -> room.getRoomNumber() == roomNumber);
        if (removed) {
            System.out.println("Room deleted successfully.");
        } else {
            System.out.println("Room not found with number: " + roomNumber);
        }
    }

    // Books a room if available for the given date range
    public void bookRoom(String customerName, int roomNumber, String startDate, String endDate) {
        // Check if room exists
        Room room = rooms.stream()
                .filter(r -> r.getRoomNumber() == roomNumber)
                .findFirst()
                .orElse(null);

        if (room == null) {
            System.out.println("Room not found with number: " + roomNumber);
            return;
        }

        // Check if the room is already booked during the given date range
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        boolean isBooked = bookings.stream()
                .anyMatch(b -> b.getRoomNumber() == roomNumber &&
                        !(LocalDate.parse(b.getEndDate()).isBefore(start) || LocalDate.parse(b.getStartDate()).isAfter(end)));

        if (isBooked) {
            System.out.println("Room is already booked for the given date range.");
        } else {
            // Create and add the booking
            Booking booking = new Booking(generateBookingId(), roomNumber, customerName, startDate, endDate);
            bookings.add(booking);
            System.out.println("Room booked successfully: " + booking);
        }
    }

    // Searches for rooms based on type, price range, and availability
    public void searchRooms(String type, double minPrice, double maxPrice, Boolean isAvailable) {
        List<Room> filteredRooms = rooms.stream()
                .filter(room -> (type == null || type.isEmpty() || room.getType().equalsIgnoreCase(type)) && // Handle null or empty type
                        room.getPrice() >= minPrice && // Price should be greater than or equal to minPrice
                        room.getPrice() <= maxPrice && // Price should be less than or equal to maxPrice
                        (isAvailable == null || room.isAvailable() == isAvailable)) // Check availability if provided
                .collect(Collectors.toList());

        if (filteredRooms.isEmpty()) {
            System.out.println("No rooms found matching the criteria.");
        } else {
            filteredRooms.forEach(System.out::println);
        }
    }


    // Generates a report of booked and available rooms for a given date range
    public void generateReport(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        System.out.println("---- Report ----");
        System.out.println("Booked Rooms:");
        bookings.stream()
                .filter(b -> !(LocalDate.parse(b.getEndDate()).isBefore(start) || LocalDate.parse(b.getStartDate()).isAfter(end)))
                .forEach(System.out::println);

        System.out.println("Available Rooms:");
        List<Integer> bookedRoomNumbers = bookings.stream()
                .filter(b -> !(LocalDate.parse(b.getEndDate()).isBefore(start) || LocalDate.parse(b.getStartDate()).isAfter(end)))
                .map(Booking::getRoomNumber)
                .collect(Collectors.toList());

        rooms.stream()
                .filter(r -> !bookedRoomNumbers.contains(r.getRoomNumber()))
                .forEach(System.out::println);
    }

    // Generates a unique booking ID
    private String generateBookingId() {
        return "BOOK" + (bookings.size() + 1);
    }

    // Saves all rooms to a file (delegates to FileHandler)
    public void saveRooms(String filePath) {
        try {
            FileHandler.saveRoomsToFile(filePath, rooms);
            System.out.println("Rooms saved to file successfully.");
        } catch (Exception e) {
            System.out.println("Error saving rooms to file: " + e.getMessage());
        }
    }

    // Loads all rooms from a file (delegates to FileHandler)
    public void loadRooms(String filePath) {
        try {
            rooms = FileHandler.loadRoomsFromFile(filePath);
            System.out.println("Rooms loaded from file successfully.");
        } catch (Exception e) {
            System.out.println("Error loading rooms from file: " + e.getMessage());
        }
    }
}


