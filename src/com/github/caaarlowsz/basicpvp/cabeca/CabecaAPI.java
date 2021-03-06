package com.github.caaarlowsz.basicpvp.cabeca;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public final class CabecaAPI {

	private static final HashMap<UUID, Cabeca> cabecaMap = new HashMap<>();

	public static boolean hasCabeca(Player player) {
		return cabecaMap.containsKey(player.getUniqueId());
	}

	public static Cabeca getCabeca(Player player) {
		return cabecaMap.getOrDefault(player.getUniqueId(), Cabecas.getNoneCabeca());
	}

	public static void updateCabeca(Player player) {
		if (hasCabeca(player) && player.getInventory().getHelmet() == null)
			player.getInventory().setHelmet(getCabeca(player).getIcon());
	}

	public static void setCabeca(Player player, Cabeca cabeca) {
		cabecaMap.put(player.getUniqueId(), cabeca);
		player.getInventory().setHelmet(cabeca.getIcon());
	}

	public static void removeCabeca(Player player) {
		player.getInventory().setHelmet(null);
		cabecaMap.remove(player.getUniqueId());
	}
}