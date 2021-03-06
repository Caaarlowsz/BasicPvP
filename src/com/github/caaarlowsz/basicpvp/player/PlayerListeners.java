package com.github.caaarlowsz.basicpvp.player;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.github.paperspigot.Title;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.apis.ChatAPI;
import com.github.caaarlowsz.basicpvp.apis.StaffAPI;
import com.github.caaarlowsz.basicpvp.apis.TabAPI;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.sidebar.SidebarAPI;
import com.github.caaarlowsz.basicpvp.tag.Tag;
import com.github.caaarlowsz.basicpvp.tag.TagAPI;
import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.WarpAPI;
import com.github.caaarlowsz.basicpvp.warp.Warps;
import com.github.caaarlowsz.basicpvp.warp.warps.UMvUMWarp;

import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;

public final class PlayerListeners implements Listener {

	@EventHandler
	private void onPlayerLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		if (event.getResult() == Result.KICK_WHITELIST)
			event.setKickMessage(Strings.getPrefixo() + " §cEstamos em manutenção.");
		else if (event.getResult() == Result.KICK_FULL)
			if (player.hasPermission("kitpvp.vip.joinfull"))
				event.setResult(Result.ALLOWED);
			else
				event.setKickMessage(Strings.getPrefixo() + " §cO Servidor está lotado.");
	}

	@EventHandler
	private void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage(null);
		player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

		if (!StatusData.hasAccount(player.getName()))
			StatusData.createAccount(player.getName());
		PlayerAPI.setStatus(player, StatusData.loadAccount(player.getName()));

		ChatAPI.removeAntiFlood(player);
		WarpAPI.setWarp(player, Warps.getDefaultWarp());

		if (!player.hasPermission("kitpvp.permission.viewadmins"))
			Bukkit.getOnlinePlayers().stream().filter(players -> StaffAPI.hasAdmin(players))
					.forEach(players -> player.hidePlayer(players));

		for (Tag tag : Tag.values())
			if (tag.isDefaultTag() || player.hasPermission("kitpvp.tag." + tag.getName())) {
				TagAPI.setTag(player, tag);
				break;
			}

		TabAPI.sendTab(player, "\n     " + Strings.getNome() + "     \n     §fServidor de KitPvP 1.7x & 1.8x     \n",
				"\n     " + "§aWebsite: §f" + Strings.getWebsite() + "     \n     " + "§aLoja: §f" + Strings.getLoja()
						+ "     \n     " + "§aDiscord: §f" + Strings.getDiscord() + "      \n");

		player.playSound(player.getLocation(), Sound.LEVEL_UP, 10F, 0F);
		for (int i = 0; i < 100; i++)
			player.sendMessage(" ");
		player.sendMessage(Strings.getPrefixo() + " §aSeja bem-vindo(a) " + player.getName() + ".");
		player.sendMessage(" ");
		player.sendTitle(new Title(Strings.getNome(), "§fConectado", 15, 20, 15));
	}

	@EventHandler
	private void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		event.setDeathMessage(null);

		Player killer = player.getKiller();
		if (!(WarpAPI.getWarp(player) instanceof UMvUMWarp) && killer != null && killer != player) {
			Status pStatus = PlayerAPI.getStatus(player);
			pStatus.resetKillStreak();
			pStatus.addMorte();
			player.playSound(player.getLocation(), Sound.ANVIL_USE, 10F, 1F);

			int pMoedas = Strings.getMorrerMoedas(), pXP = Strings.getMorrerXP();
			pStatus.drawMoedas(pMoedas);
			if (Strings.sendMoedasMessage() && pMoedas > 0)
				player.sendMessage("§6-" + pMoedas + " Moedas");

			pStatus.drawXP(pXP);
			if (Strings.sendXPMessage() && pXP > 0)
				player.sendMessage("§b-" + pXP + " XP");
			player.sendMessage(Strings.getPrefixo() + " §cVocê foi morto por " + killer.getName() + ".");

			Status kStatus = PlayerAPI.getStatus(killer);
			kStatus.addKillStreak();
			kStatus.addAbate();
			killer.playSound(killer.getLocation(), Sound.ARROW_HIT, 10F, 1F);

			int kMoedas = Strings.getMatarMoedas(), kXP = Strings.getMatarXP();
			kStatus.addMoedas(kMoedas);
			if (Strings.sendMoedasMessage() && kMoedas > 0)
				killer.sendMessage("§6+" + kMoedas + " Moedas");

			kStatus.addXP(kXP);
			if (Strings.sendXPMessage() && kXP > 0)
				killer.sendMessage("§b+" + kXP + " XP");
			killer.sendMessage(Strings.getPrefixo() + " §aVocê matou " + player.getName() + ".");
		}

		Bukkit.getScheduler().runTaskLater(BasicKitPvP.getInstance(),
				() -> ((CraftPlayer) player).getHandle().playerConnection
						.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN)),
				1L);
	}

	@EventHandler
	private void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		Bukkit.getScheduler().runTaskLater(BasicKitPvP.getInstance(), () -> {
			WarpAPI.setWarp(player, Warps.getDefaultWarp());
			player.sendTitle(new Title(Strings.getNome(), "§fVocê morreu", 15, 20, 15));
		}, 1L);
	}

	@EventHandler
	private void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		event.setQuitMessage(null);

		ChatAPI.removeAntiFlood(player);
		KitAPI.removeKit(player);
		PlayerAPI.removeTellOff(player);
		PlayerAPI.removeStatus(player);
		SidebarAPI.removeSidebar(player);
		StaffAPI.removeAdmin(player);
		StaffAPI.removeBuild(player);
		TagAPI.removeTag(player);
		WarpAPI.removeWarp(player);
	}
}