package com.github.caaarlowsz.basicpvp.warp.warps;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.Warp;

public final class UMvUMWarp extends Warp {

	public UMvUMWarp() {
		super("1v1", Stacks.item(Material.BLAZE_ROD, Strings.getCorPrincipal() + "Warp 1v1", "§7Tenha duelos solo",
				"§7contra outros jogadores."));
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.setItem(3, Stacks.item(Material.BLAZE_ROD, Strings.getCorPrincipal() + "Convidar para 1v1"));
		inv.setItem(5, Stacks.item(Material.INK_SACK, 1, 8, Strings.getCorPrincipal() + "1v1 Rápido"));
		player.updateInventory();
	}
}