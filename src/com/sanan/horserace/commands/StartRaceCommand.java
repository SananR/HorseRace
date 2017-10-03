package com.sanan.horserace.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartRaceCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("startrace")) {
			if (!sender.hasPermission("horserace.start")) {
				
			}
		}
		return true;
	}

	
	
}
