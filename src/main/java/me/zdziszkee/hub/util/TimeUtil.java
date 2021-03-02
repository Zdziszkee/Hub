package me.zdziszkee.hub.util;


public class TimeUtil {
    public static int convertMillisToDays(long millis){
        return  (int) (millis / (1000*60*60*24));    }
}
