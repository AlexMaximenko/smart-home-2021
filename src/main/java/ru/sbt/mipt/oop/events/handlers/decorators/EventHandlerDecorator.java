package ru.sbt.mipt.oop.events.handlers.decorators;

import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.handlers.EventHandler;

public class EventHandlerDecorator implements EventHandler {

    EventHandler eventHandler;

    public EventHandlerDecorator(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void handleEvent(Event event) {
        eventHandler.handleEvent(event);
    }
}
