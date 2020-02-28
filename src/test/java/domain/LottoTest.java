package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {

    @DisplayName("Lotto를_출력한다")
    @Test
    void LottoToString() {
        List<Integer> lottoNumber = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(lottoNumber);
        assertThat(1 + 2).isEqualTo(3);
        assertThat("[1, 2, 3, 4, 5, 6]").isEqualTo(lotto.toString());
    }
}