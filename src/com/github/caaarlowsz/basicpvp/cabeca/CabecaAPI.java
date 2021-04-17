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
		return cabecaMap.getOrDefault(player.getUniqueId(), Cabeca.NENHUMA);
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