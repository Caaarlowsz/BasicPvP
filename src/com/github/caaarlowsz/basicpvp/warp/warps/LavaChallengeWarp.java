package com.github.caaarlowsz.basicpvp.warp.warps;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.warp.Warp;

public final class LavaChallengeWarp extends Warp {

	public LavaChallengeWarp() {
		super("Lava Challenge", Stacks.item(Material.LAVA_BUCKET, "§aWarp Lava Challenge",
				"§7Treine seu refil e seu recraft", "§7enquanto completa desafios."));
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.setItem(0, Stacks.item(Material.STONE_SWORD, true, "§aEspada"));

		inv.setItem(13, Stacks.item(Material.BOWL, 64, "§aPote"));
		inv.setItem(14, Stacks.item(Material.RED_MUSHROOM, 64, "§aCogumelo"));
		inv.setItem(15, Stacks.item(Material.BROWN_MUSHROOM, 64, "§aCogumelo"));

		for (int i = 0; i < 32; i++)
			inv.addItem(Stacks.item(Material.MUSHROOM_SOUP));
		player.updateInventory();
	}
}