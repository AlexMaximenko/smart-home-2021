package ru.sbt.mipt.oop.events.management;

import ru.sbt.mipt.oop.smartelements.SmartHome;

public interface EventProcessor {
    void startProcessingLoop(SmartHome smartHome);
}
