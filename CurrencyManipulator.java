package com.javarush.task.task26.task2613;

import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator {
    private Map<Integer, Integer> denominations;
    private String currencyCode;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        this.denominations = new TreeMap<>();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count){
        if (denominations.containsKey(denomination)){
            denominations.put(denomination, denominations.get(denomination) + 1);
        }
        else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount(){
        int amount = 0;
        for (Integer denomination : denominations.keySet()) {
            int count = denominations.get(denomination);
            amount += denomination * count;
        }
        return amount;
    }
}
