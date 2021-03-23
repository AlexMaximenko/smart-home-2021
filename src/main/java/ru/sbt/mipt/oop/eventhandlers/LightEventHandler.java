package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.home.HomeUtils;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEventType;
import ru.sbt.mipt.oop.smartelements.Light;

public class LightEventHandler implements EventHandler{
    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        if (!isLightEvent(sensorEvent)) return;
        boolean newState = sensorEvent.getType() == SensorEventType.LIGHT_ON;
        smartHome.execute((object) -> {
            if (!(object instanceof Light)) return;
            if (sensorEvent.getObjectId().equals(((Light) object).getId())) {
                    updateLightState((Light) object, newState);
            }
        });
    }

    private void updateLightState(Light light, boolean newState) {
        light.setOn(newState);
        System.out.println("Light " + light.getId() + " was turned " + (newState ? "on." : "off."));
    }

    private boolean isLightEvent(SensorEvent sensorEvent) {
        return (sensorEvent.getType().equals(SensorEventType.LIGHT_ON) || sensorEvent.getType().equals(SensorEventType.LIGHT_OFF));
    }
}
