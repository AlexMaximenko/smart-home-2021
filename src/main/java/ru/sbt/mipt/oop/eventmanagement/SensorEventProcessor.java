package ru.sbt.mipt.oop.eventmanagement;

import ru.sbt.mipt.oop.home.SmartHome;

public interface SensorEventProcessor {
    void startProcessingLoop(SmartHome smartHome);
}
