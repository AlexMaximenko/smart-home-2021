package ru.sbt.mipt.oop.adapters;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.events.EventType;

import static org.junit.jupiter.api.Assertions.*;

class CCSensorEventAdapterTest {

    @Test
    void testConvertLibraryEventTypeToEventType(){
        String libraryEventType = "DoorIsClosed";
        EventType expectedType = EventType.DOOR_CLOSED;
        assertEquals(expectedType, CCSensorEventAdapter.convertLibraryEventTypeToEventType(libraryEventType));

        libraryEventType = "LightIsOn";
        expectedType = EventType.LIGHT_ON;
        assertEquals(expectedType, CCSensorEventAdapter.convertLibraryEventTypeToEventType(libraryEventType));

        libraryEventType = "DoorIsUnlocked";
        expectedType = EventType.DOOR_UNLOCKED;
        assertEquals(expectedType, CCSensorEventAdapter.convertLibraryEventTypeToEventType(libraryEventType));
    }

    @Test
    void testConvertEventTypeToLibraryEventType(){
        String expectedLibraryEventType = "DoorIsClosed";
        EventType eventType = EventType.DOOR_CLOSED;
        assertEquals(expectedLibraryEventType, CCSensorEventAdapter.convertEventTypeToLibraryEventType(eventType));

        expectedLibraryEventType = "LightIsOn";
        eventType = EventType.LIGHT_ON;
        assertEquals(expectedLibraryEventType, CCSensorEventAdapter.convertEventTypeToLibraryEventType(eventType));
        expectedLibraryEventType = "DoorIsUnlocked";
        eventType = EventType.DOOR_UNLOCKED;
        assertEquals(expectedLibraryEventType, CCSensorEventAdapter.convertEventTypeToLibraryEventType(eventType));    }

}