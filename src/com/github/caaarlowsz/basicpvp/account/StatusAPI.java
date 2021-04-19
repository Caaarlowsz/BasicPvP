package com.github.caaarlowsz.basicpvp.account;

import org.bukkit.entity.Player;

public final class StatusAPI {

	public static Rank getRank(Player player) {
		return Rank.getRankByXP(StatusFile.getXP(player));
	}
}