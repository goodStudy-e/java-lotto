package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResult {
    private static final int PERCENTAGE = 100;

    private List<Lotto> userLottos;
    private WinningLotto winningLotto;

    public LottoResult(List<Lotto> userLottos, WinningLotto winningLotto) {
        this.userLottos = new ArrayList<>(userLottos);
        this.winningLotto = new WinningLotto(winningLotto.getLotto(), winningLotto.getBonusBall());
    }

    public double calculateEarningRate(Map<Rank, Integer> lottoByRank, PurchaseMoney purchaseMoney) {
        return (double) getTotalWinningMoney(lottoByRank) / purchaseMoney.getAmount() * PERCENTAGE;
    }

    private long getTotalWinningMoney(Map<Rank, Integer> rankMap) {
        return Arrays.stream(Rank.values())
                .mapToLong(rank -> rank.getWinningMoney() * rankMap.getOrDefault(rank, 0))
                .sum();
    }

    public Map<Rank, Integer> getLottosByRank() {
        List<Rank> userRanks = getUserWinningLottos();
        return userRanks.stream()
                .collect(Collectors.groupingBy(rank -> rank))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()));
    }

    private List<Rank> getUserWinningLottos() {
        return userLottos.stream()
                .map(winningLotto::match)
                .filter(rank -> !rank.equals(Rank.MISS))
                .collect(Collectors.toList());
    }
}
