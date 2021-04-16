package com.github.caaarlowsz.basicpvp.warp;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public final class WarpAPI {

	private static final HashMap<UUID, Warp> warpMap = new HashMap<>();

	public static Warp getWarp(Player player) {
		return warpMap.getOrDefault(player.getUniqueId(), Warps.getNoneWarp());
	}

	public static void setWarp(Player player, Warp warp) {
		removeWarp(player);
		warpMap.put(player.getUniqueId(), warp);
		warp.giveItems(player);
	}

	public static void removeWarp(Player player) {
		warpMap.remove(player.getUniqueId());
	}
}