package com.github.caaarlowsz.basicpvp.warp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.WarpsFile;

public final class SetCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("kitpvp.command.set")) {
				if (args.length > 0) {
					if (args[0].equalsIgnoreCase("Spawn")) {
						WarpsFile.setLocation("Spawn", player.getLocation());
						player.sendMessage(Strings.getPrefixo() + " §aLocal do Spawn definido.");
					} else if (args[0].equalsIgnoreCase("1v1")) {
						if (args.length > 1) {
							if (args[1].equalsIgnoreCase("Spawn")) {
								WarpsFile.setLocation("1v1", player.getLocation());
								player.sendMessage(Strings.getPrefixo() + " §aLocal da Warp 1v1 definido.");
							} else if (args[1].equalsIgnoreCase("Pos1")) {
								WarpsFile.setLocation("1v1.Pos1", player.getLocation());
								player.sendMessage(Strings.getPrefixo() + " §aPosição #1 da Warp 1v1 definido.");
							} else if (args[1].equalsIgnoreCase("Pos2")) {
								WarpsFile.setLocation("1v1.Pos2", player.getLocation());
								player.sendMessage(Strings.getPrefixo() + " §aPosição #2 da Warp 1v1 definido.");
							} else
								player.sendMessage(Strings.getPrefixo() + " §cEste local não foi encontrado.");
						} else
							player.sendMessage(Strings.getPrefixo() + " §7Use: /" + label + " 1v1 (Spawn, Pos1, Pos2)");
					} else if (args[0].equalsIgnoreCase("FPS")) {
						WarpsFile.setLocation("FPS", player.getLocation());
						player.sendMessage(Strings.getPrefixo() + " §aLocal da Warp FPS definido.");
					} else if (args[0].equalsIgnoreCase("Lava") && args[1].equalsIgnoreCase("Challenge")) {
						WarpsFile.setLocation("Lava Challenge", player.getLocation());
						player.sendMessage(Strings.getPrefixo() + " §aLocal da Warp Lava Challenge definido.");
					} else
						player.sendMessage(Strings.getPrefixo() + " §cEste local não foi encontrado.");
				} else
					player.sendMessage(
							Strings.getPrefixo() + " §7Use: /" + label + " (Spawn, 1v1, FPS, Lava Challenge)");
			} else
				player.sendMessage(Strings.getPrefixo() + " §cVocê não possui Permissão para usar este Comando.");
		} else
			sender.sendMessage(Strings.getPrefixo() + " §cApenas Jogadores podem usar este Comando.");
		return true;
	}
}