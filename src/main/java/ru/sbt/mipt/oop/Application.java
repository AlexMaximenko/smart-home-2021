package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventhandlers.CommonDoorEventHandler;
import ru.sbt.mipt.oop.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.eventhandlers.LightEventHandler;
import ru.sbt.mipt.oop.eventhandlers.specialhandlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.eventmanagement.EventProcessor;
import ru.sbt.mipt.oop.home.HomeJsonDataReader;
import ru.sbt.mipt.oop.home.HomeJsonDataWriter;
import ru.sbt.mipt.oop.home.SmartHome;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = new SmartHome(new HomeJsonDataWriter(), new HomeJsonDataReader());
        smartHome = smartHome.readFromFile("smart-home-1.js");
        // начинаем цикл обработки событий
        Collection<EventHandler> eventHandlers = Arrays.asList(
                new LightEventHandler(),
                new CommonDoorEventHandler(Arrays.asList(new HallDoorEventHandler())));
        EventProcessor eventProcessor = new EventProcessor(eventHandlers);
        eventProcessor.startProcessingLoop(smartHome);
    }
}
