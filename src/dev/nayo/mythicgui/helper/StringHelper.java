package dev.nayo.mythicgui.helper;

import org.bukkit.ChatColor;

public class StringHelper {
    public static String format(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
