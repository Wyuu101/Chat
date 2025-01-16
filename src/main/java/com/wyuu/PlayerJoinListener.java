package com.wyuu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class PlayerJoinListener implements Listener {
    private final JavaPlugin plugin;
    public static PlayerJoinListener instance;//

    public PlayerJoinListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public static PlayerJoinListener getInstance(JavaPlugin plugin) {
        if (instance == null) {
            instance = new PlayerJoinListener(plugin);
        }
        return instance;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //plugin.getLogger().info("玩家加入");
        if(Chat.asLobby) {
            Player player = event.getPlayer();
            if (player.isOp()) {
                //plugin.getLogger().info("是OP");
                PermissionManager permissionManager = new PermissionManager(player, plugin);
                List<String> havingPermission = permissionManager.getUsingPermissions("liaotianziti.have");
                plugin.getLogger().info(String.valueOf(havingPermission.size()));
                plugin.getLogger().info(String.valueOf(CommandFunc.fonts.size()));
                if (havingPermission.size() == CommandFunc.fonts.size()) {
                    return;
                }
                for (String fontID : CommandFunc.fonts) {
                    //plugin.getLogger().info("添加");
                    permissionManager.addPermission(player, "liaotianziti.have." + fontID);
                }
            }
        }
    }
}
