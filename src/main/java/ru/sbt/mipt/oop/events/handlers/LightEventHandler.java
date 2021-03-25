package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.smartelements.Light;
import ru.sbt.mipt.oop.smartelements.SmartHome;

public class LightEventHandler implements EventHandler{
    private final SmartHome smartHome;

    public LightEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(Event event) {
        if (!isLightEvent(event)) return;
        boolean newState = event.getType() == SensorEventType.LIGHT_ON;
        smartHome.execute((object) -> {
            if (!(object instanceof Light)) return;
            if (((SensorEvent)event).getObjectId().equals(((Light) object).getId())) {
                    updateLightState((Light) object, newState);
            }
        });
    }

    private void updateLightState(Light light, boolean newState) {
        light.setOn(newState);
        System.out.println("Light " + light.getId() + " was turned " + (newState ? "on." : "off."));
    }

    private boolean isLightEvent(Event event) {
        return (event.getType().equals(SensorEventType.LIGHT_ON) || event.getType().equals(SensorEventType.LIGHT_OFF));
    }
}
