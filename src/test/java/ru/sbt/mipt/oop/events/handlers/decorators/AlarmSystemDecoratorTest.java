package ru.sbt.mipt.oop.events.handlers.decorators;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.events.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.events.handlers.EventHandler;
import ru.sbt.mipt.oop.smartelements.Door;
import ru.sbt.mipt.oop.smartelements.Room;
import ru.sbt.mipt.oop.smartelements.SmartHome;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class AlarmSystemDecoratorTest {
    @Test
    void testNoHandlingSensorEventsInEmergencySituation(){
        EventHandler eventHandler = new DoorEventHandler();
        Door door1 = new Door(true, "1");
        Room room = new Room(Collections.emptyList(), Arrays.asList(door1), "testRoom");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        AlarmSystem alarmSystem = new AlarmSystem(smartHome);
        alarmSystem.raiseAlarm();
        SensorEvent doorOpen = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        eventHandler = new AlarmSystemDecorator(eventHandler, alarmSystem);
        eventHandler.handleEvent(smartHome, doorOpen);
        assert(door1.isOpen());
    }

    @Test
    void testRaisingAlarmWhenStateIsActivated(){
        EventHandler eventHandler = new DoorEventHandler();
        Door door1 = new Door(true, "1");
        Room room = new Room(Collections.emptyList(), Arrays.asList(door1), "testRoom");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        AlarmSystem alarmSystem = new AlarmSystem(smartHome);
        alarmSystem.activate("java");
        SensorEvent doorOpen = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        eventHandler = new AlarmSystemDecorator(eventHandler, alarmSystem);
        eventHandler.handleEvent(smartHome, doorOpen);
        assert(door1.isOpen());
    }
}