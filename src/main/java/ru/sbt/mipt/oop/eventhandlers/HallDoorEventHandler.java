package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;
import ru.sbt.mipt.oop.smartelements.Door;
import ru.sbt.mipt.oop.smartelements.Light;
import ru.sbt.mipt.oop.smartelements.Room;

public class HallDoorEventHandler implements EventHandler {

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent sensorEvent){
        if (!isValidEvent(smartHome, sensorEvent)) return;

        smartHome.execute((object) -> {
            if (object instanceof Light){
                turnOffLight((Light) object);
            }
        });
    }

    private void turnOffLight(Light light){
        light.setOn(false);
        System.out.println("Light " + light.getId() + " was turned off because of closing hall door.");
    }

    private boolean isValidEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        if (!(sensorEvent.getType() == SensorEventType.DOOR_CLOSED)) {
            return false;
        }
        IsHallDoorAction action = new IsHallDoorAction(sensorEvent.getObjectId());
        smartHome.execute(action);
        return action.isHallDoor;
    }

    private class  IsHallDoorAction implements Action {
        private boolean isHallDoor = false;
        private final String id;
        private String roomName = "";

        private IsHallDoorAction(String id) {
            this.id = id;
        }

        @Override
        public void execute(Object object) {
            if (isHallDoor) {
                return;
            }
            if (object instanceof Room){
                roomName = ((Room) object).getName();
            }
            if (object instanceof Door){
                Door door = (Door) object;
                if (roomName.equals("hall") && door.getId().equals(id)){
                    isHallDoor = true;
                }
            }
        }
    }
}
