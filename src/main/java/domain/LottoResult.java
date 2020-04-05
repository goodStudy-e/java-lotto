package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class LottoResult {
    private static final int PERCENTAGE = 100;

    public static List<Rank> getUserWinningLottos(List<Lotto> userLottos, WinningLotto winningLotto) {
        return userLottos.stream()
                .map(winningLotto::match)
                .filter(rank -> !rank.equals(Rank.MISS))
                .collect(Collectors.toList());
    }

    public static double getEarningRate(Map<Rank, Integer> numberOfWinningLottosByRank, PurchaseMoney purchaseMoney) {
        return (double) getTotalWinningMoney(numberOfWinningLottosByRank) / purchaseMoney.getAmount() * PERCENTAGE;
    }

    private static long getTotalWinningMoney(Map<Rank, Integer> rankMap) {
        return Arrays.stream(Rank.values())
                .mapToLong(rank -> rank.getWinningMoney() * rankMap.getOrDefault(rank, 0))
                .sum();
    }

    public static Map<Rank, Integer> getLottosByRank(List<Rank> userRanks) {
        return userRanks.stream()
                .collect(groupingBy(rank -> rank))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()));
    }

}
