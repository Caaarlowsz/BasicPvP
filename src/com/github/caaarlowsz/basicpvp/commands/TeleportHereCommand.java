package com.github.caaarlowsz.basicpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class TeleportHereCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("command.teleporthere")) {
				if (args.length > 0) {
					Player target = Bukkit.getPlayer(args[0]);
					if (target != null) {
						target.teleport(player.getLocation());
						player.sendMessage(Strings.getPrefixo() + " §aVocê puxou " + target.getName() + " até você.");
					} else
						player.sendMessage(Strings.getPrefixo() + " §cO seu alvo não foi encontrado.");
				} else
					player.sendMessage(Strings.getPrefixo() + " §7Use: /" + label + " (Nickname)");
			} else
				player.sendMessage(Strings.getPrefixo() + " §cVocê não possui Permissão para este Comando.");
		} else
			sender.sendMessage(Strings.getPrefixo() + " §cApenas Jogadores podem usar este Comando.");
		return true;
	}
}