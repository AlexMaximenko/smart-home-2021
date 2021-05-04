package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.SensorEvent;

public class EventHandlerAdapter implements EventHandler {
    private final ru.sbt.mipt.oop.events.handlers.EventHandler eventHandler;

    public EventHandlerAdapter(ru.sbt.mipt.oop.events.handlers.EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        EventType type = CCSensorEventAdapter.convertLibraryEventTypeToEventType(event.getEventType());
        this.eventHandler.handleEvent(new SensorEvent(type, event.getObjectId()));
    }
}
