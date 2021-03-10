package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.home.SmartHome;

public interface EventHandler {
    void executeEvent(SmartHome smartHome, SensorEvent sensorEvent);
}
