package com.github.caaarlowsz.basicpvp.kit.kits;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.kit.Kit;

public final class PvPKit extends Kit {

	public PvPKit() {
		super("PvP", null);
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.getItem(0).addEnchantment(Enchantment.DAMAGE_ALL, 1);
		player.updateInventory();
	}
}