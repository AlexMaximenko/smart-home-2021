package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.alarm.AlarmSystem;

public class AlarmActivateCommand implements Command{
    private final AlarmSystem alarmSystem;
    private final String password;

    public AlarmActivateCommand(AlarmSystem alarmSystem, String password) {
        this.alarmSystem = alarmSystem;
        this.password = password;
    }

    @Override
    public void execute() {
        alarmSystem.activate(password);
    }
}
