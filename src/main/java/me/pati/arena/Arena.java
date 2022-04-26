package me.pati.arena;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Arena extends JavaPlugin {
    public static Plugin instance = null;
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public class CommandArena implements CommandExecutor {
        List<Player> players = new ArrayList<Player>();
        boolean ArenaMode = false;
        int round = 0;
        int entityCount = 0;
        public Double getRandomElementDouble(List<Double> list)
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
        public Player getRandomElementPlayer(List<Player> list)
        {
            Random r = new Random();
            int randomitem = r.nextInt(list.size());
            return list.get(randomitem);
        }
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (args.length == 0) {
                if (!ArenaMode) {
                    ArenaMode = true;
                    entityCount = 0;
                    round = 1;
                    String com = "minecraft:title @a title {\"text\":\"Arena Minigame was started!\",\"color\":\"green\"}";
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), com);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> Wave(), 20*10);
                } else {
                    ArenaMode = false;
                    String com = "minecraft:title @a title {\"text\":\"Arena Minigame was stopped!\",\"color\":\"green\"}";
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), com);
                    players.clear();
                }
            } else if (Bukkit.getServer().getPlayer(args[0]) != null) { // If name is player, then register him to the arena
                if (players.contains(Bukkit.getServer().getPlayer(args[0]))) {
                    unRegisterArenaPlayer(Bukkit.getServer().getPlayer(args[0]));
                } else {
                    RegisterArenaPlayer(Bukkit.getServer().getPlayer(args[0]));
                }
            }
            return false;
        }
        public void RegisterArenaPlayer(Player player) {
            players.add(player);
        }
        public void unRegisterArenaPlayer(Player player) {
            players.remove(player);
        }
        public void targetRandomPlayer(Creature creature) {
            Player player = getRandomElementPlayer(players);
            creature.setTarget(player);
        }
        public void spawnMob() {
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

            CommandArena obj = new CommandArena();
            double randomX = obj.getRandomElementDouble(XSpawns);
            double randomZ = obj.getRandomElementDouble(ZSpawns);
            EntityType randomEnemy = obj.getRandomElementEntityType(EntityTypes);

            World world = Bukkit.getWorld("world");
            Location l = new Location(world, randomX, 64, randomZ);
            Creature entity = (Creature) world.spawnEntity(l, randomEnemy);
            targetRandomPlayer(entity); // Moves to random player
            //entityCount++; // TODO: Count entity deaths to spawn next wave. Listen to onDeath events or smth and check for entity spawned by the Arena and then decrease count.
        }
        public void spawnWave() {
            int minMobs = (int)(10 * (0.2 * round));
            int maxMobs = (int)(10 * (0.4 * round));
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
