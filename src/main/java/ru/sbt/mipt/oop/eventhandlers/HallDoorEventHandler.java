package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.home.HomeUtils;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.CommandType;
import ru.sbt.mipt.oop.sensor.SensorCommand;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;
import ru.sbt.mipt.oop.smartelements.Door;
import ru.sbt.mipt.oop.smartelements.Light;
import ru.sbt.mipt.oop.smartelements.Room;

public class HallDoorEventHandler implements EventHandler {


    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent sensorEvent){
        if (!isHallDoorEvent(smartHome, sensorEvent)) return;
        if (sensorEvent.getType() == SensorEventType.DOOR_CLOSED) {
            smartHome.execute(new Action() {
                @Override
                public void execute(Object object) {
                    if (!(object instanceof Light)) return;
                    turnOffLight((Light) object);
                }
            });
        }
    }

    private void turnOffLight(Light light){
        light.setOn(false);
        System.out.println("Light " + light.getId() + " was turned off because of closing hall door.");
    }

    private boolean isHallDoorEvent(SmartHome smartHome, SensorEvent sensorEvent){
        if (!(sensorEvent.getType() == SensorEventType.DOOR_CLOSED || sensorEvent.getType() == SensorEventType.DOOR_OPEN))
        {
            return false;
        }
        Room eventRoom = HomeUtils.findRoomOfDoor(smartHome, sensorEvent.getObjectId());
        if (eventRoom == null) return false;
        if (eventRoom.getName().equals("hall")) return true;
        return false;
    }
}
