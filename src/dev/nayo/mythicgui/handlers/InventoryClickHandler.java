package dev.nayo.mythicgui.handlers;

import dev.nayo.mythicgui.DisplayConfig;
import dev.nayo.mythicgui.MythicGUI;
import dev.nayo.mythicgui.helper.InventoryHelper;
import dev.nayo.mythicgui.helper.StringHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InventoryClickHandler implements Listener {
    @EventHandler
    public void inventoryClick(InventoryClickEvent e) {
        // Checks if the player clicks the the side of inventory
        if (e.getClickedInventory() == null) return;
        // Checks if the inventory is actually clicked by player
        if (e.getClickedInventory().getType() == InventoryType.PLAYER) return;
        // Check if player are clicking the items in inventory
        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        //Checks if player are clicking plugin's inventory. So player can change their inventory without it being cancelled
        if (ChatColor.stripColor(e.getView().getTitle()).equals("MythicGUI")) {
            e.setCancelled(true);
            // Get the stored data in the localized name
            String invpage = e.getCurrentItem().getItemMeta().getLocalizedName();
            String[] strSplit = invpage.split(":");
            // If player clicks the next page
            if (e.getCurrentItem().getType() == Material.GREEN_STAINED_GLASS_PANE) {
                InventoryHelper.updateInventory(e.getClickedInventory(), MythicGUI.getInstance().getConfigStorage().get(strSplit[0]), 36, Integer.parseInt(strSplit[1]));
                return;
            }
            // Last page
            if (e.getCurrentItem().getType() == Material.RED_STAINED_GLASS_PANE) {
                InventoryHelper.updateInventory(e.getClickedInventory(), MythicGUI.getInstance().getConfigStorage().get(strSplit[0]), 36, Integer.parseInt(strSplit[1]));
                return;
            }
            // Back to config inventory
            if (e.getCurrentItem().getType() == Material.PINK_STAINED_GLASS_PANE) {
                Player player = (Player) e.getWhoClicked();
                Inventory inv = InventoryHelper.createConfigInventory(player, MythicGUI.getInstance().getConfigNames(), 36);
                player.openInventory(inv);
                return;
            }
            // Run the mythic mob items when clicked
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mm items give " + e.getWhoClicked().getName() + " " + e.getCurrentItem().getItemMeta().getLocalizedName());
        }
        // Checks if config inventory is clicked
        if (ChatColor.stripColor(e.getView().getTitle()).equals("Config")) {
            e.setCancelled(true);
            String configName = e.getCurrentItem().getItemMeta().getLocalizedName();
            DisplayConfig conf = MythicGUI.getInstance().getConfigStorage().get(configName);
            if (conf != null) {
                Player player = (Player) e.getWhoClicked();
                Inventory inv = InventoryHelper.createInventory(player, conf, 36);
                player.closeInventory();
                player.openInventory(inv);
                //Easter Eggs
            } else if (configName.equals("nayo")) {
                e.getWhoClicked().sendMessage(StringHelper.print("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
            }
        }
    }
}
