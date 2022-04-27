package me.pati.arena;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import me.pati.arena.utilities;

public class spawning {
    static public Plugin instance = null;
    static List<Player> players = new ArrayList<>();
    static List<Creature> entities = new ArrayList<>();
    static boolean ArenaMode = false;
    static boolean  readyToSpawn = true;
    static int round = 0;
    static public class CommandArena implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (args.length == 0) {
                if (!ArenaMode) {
                    ArenaMode = true;
                    readyToSpawn = true;
                    round = 1;
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "minecraft:title @a title {\"text\":\"Arena Minigame was started!\",\"color\":\"green\"}");
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> Wave(), 20*10);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:setblock 498 8 -787 minecraft:stone");
                    World world = Bukkit.getWorld("world"); // Time control
                    world.setTime(14000);
                } else {
                    readyToSpawn = false;
                    ArenaMode = false;
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "minecraft:title @a title {\"text\":\"Arena Minigame was stopped!\",\"color\":\"green\"}");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:setblock 498 8 -787 minecraft:redstone_block");
                    World world = Bukkit.getWorld("world"); // Time control
                    world.setTime(0);
                    for (Creature i : entities) { // Checks for living entities and kills them
                        i.remove();
                        // DEBUG
                        //System.out.println("Living entities killer");
                    }
                    entities.clear();
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
            player.sendMessage(ChatColor.GREEN + "[Arena] Du wurdest zur Arena Minigame liste hinzugefügt!");
            // DEBUG
            //System.out.println(players);
        }
        public void unRegisterArenaPlayer(Player player) {
            int idx = 0;
            while (idx < players.size())
            {
                if(players.get(idx) == player)
                {
                    player.sendMessage(ChatColor.GREEN + "[Arena] Du wurdest von der Arena Minigame liste entfernt!");
                    players.remove(idx);
                }
                else
                {
                    idx++;
                }
            }
            // DEBUG
            //System.out.println(players);
        }
        public void targetRandomPlayer(Creature creature) {
            Player player = utilities.getRandomElementPlayer(players)
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

            double randomX = utilities.getRandomElementDouble(XSpawns);
            double randomZ = utilities.getRandomElementDouble(ZSpawns);
            EntityType randomEnemy = utilities.getRandomElementEntityType(EntityTypes);

            World world = Bukkit.getWorld("world");
            Location l = new Location(world, randomX, 64, randomZ);
            Creature entity = (Creature) world.spawnEntity(l, randomEnemy);

            targetRandomPlayer(entity); // Moves to random player

            entities.add(entity);

            // DEBUG
            //System.out.println(entities);
        }
        public void spawnWave() {
            if (ArenaMode) {
                int minMobs = (int)(10 * (0.2 * round));
                int maxMobs = (int)(10 * (0.4 * round));
                for (int i = 0; i < utilities.getRandomNumber(minMobs, maxMobs); i++){
                    spawnMob();
                }
            }
        }
        public void nextWave() {
            if(ArenaMode) {
                round++;
                Wave();
            }
        }
        public void Wave() {
            //int wavesToSpawn = 4;
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "minecraft:title @a title {\"text\":\"Wave: "+ round +"\",\"color\":\"dark_red\"}");

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> spawnWave(), 20*5);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> spawnWave(), 20*(15*((long)(1+(.3*round)))));
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> spawnWave(), 20*(25*((long)(1+(.3*round)))));
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> spawnWave(), 20*(35*((long)(1+(.3*round)))));

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> nextWave(), 20*(50*((long)(1+(.3*round)))));
            /*while (readyToSpawn) {
                readyToSpawn = false;
                for (; wavesToSpawn >= 0; wavesToSpawn--) {
                    if (wavesToSpawn > 0 && (0 == entities.size())) {
                        spawnWave();
                    } else if (wavesToSpawn == 0) {
                        Wave();
                    }
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> readyToSpawn = true, 20*10);
                }
            } */
        }
    }
}