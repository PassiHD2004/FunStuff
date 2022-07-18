package Main;

import Main.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {

    private Main plugin;

    public Heal(Main plugin) {
        this.plugin = plugin;

            plugin.getCommand("heal").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("no_console")));
            return true;
        }
        Player p = (Player) sender;

        if (p.hasPermission("fun.heal")) {

            if (p.getHealth() < 20) {
                p.setHealth(20);
                p.sendMessage(Utils.chat(plugin.getConfig().getString("heal_filled")));
            } else {
                p.sendMessage(Utils.chat(plugin.getConfig().getString("heal_full")));
            }
            return true;
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm")));
        }
        return false;
    }
}
