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

    public RemoteControllerImpl(SmartHome smartHome, AlarmSystem alarmSystem, String rcId) {
        this.smartHome = smartHome;
        this.alarmSystem = alarmSystem;
        this.rcId = rcId;
        this.commands = new HashMap<String, Command>();
        this.setDefaultButtons();
    }

    private void setDefaultButtons(){
        commands.put("A", new AlarmActivateCommand(this.alarmSystem, this.alarmSystem.getDefaultPassword()));
        commands.put("B", new AlarmEmergencyCommand(this.alarmSystem));
        commands.put("C", new CloseHallDoorCommand(this.smartHome));
        commands.put("D", new TurnAllLightsOffCommand(this.smartHome));
        commands.put("1", new TurnAllLightsOnCommand(this.smartHome));
        commands.put("2", new TurnRoomLightsOnCommand("hall", this.smartHome));
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        commands.get(buttonCode).execute();
    }

    public void setButton(String buttonCode, Command command){
        commands.put(buttonCode, command);
    }
}
