package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또를 생성한다")
    @Test
    void test_createLotto() {
        //given
        List<Integer> lottoNumber = Arrays.asList(1, 2, 3, 4, 5, 6);

        //when
        Lotto lotto = new Lotto(lottoNumber);

        //then
        assertThat(lotto.getNumbers()).isEqualTo(lottoNumber);
    }

    @DisplayName("로또번호의 길이가 다르면 예외를 발생시킨다")
    @Test
    void test_LottoNumberLengthException() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5);

        //when //then
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또번호가 중복되면 예외를 발생시킨다")
    @Test
    void test_LottoNumberDuplication() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 5);

        //when //then
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또번호 중에 로또숫자 범위보다 큰 값이 있으면 예외를 발생시킨다")
    @Test
    void test_LottoNumberOverRange() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 46);

        //when //then
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또번호 중에 로또숫자 범위보다 작은 값이 있으면 예외를 발생시킨다")
    @Test
    void test_LottoNumberUnderRange() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(0, 1, 2, 3, 4, 5);

        //when //then
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}