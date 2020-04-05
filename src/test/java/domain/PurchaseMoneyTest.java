package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.InputException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PurchaseMoneyTest {
    @Test
    @DisplayName("구입 금액이 0보다 작은 경우에는 예외를 던진다.")
    void throwException_ifAmountIsLowerThan0() {
        assertThatThrownBy(() -> new PurchaseMoney(-1))
                .isExactlyInstanceOf(InputException.class);
    }

    @Test
    @DisplayName("구입 금액이 1000의 배수가 아니면 예외를 던진다.")
    void test2() {
        assertThatThrownBy(() -> new PurchaseMoney(1001))
                .isExactlyInstanceOf(InputException.class);
    }
}