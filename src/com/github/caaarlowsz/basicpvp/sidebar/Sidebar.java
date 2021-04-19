package com.github.caaarlowsz.basicpvp.sidebar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.google.common.base.Splitter;

public final class Sidebar {

	private HashMap<Integer, String> lines;
	private Scoreboard scoreboard;
	private Team[] teams;
	private ArrayList<ChatColor> chatMap;

	public Sidebar(String displayName) {
		this.lines = new HashMap<>();
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		this.scoreboard.registerNewObjective("Sidebar", "dummy");
		this.scoreboard.getObjective("Sidebar").setDisplaySlot(DisplaySlot.SIDEBAR);
		this.scoreboard.getObjective("Sidebar").setDisplayName(displayName);
		this.teams = new Team[16];
		this.chatMap = new ArrayList<>();
		for (ChatColor chatColor : ChatColor.values()) {
			if (this.chatMap.size() + 1 > 15)
				break;
			this.chatMap.add(chatColor);
		}
	}

	public String getName() {
		return this.scoreboard.getObjective("Sidebar").getName();
	}

	public boolean hasPlayer(Player player) {
		return player.getScoreboard().getObjective("Sidebar") != null;
	}

	public void addPlayer(Player player) {
		player.setScoreboard(this.scoreboard);
	}

	public boolean hasLine(int index) {
		return this.lines.containsKey(index);
	}

	public Set<Entry<Integer, String>> getLines() {
		return this.lines.entrySet();
	}

	public String getLine(int index) {
		return this.teams[index].getPrefix() + this.teams[index].getSuffix();
	}

	public void updateLine(int index, String newLine) {
		if (this.hasLine(index)) {
			newLine = this.fixDuplicates(newLine);
			Iterator<String> iterator = Splitter.fixedLength(16).split(newLine).iterator();
			String prefix = iterator.next();
			boolean shouldInsert = newLine.length() > 16 && prefix.charAt(15) == '§';
			if (shouldInsert)
				prefix = prefix.substring(0, 15);
			this.teams[index].setPrefix(prefix);
			String chatColor = ChatColor.getLastColors(prefix);
			if (newLine.length() > 16) {
				String suffix = iterator.next();
				if (shouldInsert)
					suffix = "§" + suffix;
				else
					suffix = chatColor + suffix;

				if (suffix.length() > 16)
					suffix = suffix.substring(0, 16);

				this.teams[index].setSuffix(suffix);
			} else
				this.teams[index].setSuffix("");
		}
	}

	public void addBlankLine(int index) {
		if (!this.hasLine(index))
			this.addLine(index, " ");
	}

	public void addLine(int index, String line) {
		line = this.fixDuplicates(line);
		this.teams[index] = this.scoreboard.registerNewTeam(String.valueOf(index));
		int random = new Random().nextInt(this.chatMap.size());
		String id = this.chatMap.get(random).toString();
		this.teams[index].addEntry(id);
		this.lines.put(index, id);
		this.scoreboard.getObjective("Sidebar").getScore(id).setScore(index);

		Iterator<String> iterator = Splitter.fixedLength(16).split(line).iterator();
		String prefix = iterator.next();
		boolean shouldInsert = line.length() >= 16 && prefix.charAt(15) == '§';
		if (shouldInsert)
			prefix = prefix.substring(0, 15);

		this.teams[index].setPrefix(prefix);
		String chatColor = ChatColor.getLastColors(prefix);

		if (line.length() > 16) {
			String suffix = iterator.next();
			if (shouldInsert)
				suffix = "§" + suffix;
			else
				suffix = chatColor + suffix;

			if (suffix.length() > 16)
				suffix = suffix.substring(0, 16);

			this.teams[index].setSuffix(suffix);
		}
		this.lines.put(index, line);
		this.chatMap.remove(random);
	}

	public void removeLine(int index) {
		if (this.hasLine(index)) {
			this.teams[index].unregister();
			this.teams[index] = null;

			String line = this.lines.get(index);
			this.scoreboard.resetScores(line);
			this.lines.remove(index);
		}
	}

	private String fixDuplicates(String text) {
		while (this.lines.containsValue(text))
			text += "§r";
		return text;
	}
}