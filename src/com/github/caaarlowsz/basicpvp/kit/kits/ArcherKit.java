package com.github.caaarlowsz.basicpvp.kit.kits;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.utils.KitType;
import com.github.caaarlowsz.basicpvp.utils.Stacks;

public final class ArcherKit extends Kit {

	public ArcherKit() {
		super("Archer");
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		ItemStack bow = Stacks.item(Material.BOW, true,
				Arrays.asList(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS), "§aArco", "§7Kit " + this.getName());
		bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		if (BasicKitPvP.getKitType("itens.arena") == KitType.FULLIRON)
			bow.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
		inv.setItem(1, bow);
		inv.setItem(2, Stacks.item(Material.ARROW, "§aFlecha", "§7Kit " + this.getName()));
		player.updateInventory();
	}
}