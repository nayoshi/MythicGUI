package dev.nayo.mythicgui.commands;

import dev.nayo.mythicgui.DisplayConfig;
import dev.nayo.mythicgui.MythicGUI;
import dev.nayo.mythicgui.helper.InventoryHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public class SubCommandItem implements ISubCommand{
    public void execute(CommandSender cs, String[] args) {
        DisplayConfig items = MythicGUI.getInstance().getAllItemsConfig();
        Player player = (Player) cs;
        Inventory inv = InventoryHelper.createInventory(player,items,36);
        player.openInventory(inv);
    }

    public boolean isConsoleExecutable() {
        return false;
    }
}
