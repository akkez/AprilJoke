package ru.akke.apriljoke;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AprilJokeExecutor implements CommandExecutor {

	private AprilJokePlugin pl;
	
	public AprilJokeExecutor(AprilJokePlugin aj) {
		pl = aj;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String cmd, String[] args) {
		if (!cs.hasPermission("apriljoke.manage")) {
			cs.sendMessage(ChatColor.RED + "You don't have permission!");
			return true;
		}
		
		if (cmd.equalsIgnoreCase("togglemute")) {
			pl.GlobalMute = !pl.GlobalMute;
			cs.sendMessage("GlobalMute flag switched!");
			return true;
		}
		
		if (cmd.equalsIgnoreCase("togglejoke")) {
			pl.Joke = !pl.Joke;
			cs.sendMessage("Joke flag switched!");
			return true;
		}
		
		if (cmd.equalsIgnoreCase("dispatchmessage")) {
			if (args.length < 2) {
				cs.sendMessage("Usage: /dispatchmessage <player> <message>");
				return true;
			}
			String name = args[0];
			if (Bukkit.getPlayerExact(name) == null) {
				cs.sendMessage("Player '" + name + "' not found!");
				return true;
			}
			
			Player p = Bukkit.getPlayerExact(name);
			StringBuilder msg = new StringBuilder();
			for (int i = 1; i < args.length; i++) {
				msg = msg.append(args[i]).append(" ");
			}
			String message = msg.toString().trim();
			pl.muted.add(p.getName());
			p.chat(message);
			pl.muted.remove(p.getName());
			cs.sendMessage(ChatColor.YELLOW + "Done!");
			return true;
		}
		
		return true; //never reached
	}

	
	
}
