package dev.nayo.mythicgui;

import dev.nayo.mythicgui.commands.CommandMythicMobGui;
import dev.nayo.mythicgui.handlers.InventoryClickHandler;
import io.lumine.xikage.mythicmobs.MythicMobs;

import io.lumine.xikage.mythicmobs.items.MythicItem;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class MythicGUI extends JavaPlugin {
    // Declaring variables
    private MythicMobs mm;
    private HashMap<String, DisplayConfig> configStorage = new HashMap<>();
    private List<String> configNames = new ArrayList<>();
    private static MythicGUI plugin;

    public void onEnable() {
        // Static instances instantiation
        plugin = this;
        // Gets the Mythic Mob Instance
        this.mm = (MythicMobs) getServer().getPluginManager().getPlugin("MythicMobs");
        // Bukkit intialize the mythicmobgui command
        this.getCommand("mythicmobgui").setExecutor(new CommandMythicMobGui());
        // Inventory click event handler initialized
        this.getServer().getPluginManager().registerEvents(new InventoryClickHandler(), this);
        // Intializing the variables and resetting them
        onReload();

    }
    // Getter
    public List<String> getConfigNames() {
         return configNames;
    }
    public HashMap<String,DisplayConfig> getConfigStorage() {
        return configStorage;
    }
    public DisplayConfig getAllItemsConfig() {
        return configStorage.get("ALL");
    }

    // Reload the plugin and reread all the items and store them
    public void onReload() {
        // Clear all lists
        configStorage.clear();
        configNames.clear();
        // Create a fake config group called ALL where all items are stored
        addDisplayConfig(new DisplayConfig("ALL"));
        // Iterate through all the item and sort them in their own config
        for (MythicItem item : this.mm.getItemManager().getItems()) {
            String confRawName = item.getConfig().getFile().getName();
            String conf = confRawName.substring(0, confRawName.length() - 4);
            DisplayConfig config = addDisplayConfig(new DisplayConfig(conf));

            configStorage.get("ALL").addItem(new DisplayItem(item));
            config.addItem(new DisplayItem(item));
        }
    }

    public static MythicGUI getInstance() {
        return plugin;
    }

    public void onDisable() {

    }
    // addDisplayConfig is a method that accepts a new argument of DisplayConfig object, checks if it exists and adds if it doesn't
    // Return DisplayConfig
    private DisplayConfig addDisplayConfig(DisplayConfig config) {
        DisplayConfig confInp = configStorage.putIfAbsent(config.getName(), config);
        if(confInp == null) {
            configNames.add(config.getName());
            Collections.sort(configNames);
        }
        return configStorage.get(config.getName());
    }
}
