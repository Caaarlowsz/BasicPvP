package com.github.caaarlowsz.basicpvp.warp.warps;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.warp.Warp;

public final class SpawnWarp extends Warp {

	public SpawnWarp() {
		super("Spawn");
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		player.updateInventory();
	}
}