package dev.nayo.mythicgui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class DisplayConfig {
    private HashMap<String, DisplayItem> itemStorage = new HashMap<>();
    private ArrayList<String> itemNames = new ArrayList<>();
    private String name;

    public DisplayConfig(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public DisplayItem getItem(String name) {
        return itemStorage.get(name);
    }
    public DisplayItem getThumbnail() {
        return itemStorage.get(itemNames.get(0));
    }
    public void addItem(DisplayItem item) {
        DisplayItem di = itemStorage.putIfAbsent(item.getName(), item);
        if (di == null) {
            itemNames.add(item.getName());
            Collections.sort(itemNames);
        }
    }

    public ArrayList<String> getItemNames() {
        return itemNames;
    }
}
