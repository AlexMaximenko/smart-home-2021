package ru.sbt.mipt.oop;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapters.EventHandlerAdapter;
import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.handlers.*;
import ru.sbt.mipt.oop.events.handlers.decorators.AlarmSystemDecorator;
import ru.sbt.mipt.oop.homereader.HomeJsonDataReader;
import ru.sbt.mipt.oop.smartelements.SmartHome;

import java.util.ArrayList;

@Configuration
public class ApplicationConfiguration {

    @Bean
    SensorEventsManager sensorEventsManager(){
        SensorEventsManager eventsManager = new SensorEventsManager();
        eventsManager.registerEventHandler(new EventHandlerAdapter(lightEventHandler()));
        eventsManager.registerEventHandler(new EventHandlerAdapter(doorEventHandler()));
        eventsManager.registerEventHandler(new EventHandlerAdapter(hallDoorEventHandler()));
        eventsManager.registerEventHandler(new EventHandlerAdapter(alarmEventHandler()));
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
    EventHandler lightEventHandler(){
        return new AlarmSystemDecorator(new LightEventHandler(smartHome()), alarmSystem(), smsSender());
    }

    @Bean
    EventHandler hallDoorEventHandler(){
        return new AlarmSystemDecorator(new HallDoorEventHandler(smartHome()), alarmSystem(), smsSender());
    }

    @Bean
    EventHandler doorEventHandler(){
        return new AlarmSystemDecorator(new DoorEventHandler(smartHome()), alarmSystem(), smsSender());
    }


    @Bean
    EventHandler alarmEventHandler(){
        return new AlarmEventHandler(alarmSystem());
    }
}
