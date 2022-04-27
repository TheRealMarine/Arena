package me.pati.arena;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;
public class utilities {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static Double getRandomElementDouble(List<Double> list) {
        Random r = new Random();
        int randomitem = r.nextInt(list.size());
        return list.get(randomitem);
    }

    public static EntityType getRandomElementEntityType(List<EntityType> list) {
        Random r = new Random();
        int randomitem = r.nextInt(list.size());
        return list.get(randomitem);
    }

    public static Player getRandomElementPlayer(List<Player> list) {
        if (!list.isEmpty()) {
            Random r = new Random();
            int randomitem = r.nextInt(list.size());
            return list.get(randomitem);
        }
        return null;
    }
}
