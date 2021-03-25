package ru.sbt.mipt.oop.events.handlers.decorators;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.events.handlers.EventHandler;
import ru.sbt.mipt.oop.smartelements.Door;
import ru.sbt.mipt.oop.smartelements.Room;
import ru.sbt.mipt.oop.smartelements.SmartHome;

import java.util.Arrays;
import java.util.Collections;

class AlarmSystemDecoratorTest {
    @Test
    void testNoHandlingSensorEventsInEmergencySituation(){
        Door door1 = new Door(true, "1");
        Room room = new Room(Collections.emptyList(), Arrays.asList(door1), "testRoom");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        EventHandler eventHandler = new DoorEventHandler(smartHome);
        AlarmSystem alarmSystem = new AlarmSystem(smartHome);
        alarmSystem.raiseAlarm();
        SensorEvent doorOpen = new SensorEvent(EventType.DOOR_CLOSED, "1");
        eventHandler = new AlarmSystemDecorator(eventHandler, alarmSystem);
        eventHandler.handleEvent(doorOpen);
        assert(door1.isOpen());
    }

    @Test
    void testRaisingAlarmWhenStateIsActivated(){
        Door door1 = new Door(true, "1");
        Room room = new Room(Collections.emptyList(), Arrays.asList(door1), "testRoom");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        EventHandler eventHandler = new DoorEventHandler(smartHome);
        AlarmSystem alarmSystem = new AlarmSystem(smartHome);
        alarmSystem.activate("java");
        SensorEvent doorOpen = new SensorEvent(EventType.DOOR_CLOSED, "1");
        eventHandler = new AlarmSystemDecorator(eventHandler, alarmSystem);
        eventHandler.handleEvent(doorOpen);
        assert(door1.isOpen());
    }
}