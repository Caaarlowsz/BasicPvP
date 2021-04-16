package com.github.caaarlowsz.basicpvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class ChatListeners implements Listener {

	@EventHandler
	private void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String format = player.getDisplayName() + " §8» §7", message = event.getMessage();
		if (player.hasPermission("kitpvp.vip.chatdestaque"))
			format += "§f";
		if (player.hasPermission("kitpvp.vip.chatcolor"))
			message = ChatColor.translateAlternateColorCodes('&', message);

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