package dev.nayo.mythicgui;

import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.items.MythicItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
