package com.github.caaarlowsz.basicpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class InventorySeeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("kitpvp.command.inventorysee")) {
				if (args.length > 0) {
					Player target = Bukkit.getPlayer(args[0]);
					if (target != null) {
						player.openInventory(target.getInventory());
						player.sendMessage(
								Strings.getPrefixo() + " §aVocê abriu o inventário de " + target.getName() + ".");
					} else
						player.sendMessage(Strings.getPrefixo() + " §cO seu alvo não foi encontrado.");
				} else
					player.sendMessage(Strings.getPrefixo() + " §7Use: /" + label + " (Nickname)");
			} else
				player.sendMessage(Strings.getPrefixo() + " §cVocê não possui Permissão para usar este Comando.");
		} else
			sender.sendMessage(Strings.getPrefixo() + " §cApenas Jogadores podem usar este Comando.");
		return true;
	}
}