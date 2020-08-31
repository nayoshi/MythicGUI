package dev.nayo.mythicgui;

import dev.nayo.mythicgui.commands.CommandMythicMobGui;
import dev.nayo.mythicgui.handlers.InventoryClickHandler;
import io.lumine.xikage.mythicmobs.MythicMobs;

import io.lumine.xikage.mythicmobs.items.MythicItem;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class MythicGUI extends JavaPlugin {

    private MythicMobs mm;
    private HashMap<String, DisplayConfig> configStorage = new HashMap<>();
    private List<String> configNames = new ArrayList<>();
    private static MythicGUI plugin;

    public void onEnable() {
        plugin = this;
        this.mm = (MythicMobs) getServer().getPluginManager().getPlugin("MythicMobs");
        this.getCommand("mythicmobgui").setExecutor(new CommandMythicMobGui());
        this.getServer().getPluginManager().registerEvents(new InventoryClickHandler(), this);
        onReload();

    }
    public List<String> getConfigNames() {
         return configNames;
    }
    public HashMap<String,DisplayConfig> getConfigStorage() {
        return configStorage;
    }
    public DisplayConfig getAllItemsConfig() {
        return configStorage.get("ALL");
    }
    public void onReload() {
        configStorage.clear();
        configNames.clear();
        addDisplayConfig(new DisplayConfig("ALL"));
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

    public MythicMobs getMMInstance() {
        return this.mm;
    }

    private DisplayConfig addDisplayConfig(DisplayConfig config) {
        DisplayConfig confInp = configStorage.putIfAbsent(config.getName(), config);
        if(confInp == null) {
            configNames.add(config.getName());
            Collections.sort(configNames);
        }
        return configStorage.get(config.getName());
    }
}
