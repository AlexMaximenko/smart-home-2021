package ru.sbt.mipt.oop.alarm.alarmstates;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmsSender;
import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.smartelements.SmartHome;

class AlarmSystemStateTest {
    @Test
    void testActivation(){
        SmartHome smartHome = new SmartHome();
        AlarmSystem alarmSystem = new AlarmSystem(new SmsSender(), "defaultPassword");
        alarmSystem.activate("java");
        assert(alarmSystem.isActivated());
    }

    @Test
    void testCorrectDeactivation(){
        SmartHome smartHome = new SmartHome();
        AlarmSystem alarmSystem = new AlarmSystem(new SmsSender(), "defaultPassword");
        alarmSystem.activate("java");
        alarmSystem.deactivate("java");
        assert(alarmSystem.isDeactivated());
    }

    @Test
    void testIncorrectDeactivation(){
        SmartHome smartHome = new SmartHome();
        AlarmSystem alarmSystem = new AlarmSystem(new SmsSender(), "defaultPassword");
        alarmSystem.activate("java");
        alarmSystem.deactivate("not_java");
        assert(alarmSystem.isEmergency());
    }

    @Test
    void testEmergencyState(){
        SmartHome smartHome = new SmartHome();
        AlarmSystem alarmSystem = new AlarmSystem(new SmsSender(), "defaultPassword");
        alarmSystem.raiseAlarm();
        assert(alarmSystem.isEmergency());

        try{
            alarmSystem.deactivate("java");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        assert(alarmSystem.isEmergency());


        try{
            alarmSystem.activate("java");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        assert(alarmSystem.isEmergency());
    }

}