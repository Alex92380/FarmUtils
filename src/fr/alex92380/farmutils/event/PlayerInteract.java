package fr.alex92380.farmutils.event;

import org.bukkit.Location;
import org.bukkit.Material;
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
    ArrayList<Location> locationArrayList = new ArrayList<>();
//I know this code need to be optimised (i'm gonna to doing that on the new version)
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() == null || event.getItem().getItemMeta() == null) return;
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK
                && event.getClickedBlock().getType().equals(Material.FARMLAND)
                && event.getItem().getType() == Material.DIAMOND_HOE
                && event.getItem().getItemMeta().getDisplayName().equals("§eSPECIAL HOE")) {
            locationArrayList.add(new Location(player.getWorld(), event.getClickedBlock().getLocation().getX() - 1, event.getClickedBlock().getLocation().getY(), event.getClickedBlock().getLocation().getZ() + 1));
            locationArrayList.add(new Location(player.getWorld(), event.getClickedBlock().getLocation().getX(), event.getClickedBlock().getLocation().getY(), event.getClickedBlock().getLocation().getZ() + 1));
            locationArrayList.add(new Location(player.getWorld(), event.getClickedBlock().getLocation().getX() + 1, event.getClickedBlock().getLocation().getY(), event.getClickedBlock().getLocation().getZ() + 1));
            locationArrayList.add(new Location(player.getWorld(), event.getClickedBlock().getLocation().getX() - 1, event.getClickedBlock().getLocation().getY(), event.getClickedBlock().getLocation().getZ()));
            locationArrayList.add(new Location(player.getWorld(), event.getClickedBlock().getLocation().getX(), event.getClickedBlock().getLocation().getY(), event.getClickedBlock().getLocation().getZ()));
            locationArrayList.add(new Location(player.getWorld(), event.getClickedBlock().getLocation().getX() + 1, event.getClickedBlock().getLocation().getY(), event.getClickedBlock().getLocation().getZ()));
            locationArrayList.add(new Location(player.getWorld(), event.getClickedBlock().getLocation().getX() - 1, event.getClickedBlock().getLocation().getY(), event.getClickedBlock().getLocation().getZ() - 1));
            locationArrayList.add(new Location(player.getWorld(), event.getClickedBlock().getLocation().getX(), event.getClickedBlock().getLocation().getY(), event.getClickedBlock().getLocation().getZ() - 1));
            locationArrayList.add(new Location(player.getWorld(), event.getClickedBlock().getLocation().getX() + 1, event.getClickedBlock().getLocation().getY(), event.getClickedBlock().getLocation().getZ() - 1));
            for (Location arrayList : locationArrayList) {
                if (Material.FARMLAND == arrayList.getBlock().getType() && player.getInventory().contains(Material.WHEAT_SEEDS)) {
                    removeItems(player.getInventory(), Material.WHEAT_SEEDS, 1);
                    Location blockunder = arrayList.add(0, 1, 0);
                    blockunder.getBlock().setType(Material.LEGACY_CROPS);
                }
            }
        }
    }

    public static void removeItems(Inventory inventory, Material type, int amount) {
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
