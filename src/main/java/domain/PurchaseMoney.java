package domain;

import utility.InputException;

public class PurchaseMoney {
    private static final String INPUT_PURCHASE_MONEY_NONE_ZERO_ERROR = "0원 이상의 금액을 입력해주세요.";
    private static final String INPUT_PURCHASE_MONEY_ERROR = "1,000원 단위로 입력해주세요.";
    private static final int ZERO = 0;

    private final int amount;

    public PurchaseMoney(int amount) {
        isValidatePurchaseMoney(amount);
        this.amount = amount;
    }

    private void isValidatePurchaseMoney(int amount) {
        validateMultipleOfPrice(amount);
        validatePositive(amount);
    }

    private void validateMultipleOfPrice(int amount) {
        if (amount % Lotto.LOTTO_UNIT_PRICE != ZERO) {
            throw new IllegalArgumentException(INPUT_PURCHASE_MONEY_ERROR); //todo: 표준 예외로 변경
        }
    }

    private void validatePositive(int amount) {
        if (amount <= ZERO) {
            throw new IllegalArgumentException(INPUT_PURCHASE_MONEY_NONE_ZERO_ERROR); //todo: 표준 예외로 변경
        }
    }

    public int getAmount() {
        return amount;
    }
}
