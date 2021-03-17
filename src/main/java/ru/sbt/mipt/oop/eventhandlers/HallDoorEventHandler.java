package ru.sbt.mipt.oop.eventhandlers;

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
    public void handleEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        if (isHallDoorEvent(smartHome, sensorEvent)){
            if (sensorEvent.getType().equals(SensorEventType.DOOR_CLOSED)) {
                this.turnOffLights(smartHome);
            }
        }
    }

    private void turnOffLights(SmartHome smartHome){
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                System.out.println("Pretend we're sending command " + command);
            }
        }
    }

    private boolean isHallDoorEvent(SmartHome smartHome, SensorEvent sensorEvent){
        for (Room room : smartHome.getRooms()){
            for (Door door : room.getDoors()){
                if (door.getId().equals(sensorEvent.getObjectId())) {
                    if (room.getName().equals("hall"))
                        return true;
                    else
                        return false;
                }
            }
        }
        return false;
    }
}
