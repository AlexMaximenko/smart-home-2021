package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.AlarmEventType;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.smartelements.SmartHome;

public class AlarmEventHandler implements EventHandler{
    private final AlarmSystem alarmSystem;

    public AlarmEventHandler(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void handleEvent(SmartHome smartHome, Event event) {
        if (!(isValidEvent(event))) return;
        if (event.getType().equals(AlarmEventType.ALARM_ACTIVATE)){
            this.alarmSystem.activate(((AlarmEvent) event).getCode());
        }
        else {
            this.alarmSystem.deactivate(((AlarmEvent) event).getCode());
        }
    }

    private boolean isValidEvent(Event event){
        if (event instanceof AlarmEvent) return true;
        else return false;
    }
}
