package com.wyuu;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
                    List<String> tempUsers =databaseManager.getTempUsers();
                    for(String tempUser : tempUsers) {
                        OfflinePlayer player= plugin.getServer().getOfflinePlayer(tempUser);
                        PermissionManager permissionManager = new PermissionManager(player,plugin);
                        List<String> havingPermissions = permissionManager.getUsingPermissions("liaotianziti.have");
                        List<String> usingPermissions = permissionManager.getUsingPermissions("liaotianziti.using");
                        if(havingPermissions.size() == usingPermissions.size()) {
                            continue;
                        }
                        for(String usingPermission : usingPermissions) {
                            if(!havingPermissions.contains(usingPermission)) {
                                permissionManager.removePermission(player, usingPermission);
                                havingPermissions.remove(usingPermission);
                            }
                        }
                        if(havingPermissions.size() == usingPermissions.size()){
                            databaseManager.removeTempPermission(tempUser);
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
