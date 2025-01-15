package com.wyuu;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

public class ChatColorProcessor {
    private static final Random random = new Random();
    public static Map<String, Consumer<String>> procMap;
    // 彩虹颜色数组（使用§符号）
    private static final String[] RAINBOW_COLORS = {
            "§c", // 红色
            "§6", // 金色
            "§e", // 黄色
            "§a", // 绿色
            "§b", // 天蓝色
            "§d"  // 粉色
    };
    public void initProcMap(){
        procMap = new HashMap<>();
        procMap.put("liaotianziti.using.caihong",ChatColorProcessor::toCaiHong);
        procMap.put("liaotianziti.using.fenlan",ChatColorProcessor::toPinkBlue);
        procMap.put("liaotianziti.using.gangqin",ChatColorProcessor::toGangQin);
        procMap.put("liaotianziti.using.shengdan",ChatColorProcessor::toShengDan);
        procMap.put("liaotianziti.using.elongpaoxiao",ChatColorProcessor::toELongPaoXiao);
        procMap.put("liaotianziti.using.xiaotuji",ChatColorProcessor::toRabbit);
        procMap.put("liaotianziti.using.miaomiao",ChatColorProcessor::toMiaoMiao);
        procMap.put("liaotianziti.using.huohua",ChatColorProcessor::toHuoHua);
        procMap.put("liaotianziti.using.kuangbao",ChatColorProcessor::toKuangBao);
        procMap.put("liaotianziti.using.didao",ChatColorProcessor::toDiDiao);
        procMap.put("liaotianziti.using.xuehua",ChatColorProcessor::toSnow);
        procMap.put("liaotianziti.using.zhexue",ChatColorProcessor::toZheXue);
        procMap.put("liaotianziti.using.aoaojiao",ChatColorProcessor::toAaoAaoJiao);
        procMap.put("liaotianziti.using.sisi",ChatColorProcessor::toSiSi);
    }


