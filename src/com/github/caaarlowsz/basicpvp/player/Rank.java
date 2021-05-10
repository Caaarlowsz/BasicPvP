package com.github.caaarlowsz.basicpvp.player;

import org.bukkit.ChatColor;

public enum Rank {
	LEGENDARY(ChatColor.DARK_RED, '✪', 3000), SAPPHIRE(ChatColor.DARK_AQUA, '✵', 2700), RUBY(ChatColor.RED, '✸',
			2400), EMERALD(ChatColor.DARK_GREEN, '✶', 2100), DIAMOND(ChatColor.AQUA, '❋', 1600), GOLD(ChatColor.GOLD,
					'✦', 1500), SILVER(ChatColor.GRAY, '✷', 1200), EXPERT(ChatColor.DARK_BLUE, '✥', 900), ADVANCED(
							ChatColor.YELLOW, '☲',
							600), PRIMARY(ChatColor.GREEN, '═', 300), UNRANKED(ChatColor.WHITE, '-', 0);

	private final ChatColor color;
	private final char symbol;
	private final int neededXP;

	private Rank(ChatColor color, char symbol, int neededXP) {
		this.color = color;
		this.symbol = symbol;
		this.neededXP = neededXP;
	}

	public String getName() {
		return this.name();
	}

	public ChatColor getColor() {
		return this.color;
	}

	public char getSymbol() {
		return this.symbol;
	}

	public int getNeededXP() {
		return this.neededXP;
	}

	public String getColoredName() {
		return this.getColor() + this.getName();
	}

	public String getColoredSymbol() {
		return this.getColor() + String.valueOf(this.getSymbol());
	}

	public String getColoredSymbolName() {
		return this.getColor() + String.valueOf(this.getSymbol()) + " " + this.getName();
	}

	public String getColoredNameSymbol() {
		return this.getColor() + this.getName() + " " + String.valueOf(this.getSymbol());
	}

	public static Rank getRankByXP(int xp) {
		for (Rank rank : values()) {
			if (xp >= rank.getNeededXP())
				return rank;
		}
		return null;
	}
}