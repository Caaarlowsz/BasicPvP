package com.github.caaarlowsz.basicpvp.warp.warps;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.PlayerInventory;
import org.github.paperspigot.Title;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.player.PlayerAPI;
import com.github.caaarlowsz.basicpvp.player.Status;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.Warp;
import com.github.caaarlowsz.basicpvp.warp.WarpAPI;

public final class LavaChallengeWarp extends Warp implements Listener {

	public LavaChallengeWarp() {
		super("Lava Challenge");
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

	private List<String> getSignChallenge(String level) {
		FileConfiguration config = BasicKitPvP.getInstance().getConfig();
		ArrayList<String> lines = new ArrayList<>();
		config.getStringList("placas.desafios." + level)
				.forEach(line -> lines.add(ChatColor.translateAlternateColorCodes('&', line)
						.replace("{nome}", Strings.getNome()).replace("{prefixo}", Strings.getPrefixo())
						.replace("{website}", Strings.getWebsite()).replace("{loja}", Strings.getLoja())
						.replace("{discord}", Strings.getDiscord()).replace("{nome}", Strings.getNome())));
		return lines;
	}

	@EventHandler
	private void onSignChange(SignChangeEvent event) {
		if (event.getLine(0).equalsIgnoreCase("Facil")) {
			List<String> lines = getSignChallenge("facil");
			for (int index = 0; index < 4; index++)
				if (lines.size() > index - 1)
					event.setLine(index, lines.get(index));
		} else if (event.getLine(0).equalsIgnoreCase("Medio")) {
			List<String> lines = getSignChallenge("medio");
			for (int index = 0; index < 4; index++)
				if (lines.size() > index - 1)
					event.setLine(index, lines.get(index));
		} else if (event.getLine(0).equalsIgnoreCase("Dificil")) {
			List<String> lines = getSignChallenge("dificil");
			for (int index = 0; index < 4; index++)
				if (lines.size() > index - 1)
					event.setLine(index, lines.get(index));
		} else if (event.getLine(0).equalsIgnoreCase("Extremo")) {
			List<String> lines = getSignChallenge("extremo");
			for (int index = 0; index < 4; index++)
				if (lines.size() > index - 1)
					event.setLine(index, lines.get(index));
		}
	}

	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (WarpAPI.getWarp(player) instanceof LavaChallengeWarp && event.hasBlock()
				&& event.getClickedBlock().getType().name().contains("SIGN")
				&& event.getAction().name().contains("RIGHT")) {
			Sign sign = (Sign) event.getClickedBlock().getState();
			Status status = PlayerAPI.getStatus(player);
			List<String> facil = this.getSignChallenge("facil"), medio = this.getSignChallenge("medio"),
					dificil = this.getSignChallenge("dificil"), extremo = this.getSignChallenge("extremo");

			if (sign.getLine(0).equals(facil.get(0)) && sign.getLine(1).equals(facil.get(1))
					&& sign.getLine(2).equals(facil.get(2)) && sign.getLine(3).equals(facil.get(3))) {
				WarpAPI.setWarp(player, this);

				if (Strings.announceDesafioFacil())
					Bukkit.broadcastMessage(
							Strings.getPrefixo() + " §a" + player.getName() + " completou o desafio §lFÁCIL§a.");

				int moedas = Strings.getDesafioFacilMoedas(), xp = Strings.getDesafioFacilXP();
				status.addMoedas(moedas);
				if (Strings.sendMoedasMessage() && moedas > 0)
					player.sendMessage("§6+" + moedas + " Moedas");
				status.addXP(xp);
				if (Strings.sendXPMessage() && xp > 0)
					player.sendMessage("§b+" + xp + " XP");
				player.sendMessage(Strings.getPrefixo() + " §aVocê completou o desafio Fácil.");
				player.sendTitle(new Title("§aFácil", "§fDesafio completo!", 15, 20, 15));
			} else if (sign.getLine(0).equals(medio.get(0)) && sign.getLine(1).equals(medio.get(1))
					&& sign.getLine(2).equals(medio.get(2)) && sign.getLine(3).equals(medio.get(3))) {
				WarpAPI.setWarp(player, this);

				if (Strings.announceDesafioMedio())
					Bukkit.broadcastMessage(
							Strings.getPrefixo() + " §a" + player.getName() + " completou o desafio §lMÉDIO§a.");

				int moedas = Strings.getDesafioMedioMoedas(), xp = Strings.getDesafioMedioXP();
				status.addMoedas(moedas);
				if (Strings.sendMoedasMessage() && moedas > 0)
					player.sendMessage("§6+" + moedas + " Moedas");
				status.addXP(xp);
				if (Strings.sendXPMessage() && xp > 0)
					player.sendMessage("§b+" + xp + " XP");
				player.sendMessage(Strings.getPrefixo() + " §aVocê completou o desafio Médio.");
				player.sendTitle(new Title("§aMédio", "§fDesafio completo!", 15, 20, 15));
			} else if (sign.getLine(0).equals(dificil.get(0)) && sign.getLine(1).equals(dificil.get(1))
					&& sign.getLine(2).equals(dificil.get(2)) && sign.getLine(3).equals(dificil.get(3))) {
				WarpAPI.setWarp(player, this);

				if (Strings.announceDesafioDificil()) {
					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage(
							Strings.getPrefixo() + " §a" + player.getName() + " completou o desafio §lDIFÍCIL§a.");
					Bukkit.broadcastMessage(" ");
				}

				int moedas = Strings.getDesafioDificilMoedas(), xp = Strings.getDesafioDificilXP();
				status.addMoedas(moedas);
				if (Strings.sendMoedasMessage() && moedas > 0)
					player.sendMessage("§6+" + moedas + " Moedas");
				status.addXP(xp);
				if (Strings.sendXPMessage() && xp > 0)
					player.sendMessage("§b+" + xp + " XP");
				player.sendMessage(Strings.getPrefixo() + " §aVocê completou o desafio Difícil.");
				player.sendTitle(new Title("§aDifícil", "§fDesafio completo!", 15, 20, 15));
			} else if (sign.getLine(0).equals(extremo.get(0)) && sign.getLine(1).equals(extremo.get(1))
					&& sign.getLine(2).equals(extremo.get(2)) && sign.getLine(3).equals(extremo.get(3))) {
				WarpAPI.setWarp(player, this);

				if (Strings.announceDesafioExtremo()) {
					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage(
							Strings.getPrefixo() + " §a" + player.getName() + " completou o desafio §lEXTREMO§a.");
					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage(" ");
				}

				int moedas = Strings.getDesafioExtremoMoedas(), xp = Strings.getDesafioExtremoXP();
				status.addMoedas(moedas);
				if (Strings.sendMoedasMessage() && moedas > 0)
					player.sendMessage("§6+" + moedas + " Moedas");
				status.addXP(xp);
				if (Strings.sendXPMessage() && xp > 0)
					player.sendMessage("§b+" + xp + " XP");
				player.sendMessage(Strings.getPrefixo() + " §aVocê completou o desafio Extremo.");
				player.sendTitle(new Title("§aExtremo", "§fDesafio completo!", 15, 20, 15));
			}
		}
	}
}