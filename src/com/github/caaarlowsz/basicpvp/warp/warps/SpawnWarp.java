package com.github.caaarlowsz.basicpvp.warp.warps;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.guis.MenuGUI;
import com.github.caaarlowsz.basicpvp.kit.guis.SeusKitsGUI;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.warp.Warp;
import com.github.caaarlowsz.basicpvp.warp.WarpsGUI;

public final class SpawnWarp extends Warp {

	public SpawnWarp() {
		super("Spawn", Stacks.item(Material.BEACON, "§aSpawn", "§7Local de nascimento padrão."));
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