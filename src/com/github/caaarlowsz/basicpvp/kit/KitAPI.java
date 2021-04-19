package com.github.caaarlowsz.basicpvp.kit;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.apis.StaffAPI;
import com.github.caaarlowsz.basicpvp.cabeca.CabecaAPI;
import com.github.caaarlowsz.basicpvp.sidebar.Sidebar;
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

		Sidebar sidebar = new Sidebar("   " + Strings.getCorPrincipal() + "§lARENA   ");
		sidebar.addBlankLine(15);
		sidebar.addLine(14, " Cargo: {player_group}");
		sidebar.addLine(13, " Moedas: §6{player_coins}");
		sidebar.addBlankLine(7);
		sidebar.addLine(6, " KillStreak: §7{player_killstreak}");
		sidebar.addLine(5, " Kit: " + Strings.getCorPrincipal() + "{player_kit}");
		sidebar.addBlankLine(4);
		sidebar.addLine(3, " Rank: {player_rank}");
		sidebar.addLine(2, " Jogadores: §7{server_players}/{server_slots}");
		sidebar.addBlankLine(1);
		sidebar.addLine(0, "§e" + Strings.getWebsite());
		SidebarAPI.setSidebar(player, sidebar);

		kit.giveItems(player);

		CabecaAPI.updateCabeca(player);
	}

	public static void removeKit(Player player) {
		kitMap.remove(player.getUniqueId());
	}
}