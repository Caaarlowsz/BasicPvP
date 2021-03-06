package com.github.caaarlowsz.basicpvp.cabeca;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.github.paperspigot.Title;

import com.github.caaarlowsz.basicpvp.guis.MenuGUI;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class CabecasGUI implements Listener {

	@EventHandler
	private void onInventoryClick(InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player) {
			Player player = (Player) event.getWhoClicked();
			if (event.getSlotType() == SlotType.ARMOR && event.getSlot() == 39 && CabecaAPI.hasCabeca(player)) {
				event.setCancelled(true);
				CabecaAPI.removeCabeca(player);
				player.playSound(player.getLocation(), Sound.VILLAGER_HAGGLE, 1F, 1F);
				player.sendMessage(Strings.getPrefixo() + " §aVocê removeu a sua Cabeça.");
			}

			if (event.getInventory().getName().equals("Cabeças") && event.getCurrentItem() != null
					&& event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()) {
				String display = event.getCurrentItem().getItemMeta().getDisplayName();
				event.setCancelled(true);

				if (display.equals("§7Voltar"))
					MenuGUI.openGUI(player);
				else if (display.equals("§cRemover cabeça")) {
					CabecaAPI.removeCabeca(player);
					player.sendMessage(Strings.getPrefixo() + " §aVocê removeu a sua Cabeça.");
					player.sendTitle(new Title("§a" + display, "§fCabeça removida.", 5, 10, 5));
					player.closeInventory();
				} else {
					Cabeca cabeca = Cabecas.getByIcon(event.getCurrentItem());
					if (cabeca != null) {
						if (player.hasPermission("kitpvp.vip.cabecas")) {
							CabecaAPI.setCabeca(player, cabeca);
							player.sendMessage(Strings.getPrefixo() + " §aVocê selecionou a Cabeça: " + display);
							player.sendTitle(new Title(display, "§fCabeça selecionada.", 5, 10, 5));
						} else
							player.sendMessage(
									Strings.getPrefixo() + " §cVocê não possui Permissão para usar Cabeças.");
					}
					player.closeInventory();
				}
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
		if (CabecaAPI.hasCabeca(player))
			inv.setItem(22, Stacks.item(Material.REDSTONE, "§cRemover cabeça", "§7Remove o cosmético aplicado."));

		for (Cabeca cabeca : Cabecas.getCabecas())
			inv.addItem(Stacks.applyModel(player.hasPermission("kitpvp.vip.cabecas") ? "modelos.cabeca.selecionar"
					: "modelos.cabeca.sem-permissao", cabeca.getIcon().clone()));

		inv.remove(glass);
		player.openInventory(inv);
	}
}