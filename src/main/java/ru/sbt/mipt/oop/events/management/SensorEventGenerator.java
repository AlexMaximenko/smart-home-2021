package ru.sbt.mipt.oop.events.management;

import ru.sbt.mipt.oop.events.SensorEvent;

public interface SensorEventGenerator {
    SensorEvent getNextSensorEvent();
}
