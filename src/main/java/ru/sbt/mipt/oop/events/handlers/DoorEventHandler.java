package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.smartelements.Door;
import ru.sbt.mipt.oop.smartelements.SmartHome;

import static ru.sbt.mipt.oop.events.EventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.EventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler {
    private final SmartHome smartHome;

    public DoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(Event event){
        if (!isDoorEvent(smartHome, event)) return;
        boolean newState = event.getType() == DOOR_OPEN;

        smartHome.execute((object) -> {
            if (!(object instanceof Door)) return;
            if (((SensorEvent)event).getObjectId().equals(((Door) object).getId())) {
                    updateDoorState( (Door) object, newState);
            }
        });
    }

    private void updateDoorState(Door door, boolean newState){
        door.setOpen(newState);
        System.out.println("Door " + door.getId() + " was " + (newState ? "open." : "closed."));
    }

    private boolean isDoorEvent(SmartHome smartHome, Event event) {
        return  (event.getType().equals(DOOR_CLOSED) || event.getType().equals(DOOR_OPEN));
    }
}
