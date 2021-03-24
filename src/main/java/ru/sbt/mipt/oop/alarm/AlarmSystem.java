package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.alarm.alarmstates.AlarmSystemState;
import ru.sbt.mipt.oop.alarm.alarmstates.DeactivatedState;
import ru.sbt.mipt.oop.alarm.alarmstates.EmergencyState;
import ru.sbt.mipt.oop.smartelements.SmartHome;

public class AlarmSystem {
    private final SmartHome smartHome;
    private AlarmSystemState state;
    private String code;

    public AlarmSystem(SmartHome smartHome) {
        this.smartHome = smartHome;
        this.state = new DeactivatedState(this);
        this.code = null;
    }

    public void activate(String code){
        state.activate(code);
    }

    public void deactivate(String code){
        state.deactivate(code);
    }

    public void raiseAlarm(){
        System.out.println("Включен режим тревоги, отправлено смс.");
        this.state = new EmergencyState(this);
    }

    public void setState(AlarmSystemState state) {
        this.state = state;
    }

    public AlarmSystemState getState() {
        return state;
    }
}
