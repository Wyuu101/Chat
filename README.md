### 1、简介：自研聊天系统，用于自定义聊天前缀和聊天字体

---

### 2、命令
```
/chatfont list  - 列出所有字体ID
/chatfont temp <player> - 把玩家列入过期权限检查名单中(配合lp临时权限使用)
/chatfont clear <player> - 清空玩家当前使用的聊天字体
/chatfont enable <player> <fontID> - 使玩家使用某个聊天字体进行聊天
/chatfont test - 强制进行一次过期权限检查

以上指令均需要Chat.Operator权限，默认仅OP拥有
```
### 特别注意：请保证在玩家拥有liaotianziti.have.<fontID>的权限后再基于其 liaotianziti.using.<fontID>，否则可能会引发错误。

---

### 3、占位符
```
%chatfont_amount_<fontid>% - 佩戴中返回64，拥有返回2，未拥有返回1（用于菜单）
%chatfont_state_<fontid>% - 佩戴中返回 “已选择”，拥有返回 “点击选择”，未拥有返回 “错误的ID”（用于菜单）
%chatfont_enchant_<fontid>% - 佩戴中返回1，其余返回0（用于菜单）    
```
---

### 4、特性介绍
- 支持PlaceholderAPI占位符
- 聊天事件监听优先级可自定义，默认为NORMAL
- 使用Mysql数据库

---

### 5、config.yml
```yml
#GuildPlugin by X_32xm
#for only Dxzzz.net
#QQ:2644489337

ChatSettings:
  #聊天事件触发优先级
  #可选参数: MONITOR/HIGHEST/HIGH/NORMAL/LOW/LOWEST 从LOW到MONITOR，监听到的消息越来越晚！
  Priority: NORMAL

  #是否判断event.isCancelled()
  DetectCancelOrNot: true

  #聊天前缀(支持PlaceholderAPI)
  TalkPrefix: "%rank_rank%§7%tag_all% §8> §f "

  #只有本项为true，过期权限检查才会生效。注意：后端服务器请保证有且仅有一个服务器将此项设置为true
  AsLobby: false

#数据库设置(仅支持MySQL)
DataBase:
  MySQL:
    Host: localhost
    Username: root
    Password:
    Port: 3306


```
