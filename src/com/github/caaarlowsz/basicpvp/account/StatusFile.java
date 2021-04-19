package com.github.caaarlowsz.basicpvp.account;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

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

	public static String getFormattedMoedas(Player player) {
		return new DecimalFormat().format(getMoedas(player)).replace(",", ".");
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

	public static String getFormattedXP(Player player) {
		return new DecimalFormat().format(getXP(player)).replace(",", ".");
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
		return config.getInt("status." + player.getName() + ".abatestreak", 0);
	}

	public static String getFormattedKillStreak(Player player) {
		return new DecimalFormat().format(getKillStreak(player)).replace(",", ".");
	}

	public static void setKillStreak(Player player, int value) {
		try {
			config.set("status." + player.getName() + ".abatestreak", value);
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

	public static String getFormattedAbates(Player player) {
		return new DecimalFormat().format(getAbates(player)).replace(",", ".");
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

	public static String getFormattedMortes(Player player) {
		return new DecimalFormat().format(getMortes(player)).replace(",", ".");
	}

	public static void addMorte(Player player) {
		try {
			config.set("status." + player.getName() + ".mortes", getMortes(player) + 1);
			config.save(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static String getMaxGroup(Player player) {
		if (player.hasPermission("kitpvp.tag.dono"))
			return "§4Dono";
		if (player.hasPermission("kitpvp.tag.admin"))
			return "§cAdmin";
		if (player.hasPermission("kitpvp.tag.mod"))
			return "§5Mod";
		if (player.hasPermission("kitpvp.tag.trialmod"))
			return "§5TrialMod";
		if (player.hasPermission("kitpvp.tag.builder"))
			return "§2Builder";
		if (player.hasPermission("kitpvp.tag.youtuber"))
			return "§bYouTuber";
		if (player.hasPermission("kitpvp.tag.beta"))
			return "§1Beta";
		if (player.hasPermission("kitpvp.tag.pro"))
			return "§6Pro";
		if (player.hasPermission("kitpvp.tag.mvp"))
			return "§9MvP";
		if (player.hasPermission("kitpvp.tag.vip"))
			return "§aVIP";
		return "§7Membro";
	}

	public static String getRankIconName(Player player) {
		int xp = getXP(player);
		if (xp >= 3000)
			return "§4✪ LEGENDARY";
		if (xp >= 2700)
			return "§3\u2737 SAPPHIRE";
		if (xp >= 2400)
			return "§c\u2739 RUBY";
		if (xp >= 2100)
			return "§2\u2738 EMERALD";
		if (xp >= 1800)
			return "§b\u2748 DIAMOND";
		if (xp >= 1500)
			return "§6\u272a GOLD";
		if (xp >= 1200)
			return "§7\u272f SILVER";
		if (xp >= 900)
			return "§1\u2725 EXPERT";
		if (xp >= 600)
			return "§e\u2630 ADVANCED";
		if (xp >= 300)
			return "§a\u268c PRIMARY";
		return "§f- UNRANKED";
	}

	public static String getRankIcon(Player player) {
		int xp = getXP(player);
		if (xp >= 3000)
			return "§4✪";
		if (xp >= 2700)
			return "§3\u2737";
		if (xp >= 2400)
			return "§c\u2739";
		if (xp >= 2100)
			return "§2\u2738";
		if (xp >= 1800)
			return "§b\u2748";
		if (xp >= 1500)
			return "§6\u272a";
		if (xp >= 1200)
			return "§7\u272f";
		if (xp >= 900)
			return "§1\u2725";
		if (xp >= 600)
			return "§e\u2630";
		if (xp >= 300)
			return "§a\u268c";
		return "§f-";
	}
}