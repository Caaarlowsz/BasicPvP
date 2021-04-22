package com.github.caaarlowsz.basicpvp.warp.warps;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.PlayerInventory;
import org.github.paperspigot.Title;

import com.github.caaarlowsz.basicpvp.account.StatusFile;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.Warp;
import com.github.caaarlowsz.basicpvp.warp.WarpAPI;

public final class LavaChallengeWarp extends Warp implements Listener {

	public LavaChallengeWarp() {
		super("Lava Challenge", Stacks.item(Material.LAVA_BUCKET, Strings.getCorPrincipal() + "Warp Lava Challenge",
				"§7Treine seu refil e seu recraft", "§7enquanto completa desafios."));
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.setItem(0, Stacks.item(Material.STONE_SWORD,
				Arrays.asList(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE), Strings.getEspada()));

		inv.setItem(13, Stacks.item(Material.BOWL, 64, Strings.getPote()));
		inv.setItem(14, Stacks.item(Material.RED_MUSHROOM, 64, Strings.getCogumeloVermelho()));
		inv.setItem(15, Stacks.item(Material.BROWN_MUSHROOM, 64, Strings.getCogumeloMarrom()));

		for (int i = 0; i < 32; i++)
			inv.addItem(Stacks.item(Material.MUSHROOM_SOUP));
		player.updateInventory();
	}

	@EventHandler
	private void onSignChange(SignChangeEvent event) {
		if (event.getLine(0).equalsIgnoreCase("Facil")) {
			event.setLine(0, Strings.getCorPrincipal() + "§lDESAFIO:");
			event.setLine(1, "§aFácil");
			event.setLine(2, "§7(Clique)");
			event.setLine(3, Strings.getCorPrincipal() + "§lCOMPLETO!");
		} else if (event.getLine(0).equalsIgnoreCase("Medio")) {
			event.setLine(0, Strings.getCorPrincipal() + "§lDESAFIO:");
			event.setLine(1, "§eMédio");
			event.setLine(2, "§7(Clique)");
			event.setLine(3, Strings.getCorPrincipal() + "§lCOMPLETO!");
		} else if (event.getLine(0).equalsIgnoreCase("Dificil")) {
			event.setLine(0, Strings.getCorPrincipal() + "§lDESAFIO:");
			event.setLine(1, "§cDifícil");
			event.setLine(2, "§7(Clique)");
			event.setLine(3, Strings.getCorPrincipal() + "§lCOMPLETO!");
		} else if (event.getLine(0).equalsIgnoreCase("Extremo")) {
			event.setLine(0, Strings.getCorPrincipal() + "§lDESAFIO:");
			event.setLine(1, "§8Extremo");
			event.setLine(2, "§7(Clique)");
			event.setLine(3, Strings.getCorPrincipal() + "§lCOMPLETO!");
		}
	}

	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (WarpAPI.getWarp(player) instanceof LavaChallengeWarp && event.hasBlock()
				&& event.getClickedBlock().getType().name().contains("SIGN")
				&& event.getAction().name().contains("RIGHT")) {
			Sign sign = (Sign) event.getClickedBlock().getState();

			if (sign.getLine(0).equals(Strings.getCorPrincipal() + "§lDESAFIO:") && sign.getLine(2).equals("§7(Clique)")
					&& sign.getLine(3).equals(Strings.getCorPrincipal() + "§lCOMPLETO!")) {
				if (sign.getLine(1).equals("§aFácil")) {
					WarpAPI.setWarp(player, this);

					StatusFile.addMoedas(player, 5);
					player.sendMessage("§6+5 Moedas");
					player.sendMessage(Strings.getPrefixo() + " §aVocê completou o desafio Fácil.");
					player.sendTitle(new Title("§aFácil", "§fDesafio completo!", 15, 20, 15));
				} else if (sign.getLine(1).equals("§eMédio")) {
					WarpAPI.setWarp(player, this);

					StatusFile.addMoedas(player, 10);
					player.sendMessage("§6+10 Moedas");
					StatusFile.addXP(player, 1);
					player.sendMessage("§b+1 XP");
					player.sendMessage(Strings.getPrefixo() + " §aVocê completou o desafio §eMédio§a.");
					player.sendTitle(new Title("§eMédio", "§fDesafio completo!", 15, 20, 15));
				} else if (sign.getLine(1).equals("§cDifícil")) {
					WarpAPI.setWarp(player, this);

					StatusFile.addMoedas(player, 15);
					player.sendMessage("§6+15 Moedas");
					StatusFile.addXP(player, 3);
					player.sendMessage("§b+3 XP");
					player.sendMessage(Strings.getPrefixo() + " §aVocê completou o desafio §cDifícil§a.");
					player.sendTitle(new Title("§cDifícil", "§fDesafio completo!", 15, 20, 15));
				} else if (sign.getLine(1).equals("§8Extremo")) {
					WarpAPI.setWarp(player, this);

					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage(
							Strings.getPrefixo() + " §a" + player.getName() + " completou o desafio §8§lEXTREMO§a.");
					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage(" ");

					StatusFile.addMoedas(player, 25);
					player.sendMessage("§6+25 Moedas");
					StatusFile.addXP(player, 5);
					player.sendMessage("§b+5 XP");
					player.sendMessage(Strings.getPrefixo() + " §aVocê completou o desafio §8Extremo§a.");
					player.sendTitle(new Title("§8Extremo", "§fDesafio completo!", 15, 20, 15));
				}
			}
		}
	}
}