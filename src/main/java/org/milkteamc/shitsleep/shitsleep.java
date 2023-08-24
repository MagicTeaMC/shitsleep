package org.milkteamc.shitsleep;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class shitsleep extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        Bukkit.getScheduler().runTask(this, () -> {
            List<String> pluginsToDisable = getConfig().getStringList("plugins");

            for (String pluginName : pluginsToDisable) {
                Plugin targetPlugin = Bukkit.getPluginManager().getPlugin(pluginName);

                if (targetPlugin != null && targetPlugin.isEnabled()) {
                    Bukkit.getPluginManager().disablePlugin(targetPlugin);
                    getLogger().info(pluginName + " has been disabled.");
                } else {
                    getLogger().info(pluginName + " not found or already disabled.");
                }
            }
        });
    }
}