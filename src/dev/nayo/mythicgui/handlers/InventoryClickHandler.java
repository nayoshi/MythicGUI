package dev.nayo.mythicgui.handlers;

import dev.nayo.mythicgui.DisplayConfig;
import dev.nayo.mythicgui.MythicGUI;
import dev.nayo.mythicgui.helper.InventoryHelper;
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
        if (e.getClickedInventory() == null) return;
        if (e.getClickedInventory().getType() == InventoryType.PLAYER) return;
        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;

        System.out.println(e.getView().getTitle());
        if (ChatColor.stripColor(e.getView().getTitle()).equals("MythicGUI")) {
            e.setCancelled(true);
            String invpage = e.getCurrentItem().getItemMeta().getLocalizedName();
            String[] strSplit = invpage.split(":");
            if (e.getCurrentItem().getType() == Material.GREEN_STAINED_GLASS_PANE) {
                InventoryHelper.updateInventory(e.getClickedInventory(), MythicGUI.getInstance().getConfigStorage().get(strSplit[0]), 36, Integer.parseInt(strSplit[1]));
                return;
            }
            if (e.getCurrentItem().getType() == Material.RED_STAINED_GLASS_PANE) {
                InventoryHelper.updateInventory(e.getClickedInventory(), MythicGUI.getInstance().getConfigStorage().get(strSplit[0]), 36, Integer.parseInt(strSplit[1]));
                return;
            }
            if (e.getCurrentItem().getType() == Material.PINK_STAINED_GLASS_PANE) {
                Player player = (Player) e.getWhoClicked();
                Inventory inv = InventoryHelper.createConfigInventory(player, MythicGUI.getInstance().getConfigNames(), 36);
                player.openInventory(inv);
                return;
            }
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mm items give " + e.getWhoClicked().getName() + " " + e.getCurrentItem().getItemMeta().getLocalizedName());
        }
        if (ChatColor.stripColor(e.getView().getTitle()).equals("Config")) {
            e.setCancelled(true);
            String configName = e.getCurrentItem().getItemMeta().getLocalizedName();
            DisplayConfig conf = MythicGUI.getInstance().getConfigStorage().get(configName);
            if (conf != null) {
                Player player = (Player) e.getWhoClicked();
                Inventory inv = InventoryHelper.createInventory(player, conf, 36);
                player.closeInventory();
                player.openInventory(inv);
            }
        }
    }
}
