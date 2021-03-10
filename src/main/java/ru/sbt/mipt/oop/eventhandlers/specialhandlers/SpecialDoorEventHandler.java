package ru.sbt.mipt.oop.eventhandlers.specialhandlers;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;

public interface SpecialDoorEventHandler {
    void specialExecution(SmartHome smartHome, SensorEvent sensorEvent);
}
