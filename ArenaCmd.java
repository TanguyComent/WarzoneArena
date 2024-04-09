package me.kitsoko.warzonearena;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCmd implements CommandExecutor {

	private Main main;
	public ArenaCmd(Main main) {
		this.main = main;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(args[0].equals("rl")) {
			main.onEnable();
			if(sender instanceof Player) {
				sender.sendMessage("§7[§6WarzoneArena§7] : §aThe plugin has succesfully been §2reloaded §a!");
			}
		}
		return false;
	}

}
