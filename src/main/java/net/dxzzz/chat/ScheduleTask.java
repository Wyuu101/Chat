package net.dxzzz.chat;

import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ScheduleTask {
        private static JavaPlugin plugin;
        private static DatabaseManager databaseManager;
        public ScheduleTask(JavaPlugin plugin,DatabaseManager databaseManager) {
            this.plugin = plugin;
            this.databaseManager =databaseManager;
        }
        public void permissionCheckTask() {
            long delay = GetInitialDelayInTicks();
            long period = TimeUnit.HOURS.toSeconds(6) * 20;

            new BukkitRunnable() {
                @Override
                public void run() {
                    List<String> tempUsers =Chat.databaseManager.getTempUsers();
                    //plugin.getLogger().info(tempUsers.toString());
                    for(String tempUser : tempUsers) {
                        OfflinePlayer player2= plugin.getServer().getOfflinePlayer(tempUser);
                        //plugin.getLogger().info("玩家名:"+player2.getName());

                        PermissionManager permissionManager = new PermissionManager(player2,plugin);
                        List<String> havingPermissions = permissionManager.getUsingPermissions("liaotianziti.have");
                        List<String> usingPermissions = permissionManager.getUsingPermissions("liaotianziti.using");
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
                        if(havingPermissions.size() == usingPermissions.size()){
                            Chat.databaseManager.removeTempPermission(tempUser);
                        }
                    }
                    plugin.getLogger().info("已完成聊天字体过期权限检查");
                }
            }.runTaskTimerAsynchronously(plugin, delay, period);
        }


        private long GetInitialDelayInTicks() {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime nextMidnight = now.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);

            ZonedDateTime zonedNow = ZonedDateTime.of(now, ZoneId.systemDefault());
            ZonedDateTime zonedNextMidnight = ZonedDateTime.of(nextMidnight, ZoneId.systemDefault());

            long initialDelayInSeconds = zonedNextMidnight.toEpochSecond() - zonedNow.toEpochSecond();
            return initialDelayInSeconds * 20;
        }
}
