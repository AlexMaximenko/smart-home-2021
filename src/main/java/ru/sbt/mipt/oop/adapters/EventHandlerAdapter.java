package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.SensorEvent;

import java.util.HashMap;
import java.util.Map;

public class EventHandlerAdapter implements EventHandler {
    private final ru.sbt.mipt.oop.events.handlers.EventHandler eventHandler;
    private final Map<String, EventType> ccEventTypeToEventTypeMap;

    public EventHandlerAdapter(ru.sbt.mipt.oop.events.handlers.EventHandler eventHandler, Map<String, EventType> ccEventTypeToEventTypeMap) {
        this.eventHandler = eventHandler;
        this.ccEventTypeToEventTypeMap = ccEventTypeToEventTypeMap;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        if (!ccEventTypeToEventTypeMap.containsKey(event.getEventType()))
        {
            return;
        }

        EventType type = ccEventTypeToEventTypeMap.get(event.getEventType());
        this.eventHandler.handleEvent(new SensorEvent(type, event.getObjectId()));
    }
}
