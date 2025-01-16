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
    public static DatabaseManager databaseManager;
    public static PlaceholderAPIPlugin placeholderAPI;
    public  final Logger logger= getLogger();
    private static ChatListener ChatListener;
    private static PlayerJoinListener playerJoinListener;
    public static String talkPriority;
    public static String talkPrefix;
    public static Boolean talkDetectCancelOrNot;
    public static ScheduleTask scheduleTask;
    public static boolean asLobby;
    public static boolean loadConfigSuccess ;
    private String username;
    private String password;
    private int port;
    private String host;
    @Override
    public void onEnable() {
        logger.info("===========[Chat正在加载中]===========");
        logger.info("Author: X_32mx");
        logger.info("QQ: 2644489337");
        logger.info("This plugin is only for Dxzzz.net");
        this.saveDefaultConfig();
        this.reloadConfig();
        this.loadConfig();

        if(!loadConfigSuccess){
            getServer().getPluginManager().disablePlugin(this);
            return;
        }



        if(!this.setupPlaceholderAPI()){
            logger.severe(ChatColor.RED+"未找到PlaceholderAPI前置插件,部分功能将失效。");
        }
        else {
            logger.info("已查找到PlaceholderAPI");
        }

        databaseManager = new DatabaseManager(username, password, host, port);

        if (databaseManager.GetUserDb() == null) {
            logger.severe("创建数据库连接失败，请检查配置，插件即将自动卸载.");
            loadConfigSuccess = false;
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        databaseManager.createForm_TempPermission();
        scheduleTask = new ScheduleTask(this,databaseManager);
        if(asLobby){
            scheduleTask.permissionCheckTask();
            logger.info("过期权限检查任务已启动，周期为6h");
        }
        playerJoinListener = new PlayerJoinListener(this);
        Bukkit.getPluginManager().registerEvents(playerJoinListener, this);
        ChatListener= new ChatListener(this);
        Bukkit.getPluginManager().registerEvent(AsyncPlayerChatEvent.class,ChatListener, UniversalModule.GetEventPriority(talkPriority),  new EventExecutor() {
            @Override
            public void execute(Listener listener, Event event) throws EventException {
                if (event instanceof AsyncPlayerChatEvent) {
                    ChatListener.onPlayerChat((AsyncPlayerChatEvent) event);
                }
            }
        },this);
        ChatPlaceholderExpansion chatPlaceholderExpansion = ChatPlaceholderExpansion.getInstance();
        chatPlaceholderExpansion.register();
        //初始化功能函数映射
        ChatColorProcessor.initProcMap();
        //初始化命令执行类中的字体名称列表
        CommandFunc.initFontsList();
        Bukkit.getPluginCommand("chatfont").setExecutor(new CommandExc(this));

        logger.info("==========[Chat已加载完毕]=========");
    }

    @Override
    public void onDisable() {
        databaseManager.closeDataBase();
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
        asLobby = this.getConfig().getBoolean("ChatSettings.AsLobby",false);
        username = this.getConfig().getString("DataBase.MySQL.Username", "root");
        password = this.getConfig().getString("DataBase.MySQL.Password");
        host = this.getConfig().getString("DataBase.MySQL.Host", "localhost");
        port = this.getConfig().getInt("DataBase.MySQL.Port", 3306);
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


