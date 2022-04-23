package me.pati.arena;

import java.lang.Math;
import java.sql.Array;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
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
        public Double getRandomElementDouble(List<Double> list)
        {
            Random r = new Random();
            int randomitem = r.nextInt(list.size());
            return list.get(randomitem);
        }
        public String getRandomElementString(List<String> list)
        {
            Random r = new Random();
            int randomitem = r.nextInt(list.size());
            return list.get(randomitem);
        }
        public EntityType getRandomElementEntityType(List<EntityType> list)
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
                String com = "minecraft:title @a title {\"text\":\"Arena Minigame was started!\",\"color\":\"green\"}";
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), com);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> Wave(), 20*10);
            } else {
                ArenaMode = false;
                String com = "minecraft:title @a title {\"text\":\"Arena Minigame was stopped!\",\"color\":\"green\"}";
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), com);
            }
            return false;
        }
        public void spawnMobNew(PlayerInteractEvent e) {
            List<Double> XSpawns = new ArrayList<>();
            XSpawns.add(840.5);
            XSpawns.add(876.5);
            List<Double> ZSpawns = new ArrayList<>();
            ZSpawns.add(-586.5);
            ZSpawns.add(-602.5);
            List<EntityType> EntityTypes = new ArrayList<>();
            EntityTypes.add(EntityType.SKELETON);
            EntityTypes.add(EntityType.ZOMBIE);
            EntityTypes.add(EntityType.CREEPER);
            // Take a random entry from both lists and combine them with Y 64 to make a Summon Command with random Entry from Mobs array
            CommandArena obj = new CommandArena();
            double randomX = obj.getRandomElementDouble(XSpawns);
            double randomZ = obj.getRandomElementDouble(ZSpawns);
            EntityType randomEnemy = obj.getRandomElementEntityType(EntityTypes);
            World world = Bukkit.getWorld("world");
            Location l = new Location(world, randomX, 64, randomZ);
            e.getPlayer().getWorld().spawnEntity(l, randomEnemy);
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
            //System.out.println(randomX);
            //System.out.println(randomZ);
            //System.out.println(randomEnemy);
            String spawnCommand = "minecraft:summon "+ randomEnemy +" "+ randomX +" 64 "+randomZ;
            //System.out.println(spawnCommand);
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), spawnCommand);
        }
        public void spawnWave() {
            int minMobs = (int)(10 * (0.2 * round));
            int maxMobs = (int)(10 * (0.4 * round));
            //System.out.println(minMobs);
            //System.out.println(maxMobs);
            for (int i = 0; i < getRandomNumber(minMobs, maxMobs); i++){
                spawnMob();
            }
        }
        public void nextWave() {
            if(ArenaMode) {
                round++;
                Wave();
            }
        }
        public void Wave() {
            // All 4 Waves
            String command = "minecraft:title @a title {\"text\":\"Wave: "+ round +"\",\"color\":\"dark_red\"}";
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> spawnWave(), 20*5);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> spawnWave(), 20*(15*((long)(1+(.3*round)))));
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> spawnWave(), 20*(25*((long)(1+(.3*round)))));
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> spawnWave(), 20*(35*((long)(1+(.3*round)))));

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> nextWave(), 20*(50*((long)(1+(.3*round)))));
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
