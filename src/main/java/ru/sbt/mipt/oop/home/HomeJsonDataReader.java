package ru.sbt.mipt.oop.home;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HomeJsonDataReader implements  HomeDataReader{
    private String filePath;

    public HomeJsonDataReader(String filePath){
        this.filePath = filePath;
    }
    @Override
    public SmartHome readHomeData(){
        Gson gson = new Gson();
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(json, SmartHome.class);
    }
}
