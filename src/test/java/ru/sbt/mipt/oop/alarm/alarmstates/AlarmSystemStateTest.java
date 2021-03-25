package ru.sbt.mipt.oop.alarm.alarmstates;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.smartelements.SmartHome;

class AlarmSystemStateTest {
    @Test
    void testActivation(){
        SmartHome smartHome = new SmartHome();
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.activate("java");
        assert(alarmSystem.getState() instanceof ActivatedState);
    }

    @Test
    void testCorrectDeactivation(){
        SmartHome smartHome = new SmartHome();
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.activate("java");
        alarmSystem.deactivate("java");
        assert(alarmSystem.getState() instanceof DeactivatedState);
    }

    @Test
    void testIncorrectDeactivation(){
        SmartHome smartHome = new SmartHome();
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.activate("java");
        alarmSystem.deactivate("not_java");
        assert(alarmSystem.getState() instanceof EmergencyState);
    }

    @Test
    void testEmergencyState(){
        SmartHome smartHome = new SmartHome();
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.raiseAlarm();
        assert(alarmSystem.getState() instanceof EmergencyState);

        try{
            alarmSystem.deactivate("java");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        assert(alarmSystem.getState() instanceof EmergencyState);


        try{
            alarmSystem.activate("java");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        assert(alarmSystem.getState() instanceof EmergencyState);
    }

}