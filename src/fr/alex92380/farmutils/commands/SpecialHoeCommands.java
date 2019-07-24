package fr.alex92380.farmutils.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Created by Alex92380
 */
public class SpecialHoeCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
       if(sender instanceof Player){
Player player = (Player)sender;

        ItemStack specialhoe = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta specialHoeMeta = specialhoe.getItemMeta();
        specialHoeMeta.setDisplayName("§eSPECIAL HOE");
        specialHoeMeta.setLore(Arrays.asList("§ereplace seeds on","§e 3 X 3"));
        specialHoeMeta.addEnchant(Enchantment.DURABILITY,3,true);
        specialhoe.setItemMeta(specialHoeMeta);
player.getInventory().addItem(specialhoe);
    }else {
           sender.sendMessage("[FarmUtils]This command must be executed by a player");
       }
    return false;
    }

}
