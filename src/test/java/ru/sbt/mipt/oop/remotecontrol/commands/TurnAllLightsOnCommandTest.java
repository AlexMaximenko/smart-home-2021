package ru.sbt.mipt.oop.remotecontrol.commands;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import rc.RemoteControl;
import ru.sbt.mipt.oop.ApplicationConfiguration;
import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.remotecontrol.RemoteControllerImpl;
import ru.sbt.mipt.oop.smartelements.Light;
import ru.sbt.mipt.oop.smartelements.SmartHome;

class TurnAllLightsOnCommandTest {

    @Test
    void testExecuting() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        SmartHome smartHome = context.getBean(SmartHome.class);
        AlarmSystem alarmSystem = context.getBean(AlarmSystem.class);
        RemoteControl remoteControl = new RemoteControllerImpl(smartHome, alarmSystem, "Id1");
        remoteControl.onButtonPressed("1");
        smartHome.execute(object -> {
            if (object instanceof Light){
                assert (((Light) object).isOn());
            }
        });
    }
}