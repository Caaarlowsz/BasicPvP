package com.github.caaarlowsz.basicpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.apis.ChatAPI;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class ChatCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("kitpvp.command.chat")) {
				if (args.length > 0) {
					if (args[0].equalsIgnoreCase("Clear")) {
						for (int i = 0; i < 100; i++)
							Bukkit.broadcastMessage(" ");
						Bukkit.broadcastMessage(
								Strings.getPrefixo() + " §aO Chat foi limpo por " + player.getName() + ".");
					} else if (args[0].equalsIgnoreCase("ON")) {
						if (!ChatAPI.getChat()) {
							ChatAPI.setChat(true);
							Bukkit.broadcastMessage(
									Strings.getPrefixo() + " §aO Chat foi ligado por " + player.getName() + ".");
						} else
							player.sendMessage(Strings.getPrefixo() + " §eO Chat já está ligado.");
					} else if (args[0].equalsIgnoreCase("OFF")) {
						if (ChatAPI.getChat()) {
							ChatAPI.setChat(false);
							Bukkit.broadcastMessage(
									Strings.getPrefixo() + " §cO Chat foi desligado por " + player.getName() + ".");
						} else
							player.sendMessage(Strings.getPrefixo() + " §eO Chat já está desligado.");
					} else
						player.sendMessage(Strings.getPrefixo() + " §cO Argumento não foi encontrado.");
				} else
					player.sendMessage(Strings.getPrefixo() + " §7Use: /" + label + " (Clear, ON, OFF)");
			} else
				player.sendMessage(Strings.getPrefixo() + " §cVocê não possui Permissão para usar este Comando.");
		} else
			sender.sendMessage(Strings.getPrefixo() + " §cApenas Jogadores podem usar este Comando.");
		return true;
	}
}