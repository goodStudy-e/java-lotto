package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResult {
    private final List<Lotto> userLottos;
    private final WinningLotto winningLotto;

    public LottoResult(List<Lotto> userLottos, WinningLotto winningLotto) {
        this.userLottos = new ArrayList<>(userLottos);
        this.winningLotto = new WinningLotto(winningLotto.getLotto(), winningLotto.getBonusBall());
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
