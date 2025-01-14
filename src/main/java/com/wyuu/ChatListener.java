package com.wyuu;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

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
        String raw_Msg = event.getMessage();
        String msg_Proc = PlaceholderAPI.setPlaceholders(player, Chat.talkPrefix).replace("%","%%") +raw_Msg.replace("%","%%");
        event.setFormat(msg_Proc);

    }
}
