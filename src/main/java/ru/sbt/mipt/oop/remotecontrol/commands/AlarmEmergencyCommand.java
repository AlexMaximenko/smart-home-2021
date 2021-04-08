package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.alarm.AlarmSystem;

public class AlarmEmergencyCommand implements Command{
    private final AlarmSystem alarmSystem;

    public AlarmEmergencyCommand(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void execute() {
        alarmSystem.raiseAlarm();
    }
}
