package com.github.caaarlowsz.basicpvp.warp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.Warp;
import com.github.caaarlowsz.basicpvp.warp.WarpAPI;
import com.github.caaarlowsz.basicpvp.warp.Warps;

public final class SpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Warp warp = Warps.getDefaultWarp();
			WarpAPI.setWarp(player, warp);
			player.sendMessage(Strings.getPrefixo() + " §aVocê foi teleportado para o " + warp.getName() + ".");
		} else
			sender.sendMessage(Strings.getPrefixo() + " §cApenas Jogadores podem usar este Comando.");
		return true;
	}
}