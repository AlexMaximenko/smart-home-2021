package ru.sbt.mipt.oop.alarm;

public class EmergencyState implements AlarmSystemState {
    private final AlarmSystem alarmSystem;

    public EmergencyState(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void activate(String code) {
        alarmSystem.sendMessage("Сигнализация в режиме тревоги, ее нельзя включить.");
    }

    @Override
    public void deactivate(String code) {
        alarmSystem.sendMessage("Сигнализация в режиме тревоги, ее нельзя отключить.");
    }
}
