package com.sanan.horserace.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.sanan.horserace.util.RaceTrack;
import com.sanan.horserace.util.chat.Message;
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
		if (game.getCurrentGameState() == GameState.RACING) {
			if (event.getTo().distance(track.getPortalLocation()) <= 2) {

				for (int i=0; i < PlayerUtil.getAllRacePlayers().size(); i++) {
					RacePlayer rp = PlayerUtil.getAllRacePlayers().get(i);
					if (rp.getPlayer().getUniqueId().equals(player.getUniqueId())) {
						event.setCancelled(true);
						for (String message : Message.RACE_LAP.getConfigMessageList()) {
							player.sendMessage(message.replaceAll("%current%", Integer.toString(rp.getCurrentLap())));
						}
						PlayerUtil.finishPlayerLap(player);
						return;
					}
				}		
				
			}
		}
	}
	
	

}
