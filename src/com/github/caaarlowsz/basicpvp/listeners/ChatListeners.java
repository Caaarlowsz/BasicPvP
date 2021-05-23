package com.github.caaarlowsz.basicpvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.github.caaarlowsz.basicpvp.apis.ChatAPI;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class ChatListeners implements Listener {

	@EventHandler
	private void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String format = player.getDisplayName() + " §8» §7", message = event.getMessage().replace("%", "%%");
		if (player.hasPermission("kitpvp.vip.chatdestaque"))
			format += "§f";
		if (player.hasPermission("kitpvp.vip.chatcolor"))
			message = ChatColor.translateAlternateColorCodes('&', message);

		if (!ChatAPI.getChat() && !player.hasPermission("kitpvp.vip.speakchatoff")) {
			event.setCancelled(true);
			player.sendMessage(Strings.getPrefixo() + " §cO Chat do Servidor está desligado.");
			return;
		}

		if (ChatAPI.hasAntiFlood(player) && ChatAPI.getAntiFlood(player) > 0) {
			event.setCancelled(true);
			player.sendMessage(Strings.getPrefixo() + " §cAguarde para digitar novamente.");
		} else if (!player.hasPermission("kitpvp.permission.flood"))
			ChatAPI.addAntiFlood(player, player.hasPermission("kitpvp.vip.reducedflood") ? 3 : 5);

		event.setFormat(format + message);
	}

	@EventHandler
	private void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String command = event.getMessage().toLowerCase().split(" ")[0];

		if (Bukkit.getHelpMap().getHelpTopic(command) == null) {
			event.setCancelled(true);
			player.sendMessage(Strings.getPrefixo() + " §cComando não encontrado.");
		}
	}
}