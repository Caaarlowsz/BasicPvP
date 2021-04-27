package com.github.caaarlowsz.basicpvp.apis;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public final class WorldGuardAPI implements Listener {

	private static WorldGuardPlugin getWorldGuard() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
		if (plugin != null && plugin instanceof WorldGuardPlugin)
			return ((WorldGuardPlugin) plugin);
		return null;
	}

	public static boolean hasPvP(Player player) {
		return hasPvP(player.getLocation());
	}

	public static boolean hasPvP(Location location) {
		return allowFlag(DefaultFlag.PVP, location);
	}

	public static boolean allowFlag(StateFlag flag, Location location) {
		WorldGuardPlugin worldguard = getWorldGuard();
		if (worldguard != null)
			for (ProtectedRegion protectedRegion : worldguard.getRegionManager(location.getWorld())
					.getApplicableRegions(location))
				if (protectedRegion.getFlag(flag) == State.ALLOW)
					return true;
		return false;
	}
}