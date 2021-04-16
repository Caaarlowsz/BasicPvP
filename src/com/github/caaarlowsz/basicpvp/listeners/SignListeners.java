package com.github.caaarlowsz.basicpvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import com.github.caaarlowsz.basicpvp.utils.Stacks;

public final class SignListeners implements Listener {

	@EventHandler
	private void onSignChang(SignChangeEvent event) {
		if (event.getLine(0).equalsIgnoreCase("Recraft")) {
			event.setLine(0, " ");
			event.setLine(1, "§a§lRECRAFT");
			event.setLine(2, "(§7Clique)");
			event.setLine(3, " ");
		} else if (event.getLine(0).equalsIgnoreCase("Sopas")) {
			event.setLine(0, " ");
			event.setLine(1, "§a§lRECRAFT");
			event.setLine(2, "§7(Clique)");
			event.setLine(3, " ");
		}
	}

	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.hasBlock() && event.getClickedBlock().getType().name().contains("SIGN")
				&& event.getAction().name().contains("RIGHT")) {
			Sign sign = (Sign) event.getClickedBlock().getState();
			if (sign.getLine(0).equals(" ") && sign.getLine(2).equals("§7(Clique)") && sign.getLine(3).equals(" ")) {
				if (sign.getLine(1).equals("§a§lRECRAFT")) {
					Inventory inv = Bukkit.createInventory(null, 36, "Recraft");

					for (int i = 0; i < 12; i++) {
						inv.addItem(Stacks.item(Material.BOWL, 64, "§aPote"));
						inv.addItem(Stacks.item(Material.RED_MUSHROOM, 64, "§aCogumelo"));
						inv.addItem(Stacks.item(Material.BROWN_MUSHROOM, 64, "§aCogumelo"));
					}

					player.openInventory(inv);
				} else if (sign.getLine(1).equals("§a§lSOPAS")) {
					Inventory inv = Bukkit.createInventory(null, 36, "Sopas");

					for (int i = 0; i < 36; i++)
						inv.addItem(Stacks.item(Material.MUSHROOM_SOUP));

					player.openInventory(inv);
				}
			}
		}
	}
}