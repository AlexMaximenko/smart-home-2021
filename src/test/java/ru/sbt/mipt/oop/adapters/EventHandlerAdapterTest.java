package ru.sbt.mipt.oop.adapters;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.events.EventType;

import static org.junit.jupiter.api.Assertions.*;

class CCSensorEventAdapterTest {

    @Test
    void testConvertLibraryEventTypeToEventType() {
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
}