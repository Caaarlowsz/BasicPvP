package com.github.caaarlowsz.basicpvp.player;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public final class PlayerAPI {

	private static final ArrayList<UUID> tellOff = new ArrayList<>();
	private static final HashMap<UUID, Long> useReport = new HashMap<>();

	public static boolean hasTellOff(Player player) {
		return tellOff.contains(player.getUniqueId());
	}

	public static void addTellOff(Player player) {
		if (!hasTellOff(player))
			tellOff.add(player.getUniqueId());
	}

	public static void removeTellOff(Player player) {
		tellOff.remove(player.getUniqueId());
	}

	public static boolean hasUseReport(Player player) {
		return useReport.containsKey(player.getUniqueId());
	}

	public static long getUseReport(Player player) {
		if (hasUseReport(player))
			return (useReport.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L;
		return 0L;
	}

	public static void setUseReport(Player player, long seconds) {
		useReport.put(player.getUniqueId(), System.currentTimeMillis() + (seconds * 1000L));
	}

	public static void removeUseReport(Player player) {
		useReport.remove(player.getUniqueId());
	}
	
	public static String getMoedas(Player player) {
		return new DecimalFormat().format(StatusFile.getMoedas(player)).replace(",", ".");
	}

	public static Rank getRank(Player player) {
		return Rank.getRankByXP(StatusFile.getXP(player));
	}

	public static String getXP(Player player) {
		return new DecimalFormat().format(StatusFile.getXP(player)).replace(",", ".");
	}

	public static String getKillStreak(Player player) {
		return new DecimalFormat().format(StatusFile.getKillStreak(player)).replace(",", ".");
	}

	public static String getAbates(Player player) {
		return new DecimalFormat().format(StatusFile.getAbates(player)).replace(",", ".");
	}

	public static String getMortes(Player player) {
		return new DecimalFormat().format(StatusFile.getMortes(player)).replace(",", ".");
	}
}