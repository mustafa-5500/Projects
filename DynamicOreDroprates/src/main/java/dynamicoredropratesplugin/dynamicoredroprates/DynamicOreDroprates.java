package dynamicoredropratesplugin.dynamicoredroprates;

import dynamicoredropratesplugin.dynamicoredroprates.handlers.BlockBreak;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;


public final class DynamicOreDroprates extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Ore droprates is starting");
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Ore Droprates is shutting down");
    }

}
