package ru.sbt.mipt.oop.events.management;

import ru.sbt.mipt.oop.events.Event;

public interface EventGenerator {
    Event getNextEvent();
}
