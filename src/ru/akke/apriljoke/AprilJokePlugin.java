package ru.akke.apriljoke;

import java.util.HashSet;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class AprilJokePlugin extends JavaPlugin {

	public Logger log;
	public HashSet<String> muted = new HashSet<String>();
	public boolean GlobalMute = false;
	public boolean Joke = false;
	public Random rand = new Random(System.nanoTime());
	
	@Override
	public void onEnable() {
		log = getServer().getLogger();
		getServer().getPluginManager().registerEvents(new AprilJokeListener(this), this);
		getCommand("togglejoke").setExecutor(new AprilJokeExecutor(this));
		getCommand("togglemute").setExecutor(new AprilJokeExecutor(this));
		getCommand("dispatchmessage").setExecutor(new AprilJokeExecutor(this));
		_log("Enabled!");
	}
	
	public void _log(String arg) {
		log.info("[" + this.getDescription().getName() + "] " + arg);
	}
	
}
