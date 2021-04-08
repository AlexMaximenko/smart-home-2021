package ru.sbt.mipt.oop.remotecontrol;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import rc.RemoteControl;
import ru.sbt.mipt.oop.ApplicationConfiguration;
import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.remotecontrol.commands.AlarmEmergencyCommand;
import ru.sbt.mipt.oop.smartelements.SmartHome;

import static org.junit.jupiter.api.Assertions.*;

class RemoteControllerImplTest {
    @Test
    void testSetButton(){
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        SmartHome smartHome = context.getBean(SmartHome.class);
        AlarmSystem alarmSystem = context.getBean(AlarmSystem.class);
        RemoteControllerImpl remoteControl = new RemoteControllerImpl(smartHome, alarmSystem, "Id1");
        remoteControl.onButtonPressed("A");
        assert(alarmSystem.isActivated());
        remoteControl.setButton("A", new AlarmEmergencyCommand(alarmSystem));
        remoteControl.onButtonPressed("A");
        assert(alarmSystem.isEmergency());
    }

}