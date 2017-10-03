package com.sanan.horserace.threads;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import com.sanan.horserace.util.game.Game;
import com.sanan.horserace.util.game.GameState;
import com.sanan.horserace.util.game.GameThreadManager;

public class GameThread extends BukkitRunnable {

	private Game game = Game.getInstance();
	
	public void run() {
		
		if (game.getCurrentGameState() == GameState.STOPPED) {
			Bukkit.getServer().broadcastMessage("Game thread stopped");
			GameThreadManager.stopGameThread();
		}
		
		if (game.getCurrentGameState() == GameState.TELEPORT_COUNTDOWN) {
			Bukkit.getServer().broadcastMessage("Teleporting to game in 10 seconds");
		}
		
	}

}
