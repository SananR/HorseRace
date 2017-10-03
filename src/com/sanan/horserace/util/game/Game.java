package com.sanan.horserace.util.game;

public class Game {

	private static GameState currentState = GameState.STOPPED;
	
	public static boolean canStart() {
		return (currentState == GameState.STOPPED);
	}
	
	public static void startTeleportCooldown() {
		setCurrentGameState(GameState.TELEPORT_COUNTDOWN);
	}
	
	public static void startRaceStartCooldown() {
		setCurrentGameState(GameState.RACESTART_COUNTDOWN);
	}
	
	public static void setCurrentGameState(GameState state) {
		currentState = state;
	}
	
	public static GameState getCurrentGameState() {
		return currentState;
	}
	
}
