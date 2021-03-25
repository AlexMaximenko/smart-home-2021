package ru.sbt.mipt.oop.events.handlers.decorators;

import ru.sbt.mipt.oop.alarm.AlarmSystem;
import ru.sbt.mipt.oop.alarm.alarmstates.ActivatedState;
import ru.sbt.mipt.oop.alarm.alarmstates.EmergencyState;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.handlers.EventHandler;

public class AlarmSystemDecorator extends EventHandlerDecorator{
    private final AlarmSystem alarmSystem;

    public AlarmSystemDecorator(EventHandler eventHandler, AlarmSystem alarm) {
        super(eventHandler);
        this.alarmSystem = alarm;
    }

    @Override
    public void handleEvent(Event event) {
        if (alarmSystem.getState() instanceof ActivatedState){
            alarmSystem.raiseAlarm();
        }
        if (alarmSystem.getState() instanceof EmergencyState){
            System.out.println("Включен режим тревоги, события не обрабатываются, отправлено смс.");
        }
        else{
            eventHandler.handleEvent(event);
        }
    }
}
