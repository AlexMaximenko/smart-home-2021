package ru.sbt.mipt.oop;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import ru.sbt.mipt.oop.adapters.EventHandlerAdapter;
import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.handlers.*;
import ru.sbt.mipt.oop.events.handlers.decorators.AlarmSystemDecorator;
import ru.sbt.mipt.oop.homereader.HomeJsonDataReader;
import ru.sbt.mipt.oop.remotecontrol.RemoteControllerImpl;
import ru.sbt.mipt.oop.remotecontrol.commands.*;
import ru.sbt.mipt.oop.smartelements.SmartHome;

import java.util.HashMap;
import java.util.Map;

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
        return new AlarmSystem(smsSender(), "defaultPassword");
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

    @Bean
    RemoteControl remoteController() {
        Map<String, Command> commands = new HashMap<String, Command>();
        commands.put("A", new AlarmActivateCommand(this.alarmSystem(), this.alarmSystem().getDefaultPassword()));
        commands.put("B", new AlarmEmergencyCommand(this.alarmSystem()));
        commands.put("C", new CloseHallDoorCommand(this.smartHome()));
        commands.put("D", new TurnAllLightsOffCommand(this.smartHome()));
        commands.put("1", new TurnAllLightsOnCommand(this.smartHome()));
        commands.put("2", new TurnRoomLightsOnCommand("hall", this.smartHome()));
        return new RemoteControllerImpl(smartHome(), alarmSystem(), "1", commands);
    }
}
