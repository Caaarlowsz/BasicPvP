package com.github.caaarlowsz.basicpvp.kit.guis;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
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
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.kit.Kits;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public class LojaDeKitsGUI implements Listener {

	@EventHandler
	private void onInventoryClick(InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player && event.getInventory().getName().equals("Loja de Kits")
				&& event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()
				&& event.getCurrentItem().getItemMeta().hasDisplayName()) {
			String display = event.getCurrentItem().getItemMeta().getDisplayName();
			Player player = (Player) event.getWhoClicked();
			event.setCancelled(true);

			if (display.equals("§7Voltar"))
				MenuGUI.openGUI(player);
			else if (display.startsWith(Strings.getCorPrincipal() + "Kit ")) {
				Kit kit = Kits.getByName(display.replace(Strings.getCorPrincipal() + "Kit ", ""));
				if (kit != null) {
					KitAPI.setKit(player, kit);
					player.sendMessage(Strings.getPrefixo() + " §aVocê selecionou o Kit " + kit.getName() + ".");
					player.sendTitle(
							new Title(Strings.getCorPrincipal() + "Kit " + kit.getName(), "§fSelecionado.", 5, 10, 5));
					player.closeInventory();
				}
			}
		}
	}

	public static void openGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null, 45, "Loja de Kits");

		ItemStack glass = Stacks.item(Material.THIN_GLASS, " ");
		for (int i = 0; i < 10; i++)
			inv.setItem(i, glass);
		inv.setItem(17, glass);
		inv.setItem(18, glass);
		inv.setItem(26, glass);
		inv.setItem(27, glass);
		for (int i = 35; i < 45; i++)
			inv.setItem(i, glass);

		inv.setItem(0, Stacks.item(Material.ARROW, "§7Voltar"));

		ArrayList<Kit> kits = new ArrayList<>();
		Kits.getKits().stream()
				.filter(kit -> kit.getPrice() > 0 && !player.hasPermission("kitpvp.kit." + kit.getName()))
				.forEach(kit -> kits.add(kit));
		if (kits.size() > 0) {
			kits.forEach(kit -> {
				ItemStack icon = kit.getIcon().clone();
				ItemMeta mIcon = icon.getItemMeta();
				List<String> lore = mIcon.getLore();
				lore.add(" ");
				lore.add("§7Preço: §f" + new DecimalFormat().format(kit.getPrice()).replace(",", ".") + " Moedas§7.");
				lore.add("§eClique para comprar");
				mIcon.setLore(lore);
				icon.setItemMeta(mIcon);
				inv.addItem(icon);
			});
		} else
			inv.setItem(22, Stacks.item(Material.STAINED_GLASS_PANE, 1, 14, "§cVocê já possui todos os Kits!"));

		inv.remove(glass);
		player.openInventory(inv);
	}
}