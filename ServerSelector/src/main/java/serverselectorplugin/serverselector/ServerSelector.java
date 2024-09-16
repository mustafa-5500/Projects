package serverselectorplugin.serverselector;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

public final class ServerSelector extends Plugin {

    @Override
    public void onEnable() {
        // Register your command
        getProxy().getPluginManager().registerCommand(this, new OpenServerCommand());
        getLogger().info("YourBungeePlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("YourBungeePlugin has been disabled!");
    }
    private class OpenServerCommand extends Command {
        public OpenServerCommand() {
            super("openserver");
        }

        @Override
        public void execute(CommandSender commandSender, String[] strings) {
            if (commandSender instanceof ProxiedPlayer){
                ProxiedPlayer player = (ProxiedPlayer) commandSender;

                if (strings.length == 1){
                    String server = strings[0];

                    player.connect(getProxy().getServerInfo(server));
                }
            }

        }
    }
}
