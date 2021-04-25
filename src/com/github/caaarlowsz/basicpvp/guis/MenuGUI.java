package com.github.caaarlowsz.basicpvp.guis;

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

import com.github.caaarlowsz.basicpvp.cabeca.CabecasGUI;
import com.github.caaarlowsz.basicpvp.kit.guis.LojaDeKitsGUI;
import com.github.caaarlowsz.basicpvp.utils.Stacks;

public final class MenuGUI implements Listener {

	public static ItemStack getIcon() {
		return Stacks.getConfigItem("itens.spawn.menu");
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
		if (event.getWhoClicked() instanceof Player && event.getInventory().getName().equals("Menu")
				&& event.getCurrentItem() != null) {
			ItemStack item = event.getCurrentItem();
			Player player = (Player) event.getWhoClicked();
			event.setCancelled(true);

			if (item.isSimilar(Stacks.getConfigItem("inventarios.menu-geral.loja-de-kits")))
				LojaDeKitsGUI.openGUI(player);
			else if (item.isSimilar(Stacks.getConfigItem("inventarios.menu-geral.cabecas")))
				CabecasGUI.openGUI(player);
		}
	}

	public static void openGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null, 27, "Menu");

		ItemStack glass = Stacks.item(Material.THIN_GLASS, " ");
		for (int i = 0; i < 10; i++)
			inv.setItem(i, glass);
		for (int i = 17; i < 27; i++)
			inv.setItem(i, glass);

		inv.setItem(Stacks.getSlotConfigItem("inventarios.menu-geral.perfil"),
				Stacks.getConfigItem("inventarios.menu-geral.perfil"));

		inv.setItem(Stacks.getSlotConfigItem("inventarios.menu-geral.loja-de-kits"),
				Stacks.getConfigItem("inventarios.menu-geral.loja-de-kits"));

		inv.setItem(Stacks.getSlotConfigItem("inventarios.menu-geral.cabecas"),
				Stacks.getConfigItem("inventarios.menu-geral.cabecas"));

		inv.setItem(Stacks.getSlotConfigItem("inventarios.menu-geral.requisitos-youtuber"),
				Stacks.getConfigItem("inventarios.menu-geral.requisitos-youtuber"));

		inv.remove(glass);
		player.openInventory(inv);
	}
}