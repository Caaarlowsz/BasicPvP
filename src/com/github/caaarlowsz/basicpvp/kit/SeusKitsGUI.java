package com.github.caaarlowsz.basicpvp.kit;

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

public final class SeusKitsGUI implements Listener {

	public static final ItemStack ICON = Stacks.item(Material.CHEST, "§aSeus Kits");

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
		if (event.getWhoClicked() instanceof Player && event.getInventory().getName().equals("Seus Kits")
				&& event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()
				&& event.getCurrentItem().getItemMeta().hasDisplayName()) {
			String display = event.getCurrentItem().getItemMeta().getDisplayName();
			Player player = (Player) event.getWhoClicked();
			event.setCancelled(true);

			if (display.startsWith("§aKit ")) {
				Kit kit = Kits.getByName(display.replace("§aKit ", ""));
				if (kit != null) {
					KitAPI.setKit(player, kit);
					player.sendMessage(Strings.getPrefixo() + " §aVocê selecionou o Kit " + kit.getName() + ".");
					player.sendTitle(new Title("§aKit " + kit.getName(), "§fSelecionado."));
					player.closeInventory();
				}
			}
		}
	}

	public static void openGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null, 45, "Seus Kits");

		ItemStack glass = Stacks.item(Material.THIN_GLASS, " ");
		for (int i = 0; i < 10; i++)
			inv.setItem(i, glass);
		inv.setItem(17, glass);
		inv.setItem(18, glass);
		inv.setItem(26, glass);
		inv.setItem(27, glass);
		for (int i = 35; i < 45; i++)
			inv.setItem(i, glass);

		Kits.getKits().stream().filter(kit -> kit.getName().equals(Kits.getDefaultKit())
				|| player.hasPermission("kitpvp.kit." + kit.getName())).forEach(kit -> {
					ItemStack icon = kit.getIcon().clone();
					ItemMeta mIcon = icon.getItemMeta();
					List<String> lore = mIcon.getLore();
					lore.add(" ");
					lore.add("§eClique para selecionar");
					mIcon.setLore(lore);
					icon.setItemMeta(mIcon);
				});

		inv.remove(glass);
		player.openInventory(inv);
	}
}