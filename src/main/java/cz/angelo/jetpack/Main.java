package cz.angelo.jetpack;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

	public static Main instance;

	@Override
	public void onEnable() {
		instance = this;
		this.getServer().getPluginManager().registerEvents(new GameMechanics(), this);
	}

	@Override
	public void onDisable() {

	}
}
