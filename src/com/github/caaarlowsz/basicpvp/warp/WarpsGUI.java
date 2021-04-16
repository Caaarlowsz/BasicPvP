package com.github.caaarlowsz.basicpvp.warp;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.github.paperspigot.Title;

import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class WarpsGUI implements Listener {

	public static final ItemStack ICON = Stacks.item(Material.COMPASS, "§aWarps");

	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.hasItem() && event.getItem().isSimilar(ICON)) {
			event.setCancelled(true);
			if (event.getAction().name().contains("RIGHT")) {
				player.playSound(player.getLocation(), Sound.DOOR_OPEN, 5F, 5F);
				openGUI(player);
			}
		}
	}

	@EventHandler
	private void onInventoryClick(InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player && event.getInventory().getName().equals("Warps")
				&& event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()
				&& event.getCurrentItem().getItemMeta().hasDisplayName()) {
			String display = event.getCurrentItem().getItemMeta().getDisplayName();
			Player player = (Player) event.getWhoClicked();
			event.setCancelled(true);

			if (display.startsWith("§aWarp ")) {
				Warp warp = Warps.getByName(display.replace("§aWarp ", ""));
				if (warp != null) {
					WarpAPI.setWarp(player, warp);
					player.sendMessage(
							Strings.getPrefixo() + " §aVocê foi teleportado para a Warp " + warp.getName() + ".");
					player.sendTitle(new Title("§aWarp " + warp.getName(), "§fTeleportado."));
					player.closeInventory();
				}
			}
		}
	}

	public static void openGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null, 27, "Warps");

		ItemStack glass = Stacks.item(Material.THIN_GLASS, " ");
		for (int i = 0; i < 10; i++)
			inv.setItem(i, glass);
		for (int i = 17; i < 27; i++)
			inv.setItem(i, glass);

		Warps.getWarps().forEach(warp -> {
			ItemStack icon = warp.getIcon().clone();
			ItemMeta mIcon = icon.getItemMeta();
			List<String> lore = mIcon.getLore();
			lore.add(" ");
			lore.add("§eClique para teleportar");
			mIcon.setLore(lore);
			icon.setItemMeta(mIcon);
		});

		inv.remove(glass);
		player.openInventory(inv);
	}
}