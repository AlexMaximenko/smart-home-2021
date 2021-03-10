package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.smartelements.Room;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome {
    private Collection<Room> rooms;
    private final HomeDataWriter homeDataWriter;
    private final HomeDataReader homeDataReader;

    public SmartHome(HomeDataWriter homeDataWriter, HomeDataReader homeDataReader) {
        this.homeDataWriter = homeDataWriter;
        this.homeDataReader = homeDataReader;
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms, HomeDataWriter homeDataWriter, HomeDataReader homeDataReader) {
        this.rooms = rooms;
        this.homeDataWriter = homeDataWriter;
        this.homeDataReader = homeDataReader;
    }

    public void writeToFile(String filePath){
        this.homeDataWriter.writeHomeData(filePath, this);
    }

    public SmartHome readFromFile(String filePath){
        return this.homeDataReader.readHomeData(filePath);
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }
}
