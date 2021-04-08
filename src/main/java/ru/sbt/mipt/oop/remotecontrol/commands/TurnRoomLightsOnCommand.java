package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.smartelements.Light;
import ru.sbt.mipt.oop.smartelements.Room;
import ru.sbt.mipt.oop.smartelements.SmartHome;

public class TurnRoomLightsOnCommand implements Command{
    private final String roomName;
    private final SmartHome smartHome;

    public TurnRoomLightsOnCommand(String roomName, SmartHome smartHome) {
        this.roomName = roomName;
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Room && ((Room) object).getName().equals(roomName)){
                ((Room) object).execute(light -> {
                    if (light instanceof Light){
                        ((Light) light).setOn(true);
                    }
                });
            }
        });
    }
}
