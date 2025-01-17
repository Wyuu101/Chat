package net.dxzzz.chat;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CommandFunc {

    public static List<String> fonts=new ArrayList<>();
    public static void initFontsList(){
        fonts.add("caihong");
        fonts.add("fenlan");
        fonts.add("gangqin");
        fonts.add("shengdan");
        fonts.add("elongpaoxiao");
        fonts.add("xiaotuji");
        fonts.add("miaomiao");
        fonts.add("huohua");
        fonts.add("kuangbao");
        fonts.add("didiao");
        fonts.add("xuehua");
        fonts.add("zhexue");
        fonts.add("aoaojiao");
        fonts.add("sisi");
        fonts.add("yuanliang");
        fonts.add("tiankong");
    }


    public static void sendChatFontList(CommandSender sender) {
        StringBuilder msg = new StringBuilder("可选择的聊天字体ID如下:\n");
        for (String font : fonts) {
            msg.append("- ").append(font).append("\n");
        }
        sender.sendMessage(msg.toString());
    }

    public static void changeUsingFont(Player player, String font, JavaPlugin plugin) {
        if(fonts.contains(font)){
           PermissionManager permissionManager = new PermissionManager(player,plugin);
           List<String> usingPermissions =permissionManager.getUsingPermissions("liaotianziti.using");
           if(usingPermissions.size()>0){
               if(usingPermissions.get(0).equalsIgnoreCase("liaotianziti.using."+font)){
                   return;
               }
               else {
                   for(String usingPermission : usingPermissions){
                       permissionManager.removePermission(player, usingPermission);
                   }
                   permissionManager.addPermission(player, "liaotianziti.using."+font);
               }
           }
           else {
               permissionManager.addPermission(player, "liaotianziti.using."+font);
           }
        }
    }
}
