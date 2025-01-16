package com.wyuu;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

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
                    sender.sendMessage("§c用法错误");
                    return false;
                }
                Player player = plugin.getServer().getPlayer(args[1]);
                if (player != null) {
                    CommandFunc.changeUsingFont(player, args[2],plugin);
//                    sender.sendMessage("切换称号成功");
                    return true;
                }
            case "clear":
                if(args.length < 2) {
                    sender.sendMessage("§c用法错误");
                    return false;
                }
                Player p1  = plugin.getServer().getPlayer(args[1]);
                if (p1 != null) {
                    PermissionManager permissionManager = new PermissionManager(p1,plugin);
                    List<String> usingPermissions = permissionManager.getUsingPermissions("liaotianziti.using");
                    for(String usingPermission : usingPermissions) {
                        permissionManager.removePermission(p1, usingPermission);
                    }
                }
                return true;
            case "temp":
                if(args.length < 2) {
                    sender.sendMessage("§c用法错误");
                    return false;
                }
                Player p  = plugin.getServer().getPlayer(args[1]);
                if (p != null) {
                    Chat.databaseManager.addTempPermission(p.getUniqueId().toString(), args[1]);
                    return true;
                }
            case "test":
                List<String> tempUsers =Chat.databaseManager.getTempUsers();
                //plugin.getLogger().info(tempUsers.toString());
                for(String tempUser : tempUsers) {
                    OfflinePlayer player2= plugin.getServer().getOfflinePlayer(tempUser);
                    //plugin.getLogger().info("玩家名:"+player2.getName());

                    PermissionManager permissionManager = new PermissionManager(player2,plugin);
                    List<String> havingPermissions = permissionManager.getUsingPermissions("liaotianziti.have");
                    List<String> usingPermissions = permissionManager.getUsingPermissions("liaotianziti.using");
//                    plugin.getLogger().info("Qhave"+havingPermissions.toString());
//                    plugin.getLogger().info("Quse"+usingPermissions.toString());
                    if(havingPermissions.size() == usingPermissions.size()) {
                        continue;
                    }
                    List<String> usingPermissionsCopy = new ArrayList<>(usingPermissions);
                    for(String usingPermission : usingPermissionsCopy) {
                        if(!havingPermissions.contains(usingPermission)) {
                            permissionManager.removePermission(player2, usingPermission);
                            usingPermissions.remove(usingPermission);
                        }
                    }
//                    plugin.getLogger().info("Hhave"+havingPermissions.toString());
//                    plugin.getLogger().info("Huse"+usingPermissions.toString());
                    if(havingPermissions.size() == usingPermissions.size()){
                        Chat.databaseManager.removeTempPermission(tempUser);
                    }
                }
                plugin.getLogger().info("已完成聊天字体过期权限检查");
                return true;
            default:
                return false;
        }
    }
}

