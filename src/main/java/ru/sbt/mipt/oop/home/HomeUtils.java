package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.smartelements.Door;
import ru.sbt.mipt.oop.smartelements.Light;
import ru.sbt.mipt.oop.smartelements.Room;

public class HomeUtils {
    public static Light findLight(SmartHome smartHome, String lightId){
        Light targetLight = null;
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(lightId)) {
                    targetLight = light;
                    break;
                }
            }
        }
        return targetLight;
    }

    public static Room findRoomOfDoor(SmartHome smartHome, String doorId){
        Room targetRoom = null;
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(doorId)) {
                    targetRoom = room;
                    break;
                }
            }
        }
        return targetRoom;
    }
}
