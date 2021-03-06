package com.github.caaarlowsz.basicpvp.warp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.WarpAPI;
import com.github.caaarlowsz.basicpvp.warp.warps.SpawnWarp;

public final class FlyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("kitpvp.command.fly")) {
				if (args.length > 0) {
					if (player.hasPermission("kitpvp.command.fly.others")) {
						Player target = Bukkit.getPlayer(args[0]);
						if (target != null) {
							if (WarpAPI.getWarp(target) instanceof SpawnWarp) {
								if (!target.getAllowFlight()) {
									target.setAllowFlight(true);
									player.sendMessage(Strings.getPrefixo() + " §aVocê habilitou o Fly de "
											+ target.getName() + ".");
								} else {
									player.setAllowFlight(false);
									player.sendMessage(Strings.getPrefixo() + " §cVocê desabilitou o Fly de "
											+ target.getName() + ".");
								}
							} else
								player.sendMessage(Strings.getPrefixo() + " §c" + target.getName()
										+ " precisa estar no Spawn para voar.");
						} else
							player.sendMessage(Strings.getPrefixo() + " §cO seu alvo não foi encontrado");
					} else
						player.sendMessage(Strings.getPrefixo()
								+ " §cVocê não possui Permissão para habilitar o Fly de outros jogadores.");
				} else {
					if (WarpAPI.getWarp(player) instanceof SpawnWarp) {
						if (!player.getAllowFlight()) {
							player.setAllowFlight(true);
							player.sendMessage(Strings.getPrefixo() + " §aVocê habilitou seu Fly.");
						} else {
							player.setAllowFlight(false);
							player.sendMessage(Strings.getPrefixo() + " §cVocê desabilitou seu Fly.");
						}
					} else
						player.sendMessage(Strings.getPrefixo() + " §cVocê precisa estar no Spawn para voar.");
				}
			} else
				player.sendMessage(Strings.getPrefixo() + " §cVocê não possui Permissão para usar este Comando.");
		} else
			sender.sendMessage(Strings.getPrefixo() + " §cApenas Jogadores podem usar este Comando.");
		return true;
	}
}