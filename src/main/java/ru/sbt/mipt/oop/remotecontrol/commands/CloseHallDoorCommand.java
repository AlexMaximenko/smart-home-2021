package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.smartelements.Door;
import ru.sbt.mipt.oop.smartelements.Room;
import ru.sbt.mipt.oop.smartelements.SmartHome;

public class CloseHallDoorCommand implements Command{
    private final SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Room && ((Room)object).getName().equals("hall")){
                ((Room) object).execute(door -> {
                    if (door instanceof Door){
                        ((Door) door).setOpen(false);
                    }
                });
            }
        });
    }
}
