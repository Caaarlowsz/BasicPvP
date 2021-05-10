package com.github.caaarlowsz.basicpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.caaarlowsz.basicpvp.player.Rank;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class RankCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage(" ");
		sender.sendMessage(" ");
		sender.sendMessage(" ");
		sender.sendMessage(Strings.getPrefixo() + " §eLista de Rank's do Servidor:");
		sender.sendMessage(" ");
		for (Rank rank : Rank.values())
			sender.sendMessage("   " + rank.getColoredSymbolName() + " §7" + rank.getNeededXP() + " de XP.");
		sender.sendMessage(" ");
		return true;
	}
}