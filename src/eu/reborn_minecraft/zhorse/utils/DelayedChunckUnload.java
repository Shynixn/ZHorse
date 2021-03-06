package eu.reborn_minecraft.zhorse.utils;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;

import eu.reborn_minecraft.zhorse.ZHorse;

public class DelayedChunckUnload {
	
	public DelayedChunckUnload(ZHorse zh, Chunk chunk) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(zh, new Runnable() {
			
			@Override
			public void run() {
				final Entity[] entityArray = chunk.getEntities();
				for (Entity entity : entityArray) {
					if (entity instanceof Horse) {
						Horse horse = (Horse) entity;
						if (zh.getDM().isHorseRegistered(horse.getUniqueId())) {
							zh.getHM().unloadHorse(horse);
							zh.getDM().updateHorseLocation(horse.getUniqueId(), horse.getLocation(), true);
						}
					}
				}
			}
			
		});
	}

}