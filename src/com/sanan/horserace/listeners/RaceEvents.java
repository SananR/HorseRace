package com.sanan.horserace.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

import com.sanan.horserace.util.game.Game;
import com.sanan.horserace.util.game.GameState;
import com.sanan.horserace.util.player.PlayerUtil;
import com.sanan.horserace.util.player.RacePlayer;

public class RaceEvents implements Listener {
	
	private Game game = Game.getInstance();
	
	@EventHandler
	public void onLeavesDecay(LeavesDecayEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onRaceDamage(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		if (game.getCurrentGameState() == GameState.STOPPED || game.getCurrentGameState() == GameState.TELEPORT_COUNTDOWN) return;
		if (!(entity instanceof Horse || entity instanceof Player)) return;
		for (RacePlayer rp : PlayerUtil.getAllRacePlayers()) {
			if (rp.getHorse() == null) continue;
			if (rp.getPlayer().getUniqueId().equals(entity.getUniqueId()) || rp.getHorse().getUniqueId().equals(entity.getUniqueId())) {
				event.setCancelled(true);
			}
		}	
	}
	
	@EventHandler
	public void onRacePlayerDismount(VehicleExitEvent event) {
		if (!(event.getExited() instanceof Player)) return;
		Player player = (Player)event.getExited();
		Entity vehicle = event.getVehicle();
		if (!(vehicle instanceof Horse)) return;
		if (!(game.getCurrentGameState() == GameState.RACING)) return;
		for (RacePlayer rp : PlayerUtil.getAllRacePlayers()) {
			if (rp.getPlayer().getUniqueId().equals(player.getUniqueId())) {
				event.setCancelled(true);
			}
		}	
	}
	
	@EventHandler
	public void onRacePlayerMove(PlayerMoveEvent event) {
		if (!(game.getCurrentGameState() == GameState.RACESTART_COUNTDOWN)) return;
		if (event.getTo().getX() != event.getFrom().getX() || event.getTo().getY() != event.getFrom().getY() || event.getTo().getZ() != event.getFrom().getZ()) {
			Player player = event.getPlayer();
			for (RacePlayer rp : PlayerUtil.getAllRacePlayers()) {
				if (rp.getPlayer().getUniqueId().equals(player.getUniqueId())) {
					event.setTo(event.getFrom());
				}
			}	
		}
	}
	
	@EventHandler
	public void onRaceDistanceUpdate(PlayerMoveEvent event) {
		if (!(game.getCurrentGameState() == GameState.RACING)) return;
		if (event.getTo().getX() != event.getFrom().getX() || event.getTo().getY() != event.getFrom().getY() || event.getTo().getZ() != event.getFrom().getZ()) {
			Player player = event.getPlayer();
			for (RacePlayer rp : PlayerUtil.getAllRacePlayers()) {
				if (rp.getPlayer().getUniqueId().equals(player.getUniqueId())) {
					rp.setDistanceTraveled(rp.getDistanceTraveled() + 1);
				}
			}	
		}
	}

}
