package com.github.caaarlowsz.basicpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.utils.Strings;

public class TeleportCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("kitpvp.command.teleport")) {
				if (args.length > 0) {
					if (args.length > 2) {
						try {
							double x = Integer.parseInt(args[0]);
							try {
								double y = Integer.parseInt(args[1]);
								try {
									double z = Integer.parseInt(args[2]);
									player.teleport(new Location(player.getWorld(), x + 0.5, y + 0.5, z + 0.5));
									player.sendMessage(
											Strings.getPrefixo() + " §aVocê se teleportou até as coordenadas " + x
													+ ", " + y + ", " + z + ".");
								} catch (NumberFormatException ex) {
									player.sendMessage(Strings.getPrefixo() + " §cO valor Z precisa ser numérico.");
								}
							} catch (NumberFormatException ex) {
								player.sendMessage(Strings.getPrefixo() + " §cO valor Y precisa ser numérico.");
							}
						} catch (NumberFormatException ex) {
							player.sendMessage(Strings.getPrefixo() + " §cO valor X precisa ser numérico.");
						}
					} else if (args.length > 1) {
						Player target = Bukkit.getPlayer(args[0]);
						if (target != null) {
							if (args.length == 2) {
								Player target2 = Bukkit.getPlayer(args[1]);
								if (target2 != null) {
									target.teleport(target2.getLocation());
									player.sendMessage(Strings.getPrefixo() + " §aVocê teleportou " + target.getName()
											+ " para " + target2.getName() + ".");
								} else
									player.sendMessage(
											Strings.getPrefixo() + " §cO seu segundo alvo não foi encontrado.");
							} else {
								player.teleport(target.getLocation());
								player.sendMessage(
										Strings.getPrefixo() + " §aVocê se teleportou até " + target.getName() + ".");
							}
						} else
							player.sendMessage(Strings.getPrefixo() + " §cO seu alvo não foi encontrado.");
					}
				} else
					player.sendMessage(Strings.getPrefixo() + " §7Use: /" + label + " (Nickname) [Nickname] ou /"
							+ label + " (X) (Y) (Z)");
			} else
				player.sendMessage(Strings.getPrefixo() + " §cVocê não possui Permissão para usar este Comando.");
		} else
			sender.sendMessage(Strings.getPrefixo() + " §cApenas Jogadores podem usar este Comando.");
		return true;
	}
}