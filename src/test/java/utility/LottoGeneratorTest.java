package utility;

import domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Lotto 생성기")
class LottoGeneratorTest {

    @DisplayName("Lotto를_생성한다")
    @Test
    void Lotto를_생성한다() {
        Lotto lotto = LottoGenerator.generateLotto();
        int lottoDistinctLength = (int) lotto.getNumbers().stream()
                .distinct()
                .count();
        assertThat(Lotto.LOTTO_LENGTH).isEqualTo(lottoDistinctLength);
    }
}