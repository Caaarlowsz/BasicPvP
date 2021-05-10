package com.github.caaarlowsz.basicpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.github.paperspigot.Title;

import com.github.caaarlowsz.basicpvp.player.PlayerAPI;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class ReportCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (PlayerAPI.getUseReport(player) <= 0) {
				if (args.length > 1) {
					Player target = Bukkit.getPlayer(args[0]);
					if (target != null) {
						String reason = "";
						for (int i = 1; i < args.length; i++)
							reason += (reason.isEmpty() ? "" : " ") + args[i];

						String finalReason = reason;
						for (Player staffers : Bukkit.getOnlinePlayers()) {
							if (staffers.hasPermission("kitpvp.command.report")) {
								staffers.playSound(staffers.getLocation(), Sound.LEVEL_UP, 15F, 1F);
								staffers.sendMessage(" ");
								staffers.sendMessage("  §a§lNOVA DENUNCIA!");
								staffers.sendMessage(" ");
								staffers.sendMessage(" §aReporter: §f" + player.getName());
								staffers.sendMessage(" ");
								staffers.sendMessage(" §aReportado: §f" + target.getName());
								staffers.sendMessage(" §aMotivo: §f" + finalReason);
								staffers.sendMessage(" ");
								player.sendTitle(new Title("§a§lNOVA DENUNCIA", "§fNova denúncia!"));
							}
						}

						PlayerAPI.setUseReport(player, 15);
						player.sendMessage(Strings.getPrefixo() + " §aVocê denunciou " + target.getName() + " por "
								+ reason + ".");
					} else
						player.sendMessage(Strings.getPrefixo() + " §cO seu alvo não foi encontrado.");
				} else
					player.sendMessage(Strings.getPrefixo() + " §7Use: /" + label + " (Nickname) (Motivo)");
			} else
				player.sendMessage(Strings.getPrefixo() + " §cAguarde para reportar novamente.");
		} else
			sender.sendMessage(Strings.getPrefixo() + " §cApenas Jogadores podem usar este Comando.");
		return true;
	}
}