package serverselectorplugin.serverselectorgui;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

import java.util.Objects;
import java.util.logging.Level;

public final class ServerSelectorGUI extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(getCommand("servers")).setExecutor(this::onCommand);
        getLogger().log(Level.INFO,"Server Selector Plugin is enabled");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().log(Level.INFO,"Server Selector Plugin is disabled");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("servers")){
            Gui gui = Gui.normal()
                    .setStructure(
                            "# # # # . # # # #"
                    ).addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                            .setDisplayName("")))
                    .build();
            gui.addItems(new ServerItem());
            Window window = Window.single()
                    .setViewer((Player) sender)
                    .setTitle("Servers")
                    .setGui(gui)
                    .build();

            window.open();
            return true;
        }
        return false;
    }
}
