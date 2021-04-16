package com.github.caaarlowsz.basicpvp.apis;

import java.util.UUID;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public final class StaffAPI {

	private static final ArrayList<UUID> build = new ArrayList<>();

	public static boolean hasBuild(Player player) {
		return build.contains(player.getUniqueId());
	}

	public static void addBuild(Player player) {
		if (!hasBuild(player))
			build.add(player.getUniqueId());
	}

	public static void removeBuild(Player player) {
		build.remove(player.getUniqueId());
	}
}