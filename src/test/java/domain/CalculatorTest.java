package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    @DisplayName("이득률을 구하는 기능을 테스트한다")
    @Test
    void test_getEarningRate() {
        //given
        PurchaseMoney purchaseMoney = new PurchaseMoney(3000);
        Map<Rank, Integer> userWinningLottos = new HashMap<>();
        userWinningLottos.put(Rank.THIRD, 1);
        userWinningLottos.put(Rank.FOURTH, 1);

        //when
        double earningRate = Calculator.calculateEarningRate(userWinningLottos, purchaseMoney);

        //then
        double expectedRate = (double) (Rank.THIRD.getWinningMoney() + Rank.FOURTH.getWinningMoney()) / purchaseMoney.getAmount() * 100;
        assertThat(earningRate).isEqualTo(expectedRate);
    }
}