package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.handlers.*;
import ru.sbt.mipt.oop.events.handlers.decorators.AlarmSystemDecorator;
import ru.sbt.mipt.oop.events.management.RandomEventGenerator;
import ru.sbt.mipt.oop.events.management.EventProcessor;
import ru.sbt.mipt.oop.events.management.EventProcessorImpl;
import ru.sbt.mipt.oop.homereader.HomeDataReader;
import ru.sbt.mipt.oop.homereader.HomeJsonDataReader;
import ru.sbt.mipt.oop.smartelements.SmartHome;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class Application {

    public static void main(String... args){
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        SensorEventsManager sensorEventsManager = context.getBean(SensorEventsManager.class);
        sensorEventsManager.start();
    }
}
