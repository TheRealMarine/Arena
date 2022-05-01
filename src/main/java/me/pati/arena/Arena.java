package me.pati.arena;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Arena extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getCommand("arena").setExecutor(new spawning.CommandArena());
        getServer().getPluginManager().registerEvents(new listener.ArenaListener(), this);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:setblock 498 8 -787 minecraft:redstone_block");
        spawning.instance = this;
        System.out.println("[Arena] Version 1.1.0 loaded");
    }
    @Override
    public void onDisable() {
        spawning.instance = null;
        System.out.println("[Arena] Plugin deactivated");
    }
}
