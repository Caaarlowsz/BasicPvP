package com.github.caaarlowsz.basicpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class ApplyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage(" ");
		sender.sendMessage(" ");
		sender.sendMessage(" ");
		sender.sendMessage(Strings.getPrefixo() + " §eLista de formulários:");
		sender.sendMessage(" ");
		sender.sendMessage("    §eFormulário para §5TrialMod§e: §7" + Strings.getTrialModApplication());
		sender.sendMessage(" ");
		sender.sendMessage(" ");
		sender.sendMessage(" ");
		return true;
	}
}