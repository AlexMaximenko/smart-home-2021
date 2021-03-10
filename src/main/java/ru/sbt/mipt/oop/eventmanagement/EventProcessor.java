package ru.sbt.mipt.oop.eventmanagement;

import ru.sbt.mipt.oop.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import java.util.Collection;

public class EventProcessor {
    private final Collection<EventHandler> eventHandlers;

    public EventProcessor(Collection<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    public void startProcessingLoop(SmartHome smartHome){
        EventGenerator eventGenerator = new EventGenerator();
        SensorEvent sensorEvent = eventGenerator.getNextSensorEvent();

        while (sensorEvent != null){
            System.out.println("Got event: " + sensorEvent);
            this.processEvent(smartHome, sensorEvent);
            sensorEvent = eventGenerator.getNextSensorEvent();
        }
    }

    private void processEvent(SmartHome smartHome, SensorEvent sensorEvent){
        for (EventHandler eventHandler : this.eventHandlers) {
            eventHandler.executeEvent(smartHome, sensorEvent);
        }
    }
}
