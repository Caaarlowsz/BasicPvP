package com.github.caaarlowsz.basicpvp.tag;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class TagCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 0) {
				Tag tag = Tag.getByName(args[0]);
				if (tag != null) {
					if (player.hasPermission("kitpvp.tag." + tag.getName())) {
						TagAPI.setTag(player, tag);
						player.sendMessage(Strings.getPrefixo() + " §aVocê selecionou a Tag " + tag.getName() + ".");
					} else
						player.sendMessage(
								Strings.getPrefixo() + " §cVocê não possui acesso a Tag " + tag.getName() + ".");
				} else
					player.sendMessage(Strings.getPrefixo() + " §cEsta Tag não existe.");
			} else {
				String tags = "";
				for (Tag tag : Tag.values())
					if (player.hasPermission("kitpvp.tag." + tag.getName()))
						tags += (tags.isEmpty() ? "" : ", ") + tag.getName();
				player.sendMessage(Strings.getPrefixo() + " §7Use: /" + label + " (" + tags + ")");
			}
		} else
			sender.sendMessage(Strings.getPrefixo() + " §cApenas Jogadores podem usar este Comando.");
		return true;
	}
}