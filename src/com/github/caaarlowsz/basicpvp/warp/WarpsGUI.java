package com.github.caaarlowsz.basicpvp.warp;

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
import org.github.paperspigot.Title;

import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class WarpsGUI implements Listener {

	public static ItemStack getIcon() {
		return Stacks.getConfigItem("itens.spawn.warps");
	}

	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.hasItem() && event.getItem().isSimilar(getIcon())) {
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
			Player player = (Player) event.getWhoClicked();
			event.setCancelled(true);

			Warp warp = Warps.getByIcon(event.getCurrentItem());
			if (warp != null) {
				WarpAPI.setWarp(player, warp);
				player.sendMessage(
						Strings.getPrefixo() + " §aVocê foi teleportado para a Warp " + warp.getName() + ".");
				player.sendTitle(new Title("§aWarp " + warp.getName(), "§fTeleportado.", 5, 10, 5));
				player.closeInventory();
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

		Warps.getWarps().forEach(warp -> inv.addItem(Stacks.applyModel("modelos.warp", warp.getIcon().clone())));

		inv.remove(glass);
		player.openInventory(inv);
	}
}