package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @DisplayName("당첨된 로또를 구하는 기능을 테스트한다")
    @Test
    void test_getUserWinningLottos() {
        //given
        List<Lotto> userLottos = Arrays.asList(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), //FIRST(6개일치) - 1개
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 32)), //SECOND(5개일치,보너스일치) - 1개
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 33)), //SECOND(5개일치,보너스불일치) - 1개
                new Lotto(Arrays.asList(1, 2, 3, 4, 32, 43)), //FOURTH(4개일치) - 1개
                new Lotto(Arrays.asList(1, 2, 10, 15, 22, 34)), //2개일치 MISS
                new Lotto(Arrays.asList(12, 11, 3, 20, 27, 32)) //2개일치 MISS
        );
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(lotto, new BonusBall(32, lotto));

        //when
        LottoResult lottoResult = new LottoResult(userLottos, winningLotto);
        Map<Rank, Integer> userWinningLottos = lottoResult.getLottosByRank();

        //then
        assertThat(userWinningLottos).hasSize(4);
        assertThat(userWinningLottos.get(Rank.FIRST)).isEqualTo(1);
        assertThat(userWinningLottos.get(Rank.SECOND)).isEqualTo(1);
        assertThat(userWinningLottos.get(Rank.THIRD)).isEqualTo(1);
        assertThat(userWinningLottos.get(Rank.FOURTH)).isEqualTo(1);
    }
}