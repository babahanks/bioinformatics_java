package com.michni.algorithm;

import java.util.*;

public class Utility {

    public static List<String> getMaxFrequencyKeys(Map<String, Integer> map) {
        int max_count = 0;
        List<String> max_keys = new LinkedList<>();

        for (String key: map.keySet()) {
            int count = map.get(key);
            if (count > max_count){
                max_count = count;
                max_keys.clear();
                max_keys.add(key);
            }
            else if (count == max_count){
                max_keys.add(key);
            }
        }
        return max_keys ;
    }


}
