package com.github.caaarlowsz.basicpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class PingCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 0) {
				Player target = Bukkit.getPlayer(args[0]);
				if (target != null)
					player.sendMessage(Strings.getPrefixo() + " §aO ping de " + target.getName() + " é de "
							+ ((CraftPlayer) target).getHandle().ping + "ms.");
				else
					player.sendMessage(Strings.getPrefixo() + " §cO seu alvo não foi encontrado.");
			} else
				player.sendMessage(
						Strings.getPrefixo() + " §aSeu ping: " + ((CraftPlayer) player).getHandle().ping + "ms.");
		} else
			sender.sendMessage(Strings.getPrefixo() + " §cApenas Jogadores podem usar este Comando.");
		return true;
	}
}