package ru.sbt.mipt.oop.alarm.alarmstates;

import ru.sbt.mipt.oop.alarm.AlarmSystem;

public class EmergencyState implements AlarmSystemState {
    private final AlarmSystem alarmSystem;

    public EmergencyState(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void activate(String code) {
        throw new IllegalStateException("Сигнализация в режиме тревоги, ее нельзя включить.");
    }

    @Override
    public void deactivate(String code) {
        throw new IllegalStateException("Сигнализация в режиме тревоги, ее нельзя отключить.");
    }
}
