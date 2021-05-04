package ru.sbt.mipt.oop.remotecontrol.commands;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import rc.RemoteControl;
import ru.sbt.mipt.oop.ApplicationConfiguration;
import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.remotecontrol.RemoteControllerImpl;
import ru.sbt.mipt.oop.smartelements.Light;
import ru.sbt.mipt.oop.smartelements.Room;
import ru.sbt.mipt.oop.smartelements.SmartHome;

class TurnRoomLightsOnCommandTest {

    @Test
    void testExecuting() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        SmartHome smartHome = context.getBean(SmartHome.class);
        AlarmSystem alarmSystem = context.getBean(AlarmSystem.class);
        RemoteControl remoteControl = context.getBean(RemoteControllerImpl.class);
        remoteControl.onButtonPressed("2");
        smartHome.execute(object -> {
            if (object instanceof Room && ((Room) object).getName().equals("hall")){
                ((Room) object).execute(light -> {
                    if (light instanceof Light){
                        assert (((Light) light).isOn());
                    }
                });
            }
        });
    }
}