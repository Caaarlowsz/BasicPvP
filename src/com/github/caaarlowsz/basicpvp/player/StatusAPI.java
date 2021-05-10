package com.github.caaarlowsz.basicpvp.player;

import java.text.DecimalFormat;

import org.bukkit.entity.Player;

public final class StatusAPI {

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