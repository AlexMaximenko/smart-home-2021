package ru.sbt.mipt.oop.events.management;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.handlers.EventHandler;
import ru.sbt.mipt.oop.smartelements.SmartHome;

import java.util.Collection;

public class SensorEventProcessorImpl implements  SensorEventProcessor {
    private final Collection<EventHandler> eventHandlers;
    private final SensorEventGenerator sensorEventGenerator;

    public SensorEventProcessorImpl(Collection<EventHandler> eventHandlers, SensorEventGenerator sensorEventGenerator) {
        this.eventHandlers = eventHandlers;
        this.sensorEventGenerator = sensorEventGenerator;
    }

    public void startProcessingLoop(SmartHome smartHome){
        SensorEvent sensorEvent = sensorEventGenerator.getNextSensorEvent();

        while (sensorEvent != null){
            System.out.println("Got event: " + sensorEvent);
            this.processEvent(smartHome, sensorEvent);
            sensorEvent = sensorEventGenerator.getNextSensorEvent();
        }
    }

    private void processEvent(SmartHome smartHome, SensorEvent sensorEvent){
        for (EventHandler eventHandler : this.eventHandlers) {
            eventHandler.handleEvent(smartHome, sensorEvent);
        }
    }
}
