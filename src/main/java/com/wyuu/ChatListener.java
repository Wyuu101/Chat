package com.wyuu;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;


public class ChatListener implements Listener {
    private final JavaPlugin plugin;
    public static ChatListener instance;//

    public ChatListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public static ChatListener getInstance(JavaPlugin plugin) {
        if (instance == null) {
            instance = new ChatListener(plugin);
        }
        return instance;
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player= event.getPlayer();
        if(Chat.talkDetectCancelOrNot) {
            if (event.isCancelled()) {
                return;
            }
        }
        // 在 Bukkit/Spigot 中，event.setFormat() 内部使用 String.format() 处理消息
        // 单个 % 在 String.format 中是特殊字符，会被视为格式化占位符
        // 如果不将 % 转换为 %%，当玩家消息中包含 % 时会导致 IllegalFormatException
        String raw_Msg = event.getMessage();
        PermissionManager permissionManager = new PermissionManager(player,plugin);
        List<String> usingPermissions = permissionManager.getUsingPermissions("liaotianziti.using");
        if (!usingPermissions.isEmpty()) {
            Map<String, Function<String,String>> procMap = ChatColorProcessor.getProcMap();
            Function<String,String> procFunction = procMap.get(usingPermissions.get(0));
            if (procFunction != null) {
                raw_Msg =  procFunction.apply(raw_Msg);
            }
        }
        String msg_Proc = PlaceholderAPI.setPlaceholders(player, Chat.talkPrefix).replace("%","%%") +raw_Msg.replace("%","%%");
        
        event.setFormat(msg_Proc);

    }
}
