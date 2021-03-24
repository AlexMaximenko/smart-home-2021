package ru.sbt.mipt.oop.events.handlers.decorators;

import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.handlers.EventHandler;
import ru.sbt.mipt.oop.smartelements.SmartHome;

public class EventHandlerDecorator implements EventHandler {

    EventHandler eventHandler;

    public EventHandlerDecorator(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void handleEvent(SmartHome smartHome, Event event) {
        eventHandler.handleEvent(smartHome, event);
    }
}
