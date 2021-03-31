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
        System.out.println("Включен режим тревоги, отправлено смс.");
        this.state = new EmergencyState(this);
    }

    void setState(AlarmSystemState state) {
        this.state = state;
    }

    public boolean isActivated(){
        if (this.state instanceof ActivatedState) { return true;}
        return false;
    }

    public boolean isEmergency(){
        if (this.state instanceof EmergencyState) { return true;}
        return false;
    }

    public boolean isDeactivated(){
        if (this.state instanceof DeactivatedState) { return true;}
        return false;
    }
}
