package com.wyuu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandExc implements CommandExecutor {
    private final JavaPlugin plugin;

    public CommandExc(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            return false;
        }
        switch (args[0]) {
            case "list":
                CommandFunc.sendChatFontList(sender);
                return true;
            case "enable":
                if(args.length < 3) {
                    sender.sendMessage("&c用法错误");
                    return false;
                }
                Player player = plugin.getServer().getPlayer(args[1]);
                if (player != null) {
                    CommandFunc.changeUsingFont(player, args[2],plugin);
                    sender.sendMessage("切换称号成功");
                    return true;
                }
            default:
                return false;
        }
    }
}

