package com.github.caaarlowsz.basicpvp.kit.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.utils.Stacks;

public final class PvPKit extends Kit {

	public PvPKit() {
		super("PvP", Stacks.item(Material.STONE_SWORD, "§aKit PvP", "§7Mostre a verdadeira força da",
				"§7sua Espada com Afiação I."));
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.getItem(0).addEnchantment(Enchantment.DAMAGE_ALL, 1);
		player.updateInventory();
	}
}