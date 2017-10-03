package com.sanan.horserace.util.game;

public class Game {

	public static Game game;
	
	private static GameState currentState = GameState.STOPPED;
	
	public static Game getInstance() {
		if (game == null) { game = new Game(); }
		return game;
	}
	
	public boolean canStart() {
		return (currentState == GameState.STOPPED);
	}
	
	public void startGame() {
		GameThreadManager.startGameThread();
		setCurrentGameState(GameState.TELEPORT_COUNTDOWN);
	}
	
	public void startRaceStartCooldown() {
		setCurrentGameState(GameState.RACESTART_COUNTDOWN);
	}
	
	public void setCurrentGameState(GameState state) {
		currentState = state;
	}
	
	public GameState getCurrentGameState() {
		return currentState;
	}
	
}
