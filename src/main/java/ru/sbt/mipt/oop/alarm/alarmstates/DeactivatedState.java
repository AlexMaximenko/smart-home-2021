package ru.sbt.mipt.oop.alarm.alarmstates;

import ru.sbt.mipt.oop.alarm.AlarmSystem;

public class DeactivatedState implements AlarmSystemState {
    private final AlarmSystem alarmSystem;

    public DeactivatedState(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void activate(String code) {
        this.alarmSystem.setState(new ActivatedState(this.alarmSystem, code));
        System.out.println("Сигнализация влючена.");
    }

    @Override
    public void deactivate(String code) {
        System.out.println("Попытка выключить выключенную сигнализацию");
    }
}
