package com.github.caaarlowsz.basicpvp.apis;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public final class ChatAPI {

	private static boolean chat = false;
	private static final HashMap<UUID, Long> antiFloodMap = new HashMap<>();

	public static boolean getChat() {
		return chat;
	}

	public static void setChat(boolean value) {
		chat = value;
	}

	public static boolean hasAntiFlood(Player player) {
		return antiFloodMap.containsKey(player.getUniqueId());
	}

	public static long getAntiFlood(Player player) {
		if (hasAntiFlood(player))
			return (antiFloodMap.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L;
		return 0;
	}

	public static void addAntiFlood(Player player, int seconds) {
		antiFloodMap.put(player.getUniqueId(), System.currentTimeMillis() + (seconds * 1000L));
	}

	public static void removeAntiFlood(Player player) {
		antiFloodMap.remove(player.getUniqueId());
	}

	public static void sendCommand(Player player, String message, String hoverMessage, String command) {
		TextComponent text = new TextComponent(message);
		text.setHoverEvent(
				new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[] { new TextComponent(hoverMessage) }));
		text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
		player.spigot().sendMessage(text);
	}
}