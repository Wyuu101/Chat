package com.wyuu;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class PermissionManager {
    public static LuckPerms luckPermsApi= LuckPermsProvider.get();
    private User user;
    private Player player;
    private Logger logger;

    public PermissionManager(Player player, JavaPlugin javaPlugin) {
        this.player = player;
        this.logger = javaPlugin.getLogger();
        this.user = luckPermsApi.getUserManager().getUser(player.getUniqueId());
    }

    private List<String> getAllPermissions() {
        List<String> permissionsList = new ArrayList<>();
        // 获取所有权限并添加到列表
        Collection<Node> collections= user.getNodes();
        // 检查用户对象是否为空
        if (user != null) {
            // 获取权限映射（权限名称 -> 是否启用）
            Map<String, Boolean> permissionMap = user.getCachedData().getPermissionData().getPermissionMap();

            // 遍历权限映射，将权限名称添加到列表中
            permissionMap.forEach((permission, value) -> {
                if (value) {  // 只添加启用的权限
                    permissionsList.add(permission);
                }
            });
        } else {
            // 如果用户对象为空，输出日志提示
            logger.warning("无法找到玩家 " + player.getName() + " 的权限信息！");
        }
        logger.info(permissionsList.toString());
        return permissionsList;
    }
    //获取正在使用聊天字体的权限节点: liaotianziti.using.xxx
    public List<String> getUsingPermissions(String head) {
        List<String> originPermissions = getAllPermissions();
        List<String> filteredPermissions = new ArrayList<>();
        // 遍历原始权限列表
        for (String permission : originPermissions) {
            // 如果权限以指定的前缀开头，则添加到新的列表中
            if (permission.startsWith(head)) {
                filteredPermissions.add(permission);
            }
        }
        return filteredPermissions;
    }

}

