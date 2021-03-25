package ru.sbt.mipt.oop.events.handlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.smartelements.SmartHome;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.smartelements.Door;
import ru.sbt.mipt.oop.smartelements.Room;

import java.util.Arrays;
import java.util.Collections;

class DoorEventHandlerTest {

    @Test
    void testDoorOpening() {
        Door door1 = new Door(true, "1");
        Door door2 = new Door(false, "2");
        Room room = new Room(Collections.emptyList(), Arrays.asList(door1, door2), "testRoom");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        DoorEventHandler handler = new DoorEventHandler(smartHome);
        SensorEvent doorOpen1 = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        SensorEvent doorOpen2 = new SensorEvent(SensorEventType.DOOR_OPEN, "2");
        handler.handleEvent(doorOpen1);
        handler.handleEvent(doorOpen2);
        assert(door1.isOpen());
        assert(door2.isOpen());
    }

    @Test
    void testDoorClosing(){
        Door door1 = new Door(true, "1");
        Door door2 = new Door(false, "2");
        Room room = new Room(Collections.emptyList(), Arrays.asList(door1, door2), "testRoom");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        DoorEventHandler handler = new DoorEventHandler(smartHome);
        SensorEvent doorOpen1 = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        SensorEvent doorOpen2 = new SensorEvent(SensorEventType.DOOR_CLOSED, "2");
        handler.handleEvent(doorOpen1);
        handler.handleEvent(doorOpen2);
        assert(!door1.isOpen());
        assert(!door2.isOpen());
    }


}