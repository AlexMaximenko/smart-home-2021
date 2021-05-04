package ru.sbt.mipt.oop.remotecontrol.commands;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import rc.RemoteControl;
import ru.sbt.mipt.oop.ApplicationConfiguration;
import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.remotecontrol.RemoteControllerImpl;
import ru.sbt.mipt.oop.smartelements.SmartHome;

class AlarmEmergencyCommandTest {

    @Test
    void testExecuting() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        SmartHome smartHome = context.getBean(SmartHome.class);
        AlarmSystem alarmSystem = context.getBean(AlarmSystem.class);
        RemoteControl remoteControl = context.getBean(RemoteControllerImpl.class);
        remoteControl.onButtonPressed("B");
        assert (alarmSystem.isEmergency());
    }
}