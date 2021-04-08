package ru.sbt.mipt.oop.remotecontrol;

import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.remotecontrol.commands.Command;

import java.util.HashMap;
import java.util.Map;

public class RemoteRegistryImpl extends RemoteControlRegistry {
    private final Map<String, RemoteControl> remoteControlMap;

    public RemoteRegistryImpl() {
        remoteControlMap = new HashMap<String, RemoteControl>();
    }

    @Override
    public void registerRemoteControl(RemoteControl remoteControl, String rcId){
        remoteControlMap.put(rcId, remoteControl);
    }

    public void setButton(String button, Command command, String rcId){
        RemoteControl remoteControl = remoteControlMap.get(rcId);
        ((RemoteControllerImpl)remoteControl).setButton(button, command);
    }
}
