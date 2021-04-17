package com.github.caaarlowsz.basicpvp.cabeca;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.github.paperspigot.Title;

import com.github.caaarlowsz.basicpvp.guis.MenuGUI;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class CabecasGUI implements Listener {

	@EventHandler
	private void onInventoryClick(InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player && event.getInventory().getName().equals("Cabeças")
				&& event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()
				&& event.getCurrentItem().getItemMeta().hasDisplayName()) {
			String display = event.getCurrentItem().getItemMeta().getDisplayName();
			Player player = (Player) event.getWhoClicked();
			event.setCancelled(true);

			if (display.equals("§7Voltar"))
				MenuGUI.openGUI(player);
			else if (display.equals("§cRemover cabeça")) {
				CabecaAPI.removeCabeca(player);
				player.sendMessage(Strings.getPrefixo() + " §aVocê removeu a Cabeça: " + display);
				player.sendTitle(new Title(display, "§fCabeça removida."));
				player.closeInventory();
			} else if (display.startsWith("§a")) {
				CabecaAPI.setCabeca(player, Cabeca.getByName(ChatColor.stripColor(display)));
				player.sendMessage(Strings.getPrefixo() + " §aVocê selecionou a Cabeça: " + display);
				player.sendTitle(new Title(display, "§fCabeça selecionada."));
				player.closeInventory();
			}
		}
	}

	public static void openGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null, 27, "Cabeças");

		ItemStack glass = Stacks.item(Material.THIN_GLASS, " ");
		for (int i = 0; i < 10; i++)
			inv.setItem(i, glass);
		for (int i = 17; i < 27; i++)
			inv.setItem(i, glass);

		inv.setItem(0, Stacks.item(Material.ARROW, "§7Voltar"));
		inv.setItem(22, Stacks.item(Material.REDSTONE, "§cRemover cabeça", "§7Remove o cosmético aplicado."));

		for (Cabeca cabeca : Cabeca.values()) {
			ItemStack icon = cabeca.getIcon().clone();
			ItemMeta mIcon = icon.getItemMeta();
			List<String> lore = mIcon.getLore();
			lore.add(" ");
			lore.add("§eClique para selecionar");
			mIcon.setLore(lore);
			icon.setItemMeta(mIcon);
			inv.addItem(icon);
		}

		inv.remove(glass);
		player.openInventory(inv);
	}
}