package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.events.handlers.LightEventHandler;
import ru.sbt.mipt.oop.smartelements.Door;
import ru.sbt.mipt.oop.smartelements.Light;
import ru.sbt.mipt.oop.smartelements.Room;
import ru.sbt.mipt.oop.smartelements.SmartHome;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class EventHandlerAdapterTest {

    @Test
    void testDoorEventsHandling() {
        String libraryEventType = "DoorIsClosed";
        Door door1 = new Door(true, "1");
        Door door2 = new Door(false, "2");
        Room room = new Room(Collections.emptyList(), Arrays.asList(door1, door2), "testRoom");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        DoorEventHandler handler = new DoorEventHandler(smartHome);
        CCSensorEvent event1 = new CCSensorEvent(libraryEventType, "1");
        CCSensorEvent event2 = new CCSensorEvent(libraryEventType, "2");
        EventHandlerAdapter handlerAdapter = new EventHandlerAdapter(handler);

        handlerAdapter.handleEvent(event1);
        handlerAdapter.handleEvent(event2);
        assertFalse(door1.isOpen());
        assertFalse(door2.isOpen());

        libraryEventType = "DoorIsOpen";
        event1 = new CCSensorEvent(libraryEventType, "1");
        event2 = new CCSensorEvent(libraryEventType, "2");

        handlerAdapter.handleEvent(event1);
        handlerAdapter.handleEvent(event2);
        assertTrue(door1.isOpen());
        assertTrue(door2.isOpen());
    }

    @Test
    void testLightEventsHandling() {
        String libraryEventType = "LightIsOn";
        Light light1 = new Light("1", true);
        Light light2 = new Light("2", false);
        Room room = new Room(Arrays.asList(light1, light2), Collections.emptyList(), "testRoom");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        LightEventHandler handler = new LightEventHandler(smartHome);
        CCSensorEvent event1 = new CCSensorEvent(libraryEventType, "1");
        CCSensorEvent event2 = new CCSensorEvent(libraryEventType, "2");
        EventHandlerAdapter handlerAdapter = new EventHandlerAdapter(handler);

        handlerAdapter.handleEvent(event1);
        handlerAdapter.handleEvent(event2);
        assertTrue(light1.isOn());
        assertTrue(light2.isOn());

        libraryEventType = "LightIsOff";
        event1 = new CCSensorEvent(libraryEventType, "1");
        event2 = new CCSensorEvent(libraryEventType, "2");

        handlerAdapter.handleEvent(event1);
        handlerAdapter.handleEvent(event2);
        assertFalse(light1.isOn());
        assertFalse(light2.isOn());
    }
}