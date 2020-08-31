package dev.nayo.mythicgui.commands;

import org.bukkit.command.CommandSender;

public interface ISubCommand {
    void execute(CommandSender cs, String[] args);
    boolean isConsoleExecutable();
}
