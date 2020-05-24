package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoStoreTest {

    @DisplayName("로또를 발급하는 기능을 테스트한다")
    @Test
    void test_generateLottos() {
        //given
        PurchaseMoney purchaseMoney = new PurchaseMoney(3000);

        //when
        LottoStore lottoStore = new LottoStore(purchaseMoney);
        List<Lotto> lottos = lottoStore.issueUserLottos();

        //then
        assertThat(lottos).hasSize(3);
    }
}