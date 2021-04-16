package com.github.caaarlowsz.basicpvp.warp;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

public final class WarpsFile {

	private static File file;
	private static YamlConfiguration config;

	public static void createNewFile(String path) {
		file = new File(path, "warps.yml");
		if (!file.exists())
			file.getParentFile().mkdirs();
		config = YamlConfiguration.loadConfiguration(file);
	}

	public static boolean hasLocation(String warp) {
		return config.contains("warps." + warp.replace(" ", "-"));
	}

	public static Location getLocation(String name) {
		name = name.replace(" ", "-");
		return new Location(Bukkit.getWorld(config.getString("warps." + name + ".world")),
				config.getDouble("warps." + name + ".x"), config.getDouble("warps." + name + ".y"),
				config.getDouble("warps." + name + ".z"), config.getFloat("warps." + name + ".yaw"),
				config.getFloat("warps." + name + ".pitch"));
	}

	public static void setLocation(String name, Location location) {
		name = name.replace(" ", "-");
		try {
			config.set("warps." + name + ".world", location.getWorld().getName());
			config.set("warps." + name + ".x", location.getX());
			config.set("warps." + name + ".y", location.getY());
			config.set("warps." + name + ".z", location.getZ());
			config.set("warps." + name + ".yaw", location.getYaw());
			config.set("warps." + name + ".pitch", location.getPitch());
			config.save(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}