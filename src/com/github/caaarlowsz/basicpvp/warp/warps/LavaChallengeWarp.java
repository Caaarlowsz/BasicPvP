package com.github.caaarlowsz.basicpvp.warp.warps;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.sidebar.Sidebar;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.Warp;

public final class LavaChallengeWarp extends Warp {

	public LavaChallengeWarp() {
		super("Lava Challenge", Stacks.item(Material.LAVA_BUCKET, Strings.getCorPrincipal() + "Warp Lava Challenge",
				"§7Treine seu refil e seu recraft", "§7enquanto completa desafios."));
		Sidebar sidebar = new Sidebar("   " + Strings.getCorPrincipal() + "§l" + this.getName().toUpperCase() + "   ");
		sidebar.addBlankLine(15);
		sidebar.addLine(14, " Cargo: {player_group}");
		sidebar.addLine(13, " Moedas: §6{player_coins}");
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

		inv.setItem(0, Stacks.item(Material.STONE_SWORD,
				Arrays.asList(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE), Strings.getEspadaDePedra()));

		inv.setItem(13, Stacks.item(Material.BOWL, 64, Strings.getPote()));
		inv.setItem(14, Stacks.item(Material.RED_MUSHROOM, 64, Strings.getCogumeloVermelho()));
		inv.setItem(15, Stacks.item(Material.BROWN_MUSHROOM, 64, Strings.getCogumeloMarrom()));

		for (int i = 0; i < 32; i++)
			inv.addItem(Stacks.item(Material.MUSHROOM_SOUP));
		player.updateInventory();
	}
}