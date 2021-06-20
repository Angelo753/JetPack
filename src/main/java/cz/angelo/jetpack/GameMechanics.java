package cz.angelo.jetpack;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.UUID;


public class GameMechanics extends Utils implements Listener {

	HashSet<UUID> sneak = new HashSet<>();

	@EventHandler
	public void onFlyToggle(PlayerToggleSneakEvent event) {
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();
		if (player.isSneaking()) {
			if (sneak.contains(uuid)) {
				return;
			}
			new BukkitRunnable() {
				@Override
				public void run() {
					if (!Bukkit.getOnlinePlayers().contains(player)){
						if (sneak.contains(uuid)) {
							sneak.remove(uuid);
						}
						this.cancel();
					}
					if (!isWearingJetpack(player)) {
						if (sneak.contains(uuid)) {
							sneak.remove(uuid);
							this.cancel();
						}
					}
					if (!player.isSneaking()) {
						if (sneak.contains(uuid)) {
							sneak.remove(uuid);
							this.cancel();
						}
						return;
					}
					Vector vector = new Vector(0, 1, 0);
					vector.add(player.getEyeLocation().getDirection().multiply(0.5));
					player.setVelocity(vector);
					player.getLocation().getWorld().playSound(player.getLocation(), Sound.BLOCK_REDSTONE_TORCH_BURNOUT, 1, 1);
					player.getLocation().getWorld().spawnParticle(Particle.SMOKE_NORMAL, player.getLocation(), 5);
				}
			}.runTaskTimer(Main.instance, 0, 20);
		}

	}



}
