package com.github.caaarlowsz.basicpvp.warp;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.apis.StaffAPI;
import com.github.caaarlowsz.basicpvp.cabeca.CabecaAPI;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.sidebar.SidebarAPI;

public final class WarpAPI {

	private static final HashMap<UUID, Warp> warpMap = new HashMap<>();

	public static Warp getWarp(Player player) {
		return warpMap.getOrDefault(player.getUniqueId(), Warps.getNoneWarp());
	}

	public static void setWarp(Player player, Warp warp) {
		KitAPI.removeKit(player);
		StaffAPI.removeAdmin(player);
		StaffAPI.removeBuild(player);
		removeWarp(player);
		warpMap.put(player.getUniqueId(), warp);
		SidebarAPI.setSidebar(player, warp.getSidebar());

		if (WarpsFile.hasLocation(warp.getName()))
			player.teleport(WarpsFile.getLocation(warp.getName()));

		warp.giveItems(player);

		CabecaAPI.updateCabeca(player);
	}

	public static void removeWarp(Player player) {
		warpMap.remove(player.getUniqueId());
	}
}