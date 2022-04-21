package me.pati.arena;

import java.lang.Math;
import java.sql.Array;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Arena extends JavaPlugin {
    public static Plugin instance = null;
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public class CommandArena implements CommandExecutor {
        boolean ArenaMode = false;
        int round = 0;
        public int getRandomElement(List<Integer> list)
        {
            Random rand = new Random();
            return list.get(rand.nextInt(list.size()));
        }
        public String getRandomElementString(List<String> list)
        {
            Random r = new Random();
            int randomitem = r.nextInt(list.size());
            return list.get(randomitem);
        }
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (!ArenaMode) {
                ArenaMode = true;
                round = 1;
                System.out.println("Arena Minigame wurde aktiviert");
                Wave();
            } else {
                ArenaMode = false;
                System.out.println("Arena Minigame wurde deaktiviert");
            }
            return false;
        }
        public void spawnMob() {
            List<String> XSpawns = new ArrayList<>();
            XSpawns.add("840.5");
            XSpawns.add("876.5");
            List<String> ZSpawns = new ArrayList<>();
            ZSpawns.add("-586.5");
            ZSpawns.add("-602.5");
            List<String> EnemyTypes = new ArrayList<>();
            EnemyTypes.add("minecraft:skeleton");
            EnemyTypes.add("minecraft:zombie");
            EnemyTypes.add("minecraft:creeper");
            // Take a random entry from both lists and combine them with Y 64 to make a Summon Command with random Entry from Mobs array
            CommandArena obj = new CommandArena();
            String randomX = obj.getRandomElementString(XSpawns);
            String randomZ = obj.getRandomElementString(ZSpawns);
            String randomEnemy = obj.getRandomElementString(EnemyTypes);
            String spawnCommand = "minecraft:summon "+ randomEnemy +" "+ randomX +" 64 "+randomZ;
        }
        public void spawnWave() {
            int maxMobs = ;
            for (int i = 0; i < getRandomNumber(); i++){

            }
        }
        public void Wave() {
            // All 4 Waves
            String command = "minecraft:title @a title {\"text\":\"Wave: "+ round +"\",\"color\":\"dark_red\"}";
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> spawnWave(), 20*10);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> spawnWave(), 20*30);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> spawnWave(), 20*50);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> spawnWave(), 20*70);

            if(ArenaMode) {

                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> Wave(), 20*110);
                round++;
            }
        }
    }

    @Override
    public void onEnable() {
        instance = this;
        System.out.println("Minigame Plugin aktiviert");
        this.getCommand("arena").setExecutor(new CommandArena());
    }

    @Override
    public void onDisable() {
        instance = null;
        System.out.println("Minigame Plugin deaktiviert");
    }
}
