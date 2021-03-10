package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.eventhandlers.specialhandlers.SpecialDoorEventHandler;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.smartelements.Door;
import ru.sbt.mipt.oop.smartelements.Room;

import java.util.Collection;

import static ru.sbt.mipt.oop.sensor.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.sensor.SensorEventType.DOOR_OPEN;

public class CommonDoorEventHandler implements EventHandler {
    private final Collection<SpecialDoorEventHandler> specialDoorEventHandlers;

    public CommonDoorEventHandler(Collection<SpecialDoorEventHandler> specialDoorEventHandlers) {
        this.specialDoorEventHandlers = specialDoorEventHandlers;
    }

    public CommonDoorEventHandler(){
        specialDoorEventHandlers = null;
    }

    @Override
    public void executeEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        if (this.isDoorEvent(smartHome, sensorEvent)) {
            this.changeDoorState(smartHome, sensorEvent);
            if (specialDoorEventHandlers != null) {
                for (SpecialDoorEventHandler specialDoorEventHandler : specialDoorEventHandlers) {
                    specialDoorEventHandler.specialExecution(smartHome, sensorEvent);
                }
            }
        }
    }

    protected void changeDoorState(SmartHome smartHome, SensorEvent sensorEvent){
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(sensorEvent.getObjectId())) {
                    if (sensorEvent.getType().equals(DOOR_CLOSED)){
                        door.setOpen(false);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                    }
                    if (sensorEvent.getType().equals(DOOR_OPEN)){
                        door.setOpen(true);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    }
                }
            }
        }
    }

    private boolean isDoorEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        return  (sensorEvent.getType().equals(DOOR_CLOSED) || sensorEvent.getType().equals(DOOR_OPEN));
    }
}
