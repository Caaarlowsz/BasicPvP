package com.github.caaarlowsz.basicpvp.warp.warps;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.Warp;

public final class FPSWarp extends Warp {

	public FPSWarp() {
		super("FPS", Stacks.item(Material.GLASS, Strings.getCorPrincipal() + "Warp FPS",
				"§7Jogue em um local mais limpo", "§7e leve para otimizar seus FPS."));
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.setChestplate(Stacks.item(Material.LEATHER_CHESTPLATE, true, Arrays.asList(ItemFlag.HIDE_UNBREAKABLE),
				Strings.getPeitoral()));

		ItemStack sword = Stacks.item(Material.STONE_SWORD, true,
				Arrays.asList(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS),
				Strings.getEspadaDePedra());
		sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		inv.setItem(0, sword);

		inv.setItem(13, Stacks.item(Material.BOWL, 64, Strings.getPote()));
		inv.setItem(14, Stacks.item(Material.RED_MUSHROOM, 64, Strings.getCogumeloVermelho()));
		inv.setItem(15, Stacks.item(Material.BROWN_MUSHROOM, 64, Strings.getCogumeloMarrom()));

		for (int i = 0; i < 32; i++)
			inv.addItem(Stacks.item(Material.MUSHROOM_SOUP));
		player.updateInventory();
	}
}