package com.github.caaarlowsz.basicpvp.kit;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.apis.StaffAPI;
import com.github.caaarlowsz.basicpvp.warp.WarpAPI;

public final class KitAPI {

	private static final HashMap<UUID, Kit> kitMap = new HashMap<>();

	public static boolean hasKit(Player player) {
		return kitMap.containsKey(player.getUniqueId());
	}

	public static Kit getKit(Player player) {
		return kitMap.getOrDefault(player.getUniqueId(), Kits.getNoneKit());
	}

	public static void setKit(Player player, Kit kit) {
		kitMap.put(player.getUniqueId(), kit);
		StaffAPI.removeBuild(player);
		WarpAPI.removeWarp(player);

		kit.giveItems(player);
	}

	public static void removeKit(Player player) {
		kitMap.remove(player.getUniqueId());
	}
}