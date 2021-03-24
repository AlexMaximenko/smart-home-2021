package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.smartelements.SmartHome;

public interface EventHandler {
    void handleEvent(SmartHome smartHome, Event event);
}
