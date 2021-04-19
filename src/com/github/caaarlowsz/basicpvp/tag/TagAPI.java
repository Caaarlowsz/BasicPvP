package com.github.caaarlowsz.basicpvp.tag;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.account.StatusFile;
import com.nametagedit.plugin.NametagEdit;

public final class TagAPI {

	private static final HashMap<UUID, Tag> tagMap = new HashMap<>();

	public static Tag getTag(Player player, Tag tag) {
		return tagMap.getOrDefault(player.getUniqueId(), Tag.getDefaultTag());
	}

	public static void setTag(Player player, Tag tag) {
		tagMap.put(player.getUniqueId(), tag);
		NametagEdit.getApi().setNametag(player.getName(), tag.getPrefix(),
				" §8(" + StatusFile.getRankIcon(player) + "§8)");
		player.setDisplayName(tag.getPrefix() + player.getName() + " §8(" + StatusFile.getRankIcon(player) + "§8)");
	}

	public static void removeTag(Player player) {
		NametagEdit.getApi().clearNametag(player.getName());
		player.setDisplayName(player.getName());
		tagMap.remove(player.getUniqueId());
	}
}