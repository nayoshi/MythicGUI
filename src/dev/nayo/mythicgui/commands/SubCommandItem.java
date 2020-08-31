package dev.nayo.mythicgui.commands;

import dev.nayo.mythicgui.DisplayConfig;
import dev.nayo.mythicgui.MythicGUI;
import dev.nayo.mythicgui.helper.InventoryHelper;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.items.MythicItem;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
