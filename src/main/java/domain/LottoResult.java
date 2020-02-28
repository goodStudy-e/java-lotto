package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {

    public static void printWinningStatistics(List<Lotto> userLottos, WinningLotto winningLotto) {
        List<Rank> userRanks = getUserRanks(userLottos, winningLotto);
        System.out.println("당첨통계");
        System.out.println("---------");
        Map<Rank, Integer> rankMap = new HashMap<>();

        for (Rank rank : userRanks) {
            if (!rankMap.containsKey(rank)) {
                rankMap.put(rank, 1);
                continue;
            }
            rankMap.put(rank, rankMap.get(rank) + 1);
        }

        long sum = 0;
        for (Rank rank : Rank.values()) { //todo: 리팩토링
            if (rank.equals(Rank.MISS)) {
                continue;
            }

            long rankWinningMoney = rank.getWinningMoney();
            int rankNumber = rankMap.getOrDefault(rank, 0);

            System.out.print(rank.getCountOfMatch() + "개일치");
            if (rank.equals(Rank.SECOND)) {
                System.out.print(", 보너스 볼 일치");
            }
            System.out.print("(" + rankWinningMoney + "원)- ");
            System.out.println(rankMap.getOrDefault(rank, 0) + "개");
            System.out.println("추가금액: " + rankWinningMoney * rankNumber);
            sum += rankWinningMoney * rankNumber;
        }

        long purchaseMoney = Lotto.LOTTO_UNIT_PRICE * userLottos.size();
        double earningsRate = (double) sum / purchaseMoney * 100;
        System.out.println("총 수익률은 " + earningsRate + "% 입니다.");
    }

    private static List<Rank> getUserRanks(List<Lotto> userLottos, WinningLotto winningLotto) {
        List<Rank> userRanks = new ArrayList<>();

        for (Lotto userLotto : userLottos) { //todo: stream 으로 변환 가능할까?
            userRanks.add(winningLotto.match(userLotto));
        }
        return userRanks;
    }
}
