package ru.sbt.mipt.oop.events.management;

import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.EventType;

public class RandomEventGenerator implements EventGenerator {
    public Event getNextEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        EventType eventType = EventType.values()[(int) (6 * Math.random())];
        if (!(isAlarmEventType(eventType))) {
            String objectId = "" + ((int) (10 * Math.random()));
            return new SensorEvent(eventType, objectId);
        }
        else {
            String code;
            if (Math.random() > 0.5){
                code = "java";
            }
            else{
                code = "not_java";
            }
            return new AlarmEvent(eventType, code);
        }
    }

    private boolean isAlarmEventType(EventType eventType){
        if (eventType.equals(EventType.ALARM_ACTIVATE) || eventType.equals(EventType.ALARM_DEACTIVATE)){
            return true;
        }
        return false;
    }
}
