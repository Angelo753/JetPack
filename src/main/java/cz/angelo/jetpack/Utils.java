package cz.angelo.jetpack;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Utils {

	public static boolean isWearingJetpack(Player player){
		if (player.getInventory().getChestplate() == null){
			return false;
		}
		return player.getInventory().getChestplate().getType() == Material.DIAMOND_CHESTPLATE;
	}

}
