package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.MessageSender;

public class AlarmSystem {
    private AlarmSystemState state;
    private final MessageSender sender;

    public AlarmSystem(MessageSender sender) {
        this.sender = sender;
        this.state = new DeactivatedState(this);
    }

    void sendMessage(String message){
        sender.sendMessage(message);
    }

    public void activate(String code){
        state.activate(code);
    }

    public void deactivate(String code){
        state.deactivate(code);
    }

    public void raiseAlarm(){
        sendMessage("Включен режим тревоги, отправлено смс.");
        this.state = new EmergencyState(this);
    }

    void setState(AlarmSystemState state) {
        this.state = state;
    }

    public boolean isActivated(){
        return state instanceof ActivatedState;
    }

    public boolean isEmergency(){
        return state instanceof EmergencyState;
    }

    public boolean isDeactivated(){
        return state instanceof DeactivatedState;
    }
}
