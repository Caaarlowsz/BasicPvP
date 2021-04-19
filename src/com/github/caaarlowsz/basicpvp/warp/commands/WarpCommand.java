package com.github.caaarlowsz.basicpvp.warp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.github.paperspigot.Title;

import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.Warp;
import com.github.caaarlowsz.basicpvp.warp.WarpAPI;
import com.github.caaarlowsz.basicpvp.warp.Warps;

public final class WarpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 0) {
				String name = "";
				for (int i = 0; i < args.length; i++)
					name += (name.isEmpty() ? "" : " ") + args[i];

				Warp warp = Warps.getByName(name);
				if (warp != null) {
					WarpAPI.setWarp(player, warp);
					player.sendMessage(
							Strings.getPrefixo() + " §aVocê foi teleportado para a Warp " + warp.getName() + ".");
					player.sendTitle(new Title(Strings.getCorPrincipal() + "Warp " + warp.getName(), "§fTeleportado.",
							5, 10, 5));
				} else
					player.sendMessage(Strings.getPrefixo() + " §cEsta Warp não existe.");
			} else {
				String warps = "";
				for (Warp warp : Warps.getWarps())
					warps += (warps.isEmpty() ? "" : ", ") + warp.getName();
				player.sendMessage(Strings.getPrefixo() + " §7Use: /" + label + " (" + warps + ")");
			}
		} else
			sender.sendMessage(Strings.getPrefixo() + " §cApenas Jogadores podem usar este Comando.");
		return true;
	}
}