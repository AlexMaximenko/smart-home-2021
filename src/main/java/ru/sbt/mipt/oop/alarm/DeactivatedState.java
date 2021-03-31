package ru.sbt.mipt.oop.alarm;

public class DeactivatedState implements AlarmSystemState {
    private final AlarmSystem alarmSystem;

    public DeactivatedState(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void activate(String code) {
        this.alarmSystem.setState(new ActivatedState(this.alarmSystem, code));
        alarmSystem.sendMessage("Сигнализация влючена.");
    }

    @Override
    public void deactivate(String code) {
        alarmSystem.sendMessage("Попытка выключить выключенную сигнализацию");
    }
}
