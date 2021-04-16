package com.github.caaarlowsz.basicpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.apis.PlayerAPI;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class TellCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 0) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("ON")) {
						if (PlayerAPI.hasTellOff(player)) {
							PlayerAPI.removeTellOff(player);
							player.sendMessage(Strings.getPrefixo() + " §aVocê ligou o seu Tell.");
						} else
							player.sendMessage(Strings.getPrefixo() + " §eVocê já está com o Tell ligado.");
					} else if (args[0].equalsIgnoreCase("OFF")) {
						if (PlayerAPI.hasTellOff(player)) {
							PlayerAPI.addTellOff(player);
							player.sendMessage(Strings.getPrefixo() + " §cVocê desligou o seu Tell.");
						} else
							player.sendMessage(Strings.getPrefixo() + " §eVocê já está com o Tell desligado.");
					} else
						player.sendMessage(Strings.getPrefixo() + " §7Use: /" + label + " (Nickname) (Mensagem)");
				} else {
					if (!PlayerAPI.hasTellOff(player)) {
						Player target = Bukkit.getPlayer(args[0]);
						if (target != null) {
							if (!PlayerAPI.hasTellOff(target)) {
								String message = "";
								for (int i = 1; i < args.length; i++)
									message += (message.isEmpty() ? "" : " ") + args[i];

								target.sendMessage("§7(" + player.getName() + " §8» §7você) §6" + message);
								player.sendMessage("§7(você §8» §7" + target.getName() + ") §6" + message);
							} else
								player.sendMessage(Strings.getPrefixo() + " §e" + target.getName()
										+ " está com o Tell desligado.");
						} else
							player.sendMessage(Strings.getPrefixo() + " §cO seu alvo não foi encontrado.");
					} else
						player.sendMessage(Strings.getPrefixo() + " §eVocê está com o Tell desligado.");
				}
			} else
				player.sendMessage(Strings.getPrefixo() + " §7Use: /" + label + " (Nickname) (Mensagem)");
		} else
			sender.sendMessage(Strings.getPrefixo() + " §cApenas Jogadores podem usar este Comando.");
		return true;
	}
}