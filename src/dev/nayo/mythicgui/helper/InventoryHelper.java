package dev.nayo.mythicgui.helper;

import dev.nayo.mythicgui.DisplayConfig;
import dev.nayo.mythicgui.MythicGUI;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.items.MythicItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InventoryHelper {

    //Creates the inventories for the plugin
    public static Inventory updateInventory(Inventory inv, DisplayConfig displayConfig, int size, int pageNum) {
        inv.clear();

        int itemLimit = size - 9;
        int itemL = itemLimit;
        int itemIndex = itemLimit * pageNum;
        int endingIndex = itemLimit * (pageNum + 1);
        List<String> items = displayConfig.getItemNames();

        if (items.size() <= endingIndex) {
            itemL = endingIndex - items.size();
            itemL = itemLimit - itemL;
        }

        System.out.println(itemLimit + " " + itemL + " " + itemIndex + " " + endingIndex + " " + items.size());

        for (int i = 0; i < itemL; i++) {
            MythicItem mi = displayConfig.getItem(items.get(i + itemIndex)).getMythicItem();
            ItemStack item = BukkitAdapter.adapt(mi.generateItemStack(1));
            ItemMeta meta = item.getItemMeta();
            meta.setLocalizedName(mi.getInternalName());
            item.setItemMeta(meta);
            inv.addItem(item);
        }

        ItemStack page = new ItemStack(Material.PINK_STAINED_GLASS_PANE);
        ItemMeta pageMeta = page.getItemMeta();
        pageMeta.setLocalizedName(displayConfig.getName() + ":" + pageNum);
        pageMeta.setDisplayName(StringHelper.format("&r&fReturn To Config Page"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.RESET + "Page " + (pageNum + 1));

        pageMeta.setLore(lore);
        page.setItemMeta(pageMeta);
        inv.setItem(size - 5, page);
        if (items.size() > endingIndex) {
            page = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            pageMeta = page.getItemMeta();
            pageMeta.setLocalizedName(displayConfig.getName() + ":" + (pageNum + 1));
            pageMeta.setDisplayName(StringHelper.format("&rNext Page"));
            page.setItemMeta(pageMeta);
            inv.setItem(size - 1, page);
        }
        if(pageNum != 0) {
            page = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            pageMeta = page.getItemMeta();
            pageMeta.setLocalizedName(displayConfig.getName() + ":" + (pageNum - 1));
            pageMeta.setDisplayName(StringHelper.format("&rPrev Page"));
            page.setItemMeta(pageMeta);
            inv.setItem(size - 9, page);
        }

        return inv;
    }

    public static Inventory createInventory(Player player, DisplayConfig displayConfig, int size) {
        Inventory inv = Bukkit.createInventory(player, size, StringHelper.format("&rMythicGUI"));
        return updateInventory(inv, displayConfig, size, 0);
    }
    public static Inventory updateConfigInventory(Inventory inv, List<String> str, int size) {
        inv.clear();
        for(String string: str) {
            ItemStack item = MythicGUI.getInstance().getConfigStorage().get(string).getThumbnail().getItem();
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setLocalizedName(string);
            itemMeta.setDisplayName(ChatColor.RESET + string);
            itemMeta.setLore(null);
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
            item.setItemMeta(itemMeta);
            inv.addItem(item);
        }
        ItemStack nayoHead = new ItemStack(Material.PLAYER_HEAD);
        Bukkit.getUnsafe().modifyItemStack(nayoHead, "{SkullOwner:{Id:[I;-657158004,-1808708937,-1827101785,-1708230178],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzk3YjkxZjdhN2FjMWMzOWVhYzIxZDA2Nzg4Mjg4MzU4ZTIxMWFlZjIzYzBiNDAyMWY5ODNkOGRjNTU5YmQwYiJ9fX0=\"}]}}}");
        ItemMeta nayoMeta = nayoHead.getItemMeta();
        nayoMeta.setDisplayName(StringHelper.format("&r&6Mythic Item Browser"));
        nayoMeta.setLocalizedName("nayo");
        List<String> lore = new ArrayList<>();
        lore.add(StringHelper.format("&r&3Author: mayo :P"));
        lore.add(StringHelper.format("&r&8imagine writing plugins for a server xdddd"));
        nayoMeta.setLore(lore);
        nayoHead.setItemMeta(nayoMeta);
        inv.setItem(size-5, nayoHead);
        return inv;
    }
    public static Inventory createConfigInventory(Player player, List<String> str, int size) {
        Inventory inv = Bukkit.createInventory(player, size, StringHelper.format("&rConfig"));
        updateConfigInventory(inv, str, size);
        return inv;
    }
}