    /**
     * 将消息转换为彩虹渐变色
     * @param message 原始消息
     * @return 处理后的彩虹渐变色消息
     */
    public static String toCaiHong(String message) {
        StringBuilder coloredMessage = new StringBuilder();
        char[] chars = message.toCharArray();
        
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                String color = RAINBOW_COLORS[i % RAINBOW_COLORS.length];
                coloredMessage.append(color).append(chars[i]);
            } else {
                coloredMessage.append(chars[i]);
            }
        }
        
        return coloredMessage.toString();
    }

    /**
     * 将消息转换为橙色并在末尾添加"嗷~"
     * @param message 原始消息
     * @return 处理后的橙色消息
     */
    public static String toAaoAaoJiao(String message) {
        return "§6§l" + message + " 嗷~";
    }

    /**
     * 将消息转换为深绿色并在末尾添加"嘶嘶~"
     * @param message 原始消息
     * @return 处理后的橙色消息
     */
    public static String toSiSi(String message) {
        return "§2§l" + message + " 嘶嘶~";
    }

    /**
     * 将消息转换为粉蓝交替
     * @param message 原始消息
     * @return 处理后的粉蓝交替消息
     */
    public static String toPinkBlue(String message) {
        StringBuilder coloredMessage = new StringBuilder();
        char[] chars = message.toCharArray();
        
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                String color = (i % 2 == 0) ? "§d" : "§b"; // 偶数位粉色，奇数位蓝色
                coloredMessage.append(color).append(chars[i]);
            } else {
                coloredMessage.append(chars[i]);
            }
        }
        
        return coloredMessage.toString();
    }

    /**
     * 将消息转换为白灰黑循环交替
     * @param message 原始消息
     * @return 处理后的白灰黑交替消息
     */
    public static String toGangQin(String message) {
        StringBuilder coloredMessage = new StringBuilder();
        char[] chars = message.toCharArray();
        String[] colors = {"§f", "§7", "§0"}; // 白、灰、黑
        
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                String color = colors[i % 3];
                coloredMessage.append(color).append(chars[i]);
            } else {
                coloredMessage.append(chars[i]);
            }
        }
        
        return coloredMessage.toString();
    }

    /**
     * 将消息转换为绿红白黄循环交替
     * @param message 原始消息
     * @return 处理后的绿红白黄交替消息
     */
    public static String toShengDan(String message) {
        StringBuilder coloredMessage = new StringBuilder();
        char[] chars = message.toCharArray();
        String[] colors = {"§a", "§c", "§f", "§e"}; // 绿、红、白、黄
        
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                String color = colors[i % 4];
                coloredMessage.append(color).append(chars[i]);
            } else {
                coloredMessage.append(chars[i]);
            }
        }
        
        return coloredMessage.toString();
    }

    /**
     * 将消息转换为红色并添加斜体嗷呜表情
     * @param message 原始消息
     * @return 处理后的红色消息加表情
     */
    public static String toELongPaoXiao(String message) {
        return "§c" + message + "§c§o 嗷呜~┗|｀O′|┛";
    }

    /**
     * 将消息转换为白色并添加兔耳表情
     * @param message 原始消息
     * @return 处理后的白色消息加表情
     */
    public static String toRabbit(String message) {
        return "§f" + message + "₍  ̥ ̞ ̥ᐢ₎ ";
    }

    /**
     * 将消息转换为粉色并添加斜体猫咪表情
     * @param message 原始消息
     * @return 处理后的粉色消息加表情
     */
    public static String toMiaoMiao(String message) {
        return "§d" + message + "§d§o ｡^･ｪ･^｡喵~";
    }

    /**
     * 将消息转换为随机长度的黄橙交替
     * @param message 原始消息
     * @return 处理后的黄橙交替消息
     */
    public static String toHuoHua(String message) {
        StringBuilder coloredMessage = new StringBuilder();
        char[] chars = message.toCharArray();
        boolean isYellow = random.nextBoolean();
        int currentLength = 0;
        int targetLength = random.nextInt(3) + 1; // 1-3个字符的随机长度
        
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                if (currentLength >= targetLength) {
                    isYellow = !isYellow;
                    currentLength = 0;
                    targetLength = random.nextInt(3) + 1;
                }
                String color = isYellow ? "§e" : "§6";
                coloredMessage.append(color).append(chars[i]);
                currentLength++;
            } else {
                coloredMessage.append(chars[i]);
            }
        }
        return coloredMessage.toString();
    }

    /**
     * 将消息转换为随机长度的红深红交替
     * @param message 原始消息
     * @return 处理后的红深红交替消息
     */
    public static String toKuangBao(String message) {
        StringBuilder coloredMessage = new StringBuilder();
        char[] chars = message.toCharArray();
        boolean isRed = random.nextBoolean();
        int currentLength = 0;
        int targetLength = random.nextInt(3) + 1;
        
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                if (currentLength >= targetLength) {
                    isRed = !isRed;
                    currentLength = 0;
                    targetLength = random.nextInt(3) + 1;
                }
                String color = isRed ? "§c" : "§4";
                coloredMessage.append(color).append(chars[i]);
                currentLength++;
            } else {
                coloredMessage.append(chars[i]);
            }
        }
        return coloredMessage.toString();
    }

    /**
     * 将消息转换为随机长度的绿深绿交替
     * @param message 原始消息
     * @return 处理后的绿深绿交替消息
     */
    public static String toYuanLiang(String message) {
        StringBuilder coloredMessage = new StringBuilder();
        char[] chars = message.toCharArray();
        boolean isGreen = random.nextBoolean();
        int currentLength = 0;
        int targetLength = random.nextInt(3) + 1;
        
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                if (currentLength >= targetLength) {
                    isGreen = !isGreen;
                    currentLength = 0;
                    targetLength = random.nextInt(3) + 1;
                }
                String color = isGreen ? "§a" : "§2";
                coloredMessage.append(color).append(chars[i]);
                currentLength++;
            } else {
                coloredMessage.append(chars[i]);
            }
        }
        return coloredMessage.toString();
    }

    /**
     * 将消息转换为随机长度的灰黑交替
     * @param message 原始消息
     * @return 处理后的灰黑交替消息
     */
    public static String toDiDiao(String message) {
        StringBuilder coloredMessage = new StringBuilder();
        char[] chars = message.toCharArray();
        boolean isGrey = random.nextBoolean();
        int currentLength = 0;
        int targetLength = random.nextInt(3) + 1;
        
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                if (currentLength >= targetLength) {
                    isGrey = !isGrey;
                    currentLength = 0;
                    targetLength = random.nextInt(3) + 1;
                }
                String color = isGrey ? "§7" : "§0";
                coloredMessage.append(color).append(chars[i]);
                currentLength++;
            } else {
                coloredMessage.append(chars[i]);
            }
        }
        return coloredMessage.toString();
    }

    /**
     * 将消息转换为随机长度的淡蓝青色下划线交替
     * @param message 原始消息
     * @return 处理后的淡蓝青色下划线交替消息
     */
    public static String toTianKong(String message) {
        StringBuilder coloredMessage = new StringBuilder();
        char[] chars = message.toCharArray();
        boolean isAqua = random.nextBoolean();
        int currentLength = 0;
        int targetLength = random.nextInt(3) + 1;
        
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                if (currentLength >= targetLength) {
                    isAqua = !isAqua;
                    currentLength = 0;
                    targetLength = random.nextInt(3) + 1;
                }
                String color = isAqua ? "§b" : "§3§n";
                coloredMessage.append(color).append(chars[i]);
                currentLength++;
            } else {
                coloredMessage.append(chars[i]);
            }
        }
        return coloredMessage.toString();
    }

    /**
     * 将消息转换为白色，并随机长度的加粗下划线交替
     * @param message 原始消息
     * @return 处理后的白色加粗下划线交替消息
     */
    public static String toSnow(String message) {
        StringBuilder coloredMessage = new StringBuilder();
        char[] chars = message.toCharArray();
        boolean isBold = random.nextBoolean();
        int currentLength = 0;
        int targetLength = random.nextInt(3) + 1;
        
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                if (currentLength >= targetLength) {
                    isBold = !isBold;
                    currentLength = 0;
                    targetLength = random.nextInt(3) + 1;
                }
                String style = isBold ? "§f§l" : "§f§n";
                coloredMessage.append(style).append(chars[i]);
                currentLength++;
            } else {
                coloredMessage.append(chars[i]);
            }
        }
        return coloredMessage.toString();
    }

    /**
     * 将消息转换为随机长度的黄色和带下划线的橙色交替
     * @param message 原始消息
     * @return 处理后的黄色和带下划线的橙色交替消息
     */
    public static String toZheXue(String message) {
        StringBuilder coloredMessage = new StringBuilder();
        char[] chars = message.toCharArray();
        boolean isYellow = random.nextBoolean();
        int currentLength = 0;
        int targetLength = random.nextInt(3) + 1;
        
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                if (currentLength >= targetLength) {
                    isYellow = !isYellow;
                    currentLength = 0;
                    targetLength = random.nextInt(3) + 1;
                }
                String color = isYellow ? "§e" : "§6§n"; // 黄色或带下划线的橙色
                coloredMessage.append(color).append(chars[i]);
                currentLength++;
            } else {
                coloredMessage.append(chars[i]);
            }
        }
        return coloredMessage.toString();
    }
} 