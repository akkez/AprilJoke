package ru.akke.apriljoke;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AprilJokeListener implements Listener {

	private AprilJokePlugin pl;
	
	public AprilJokeListener(AprilJokePlugin aj) {
		pl = aj;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onChat(AsyncPlayerChatEvent event) {
		if (event.isCancelled()) {
			return;
		}
		String name = event.getPlayer().getName();
		if (pl.GlobalMute && (!event.getPlayer().hasPermission("apriljoke.exempt"))) {
			event.setCancelled(true);
			return;
		}		
		if (!pl.Joke) {
			return;
		}
		if (pl.muted.contains(name)) {
			return;
		}
		Player victim;
		Player[] ps = pl.getServer().getOnlinePlayers();
		boolean allperm = true;
		for (int i = 0; i < ps.length; i++) {
			if (!ps[i].hasPermission("apriljoke.exempt")) {
				allperm = false;
				break;
			}
		}
		if (allperm) {
			event.getPlayer().sendMessage(ChatColor.YELLOW + "Impossible!");
			return;
		}
		do {
			victim = ps[pl.rand.nextInt(ps.length)];
		} while (victim.hasPermission("apriljoke.exempt"));
		
		pl._log(event.getPlayer().getName() + " '" + victim.getName() + "': " + event.getMessage());
		
		pl.muted.add(victim.getName());
		victim.chat(event.getMessage());
		pl.muted.remove(victim.getName());
		event.setCancelled(true);
	}
	
}
