package com.sanan.horserace.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sanan.horserace.util.RaceTrack;
import com.sanan.horserace.util.chat.Message;

public class SetPortalCommand implements CommandExecutor {

	private RaceTrack track = RaceTrack.getInstance();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("setportal")) {
			if (!(sender instanceof Player)) {
				return true;
			}
			Player player = (Player)sender;
			if (!sender.hasPermission("horserace.setportal")) {
				sender.sendMessage(Message.NO_PERMISSION.getConfigMessage());
				return true;
			}
			track.setPortalLocation(player.getLocation());
			player.sendMessage(Message.SET_PORTAL.getConfigMessage());
			return true;
		}
		return true;
	}
}
