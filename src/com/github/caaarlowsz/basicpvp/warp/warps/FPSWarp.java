package com.github.caaarlowsz.basicpvp.warp.warps;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.warp.Warp;

public final class FPSWarp extends Warp {

	public FPSWarp() {
		super("FPS", Stacks.item(Material.GLASS, "§aWarp FPS", "§7Jogue em um local mais limpo",
				"§7e leve para otimizar seus FPS."));
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.setChestplate(Stacks.item(Material.LEATHER_CHESTPLATE, true, "§aPeitoral"));
		inv.setItem(0, Stacks.item(Material.STONE_SWORD, true, "§aEspada"));

		inv.setItem(13, Stacks.item(Material.BOWL, 64, "§aPote"));
		inv.setItem(14, Stacks.item(Material.RED_MUSHROOM, 64, "§aCogumelo"));
		inv.setItem(15, Stacks.item(Material.BROWN_MUSHROOM, 64, "§aCogumelo"));

		for (int i = 0; i < 32; i++)
			inv.addItem(Stacks.item(Material.MUSHROOM_SOUP));
		player.updateInventory();
	}
}