package domain;

public class BonusBall {
    private static final String INPUT_NUMBERS_RANGE_ERROR = "1 ~ 45 중에 입력하세요!";
    private static final String INPUT_BONUS_BALL_OVERLAPPING_ERROR = "당첨 번호와 다른 숫자를 입력하세요!";

    private final int number;

    public BonusBall(int number, Lotto winningLotto) {
        validateBonusBall(number, winningLotto);
        this.number = number;
    }

    private void validateBonusBall(int bonusBall, Lotto winningLotto) {
        validateNumberRange(bonusBall);
        validateDuplication(bonusBall, winningLotto);
    }

    private void validateDuplication(int bonusBall, Lotto winningLotto) {
        if (winningLotto.getNumbers().contains(bonusBall)) {
            throw new IllegalArgumentException(INPUT_BONUS_BALL_OVERLAPPING_ERROR);
        }
    }

    private void validateNumberRange(int bonusBall) {
        if (bonusBall > Lotto.MAX_LOTTO_NUMBER || bonusBall < Lotto.MIN_LOTTO_NUMBER) {
            throw new IllegalArgumentException(INPUT_NUMBERS_RANGE_ERROR);
        }
    }

    public int getNumber() {
        return number;
    }
}
