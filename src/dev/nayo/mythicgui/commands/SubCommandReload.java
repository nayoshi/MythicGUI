package dev.nayo.mythicgui.commands;

import dev.nayo.mythicgui.MythicGUI;
import org.bukkit.command.CommandSender;

public class SubCommandReload implements ISubCommand {
    public void execute(CommandSender cs, String[] args) {
        MythicGUI.getInstance().onReload();
        cs.sendMessage("plugin reloaded");
    }

    public boolean isConsoleExecutable() {
        return true;
    }
}
