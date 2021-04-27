package com.github.caaarlowsz.basicpvp.kit;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.apis.StaffAPI;
import com.github.caaarlowsz.basicpvp.cabeca.CabecaAPI;
import com.github.caaarlowsz.basicpvp.sidebar.SidebarAPI;
import com.github.caaarlowsz.basicpvp.utils.Strings;
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
		StaffAPI.removeAdmin(player);
		StaffAPI.removeBuild(player);
		WarpAPI.removeWarp(player);

		SidebarAPI.setSidebar(player, Strings.getSidebarKits());

		kit.giveItems(player);

		CabecaAPI.updateCabeca(player);
	}

	public static Kit removeKit(Player player) {
		Kit kit = Kits.getNoneKit();
		if (hasKit(player))
			kit = kitMap.remove(player.getUniqueId());
		kit.removeCooldown(player);
		return kit;
	}
}