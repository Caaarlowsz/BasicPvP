package com.github.caaarlowsz.basicpvp.kit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.github.paperspigot.Title;

import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.WarpAPI;
import com.github.caaarlowsz.basicpvp.warp.Warps;

public final class KitCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 0) {
				Kit kit = Kits.getByName(args[0]);
				if (kit != null) {
					if (!KitAPI.hasKit(player)) {
						if (!WarpAPI.getWarp(player).getName().equals(Warps.getDefaultWarp().getName()))
							WarpAPI.setWarp(player, Warps.getDefaultWarp());
						KitAPI.setKit(player, kit);
						player.sendMessage(Strings.getPrefixo() + " §aVocê selecionou o Kit " + kit.getName() + ".");
						player.sendTitle(new Title(Strings.getCorPrincipal() + "Kit " + kit.getName(), "§fSelecionado.",
								5, 10, 5));
					} else
						player.sendMessage(Strings.getPrefixo() + " §cVocê já está usando um Kit.");
				} else
					player.sendMessage(Strings.getPrefixo() + " §cEste Kit não existe.");
			} else {
				String kits = "";
				for (Kit kit : Kits.getKits())
					kits += (kits.isEmpty() ? "" : ", ") + kit.getName();
				player.sendMessage(Strings.getPrefixo() + " §7Use: /" + label + " (" + kits + ")");
			}
		} else
			sender.sendMessage(Strings.getPrefixo() + " §cApenas Jogadores podem usar este Comando.");
		return true;
	}
}