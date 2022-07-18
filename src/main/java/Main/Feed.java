package Main;

import Main.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Feed implements CommandExecutor {

    private Main plugin;

    public Feed(Main plugin) {
        this.plugin = plugin;

            plugin.getCommand("feed").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("no_console")));
            return true;
        }
        Player p = (Player) sender;

        if (p.hasPermission("fun.feed")) {

            int maxFoodLevel = 20;

            if (p.getFoodLevel() < maxFoodLevel) {
                p.setFoodLevel(maxFoodLevel);
                p.sendMessage(Utils.chat(plugin.getConfig().getString("feed_filled")));
            } else {
                p.sendMessage(Utils.chat(plugin.getConfig().getString("feed_full")));
            }
            return true;
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm")));
        }
        return false;
    }

}
