package com.sanan.horserace.util.game;

import org.bukkit.entity.Player;

import com.sanan.horserace.util.chat.Message;
import com.sanan.horserace.util.player.PlayerUtil;
import com.sanan.horserace.util.player.RacePlayer;

public class Game {

	public static Game game;
	
	private int finishedPlayers = 0;
	private int currentTimer = 0;
	private GameState currentState = GameState.STOPPED;
	
	public static Game getInstance() {
		if (game == null) { game = new Game(); }
		return game;
	}
	
	public boolean canStart() {
		return (currentState == GameState.STOPPED);
	}
	
	public int getFinishedPlayers() {
		return finishedPlayers;
	}
	
	public int getTimer() {
		return currentTimer;
	}
	
	public void stopRace() {
		GameThreadManager.stopGameThread();
		for (RacePlayer rp : PlayerUtil.getAllRacePlayers()) {
			rp.getPlayer().sendMessage(Message.GAME_OVER.getConfigMessage());
			PlayerUtil.unregisterPlayer(rp);
		}
		game.setCurrentTimer(0);
		game.setFinishedPlayers(0);
		game.setCurrentGameState(GameState.STOPPED);
	}
	
	public void startRace() {
		setCurrentGameState(GameState.RACING);
		for (RacePlayer rp : PlayerUtil.getAllRacePlayers()) {
			Player player = rp.getPlayer();
			PlayerUtil.spawnPlayerHorse(player);
		}
	}
	
	public void startWaitCountdown() {
		setCurrentGameState(GameState.RACESTART_COUNTDOWN);
		PlayerUtil.teleportAndRegisterAllPlayersToRace();
		setCurrentTimer(5);
		
	}
	
	public void startTeleport() {
		GameThreadManager.startGameThread();
		setCurrentGameState(GameState.TELEPORT_COUNTDOWN);
		setCurrentTimer(10);
	}
	
	public void setFinishedPlayers(int finishedPlayers) {
		this.finishedPlayers = finishedPlayers;
	}
	public void setCurrentTimer(int timer) {
		currentTimer = timer;
	}
	
	public void setCurrentGameState(GameState state) {
		currentState = state;
	}
	
	public GameState getCurrentGameState() {
		return currentState;
	}
	
}
