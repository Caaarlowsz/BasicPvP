package com.github.caaarlowsz.basicpvp.warp.warps;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.sidebar.Sidebar;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.Warp;

public final class FPSWarp extends Warp {

	public FPSWarp() {
		super("FPS", Stacks.item(Material.GLASS, Strings.getCorPrincipal() + "Warp FPS",
				"§7Jogue em um local mais limpo", "§7e leve para otimizar seus FPS."));
		Sidebar sidebar = new Sidebar("   " + Strings.getCorPrincipal() + "§l" + this.getName().toUpperCase() + "   ");
		sidebar.addBlankLine(15);
		sidebar.addLine(14, " Cargo: {player_group}");
		sidebar.addLine(13, " Moedas: §6{player_coins}");
		sidebar.addBlankLine(6);
		sidebar.addLine(5, " KillStreak: §7{player_killstreak}");
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

		inv.setChestplate(Stacks.item(Material.LEATHER_CHESTPLATE, true, Arrays.asList(ItemFlag.HIDE_UNBREAKABLE),
				Strings.getPeitoral()));

		ItemStack sword = Stacks.item(Material.STONE_SWORD, true,
				Arrays.asList(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS),
				Strings.getEspadaDePedra());
		sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		inv.setItem(0, sword);

		inv.setItem(13, Stacks.item(Material.BOWL, 64, Strings.getPote()));
		inv.setItem(14, Stacks.item(Material.RED_MUSHROOM, 64, Strings.getCogumeloVermelho()));
		inv.setItem(15, Stacks.item(Material.BROWN_MUSHROOM, 64, Strings.getCogumeloMarrom()));

		for (int i = 0; i < 32; i++)
			inv.addItem(Stacks.item(Material.MUSHROOM_SOUP));
		player.updateInventory();
	}
}