package com.github.caaarlowsz.basicpvp.sidebar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
		sidebar = sidebar.clone();
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
			String prefix = "", line = entry.getValue();

			List<String> suffixes = Arrays.asList("{player_group}", "{player_coins}", "{player_killstreak}",
					"{player_kills}", "{player_deaths}", "{player_rank}", "{player_rank_icon} {player_rank}",
					"{player_rank} {player_rank_icon}", "{player_kit}", "{player_warp}",
					"{server_players}/{server_slots}", "{server_players}", "{server_slots}");
			for (String suffix : suffixes)
				if (line.contains(suffix))
					sidebar.setSuffix(suffix, line = line.replace(suffix, ""));

			if (line.length() > 16) {
				prefix = line.substring(0, 16);
				line = line.substring(16);
			}
			if (line.length() > 16)
				line = line.substring(0, 16);

			int score = entry.getKey();
			Team team = scoreboard.getTeam("sidebar-" + score);
			if (team == null)
				team = scoreboard.registerNewTeam("sidebar-" + score);
			team.setPrefix(prefix);
			team.setSuffix("");
			team.addEntry(line);

			objective.getScore(line).setScore(score);
		}

		updateSidebar(player);
	}

	public static void updateSidebar(Player player) {
		HashMap<String, String> suffixes = getSidebar(player).getSuffixes();
		player.getScoreboard().getTeams().stream().filter(team -> team.getName().startsWith("sidebar-"))
				.forEach(team -> team.getEntries().forEach(entry -> {
					String line = team.getPrefix() + entry;
					if (suffixes.containsValue(line))
						suffixes.entrySet().stream().filter(map -> map.getValue().equals(line)).forEach(map -> {
							if (map.getKey().equals("{player_group}"))
								team.setSuffix(TagAPI.getMaxTag(player).getColoredName());
							if (map.getKey().equals("{player_coins}"))
								team.setSuffix(StatusAPI.getMoedas(player));
							if (map.getKey().equals("{player_killstreak}"))
								team.setSuffix(StatusAPI.getKillStreak(player));
							if (map.getKey().equals("{player_kills}"))
								team.setSuffix(StatusAPI.getAbates(player));
							if (map.getKey().equals("{player_deaths}"))
								team.setSuffix(StatusAPI.getMortes(player));
							if (map.getKey().equals("{player_rank_icon} {player_rank}"))
								team.setSuffix(StatusAPI.getRank(player).getColoredSymbolName());
							if (map.getKey().equals("{player_rank} {player_rank_icon}"))
								team.setSuffix(StatusAPI.getRank(player).getColoredNameSymbol());
							if (map.getKey().equals("{player_rank}"))
								team.setSuffix(StatusAPI.getRank(player).getColoredName());
							if (map.getKey().equals("{player_kit}"))
								team.setSuffix(KitAPI.getKit(player).getName());
							if (map.getKey().equals("{player_warp}"))
								team.setSuffix(WarpAPI.getWarp(player).getName());
							if (map.getKey().equals("{server_players}/{server_slots}"))
								team.setSuffix(Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
							if (map.getKey().equals("{server_players}"))
								team.setSuffix(Bukkit.getOnlinePlayers().size() + "");
							if (map.getKey().equals("{server_slots}"))
								team.setSuffix(Bukkit.getMaxPlayers() + "");
						});
				}));
	}

	public static void removeSidebar(Player player) {
		sidebarMap.remove(player.getUniqueId());
	}
}