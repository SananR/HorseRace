package com.sanan.horserace.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.sanan.horserace.util.chat.Message;
import com.sanan.horserace.util.game.Game;

public class StartRaceCommand implements CommandExecutor {

	private Game game = Game.getInstance();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("startrace")) {
			if (!sender.hasPermission("horserace.start")) {
				sender.sendMessage(Message.NO_PERMISSION.getConfigMessage());
				return true;
			}
			if (game.canStart()) {
				game.startGame ();
				sender.sendMessage(Message.GAME_START.getConfigMessage());
				return true;
			} else {
				sender.sendMessage(Message.ALREADY_STARTED.getConfigMessage());
				return true;
			}
		}
		return true;
	}

	
	
}
