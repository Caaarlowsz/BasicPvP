package com.github.caaarlowsz.basicpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class BroadcastCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("kitpvp.command.broadcast")) {
			if (args.length > 0) {
				String message = "";
				for (int i = 0; i < args.length; i++)
					message += (args[i].isEmpty() ? "" : " ") + args[i];

				Bukkit.broadcastMessage(
						Strings.getPrefixo() + " §f" + ChatColor.translateAlternateColorCodes('&', message));
			} else
				sender.sendMessage(Strings.getPrefixo() + " §7Use: /" + label + " (Mensagem)");
		} else
			sender.sendMessage(Strings.getPrefixo() + " §cVocê não possui Permissão para usar este Comando.");
		return true;
	}
}