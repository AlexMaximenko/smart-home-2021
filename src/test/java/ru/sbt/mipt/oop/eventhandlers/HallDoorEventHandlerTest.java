package ru.sbt.mipt.oop.eventhandlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;
import ru.sbt.mipt.oop.smartelements.Door;
import ru.sbt.mipt.oop.smartelements.Light;
import ru.sbt.mipt.oop.smartelements.Room;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HallDoorEventHandlerTest {

    @Test
    void testTurningLightsOff(){
        HallDoorEventHandler handler= new HallDoorEventHandler();
        Door doorHall = new Door(true, "hallDoor");
        Door doorBath = new Door(false, "bathDoor");
        Light lightHall = new Light("lightHall", true);
        Light lightBath = new Light("lightBath", true);
        Room roomBath = new Room(Arrays.asList(lightBath), Arrays.asList(doorBath), "bath");
        Room roomHall = new Room(Arrays.asList(lightHall), Arrays.asList(doorHall), "hall");
        SmartHome smartHome = new SmartHome(Arrays.asList(roomBath, roomHall));
        SensorEvent hallDoorClosed = new SensorEvent(SensorEventType.DOOR_CLOSED, "hallDoor");
        handler.handleEvent(smartHome, hallDoorClosed);
        assert(!lightBath.isOn());
        assert(!lightHall.isOn());
    }

    @Test
    void testCorrectIsHallDoorChecking(){
        HallDoorEventHandler handler= new HallDoorEventHandler();
        Door doorHall = new Door(true, "hallDoor");
        Door doorBath = new Door(false, "bathDoor");
        Light lightHall = new Light("lightHall", false);
        Light lightBath = new Light("lightBath", true);
        Room roomBath = new Room(Arrays.asList(lightBath), Arrays.asList(doorBath), "bath");
        Room roomHall = new Room(Arrays.asList(lightHall), Arrays.asList(doorHall), "hall");
        SmartHome smartHome = new SmartHome(Arrays.asList(roomBath, roomHall));
        SensorEvent hallDoorClosed = new SensorEvent(SensorEventType.DOOR_CLOSED, "bathDoor");
        handler.handleEvent(smartHome, hallDoorClosed);
        assert(lightBath.isOn());
        assert(!lightHall.isOn());
    }
}