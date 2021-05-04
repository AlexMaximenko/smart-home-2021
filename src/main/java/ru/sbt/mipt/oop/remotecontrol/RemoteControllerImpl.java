package ru.sbt.mipt.oop.remotecontrol;

import rc.RemoteControl;
import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.remotecontrol.commands.*;
import ru.sbt.mipt.oop.smartelements.SmartHome;

import java.util.HashMap;
import java.util.Map;

public class RemoteControllerImpl implements RemoteControl {
    private final Map<String, Command> commands;
    private final SmartHome smartHome;
    private final AlarmSystem alarmSystem;
    private final String rcId;

    public RemoteControllerImpl(SmartHome smartHome, AlarmSystem alarmSystem, String rcId, Map<String, Command> commands) {
        this.smartHome = smartHome;
        this.alarmSystem = alarmSystem;
        this.rcId = rcId;
        this.commands = commands;
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        if (commands.containsKey(buttonCode)) commands.get(buttonCode).execute();
    }

    public void setButton(String buttonCode, Command command){
        commands.put(buttonCode, command);
    }
}
