package Main;

import Main.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;

public class Nuke implements CommandExecutor {

    private Main plugin;

    public Nuke(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("nuke").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("no_console")));
            return true;
        }

        sender.sendMessage(Utils.chat(plugin.getConfig().getString("nuke_msg")));

        int delayInSeconds = plugin.getConfig().getInt("tnt_spawn_delay");
        final int yAbovePlayer = plugin.getConfig().getInt("tnt_spawn_yaboveplayer");
        final int fusingTimeInSeconds = plugin.getConfig().getInt("tnt_fusingtime");

        Bukkit.getScheduler().runTaskTimer(this.plugin, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                TNTPrimed tnt = (TNTPrimed) p.getWorld().spawnEntity(p.getLocation().add(0, yAbovePlayer, 0), EntityType.PRIMED_TNT);
                tnt.setFuseTicks(fusingTimeInSeconds * 20);
            }
        }, fusingTimeInSeconds * 20, delayInSeconds *20);
        return false;
    }

}