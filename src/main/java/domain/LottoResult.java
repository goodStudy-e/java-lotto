package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class LottoResult {
    private static final int PERCENTAGE = 100;

    public static void printWinningStatistics(List<Lotto> userLottos, WinningLotto winningLotto) {
        List<Rank> userRanks = getUserRemovedMISSRanks(userLottos, winningLotto);
        Map<Rank, Integer> rankMap = getRankMap(userRanks, new HashMap<>());
        long purchaseMoney = Lotto.LOTTO_UNIT_PRICE * userLottos.size();

        System.out.println("당첨통계");
        System.out.println("---------");
        double earningsRate = (double) getTotalWinningMoney(rankMap) / purchaseMoney * PERCENTAGE;
        System.out.println("총 수익률은 " + earningsRate + "% 입니다.");
    }

    private static long getTotalWinningMoney(Map<Rank, Integer> rankMap) {
        long sum = 0;
        for (Rank rank : Rank.values()) {
            printRank(rankMap, rank);
            sum += getRankSum(rank, rankMap);
        }
        return sum;
    }

    private static long getRankSum(Rank rank, Map<Rank, Integer> rankMap) {
        long rankWinningMoney = rank.getWinningMoney();
        int rankNumber = rankMap.getOrDefault(rank, 0);
        return rankWinningMoney * rankNumber;
    }

    private static void printRank(Map<Rank, Integer> rankMap, Rank rank) {
        long rankWinningMoney = rank.getWinningMoney();
        int rankNumber = rankMap.getOrDefault(rank, 0);

        System.out.print(rank.getCountOfMatch() + "개일치");
        isEqualToSECOND(rank);
        System.out.print("(" + rankWinningMoney + "원)- ");
        System.out.println(rankNumber + "개");
        System.out.println("추가금액: " + getRankSum(rank, rankMap));
    }


    private static void isEqualToSECOND(Rank rank) {
        if (rank.equals(Rank.SECOND)) {
            System.out.print(", 보너스 볼 일치");
        }
    }

    private static Map<Rank, Integer> getRankMap(List<Rank> userRanks, Map<Rank, Integer> rankMap) {
        return userRanks.stream()
                .collect(groupingBy(rank -> rank))
                .entrySet().stream()
                .collect(toMap(Map.Entry::getKey, entry -> entry.getValue().size()));
    }

    private static List<Rank> getUserRemovedMISSRanks(List<Lotto> userLottos, WinningLotto winningLotto) {
        return userLottos.stream()
                .map(winningLotto::match)
                .filter(rank -> !rank.equals(Rank.MISS))
                .collect(toList());
    }
}
