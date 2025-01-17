package net.dxzzz.chat;

import org.bukkit.event.EventPriority;


import java.util.Arrays;
import java.util.List;


public class UniversalModule {
    private static final List<String> options = Arrays.asList("MONITOR","HIGHEST","HIGH","NORMAL","LOW","LOWEST");
    public static final EventPriority GetEventPriority(String param){
        if(param.equalsIgnoreCase("MONITOR")){
            return EventPriority.MONITOR;
        }
        else if(param.equalsIgnoreCase("HIGHEST")){
            return EventPriority.HIGHEST;
        }
        else if(param.equalsIgnoreCase("HIGH")){
            return EventPriority.HIGH;
        }
        else if(param.equalsIgnoreCase("NORMAL")){
            return EventPriority.NORMAL;
        }
        else if(param.equalsIgnoreCase("LOW")){
            return EventPriority.LOW;
        }
        else {
            return EventPriority.LOWEST;
        }
    }
    public static final boolean isTalkPriorityParamTrue(String param){
        for(String option: options){
            if(param.equals(option)){
                return true;
            }
        }
        return false;
    }
}
