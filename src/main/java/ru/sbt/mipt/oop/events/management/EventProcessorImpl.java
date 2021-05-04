package ru.sbt.mipt.oop.events.management;

import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.handlers.EventHandler;
import ru.sbt.mipt.oop.smartelements.SmartHome;

import java.util.Collection;

public class EventProcessorImpl implements EventProcessor {
    private final Collection<EventHandler> eventHandlers;
    private final EventGenerator eventGenerator;

    public EventProcessorImpl(Collection<EventHandler> eventHandlers, EventGenerator eventGenerator) {
        this.eventHandlers = eventHandlers;
        this.eventGenerator = eventGenerator;
    }

    public void startProcessingLoop(SmartHome smartHome){
        Event event = eventGenerator.getNextEvent();

        while (event != null){
            System.out.println("Got event: " + event);
            this.processEvent(smartHome, event);
            event = eventGenerator.getNextEvent();
        }
    }

    private void processEvent(SmartHome smartHome, Event event){
        for (EventHandler eventHandler : this.eventHandlers) {
            eventHandler.handleEvent(event);
        }
    }
}
