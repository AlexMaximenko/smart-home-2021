package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.smartelements.Room;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome {
    private Collection<Room> rooms;

    public SmartHome(){
        this.rooms = new ArrayList<Room>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }
}
