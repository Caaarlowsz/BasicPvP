package com.github.caaarlowsz.basicpvp.warp.warps;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.guis.MenuGUI;
import com.github.caaarlowsz.basicpvp.kit.guis.SeusKitsGUI;
import com.github.caaarlowsz.basicpvp.sidebar.Sidebar;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.Warp;
import com.github.caaarlowsz.basicpvp.warp.WarpsGUI;

public final class SpawnWarp extends Warp {

	public SpawnWarp() {
		super("Spawn",
				Stacks.item(Material.BEACON, Strings.getCorPrincipal() + "Spawn", "§7Local de nascimento padrão."));
		Sidebar sidebar = new Sidebar(Strings.getNome());
		sidebar.addBlankLine(11);
		sidebar.addLine(10, " Cargo: {player_group}");
		sidebar.addLine(9, " Moedas: §6{player_coins}");
		sidebar.addBlankLine(8);
		sidebar.addLine(7, " KillStreak: §7{player_killstreak}");
		sidebar.addLine(6, " Abates: " + Strings.getCorPrincipal() + "{player_kills}");
		sidebar.addLine(5, " Mortes: " + Strings.getCorPrincipal() + "{player_deaths}");
		sidebar.addBlankLine(4);
		sidebar.addLine(3, " Rank: {player_rank}");
		sidebar.addLine(2, " Jogadores: §7{server_players}/{server_slots}");
		sidebar.addBlankLine(1);
		sidebar.addLine(0, "§e" + Strings.getWebsite());
		this.setSidebar(sidebar);
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.setItem(3, SeusKitsGUI.ICON);
		inv.setItem(4, MenuGUI.ICON);
		inv.setItem(5, WarpsGUI.ICON);

		player.updateInventory();
	}
}