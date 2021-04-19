package com.github.caaarlowsz.basicpvp.sidebar;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.account.StatusAPI;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.tag.TagAPI;
import com.github.caaarlowsz.basicpvp.warp.WarpAPI;

public final class SidebarAPI {

	private static final HashMap<UUID, Sidebar> sidebarMap = new HashMap<>();

	public static boolean hasSidebar(Player player) {
		return sidebarMap.containsKey(player.getUniqueId());
	}

	public static Sidebar getSidebar(Player player) {
		if (hasSidebar(player))
			return sidebarMap.get(player.getUniqueId());
		return null;
	}

	public static void setSidebar(Player player, Sidebar sidebar) {
		sidebarMap.put(player.getUniqueId(), sidebar);
		sidebar.addPlayer(player);
		updateSidebar(player);
	}

	public static void updateSidebar(Player player) {
		Sidebar sidebar = getSidebar(player);
		for (Entry<Integer, String> lines : sidebar.getLines())
			sidebar.updateLine(lines.getKey(),
					lines.getValue().replace("{player_group}", TagAPI.getMaxTag(player).getColoredName())
							.replace("{player_coins}", StatusAPI.getMoedas(player))
							.replace("{player_killstreak}", StatusAPI.getKillStreak(player))
							.replace("{player_kills}", StatusAPI.getAbates(player))
							.replace("{player_deaths}", StatusAPI.getMortes(player))
							.replace("{player_rank}", StatusAPI.getRank(player).getColoredName())
							.replace("{player_kit}", KitAPI.getKit(player).getName())
							.replace("{player_warp}", WarpAPI.getWarp(player).getName())
							.replace("{server_players}", String.valueOf(Bukkit.getOnlinePlayers().size()))
							.replace("{server_slots}", String.valueOf(Bukkit.getMaxPlayers())));
	}

	public static void removeSidebar(Player player) {
		sidebarMap.remove(player.getUniqueId());
	}
}