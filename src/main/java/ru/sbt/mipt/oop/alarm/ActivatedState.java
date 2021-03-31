package ru.sbt.mipt.oop.alarm;

public class ActivatedState implements AlarmSystemState {
    private final AlarmSystem alarmSystem;
    private final String code;

    public ActivatedState(AlarmSystem alarmSystem, String code) {
        this.alarmSystem = alarmSystem;
        this.code = code;
    }

    @Override
    public void activate(String code) {
        alarmSystem.sendMessage("Попытка активировать уже активированную сигнализацию");
    }

    @Override
    public void deactivate(String code) {
        if (this.code == code) {
            this.alarmSystem.setState(new DeactivatedState(this.alarmSystem));
            alarmSystem.sendMessage("Введен правильный пароль, сигнализация отключена.");
        }
        else {
            this.alarmSystem.setState(new EmergencyState(this.alarmSystem));
            alarmSystem.sendMessage("Введен неправильный пароль, режим тревоги включен.");
        }
    }
}
