package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoTest {

    @DisplayName("당첨복권을 생성한다")
    @Test
    void test_generateWinningLotto() {
        //given
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusBallNumber = 7;
        BonusBall bonusBall = new BonusBall(bonusBallNumber, lotto);

        //when
        WinningLotto winningLotto = new WinningLotto(lotto, bonusBall);

        //then
        assertThat(winningLotto.getBonusBall().getNumber()).isEqualTo(bonusBallNumber);
        assertThat(winningLotto.getLotto().getNumbers()).isEqualTo(lotto.getNumbers());
    }

    @DisplayName("복권의 당첨여부를 확인하는 기능을 테스트한다")
    @Test
    void test_matchFunction() {
        //given
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto userLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 10));
        int bonusNumber = 7;
        BonusBall bonusBall = new BonusBall(bonusNumber, lotto);

        //when
        WinningLotto winningLotto = new WinningLotto(lotto, bonusBall);
        Rank answer = winningLotto.match(userLotto);

        //then
        assertThat(answer).isEqualTo(Rank.THIRD);
    }
}