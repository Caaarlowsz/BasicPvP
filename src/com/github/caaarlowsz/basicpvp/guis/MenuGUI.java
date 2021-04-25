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

import com.github.caaarlowsz.basicpvp.account.StatusAPI;
import com.github.caaarlowsz.basicpvp.cabeca.CabecasGUI;
import com.github.caaarlowsz.basicpvp.kit.guis.LojaDeKitsGUI;
import com.github.caaarlowsz.basicpvp.tag.TagAPI;
import com.github.caaarlowsz.basicpvp.utils.Stacks;

public final class MenuGUI implements Listener {

	public static final ItemStack ICON = Stacks.item(Material.NETHER_STAR, "§aMenu");

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
		if (event.getWhoClicked() instanceof Player && event.getInventory().getName().equals("Menu")
				&& event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()
				&& event.getCurrentItem().getItemMeta().hasDisplayName()) {
			String display = event.getCurrentItem().getItemMeta().getDisplayName();
			Player player = (Player) event.getWhoClicked();
			event.setCancelled(true);

			if (display.equals("§aLoja de Kits"))
				LojaDeKitsGUI.openGUI(player);
			else if (display.equals("§aCabeças"))
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

		inv.setItem(10,
				Stacks.skull(1, player.getName(), "§aSeu Perfil", " ",
						"  §fCargo: " + TagAPI.getMaxTag(player).getColoredName() + "   ",
						"  §fMoedas: §6" + StatusAPI.getMoedas(player) + "   ", " ",
						"  §fRank: " + StatusAPI.getRank(player).getColoredSymbolName() + "   ",
						"  §fXP: §b" + StatusAPI.getXP(player) + "   ", " ",
						" §fKillStreak: §7" + StatusAPI.getKillStreak(player) + "   ",
						" §fAbates: §7" + StatusAPI.getAbates(player) + "   ",
						" §fMortes: §7" + StatusAPI.getMortes(player) + "   ", " "));

		inv.setItem(12, Stacks.item(Material.EMERALD, "§aLoja de Kits", "§7Compre Kits usando as moedas",
				"§7que adquiriu jogando."));

		inv.setItem(14, Stacks.item(Material.LEATHER_HELMET, "§aCabeças", "§7Escolha um bloco e deixe",
				"§7sua cabeça customizada."));

		inv.setItem(16,
				Stacks.item(Material.BOOK, "§aRequisitos para §bYouTuber" + "§a:", " ", " §7Requisitos do canal:",
						"  §7Mínimo de §f1.000 inscritos§7.", "  §7Necessário ser um §fcanal ativo§7.",
						"  §7Gravar um §fvídeo no servidor§7.", " §7Requisitos do vídeo:",
						"  §7Mínimo de §f300 visualizações§7.", "  §7Mínimo de §f50 gostei§7.",
						"  §fIP do servidor na descrição§7."));

		inv.remove(glass);
		player.openInventory(inv);
	}
}