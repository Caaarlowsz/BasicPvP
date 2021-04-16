package com.github.caaarlowsz.basicpvp.kit;

import java.util.Arrays;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.utils.Stacks;

public class Kit {

	private final String name;
	private final ItemStack icon;

	public Kit(String name, ItemStack icon) {
		this.name = name;
		this.icon = icon;
	}

	public String getName() {
		return this.name;
	}

	public ItemStack getIcon() {
		return this.icon;
	}

	public void giveItems(Player player) {
		player.setGameMode(GameMode.SURVIVAL);
		player.setAllowFlight(false);
		player.setFlying(false);
		player.setHealthScale(20D);
		player.setMaxHealth(20D);
		player.setHealth(20D);

		PlayerInventory inv = player.getInventory();
		inv.setArmorContents(null);
		inv.clear();

		inv.setChestplate(
				Stacks.item(Material.LEATHER_CHESTPLATE, true, Arrays.asList(ItemFlag.HIDE_UNBREAKABLE), "§aPeitoral"));
		inv.setItem(0, Stacks.item(Material.STONE_SWORD, true,
				Arrays.asList(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE), "§aEspada"));
		inv.setItem(8, Stacks.item(Material.COMPASS, "§aBússola"));

		inv.setItem(13, Stacks.item(Material.BOWL, 64, "§aPote"));
		inv.setItem(14, Stacks.item(Material.RED_MUSHROOM, 64, "§aCogumelo"));
		inv.setItem(15, Stacks.item(Material.BROWN_MUSHROOM, 64, "§aCogumelo"));

		for (int i = 0; i < 32; i++)
			inv.addItem(Stacks.item(Material.MUSHROOM_SOUP));
	}
}