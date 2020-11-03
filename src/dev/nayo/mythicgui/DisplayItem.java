package dev.nayo.mythicgui;

import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.items.MythicItem;
import org.bukkit.inventory.ItemStack;

// DisplayItem holds all the MythicItem and creates the item for display
public class DisplayItem {
    private String name;
    private MythicItem mythicItem;
    public DisplayItem(MythicItem item) {
        this.mythicItem = item;
        this.name = item.getInternalName();

    }
    public ItemStack getItem() {
        return BukkitAdapter.adapt(mythicItem.generateItemStack(1));
    }
    public String getName() {
        return name;
    }

    public MythicItem getMythicItem() {
        return mythicItem;
    }
}
