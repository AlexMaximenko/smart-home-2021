package ru.sbt.mipt.oop.alarm.alarmstates;

import ru.sbt.mipt.oop.alarm.AlarmSystem;

public class EmergencyState implements AlarmSystemState {
    private final AlarmSystem alarmSystem;

    public EmergencyState(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void activate(String code) {
        System.out.println("Сигнализация в режиме тревоги, ее нельзя включить.");
    }

    @Override
    public void deactivate(String code) {
        System.out.println("Сигнализация в режиме тревоги, ее нельзя отключить.");
    }
}
