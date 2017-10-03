package com.sanan.horserace.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sanan.horserace.util.RaceTrack;
import com.sanan.horserace.util.chat.Message;
import com.sanan.horserace.util.game.Game;

public class SetTrackCommand implements CommandExecutor {

	private Game game = Game.getInstance();
	private RaceTrack track = RaceTrack.getInstance();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("settrack")) {
			if (!(sender instanceof Player)) {
				return true;
			}
			Player player = (Player)sender;
			if (!sender.hasPermission("horserace.settrack")) {
				sender.sendMessage(Message.NO_PERMISSION.getConfigMessage());
				return true;
			}
			track.setSpawnLocation(player.getLocation());
			player.sendMessage(Message.SET_TRACK.getConfigMessage());
			return true;
		}
		return true;
	}
	
}
