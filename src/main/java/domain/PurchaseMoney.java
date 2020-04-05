package domain;

import utility.InputException;

public class PurchaseMoney {
    private static final String INPUT_PURCHASE_MONEY_NONE_ZERO_ERROR = "0원 이상의 금액을 입력해주세요.";
    private static final String INPUT_PURCHASE_MONEY_ERROR = "1,000원 단위로 입력해주세요.";

    private final int amount;

    public PurchaseMoney(int amount) {
        validatePositive(amount);
        validateMultipleOf1000(amount);

        this.amount = amount;
    }

    private void validateMultipleOf1000(int amount) {
        if (amount % Lotto.LOTTO_UNIT_PRICE != 0) {
            throw new InputException(INPUT_PURCHASE_MONEY_ERROR);
        }
    }

    private void validatePositive(int amount) {
        if (amount <= 0) {
            throw new InputException(INPUT_PURCHASE_MONEY_NONE_ZERO_ERROR);
        }
    }

    public int getAmount() {
        return amount;
    }
}
