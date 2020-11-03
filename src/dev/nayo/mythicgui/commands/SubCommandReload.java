package dev.nayo.mythicgui.commands;

import dev.nayo.mythicgui.MythicGUI;
import dev.nayo.mythicgui.helper.StringHelper;
import org.bukkit.command.CommandSender;

public class SubCommandReload implements ISubCommand {

    // Reloads the plugin and trigger the method in MythicGUI
    public void execute(CommandSender cs, String[] args) {
        MythicGUI.getInstance().onReload();
        cs.sendMessage(StringHelper.print("Plugin is reloaded."));
    }

    public boolean isConsoleExecutable() {
        return true;
    }
}
