package fr.alex92380.farmutils.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;

/**
 * Created by Alex92380
 */
public class BlockFade implements Listener {
    @EventHandler
    public void onBlockFade(BlockFadeEvent event){
        event.setCancelled(true);
    }
}
