package com.sanan.horserace.threads;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import com.sanan.horserace.util.chat.Message;
import com.sanan.horserace.util.game.Game;
import com.sanan.horserace.util.game.GameState;
import com.sanan.horserace.util.game.GameThreadManager;

public class GameThread extends BukkitRunnable {

	private Game game = Game.getInstance();
	
	public void run() {
		
		if (game.getCurrentGameState() == GameState.STOPPED) {
			GameThreadManager.stopGameThread();
		}
		
		else if (game.getCurrentGameState() == GameState.TELEPORT_COUNTDOWN) {
			int currentTimer = game.getTimer();
			
			if (currentTimer > 0) {
				game.setCurrentTimer(currentTimer-1);
				Bukkit.getServer().broadcastMessage(Message.TELEPORT_TIMER.getConfigMessage().replaceAll("%timer%", Integer.toString(currentTimer)));
				return;
			} else {
				game.startWaitCountdown();
				return;
			}
		}
		
		else if (game.getCurrentGameState() == GameState.RACESTART_COUNTDOWN) {
			int currentTimer = game.getTimer();
			
			if (currentTimer > 0) {
				game.setCurrentTimer(currentTimer-1);
				Bukkit.getServer().broadcastMessage(Message.WAITING_TIMER.getConfigMessage().replaceAll("%timer%", Integer.toString(currentTimer)));
				return;
			} else {
				game.startRace();
				Bukkit.getServer().broadcastMessage(Message.RACE_START.getConfigMessage());
				return;
			}
		}
		
		else if (game.getCurrentGameState() == GameState.RACING) {
			Bukkit.getServer().broadcastMessage("You are in a race");
		}
		
	}

}
