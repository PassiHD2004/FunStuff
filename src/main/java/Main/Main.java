package Main;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;


    @Override
    public void onEnable() {
        new Nuke(this);
        saveDefaultConfig();
    }

}
