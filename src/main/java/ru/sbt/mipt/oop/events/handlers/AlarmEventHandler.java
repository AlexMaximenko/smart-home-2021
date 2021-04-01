package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.Event;

public class AlarmEventHandler implements EventHandler{
    private final AlarmSystem alarmSystem;

    public AlarmEventHandler(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void handleEvent(Event event) {
        if (!(isValidEvent(event))) return;
        if (event.getType().equals(EventType.ALARM_ACTIVATE)){
            this.alarmSystem.activate(((AlarmEvent) event).getCode());
        }
        else {
            this.alarmSystem.deactivate(((AlarmEvent) event).getCode());
        }
    }

    private boolean isValidEvent(Event event){
        return event instanceof AlarmEvent;
    }
}
