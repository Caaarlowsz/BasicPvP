package com.github.caaarlowsz.basicpvp.apis;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.entity.Player;

public final class PlayerAPI {

	private static final ArrayList<UUID> tellOff = new ArrayList<>();

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
}