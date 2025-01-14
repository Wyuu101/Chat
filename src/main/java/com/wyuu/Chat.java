package com.wyuu;

import me.clip.placeholderapi.PlaceholderAPIPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Chat extends JavaPlugin {
    public static PlaceholderAPIPlugin placeholderAPI;
    public final Logger logger= getLogger();
    private static ChatListener ChatListener;
    public static String talkPriority;
    public static String talkPrefix;
    public static Boolean talkDetectCancelOrNot;
    public static boolean loadConfigSuccess ;

    @Override
    public void onEnable() {
        logger.info("===========[Chat正在加载中]===========");
        logger.info("Author: X_32mx");
        logger.info("QQ: 2644489337");
        logger.info("This plugin is only for Dxzzz.net");
        this.saveDefaultConfig();
        this.reloadConfig();
        this.loadConfig();


        if(!this.setupPlaceholderAPI()){
            logger.severe(ChatColor.RED+"未找到PlaceholderAPI前置插件,部分功能将失效。");
        }
        else {
            logger.info("已查找到PlaceholderAPI");
        }

        if(!loadConfigSuccess){
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        ChatListener= new ChatListener(this);
        Bukkit.getPluginManager().registerEvent(AsyncPlayerChatEvent.class,ChatListener, UniversalModule.GetEventPriority(talkPriority),  new EventExecutor() {
            @Override
            public void execute(Listener listener, Event event) throws EventException {
                if (event instanceof AsyncPlayerChatEvent) {
                    ChatListener.onPlayerChat((AsyncPlayerChatEvent) event);
                }
            }
        },this);


        logger.info("==========[Chat已加载完毕]=========");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadConfig() {

        talkPriority = this.getConfig().getString("ChatSettings.Priority","NORMAL");
        talkDetectCancelOrNot =  this.getConfig().getBoolean("ChatSettings.DetectCancelOrNot",false);
        if(!UniversalModule.isTalkPriorityParamTrue(talkPriority)){
            logger.severe(ChatColor.RED+"聊天监听优先级配置有误，请前往config.yml检查!");
            logger.severe(ChatColor.RED+"插件开始自动卸载");
            loadConfigSuccess=false;
            return;
        }
        talkPrefix = this.getConfig().getString("ChatSettings.TalkPrefix");

        loadConfigSuccess=true;
    }


    private boolean setupPlaceholderAPI() {
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            return false;
        }
        placeholderAPI = (PlaceholderAPIPlugin) getServer().getPluginManager().getPlugin("PlaceholderAPI");
        return placeholderAPI != null;
    }

}
