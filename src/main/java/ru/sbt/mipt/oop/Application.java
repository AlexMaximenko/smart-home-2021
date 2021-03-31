package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventhandlers.DoorEventHandler;
import ru.sbt.mipt.oop.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.eventhandlers.LightEventHandler;
import ru.sbt.mipt.oop.eventhandlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.eventmanagement.RandomSensorEventGenerator;
import ru.sbt.mipt.oop.eventmanagement.SensorEventProcessor;
import ru.sbt.mipt.oop.eventmanagement.SensorEventProcessorImpl;
import ru.sbt.mipt.oop.home.HomeDataReader;
import ru.sbt.mipt.oop.home.HomeJsonDataReader;
import ru.sbt.mipt.oop.home.SmartHome;

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
                new LightEventHandler(),
                new DoorEventHandler(),
                new HallDoorEventHandler());

        SensorEventProcessor eventProcessor = new SensorEventProcessorImpl(eventHandlers, new RandomSensorEventGenerator());
        eventProcessor.startProcessingLoop(smartHome);
    }
}
