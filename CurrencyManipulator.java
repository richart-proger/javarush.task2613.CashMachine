package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

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

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int amount = 0;
        for (Integer denomination : denominations.keySet()) {
            int count = denominations.get(denomination);
            amount += denomination * count;
        }
        return amount;
    }

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        int expectedSum = expectedAmount;
        HashMap<Integer, Integer> copyDenominations = new HashMap<>(denominations);

        ArrayList<Integer> keysDenom = new ArrayList<>(this.denominations.keySet());

        Collections.sort(keysDenom);
        Collections.reverse(keysDenom);

        Map<Integer, Integer> resultMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for (Integer denomination : keysDenom) {
            final int keyDenom = denomination;
            int valueCount = copyDenominations.get(denomination);

            while (true) {
                if (expectedSum < keyDenom || valueCount == 0) {
                    copyDenominations.put(keyDenom, valueCount);
                    break;
                }
                expectedSum -= keyDenom;
                valueCount--;

                if (resultMap.containsKey(keyDenom))
                    resultMap.put(keyDenom, resultMap.get(keyDenom) + 1);
                else
                    resultMap.put(keyDenom, 1);
            }
        }

        if (expectedSum > 0) {
            throw new NotEnoughMoneyException();
        } else  {
            this.denominations.clear();
            this.denominations.putAll(copyDenominations);
        }
        return resultMap;
    }
}
