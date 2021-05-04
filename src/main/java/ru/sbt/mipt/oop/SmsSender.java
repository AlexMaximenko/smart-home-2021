package ru.sbt.mipt.oop;

public class SmsSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }
}
