package com.github.caaarlowsz.basicpvp.kit.kits;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.utils.ServerType;
import com.github.caaarlowsz.basicpvp.utils.Stacks;

public final class PvPKit extends Kit {

	public PvPKit() {
		super("PvP", 0,
				Stacks.item(
						BasicKitPvP.isServerType(ServerType.FULLIRON) ? Material.DIAMOND_SWORD : Material.STONE_SWORD,
						Arrays.asList(ItemFlag.HIDE_ATTRIBUTES), "§aKit PvP", "§7Mostre a verdadeira força da",
						"§7sua Espada com Afiação I."));
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		ItemStack item = inv.getItem(0);
		item.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		ItemMeta mItem = inv.getItem(0).getItemMeta();
		mItem.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(mItem);
		player.updateInventory();
	}
}