package ru.sbt.mipt.oop.events.handlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.alarm.alarmstates.ActivatedState;
import ru.sbt.mipt.oop.alarm.alarmstates.DeactivatedState;
import ru.sbt.mipt.oop.alarm.alarmstates.EmergencyState;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.smartelements.Door;
import ru.sbt.mipt.oop.smartelements.Room;
import ru.sbt.mipt.oop.smartelements.SmartHome;

import java.util.Arrays;
import java.util.Collections;

class AlarmEventHandlerTest {
    @Test
    void testActivation(){
        Door door1 = new Door(true, "1");
        Door door2 = new Door(false, "2");
        Room room = new Room(Collections.emptyList(), Arrays.asList(door1, door2), "testRoom");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        AlarmSystem alarmSystem = new AlarmSystem(smartHome);
        AlarmEventHandler handler = new AlarmEventHandler(alarmSystem);
        AlarmEvent event = new AlarmEvent(EventType.ALARM_ACTIVATE, "java");
        handler.handleEvent(event);
        assert(alarmSystem.getState() instanceof ActivatedState);
    }

    @Test
    void testCorrectDeactivation(){
        Door door1 = new Door(true, "1");
        Door door2 = new Door(false, "2");
        Room room = new Room(Collections.emptyList(), Arrays.asList(door1, door2), "testRoom");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        AlarmSystem alarmSystem = new AlarmSystem(smartHome);
        AlarmEventHandler handler = new AlarmEventHandler(alarmSystem);
        alarmSystem.activate("java");
        AlarmEvent event = new AlarmEvent(EventType.ALARM_DEACTIVATE, "java");
        handler.handleEvent(event);
        assert(alarmSystem.getState() instanceof DeactivatedState);
    }

    @Test
    void testIncorrectDeactivation(){
        Door door1 = new Door(true, "1");
        Door door2 = new Door(false, "2");
        Room room = new Room(Collections.emptyList(), Arrays.asList(door1, door2), "testRoom");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        AlarmSystem alarmSystem = new AlarmSystem(smartHome);
        AlarmEventHandler handler = new AlarmEventHandler(alarmSystem);
        alarmSystem.activate("java");
        AlarmEvent event = new AlarmEvent(EventType.ALARM_DEACTIVATE, "not_java");
        handler.handleEvent(event);
        assert(alarmSystem.getState() instanceof EmergencyState);
    }
}