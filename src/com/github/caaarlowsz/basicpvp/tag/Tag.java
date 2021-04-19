package com.github.caaarlowsz.basicpvp.tag;

import org.bukkit.ChatColor;

public enum Tag {
	DONO(ChatColor.DARK_RED),
	ADMIN(ChatColor.RED),
	MOD(ChatColor.DARK_PURPLE),
	TRIALMOD("TrialMod", ChatColor.DARK_PURPLE),
	BUILDER(ChatColor.DARK_GREEN),
	YOUTUBER("YouTuber", ChatColor.AQUA),
	BETA(ChatColor.DARK_BLUE),
	PRO(ChatColor.GOLD),
	MVP("MvP", ChatColor.BLUE),
	VIP("VIP", ChatColor.GREEN),
	MEMBRO(ChatColor.GRAY, "§7");

	private final String name, prefix;
	private final ChatColor color;

	private Tag(ChatColor color) {
		this.name = name().substring(0, 1).toUpperCase() + name().substring(1);
		this.color = color;
		this.prefix = color + "§l" + name.toUpperCase() + " " + color;
	}

	private Tag(String name, ChatColor color) {
		this(name, color, color + "§l" + name.toUpperCase() + " " + color);
	}

	private Tag(ChatColor color, String prefix) {
		this.name = name().substring(0, 1).toUpperCase() + name().substring(1);
		this.color = color;
		this.prefix = prefix;
	}

	private Tag(String name, ChatColor color, String prefix) {
		this.name = name;
		this.color = color;
		this.prefix = prefix;
	}

	public String getName() {
		return this.name;
	}

	public ChatColor getColor() {
		return this.color;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public int getPriority() {
		return this.ordinal();
	}

	public boolean isDefaultTag() {
		return this == getDefaultTag();
	}

	public String getColoredName() {
		return this.getColor() + this.getName();
	}

	public static Tag getDefaultTag() {
		return MEMBRO;
	}

	public static Tag getByName(String name) {
		for (Tag tag : values()) {
			if (tag.getName().equalsIgnoreCase(name))
				return tag;
		}
		return null;
	}
}