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
import com.github.caaarlowsz.basicpvp.utils.ServerType;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class ArcherKit extends Kit {

	public ArcherKit() {
		super("Archer", Stacks.item(Material.BOW, Strings.getCorPrincipal() + "Kit Archer",
				"§7Seja um atirador de longa distância,", "§7e mostre um estilo de combate diferente."));
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		ItemStack bow = Stacks.item(Material.BOW, true,
				Arrays.asList(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS), Strings.getCorPrincipal() + "Arco",
				"§7Kit " + this.getName());
		bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		if (BasicKitPvP.isServerType(ServerType.FULLIRON))
			bow.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
		inv.setItem(1, bow);
		inv.setItem(2, Stacks.item(Material.ARROW, Strings.getCorPrincipal() + "Flecha", "§7Kit " + this.getName()));
		player.updateInventory();
	}
}