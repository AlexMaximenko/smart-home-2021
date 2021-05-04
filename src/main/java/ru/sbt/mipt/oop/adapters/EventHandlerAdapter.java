package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.SensorEvent;

import java.util.HashMap;
import java.util.Map;

public class EventHandlerAdapter implements EventHandler {
    private final ru.sbt.mipt.oop.events.handlers.EventHandler eventHandler;
    private final Map<String, EventType> stringToEventTypeMap;

    public EventHandlerAdapter(ru.sbt.mipt.oop.events.handlers.EventHandler eventHandler) {
        this.eventHandler = eventHandler;
        this.stringToEventTypeMap = new HashMap<String, EventType>();
        this.fillStringToEventTypeMap();
    }

    private void fillStringToEventTypeMap(){
        this.stringToEventTypeMap.put("LightIsOn", EventType.LIGHT_ON);
        this.stringToEventTypeMap.put("LightIsOff", EventType.LIGHT_OFF);
        this.stringToEventTypeMap.put("DoorIsOpen", EventType.DOOR_OPEN);
        this.stringToEventTypeMap.put("DoorIsClosed", EventType.DOOR_CLOSED);
        this.stringToEventTypeMap.put("DoorIsLocked", EventType.DOOR_LOCKED);
        this.stringToEventTypeMap.put("DoorIsUnlocked", EventType.DOOR_UNLOCKED);
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        EventType type = stringToEventTypeMap.get(event.getEventType());
        this.eventHandler.handleEvent(new SensorEvent(type, event.getObjectId()));
    }
}
