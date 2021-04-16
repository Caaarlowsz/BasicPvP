package com.github.caaarlowsz.basicpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.apis.StaffAPI;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class BuildCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("kitpvp.command.build")) {
				if (args.length > 0) {
					if (args[0].equalsIgnoreCase("ON")) {
						if (!StaffAPI.hasBuild(player)) {
							StaffAPI.addBuild(player);
							player.sendMessage(Strings.getPrefixo() + " §aVocê ligou seu Build.");
						} else
							player.sendMessage(Strings.getPrefixo() + " §eO seu Build já está ligado.");
					} else if (args[0].equalsIgnoreCase("OFF")) {
						if (StaffAPI.hasBuild(player)) {
							StaffAPI.removeBuild(player);
							player.sendMessage(Strings.getPrefixo() + " §cVocê desligou o seu Build.");
						} else
							player.sendMessage(Strings.getPrefixo() + " §eO seu Build já está desligado.");
					}
				} else
					player.sendMessage(Strings.getPrefixo() + " §7Use: /" + label + " (ON, OFF)");
			} else
				player.sendMessage(Strings.getPrefixo() + " §cVocê não possui Permissão para usar este Comando.");
		} else
			sender.sendMessage(Strings.getPrefixo() + " §cApenas Jogadores podem usar este Comando.");
		return true;
	}
}