package com.sanan.horserace.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class PortalEvents implements Listener {
	
	@EventHandler
	public void onRacePortal(PlayerPortalEvent event) {
		Player player = event.getPlayer();
	}
	
	

}
