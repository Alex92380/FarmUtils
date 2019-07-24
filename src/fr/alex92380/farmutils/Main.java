package fr.alex92380.farmutils;

import fr.alex92380.farmutils.commands.SpecialHoeCommands;
import fr.alex92380.farmutils.event.BlockBreak;
import fr.alex92380.farmutils.event.BlockFade;
import fr.alex92380.farmutils.event.PlayerInteract;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;



/**
 * Created by Alex92380
 */
public class Main extends JavaPlugin {
    @Override
    public void onEnable() { 
        registerEvents();
        registerCommands();
    }

    private void registerCommands() {
        getCommand("specialhoe").setExecutor(new SpecialHoeCommands());
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new BlockBreak(), this);
        pm.registerEvents(new BlockFade(), this);
        pm.registerEvents(new PlayerInteract(), this);
    }
}
