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

import com.github.caaarlowsz.basicpvp.account.StatusFile;
import com.github.caaarlowsz.basicpvp.guis.MenuGUI;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.Kits;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;

import ru.tehkode.permissions.bukkit.PermissionsEx;

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
			else {
				Kit kit = Kits.getByIcon(event.getCurrentItem());
				if (kit != null) {
					if (StatusFile.getMoedas(player) >= kit.getPrice()) {
						PermissionsEx.getUser(player.getName())
								.addPermission("kitpvp.kit." + kit.getName().toLowerCase());
						player.sendTitle(new Title("§aKit " + kit.getName(), "§fComprado.", 15, 20, 15));
						player.sendMessage(Strings.getPrefixo() + " §aVocê comprou o Kit " + kit.getName() + ".");
					} else
						player.sendMessage(Strings.getPrefixo()
								+ " §cVocê não possui Moedas suficientes para comprar o Kit " + kit.getName() + ".");
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