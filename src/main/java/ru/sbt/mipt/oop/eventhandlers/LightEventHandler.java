package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEventType;
import ru.sbt.mipt.oop.smartelements.Light;
import ru.sbt.mipt.oop.smartelements.Room;

public class LightEventHandler implements EventHandler{
    @Override
    public void executeEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        if (isLightEvent(sensorEvent))
            this.changeLightState(smartHome, sensorEvent);
    }

    private void changeLightState(SmartHome smartHome, SensorEvent sensorEvent){
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(sensorEvent.getObjectId())) {
                    if (sensorEvent.getType().equals(SensorEventType.LIGHT_ON)) {
                        light.setOn(true);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                    }
                    else {
                        light.setOn(false);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                    }
                }
            }
        }
    }

    private boolean isLightEvent(SensorEvent sensorEvent) {
        return (sensorEvent.getType().equals(SensorEventType.LIGHT_ON) || sensorEvent.getType().equals(SensorEventType.LIGHT_OFF));
    }
}
