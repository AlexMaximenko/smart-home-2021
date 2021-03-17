package ru.sbt.mipt.oop.eventmanagement;

import ru.sbt.mipt.oop.sensor.SensorEvent;

public interface SensorEventGenerator {
    SensorEvent getNextSensorEvent();
}
