package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.smartelements.Light;
import ru.sbt.mipt.oop.smartelements.SmartHome;

public class TurnAllLightsOnCommand implements Command{
    private final SmartHome smartHome;

    public TurnAllLightsOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Light){
                ((Light) object).setOn(true);
            }
        });
    }
}
