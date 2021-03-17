package ru.sbt.mipt.oop.eventhandlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;
import ru.sbt.mipt.oop.smartelements.Light;
import ru.sbt.mipt.oop.smartelements.Room;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class LightEventHandlerTest {
    @Test
    void testLightOn(){
        LightEventHandler handler = new LightEventHandler();
        Light light1 = new Light("1", true);
        Light light2 = new Light("2", false);
        Room room = new Room(Arrays.asList(light1, light2), Collections.emptyList(), "testRoom");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        SensorEvent lightOn1 = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        SensorEvent lightOn2 = new SensorEvent(SensorEventType.LIGHT_ON, "2");
        handler.handleEvent(smartHome, lightOn1);
        handler.handleEvent(smartHome, lightOn2);
        assert(light1.isOn());
        assert(light2.isOn());
    }

    @Test
    void testLightOff(){
        LightEventHandler handler = new LightEventHandler();
        Light light1 = new Light("1", true);
        Light light2 = new Light("2", false);
        Room room = new Room(Arrays.asList(light1, light2), Collections.emptyList(), "testRoom");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        SensorEvent lightOn1 = new SensorEvent(SensorEventType.LIGHT_OFF, "1");
        SensorEvent lightOn2 = new SensorEvent(SensorEventType.LIGHT_OFF, "2");
        handler.handleEvent(smartHome, lightOn1);
        handler.handleEvent(smartHome, lightOn2);
        assert(!light1.isOn());
        assert(!light2.isOn());
    }

}