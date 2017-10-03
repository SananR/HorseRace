package com.sanan.horserace.util.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.sanan.horserace.util.RaceTrack;

public class PlayerUtil {
	
	private static RaceTrack raceTrack = RaceTrack.getInstance();
	
	public static void teleportAllPlayersToRace() {
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			player.teleport(raceTrack.getSpawnLocation());
		}
	}

}
