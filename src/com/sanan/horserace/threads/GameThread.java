package com.sanan.horserace.threads;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import com.sanan.horserace.util.chat.Message;
import com.sanan.horserace.util.game.Game;
import com.sanan.horserace.util.game.GameState;
import com.sanan.horserace.util.game.GameThreadManager;
import com.sanan.horserace.util.player.PlayerUtil;
import com.sanan.horserace.util.player.RacePlayer;

public class GameThread extends BukkitRunnable {

	private Game game = Game.getInstance();

	public void run() {

		if (game.getCurrentGameState() == GameState.STOPPED) {
			GameThreadManager.stopGameThread();
		}

		else if (game.getCurrentGameState() == GameState.TELEPORT_COUNTDOWN) {
			int currentTimer = game.getTimer();

			if (currentTimer > 0) {
				game.setCurrentTimer(currentTimer - 1);
				Bukkit.getServer().broadcastMessage(Message.TELEPORT_TIMER.getConfigMessage().replaceAll("%timer%",
						Integer.toString(currentTimer)));
				return;
			} else {
				game.startWaitCountdown();
				return;
			}
		}

		else if (game.getCurrentGameState() == GameState.RACESTART_COUNTDOWN) {
			int currentTimer = game.getTimer();

			if (currentTimer > 0) {
				game.setCurrentTimer(currentTimer - 1);
				Bukkit.getServer().broadcastMessage(Message.WAITING_TIMER.getConfigMessage().replaceAll("%timer%", Integer.toString(currentTimer)));
				return;
			} else {
				game.startRace();
				Bukkit.getServer().broadcastMessage(Message.RACE_START.getConfigMessage());
				for (RacePlayer rp : PlayerUtil.getAllRacePlayers()) {
					for (String message : Message.RACE_LAP.getConfigMessageList()) {
						rp.getPlayer().sendMessage(message.replaceAll("%current%", Integer.toString(rp.getCurrentLap())));
					}
				}
				return;
			}
		}

		else if (game.getCurrentGameState() == GameState.RACING) {
			int currentTimer = game.getTimer();
			
			if (game.getFinishedPlayers() == 0) return;

			if (currentTimer > 0) {
				game.setCurrentTimer(currentTimer - 1);
				if (currentTimer == 30 || currentTimer == 15 || currentTimer == 10 || currentTimer <= 5) {
					for (RacePlayer rp : PlayerUtil.getAllRacePlayers()) {
						rp.getPlayer().sendMessage(Message.TIME_LIMIT.getConfigMessage().replaceAll("%time%",Integer.toString(currentTimer)));
					}
				}
			} else {
				for (RacePlayer rp : PlayerUtil.getAllRacePlayers()) {
					if (!rp.getHasFinished()) {
						PlayerUtil.returnAndUnregisterPlayerFromRace(rp.getPlayer());
					}
				}
			}

		}

	}

}
