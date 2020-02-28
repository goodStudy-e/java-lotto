package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @Test
    void 당첨갯수를_출력한다() { //테스트 코드로 리팩토링이 필요한 것인가??
        List<Lotto> userLottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), //6개일치
                new Lotto(List.of(1, 2, 3, 4, 5, 32)), //5개일치(보너스일치)
                new Lotto(List.of(1, 2, 3, 4, 32, 43)), //4개일치
                new Lotto(List.of(1, 2, 10, 15, 22, 34)),
                new Lotto(List.of(12, 6, 3, 20, 27, 32))
        );
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 32);

        List<Rank> userRanks = userLottos.stream()
                .map(userLotto -> winningLotto.match(userLotto))
                .collect(toList());

        int firstRankNumber = (int) userRanks.stream()
                .filter(rank -> rank.equals(Rank.FIRST))
                .count();
        assertThat(1).isEqualTo(firstRankNumber);

        int secondRankNumber = (int) userRanks.stream()
                .filter(rank -> rank.equals(Rank.SECOND))
                .count();
        assertThat(1).isEqualTo(secondRankNumber);

        int fourthRankNumber = (int) userRanks.stream()
                .filter(rank -> rank.equals(Rank.FOURTH))
                .count();
        assertThat(1).isEqualTo(fourthRankNumber);
    }
}