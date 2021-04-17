package com.github.caaarlowsz.basicpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class InfoCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage(" ");
		sender.sendMessage(" ");
		sender.sendMessage(" ");
		sender.sendMessage(Strings.getPrefixo() + " §eInformações do Servidor:");
		sender.sendMessage(" ");
		sender.sendMessage("  §aWebsite: §f" + Strings.getWebsite());
		sender.sendMessage("  §aLoja: §f" + Strings.getLoja());
		sender.sendMessage("  §aDiscord: §f" + Strings.getDiscord());
		sender.sendMessage("  §7Disponível para versões 1.7x & 1.8x");
		sender.sendMessage(" ");
		sender.sendMessage(" ");
		sender.sendMessage(" ");
		return true;
	}
}