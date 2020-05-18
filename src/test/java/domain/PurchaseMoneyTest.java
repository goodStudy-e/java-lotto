package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseMoneyTest {
    @Test
    @DisplayName("구입 금액이 0보다 작은 경우에는 예외를 던진다.")
    void throwException_ifAmountIsLowerThan0() {
        assertThatThrownBy(() -> new PurchaseMoney(-1))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입 금액이 1000의 배수가 아니면 예외를 던진다.")
    void throwException_ifAmountIsNot1000Multiple() {
        assertThatThrownBy(() -> new PurchaseMoney(1001))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

}