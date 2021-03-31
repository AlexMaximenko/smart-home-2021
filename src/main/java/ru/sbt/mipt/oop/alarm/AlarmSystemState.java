package ru.sbt.mipt.oop.alarm;

public interface AlarmSystemState {
    void activate(String code);

    void deactivate(String code);
}
