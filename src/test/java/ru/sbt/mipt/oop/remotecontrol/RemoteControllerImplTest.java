package ru.sbt.mipt.oop.remotecontrol;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import rc.RemoteControl;
import ru.sbt.mipt.oop.ApplicationConfiguration;
import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.remotecontrol.commands.*;
import ru.sbt.mipt.oop.smartelements.SmartHome;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RemoteControllerImplTest {
    @Test
    void testSetButton(){
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        RemoteControllerImpl remoteControl = context.getBean(RemoteControllerImpl.class);
        AlarmSystem alarmSystem = context.getBean(AlarmSystem.class);
        remoteControl.onButtonPressed("A");
        assert(alarmSystem.isActivated());
        remoteControl.setButton("A", new AlarmEmergencyCommand(alarmSystem));
        remoteControl.onButtonPressed("A");
        assert(alarmSystem.isEmergency());
    }

}