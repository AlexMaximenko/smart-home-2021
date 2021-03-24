package ru.sbt.mipt.oop.alarm.alarmstates;

public interface AlarmSystemState {
    void activate(String code);

    void deactivate(String code);
}
