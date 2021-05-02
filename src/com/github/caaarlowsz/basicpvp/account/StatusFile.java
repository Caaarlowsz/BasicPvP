package com.github.caaarlowsz.basicpvp.account;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public final class StatusFile {

	private static File file;
	private static YamlConfiguration config;

	public static void createNewFile(String path) {
		file = new File(path, "status.yml");
		if (!file.exists())
			file.getParentFile().mkdirs();
		config = YamlConfiguration.loadConfiguration(file);
	}

	public static int getMoedas(Player player) {
		return config.getInt("status." + player.getName() + ".moedas", 0);
	}

	private static void setMoedas(Player player, int value) {
		try {
			config.set("status." + player.getName() + ".moedas", value);
			config.save(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void addMoedas(Player player, int value) {
		setMoedas(player, getMoedas(player) + value);
	}

	public static void drawMoedas(Player player, int value) {
		int result = getMoedas(player) - value;
		setMoedas(player, result < 0 ? 0 : result);
	}

	public static int getXP(Player player) {
		return config.getInt("status." + player.getName() + ".xp", 0);
	}

	private static void setXP(Player player, int value) {
		try {
			config.set("status." + player.getName() + ".xp", value);
			config.save(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void addXP(Player player, int value) {
		setXP(player, getXP(player) + value);
	}

	public static void drawXP(Player player, int value) {
		int result = getXP(player) - value;
		setXP(player, result < 0 ? 0 : result);
	}

	public static int getKillStreak(Player player) {
		return config.getInt("status." + player.getName() + ".killstreak", 0);
	}

	public static void setKillStreak(Player player, int value) {
		try {
			config.set("status." + player.getName() + ".killstreak", value);
			config.save(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void addKillStreak(Player player) {
		setKillStreak(player, getKillStreak(player) + 1);
	}

	public static void resetKillStreak(Player player) {
		setKillStreak(player, 0);
	}

	public static int getAbates(Player player) {
		return config.getInt("status." + player.getName() + ".abates", 0);
	}

	public static void addAbate(Player player) {
		try {
			config.set("status." + player.getName() + ".abates", getAbates(player) + 1);
			config.save(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static int getMortes(Player player) {
		return config.getInt("status." + player.getName() + ".mortes", 0);
	}

	public static void addMorte(Player player) {
		try {
			config.set("status." + player.getName() + ".mortes", getMortes(player) + 1);
			config.save(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}