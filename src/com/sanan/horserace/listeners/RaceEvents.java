package com.sanan.horserace.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.sanan.horserace.util.game.Game;
import com.sanan.horserace.util.game.GameState;
import com.sanan.horserace.util.player.PlayerUtil;
import com.sanan.horserace.util.player.RacePlayer;

public class RaceEvents implements Listener {
	
	private Game game = Game.getInstance();
	
	@EventHandler
	public void onRacePlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (!(game.getCurrentGameState() == GameState.RACESTART_COUNTDOWN)) return;
		for (RacePlayer rp : PlayerUtil.getAllRacePlayers()) {
			if (rp.getPlayer().getUniqueId().equals(player.getUniqueId())) {
				event.setCancelled(true);
			}
		}
	}

}
