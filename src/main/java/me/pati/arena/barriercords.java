package me.pati.arena;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public class barriercords {
    static List<Location> barrierCoords = new ArrayList<>();
    static public void LocationsBarrier() {
        World world = Bukkit.getWorld("world");
        barrierCoords.add(0,new Location(world, 875, 66, -590));
        barrierCoords.add(1,new Location(world, 875, 66, -600));
        barrierCoords.add(2,new Location(world, 874, 66, -590));
        barrierCoords.add(3,new Location(world, 874, 66, -600));
        barrierCoords.add(4,new Location(world, 872, 66, -592));
        barrierCoords.add(5,new Location(world, 872, 66, -593));
        barrierCoords.add(6,new Location(world, 872, 66, -594));
        barrierCoords.add(7,new Location(world, 872, 66, -595));
        barrierCoords.add(8,new Location(world, 872, 66, -596));
        barrierCoords.add(9,new Location(world, 872, 66, -597));
        barrierCoords.add(10,new Location(world, 872, 66, -598));
        barrierCoords.add(11,new Location(world, 842, 66, -590));
        barrierCoords.add(12,new Location(world, 842, 66, -600));
        barrierCoords.add(13,new Location(world, 841, 66, -590));
        barrierCoords.add(14,new Location(world, 841, 66, -600));
        barrierCoords.add(15,new Location(world, 844, 66, -592));
        barrierCoords.add(16,new Location(world, 844, 66, -593));
        barrierCoords.add(17,new Location(world, 844, 66, -594));
        barrierCoords.add(18,new Location(world, 844, 66, -595));
        barrierCoords.add(19,new Location(world, 844, 66, -596));
        barrierCoords.add(20,new Location(world, 844, 66, -596));
        barrierCoords.add(21,new Location(world, 844, 66, -597));
        barrierCoords.add(22,new Location(world, 844, 66, -598));
        barrierCoords.add(23,new Location(world, 857, 66, -627));
        barrierCoords.add(24,new Location(world, 858, 66, -627));
        barrierCoords.add(25,new Location(world, 859, 66, -627));
        for (int i = 0; i < 26; i++) {
            Location loc = barrierCoords.get(i);
            loc.getBlock().setType(Material.BARRIER);
        }
        barrierCoords.clear();
    }
    static public void RemoveLocationsBarrier() {
        World world = Bukkit.getWorld("world");
        barrierCoords.add(0,new Location(world, 875, 66, -590));
        barrierCoords.add(1,new Location(world, 875, 66, -600));
        barrierCoords.add(2,new Location(world, 874, 66, -590));
        barrierCoords.add(3,new Location(world, 874, 66, -600));
        barrierCoords.add(4,new Location(world, 872, 66, -592));
        barrierCoords.add(5,new Location(world, 872, 66, -593));
        barrierCoords.add(6,new Location(world, 872, 66, -594));
        barrierCoords.add(7,new Location(world, 872, 66, -595));
        barrierCoords.add(8,new Location(world, 872, 66, -596));
        barrierCoords.add(9,new Location(world, 872, 66, -597));
        barrierCoords.add(10,new Location(world, 872, 66, -598));
        barrierCoords.add(11,new Location(world, 842, 66, -590));
        barrierCoords.add(12,new Location(world, 842, 66, -600));
        barrierCoords.add(13,new Location(world, 841, 66, -590));
        barrierCoords.add(14,new Location(world, 841, 66, -600));
        barrierCoords.add(15,new Location(world, 844, 66, -592));
        barrierCoords.add(16,new Location(world, 844, 66, -593));
        barrierCoords.add(17,new Location(world, 844, 66, -594));
        barrierCoords.add(18,new Location(world, 844, 66, -595));
        barrierCoords.add(19,new Location(world, 844, 66, -596));
        barrierCoords.add(20,new Location(world, 844, 66, -596));
        barrierCoords.add(21,new Location(world, 844, 66, -597));
        barrierCoords.add(22,new Location(world, 844, 66, -598));
        barrierCoords.add(23,new Location(world, 857, 66, -627));
        barrierCoords.add(24,new Location(world, 858, 66, -627));
        barrierCoords.add(25,new Location(world, 859, 66, -627));
        for (int i = 0; i < 26; i++) {
            Location loc = barrierCoords.get(i);
            loc.getBlock().setType(Material.AIR);
        }
        barrierCoords.clear();
    }
}