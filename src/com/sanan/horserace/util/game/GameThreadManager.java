package com.sanan.horserace.util.game;

import org.bukkit.scheduler.BukkitTask;

import com.sanan.horserace.Main;
import com.sanan.horserace.threads.GameThread;

public class GameThreadManager {
	
	private static BukkitTask gameThread = null;
	
	public static void startGameThread() {
		if (gameThread != null) return;
		gameThread = new GameThread().runTaskTimer(Main.plugin, 20, 20);
	}
	
	public static void stopGameThread() {
		gameThread.cancel();
		gameThread = null;
	}
	

}
