package utility;

import domain.Lotto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class InputManagerTest {

    @Test
    public void 고객로또번호를_생성한다() {
        int purchaseMoney = 6000;
        int resultNumber = 6;
        List<Lotto> userLottoList =  InputManager.getUserLottos(purchaseMoney);
        //로또 내용은 확인할 수 없다. 랜덤으로 생성되기 때문이다.
        assertThat(resultNumber).isEqualTo(userLottoList.size());
    }

}