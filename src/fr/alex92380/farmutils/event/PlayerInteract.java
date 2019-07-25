package fr.alex92380.farmutils.event;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


import java.util.ArrayList;

/**
 * Created by Alex92380
 */
public class PlayerInteract implements Listener {
    ArrayList<Location> dirtPosition = new ArrayList<>();
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (event.getItem() == null || event.getItem().getItemMeta() == null) return;
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK
                && event.getClickedBlock().getType().equals(Material.FARMLAND)
                && event.getItem().getType() == Material.DIAMOND_HOE
                && event.getItem().getItemMeta().getDisplayName().equals("§eSPECIAL HOE")) {
            double blockX = event.getClickedBlock().getX();
            double blockY = event.getClickedBlock().getY();
            double blockZ = event.getClickedBlock().getZ();
            dirtPosition.add(new Location(world, blockX - 1, blockY, blockZ + 1));
            dirtPosition.add(new Location(world, blockX, blockY, blockZ + 1));
            dirtPosition.add(new Location(world, blockX + 1, blockY, blockZ + 1));
            dirtPosition.add(new Location(world, blockX - 1, blockY, blockZ));
            dirtPosition.add(new Location(world, blockX, blockY, blockZ));
            dirtPosition.add(new Location(world, blockX + 1, blockY, blockZ));
            dirtPosition.add(new Location(world, blockX - 1,blockY, blockZ - 1));
            dirtPosition.add(new Location(world, blockX, blockY, blockZ - 1));
            dirtPosition.add(new Location(world, blockX + 1,blockY, blockZ - 1));
            for (Location dirtLocation : dirtPosition) {
                if (Material.FARMLAND == dirtLocation.getBlock().getType() && player.getInventory().contains(Material.WHEAT_SEEDS)) {
                    Location blockon = dirtLocation.add(0, 1, 0);
                    if(blockon.getBlock().isEmpty()){
                        removeItems(player.getInventory(), Material.WHEAT_SEEDS, 1);
                    blockon.getBlock().setType(Material.LEGACY_CROPS);
                }}
            }
        }
    }

    private static void removeItems(Inventory inventory, Material type, int amount) {
        if (amount <= 0) return;
        int size = inventory.getSize();
        for (int slot = 0; slot < size; slot++) {
            ItemStack is = inventory.getItem(slot);
            if (is == null) continue;
            if (type == is.getType()) {
                int newAmount = is.getAmount() - amount;
                if (newAmount > 0) {
                    is.setAmount(newAmount);
                    break;
                } else {
                    inventory.clear(slot);
                    amount = -newAmount;
                    if (amount == 0) break;
                }
            }
        }
    }
}
