package ru.sbt.mipt.oop.eventhandlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;
import ru.sbt.mipt.oop.smartelements.Door;
import ru.sbt.mipt.oop.smartelements.Light;
import ru.sbt.mipt.oop.smartelements.Room;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DoorEventHandlerTest {

    @Test
    void testDoorOpening() {
        DoorEventHandler handler = new DoorEventHandler();
        Door door1 = new Door(true, "1");
        Door door2 = new Door(false, "2");
        Room room = new Room(Collections.emptyList(), Arrays.asList(door1, door2), "testRoom");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        SensorEvent doorOpen1 = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        SensorEvent doorOpen2 = new SensorEvent(SensorEventType.DOOR_OPEN, "2");
        handler.handleEvent(smartHome, doorOpen1);
        handler.handleEvent(smartHome, doorOpen2);
        assert(door1.isOpen());
        assert(door2.isOpen());
    }

    @Test
    void testDoorClosing(){
        DoorEventHandler handler = new DoorEventHandler();
        Door door1 = new Door(true, "1");
        Door door2 = new Door(false, "2");
        Room room = new Room(Collections.emptyList(), Arrays.asList(door1, door2), "testRoom");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        SensorEvent doorOpen1 = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        SensorEvent doorOpen2 = new SensorEvent(SensorEventType.DOOR_CLOSED, "2");
        handler.handleEvent(smartHome, doorOpen1);
        handler.handleEvent(smartHome, doorOpen2);
        assert(!door1.isOpen());
        assert(!door2.isOpen());
    }


}