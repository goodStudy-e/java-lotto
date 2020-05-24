package domain;

import java.util.Arrays;
import java.util.Map;

public class Calculator {
    private static final int PERCENTAGE = 100;

    private Calculator() {
        throw new AssertionError();
    }

    public static double calculateEarningRate(Map<Rank, Integer> lottoByRank, PurchaseMoney purchaseMoney) {
        return (double) getTotalWinningMoney(lottoByRank) / purchaseMoney.getAmount() * PERCENTAGE;
    }

    private static long getTotalWinningMoney(Map<Rank, Integer> rankMap) {
        return Arrays.stream(Rank.values())
                .mapToLong(rank -> rank.getWinningMoney() * rankMap.getOrDefault(rank, 0))
                .sum();
    }
}
