package com.orange.utils;

import java.util.Comparator;
import java.util.Map;

public class MapValueComparator implements Comparator<Map.Entry<Integer, Integer>> {

    @Override
    public int compare(Map.Entry<Integer, Integer> me1, Map.Entry<Integer, Integer> me2) {

        return me2.getValue().compareTo(me1.getValue());
    }
}