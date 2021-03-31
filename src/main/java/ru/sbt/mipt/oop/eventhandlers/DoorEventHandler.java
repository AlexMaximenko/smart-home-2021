package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.smartelements.Door;

import static ru.sbt.mipt.oop.sensor.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.sensor.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler {
    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent sensorEvent){
        if (!isDoorEvent(smartHome, sensorEvent)) return;
        boolean newState = sensorEvent.getType() == DOOR_OPEN;

        smartHome.execute((object) -> {
            if (!(object instanceof Door)) return;
            if (sensorEvent.getObjectId().equals(((Door) object).getId())) {
                    updateDoorState( (Door) object, newState);
            }
        });
    }

    private void updateDoorState(Door door, boolean newState){
        door.setOpen(newState);
        System.out.println("Door " + door.getId() + " was " + (newState ? "open." : "closed."));
    }

    private boolean isDoorEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        return  (sensorEvent.getType().equals(DOOR_CLOSED) || sensorEvent.getType().equals(DOOR_OPEN));
    }
}
