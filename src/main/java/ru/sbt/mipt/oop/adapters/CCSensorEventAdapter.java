package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.SensorEvent;

public class CCSensorEventAdapter extends CCSensorEvent implements Event {
    private final SensorEvent sensorEvent;
    /**
     * Default constructor
     *
     * @param eventType - defines type of event.
     * @param objectId  - id of the object which fired the event (door/lightswitch)
     */
    public CCSensorEventAdapter(String eventType, String objectId) {
        super(eventType, objectId);
        this.sensorEvent = new SensorEvent(convertLibraryEventTypeToEventType(eventType), objectId);
    }

    public static EventType convertLibraryEventTypeToEventType(String libraryEventType){
        libraryEventType = libraryEventType.toUpperCase().replace("IS", "_");
        return EventType.valueOf(libraryEventType);
    }

    public static String convertEventTypeToLibraryEventType(EventType eventType){
        int underscoreIndex = eventType.toString().indexOf("_");
        String type = eventType.toString().toLowerCase();
        StringBuilder libraryEventType = new StringBuilder(type.substring(0, 1).toUpperCase());
        libraryEventType.append(type.substring(1, underscoreIndex));
        libraryEventType.append("Is");
        libraryEventType.append(type.substring(underscoreIndex + 1, underscoreIndex + 2).toUpperCase());
        libraryEventType.append(type.substring(underscoreIndex+2));
        return libraryEventType.toString();
    }

    @Override
    public String getEventType(){ return convertEventTypeToLibraryEventType(this.sensorEvent.getType()); }

    @Override
    public String getObjectId(){ return this.sensorEvent.getObjectId(); }

    @Override
    public EventType getType() {
        return this.sensorEvent.getType();
    }
}
