package com.github.caaarlowsz.basicpvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.github.paperspigot.Title;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.WarpAPI;
import com.github.caaarlowsz.basicpvp.warp.Warps;

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

		player.playSound(player.getLocation(), Sound.LEVEL_UP, 10F, 0F);
		for (int i = 0; i < 100; i++)
			player.sendMessage(" ");
		player.sendMessage(Strings.getPrefixo() + " §aSeja bem-vindo(a) " + player.getName() + ".");
		player.sendMessage(" ");
		player.sendTitle(new Title(Strings.getNome(), "§fConectado"));
	}

	@EventHandler
	private void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		event.setDeathMessage(null);

		Player killer = player.getKiller();
		if (!WarpAPI.getWarp(player).equals("1v1") && killer != null && killer != player) {
			player.playSound(player.getLocation(), Sound.ANVIL_USE, 10F, 1F);
			player.sendMessage("§b-1 XP");
			player.sendMessage("§6-5 Moedas");
			player.sendMessage(Strings.getPrefixo() + " §cVocê foi morto por " + killer.getName() + ".");

			killer.playSound(killer.getLocation(), Sound.ARROW_HIT, 10F, 1F);
			killer.sendMessage("§b+1 XP");
			killer.sendMessage("§6+10 Moedas");
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
			player.sendTitle(new Title(Strings.getNome(), "§fVocê morreu"));
		}, 1L);
	}

	@EventHandler
	private void onPlayerQuit(PlayerQuitEvent event) {
		event.setQuitMessage(null);
	}
}