package ru.sbt.mipt.oop.events.management;

import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.EventType;

public class RandomEventGenerator implements EventGenerator {
    public Event getNextEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        if (Math.random() < 0.75) {
            return generateSensorEvent();
        }
        else {
            return generateAlarmEvent();
        }
    }

    private AlarmEvent generateAlarmEvent(){
        EventType eventType = EventType.values()[(int) (4 + 2 * Math.random())];
        if (Math.random() < 0.75) {
            return new AlarmEvent(eventType, "java");
        }
        else {
            return new AlarmEvent(eventType, "not_java");
        }

    }

    private SensorEvent generateSensorEvent(){
        EventType eventType = EventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(eventType, objectId);
    }

    private boolean isAlarmEventType(EventType eventType){
        if (eventType.equals(EventType.ALARM_ACTIVATE) || eventType.equals(EventType.ALARM_DEACTIVATE)){
            return true;
        }
        return false;
    }
}
