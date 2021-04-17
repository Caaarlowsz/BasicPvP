package com.github.caaarlowsz.basicpvp.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class GameModeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("kitpvp.command.gamemode")) {
				if (args.length > 0) {
					if (args[0].equals("1") || args[0].equalsIgnoreCase("C") || args[0].equalsIgnoreCase("Creative")) {
						if (!player.getGameMode().equals(GameMode.CREATIVE)) {
							player.setGameMode(GameMode.CREATIVE);
							player.sendMessage(Strings.getPrefixo() + " §aVocê alterou seu GameMode para Creative.");
						} else
							player.sendMessage(Strings.getPrefixo() + " §eVocê já está no GameMode Creative.");
					} else {
						if (!player.getGameMode().equals(GameMode.SURVIVAL)) {
							player.setGameMode(GameMode.SURVIVAL);
							player.sendMessage(Strings.getPrefixo() + " §aVocê alterou seu GameMode para Survival.");
						} else
							player.sendMessage(Strings.getPrefixo() + " §eVocê já está no GameMode Survival.");
					}
				} else
					player.sendMessage(Strings.getPrefixo() + " §7Use: /" + label + " (0, 1)");
			} else
				player.sendMessage(Strings.getPrefixo() + " §cVocê não possui Permissão para usar este Comando.");
		} else
			sender.sendMessage(Strings.getPrefixo() + " §cApenas Jogadores podem usar este Comando.");
		return true;
	}
}