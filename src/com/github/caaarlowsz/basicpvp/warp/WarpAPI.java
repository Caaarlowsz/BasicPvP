package com.github.caaarlowsz.basicpvp.warp;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.apis.StaffAPI;
import com.github.caaarlowsz.basicpvp.cabeca.CabecaAPI;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.sidebar.SidebarAPI;
import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.warps.UMvUMWarp;

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

		SidebarAPI.setSidebar(player, Strings.getSidebarWarps());

		if (WarpsFile.hasLocation(warp.getName()))
			player.teleport(WarpsFile.getLocation(warp.getName()));

		warp.giveItems(player);

		CabecaAPI.updateCabeca(player);
	}

	public static Warp removeWarp(Player player) {
		Warp warp = Warps.getNoneWarp();
		if (warpMap.containsKey(player.getUniqueId()))
			warp = warpMap.remove(player.getUniqueId());

		if (warp instanceof UMvUMWarp) {
			UMvUMWarp umvum = (UMvUMWarp) warp;
			umvum.clearInvites(player);
			umvum.removeFastDuel(player);
			umvum.removeEnemy(player);
		}
		return warp;
	}
}