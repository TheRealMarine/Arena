package me.pati.arena;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Arena extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("[Arena] Version 1.1.0 loaded");
        this.getCommand("arena").setExecutor(new spawning.CommandArena());
        getServer().getPluginManager().registerEvents(new listener.ArenaListener(), this);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:setblock 498 8 -787 minecraft:redstone_block");
        spawning.instance = this;
    }
    @Override                           
    public void onDisable() {
        System.out.println("[Arena] Version 1.1.0 unloaded");
        spawning.instance = null;
    }
}
