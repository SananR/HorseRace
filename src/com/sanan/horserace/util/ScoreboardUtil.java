package com.sanan.horserace.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.sanan.horserace.util.player.PlayerUtil;
import com.sanan.horserace.util.player.RacePlayer;

public class ScoreboardUtil {

	private static Scoreboard board;
	
	public static void updateScoreboard(Player player) {
		for (RacePlayer rp : PlayerUtil.getAllRacePlayers()) {
			board.getObjective(DisplaySlot.SIDEBAR).getScore(player.getName()).setScore(rp.getDistanceTraveled());
		}
		player.setScoreboard(board);
	}
	
	public static void resetScoreboard() {
		board = null;
	}

	public static void registerPlayerToScoreboard(Player player) {
        Team playerName = board.registerNewTeam("testing");
        playerName.addEntry(player.getName());
        playerName.setPrefix(ChatColor.AQUA + "");
        board.getObjective(DisplaySlot.SIDEBAR).getScore(player.getName()).setScore(0);
	}
	
	public static void setupScoreboard() {
		board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective("HorseRace", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("Distance");
	}
	
	
}
