package com.github.caaarlowsz.basicpvp.apis;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;

public final class TabAPI {

	public static void sendTab(Player player, String header, String footer) {
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(
				ChatSerializer.a("'" + header + "'"));
		setField(packet, "b", ChatSerializer.a("'" + footer + "'"));
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}

	private static void setField(Object packet, String field, Object value) {
		try {
			Field f = packet.getClass().getDeclaredField(field);
			f.setAccessible(true);
			f.set(packet, value);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}