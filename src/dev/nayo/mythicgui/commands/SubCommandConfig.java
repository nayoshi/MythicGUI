package dev.nayo.mythicgui.commands;

import dev.nayo.mythicgui.MythicGUI;
import dev.nayo.mythicgui.helper.InventoryHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SubCommandConfig implements ISubCommand {
    public void execute(CommandSender cs, String[] args) {
        Inventory inv = InventoryHelper.createConfigInventory((Player) cs, MythicGUI.getInstance().getConfigNames(), 36);
        Player player = (Player) cs;
        player.openInventory(inv);
    }

    public boolean isConsoleExecutable() {
        return false;
    }
}
