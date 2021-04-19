package com.github.caaarlowsz.basicpvp.warp.warps;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.sidebar.Sidebar;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.Warp;

public final class UMvUMWarp extends Warp {

	public UMvUMWarp() {
		super("1v1",
				Stacks.item(Material.BLAZE_ROD, "§aWarp 1v1", "§7Tenha duelos solo", "§7contra outros jogadores."));
		Sidebar sidebar = new Sidebar("   §a§l" + this.getName().toUpperCase() + "   ");
		sidebar.addBlankLine(15);
		sidebar.addLine(14, " Cargo: {player_group}");
		sidebar.addLine(13, " Moedas: §6{player_coins}");
		sidebar.addBlankLine(7);
		sidebar.addLine(6, " Inimigo:");
		sidebar.addLine(5, "  §a{player_enemy}");
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

		inv.setItem(3, Stacks.item(Material.BLAZE_ROD, "§aConvidar para 1v1"));
		inv.setItem(5, Stacks.item(Material.INK_SACK, 1, 8, "§a1v1 Rápido"));
		player.updateInventory();
	}
}