package com.github.caaarlowsz.basicpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.apis.StaffAPI;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class StaffChatCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("kitpvp.command.staffchat")) {
				if (args.length > 0) {
					if (args.length == 1 && args[0].equalsIgnoreCase("silent")) {
						if (!StaffAPI.hasSilentStaffChat(player)) {
							StaffAPI.removeStaffChat(player);
							StaffAPI.addSilentStaffChat(player);
							player.sendMessage(Strings.getPrefixo() + " §aVocê silenciou o bate-papo da equipe.");
						} else {
							StaffAPI.removeSilentStaffChat(player);
							player.sendMessage(Strings.getPrefixo() + " §aVocê dessilenciou o bate-papo da equipe.");
						}
						return true;
					}

					String message = "";
					for (int i = 0; i < args.length; i++)
						message += (args[i].isEmpty() ? "" : " ") + args[i];

					if (!StaffAPI.hasSilentStaffChat(player))
						StaffAPI.sendToStaffChat(player, message);
					else
						player.sendMessage(Strings.getPrefixo() + " §cVocê está com o bate-papo da equipe silenciado.");
				} else {
					if (!StaffAPI.hasStaffChat(player)) {
						StaffAPI.addStaffChat(player);
						if (StaffAPI.hasSilentStaffChat(player)) {
							StaffAPI.removeSilentStaffChat(player);
							player.sendMessage(Strings.getPrefixo() + " §aVocê dessilenciou o bate-papo da equipe.");
						}
						player.sendMessage(Strings.getPrefixo() + " §aVocê entrou no bate-papo da equipe.");
					} else {
						StaffAPI.removeStaffChat(player);
						player.sendMessage(Strings.getPrefixo() + " §cVocê saiu do bate-papo da equipe.");
					}
				}
			} else
				player.sendMessage(Strings.getPrefixo() + " §cVocê não possui Permissão para este Comando.");
		} else
			sender.sendMessage(Strings.getPrefixo() + " §cApenas Jogadores podem usar este Comando.");
		return true;
	}
}