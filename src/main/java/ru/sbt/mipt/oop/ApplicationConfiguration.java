package ru.sbt.mipt.oop;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapters.EventHandlerAdapter;
import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.handlers.*;
import ru.sbt.mipt.oop.events.handlers.decorators.AlarmSystemDecorator;
import ru.sbt.mipt.oop.homereader.HomeJsonDataReader;
import ru.sbt.mipt.oop.smartelements.SmartHome;

import java.util.*;

@Configuration
public class ApplicationConfiguration {

    @Bean
    SensorEventsManager sensorEventsManager(Collection<com.coolcompany.smarthome.events.EventHandler> eventHandlers){
        SensorEventsManager eventsManager = new SensorEventsManager();
        for (com.coolcompany.smarthome.events.EventHandler eventHandler : eventHandlers) {
            eventsManager.registerEventHandler(eventHandler);
        }
        return eventsManager;
    }

    @Bean
    SmartHome smartHome(){
        return new HomeJsonDataReader("smart-home-1.js").readHomeData();
    }

    @Bean
    SmsSender smsSender(){
        return new SmsSender();
    }

    @Bean
    AlarmSystem alarmSystem(){
        return new AlarmSystem(smsSender());
    }

    @Bean
    Map<String, EventType> ccEventTypeToEventTypeMap(){
        HashMap<String, EventType> ccEventTypeToEventTypeMap = new HashMap<String, EventType>();
        ccEventTypeToEventTypeMap.put("LightIsOn", EventType.LIGHT_ON);
        ccEventTypeToEventTypeMap.put("LightIsOff", EventType.LIGHT_OFF);
        ccEventTypeToEventTypeMap.put("DoorIsOpen", EventType.DOOR_OPEN);
        ccEventTypeToEventTypeMap.put("DoorIsClosed", EventType.DOOR_CLOSED);
        ccEventTypeToEventTypeMap.put("DoorIsLocked", EventType.DOOR_LOCKED);
        ccEventTypeToEventTypeMap.put("DoorIsUnlocked", EventType.DOOR_UNLOCKED);
        return ccEventTypeToEventTypeMap;
    }

    @Bean
    com.coolcompany.smarthome.events.EventHandler lightEventHandler(){
        return new EventHandlerAdapter(new AlarmSystemDecorator(new LightEventHandler(smartHome()), alarmSystem(), smsSender()), ccEventTypeToEventTypeMap());
    }

    @Bean
    com.coolcompany.smarthome.events.EventHandler hallDoorEventHandler(){
        return new EventHandlerAdapter(new AlarmSystemDecorator(new HallDoorEventHandler(smartHome()), alarmSystem(), smsSender()), ccEventTypeToEventTypeMap());
    }

    @Bean
    com.coolcompany.smarthome.events.EventHandler doorEventHandler(){
        return new EventHandlerAdapter(new AlarmSystemDecorator(new DoorEventHandler(smartHome()), alarmSystem(), smsSender()), ccEventTypeToEventTypeMap());
    }


    @Bean
    com.coolcompany.smarthome.events.EventHandler alarmEventHandler(){
        return new EventHandlerAdapter(new AlarmEventHandler(alarmSystem()), ccEventTypeToEventTypeMap());
    }
}
