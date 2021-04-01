package ru.sbt.mipt.oop.events.handlers.decorators;

import ru.sbt.mipt.oop.MessageSender;
import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.handlers.EventHandler;

public class AlarmSystemDecorator implements EventHandler {
    private final AlarmSystem alarmSystem;
    private final EventHandler eventHandler;
    private final MessageSender sender;

    public AlarmSystemDecorator(EventHandler eventHandler, AlarmSystem alarm, MessageSender sender) {
        this.eventHandler = eventHandler;
        this.alarmSystem = alarm;
        this.sender = sender;
    }

    @Override
    public void handleEvent(Event event) {
        if (alarmSystem.isActivated()){
            alarmSystem.raiseAlarm();
        }
        if (alarmSystem.isEmergency()){
            sender.sendMessage("Включен режим тревоги, события не обрабатываются, отправлено смс.");
        }
        else{
            eventHandler.handleEvent(event);
        }
    }
}
