package com.sanan.horserace.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.sanan.horserace.util.RaceTrack;
import com.sanan.horserace.util.game.Game;
import com.sanan.horserace.util.game.GameState;
import com.sanan.horserace.util.player.PlayerUtil;
import com.sanan.horserace.util.player.RacePlayer;

public class PortalEvents implements Listener {
	
	private Game game = Game.getInstance();
	private RaceTrack track = RaceTrack.getInstance();
	
	@EventHandler
	public void onRacePortal(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (event.getTo().distance(event.getFrom()) > 10) return;
		if (game.getCurrentGameState() == GameState.RACING) {
			if (event.getTo().distance(track.getPortalLocation()) <= 3) {
				for (RacePlayer rp : PlayerUtil.getAllRacePlayers()) {
					if (rp.getPlayer().getUniqueId().equals(player.getUniqueId())) {
						event.setCancelled(true);
						PlayerUtil.finishPlayerLap(player);
						//TODO Message
					}
				}		
			}
		}
	}
	
	

}
