package com.github.caaarlowsz.basicpvp.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class SignListeners implements Listener {

	private List<String> getSign(String name) {
		FileConfiguration config = BasicKitPvP.getInstance().getConfig();
		ArrayList<String> lines = new ArrayList<>();
		config.getStringList("placas." + name)
				.forEach(line -> lines.add(ChatColor.translateAlternateColorCodes('&', line)
						.replace("{nome}", Strings.getNome()).replace("{prefixo}", Strings.getPrefixo())
						.replace("{website}", Strings.getWebsite()).replace("{loja}", Strings.getLoja())
						.replace("{discord}", Strings.getDiscord()).replace("{nome}", Strings.getNome())));
		return lines;
	}

	@EventHandler
	private void onSignChang(SignChangeEvent event) {
		if (event.getLine(0).equalsIgnoreCase("Recraft")) {
			List<String> lines = this.getSign("recraft");
			for (int index = 0; index < 4; index++)
				if (lines.size() > index - 1)
					event.setLine(index, lines.get(index));
		} else if (event.getLine(0).equalsIgnoreCase("Sopas")) {
			List<String> lines = this.getSign("sopas");
			for (int index = 0; index < 4; index++)
				if (lines.size() > index - 1)
					event.setLine(index, lines.get(index));
		}
	}

	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.hasBlock() && event.getClickedBlock().getType().name().contains("SIGN")
				&& event.getAction().name().contains("RIGHT")) {
			Sign sign = (Sign) event.getClickedBlock().getState();
			List<String> recraft = this.getSign("recraft"), sopas = this.getSign("sopas");
			if (sign.getLine(0).equals(recraft.get(0)) && sign.getLine(1).equals(recraft.get(1))
					&& sign.getLine(2).equals(recraft.get(2)) && sign.getLine(3).equals(recraft.get(3))) {
				String align = BasicKitPvP.getInstance().getConfig().getString("placas.recraft-align");
				if (align.equalsIgnoreCase("HORIZONTAL")) {
					Inventory inv = Bukkit.createInventory(null, 36, "Recraft");

					for (int i = 0; i < 12; i++) {
						inv.addItem(Stacks.item(Material.BOWL, 64, Strings.getPote()));
						inv.addItem(Stacks.item(Material.RED_MUSHROOM, 64, Strings.getCogumeloVermelho()));
						inv.addItem(Stacks.item(Material.BROWN_MUSHROOM, 64, Strings.getCogumeloMarrom()));
					}

					player.openInventory(inv);
				} else if (align.equalsIgnoreCase("VERTICAL")) {
					Inventory inv = Bukkit.createInventory(null, 27, "Recraft");

					for (int i = 0; i < 9; i++) {
						inv.setItem(i, Stacks.item(Material.BOWL, 64, Strings.getPote()));
						inv.setItem(i + 9, Stacks.item(Material.RED_MUSHROOM, 64, Strings.getCogumeloVermelho()));
						inv.setItem(i + 18, Stacks.item(Material.BROWN_MUSHROOM, 64, Strings.getCogumeloMarrom()));
					}

					player.openInventory(inv);
				}
			}
			if (sign.getLine(0).equals(sopas.get(0)) && sign.getLine(1).equals(sopas.get(1))
					&& sign.getLine(2).equals(sopas.get(2)) && sign.getLine(3).equals(sopas.get(3))) {
				Inventory inv = Bukkit.createInventory(null, 36, "Sopas");

				for (int i = 0; i < 36; i++)
					inv.addItem(Stacks.item(Material.MUSHROOM_SOUP));

				player.openInventory(inv);
			}
		}
	}
}