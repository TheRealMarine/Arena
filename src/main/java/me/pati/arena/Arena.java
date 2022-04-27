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
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import me.pati.arena.*;

public final class Arena extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("[Arena] Version 1.0.2 loaded");
        this.getCommand("arena").setExecutor(new spawning.CommandArena());
        getServer().getPluginManager().registerEvents(new listener.ArenaListener(), this);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:setblock 498 8 -787 minecraft:redstone_block");
        spawning.instance = this;
    }
    @Override
    public void onDisable() {
        System.out.println("[Arena] Minigame Plugin deaktiviert");
        spawning.instance = null;
    }
}
