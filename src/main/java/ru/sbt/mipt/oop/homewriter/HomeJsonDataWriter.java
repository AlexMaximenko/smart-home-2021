package ru.sbt.mipt.oop.homewriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.smartelements.SmartHome;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HomeJsonDataWriter implements HomeDataWriter{
    private final String filePath;

    public HomeJsonDataWriter(String filePath){
        this.filePath = filePath;
    }
    @Override
    public void writeHomeData(SmartHome home) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(home);
        Path path = Paths.get(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
