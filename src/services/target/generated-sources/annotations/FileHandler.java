import com.google.gson.Gson;
import models.Room;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler {
    public static void saveRoomsToFile(String filePath, List<Room> rooms) throws IOException {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(gson.toJson(rooms));
        }
    }

    public static List<Room> loadRoomsFromFile(String filePath) throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Room[] roomsArray = gson.fromJson(reader, Room[].class);
            return new ArrayList<>(Arrays.asList(roomsArray));
        }
    }
}

