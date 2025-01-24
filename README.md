Hotel Booking Management System
Introduction

- A Java-based console application for managing hotel bookings.

- Provides functionalities to add, view, update, delete rooms, and book rooms.

- Includes search functionality and generates reports for booked and available rooms.

- Data is persisted in JSON File.

Features

1. Room Management (Add, View, Update, Delete rooms)

2. Booking System (Book rooms for specific dates)

3. Search Functionality (Filter by room type, price, availability)

4. Reports (Booked and available rooms for a given date range)

5. Data Persistence (Save and load rooms from a file)

Create and Setup Project

1.Open IntelliJ IDE

2.Go to File -> New -> Project

3.In Project, name the project, choose the Build System as Maven, Select JDK appropriate version click create

4.After that add dependency gson in maven repository official website

5.If you want to run the application go to Main.java file and we able to see the run icon

1.Add Room

-    Allows users to add new rooms to the system.
Room details include: room number, type (Single/Double/Suite), price, availability.

2. View & Update Rooms

 -   View all rooms (available and unavailable).
 -    Update room details (room number, type, price, availability).

3. Delete Room

-  Delete rooms from the system by specifying room number.

4. Book Room

- Book rooms by specifying customer name, room number, start date, and end date.
- Prevents double booking by checking availability for the specified dates.

5. Search Rooms

- Search for rooms by type, price range, and availability.
- Filters rooms based on the given criteria.

6. Generate Report

- Generate reports for booked and available rooms within a given date range.
  
7. Save and Load data

- Save rooms data to a file.
- Load rooms data from a file.
- Supports file formats like JSON File


Future Enhancement

1.Convert console Application into web Application

2.Improve Calendar-based date picker usability

3.Payment Integration

4.Notification and Remainders

5.Add room features(such as wifi, AC, ..etc)

6.Mobile App Integration







