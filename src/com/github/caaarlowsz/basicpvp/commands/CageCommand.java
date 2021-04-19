package com.github.caaarlowsz.basicpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class CageCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("kitpvp.command.cage")) {
				if (args.length > 0) {
					Player target = Bukkit.getPlayer(args[0]);
					if (target != null) {
						Location loc = target.getLocation();
						loc.add(0, 13, 0).getBlock().setType(Material.BEDROCK);
						loc.add(0, 11, 1).getBlock().setType(Material.BEDROCK);
						loc.add(1, 11, 0).getBlock().setType(Material.BEDROCK);
						loc.add(0, 11, -1).getBlock().setType(Material.BEDROCK);
						loc.add(-1, 11, 0).getBlock().setType(Material.BEDROCK);
						loc.add(0, 10, 0).getBlock().setType(Material.BEDROCK);
						target.teleport(loc.add(0, 11, -0.05));
						player.sendMessage(Strings.getPrefixo() + " §aVocê prendeu " + target.getName() + ".");
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