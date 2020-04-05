package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class LottoResultTest {

    @DisplayName("당첨된로또를_구한다")
    @Test
    void test_getUserWinningLottos() {
        //given
        List<Lotto> userLottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), //FIRST(6개일치) - 1개
                new Lotto(List.of(1, 2, 3, 4, 5, 32)), //SECOND(5개일치,보너스일치) - 1개
                new Lotto(List.of(1, 2, 3, 4, 32, 43)), //FOURTH(4개일치) - 1개
                new Lotto(List.of(1, 2, 10, 15, 22, 34)), //2개일치 MISS
                new Lotto(List.of(12, 11, 3, 20, 27, 32)) //2개일치 MISS
        );

        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        //when
        List<Rank> userWinningLottos = LottoResult.getUserWinningLottos(userLottos, winningLotto);

        //then
        assertThat(3).isEqualTo(userWinningLottos.size());
    }

    @DisplayName("사용자의_당첨로또를_등수별로_나눈다")
    @Test
    void test_getLottosByRank() {
        //given
        List<Rank> userWinningLottos = List.of(
                Rank.FIRST,
                Rank.SECOND,
                Rank.FOURTH
        );

        //when
        Map<Rank, Integer> userLottosByRank = LottoResult.getLottosByRank(userWinningLottos);

        //then
        assertThat(1).isEqualTo(userLottosByRank.get(Rank.FIRST));
        assertThat(1).isEqualTo(userLottosByRank.get(Rank.SECOND));
        assertThat(1).isEqualTo(userLottosByRank.get(Rank.FOURTH));
        assertThat(3).isEqualTo(userLottosByRank.size());
    }

    @DisplayName("이득률을_구한다")
    @Test
    void test_getEarningRate() {
        //given
        PurchaseMoney purchaseMoney = new PurchaseMoney(3000);
        Map<Rank, Integer> userWinningLottos = Map.of(
                Rank.THIRD, 1, // 1,500,000
                Rank.FOURTH, 1 // 50,000
        );

        //when
        double earningRate = LottoResult.getEarningRate(userWinningLottos, purchaseMoney);

        //then
        double expectedEarningRate = (double) (1500000 + 50000) / purchaseMoney.getAmount() * 100;
        assertThat(expectedEarningRate).isEqualTo(earningRate);
    }
}