package ru.sbt.mipt.oop;

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

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = new SmartHome();
        HomeDataReader reader = new HomeJsonDataReader("smart-home-1.js");
        smartHome = reader.readHomeData();
        AlarmSystem alarmSystem = new AlarmSystem(new SmsSender());
        MessageSender sender = new SmsSender();
        EventHandler lightEventHandler = new AlarmSystemDecorator(new LightEventHandler(smartHome), alarmSystem, sender);
        EventHandler doorEventHandler = new AlarmSystemDecorator(new DoorEventHandler(smartHome), alarmSystem, sender);
        EventHandler hallEventHandler = new AlarmSystemDecorator(new HallDoorEventHandler(smartHome), alarmSystem, sender);
        EventHandler alarmEventHandler = new AlarmEventHandler(alarmSystem);
        // начинаем цикл обработки событий
        Collection<EventHandler> eventHandlers = Arrays.asList(
                lightEventHandler, doorEventHandler, hallEventHandler, alarmEventHandler);

        EventProcessor eventProcessor = new EventProcessorImpl(eventHandlers, new RandomEventGenerator());
        eventProcessor.startProcessingLoop(smartHome);
    }
}
