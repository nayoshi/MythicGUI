package dev.nayo.mythicgui.commands;

import dev.nayo.mythicgui.helper.StringHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class CommandMythicMobGui implements CommandExecutor {
    private HashMap<String, ISubCommand> commandMap = new HashMap<>();
    public CommandMythicMobGui() {
        commandMap.put("reload", new SubCommandReload());
        commandMap.put("config", new SubCommandConfig());
        commandMap.put("item", new SubCommandItem());
    }

    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length >= 1) {
            ISubCommand cmd = commandMap.get(args[0]);

            if(cmd != null) {
                if(commandSender instanceof ConsoleCommandSender) {
                    if(!cmd.isConsoleExecutable()) {
                        commandSender.sendMessage(StringHelper.print("Nope"));
                        return true;
                    }
                }
                cmd.execute(commandSender, args);
            } else {
                commandSender.sendMessage(StringHelper.print("Invalid Argument. (item/config/reload)."));
            }
        } else {
            commandMap.get("config").execute(commandSender,args);
        }
        return false;
    }
}
