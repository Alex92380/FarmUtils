package fr.alex92380.farmutils.event;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
/**
 * Created by Alex92380
 */
public class BlockBreak implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Block block = event.getBlock();
        if(block.getType().equals(Material.WHEAT) && block.getData() == (byte)7){
            event.setCancelled(true);
            block.breakNaturally();
            block.setType(Material.LEGACY_CROPS);
    }
}}
