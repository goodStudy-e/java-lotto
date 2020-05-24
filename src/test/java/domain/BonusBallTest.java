package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BonusBallTest {

    @DisplayName("보너스볼 객체를 생성한다")
    @Test
    void test_createBonusBall() {
        //given
        int bonusBallNumber = 7;
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        //when
        BonusBall bonusBall = new BonusBall(bonusBallNumber, lotto);

        //then
        assertThat(bonusBall.getNumber()).isEqualTo(bonusBallNumber);
    }

    @DisplayName("보너스볼 로또번호이와 중복되면 예외를 발생한다")
    @Test
    void test_DuplicationException() {
        //given
        int bonusBallNumber = 6;
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        //when then
        assertThatThrownBy(() -> new BonusBall(bonusBallNumber, lotto))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스볼이 로또숫자 범위보다 작으면 예외를 발생시킨다")
    @Test
    void test_OverRangeNumberExceptionZero() {
        //given
        int bonusBallNumber = 0;
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        //when then
        assertThatThrownBy(() -> new BonusBall(bonusBallNumber, lotto))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스볼이 로또숫자 범위보다 크면 예외를 발생시킨다")
    @Test
    void test_OverRangeNumberException() {
        //given
        int bonusBallNumber = 46;
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        //when then
        assertThatThrownBy(() -> new BonusBall(bonusBallNumber, lotto))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}