package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.events.handlers.EventHandler;
import ru.sbt.mipt.oop.events.handlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.events.handlers.LightEventHandler;
import ru.sbt.mipt.oop.events.management.RandomSensorEventGenerator;
import ru.sbt.mipt.oop.events.management.SensorEventProcessor;
import ru.sbt.mipt.oop.events.management.SensorEventProcessorImpl;
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

        // начинаем цикл обработки событий
        Collection<EventHandler> eventHandlers = Arrays.asList(
                new LightEventHandler(smartHome),
                new DoorEventHandler(smartHome),
                new HallDoorEventHandler(smartHome));

        SensorEventProcessor eventProcessor = new SensorEventProcessorImpl(eventHandlers, new RandomSensorEventGenerator());
        eventProcessor.startProcessingLoop(smartHome);
    }
}
