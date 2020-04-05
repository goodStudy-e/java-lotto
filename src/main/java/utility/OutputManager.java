package utility;

import domain.Rank;

import java.util.Arrays;
import java.util.Map;

public class OutputManager {

    public static void printResult(Map<Rank, Integer> numberOfWinningLottosByRank, double earningRate) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        printWinningLottoInfoByRank(numberOfWinningLottosByRank);
        System.out.println("총 수익률은 " + earningRate + "% 입니다.");
    }

    private static void printWinningLottoInfoByRank(Map<Rank, Integer> rankMap) {
        Arrays.stream(Rank.values())
                .forEach(rank -> printRankInfo(rankMap, rank));
    }

    private static void printRankInfo(Map<Rank, Integer> rankMap, Rank rank) {
        System.out.print(rank.getCountOfMatch() + "개일치");
        isEqualToSECOND(rank);
        System.out.print("(" + rank.getWinningMoney() + "원)- ");
        System.out.println(rankMap.getOrDefault(rank, 0) + "개");
    }

    private static void isEqualToSECOND(Rank rank) {
        if (rank.equals(Rank.SECOND)) {
            System.out.print(", 보너스 볼 일치");
        }
    }
}
