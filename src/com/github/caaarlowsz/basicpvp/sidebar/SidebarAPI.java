package com.github.caaarlowsz.basicpvp.sidebar;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

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

		Scoreboard scoreboard = player.getScoreboard();
		Objective objective = scoreboard.getObjective("Sidebar");
		if (objective == null)
			objective = scoreboard.registerNewObjective("Sidebar", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(sidebar.getDisplayName());
		scoreboard.getTeams().stream().filter(teams -> teams.getName().startsWith("sidebar-"))
				.forEach(teams -> teams.getEntries().forEach(entries -> scoreboard.resetScores(entries)));

		for (Entry<Integer, String> entry : sidebar.getLines().entrySet()) {
			int index = entry.getKey();
			String line = entry.getValue().replace("{player_group}", TagAPI.getMaxTag(player).getColoredName())
					.replace("{player_coins}", StatusAPI.getMoedas(player))
					.replace("{player_killstreak}", StatusAPI.getKillStreak(player))
					.replace("{player_kills}", StatusAPI.getAbates(player))
					.replace("{player_deaths}", StatusAPI.getMortes(player))
					.replace("{player_rank}", StatusAPI.getRank(player).getColoredName())
					.replace("{player_kit}", KitAPI.getKit(player).getName())
					.replace("{player_warp}", WarpAPI.getWarp(player).getName())
					.replace("{server_players}", String.valueOf(Bukkit.getOnlinePlayers().size()))
					.replace("{server_slots}", String.valueOf(Bukkit.getMaxPlayers())), prefix = "", suffix = "";
			if (line.length() > 16) {
				prefix = line.substring(0, 16);
				line = line.substring(16);
			}
			if (line.length() > 16) {
				suffix = line.substring(16);
				if (suffix.length() > 16)
					suffix = suffix.substring(0, 16);
				line = line.substring(0, 16);
			}

			Team team = scoreboard.getTeam("sidebar-" + index);
			if (team == null)
				team = scoreboard.registerNewTeam("sidebar-" + index);
			team.setPrefix(prefix);
			team.addEntry(line);
			team.setSuffix(suffix);

			objective.getScore(line).setScore(index);
		}

		updateSidebar(player);
	}

	public static void updateSidebar(Player player) {
		// Sidebar sidebar = getSidebar(player);
		// for (Entry<Integer, String> lines : sidebar.getLines())
		// sidebar.updateLine(lines.getKey(),
		// );
	}

	public static void removeSidebar(Player player) {
		sidebarMap.remove(player.getUniqueId());
	}
}